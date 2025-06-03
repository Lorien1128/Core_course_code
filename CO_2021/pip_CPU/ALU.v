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
    output reg [31:0] ALUresult
    );
    wire by;
    assign by = ($signed(A) < $signed(B));
    
    always @(*) begin
        case(ALUOp)
            2'b00 : ALUresult = A + B;
            2'b01 : ALUresult = A - B;
            2'b10 : ALUresult = A | B;
            2'b11 : ALUresult = {31'b0, by};
            default : ALUresult = 0;
        endcase
    end

    assign zero = (A == B);
    
endmodule
