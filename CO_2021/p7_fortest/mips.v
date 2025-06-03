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
module mips(
	input clk,
	input reset,
	input interrupt,
	output [31:0] addr
    );
	wire PrWE, timer0_IRD, timer1_IRQ, timer0_WE, timer1_WE;
	wire [3:0] PrBE;
	wire [7:2] HWInt_both, HWInt_inside;
	wire [31:2] PrAddr, DEV_Addr;
	wire [31:0] PrRD, PrWD, timer0_RD, timer1_RD, DEV_WD;
	assign HWInt_both = {3'b0, interrupt, HWInt_inside[3:2]};
	
	CPU CPU_(
		.clk(clk),
		.reset(reset),
		.PrWE(PrWE),
		.PrBE(PrBE),
		.HWInt(HWInt_both),
		.PrAddr(PrAddr),
		.PrRD(PrRD),
		.PrWD(PrWD),
		.PC_M(addr)
		);
		
	BRIDGE Bridge(
		.PrAddr(PrAddr),	//CPU��Bridge��д�ĵ�ַ���ߣ���2λ����
		.PrBE(PrBE),		//4λ�ֽ�ʹ��
		.PrWD(PrWD),	//��Bridgeд�������
		.PrWE(PrWE),				//��Bridgeдʹ��
		.PrRD(PrRD),	//��Bridge����������
		.HWInt(HWInt_inside),	//��λ��Ӧ����Ӳ���ж�����
								//////����Timer
		.timer0_RD(timer0_RD),		//��timer�ж���������
		.timer1_RD(timer1_RD),
		.timer0_IRQ(timer0_IRQ),				//timer1�������ж�����
		.timer1_IRQ(timer1_IRQ),
		.timer0_WE(timer0_WE),				//timer1��дʹ��
		.timer1_WE(timer1_WE),
		.DEV_WD(DEV_WD),		//��timer1/timer2д�������
		.DEV_Addr(DEV_Addr)		//дtimer�ĵ�ַ
		);
		
	TC Timer0(
		.clk(clk),
		.reset(reset),
		.Addr(DEV_Addr),
		.WE(timer0_WE),
		.Din(DEV_WD),
		.Dout(timer0_RD),
		.IRQ(timer0_IRQ)
		);
		
	TC Timer1(
		.clk(clk),
		.reset(reset),
		.Addr(DEV_Addr),
		.WE(timer1_WE),
		.Din(DEV_WD),
		.Dout(timer1_RD),
		.IRQ(timer1_IRQ)
		);
endmodule 
