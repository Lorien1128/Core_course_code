`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    21:14:00 11/12/2021 
// Design Name: 
// Module Name:    IFU 
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
`define brbeq 3'd1
`define brbne 3'd2
`define brblez 3'd3
`define brbgtz 3'd4
`define brbltz 3'd5
`define brbgez 3'd6

module IFU(
    input clk,
    input reset,
    input we,
    input [2:0] BROp,
    input zero,
    input less,
    input Jal,
    input Jr,
    input [31:0] ra,
    input [15:0] immin,
    input [25:0] addrin,
    input [31:0] insin,
    output [5:0] funct,
    output [4:0] shamt,
    output [4:0] rd,
    output [4:0] rt,
    output [4:0] rs,
    output [5:0] op,
    output [15:0] immout,
    output [25:0] addrout,
    output [31:0] pcout,
    output [31:0] pc8,
    output [31:0] ins
    );
    wire [15:0] imm16;
    wire [25:0] imm26;
    wire [31:0] pcplus4;
    reg [31:0] pc;

    always @(posedge clk) begin
        if (reset) begin
            pc <= 32'h0000_3000;
        end
        else if (we) begin
            if ((BROp == `brbeq) & zero) begin
                pc <= $signed(pc) + $signed({immin, 2'b0});
            end
            else if ((BROp == `brbne) & ~zero) begin
                pc <= $signed(pc) + $signed({immin, 2'b0});
            end
            else if ((BROp == `brblez) & (less | zero)) begin
                pc <= $signed(pc) + $signed({immin, 2'b0});
            end
            else if ((BROp == `brbgtz) & ~(less | zero)) begin
                pc <= $signed(pc) + $signed({immin, 2'b0});
            end
            else if ((BROp == `brbltz) & less) begin
                pc <= $signed(pc) + $signed({immin, 2'b0});
            end
            else if ((BROp == `brbgez) & ~less) begin
                pc <= $signed(pc) + $signed({immin, 2'b0});
            end
            else if (Jal) begin
                pc <= {pc[31:28], addrin, 2'b0};
            end
            else if (Jr) begin
                pc <= ra;
            end
            else begin
                pc <= pc + 4;
            end
        end
    end



    assign funct = insin[5:0];
    assign shamt = insin[10:6];
    assign rd = insin[15:11];
    assign rt = insin[20:16];
    assign rs = insin[25:21];
    assign op = insin[31:26];
    assign immout = insin[15:0];

    assign addrout = insin[25:0];

    assign pcplus4 = pc + 4;
    assign pcout = pc;
    assign pc8 = pcplus4 + 4;
    assign ins = insin;

    initial begin
        pc <= 32'h0000_3000;
    end

endmodule
