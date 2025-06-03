`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:28:21 11/17/2019 
// Design Name: 
// Module Name:    ALU 
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

module MD(
	input [31:0]IR_E,
	input clk,
	input reset,
	input EXLSet_both,
	input [31:0] D1,
	input [31:0] D2,
	output  [31:0]HIout,
	output  [31:0]LOout,
	output start,
	output reg busy
	);
	integer count_mult,count_div;	//乘除周期计数
	wire [1:0]MDWrite;
	wire[2:0]MDcal;
	reg [31:0]HI,LO,HItemp,LOtemp;
	decoder code_MD(.IR(IR_E),.MDWrite(MDWrite),.MDcal(MDcal),.start(start));
	
	assign HIout=HI;
	assign LOout=LO;
	
	initial begin 
		HI=0;LO=0;busy=0;count_mult=5;count_div=10;HItemp=0;LOtemp=0;
	end
	
	always@(posedge clk)begin
		if(reset)begin
			HI=0;
			LO=0;
			HItemp=0;
			LOtemp=0;
			busy=0;
			count_mult=5;
			count_div=10;
		end
		else begin
			//WRITE HI LO
			if(MDWrite==`writehi && !EXLSet_both)
				HI=D1;
			else if(MDWrite==`writelo && !EXLSet_both)
				LO=D1;
			
			//乘法
			if(EXLSet_both&&(count_mult==4||count_div==9)&&count_mult!=5) begin//异常时清除MD刚写入的数据
				busy=0;
				count_mult=5;
			end
			else if(count_mult==0) begin	//乘法计算完成
				HI=HItemp;
				LO=LOtemp;
				busy=0;
				count_mult=5;
			end
			else if(count_mult!=5) begin	//乘法正在计算
				busy=1;
				count_mult = count_mult-1;
			end
			else if(MDcal==`mult && !EXLSet_both) begin//开始计算无符号乘法
				{HItemp,LOtemp}=D1*D2;
				busy=1;
				count_mult = count_mult-1;
			end
			else if(MDcal==`sign_mult && !EXLSet_both) begin//开始计算有符号乘法
				{HItemp,LOtemp}=$signed(D1)*$signed(D2);
				busy=1;
				count_mult = count_mult-1;
			end
			
			//除法
			if(EXLSet_both&&(count_mult==4||count_div==9)&&count_mult!=10) begin//异常时清除MD刚写入的数据
				busy=0;
				count_div=10;
			end
			else if(count_div==0) begin	//除法计算完成
				HI=HItemp;
				LO=LOtemp;
				busy=0;
				count_div=10;
			end
			else if(count_div!=10) begin	//除法正在计算
				busy=1;
				count_div = count_div-1;
			end
			else if(MDcal==`div && !EXLSet_both) begin//开始计算无符号除法
				HItemp=D1%D2;
				LOtemp=D1/D2;
				busy=1;
				count_div = count_div-1;
			end
			else if(MDcal==`sign_div && !EXLSet_both) begin//开始计算有符号除法
				HItemp=$signed(D1)%$signed(D2);
				LOtemp=$signed(D1)/$signed(D2);
				busy=1;
				count_div = count_div-1;
			end
		end
	end
endmodule		




module ALU(
    input [31:0] A,
    input [31:0] B,
    input [31:0] IR_E, 
    output reg [31:0] ALUout,
	 output overflow,
	 output loadflow,
	 output storeflow
    );
	wire [3:0] ALUctr;
	wire load,store;
	reg overflow_=0,loadflow_=0,storeflow_=0,add_flow,sub_flow;
	reg [32:0] A_EXT,B_EXT,temp_add,temp_sub;
	
	assign overflow=overflow_;
	assign loadflow=loadflow_;
	assign storeflow=storeflow_;
	
	decoder code_ALU(.IR(IR_E),.ALUctr(ALUctr),.load(load),.store(store));
	
	always @(*) begin
		A_EXT={A[31],A};
		B_EXT={B[31],B};
		temp_add=A_EXT+B_EXT;
		temp_sub=A_EXT-B_EXT;
		add_flow=(temp_add[31]!=temp_add[32])?1:0;
		sub_flow=(temp_sub[31]!=temp_sub[32])?1:0;
		loadflow_ = load && add_flow;
		storeflow_ = store && sub_flow;
		overflow_ = (add_flow&&ALUctr==`add)||(sub_flow&&ALUctr==`sub);
		
		case(ALUctr)
			`add: ALUout=A+B;
			`sub: ALUout=A-B;
			`addu: ALUout=A+B;
			`subu: ALUout=A-B;
			`andd: ALUout=A&B;
			`orr: ALUout=A|B;
			`xorr: ALUout=A^B;
			`norr: ALUout=~(A|B);
			`left: ALUout=B<<A[4:0];
			`sign_right: ALUout=($signed(B)>>>A[4:0]);
			`zero_right: ALUout=B>>A[4:0];
			`sign_less: ALUout= ($signed(A)<$signed(B));
			`zero_less: ALUout=(A<B);
			default: ALUout=32'h18231047;		//debug
		endcase
	end

endmodule
