`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:27:43 09/15/2021 
// Design Name: 
// Module Name:    code 
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
module code(
    input Clk,
    input Reset,
    input Slt,
    input En,
    output reg [63:0] Output0,
    output reg [63:0] Output1
    );
    reg [1:0] counto1;
	 always @(posedge Clk) begin
	    if(Reset == 1) begin
		    Output0 <= 0;
			 Output1 <= 0;
			 counto1 <= 0;
		 end
		 
		 else if(En == 1 && Reset == 0) begin
			 if(Slt == 0) begin
			    Output0 <= Output0 + 1;
			 end
			 else if(Slt == 1) begin
			    if(counto1 == 2'b11) begin
				    Output1 <= Output1 + 1;
					 counto1 <= 0;
				 end
				 else if(counto1 != 2'b11) begin
			       counto1 <= counto1 + 2'b01;
				 end
			 end
		 end
	 end
	 
	 initial begin
	    Output0 <= 0;
		 Output1 <= 0;
		 counto1 <= 0;
	 end

endmodule
