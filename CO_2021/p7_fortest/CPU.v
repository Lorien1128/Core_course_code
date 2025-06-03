`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:06:43 11/19/2019 
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
module CPU(
	input clk,
	input reset,
	input [7:2] HWInt,
	input [31:0] PrRD,
	output PrWE,
	output [3:0] PrBE,
	output [31:2] PrAddr,
	output [31:0] PrWD,
	output [31:0] PC_M
    );
	wire [31:0]IR_D,IR_E,IR_M;
	wire [3:0]MCMP1D,MCMP2D,MALUAE,MALUBE,MWDM;
	wire [4:0]A3;
	wire enD,enPC,clrE,stall_md;
	
	datapath dp(
		.clk(clk),
		.reset(reset),
		.enD(enD),
		.enPC(enPC),
		.clrE(clrE),
		.stall_md(stall_md),
		.A3(A3),
		.IR_D(IR_D),
		.IR_E(IR_E),
		.IR_M(IR_M),
		.MCMP1D(MCMP1D),
		.MCMP2D(MCMP2D),
		.MALUAE(MALUAE),
		.MALUBE(MALUBE),
		.MWDM(MWDM),
		.PrAddr(PrAddr),
		.PrWD(PrWD),
		.PrWE(PrWE),
		.PrBE(PrBE),
		.PrRD(PrRD),
		.HWInt(HWInt),
		.PC_M(PC_M)
		);
		
	control ctrl(
		.clk(clk),
		.reset(reset),
		.IR_D(IR_D),
		.IR_E(IR_E),
		.IR_M(IR_M),
		.A3_W(A3),
		.enD(enD),
		.enPC(enPC),
		.clrE(clrE),
		.stall_md(stall_md),
		.MCMP1D(MCMP1D),
		.MCMP2D(MCMP2D),
		.MALUAE(MALUAE),
		.MALUBE(MALUBE),
		.MWDM(MWDM)
		);
endmodule 
