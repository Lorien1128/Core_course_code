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
`define J 6'b000010
`define JAL 6'b000011
`define JR 6'b001000
`define ADDIU 6'd9
`define ADDI 6'd8
`define ADD 6'd32
`define SUB 6'd34
`define SLL 6'd0
`define SRL 6'd2
`define SRA 6'd3
`define SLLV 6'd4
`define SRLV 6'd6
`define SRAV 6'd7
`define AND 6'd36
`define OR 6'd37
`define XOR 6'd38
`define NOR 6'd39
`define ANDI 6'd12
`define XORI 6'd14
`define SLT 6'd42
`define SLTI 6'd10
`define SLTIU 6'd11
`define SLTU 6'd43
`define MULT 6'd24
`define MULTU 6'd25
`define DIV 6'd26
`define DIVU 6'd27
`define MFHI 6'd16
`define MFLO 6'd18
`define MTHI 6'd17
`define MTLO 6'd19
`define LB 6'd32
`define LBU 6'd36
`define LH 6'd33
`define LHU 6'd37
`define SB 6'd40
`define SH 6'd41
`define BNE 6'd5
`define BLEZ 6'd6
`define BGTZ 6'd7
`define BLTZ 6'd1
`define BGEZ 6'd1
`define JALR 6'd9
`define JAP 6'd25
//ALUOp
`define add 4'd0
`define sub 4'd1
`define sll 4'd2
`define srl 4'd3
`define sra 4'd4
`define aand 4'd5
`define oor 4'd6
`define xxor 4'd7
`define nnor 4'd8
`define slt 4'd9
`define sltu 4'd10
//MDOp
`define mdmult 3'd1
`define mdmultu 3'd2
`define mddiv 3'd3
`define mddivu 3'd4
`define mdmthi 3'd5
`define mdmtlo 3'd6
//DMWOp
`define dmwsb 2'd1
`define dmwsh 2'd2
`define dmwsw 2'd3
//DMROp
`define dmrlb 3'd1
`define dmrlbu 3'd2
`define dmrlh 3'd3
`define dmrlhu 3'd4
`define dmrlw 3'd5
//BROP
`define brbeq 3'd1
`define brbne 3'd2
`define brblez 3'd3
`define brbgtz 3'd4
`define brbltz 3'd5
`define brbgez 3'd6


module Control(
    input [5:0] funct,
    input [5:0] op,
    input [4:0] rt,
    output [1:0] WRsel,
    output [1:0] WDsel,
    output RFWr,
    output EXTOp,
    output Asel,
    output Bsel,
    output [3:0] ALUOp,

    output [2:0] MDOp,

    output [1:0] DMWOp,
    output [2:0] DMROp,
    output [2:0] BROp,
    output [1:0] AMsel,
    output LUIsel,
    output Jal,
    output Jr,
    output Temp
    );
    wire addu, subu, ori, lw, sw, beq, lui, jal, jr, j, addi, addiu, add, sub, sll, srl, sra, sllv, srlv, 
         srav, aand, oor, xxor, nnor, andi, xori, slt, slti, sltiu, sltu, mult, multu, div, divu, mfhi, mflo, 
         mthi, mtlo, lb, lbu, lh, lhu, sb, sh, bne, blez, bgtz, bltz, bgez, jalr, jap;
    wire Rtype;
    assign Rtype = (op == 6'b000000);
    //AND LOGIC
    assign addu = Rtype & (funct == `ADDU);
    assign subu = Rtype & (funct == `SUBU);
    assign ori = (op == `ORI);
    assign lw = (op == `LW);
    assign sw = (op == `SW);
    assign beq = (op == `BEQ);
    assign lui = (op == `LUI);
    assign j = (op == `J);
    assign jal = (op == `JAL);
    assign jr = Rtype & (funct == `JR);
    assign addi = (op == `ADDI);
    assign addiu = (op == `ADDIU);
    assign add = Rtype & (funct == `ADD);
    assign sub = Rtype & (funct == `SUB);
    assign sll = Rtype & (funct == `SLL);
    assign srl = Rtype & (funct == `SRL);
    assign sra = Rtype & (funct == `SRA);
    assign sllv = Rtype & (funct == `SLLV);
    assign srlv = Rtype & (funct == `SRLV);
    assign srav = Rtype & (funct == `SRAV);
    assign aand = Rtype & (funct == `AND);
    assign oor = Rtype & (funct == `OR);
    assign xxor = Rtype & (funct == `XOR);
    assign nnor = Rtype & (funct == `NOR);
    assign andi = (op == `ANDI);
    assign xori = (op == `XORI);
    assign slt = Rtype & (funct == `SLT);
    assign slti = (op == `SLTI);
    assign sltiu = (op == `SLTIU);
    assign sltu = Rtype & (funct == `SLTU);
    assign mult = Rtype & (funct == `MULT);
    assign multu = Rtype & (funct == `MULTU);
    assign div = Rtype & (funct == `DIV);
    assign divu = Rtype & (funct == `DIVU);
    assign mfhi = Rtype & (funct == `MFHI);
    assign mflo = Rtype & (funct == `MFLO);
    assign mthi = Rtype & (funct == `MTHI);
    assign mtlo = Rtype & (funct == `MTLO);
    assign lb = (op == `LB);
    assign lbu = (op == `LBU);
    assign lh = (op == `LH);
    assign lhu = (op == `LHU);
    assign sb = (op == `SB);
    assign sh = (op == `SH);
    assign bne = (op == `BNE);
    assign blez = (op == `BLEZ);
    assign bgtz = (op == `BGTZ);
    assign bltz = (rt == 0) & (op == `BLTZ);
    assign bgez = (rt == 1) & (op == `BGEZ);
    assign jalr = Rtype & (funct == `JALR);
    assign jap = (op == `JAP);

     
    
    //OR LOGIC
    assign WRsel = (add | addu | sub | subu | sll | srl | sra | sllv | srlv | srav | aand | oor | xxor | nnor | slt | sltu | mfhi | mflo | jalr)? 2'b01 ://rd
                   (jal)? 2'b10 ://ra
                   (jap)? 2'b11 :
                    2'b00;//rt
    assign WDsel = (lb | lbu | lh | lhu | lw)? 2'b01 ://dm
                   (jal | jalr)? 2'b10 ://pc8
                   (jap)? 2'b11 :
                    2'b00;//alu
    assign RFWr = (jap| add | addu | sub | subu | sll | srl | sra | sllv | srlv | srav | aand | oor | xxor | nnor | slt | sltu | mfhi | mflo | jalr | addi | addiu | andi | ori | xori | lui | slti | sltiu | lb | lbu | lh | lhu | lw | jal);
    assign EXTOp = (addi | addiu | slti | sltiu | lb | lbu | lh | lhu | lw | sb | sh | sw);//signext
    assign Asel = (sll | srl | sra);//A=shamt
    assign Bsel = (addi | addiu | andi | ori | xori | lui | slti | sltiu | lb | lbu | lh | lhu | lw | sb | sh | sw);//B=ext
    assign ALUOp = (addi | addiu | add | addu | lui | lb | lbu | lh | lhu | lw | sb | sh | sw)? `add :
                   (sub | subu)? `sub :
                   (sll | sllv)? `sll :
                   (srl | srlv)? `srl :
                   (sra | srav)? `sra :
                   (aand | andi)? `aand :
                   (oor | ori)? `oor :
                   (xxor | xori)? `xxor :
                   (nnor)? `nnor :
                   (slt | slti)? `slt :
                   (sltiu | sltu)? `sltu : 0;

    assign MDOp = (mult)? `mdmult :
                  (multu)? `mdmultu :
                  (div)? `mddiv :
                  (divu)? `mddivu :
                  (mthi)? `mdmthi :
                  (mtlo)? `mdmtlo : 0;

    assign DMWOp = (sb)? `dmwsb :
                   (sh)? `dmwsh :
                   (sw | jap)? `dmwsw : 0;
    assign DMROp = (lb)? `dmrlb :
                   (lbu)? `dmrlbu :
                   (lh)? `dmrlh :
                   (lhu)? `dmrlhu :
                   (lw)? `dmrlw : 0;
    assign BROp = (beq)? `brbeq :
                  (bne)? `brbne :
                  (blez)? `brblez :
                  (bgtz)? `brbgtz :
                  (bltz)? `brbltz :
                  (bgez)? `brbgez : 0;
    assign AMsel = (mfhi)? 2'd1 :
                   (mflo)? 2'd2 : 0;
    assign LUIsel = lui;
    assign Jal = (jal | j);
    assign Jr = (jr | jalr);
    assign Temp = jap;
endmodule
