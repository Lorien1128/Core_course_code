`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:38:48 09/14/2021 
// Design Name: 
// Module Name:    Pre_combination_circuit_1 
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
module Pre_combination_circuit_1(
    input [3:0] inA,
    input [3:0] inB,
    input [1:0] op,
    output [3:0] ans
    );
	 
	 assign ans= (op==2'b00)? inA&inB :
	             (op==2'b01)? inA|inB :
					 (op==2'b10)? inA^inB :
					 (op==2'b11)? inA+inB :  4'b000 ;//error
endmodule
