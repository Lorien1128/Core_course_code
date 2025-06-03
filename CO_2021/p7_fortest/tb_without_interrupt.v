`timescale 1ns/1ps

module mips_txt;

	reg clk;
	reg reset;
	reg interrupt;

	wire [31:0] pc;


	mips uut(
		.clk(clk),
		.reset(reset),
		.interrupt(interrupt),
		.addr(pc)
	);

	initial begin
		clk <= 0;
		reset <= 1;
		interrupt <= 0;
		#20 reset <= 0;
	end



	always #2 clk <= ~clk;

endmodule
