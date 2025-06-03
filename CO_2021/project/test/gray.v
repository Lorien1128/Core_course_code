`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:43:22 10/21/2021 
// Design Name: 
// Module Name:    gray 
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
module gray(
    input Clk,
    input Reset,
    input En,
    output [2:0] Output,
    output reg Overflow
    );
    reg [2:0] count;
    always @(posedge Clk) begin
        if (Reset == 1) begin
            count <= 0;
            Overflow <= 0;
        end
        else if (En == 1) begin
            if (count <= 6) begin
                count <= count + 1;
            end
            else if (count == 7) begin
                count <= 0;
                Overflow <= 1;
            end
        end
    end

    assign Output[2] = count[2];
    assign Output[1] = count[2] ^ count[1];
    assign Output[0] = count[1] ^ count[0];

    initial begin
        Overflow <= 0;
        count <= 0;
    end


endmodule
