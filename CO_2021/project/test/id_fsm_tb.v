`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   21:58:01 09/15/2021
// Design Name:   id_fsm
// Module Name:   D:/ISE/project/test/id_fsm_tb.v
// Project Name:  test
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: id_fsm
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module id_fsm_tb;

	// Inputs
	reg [7:0] char;
	reg clk;

	// Outputs
	wire out;

	// Instantiate the Unit Under Test (UUT)
	id_fsm uut (
		.char(char), 
		.clk(clk), 
		.out(out)
	);

	initial begin
		char = 8'd49;
		clk = 0;
		#100;
		char <= 8'd97;
		#100;
		char <= 8'd98;
		#100;
		char <= 8'd49;
		#100;
		char <= 8'd50;
		#100;
		char <= 8'd37;
	end

   always #50 clk = ~clk;  
endmodule

