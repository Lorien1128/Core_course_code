`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:02:26 11/19/2019 
// Design Name: 
// Module Name:    STOP 
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
`include "define.v"

module STOP_control(		//对照着策略矩阵写
	input Tuse_rs0,
	input Tuse_rs1,
	input Tuse_rt0,
	input Tuse_rt1,
	input Tuse_rt2,
	input [31:0]IR_D,
	input [31:0]IR_E,
	input [31:0]IR_M,
	input [4:0]A3_E,
	input [4:0]A3_M,
	input [2:0]res_E,
	input [2:0]res_M,
	output reg stall
	);
	reg stall_rs0_e1,stall_rs0_e2,stall_rs1_e2,stall_rs0_m1,stall_rs;
	reg stall_rt0_e1,stall_rt0_e2,stall_rt1_e2,stall_rt0_m1,stall_rt;
	reg stall_epc;
	wire eret_D,mtepc_E,mtepc_M;
	
	decoder code_stop_D(.IR(IR_D),.eret(eret_D));
	decoder code_stop_E(.IR(IR_E),.mtepc(mtepc_E));
	decoder code_stop_M(.IR(IR_M),.mtepc(mtepc_M));	
	
	always@*begin
		stall_rs0_e1 = Tuse_rs0 && (IR_D[25:21]==A3_E) && (res_E==`ALU || res_E==`HIout || res_E==`LOout);
		stall_rs0_e2 = Tuse_rs0 && (IR_D[25:21]==A3_E) && (res_E==`DM);
		stall_rs1_e2 = Tuse_rs1 && (IR_D[25:21]==A3_E) && (res_E==`DM);
		stall_rs0_m1 = Tuse_rs0 && (IR_D[25:21]==A3_M) && (res_M==`DM);
		stall_rs = stall_rs0_e1 || stall_rs0_e2 || stall_rs1_e2 || stall_rs0_m1;
		
		stall_rt0_e1 = Tuse_rt0 && (IR_D[20:16]==A3_E) && (res_E==`ALU || res_E==`HIout || res_E==`LOout);
		stall_rt0_e2 = Tuse_rt0 && (IR_D[20:16]==A3_E) && (res_E==`DM);
		stall_rt1_e2 = Tuse_rt1 && (IR_D[20:16]==A3_E) && (res_E==`DM);
		stall_rt0_m1 = Tuse_rt0 && (IR_D[20:16]==A3_M) && (res_M==`DM);
		stall_rt = stall_rt0_e1 || stall_rt0_e2 || stall_rt1_e2 || stall_rt0_m1;	
		
		stall_epc = eret_D && (mtepc_E || mtepc_M);	//mtc0时，eret暂停2周期
		
		stall = stall_rs||stall_rt||stall_epc;
	end
	
endmodule


module STOP(
	input stall_data,
	input clk,
	output reg enPC,
	output reg enD,
	output reg clrE
		 );

	initial begin
		enPC=1;
		enD=1;
		clrE=0;
	end
	always @(*) begin
		enPC=(stall_data)?0:1;
		enD=(stall_data)?0:1;
		clrE=stall_data;
	end 
endmodule

