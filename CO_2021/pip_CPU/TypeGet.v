`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:19:02 11/19/2021 
// Design Name: 
// Module Name:    TypeGet 
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
`define J 6'b000010
`define JAL 6'b000011
`define JR 6'b001000
`define SLT 6'b101010

`define ADDUtype 6'd1
`define SUBUtype 6'd2
`define ORItype 6'd3
`define LWtype 6'd4
`define SWtype 6'd5
`define BEQtype 6'd6
`define LUItype 6'd7
`define Jtype 6'd8
`define JALtype 6'd9
`define JRtype 6'd10
`define SLTtype 6'd11

`define beq 3'b001
`define alu 3'b010
`define dm 3'b011
`define pc8 3'b001
`define RA 5'd31
module TypeGet(
    input [31:0] ins,
    output [5:0] type,
    output [2:0] urs,
    output [2:0] urt,
    output [2:0] wd,
    output [4:0] wr,
    output [4:0] rs,
    output [4:0] rt
    );
    wire [5:0] op, funct;
    wire [4:0] rd;
    wire Rtype;
    assign op = ins[31:26];
    assign funct = ins[5:0];
    assign rs = ins[25:21];
    assign rt = ins[20:16];
    assign rd = ins[15:11];
    assign Rtype = (op == 0);
    

    assign type = (Rtype & (funct == `ADDU))? `ADDUtype :
                  (Rtype & (funct == `SUBU))? `SUBUtype :
                  (op == `ORI)? `ORItype :
                  (op == `LW)? `LWtype :
                  (op == `SW)? `SWtype :
                  (op == `BEQ)? `BEQtype :
                  (op == `LUI)? `LUItype :
                  (op == `J)? `Jtype :
                  (op == `JAL)? `JALtype :
                  (Rtype & (funct == `JR))? `JRtype :
                  (Rtype & (funct == `SLT))? `SLTtype : 0;
    assign urs = ((type == `ADDUtype) | (type == `SUBUtype) | (type == `ORItype) | (type == `LWtype) | (type == `SWtype) | (type == `LUItype) | (type == `SLTtype))? `alu :
                 ((type == `BEQtype) | (type == `JRtype))? `beq : 2'b00;
    assign urt = ((type == `ADDUtype) | (type == `SUBUtype) | (type == `SLTtype))? `alu :
                 (type == `BEQtype)? `beq :
                 ((type == `LWtype) | (type == `SWtype))? `dm : 2'b00;
    assign wd = ((type == `ADDUtype) | (type == `SUBUtype) | (type == `ORItype) | (type == `LUItype) | (type == `SLTtype))? `alu :
                ((type == `LWtype))? `dm :
                (type == `JALtype)? `pc8 : 2'b00;
    assign wr = ((type == `ADDUtype) | (type == `SUBUtype) | (type == `SLTtype))? rd :
                ((type == `ORItype) | (type == `LUItype) | (type == `LWtype))? rt :
                (type == `JALtype)? `RA : 5'd0;
                  
endmodule
