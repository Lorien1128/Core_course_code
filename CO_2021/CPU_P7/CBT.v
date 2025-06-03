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
`define ADDIUtype 6'd11
`define ADDItype 6'd12
`define ADDtype 6'd13
`define SUBtype 6'd14
`define SLLtype 6'd15
`define SRLtype 6'd16
`define SRAtype 6'd17
`define SLLVtype 6'd18
`define SRLVtype 6'd19
`define SRAVtype 6'd20
`define ANDtype 6'd21
`define ORtype 6'd22
`define XORtype 6'd23
`define NORtype 6'd24
`define ANDItype 6'd25
`define XORItype 6'd26
`define SLTtype 6'd27
`define SLTItype 6'd28
`define SLTIUtype 6'd29
`define SLTUtype 6'd30
`define MULTtype 6'd31
`define MULTUtype 6'd32
`define DIVtype 6'd33
`define DIVUtype 6'd34
`define MFHItype 6'd35
`define MFLOtype 6'd36
`define MTHItype 6'd37
`define MTLOtype 6'd38
`define LBtype 6'd39
`define LBUtype 6'd40
`define LHtype 6'd41
`define LHUtype 6'd42
`define SBtype 6'd43
`define SHtype 6'd44
`define BNEtype 6'd45
`define BLEZtype 6'd46
`define BGTZtype 6'd47
`define BLTZtype 6'd48
`define BGEZtype 6'd49
`define JALRtype 6'd50
`define MFC0type 6'd51
`define MTC0type 6'd52
`define ERETtype 6'd53

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
    input start,
    input busy,
    output reg [1:0] M1sel,
    output reg [1:0] M2sel,
    output reg [1:0] M3sel,
    output reg [1:0] M4sel,
    output reg M5sel,
    output reg stall,
    output reg Dclear
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
        else if (((Dtype == `MULTtype) | (Dtype == `MULTUtype) | (Dtype == `DIVtype) | (Dtype == `DIVUtype) | (Dtype == `MFHItype) | (Dtype == `MFLOtype) | (Dtype == `MTHItype) | (Dtype == `MTLOtype)) & (start | busy)) stall = 1;
        else if (Etype == `ERETtype) stall = 1;
        else stall = 0;

        if (Mtype == `ERETtype) Dclear = 1;
        else Dclear = 0;

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
