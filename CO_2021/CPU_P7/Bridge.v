`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    14:36:37 12/09/2021 
// Design Name: 
// Module Name:    Bridge 
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
module Bridge(
    input [31:0] CPU_addr,
    input [31:0] CPU_wd,
    input [31:0] DM_rd,
    input [31:0] T0_rd,
    input [31:0] T1_rd,
    input [3:0] CPU_byteen,
    output [31:0] CPU_rd,
    output [31:0] DEV_wd,
    output [31:0] DEV_addr,
    output [3:0] DM_byteen,
    output T0_we,
    output T1_we
    );

    wire HitDM, HitT0, HitT1;
    assign HitDM = ((CPU_addr >= 32'h0000_0000) & (CPU_addr <= 32'h0000_2fff));
    assign HitT0 = ((CPU_addr >= 32'h0000_7f00) & (CPU_addr <= 32'h0000_7f0b));
    assign HitT1 = ((CPU_addr >= 32'h0000_7f10) & (CPU_addr <= 32'h0000_7f1b));

    assign CPU_rd = (HitDM)? DM_rd :
                    (HitT0)? T0_rd :
                    (HitT1)? T1_rd : 32'h0;
    assign DEV_wd = CPU_wd;
    assign DEV_addr = CPU_addr;
    assign DM_byteen = (HitDM)? CPU_byteen : 4'b0;
    assign T0_we = (HitT0 & (CPU_byteen == 4'b1111))? 1'b1 : 1'b0;
    assign T1_we = (HitT1 & (CPU_byteen == 4'b1111))? 1'b1 : 1'b0;


endmodule
