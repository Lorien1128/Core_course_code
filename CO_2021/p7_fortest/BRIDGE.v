`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    01:56:48 12/13/2019 
// Design Name: 
// Module Name:    BRIDGE 
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
module BRIDGE(
								//////连到CPU
	input [31:2] PrAddr,	//CPU从Bridge读写的地址总线，低2位忽略
	input [3:0] PrBE,		//4位字节使能
	input [31:0] PrWD,	//向Bridge写入的数据
	input PrWE,				//向Bridge写使能
	output [31:0] PrRD,	//从Bridge读出的数据
	output [7:2] HWInt,	//六位对应六个硬件中断请求
								
								//////连到Timer
	input [31:0] timer0_RD,		//从timer中读出的数据
	input [31:0] timer1_RD,
	input timer0_IRQ,				//timer1产生的中断请求
	input timer1_IRQ,
	output timer0_WE,				//timer1的写使能
	output timer1_WE,
	output [31:0] DEV_WD,		//向timer1/timer2写入的数据
	output [31:2] DEV_Addr		//写timer的地址
    );
	wire HitTimer0,HitTimer1;
	assign HitTimer0 = (PrAddr[31:4]==28'h7f0 && PrAddr[3:2]!=2'b11 )?1:0;
	assign HitTimer1 = (PrAddr[31:4]==28'h7f1 && PrAddr[3:2]!=2'b11 )?1:0;
	
	assign PrRD = (HitTimer0)?timer0_RD:
					  (HitTimer1)?timer1_RD:
					  32'habcddcba;	//abcddcba for debugging
	assign HWInt = {4'b0,timer1_IRQ,timer0_IRQ};
	assign timer0_WE = (PrWE && HitTimer0)?1:0;
	assign timer1_WE = (PrWE && HitTimer1)?1:0;
	assign DEV_Addr = PrAddr;
	assign DEV_WD = PrWD;
	

endmodule
