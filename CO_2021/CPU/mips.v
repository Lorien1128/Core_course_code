`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:20:22 11/13/2021 
// Design Name: 
// Module Name:    mips 
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
module mips(
    input clk,
    input reset
    );


    //ifu
    wire [5:0] funct, op;
    wire [4:0] shamt, rd, rt, rs;
    wire [15:0] imm;
    wire [25:0] addr;
    wire [31:0] pc, pc4;
    //datapath
    wire zero;
    wire [31:0] ra;
    //control
    wire [1:0] WRsel, WDsel, ALUOp;
    wire RFWr, EXTOp, Bsel, DMWr, Br, LUIsel, Jal, Jr, Slt;



    IFU ifu (
    .clk(clk), 
    .reset(reset), 
    .Br(Br), 
    .zero(zero), 
    .Jal(Jal), 
    .Jr(Jr), 
    .ra(ra), 
    .funct(funct), 
    .shamt(shamt), 
    .rd(rd), 
    .rt(rt), 
    .rs(rs), 
    .op(op), 
    .imm(imm), 
    .addr(addr), 
    .pcout(pc), 
    .pc4(pc4)
    );




    Control control (
    .funct(funct), 
    .op(op), 
    .WRsel(WRsel), 
    .WDsel(WDsel), 
    .RFWr(RFWr), 
    .EXTOp(EXTOp), 
    .Bsel(Bsel), 
    .ALUOp(ALUOp), 
    .DMWr(DMWr), 
    .Br(Br), 
    .LUIsel(LUIsel), 
    .Jal(Jal), 
    .Jr(Jr),
    .Slt(Slt)
    );




    DataPath datapath (
    .clk(clk), 
    .reset(reset), 
    .pc(pc), 
    .pc4(pc4), 
    .shamt(shamt), 
    .rd(rd), 
    .rt(rt), 
    .rs(rs), 
    .imm(imm), 
    .addr(addr), 
    .WRsel(WRsel), 
    .WDsel(WDsel), 
    .RFWr(RFWr), 
    .EXTOp(EXTOp), 
    .Bsel(Bsel), 
    .ALUOp(ALUOp), 
    .DMWr(DMWr), 
    .Br(Br), 
    .LUIsel(LUIsel), 
    .Jal(Jal), 
    .Jr(Jr), 
    .Slt(Slt),
    .zero(zero), 
    .ra(ra)
    );



endmodule

