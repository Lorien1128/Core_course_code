`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:41:49 11/13/2021 
// Design Name: 
// Module Name:    DataPath 
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
`define RA 5'd31
module DataPath(
    input clk,
    input reset,
    input [31:0] pc,
    input [31:0] pc4,
    input [4:0] shamt,
    input [4:0] rd,
    input [4:0] rt,
    input [4:0] rs,
    input [15:0] imm,
    input [25:0] addr,
    input [1:0] WRsel,
    input [1:0] WDsel,
    input RFWr,
    input EXTOp,
    input Bsel,
    input [1:0] ALUOp,
    input DMWr,
    input Br,
    input LUIsel,
    input Jal,
    input Jr,
    input Slt,
    output zero,
    output [31:0] ra
    );

    
    //rf
    wire [4:0] A3;
    wire [31:0] WD, RD1, RD2;
    //ext
    wire [31:0] EXTout;
    //alu
    wire [31:0] B, ALUresult;
    wire less;
    //dm
    wire [31:0] dout;



    assign A3 = (WRsel == 2'b00)? rt :
                (WRsel == 2'b01)? rd :
                (WRsel == 2'b10)? `RA : 0;
    assign WD = (Slt == 1)? less :
                (WDsel == 2'b00)? ALUresult :
                (WDsel == 2'b01)? dout :
                (WDsel == 2'b10)? pc4 : 0;
    RF rf (
    .A1(rs), 
    .A2(rt), 
    .A3(A3), 
    .WD(WD), 
    .clk(clk), 
    .reset(reset), 
    .we(RFWr), 
    .pc(pc), 
    .RD1(RD1), 
    .RD2(RD2)
    );
    assign ra = RD1;




    EXT ext (
    .EXTin(imm), 
    .EXTOp(EXTOp), 
    .LUIsel(LUIsel), 
    .EXTout(EXTout)
    );




    assign B = (Bsel == 1)? EXTout : RD2;
    ALU alu (
    .A(RD1), 
    .B(B), 
    .ALUOp(ALUOp), 
    .zero(zero), 
    .ALUresult(ALUresult),
    .less(less)
    );




    DM dm (
    .clk(clk), 
    .reset(reset), 
    .we(DMWr), 
    .pc(pc), 
    .addr(ALUresult), 
    .din(RD2), 
    .dout(dout)
    );




endmodule
