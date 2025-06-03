`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    22:11:56 12/09/2021 
// Design Name: 
// Module Name:    CP0 
// Project Name: 
// Target Devices: 
// Tool versions: 
// Description: 
//
// Dependencies: 
//
// Revision: 
// Revision 0.01 - File Created
// Additional Comments: 
//
//////////////////////////////////////////////////////////////////////////////////
`define SR mem[12]
`define Cause mem[13]
`define EPC mem[14]
`define PRId mem[15]

module CP0(
    input [4:0] addr,
    input [31:0] din,
    input [31:0] pc,
    input [31:0] macro_pc,
    input [4:0] ExcCode,
    input [5:0] HwInt,
    input WE,
    input Exlclr,
    input clk,
    input reset,
    input W_Bd,
    output IntReq,
    output [31:0] EPC,
    output [31:0] dout,
    output reg answer_interrupt
    );

    reg [31:0] mem[0:31];
    wire [5:0] IM, IP;
    wire IE, Exl, BD;

    assign IM = `SR[15:10];
    assign IE = `SR[0];
    assign Exl = `SR[1];
    assign BD = `Cause[31];

    wire Reqin, Reqout;
    assign Reqin = (ExcCode != 0) & ~Exl;
    assign Reqout = (|(IM & HwInt)) & IE & ~Exl;
    assign IntReq = Reqin | Reqout;

    always @(posedge clk) begin
        if (answer_interrupt == 0) begin
            answer_interrupt <= (IM[2] & HwInt[2] & IE & ~Exl);
        end
        else answer_interrupt <= 0;
    end
    

    wire [31:0] epc;
    assign epc = (Reqout & W_Bd == 1)? macro_pc - 4 :
                 (Reqout & W_Bd != 1)? macro_pc :
                 (W_Bd == 1)? pc - 4 : pc;

    wire [4:0] exccode;
    assign exccode = (Reqout == 1)? 5'b0 : ExcCode;

    always @(posedge clk) begin
        if (reset) begin
            `Cause <= 0;
        end
        else begin
            `Cause[15:10] <= HwInt;
        end
    end

    always @(posedge clk) begin
        if (reset) begin
            `SR <= 0;
            `EPC <= 0;
            `PRId <= 0;
        end
        else if (WE) begin
            mem[addr] <= din;
        end
        else if (IntReq) begin
            `SR[1] <= 1;
            `Cause[6:2] <= exccode;
            `Cause[31] <= W_Bd;
            `EPC <= epc;
        end
        else if (Exlclr == 1) begin
            `SR[1] <= 0;
        end
    end

    assign EPC = `EPC;
    assign dout = mem[addr];

endmodule
