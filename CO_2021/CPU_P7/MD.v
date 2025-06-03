`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    22:55:42 11/25/2021 
// Design Name: 
// Module Name:    MD 
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
`define mdmult 3'd1
`define mdmultu 3'd2
`define mddiv 3'd3
`define mddivu 3'd4
`define mdmthi 3'd5
`define mdmtlo 3'd6

module MD(
    input [31:0] MDA,
    input [31:0] MDB,
    input [2:0] MDOp,
    input we,
    input clk,
    input reset,
    output [31:0] HIout,
    output [31:0] LOout,
    output start,
    output busy
    );
    reg [4:0] multcount, divcount;
    reg [31:0] HI, LO;
    wire [63:0] temp;
    assign temp = (MDOp == `mdmult)? $signed({{32{MDA[31]}}, MDA}) * $signed({{32{MDB[31]}}, MDB}) :
                  (MDOp == `mdmultu)? {{32'b0}, MDA} * {{32'b0}, MDB} : 0;

    always @(posedge clk) begin
        if (reset) begin
            HI <= 0;
            LO <= 0;
        end
        else if (we) begin
            if (MDOp == `mdmult || MDOp == `mdmultu) begin
                HI <= temp[63:32];
                LO <= temp[31:0];
            end
            else if (MDOp == `mddiv) begin
                HI <= $signed(MDA) % $signed(MDB);
                LO <= $signed(MDA) / $signed(MDB);
            end
            else if (MDOp == `mddivu) begin
                HI <= MDA % MDB;
                LO <= MDA / MDB;
            end
            else if (MDOp == `mdmthi) begin
                HI <= MDA;
            end
            else if (MDOp == `mdmtlo) begin
                LO <= MDA;
            end
        end
    end

    always @(posedge clk) begin
        if (reset) begin
            multcount <= 0;
            divcount <= 0;
        end
        else if (MDOp == `mdmult || MDOp == `mdmultu || multcount != 0) begin
            if (multcount <= 4) begin
                multcount = multcount + 1;
            end
            else multcount <= 0;
        end
        else if (MDOp == `mddiv || MDOp == `mddivu || divcount != 0) begin
            if (divcount <= 9) begin
                divcount = divcount + 1;
            end
            else divcount <= 0;
        end
    end
    assign start = ((MDOp == `mdmult) | (MDOp == `mdmultu) | (MDOp == `mddiv) | (MDOp == `mddivu));
    assign busy = ((multcount != 0) | (divcount != 0));
    assign HIout = HI;
    assign LOout = LO;


endmodule
