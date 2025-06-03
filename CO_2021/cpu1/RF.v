`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    23:58:15 11/12/2021 
// Design Name: 
// Module Name:    RF 
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
module RF(
    input [4:0] A1,
    input [4:0] A2,
    input [4:0] A3,
    input [31:0] WD,
    input clk,
    input reset,
    input we,
    input [31:0] pc,
    output [31:0] RD1,
    output [31:0] RD2
    );
    reg [31:0] grf [0:31];
    integer i;

    always @(posedge clk) begin
        if (reset) begin
            for (i = 0; i <= 31; i = i + 1) begin
                if (i == 28) begin
                    grf[i] <= 32'h0000_1800;
                end
                else if (i == 29) begin
                    grf[i] <= 32'h0000_2ffc;
                end
                else begin
                    grf[i] <= 0;
                end
            end
        end
        else if (we) begin
            if (A3 != 0) begin
                grf[A3] <= WD;
                $display("@%h: $%d <= %h", pc, A3, WD);
            end
        end
    end

    assign RD1 = grf[A1];
    assign RD2 = grf[A2];

    initial begin
        for (i = 0; i <= 31; i = i + 1) begin
            if (i == 28) begin
                grf[i] <= 32'h0000_1800;
            end
            else if (i == 29) begin
                grf[i] <= 32'h0000_2ffc;
            end
            else begin
                grf[i] <= 0;
            end
        end      
    end

endmodule
