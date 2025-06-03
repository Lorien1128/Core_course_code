`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:22:04 11/13/2021 
// Design Name: 
// Module Name:    EXT 
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
module EXT(
    input [15:0] EXTin,
    input EXTOp,
    input LUIsel,
    output [31:0] EXTout
    );

    assign EXTout = (LUIsel == 1)? {EXTin, {16{1'b0}}} :
                    (EXTOp == 0)? {{16{1'b0}}, EXTin} :
                    (EXTOp == 1)? {{16{EXTin[15]}}, EXTin} : 0;

endmodule
