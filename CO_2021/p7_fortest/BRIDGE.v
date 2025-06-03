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
								//////����CPU
	input [31:2] PrAddr,	//CPU��Bridge��д�ĵ�ַ���ߣ���2λ����
	input [3:0] PrBE,		//4λ�ֽ�ʹ��
	input [31:0] PrWD,	//��Bridgeд�������
	input PrWE,				//��Bridgeдʹ��
	output [31:0] PrRD,	//��Bridge����������
	output [7:2] HWInt,	//��λ��Ӧ����Ӳ���ж�����
								
								//////����Timer
	input [31:0] timer0_RD,		//��timer�ж���������
	input [31:0] timer1_RD,
	input timer0_IRQ,				//timer1�������ж�����
	input timer1_IRQ,
	output timer0_WE,				//timer1��дʹ��
	output timer1_WE,
	output [31:0] DEV_WD,		//��timer1/timer2д�������
	output [31:2] DEV_Addr		//дtimer�ĵ�ַ
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
