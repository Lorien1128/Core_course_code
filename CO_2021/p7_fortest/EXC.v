`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    01:28:27 12/16/2019 
// Design Name: 
// Module Name:    EXC_D 
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
module EXC_F(
	input [31:0] PC,
	output [6:2] EXCcode_F,
	output [31:0] WPC_F,
	output clr_wrongPC
    );
	wire wrong_PC;
	assign wrong_PC = (PC<32'h3000 || PC>32'h4ffc ||  PC[1:0]!=2'b0);
	assign clr_wrongPC = wrong_PC;
	assign WPC_F = (wrong_PC)?PC:0;
	assign EXCcode_F = (wrong_PC)?4:0;
	
endmodule


module EXC_D(
	input [6:2] EXCcode_F,
	input [31:0] WPC_F,
	input [31:0] IR_D,
	input [31:0] PC4_D,
	output [6:2] EXCcode_D,
	output [31:0] WPC_D
    );
	wire Wrong_IR;
	decoder code_EXC_D (.IR(IR_D), .Wrong_IR(Wrong_IR));
	assign EXCcode_D = 	(EXCcode_F!=0)?EXCcode_F:
								(Wrong_IR)?10:
								0;
	assign WPC_D = (EXCcode_F!=0)?WPC_F:
						(Wrong_IR)?PC4_D-4:
						0;

endmodule 

module EXC_E(
	input [6:2] EXCcode_D,
	input [31:0] WPC_D,
	input [31:0] PC4_E,
	input overflow,
	input loadflow,
	input storeflow,
	output [6:2] EXCcode_E,
	output [31:0] WPC_E
    );
	assign EXCcode_E = 	(EXCcode_D!=0)?EXCcode_D:
								(overflow)?12:
								(loadflow)?4:
								(storeflow)?5:
								0;
	assign WPC_E = (EXCcode_D!=0)?WPC_D:
						(overflow||loadflow||storeflow)?PC4_E-4:
						0;

endmodule 

module EXC_M(
	input [6:2] EXCcode_E,
	input [31:0] WPC_E,
	input [31:0] PC4_M,
	input [7:2] HWInt,
	input wrong_load,
	input wrong_store,
	output EXLSet,
	output [6:2] EXCcode_M,
	output [31:0] WPC_M
    );
	assign EXCcode_M = 	(HWInt!=0)?0:
								(EXCcode_E!=0)?EXCcode_E:
								(wrong_load)?4:
								(wrong_store)?5:
								0;
	assign WPC_M = (HWInt!=0)?PC4_M-4:
						(EXCcode_E!=0)?WPC_E:
						(wrong_load||wrong_store)?PC4_M-4:
						PC4_M-4;
	
	assign EXLSet = (HWInt!=0)||(EXCcode_M!=0);

endmodule 