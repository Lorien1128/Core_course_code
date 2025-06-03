`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    20:02:09 11/12/2021 
// Design Name: 
// Module Name:    IM 
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
module IM(
    input [31:0] IMA,
    output [31:0] IMD
    );
	 wire [9:0] ima;
	 assign ima = IMA[11:2];
    reg [31:0] store [0:1023];
    assign IMD = store[ima];

    initial begin
        $readmemh("code.txt",store);
    end
endmodule
