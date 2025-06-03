`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    15:50:08 11/18/2021 
// Design Name: 
// Module Name:    pipReg 
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
module pipReg(
    input [31:0] ins_i,
    input [31:0] pc_i,
    input [31:0] pc8_i,
    input [31:0] d1_i,
    input [31:0] d2_i,
    input [31:0] d3_i,    
    input [1:0] WRsel_i,
    input [1:0] WDsel_i,
    input RFWr_i,
    input EXTOp_i,
    input Asel_i,
    input Bsel_i,
    input [3:0] ALUOp_i,

    input [2:0] MDOp_i,

    input [1:0] DMWOp_i,
    input [2:0] DMROp_i,
    input [2:0] BROp_i,
    input [1:0] AMsel_i,
    input LUIsel_i,
    input Jal_i,
    input Jr_i,
    input [4:0] shamt_i,
    input [4:0] rd_i,
    input [4:0] rt_i,
    input [4:0] rs_i,
    input [15:0] imm_i,
    input [25:0] addr_i,
    input clk,
    input we,
    input clear,
    input reset,
    output reg [31:0] ins_o,
    output reg [31:0] pc_o,
    output reg [31:0] pc8_o,
    output reg [31:0] d1_o,
    output reg [31:0] d2_o,
    output reg [31:0] d3_o, 
    output reg [1:0] WRsel_o,
    output reg [1:0] WDsel_o,
    output reg RFWr_o,
    output reg EXTOp_o,
    output reg Asel_o,
    output reg Bsel_o,
    output reg [3:0] ALUOp_o,

    output reg [2:0] MDOp_o,

    output reg [1:0] DMWOp_o,
    output reg [2:0] DMROp_o,
    output reg [2:0] BROp_o,
    output reg [1:0] AMsel_o,
    output reg LUIsel_o,
    output reg Jal_o,
    output reg Jr_o,
    output reg [4:0] shamt_o,
    output reg [4:0] rd_o,
    output reg [4:0] rt_o,
    output reg [4:0] rs_o,
    output reg [15:0] imm_o,
    output reg [25:0] addr_o,
    input Temp_i,
    output reg Temp_o
    );


    always @(posedge clk) begin
        if (clear | reset) begin
            ins_o <= 0;
            pc_o <= 0; 
            pc8_o <= 0; 
            d1_o <= 0;
            d2_o <= 0;
            d3_o <= 0;
            WRsel_o <= 0; 
            WDsel_o <= 0;
            RFWr_o <= 0;
            EXTOp_o <= 0;
            Asel_o <= 0;
            Bsel_o <= 0;
            ALUOp_o <= 0;
   
            MDOp_o <= 0;
 
            DMWOp_o <= 0;
            DMROp_o <= 0;
            BROp_o <= 0;
            AMsel_o <= 0;
            LUIsel_o <= 0;
            Jal_o <= 0;
            Jr_o <= 0;
            shamt_o <= 0;
            rd_o <= 0;
            rt_o <= 0;
            rs_o <= 0;
            imm_o <= 0;
            addr_o <= 0; 
            Temp_o <= 0;
        end
        else if (we) begin
            ins_o <= ins_i;
            pc_o <= pc_i; 
            pc8_o <= pc8_i; 
            d1_o <= d1_i;
            d2_o <= d2_i;
            d3_o <= d3_i;
            WRsel_o <= WRsel_i; 
            WDsel_o <= WDsel_i;
            RFWr_o <= RFWr_i;
            EXTOp_o <= EXTOp_i;
            Asel_o <= Asel_i;
            Bsel_o <= Bsel_i;
            ALUOp_o <= ALUOp_i;
    
            MDOp_o <= MDOp_i;
   
            DMWOp_o <= DMWOp_i;
            DMROp_o <= DMROp_i;
            BROp_o <= BROp_i;
            AMsel_o <= AMsel_i;
            LUIsel_o <= LUIsel_i;
            Jal_o <= Jal_i;
            Jr_o <= Jr_i;
            shamt_o <= shamt_i;
            rd_o <= rd_i;
            rt_o <= rt_i;
            rs_o <= rs_i;
            imm_o <= imm_i;
            addr_o <= addr_i;       
            Temp_o <= Temp_i;              
        end
    end
    
  

    initial begin
        ins_o <= 0;
        pc_o <= 0; 
        pc8_o <= 0; 
        d1_o <= 0;
        d2_o <= 0;
        d3_o <= 0;
        WRsel_o <= 0; 
        WDsel_o <= 0;
        RFWr_o <= 0;
        EXTOp_o <= 0;
        Asel_o <= 0;
        Bsel_o <= 0;
        ALUOp_o <= 0;
    
        MDOp_o <= 0;
   
        DMWOp_o <= 0;
        DMROp_o <= 0;
        BROp_o <= 0;
        AMsel_o <= 0;
        LUIsel_o <= 0;
        Jal_o <= 0;
        Jr_o <= 0;
        shamt_o <= 0;
        rd_o <= 0;
        rt_o <= 0;
        rs_o <= 0;
        imm_o <= 0;
        addr_o <= 0;   
        Temp_o <= 0;      
    end   


endmodule
