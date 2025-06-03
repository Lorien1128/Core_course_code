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
    wire [31:0] pc, pc8, ins;

    //control
    wire [1:0] WRsel, WDsel, ALUOp;
    wire RFWr, EXTOp, Bsel, DMWr, Br, LUIsel, Jal, Jr, Slt;

    //D
    wire [31:0] D_ins_o, D_pc_o, D_pc8_o;
    wire [1:0] D_WRsel_o, D_WDsel_o, D_ALUOp_o;
    wire [4:0] D_shamt_o, D_rd_o, D_rt_o, D_rs_o;
    wire [15:0] D_imm_o;
    wire [25:0] D_addr_o;
    wire D_RFWr_o, D_EXTOp_o, D_Bsel_o, D_DMWr_o, D_Br_o, D_LUIsel_o, D_Jal_o, D_Jr_o, D_Slt_o;   

    //rf
    wire [31:0] RD1, RD2, WD;
    wire [4:0] A3;
    wire zero;

    //ext
    wire [31:0] EXTout;

    //E
    wire [31:0] E_ins_o, E_pc_o, E_pc8_o, E_d1_o, E_d2_o, E_EXTout_o, E_d1_i, E_d2_i;
    wire [1:0] E_WRsel_o, E_WDsel_o, E_ALUOp_o;
    wire [4:0] E_shamt_o, E_rd_o, E_rt_o;
    wire E_RFWr_o, E_Bsel_o, E_DMWr_o, E_Slt_o; 

    //alu
    wire [31:0] ALUresult, ALU_A, ALU_B;
    
    //M
    wire [31:0] M_ins_o, M_pc_o, M_pc8_o, M_ALUresult_o, M_d2_o, M_d2_i;
    wire [1:0] M_WRsel_o, M_WDsel_o;
    wire [4:0] M_shamt_o, M_rd_o, M_rt_o;
    wire M_RFWr_o, M_DMWr_o, M_Slt_o;   

    //dm
    wire [31:0] dout, din; 

    //W
    wire [31:0] W_ins_o, W_pc_o, W_pc8_o, W_ALUresult_o, W_dout_o;
    wire [1:0] W_WRsel_o, W_WDsel_o;
    wire [4:0] W_shamt_o, W_rd_o, W_rt_o;
    wire W_RFWr_o, W_Slt_o;  

    //cbt
    wire [1:0] M1sel, M2sel, M3sel, M4sel;  
    wire M5sel, stall; 


    IFU ifu (
    .clk(clk), 
    .reset(reset), 
    .we(~stall),
    .Br(D_Br_o), 
    .zero(zero), 
    .Jal(D_Jal_o), 
    .Jr(D_Jr_o), 
    .ra(E_d1_i),
    .immin(D_imm_o),
    .addrin(D_addr_o),
    .funct(funct), 
    .shamt(shamt), 
    .rd(rd), 
    .rt(rt), 
    .rs(rs), 
    .op(op), 
    .immout(imm), 
    .addrout(addr), 
    .pcout(pc), 
    .pc8(pc8),
    .ins(ins)
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




    pipReg D (
    .ins_i(ins), 
    .pc_i(pc), 
    .pc8_i(pc8), 

    .WRsel_i(WRsel), 
    .WDsel_i(WDsel), 
    .RFWr_i(RFWr), 
    .EXTOp_i(EXTOp), 
    .Bsel_i(Bsel), 
    .ALUOp_i(ALUOp), 
    .DMWr_i(DMWr), 
    .Br_i(Br), 
    .LUIsel_i(LUIsel), 
    .Jal_i(Jal), 
    .Jr_i(Jr), 
    .shamt_i(shamt), 
    .rd_i(rd), 
    .rt_i(rt), 
    .rs_i(rs), 
    .imm_i(imm), 
    .addr_i(addr), 
    .clk(clk), 
    .we(~stall), 
    .clear(1'b0),
    .reset(reset), 
    .ins_o(D_ins_o), 
    .pc_o(D_pc_o), 
    .pc8_o(D_pc8_o), 

    .WRsel_o(D_WRsel_o), 
    .WDsel_o(D_WDsel_o), 
    .RFWr_o(D_RFWr_o), 
    .EXTOp_o(D_EXTOp_o), 
    .Bsel_o(D_Bsel_o), 
    .ALUOp_o(D_ALUOp_o), 
    .DMWr_o(D_DMWr_o), 
    .Br_o(D_Br_o), 
    .LUIsel_o(D_LUIsel_o), 
    .Jal_o(D_Jal_o), 
    .Jr_o(D_Jr_o), 
    .shamt_o(D_shamt_o), 
    .rd_o(D_rd_o), 
    .rt_o(D_rt_o), 
    .rs_o(D_rs_o), 
    .imm_o(D_imm_o), 
    .addr_o(D_addr_o),
    .Slt_i(Slt),
    .Slt_o(D_Slt_o)
    );




    assign A3 = (W_WRsel_o == 2'b00)? W_rt_o :
                (W_WRsel_o == 2'b01)? W_rd_o :
                (W_WRsel_o == 2'b10)? 5'd31 : 5'd0;
    assign WD = (W_WDsel_o == 2'b00)? W_ALUresult_o :
                (W_WDsel_o == 2'b01)? W_dout_o :
                (W_WDsel_o == 2'b10)? W_pc8_o : 32'd0;
    RF rf (
    .A1(D_rs_o), 
    .A2(D_rt_o), 
    .A3(A3), 
    .WD(WD), 
    .clk(clk), 
    .reset(reset), 
    .we(W_RFWr_o), 
    .pc(W_pc_o), 
    .RD1(RD1), 
    .RD2(RD2)
    );
    assign E_d1_i = (M1sel == 2'b00)? RD1 :
                    (M1sel == 2'b01)? WD :
                    (M1sel == 2'b10)? M_ALUresult_o : M_pc8_o;
    assign E_d2_i = (M2sel == 2'b00)? RD2 :
                    (M2sel == 2'b01)? WD :
                    (M2sel == 2'b10)? M_ALUresult_o : M_pc8_o;  
    assign zero = (E_d1_i == E_d2_i);  




    EXT ext (
    .EXTin(D_imm_o), 
    .EXTOp(D_EXTOp_o), 
    .LUIsel(D_LUIsel_o), 
    .EXTout(EXTout)
    );




    pipReg E (
    .ins_i(D_ins_o), 
    .pc_i(D_pc_o), 
    .pc8_i(D_pc8_o), 
    .d1_i(E_d1_i), 
    .d2_i(E_d2_i), 
    .d3_i(EXTout), 
    .WRsel_i(D_WRsel_o), 
    .WDsel_i(D_WDsel_o), 
    .RFWr_i(D_RFWr_o), 

    .Bsel_i(D_Bsel_o), 
    .ALUOp_i(D_ALUOp_o), 
    .DMWr_i(D_DMWr_o), 



    .shamt_i(D_shamt_o), 
    .rd_i(D_rd_o), 
    .rt_i(D_rt_o), 


    .clk(clk),
    .we(1'b1),
    .clear(stall), 
    .reset(reset), 
    .ins_o(E_ins_o), 
    .pc_o(E_pc_o), 
    .pc8_o(E_pc8_o), 
    .d1_o(E_d1_o), 
    .d2_o(E_d2_o), 
    .d3_o(E_EXTout_o), 
    .WRsel_o(E_WRsel_o), 
    .WDsel_o(E_WDsel_o), 
    .RFWr_o(E_RFWr_o), 

    .Bsel_o(E_Bsel_o), 
    .ALUOp_o(E_ALUOp_o), 
    .DMWr_o(E_DMWr_o), 


    .shamt_o(E_shamt_o), 
    .rd_o(E_rd_o), 
    .rt_o(E_rt_o),
    .Slt_i(D_Slt_o),
    .Slt_o(E_Slt_o)


    );




    assign ALU_A = (M3sel == 2'b00)? E_d1_o :
                   (M3sel == 2'b01)? WD :
                   (M3sel == 2'b10)? M_ALUresult_o :
                   (M3sel == 2'b11)? M_pc8_o: 0;
    assign ALU_B = (E_Bsel_o == 1)? E_EXTout_o :
                   (M4sel == 2'b00)? E_d2_o :
                   (M4sel == 2'b01)? WD :
                   (M4sel == 2'b10)? M_ALUresult_o :
                   (M4sel == 2'b11)? M_pc8_o: 0;
    ALU alu (
    .A(ALU_A), 
    .B(ALU_B), 
    .ALUOp(E_ALUOp_o), 
    .ALUresult(ALUresult)
    );




    assign M_d2_i = (M4sel == 2'b00)? E_d2_o :
                    (M4sel == 2'b01)? WD :
                    (M4sel == 2'b10)? M_ALUresult_o :
                    (M4sel == 2'b11)? M_pc8_o: 0;
    pipReg M (
    .ins_i(E_ins_o), 
    .pc_i(E_pc_o), 
    .pc8_i(E_pc8_o), 
    .d1_i(ALUresult), 
    .d2_i(M_d2_i), 

    .WRsel_i(E_WRsel_o), 
    .WDsel_i(E_WDsel_o), 
    .RFWr_i(E_RFWr_o), 


    .DMWr_i(E_DMWr_o), 



    .shamt_i(E_shamt_o), 
    .rd_i(E_rd_o), 
    .rt_i(E_rt_o), 


    .clk(clk), 
    .we(1'b1),
    .clear(1'b0),
    .reset(reset), 
    .ins_o(M_ins_o), 
    .pc_o(M_pc_o), 
    .pc8_o(M_pc8_o), 
    .d1_o(M_ALUresult_o), 
    .d2_o(M_d2_o), 

    .WRsel_o(M_WRsel_o), 
    .WDsel_o(M_WDsel_o), 
    .RFWr_o(M_RFWr_o), 


    .DMWr_o(M_DMWr_o), 


    .shamt_o(M_shamt_o), 
    .rd_o(M_rd_o), 
    .rt_o(M_rt_o),
    .Slt_i(E_Slt_o),
    .Slt_o(M_Slt_o)


    );




    assign din = (M5sel == 1)? WD : M_d2_o;
    DM dm (
    .clk(clk), 
    .reset(reset), 
    .we(M_DMWr_o), 
    .pc(M_pc_o), 
    .addr(M_ALUresult_o), 
    .din(din), 
    .dout(dout)
    );




    pipReg W (
    .ins_i(M_ins_o), 
    .pc_i(M_pc_o), 
    .pc8_i(M_pc8_o), 
    .d1_i(M_ALUresult_o), 
    .d2_i(dout), 

    .WRsel_i(M_WRsel_o), 
    .WDsel_i(M_WDsel_o), 
    .RFWr_i(M_RFWr_o), 




    .shamt_i(M_shamt_o), 
    .rd_i(M_rd_o), 
    .rt_i(M_rt_o), 


    .clk(clk), 
    .we(1'b1),
    .clear(1'b0),
    .reset(reset), 
    .ins_o(W_ins_o), 
    .pc_o(W_pc_o), 
    .pc8_o(W_pc8_o), 
    .d1_o(W_ALUresult_o), 
    .d2_o(W_dout_o), 

    .WRsel_o(W_WRsel_o), 
    .WDsel_o(W_WDsel_o), 
    .RFWr_o(W_RFWr_o), 



    .shamt_o(W_shamt_o), 
    .rd_o(W_rd_o), 
    .rt_o(W_rt_o),
    .Slt_i(M_Slt_o),
    .Slt_o(W_Slt_o)


    );




    CBT cbt (
    .D_ins_o(D_ins_o), 
    .E_ins_o(E_ins_o), 
    .M_ins_o(M_ins_o), 
    .W_ins_o(W_ins_o), 
    .M1sel(M1sel), 
    .M2sel(M2sel), 
    .M3sel(M3sel), 
    .M4sel(M4sel), 
    .M5sel(M5sel), 
    .stall(stall)
    );





endmodule

