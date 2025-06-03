`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    01:50:15 12/13/2019 
// Design Name: 
// Module Name:    CP0 
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

//SR = 		{16'b0, IM[15:10], 8'b0, exl, ie};
//Cause =	{BD, 15'b0, IP[15:10], 3'b0, EXCcode[6:2], 2'b0};
`define BD Cause[31]				//��֧�ӳ٣�1�쳣�������ӳٲ��У�EPCָ���ָ֧��
`define IP Cause[15:10]		//6���������ж�λ��1�����жϣ�0���ж�
`define EXCcode Cause[6:2]		//�쳣���룬0�жϣ�4ȡָ/ȡ����ַ����,5������ַ����10δָ֪���룬12�������

`define IM SR[15:10]	//6���ж�����λ��1�����жϣ�0��ֹ�ж�
`define EXL SR[1]		//�쳣����1�����쳣�������жϣ�0�����ж�
`define IE SR[0]		//ȫ���ж�ʹ�ܣ�1�����жϣ�0��ֹ�ж�


module CP0(
	input [31:0] DIn,
	input [31:0] PC,
	input [6:2] EXCcode,		//�쳣���룬ǰ����ˮ�߲���
	input [15:10] HWInt,		//�ⲿ�жϣ������贫��������CPU�ٴ���CP0
	input EXLSet,
	input clk,
	input reset,
	input [31:0] IR_M,
	input [31:0] IR_W,
	output IntReq,			//�ڲ��ж�
	output [31:0] EPCout,
	output [31:0] DOut,
	output EXLSet_both
    );
	
	reg [31:0] SR;
	reg [31:0] Cause;
	reg [31:0] EPC;
	reg [31:0] PRId=32'h18379146;		//qw
	
	wire [4:0] A2;
	wire DelaySlot;
	wire WE,isb,isj,isjr,EXLClr;
	
	decoder code_CP0_M(.IR(IR_M), .mtc0(WE));
	decoder code_CP0_W(.IR(IR_W), .isb(isb), .isj(isj), .isjr(isjr), .EXLClr(EXLClr));
	
	
	assign A2 = IR_M[15:11];		//rd����ӦҪ��д��CP0�Ĵ���
	assign EPCout = EPC[31:0];
	assign DelaySlot = isb||isj||isjr;
	assign EXLSET_both = ( EXLSet || (EXCcode!=0) ) && IntReq;	// �쳣=(�����쳣||�����쳣)&&�����ж�
	//   ( EXLSet&& ( |(HWInt[15:10]&`IM) && !`EXL && `IE))|| (EXCcode!=0);
	assign IntReq = ( |(HWInt[15:10]&`IM) && !`EXL && `IE) || (EXCcode!=0);		//�ж�=�ⲿ�ж�||�쳣
	assign DOut =  (A2==12)? SR:
						(A2==13)? Cause:
						(A2==14)? EPC:
						(A2==15)? PRId:
						32'h18231047;		//debug
	
	always @(posedge clk) begin		
		if (reset) begin
			SR=0;
			Cause=0;
			EPC=0;
		end
		else if (WE) begin
			case(A2)
			12: 	begin
					`IM = DIn[15:10];
					`EXL = DIn[1];
					`IE = DIn[0];
					end
			14:	EPC = DIn;
			default: ;
			endcase
		end
		
		else begin
			if(EXLClr)				//SR
				`EXL=0;
			else if(EXLSET_both)
				`EXL=1;
			
			if (EXLSET_both) begin		//Cause
				`BD = (EXLClr)?0: 
						(DelaySlot)?1:
						0;
				`IP = HWInt;
				`EXCcode = EXCcode;
			end
			else						
				`IP = HWInt;
			
			if (EXLSET_both) begin		//EPC
				if (DelaySlot)
					EPC = {PC[31:2]-1, 2'b0};
				else
					EPC = {PC[31:2], 2'b0};
			end
			
		end
	end

endmodule
