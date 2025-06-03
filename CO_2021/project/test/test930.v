`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:30:45 09/30/2021 
// Design Name: 
// Module Name:    test930 
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
module test930(
    input [3:0] in,
    output [3:0] out
    );
reg [3:0] temp1;
reg [3:0] temp2;
genvar i;
generate
    for(i = 0;i < 15;i = i + 1) begin
	     
        
		  #10;
		  temp1 <= temp2;
    end
endgenerate
assign out = temp1;
initial begin
    temp1 = 10;
end
endmodule
