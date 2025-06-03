`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    00:22:53 11/18/2019 
// Design Name: 
// Module Name:    regE 
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

//////////////////////////////////////////////////////////////////////////////////
module regD(
    input [31:0] IR,
    input [31:0] PC4,
	 input [31:0] WPC_FIN,
	 input [6:2] EXCcode_FIN,
	 input clr_wrongPC,
    input clk,
    input reset,
    input en,
	 input stall_md,
	 input IntReq,
	 output reg [31:0] PC4_D,
    output reg [31:0] IR_D,
	 output reg [31:0] WPC_FOUT,
	 output reg [6:2] EXCcode_FOUT,
	 output mul_div_d,
	 output EXLClr
    );
		decoder code_regD(.IR(IR_D),.mul_div(mul_div_d),.EXLClr(EXLClr));
	
	always@(posedge clk)begin
		if( reset||IntReq||(EXLClr&&en) )begin
			IR_D=0;
			PC4_D=0;
			WPC_FOUT=0;
			EXCcode_FOUT=0;
		end
		else if( clr_wrongPC && en)
			IR_D=0;
		else if( en && !stall_md && !EXLClr ) begin
			IR_D=IR;
			PC4_D=PC4;
			WPC_FOUT=WPC_FIN;
			EXCcode_FOUT=EXCcode_FIN;
		end	
		
end

endmodule

	module regE(
	input clk,
	input reset,
	input clr,
	input IntReq,
	input [31:0]IR_D,
	input [31:0]V1_D,
	input [31:0]V2_D,
	input [31:0]E32_D,
	input [31:0]PC4_D,
	input [31:0]WPC_DIN,
	input [6:2]EXCcode_DIN,
	input stall_md,
	output reg [31:0]V1_E,
	output reg [31:0]V2_E,
	output reg [31:0]E32_E,
	output reg [31:0]PC4_E,
	output reg [31:0]IR_E,
	output reg [31:0]WPC_DOUT,
	output reg [6:2]EXCcode_DOUT
    );
	always@(posedge clk) begin
			if(reset||IntReq)
				PC4_E=0;
			else
				PC4_E=PC4_D;
			if(reset||clr||stall_md||IntReq)begin
				IR_E=0;
				V1_E=0;
				V2_E=0;
				E32_E=0;
				WPC_DOUT=0;
				EXCcode_DOUT=0;
			end
			else begin
				IR_E=IR_D;
				V1_E=V1_D;
				V2_E=V2_D;
				E32_E=E32_D;
				WPC_DOUT=WPC_DIN;
				EXCcode_DOUT=EXCcode_DIN;
			end
	end
endmodule

module regM(
	input clk,
	input reset,
	input IntReq,
	input [31:0]WPC_EIN,
	input [6:2]EXCcode_EIN,
	input [31:0]V2_E,
	input [31:0]AO_E,
	input [31:0]PC4_E,
	input [31:0]IR_E,
	input [31:0]HI_E,
	input [31:0]LO_E,
	output reg[31:0]V2_M,
	output reg[31:0]AO_M,
	output reg[31:0]PC4_M,
	output reg[31:0]IR_M,
	output reg[31:0]HI_M,
	output reg[31:0]LO_M,
	output reg[31:0]WPC_EOUT,
	output reg[6:2]EXCcode_EOUT
    );
	always@(posedge clk)begin
			if(reset||IntReq)begin
				WPC_EOUT=0;
				EXCcode_EOUT=0;
				V2_M=0;
				AO_M=0;
				PC4_M=0;
				IR_M=0;
				HI_M=0;
				LO_M=0;
			end
			else begin
				V2_M=V2_E;
				AO_M=AO_E;
				PC4_M=PC4_E;
				IR_M=IR_E;
				HI_M=HI_E;
				LO_M=LO_E;
				WPC_EOUT=WPC_EIN;
				EXCcode_EOUT=EXCcode_EIN;
			end
	end
endmodule 

module regW(
	input clk,
	input reset,
	input IntReq,
	input [31:0]IR_M,
	input [31:0]PC4_M,
	input [31:0]AO_M,
	input [31:0]DM_M,
	input [31:0]HI_M,
	input [31:0]LO_M,
	output reg [31:0]PC4_W,
	output reg [31:0]AO_W,
	output reg [31:0]DM_W,
	output reg [31:0]IR_W,
	output reg [31:0]HI_W,
	output reg [31:0]LO_W,
	output reg [4:0]A3_W,
	output reg RegWrite_W
    );
	wire [4:0]A3;
	wire RegWrite;
	decoder code_W(.IR(IR_M),.A3(A3),.RegWrite(RegWrite));
	always@(posedge clk)begin
			if(reset||IntReq)begin
				IR_W=0;
				PC4_W=0;
				AO_W=0;
				DM_W=0;
				HI_W=0;
				LO_W=0;
				A3_W=0;
				RegWrite_W=0;
			end
			else begin
				IR_W=IR_M;
				PC4_W=PC4_M;
				AO_W=AO_M;
				DM_W=DM_M;
				HI_W=HI_M;
				LO_W=LO_M;
				A3_W=A3;
				RegWrite_W=RegWrite;
			end
	end

endmodule 