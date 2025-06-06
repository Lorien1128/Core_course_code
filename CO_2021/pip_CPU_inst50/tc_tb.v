`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   14:55:38 12/02/2021
// Design Name:   TC
// Module Name:   D:/ISE/pip_CPU_inst50/tc_tb.v
// Project Name:  pip_CPU_inst50
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: TC
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module tc_tb;

	// Inputs
	reg clk;
	reg reset;
	reg [31:2] Addr;
	reg WE;
	reg [31:0] Din;

	// Outputs
	wire [31:0] Dout;
	wire IRQ;

	// Instantiate the Unit Under Test (UUT)
	TC uut (
		.clk(clk), 
		.reset(reset), 
		.Addr(Addr), 
		.WE(WE), 
		.Din(Din), 
		.Dout(Dout), 
		.IRQ(IRQ)
	);

	initial begin
		// Initialize Inputs
		clk = 0;
		reset = 0;
		Addr = 32'h1a2b3c4d;
		WE = 0;
		Din = 0;

		// Wait 100 ns for global reset to finish
		#100;
        
		// Add stimulus here

	end
      
endmodule

