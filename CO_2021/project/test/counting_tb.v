`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   16:47:32 09/14/2021
// Design Name:   counting
// Module Name:   D:/ISE/project/test/counting_tb.v
// Project Name:  test
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: counting
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module counting_tb;

	// Inputs
	reg [1:0] num;
	reg clk;

	// Outputs
	wire ans;

	// Instantiate the Unit Under Test (UUT)
	counting uut (
		.num(num), 
		.ans(ans), 
		.clk(clk)
	);

	initial begin
	   clk=1;
		num=0;
		#50;
		num<=2'b01;
		#50;
		num<=2'b10;
		#50;
		num<=2'b11;
		#50;
		num<=2'b11;
		#50;
		num<=2'b10;
		#50;
		num<=2'b01;
	end
	always #25 clk=~clk;
      
endmodule

