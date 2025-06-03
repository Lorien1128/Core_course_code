`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:00:58 09/15/2021 
// Design Name: 
// Module Name:    id_fsm 
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
`define s0 2'b00
`define s1 2'b01
`define s2 2'b10
`define other 2'b01
`define num 2'b10
`define letter 2'b11
module id_fsm(
    input [7:0] char,
    input clk,
    output out
    );
   reg [1:0] s;
	wire [1:0] charw;
	assign charw = ((char>='d97 && char<='d122) || (char>='d65 && char<='d90))? `letter :
	               (char>='d48 && char<='d57)? `num : `other;
	always @(posedge clk) begin
	   if(charw == `letter && s == `s0) begin
		   s <= `s1;
		end
		else if(charw == `letter && s == `s1) begin
		   s <= `s1;
		end
		else if(charw == `num && s == `s1) begin
		   s <= `s2;
		end
		else if(charw == `num && s == `s2) begin
		   s <= `s2;
		end
		else if(charw == `letter && s == `s2) begin
		   s <= `s1;
		end
		else begin
		   s <= `s0;
		end
	end
	
	assign out = (s == `s2)? 1'b1 : 1'b0;
	
	initial begin
	   s <= `s0;
	end
	
endmodule
