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
`define add 4'd0
`define sub 4'd1
`define sll 4'd2
`define srl 4'd3
`define sra 4'd4
`define aand 4'd5
`define oor 4'd6
`define xxor 4'd7
`define nnor 4'd8
`define slt 4'd9
`define sltu 4'd10
`define addu 4'd11
`define subu 4'd12

module ALU(
    input [31:0] A,
    input [31:0] B,
    input [3:0] ALUOp,
    output reg [31:0] ALUresult,
    output reg Overflow
    );
    wire [4:0] s;
    assign s = A[4:0];
    integer i;

    
    always @(*) begin
        case(ALUOp)
            `add : ALUresult = A + B;
            `addu : ALUresult = A + B;
            `sub : ALUresult = A - B;
            `subu : ALUresult = A - B;
            `sll : begin
                for (i = 0; i <= 31; i = i + 1) begin
                    if (i < s ) begin
                        ALUresult[i] = 1'b0;
                    end
                    else ALUresult[i] = B[i - s];
                end
            end
            `srl : begin
                for (i = 0; i <= 31; i = i + 1) begin
                    if (i < 32 - s) begin
                        ALUresult[i] = B[i + s];
                    end
                    else ALUresult[i] = 1'b0;
                end                
            end
            `sra : begin
                for (i = 0; i <= 31; i = i + 1) begin
                    if (i <= 31 - s) begin
                        ALUresult[i] = B[i + s];
                    end
                    else ALUresult[i] = B[31];
                end                   
            end
            `aand : ALUresult = A & B;
            `oor : ALUresult = A | B;
            `xxor : ALUresult = A ^ B;
            `nnor : ALUresult = ~(A | B);
            `slt : ALUresult = {31'b0, ($signed(A) < $signed(B))};
            `sltu : ALUresult = {31'b0, (A < B)};
            default : ALUresult = 0;
        endcase
    end

    always @(*) begin
        case(ALUOp)
            `add : begin
                if (A[31] == 1 && B[31] == 1 && ALUresult[31] == 0) begin
                    Overflow = 1;
                end
                else if (A[31] == 0 && B[31] == 0 && ALUresult[31] == 1) begin
                    Overflow = 1;
                end
                else Overflow = 0;
            end
            `sub : begin
                if (A[31] == 1 && B[31] == 0 && ALUresult[31] == 0) begin
                    Overflow = 1;
                end
                else if (A[31] == 0 && B[31] == 1 && ALUresult[31] == 1) begin
                    Overflow = 1;
                end
                else Overflow = 0;                
            end
            default : Overflow = 0;
        endcase
    end

    
endmodule
