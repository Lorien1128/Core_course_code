`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:38:02 11/13/2021 
// Design Name: 
// Module Name:    DM 
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
module DM(
    input clk,
    input reset,
    input we,
    input [31:0] pc,
    input [31:0] addr,
    input [31:0] din,
    output [31:0] dout
    );
    reg [31:0] store [0:1023];
    wire [9:0] a;
    integer i;

    assign a = addr[11:2];
    always @(posedge clk) begin
        if (reset) begin
            for (i = 0; i <= 1023; i = i + 1) begin
                store[i] <= 0;
            end
        end
        else if (we) begin
            store[a] <= din;
            $display("@%h: *%h <= %h", pc, addr, din);
        end
    end
    assign dout = store[a];

    initial begin
        for (i = 0; i <= 1023; i = i + 1) begin
            store[i] <= 0;
        end       
    end

endmodule
