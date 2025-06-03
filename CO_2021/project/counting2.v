`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    16:08:50 09/14/2021 
// Design Name: 
// Module Name:    counting 
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
`define s3 2'b11
module counting(
    input [1:0] num,
    output ans,
    input clk
	  );
	 reg [1:0] s;
  always@(posedge clk) begin
      if(num==2'b01) 
		begin
	      s <= `s1;
		end
		
		else if(num==2'b10&&s==`s1) 
		begin
		   s <= `s2;
		end
		
		else if(num==2'b11&&s==`s2) 
		begin
		   s <= `s3;
		end
		
		else if(num==2'b10&&s==`s2)
		begin
		   s <= `s2;
		end
		
		else if(num==2'b11&&s==`s3)
		begin
		   s <= `s3;
		end
		
		else 
		begin
		   s <= `s0;
		end
	end
   
	assign ans= (s==`s3)?1'b1:1'b0;
	
   initial begin
		s<=`s0;
	end
endmodule
