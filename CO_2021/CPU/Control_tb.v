`timescale 1ns / 1ps

////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer:
//
// Create Date:   19:45:53 11/12/2021
// Design Name:   Control
// Module Name:   D:/ISE/CPU/Control_tb.v
// Project Name:  CPU
// Target Device:  
// Tool versions:  
// Description: 
//
// Verilog Test Fixture created by ISE for module: Control
//
// Dependencies:
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
////////////////////////////////////////////////////////////////////////////////

module Control_tb;

	// Inputs
	reg [5:0] funct;
	reg [5:0] op;

	// Outputs
	wire [1:0] WRsel;
	wire [1:0] WDsel;
	wire RFWr;
	wire EXTOp;
	wire Bsel;
	wire [1:0] ALUOp;
	wire DMWr;
	wire Br;
	wire LUIsel;
	wire Jal;
	wire Jr;

	// Instantiate the Unit Under Test (UUT)
	Control uut (
		.funct(funct), 
		.op(op), 
		.WRsel(WRsel), 
		.WDsel(WDsel), 
		.RFWr(RFWr), 
		.EXTOp(EXTOp), 
		.Bsel(Bsel), 
		.ALUOp(ALUOp), 
		.DMWr(DMWr), 
		.Br(Br), 
		.LUIsel(LUIsel), 
		.Jal(Jal), 
		.Jr(Jr)
	);

	initial begin
		// Initialize Inputs
		funct = 0;
		op = 0;
		#100;
		funct = 6'b100001;
		#10 op = 6'b001101;
		#10 op = 6'b000011;
        
		// Add stimulus here

	end
      
endmodule

