`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:14:00 11/12/2021 
// Design Name: 
// Module Name:    IFU 
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
module IFU(
    input clk,
    input reset,
    input we,
    input Br,
    input zero,
    input Jal,
    input Jr,
    input [31:0] ra,
    input [15:0] immin,
    input [25:0] addrin,
    output [5:0] funct,
    output [4:0] shamt,
    output [4:0] rd,
    output [4:0] rt,
    output [4:0] rs,
    output [5:0] op,
    output [15:0] immout,
    output [25:0] addrout,
    output [31:0] pcout,
    output [31:0] pc8,
    output [31:0] ins
    );
    wire [15:0] imm16;
    wire [25:0] imm26;
    wire [31:0] pcplus4;
    reg [31:0] pc;

    always @(posedge clk) begin
        if (reset) begin
            pc <= 32'h0000_3000;
        end
        else if (we) begin
            if (Br & zero) begin
                pc <= $signed(pc) + $signed({immin, 2'b0});
            end
            else if (Jal) begin
                pc <= {pc[31:28], addrin, 2'b0};
            end
            else if (Jr) begin
                pc <= ra;
            end
            else begin
                pc <= pc + 4;
            end
        end
    end

    wire [31:0] IMD;
    IM im (
    .IMA(pc), 
    .IMD(IMD)
    );

    assign funct = IMD[5:0];
    assign shamt = IMD[10:6];
    assign rd = IMD[15:11];
    assign rt = IMD[20:16];
    assign rs = IMD[25:21];
    assign op = IMD[31:26];
    assign immout = IMD[15:0];

    assign addrout = IMD[25:0];

    assign pcplus4 = pc + 4;
    assign pcout = pc;
    assign pc8 = pcplus4 + 4;
    assign ins = IMD;

    initial begin
        pc <= 32'h0000_3000;
    end

endmodule
