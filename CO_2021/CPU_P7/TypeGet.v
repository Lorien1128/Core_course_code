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
`define MFC0 6'd16
`define MTC0 6'd16
`define ERET 6'd16


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
                  (op == `ADDI)? `ADDItype :
                  (op == `ADDIU)? `ADDIUtype :
                  (Rtype & (funct == `ADD))? `ADDtype :
                  (Rtype & (funct == `SUB))? `SUBtype :
                  (Rtype & (funct == `SLL))? `SLLtype :
                  (Rtype & (funct == `SRL))? `SRLtype :
                  (Rtype & (funct == `SRA))? `SRAtype :
                  (Rtype & (funct == `SLLV))? `SLLVtype :
                  (Rtype & (funct == `SRLV))? `SRLVtype :
                  (Rtype & (funct == `SRAV))? `SRAVtype :
                  (Rtype & (funct == `AND))? `ANDtype :
                  (Rtype & (funct == `OR))? `ORtype :
                  (Rtype & (funct == `XOR))? `XORtype :
                  (Rtype & (funct == `NOR))? `NORtype :
                  (op == `ANDI)? `ANDItype :
                  (op == `XORI)? `XORItype :
                  (Rtype & (funct == `SLT))? `SLTtype :
                  (op == `SLTI)? `SLTItype :
                  (op == `SLTIU)? `SLTIUtype :
                  (Rtype & (funct == `SLTU))? `SLTUtype :
                  (Rtype & (funct == `MULT))? `MULTtype :
                  (Rtype & (funct == `MULTU))? `MULTUtype :
                  (Rtype & (funct == `DIV))? `DIVtype :
                  (Rtype & (funct == `DIVU))? `DIVUtype :
                  (Rtype & (funct == `MFHI))? `MFHItype :
                  (Rtype & (funct == `MFLO))? `MFLOtype :
                  (Rtype & (funct == `MTHI))? `MTHItype :
                  (Rtype & (funct == `MTLO))? `MTLOtype :
                  (op == `LB)? `LBtype :
                  (op == `LBU)? `LBUtype :
                  (op == `LH)? `LHtype :
                  (op == `LHU)? `LHUtype :
                  (op == `SB)? `SBtype :
                  (op == `SH)? `SHtype :
                  (op == `BNE)? `BNEtype :
                  (op == `BLEZ)? `BLEZtype :
                  (op == `BGTZ)? `BGTZtype :
                  ((rt == 0) & (op == `BLTZ))? `BLTZtype :
                  ((rt == 1) & (op == `BGEZ))? `BGEZtype :
                  (Rtype & (funct == `JALR))? `JALRtype :
                  ((op == `MFC0) & (rs == 0))? `MFC0type :
                  ((op == `MTC0) & (rs == 4))? `MTC0type :
                  ((op == `ERET) & (rs == 16))? `ERETtype :
                   0;

    wire CALtype, CALItype, STOREtype, LOADtype, SHIFTtype, SHIFTSHAMTtype, MFtype, MTtype, MDtype, BRtype, BRZtype;
    assign CALtype = ((type == `ADDtype) | (type == `ADDUtype) | (type == `SUBtype) | (type == `SUBUtype) | (type == `ANDtype) | (type == `ORtype) | (type == `XORtype) | (type == `NORtype) | (type == `SLTtype) | (type == `SLTUtype));
    assign CALItype = ((type == `ADDItype) | (type == `ADDIUtype) | (type == `ANDItype) | (type == `ORItype) | (type == `XORItype) | (type == `SLTItype) | (type == `SLTIUtype));
    assign STOREtype = ((type == `SBtype) | (type == `SHtype) | (type == `SWtype));
    assign LOADtype  = ((type == `LBtype) | (type == `LBUtype) | (type == `LHtype) | (type == `LHUtype) | (type == `LWtype));
    assign SHIFTtype = ((type == `SLLVtype) | (type == `SRLVtype) | (type == `SRAVtype));
    assign SHIFTSHAMTtype = ((type == `SLLtype) | (type == `SRLtype) | (type == `SRAtype));
    assign MFtype = ((type == `MFHItype) | (type == `MFLOtype));  
    assign MTtype = ((type == `MTHItype) | (type == `MTLOtype));
    assign MDtype = ((type == `MULTtype) | (type == `MULTUtype) | (type == `DIVtype) | (type == `DIVUtype));
    assign BRtype = ((type == `BEQtype) | (type == `BNEtype));
    assign BRZtype = ((type == `BLEZtype) | (type == `BGTZtype) | (type == `BLTZtype) | (type == `BGEZtype));


    assign urs = (CALtype | CALItype | SHIFTtype | STOREtype | LOADtype | MTtype | MDtype)? `alu :
                 (BRtype | BRZtype | (type == `JRtype) | (type == `JALRtype))? `beq : 2'b00;
    assign urt = (CALtype | SHIFTtype | SHIFTSHAMTtype | MDtype)? `alu :
                 (BRtype)? `beq :
                 (STOREtype | (type == `MTC0type))? `dm : 2'b00;
    assign wd = (CALtype | CALItype | (type == `LUItype) | SHIFTtype | SHIFTSHAMTtype | MFtype)? `alu :
                (LOADtype | (type == `MFC0type))? `dm :
                ((type == `JALtype) | (type == `JALRtype))? `pc8 : 2'b00;
    assign wr = (CALtype | SHIFTtype | SHIFTSHAMTtype | MFtype | (type == `JALRtype))? rd :
                (CALItype | LOADtype | (type == `LUItype) | (type == `MFC0type))? rt :
                (type == `JALtype)? `RA : 5'd0;
                  
endmodule
