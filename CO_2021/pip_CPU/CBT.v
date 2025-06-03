`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:34:51 11/18/2021 
// Design Name: 
// Module Name:    CBT 
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
`define beq 3'b001
`define alu 3'b010
`define dm 3'b011
`define pc8 3'b001
`define RA 5'd31

module CBT(
    input [31:0] D_ins_o,
    input [31:0] E_ins_o,
    input [31:0] M_ins_o,
    input [31:0] W_ins_o,
    output reg [1:0] M1sel,
    output reg [1:0] M2sel,
    output reg [1:0] M3sel,
    output reg [1:0] M4sel,
    output reg M5sel,
    output reg stall
    );
    wire [5:0] Dtype, Etype, Mtype, Wtype;
    wire [2:0] Durs, Durt, Eurs, Eurt, Murs, Murt, Wurs, Wurt;
    wire [2:0] Dw, Ew, Mw, Ww;
    wire [4:0] Dwr, Ewr, Mwr, Wwr;
    wire [4:0] Drs, Drt, Ers, Ert, Mrs, Mrt, Wrs, Wrt; 
    TypeGet dtype (
    .ins(D_ins_o), 
    .type(Dtype),
    .urs(Durs),
    .urt(Durt),
    .rs(Drs),
    .rt(Drt)
    );
    TypeGet etype (
    .ins(E_ins_o), 
    .type(Etype),
    .urs(Eurs),
    .urt(Eurt),
    .wd(Ew),
    .wr(Ewr),
    .rs(Ers),
    .rt(Ert)
    );
    TypeGet mtype (
    .ins(M_ins_o), 
    .type(Mtype),
    .urt(Murt),
    .wd(Mw),
    .wr(Mwr),
    .rt(Mrt)
    );
    TypeGet wtype (
    .ins(W_ins_o), 
    .type(Wtype),
    .wd(Ww),
    .wr(Wwr)
    );



    always @(*) begin
        //D
        if ((Durs == `alu) & (Ew == `dm) & (Drs == Ewr) & (Drs != 0)) stall = 1;
        else if ((Durt == `alu) & (Ew == `dm) & (Drt == Ewr) & (Drt != 0)) stall = 1;
        else if ((Durs == `beq) & (Ew == `alu) & (Drs == Ewr) & (Drs != 0)) stall = 1;
        else if ((Durt == `beq) & (Ew == `alu) & (Drt == Ewr) & (Drt != 0)) stall = 1;
        else if ((Durt == `beq) & (Ew == `dm) & (Drt == Ewr) & (Drt != 0)) stall = 1;
        else if ((Durs == `beq) & (Ew == `dm) & (Drs == Ewr) & (Drs != 0)) stall = 1;
        else if ((Durs == `beq) & (Mw == `dm) & (Drs == Mwr) & (Drs != 0)) stall = 1;
        else if ((Durt == `beq) & (Mw == `dm) & (Drt == Mwr) & (Drt != 0)) stall = 1;
        else stall = 0;

        if ((Durs == `beq)& (Mw == `alu) & (Drs == Mwr) & (Drs != 0)) M1sel = 2'b10;
        else if ((Durs == `beq) & (Mw == `pc8) & (Drs == Mwr) & (Drs != 0)) M1sel = 2'b11;
        else if (((Durs == `beq)|(Durs == `alu)) & (Ww == `alu) & (Drs == Wwr) & (Drs != 0)) M1sel = 2'b01;
        else if (((Durs == `beq)|(Durs == `alu)) & (Ww == `dm) & (Drs == Wwr) & (Drs != 0)) M1sel = 2'b01;
        else if (((Durs == `beq)|(Durs == `alu)) & (Ww == `pc8) & (Drs == Wwr) & (Drs != 0)) M1sel = 2'b01;
        else M1sel = 2'b00;

        if ((Durt == `beq) & (Mw == `alu) & (Drt == Mwr) & (Drt != 0)) M2sel = 2'b10;
        else if ((Durt == `beq) & (Mw == `pc8) & (Drt == Mwr) & (Drt != 0)) M2sel = 2'b11;
        else if (((Durt == `beq)|(Durt == `alu)|(Durt == `dm)) & (Ww == `alu) & (Drt == Wwr) & (Drt != 0)) M2sel = 2'b01;
        else if (((Durt == `beq)|(Durt == `alu)|(Durt == `dm)) & (Ww == `dm) & (Drt == Wwr) & (Drt != 0)) M2sel = 2'b01;
        else if (((Durt == `beq)|(Durt == `alu)|(Durt == `dm)) & (Ww == `pc8) & (Drt == Wwr) & (Drt != 0)) M2sel = 2'b01;
        else M2sel = 2'b00;

        //E
        if ((Eurs == `alu) & (Mw == `alu) & (Ers == Mwr) & (Ers != 0)) M3sel = 2'b10;
        else if ((Eurs == `alu) & (Mw == `pc8) & (Ers == Mwr) & (Ers != 0)) M3sel = 2'b11;
        else if ((Eurs == `alu) & (Ww == `alu) & (Ers == Wwr) & (Ers != 0)) M3sel = 2'b01;
        else if ((Eurs == `alu) & (Ww == `dm) & (Ers == Wwr) & (Ers != 0)) M3sel = 2'b01;
        else if ((Eurs == `alu) & (Ww == `pc8) & (Ers == Wwr) & (Ers != 0)) M3sel = 2'b01;
        else M3sel = 2'b00;

        if ((Eurt == `alu) & (Mw == `alu) & (Ert == Mwr) & (Ert != 0)) M4sel = 2'b10;
        else if ((Eurt == `alu) & (Mw == `pc8) & (Ert == Mwr) & (Ert != 0)) M4sel = 2'b11;
        else if (((Eurt == `alu)|(Eurt == `dm)) & (Ww == `alu) & (Ert == Wwr) & (Ert != 0)) M4sel = 2'b01;
        else if (((Eurt == `alu)|(Eurt == `dm)) & (Ww == `dm) & (Ert == Wwr) & (Ert != 0)) M4sel = 2'b01;
        else if (((Eurt == `alu)|(Eurt == `dm)) & (Ww == `pc8) & (Ert == Wwr) & (Ert != 0)) M4sel = 2'b01;
        else M4sel = 2'b00;

        //M
        if ((Murt == `dm) & ((Ww == `alu)|(Ww == `dm)|(Ww == `pc8)) & (Mrt == Wwr) & (Mrt != 0)) M5sel = 1'b1;
        else M5sel = 1'b0;

    end



endmodule
