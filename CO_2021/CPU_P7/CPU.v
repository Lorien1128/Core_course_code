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
`define AdEL 5'd4
`define AdES 5'd5
`define RI 5'd10
`define Ov 5'd12
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

module CPU(
    input clk,
    input reset,
    input [5:0] HwInt,
    input [31:0] i_inst_rdata,
    input [31:0] m_data_rdata,
    output [31:0] i_inst_addr,
    output [31:0] m_data_addr,
    output [31:0] m_data_wdata,
    output [3 :0] m_data_byteen,
    output [31:0] m_inst_addr,
    output w_grf_we,
    output [4:0] w_grf_addr,
    output [31:0] w_grf_wdata,
    output [31:0] w_inst_addr,
    output [31:0] macro_pc,
    output answer_interrupt
    );


    //ifu
    wire [5:0] funct, op;
    wire [4:0] shamt, rd, rt, rs;
    wire [15:0] imm;
    wire [25:0] addr;
    wire [31:0] pc, pc8, ins, insin;
    wire Bdout;

    //control
    wire [1:0] WRsel, WDsel, DMWOp, AMsel;
    wire [2:0] MDOp, DMROp, BROp;  
    wire [3:0] ALUOp;
    wire RFWr, EXTOp, Asel, Bsel, LUIsel, Jal, Jr, CP0Wr, Eret, Bd, Hit;

    //D
    wire [31:0] D_ins_o, D_pc_o, D_pc8_o;
    wire [1:0] D_WRsel_o, D_WDsel_o, D_DMWOp_o, D_AMsel_o;
    wire [2:0] D_MDOp_o, D_DMROp_o, D_BROp_o;
    wire [3:0] D_ALUOp_o;
    wire [4:0] D_shamt_o, D_rd_o, D_rt_o, D_rs_o, D_ExcCode_o, D_ExcCode_i;
    wire [15:0] D_imm_o;
    wire [25:0] D_addr_o;
    wire D_RFWr_o, D_EXTOp_o, D_Asel_o, D_Bsel_o, D_LUIsel_o, D_Jal_o, D_Jr_o, D_CP0Wr_o, D_Eret_o, D_Bd_o;   

    //rf
    wire [31:0] RD1, RD2, WD;
    wire [4:0] A3;
    wire zero, less;

    //ext
    wire [31:0] EXTout;

    //E
    wire [31:0] E_ins_o, E_pc_o, E_pc8_o, E_d1_i, E_d2_i, E_d1_o, E_d2_o, E_EXTout_o;
    wire [1:0] E_WRsel_o, E_WDsel_o, E_DMWOp_o, E_AMsel_o;
    wire [2:0] E_MDOp_o, E_DMROp_o;
    wire [3:0] E_ALUOp_o;
    wire [4:0] E_shamt_o, E_rd_o, E_rt_o, E_ExcCode_o, E_ExcCode_i;
    wire E_RFWr_o, E_Asel_o, E_Bsel_o, E_CP0Wr_o, E_Eret_o, E_Bd_o;

    //alu
    wire [31:0] ALUresult, ALU_A, ALU_B;
    wire Overflow;

    //md
    wire [31:0] HIout, LOout;
    wire start, busy;
    
    //M
    wire [31:0] M_ins_o, M_pc_o, M_pc8_o, M_d1_i, M_d2_i, M_ALUresult_o, M_d2_o;
    wire [1:0] M_WRsel_o, M_WDsel_o, M_DMWOp_o;
    wire [2:0] M_DMROp_o;
    wire [4:0] M_shamt_o, M_rd_o, M_rt_o, M_ExcCode_o, M_ExcCode_i;
    wire M_RFWr_o, M_CP0Wr_o, M_Eret_o, M_Bd_o;

    //dmw
    wire [3:0] byteen;

    //dmr
    wire [31:0] dataout;

    //cp0
    wire IntReq, macro_bd;
    wire [31:0] EPC, CP0dout;
    wire [4:0] ExcCode;
    wire [1:0] a10;

    //W
    wire [31:0] W_ins_o, W_pc_o, W_pc8_o, W_ALUresult_o, W_dataout_o, W_CP0data_o;
    wire [1:0] W_WRsel_o, W_WDsel_o;
    wire [4:0] W_shamt_o, W_rd_o, W_rt_o;
    wire W_RFWr_o, W_Bd_o;  

    //cbt
    wire [1:0] M1sel, M2sel, M3sel, M4sel;  
    wire M5sel, stall, Dclear; 



    assign insin = (D_ExcCode_i == `AdEL)? 32'h0 : i_inst_rdata;
    IFU ifu (
    .clk(clk), 
    .reset(reset), 
    .we(~stall),
    .BROp(D_BROp_o), 
    .zero(zero),
    .less(less), 
    .Jal(D_Jal_o), 
    .Jr(D_Jr_o), 
    .Eret(M_Eret_o),
    .IntReq(IntReq),
    .EPC(EPC),
    .Bdin(Bd),
    .ra(E_d1_i),
    .immin(D_imm_o),
    .addrin(D_addr_o),
    .insin(insin),
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
    .ins(ins),
    .Bdout(Bdout)
    );
    assign i_inst_addr = pc;




    Control control (
    .funct(funct), 
    .op(op), 
    .rs(rs),
    .rt(rt),
    .WRsel(WRsel), 
    .WDsel(WDsel), 
    .RFWr(RFWr), 
    .EXTOp(EXTOp), 
    .Asel(Asel),
    .Bsel(Bsel), 
    .ALUOp(ALUOp), 
    .MDOp(MDOp),
    .DMWOp(DMWOp),
    .DMROp(DMROp), 
    .BROp(BROp),
    .AMsel(AMsel), 
    .LUIsel(LUIsel), 
    .Jal(Jal), 
    .Jr(Jr),
    .CP0Wr(CP0Wr),
    .Eret(Eret),
    .Bd(Bd),
    .Hit(Hit)
    );



    assign D_ExcCode_i = ((i_inst_addr[1:0] != 0) | (i_inst_addr < 32'h0000_3000) | (i_inst_addr > 32'h0000_6ffc))? `AdEL :
                         (Hit == 0)? `RI : 5'd0;
    pipReg D (
    .ins_i(ins), 
    .pc_i(pc), 
    .pc8_i(pc8), 

    .WRsel_i(WRsel), 
    .WDsel_i(WDsel), 
    .RFWr_i(RFWr), 
    .EXTOp_i(EXTOp), 
    .Asel_i(Asel),
    .Bsel_i(Bsel), 
    .ALUOp_i(ALUOp), 
    .MDOp_i(MDOp),
    .DMWOp_i(DMWOp),
    .DMROp_i(DMROp),
    .BROp_i(BROp),
    .AMsel_i(AMsel),
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
    .clear(IntReq | Dclear),
    .reset(reset), 

    .ins_o(D_ins_o), 
    .pc_o(D_pc_o), 
    .pc8_o(D_pc8_o), 

    .WRsel_o(D_WRsel_o), 
    .WDsel_o(D_WDsel_o), 
    .RFWr_o(D_RFWr_o), 
    .EXTOp_o(D_EXTOp_o),
    .Asel_o(D_Asel_o), 
    .Bsel_o(D_Bsel_o), 
    .ALUOp_o(D_ALUOp_o), 
    .MDOp_o(D_MDOp_o),
    .DMWOp_o(D_DMWOp_o),
    .DMROp_o(D_DMROp_o),
    .BROp_o(D_BROp_o),
    .AMsel_o(D_AMsel_o),
    .LUIsel_o(D_LUIsel_o), 
    .Jal_o(D_Jal_o), 
    .Jr_o(D_Jr_o), 
    .shamt_o(D_shamt_o), 
    .rd_o(D_rd_o), 
    .rt_o(D_rt_o), 
    .rs_o(D_rs_o), 
    .imm_o(D_imm_o), 
    .addr_o(D_addr_o),
    .CP0Wr_i(CP0Wr),
    .CP0Wr_o(D_CP0Wr_o),
    .Eret_i(Eret),
    .Eret_o(D_Eret_o),
    .Bd_i(Bdout),
    .Bd_o(D_Bd_o),
    .ExcCode_i(D_ExcCode_i),
    .ExcCode_o(D_ExcCode_o)
    );




    assign A3 = (W_WRsel_o == 2'b00)? W_rt_o :
                (W_WRsel_o == 2'b01)? W_rd_o :
                (W_WRsel_o == 2'b10)? 5'd31 : 5'd0;
    assign WD = (W_WDsel_o == 2'b11)? W_CP0data_o :
                (W_WDsel_o == 2'b00)? W_ALUresult_o :
                (W_WDsel_o == 2'b01)? W_dataout_o :
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
    assign w_grf_we = W_RFWr_o;
    assign w_grf_wdata = WD;
    assign w_grf_addr = A3;
    assign w_inst_addr = W_pc_o;

    assign E_d1_i = (M1sel == 2'b00)? RD1 :
                    (M1sel == 2'b01)? WD :
                    (M1sel == 2'b10)? M_ALUresult_o : M_pc8_o;
    assign E_d2_i = (M2sel == 2'b00)? RD2 :
                    (M2sel == 2'b01)? WD :
                    (M2sel == 2'b10)? M_ALUresult_o : M_pc8_o;  
    assign zero = (E_d1_i == E_d2_i);  
    assign less = ($signed(E_d1_i) < 0);




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

    .Asel_i(D_Asel_o),
    .Bsel_i(D_Bsel_o), 
    .ALUOp_i(D_ALUOp_o), 
    .MDOp_i(D_MDOp_o),
    .DMWOp_i(D_DMWOp_o),
    .DMROp_i(D_DMROp_o),
    .AMsel_i(D_AMsel_o),



    .shamt_i(D_shamt_o), 
    .rd_i(D_rd_o), 
    .rt_i(D_rt_o), 


    .clk(clk),
    .we(1'b1),
    .clear(stall | IntReq | Dclear), 
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

    .Asel_o(E_Asel_o),
    .Bsel_o(E_Bsel_o), 
    .ALUOp_o(E_ALUOp_o), 
    .MDOp_o(E_MDOp_o),
    .DMWOp_o(E_DMWOp_o),
    .DMROp_o(E_DMROp_o),
    .AMsel_o(E_AMsel_o),


    .shamt_o(E_shamt_o), 
    .rd_o(E_rd_o), 
    .rt_o(E_rt_o),
    .CP0Wr_i(D_CP0Wr_o),
    .CP0Wr_o(E_CP0Wr_o),
    .Eret_i(D_Eret_o),
    .Eret_o(E_Eret_o),
    .Bd_i(D_Bd_o),
    .Bd_o(E_Bd_o),
    .ExcCode_i(D_ExcCode_o),
    .ExcCode_o(E_ExcCode_o)
    );




    assign ALU_A = (E_Asel_o == 1)? {27'b0, E_shamt_o} :
                   (M3sel == 2'b00)? E_d1_o :
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
    .ALUresult(ALUresult),
    .Overflow(Overflow)
    );




    MD md (
    .MDA(ALU_A), 
    .MDB(ALU_B), 
    .MDOp(E_MDOp_o), 
    .clk(clk), 
    .reset(reset), 
    .we(~IntReq),
    .HIout(HIout), 
    .LOout(LOout), 
    .start(start), 
    .busy(busy)
    );







    assign M_d1_i = (E_AMsel_o == 2'b00)? ALUresult :
                    (E_AMsel_o == 2'b01)? HIout :
                    (E_AMsel_o == 2'b10)? LOout : 0;
    assign M_d2_i = (M4sel == 2'b00)? E_d2_o :
                    (M4sel == 2'b01)? WD :
                    (M4sel == 2'b10)? M_ALUresult_o :
                    (M4sel == 2'b11)? M_pc8_o: 0;
    assign M_ExcCode_i = (Overflow & (E_DMWOp_o != 0))? `AdES :
                         (Overflow & (E_DMROp_o != 0))? `AdEL :
                         (Overflow)? `Ov : E_ExcCode_o;
    pipReg M (
    .ins_i(E_ins_o), 
    .pc_i(E_pc_o), 
    .pc8_i(E_pc8_o), 
    .d1_i(M_d1_i), 
    .d2_i(M_d2_i), 

    .WRsel_i(E_WRsel_o), 
    .WDsel_i(E_WDsel_o), 
    .RFWr_i(E_RFWr_o), 


    .DMWOp_i(E_DMWOp_o),
    .DMROp_i(E_DMROp_o),



    .shamt_i(E_shamt_o), 
    .rd_i(E_rd_o), 
    .rt_i(E_rt_o), 


    .clk(clk), 
    .we(1'b1),
    .clear(IntReq),
    .reset(reset), 
    .ins_o(M_ins_o), 
    .pc_o(M_pc_o), 
    .pc8_o(M_pc8_o), 
    .d1_o(M_ALUresult_o), 
    .d2_o(M_d2_o), 

    .WRsel_o(M_WRsel_o), 
    .WDsel_o(M_WDsel_o), 
	 .RFWr_o(M_RFWr_o),

    .DMWOp_o(M_DMWOp_o),
    .DMROp_o(M_DMROp_o),






    .shamt_o(M_shamt_o), 
    .rd_o(M_rd_o), 
    .rt_o(M_rt_o),
    .CP0Wr_i(E_CP0Wr_o),
    .CP0Wr_o(M_CP0Wr_o),
    .Eret_i(E_Eret_o),
    .Eret_o(M_Eret_o),
    .Bd_i(E_Bd_o),
    .Bd_o(M_Bd_o),
    .ExcCode_i(M_ExcCode_i),
    .ExcCode_o(M_ExcCode_o)
    );



    wire [31:0] din;
    assign din = (M5sel == 1)? WD : M_d2_o;
    assign m_data_wdata = (byteen == 4'b0001)? din :
                          (byteen == 4'b0010)? (din << 8) :
                          (byteen == 4'b0100)? (din << 16) :
                          (byteen == 4'b1000)? (din << 24) :
                          (byteen == 4'b0011)? din :
                          (byteen == 4'b1100)? (din << 16) :
                          (byteen == 4'b1111)? din : din;


    assign m_data_addr = M_ALUresult_o;
    assign m_inst_addr = M_pc_o;

    assign macro_pc = (M_pc_o != 0)? M_pc_o :
                      (E_pc_o != 0)? E_pc_o :
                      (D_pc_o != 0)? D_pc_o : pc;
    assign macro_bd = (M_pc_o != 0)? M_Bd_o :
                      (E_pc_o != 0)? E_Bd_o :
                      (D_pc_o != 0)? D_Bd_o : 1'b0;

    DMW dmw (
    .DMWOp(M_DMWOp_o), 
    .addr(M_ALUresult_o), 
    .byteen(byteen)
    );
    assign m_data_byteen = (IntReq)? 4'b0000 : byteen;





    wire HitDM, HitT0, HitT1, HitCount;
    assign HitDM = ((m_data_addr >= 32'h0000_0000) & (m_data_addr <= 32'h0000_2fff));
    assign HitT0 = ((m_data_addr >= 32'h0000_7f00) & (m_data_addr <= 32'h0000_7f0b));
    assign HitT1 = ((m_data_addr >= 32'h0000_7f10) & (m_data_addr <= 32'h0000_7f1b));
    assign HitCount = ((m_data_addr == 32'h0000_7f08) | (m_data_addr == 32'h0000_7f18));

    assign a10 = M_ALUresult_o[1:0];
    assign ExcCode = (M_DMROp_o == `dmrlw & a10 != 2'b00)? `AdEL :
                     ((M_DMROp_o == `dmrlh | M_DMROp_o == `dmrlhu) & a10[0] != 0)? `AdEL :
                     ((M_DMROp_o == `dmrlh | M_DMROp_o == `dmrlhu | M_DMROp_o == `dmrlb | M_DMROp_o == `dmrlbu) & (HitT0 | HitT1))? `AdEL :
                     (M_DMROp_o != 0 & ~(HitDM | HitT0 | HitT1))? `AdEL :
                     (M_DMWOp_o == `dmwsw & a10 != 2'b00)? `AdES :
                     (M_DMWOp_o == `dmwsh & a10[0] != 0)? `AdES :
                     ((M_DMWOp_o == `dmwsh | M_DMWOp_o == `dmwsb) & (HitT0 | HitT1))? `AdES :
                     (M_DMWOp_o != 0 & HitCount)? `AdES :
                     (M_DMWOp_o != 0 & ~(HitDM | HitT0 | HitT1))? `AdES : M_ExcCode_o;

    CP0 cp0 (
    .addr(M_rd_o), 
    .din(din), 
    .pc(M_pc_o), 
    .macro_pc(macro_pc),
    .ExcCode(ExcCode), 
    .HwInt(HwInt), 
    .WE(M_CP0Wr_o), 
    .Exlclr(M_Eret_o), 
    .clk(clk), 
    .reset(reset), 
    .W_Bd(macro_bd), 
    .IntReq(IntReq), 
    .EPC(EPC), 
    .dout(CP0dout),
    .answer_interrupt(answer_interrupt)
    );



    DMR dmr (
    .data(m_data_rdata), 
    .addr(M_ALUresult_o), 
    .DMROp(M_DMROp_o), 
    .dataout(dataout)
    );


 




    pipReg W (
    .ins_i(M_ins_o), 
    .pc_i(M_pc_o), 
    .pc8_i(M_pc8_o), 
    .d1_i(M_ALUresult_o), 
    .d2_i(dataout), 

    .WRsel_i(M_WRsel_o), 
    .WDsel_i(M_WDsel_o), 
    .RFWr_i(M_RFWr_o), 




    .shamt_i(M_shamt_o), 
    .rd_i(M_rd_o), 
    .rt_i(M_rt_o), 


    .clk(clk), 
    .we(1'b1),
    .clear(IntReq),
    .reset(reset), 
    .ins_o(W_ins_o), 
    .pc_o(W_pc_o), 
    .pc8_o(W_pc8_o), 
    .d1_o(W_ALUresult_o), 
    .d2_o(W_dataout_o), 

    .WRsel_o(W_WRsel_o), 
    .WDsel_o(W_WDsel_o), 
    .RFWr_o(W_RFWr_o), 



    .shamt_o(W_shamt_o), 
    .rd_o(W_rd_o), 
    .rt_o(W_rt_o),
    .Bd_i(M_Bd_o),
    .Bd_o(W_Bd_o),
    .CP0data_i(CP0dout),
    .CP0data_o(W_CP0data_o)

    );




    CBT cbt (
    .D_ins_o(D_ins_o), 
    .E_ins_o(E_ins_o), 
    .M_ins_o(M_ins_o), 
    .W_ins_o(W_ins_o), 
    .start(start),
    .busy(busy),
    .M1sel(M1sel), 
    .M2sel(M2sel), 
    .M3sel(M3sel), 
    .M4sel(M4sel), 
    .M5sel(M5sel), 
    .stall(stall),
    .Dclear(Dclear)
    );





endmodule

