`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:00:55 11/13/2021 
// Design Name: 
// Module Name:    ALU 
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
module ALU(
    input [31:0] A,
    input [31:0] B,
    input [1:0] ALUOp,
    output zero,
    output [31:0] ALUresult
    );

    assign ALUresult = (ALUOp == 2'b00)? A + B :
                       (ALUOp == 2'b01)? A - B :
                       (ALUOp == 2'b10)? A | B : 0;
    assign zero = (A == B);
    
endmodule
