`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:59:44 11/12/2021 
// Design Name: 
// Module Name:    Control 
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
`define ADDU 6'b100001
`define SUBU 6'b100011
`define ORI 6'b001101
`define LW 6'b100011
`define SW 6'b101011
`define BEQ 6'b000100
`define LUI 6'b001111
`define JAL 6'b000011
`define JR 6'b001000

module Control(
    input [5:0] funct,
    input [5:0] op,
    output [1:0] WRsel,
    output [1:0] WDsel,
    output RFWr,
    output EXTOp,
    output Bsel,
    output [1:0] ALUOp,
    output DMWr,
    output Br,
    output LUIsel,
    output Jal,
    output Jr,
    output Slt
    );
    wire addu, subu, ori, lw, sw, beq, lui, jal, jr, slt, Rtype;
    assign Rtype = (op == 6'b000000);
    //AND LOGIC
    assign addu = Rtype & (funct == `ADDU);
    assign subu = Rtype & (funct == `SUBU);
    assign slt = Rtype & (funct == 6'b101010);
    assign ori = (op == `ORI);
    assign lw = (op == `LW);
    assign sw = (op == `SW);
    assign beq = (op == `BEQ);
    assign lui = (op == `LUI);
    assign jal = (op == `JAL);
    assign jr = Rtype & (funct == `JR);
    //OR LOGIC
    assign WRsel = (addu | subu | slt)? 2'b01 :
                   (jal)? 2'b10 : 2'b00;
    assign WDsel = (lw)? 2'b01 :
                   (jal)? 2'b10 : 2'b00;
    assign RFWr = (addu | subu | ori | lw | lui | jal | slt);
    assign EXTOp = (lw | sw);
    assign Bsel = (ori | lw | sw | lui);
    assign ALUOp = (subu | beq)? 2'b01 :
                   (ori)? 2'b10 : 2'b00;
    assign DMWr = sw;
    assign Br = beq;
    assign LUIsel = lui;
    assign Jal = jal;
    assign Jr = jr;
    assign Slt = slt;
endmodule
