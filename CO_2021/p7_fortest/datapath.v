`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:07:18 11/19/2019 
// Design Name: 
// Module Name:    datapath 
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
module datapath(
	input clk,
	input reset,
	input enD,
	input enPC,
	input clrE,
	input [4:0]A3,
	input [3:0]MCMP1D,
	input [3:0]MCMP2D,
	input [3:0]MALUAE,
	input [3:0]MALUBE,
	input [3:0]MWDM,
	output [31:0]IR_D,
	output [31:0]IR_E,
	output [31:0]IR_M,
	output stall_md,
	
	input [31:0] PrRD,	
	input [7:2] HWInt,	
	output [31:2] PrAddr,
	output [31:0] PrWD,
	output [3:0] PrBE,
	output PrWE,
	output [31:0] PC_M
    );
	wire [4:0]A3_W;
	wire [31:0]PC,PC4,PC0,PC4_D,PC4_E,PC4_M,PC4_W,DM,DM_M,DM_W;
	wire [31:0]MFALUAE,MFALUBE,MFCMP1D,MFCMP2D,MFWDM;
	wire [31:0]V1_E,V2_E,V2_M,E32_D,E32_E;
	wire [31:0]HIout,LOout,HI_M,LO_M,HI_W,LO_W,AO_E,AO_M,AO_W,IR_W;
	wire [31:0]WPC_FIN,WPC_FOUT,WPC_DIN,WPC_DOUT,WPC_EIN,WPC_EOUT,WPC_M;
	wire [31:0]EPC,CP0_RD,RD,DO,IR,WD,ALUA,ALUB,AOE,XTout,NPC,RFRD1,RFRD2,EXTout,AO;
	wire [6:2] EXCcode_FIN,EXCcode_FOUT,EXCcode_DIN,EXCcode_DOUT,EXCcode_EIN,EXCcode_EOUT,EXCcode_M;
	wire RegWrite_W,zero,mul_div_d,start,busy,EXLClr,EXLSet,EXLSet_both,IntReq;
	
	assign PC_M = PC4_M-4;
	assign stall_md = (start||busy) && mul_div_d;
	assign PrAddr = AO_M[31:2];
	assign PrWD = MFWDM;
	
//F级组合逻辑，包含PC寄存器、IM取指令单元和ADD4逻辑
//PC之前有个功能多选器mux_pc,jr的回写rs之前有个转发多选器MFCMP1D

MUX_PC MUX_PC_(
	.IR_D(IR_D),
	.IntReq(IntReq),
	.EPC(EPC),
	.NPC(NPC),
	.PC4(PC4),
	.MFPCF(MFCMP1D),
	.PC0(PC0)
	);
PC PC_(
	.PC0(PC0),
	.clk(clk),
	.reset(reset),
	.en(enPC),
	.stall_md(stall_md),
	.EXLClr(EXLClr),
	.IntReq(IntReq),
	.PC(PC)
	);
ADD4 ADD4_(
	.PC(PC),
	.PC4(PC4)
	);
IM IM_(
	.PC(PC),
	.IR(IR)
	);
EXC_F EXC_F_(
	.PC(PC),
	.EXCcode_F(EXCcode_FIN),
	.WPC_F(WPC_FIN),
	.clr_wrongPC(clr_wrongPC)
	);
	
///////////////////D级寄存器
regD regD_(
	.IR(IR), .PC4(PC4), .EXCcode_FIN(EXCcode_FIN), .WPC_FIN(WPC_FIN),
	.clk(clk),
	.reset(reset),
	.en(enD),
	.IntReq(IntReq),
	.clr_wrongPC(clr_wrongPC),
	.stall_md(stall_md),
	.IR_D(IR_D), .PC4_D(PC4_D), .EXCcode_FOUT(EXCcode_FOUT), .WPC_FOUT(WPC_FOUT),
	.mul_div_d(mul_div_d),
	.EXLClr(EXLClr)
	);

//D级组合逻辑，包含NPC、RF寄存器堆、EXT扩展单元、比较器;
//NPC是为跳转类指令计算下一个PC（考虑延迟槽）
//RF为D级的组合逻辑，和回写单元的寄存器;EXT根据需要扩展;
//比较器为beq指令实现，两输入之前分别有一个转发多路选择器MFCMP1D,MFCMP2D
NPC NPC_(
	.zero(zero),
	.PC4(PC4_D),
	.IR_D(IR_D),
	.NPC(NPC)
	);
RF RF_(
	.A1(IR_D[25:21]),
	.A2(IR_D[20:16]),
	.A3(A3_W),
	.RegWrite_W(RegWrite_W),
	.WD(WD),
	.PC4(PC4_W),
	.clk(clk),
	.reset(reset),
	.V1(RFRD1),
	.V2(RFRD2)
	);
EXT EXT_(
	.IR_D(IR_D),
	.extout(EXTout)
	);
	
MFCMP1D MFCMP1D_(
	.MCMP1D(MCMP1D),
	.RFRD1(RFRD1),
	.PC4_M(PC4_M), .AO_M(AO_M), .HI_M(HI_M), .LO_M(LO_M),
	.PC4_W(PC4_W), .AO_W(AO_W), .DM_W(DM_W), .HI_W(HI_W), .LO_W(LO_W),
	.MFCMP1D(MFCMP1D)
	);

MFCMP2D MFCMP2D_(
	.MCMP2D(MCMP2D),
	.RFRD2(RFRD2),
	.PC4_M(PC4_M), .AO_M(AO_M), .HI_M(HI_M), .LO_M(LO_M),
	.PC4_W(PC4_W), .AO_W(AO_W), .DM_W(DM_W), .HI_W(HI_W), .LO_W(LO_W),
	.MFCMP2D(MFCMP2D)
	);

CMP CMP_(
	.IR_D(IR_D),
	.D1(MFCMP1D),
	.D2(MFCMP2D),
	.zero(zero)
	);

EXC_D EXC_D_(
	.EXCcode_F(EXCcode_FOUT),
	.WPC_F(WPC_FOUT),
	.IR_D(IR_D),
	.PC4_D(PC4_D),
	.EXCcode_D(EXCcode_DIN),
	.WPC_D(WPC_DIN)
	);

/////////////E级寄存器
regE regE_(
	.clk(clk),
	.reset(reset),
	.clr(clrE),
	.stall_md(stall_md),
	.IntReq(IntReq),
	.IR_D(IR_D), .V1_D(MFCMP1D), .V2_D(MFCMP2D), .E32_D(EXTout), .PC4_D(PC4_D), .WPC_DIN(WPC_DIN), .EXCcode_DIN(EXCcode_DIN),
	.IR_E(IR_E), .V1_E(V1_E), .V2_E(V2_E), .E32_E(E32_E), .PC4_E(PC4_E), .WPC_DOUT(WPC_DOUT), .EXCcode_DOUT(EXCcode_DOUT)
	);


//E级组合逻辑，包含ALU单元，每一路输入之前有个转发多选器
MFALUAE MFALUAE_(
	.MALUAE(MALUAE),
	.V1_E(V1_E),
	.PC4_M(PC4_M),.AO_M(AO_M),.HI_M(HI_M),.LO_M(LO_M),
	.PC4_W(PC4_W),.AO_W(AO_W),.DM_W(DM_W),.HI_W(HI_W),.LO_W(LO_W),
	.MFALUAE(MFALUAE)
	);
MFALUBE MFALUBE_(
	.MALUBE(MALUBE),
	.V2_E(V2_E),
	.PC4_M(PC4_M),.AO_M(AO_M),.HI_M(HI_M),.LO_M(LO_M),
	.PC4_W(PC4_W),.AO_W(AO_W),.DM_W(DM_W),.HI_W(HI_W),.LO_W(LO_W),
	.MFALUBE(MFALUBE)
	);
MUX_ALUA MUX_ALUA_(
	.IR_E(IR_E),
	.V1_E(MFALUAE),
	.A(ALUA)
	);
MUX_ALUB MUX_ALUB_(
	.IR_E(IR_E),
	.V2_E(MFALUBE),
	.E32_E(E32_E),
	.B(ALUB)
	);
ALU ALU_(
	.IR_E(IR_E),
	.A(ALUA),
	.B(ALUB),
	.ALUout(AO),
	.overflow(overflow),
	.loadflow(loadflow),
	.storeflow(storeflow)
	);
MD MD_(
	.EXLSet_both(EXLSet_both),
	.start(start),
	.IR_E(IR_E),
	.clk(clk),
	.reset(reset),
	.D1(MFALUAE),
	.D2(MFALUBE),
	.busy(busy),
	.HIout(HIout),
	.LOout(LOout)
	);

EXC_E EXC_E_(
	.EXCcode_D(EXCcode_DOUT),
	.WPC_D(WPC_DOUT),
	.PC4_E(PC4_E),
	.overflow(overflow),
	.loadflow(loadflow),
	.storeflow(storeflow),
	.EXCcode_E(EXCcode_EIN),
	.WPC_E(WPC_EIN)
	);

////////////M级寄存器
regM regM_(
	.clk(clk),
	.reset(reset),
	.IntReq(IntReq),
	.IR_E(IR_E), .V2_E(MFALUBE), .AO_E(AO), .PC4_E(PC4_E), .HI_E(HIout), .LO_E(LOout), .WPC_EIN(WPC_EIN), .EXCcode_EIN(EXCcode_EIN),
	.IR_M(IR_M), .V2_M(V2_M), .AO_M(AO_M), .PC4_M(PC4_M), .HI_M(HI_M), .LO_M(LO_M), .WPC_EOUT(WPC_EOUT), .EXCcode_EOUT(EXCcode_EOUT)
	);

//M级组合逻辑，DM，它的地址输入之前有个转发多选器
MFWDM MFWDM_(
	.MWDM(MWDM),
	.V2_M(V2_M),
	.PC4_W(PC4_W),.AO_W(AO_W),.DM_W(DM_W),.HI_W(HI_W),.LO_W(LO_W),
	.MFWDM(MFWDM)
	);
DM DM_(
	 .Addr(AO_M),
    .DIn(MFWDM),
    .IR_M(IR_M),
    .clk(clk),
    .reset(reset),
	 .EXLSet(EXLSet),
    .PC4(PC4_M),
    .DO(DO),
	 .PrBE(PrBE),
	 .PrWE(PrWE),
	 .wrong_load(wrong_load),
	 .wrong_store(wrong_store)	
	 );

EXC_M EXC_M_(
	.EXCcode_E(EXCcode_EOUT),
	.WPC_E(WPC_EOUT),
	.PC4_M(PC4_M),
	.HWInt(HWInt),
	.wrong_load(wrong_load),
	.wrong_store(wrong_store),
	.EXLSet(EXLSet),
	.EXCcode_M(EXCcode_M),
	.WPC_M(WPC_M)
	);

MUX_DM MUX_DM_(
	.AO_M(AO_M),
	.PrRD(PrRD),
	.IR_M(IR_M),
	.DO(DO),
	.CP0_RD(CP0_RD),
	.RD(RD)
);
///////////W级寄存器
regW regW_(
	.IntReq(IntReq),
	.clk(clk),
	.reset(reset),
	.IR_M(IR_M), .PC4_M(PC4_M), .AO_M(AO_M), .DM_M(RD), .HI_M(HI_M), .LO_M(LO_M),
	.PC4_W(PC4_W), .AO_W(AO_W), .DM_W(DM_W), .IR_W(IR_W), .HI_W(HI_W),.LO_W(LO_W),
	.A3_W(A3_W),
	.RegWrite_W(RegWrite_W)
	);

//选择回写的数据的多路选择器
MUX_WD MUX_WD_(
	.IR_W(IR_W),
	.PC4_W(PC4_W),
	.AO_W(AO_W),
	.DM_W(DM_W),
	.HI_W(HI_W),
	.LO_W(LO_W),
	.WD(WD)
	);



CP0 CP0_(
	.DIn(MFWDM),
	.PC(WPC_M),
	.EXCcode(EXCcode_M),		//异常代码，前级流水线产生
	.HWInt(HWInt),		//外部中断，从外设传出，传入CPU再传入CP0
	.EXLSet(EXLSet),
	.clk(clk),
	.reset(reset),
	.IR_M(IR_M),
	.IR_W(IR_W),
	.IntReq(IntReq),			//内部中断
	.EPCout(EPC),
	.DOut(CP0_RD),
	.EXLSet_both(EXLSet_both)
	);


endmodule
