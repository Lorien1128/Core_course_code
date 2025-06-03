`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    00:17:35 11/26/2021 
// Design Name: 
// Module Name:    DMW 
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
`define dmwsb 2'd1
`define dmwsh 2'd2
`define dmwsw 2'd3


module DMW(
    input [1:0] DMWOp,
    input [31:0] addr,
    output reg [3:0] byteen
    );
    wire [1:0] by;
    assign by = addr[1:0];

    always @(*) begin
        if (DMWOp == `dmwsb) begin
            case (by)
                2'd0 : byteen = 4'b0001;
                2'd1 : byteen = 4'b0010;
                2'd2 : byteen = 4'b0100;
                2'd3 : byteen = 4'b1000;
                default : byteen = 0;
            endcase
        end
        else if (DMWOp == `dmwsh) begin
            casex (by)
                2'b0x : byteen = 4'b0011;
                2'b1x : byteen = 4'b1100;
                default : byteen = 0;
            endcase
        end
        else if (DMWOp == `dmwsw) begin
            casex (by)
                2'bxx : byteen = 4'b1111; 
                default : byteen = 0;
            endcase
        end
        else byteen = 0;
    end

endmodule
