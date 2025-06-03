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
    input Bsel_i,
    input [1:0] ALUOp_i,
    input DMWr_i,
    input Br_i,
    input LUIsel_i,
    input Jal_i,
    input Jr_i,
    input Slt_i,
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
    output [31:0] ins_o,
    output [31:0] pc_o,
    output [31:0] pc8_o,
    output [31:0] d1_o,
    output [31:0] d2_o,
    output [31:0] d3_o, 
    output [1:0] WRsel_o,
    output [1:0] WDsel_o,
    output RFWr_o,
    output EXTOp_o,
    output Bsel_o,
    output [1:0] ALUOp_o,
    output DMWr_o,
    output Br_o,
    output LUIsel_o,
    output Jal_o,
    output Jr_o,
    output Slt_o,
    output [4:0] shamt_o,
    output [4:0] rd_o,
    output [4:0] rt_o,
    output [4:0] rs_o,
    output [15:0] imm_o,
    output [25:0] addr_o
    );
    reg [31:0] ins, pc, pc8, d1, d2, d3;
    reg [1:0] WRsel, WDsel, ALUOp;
    reg [4:0] shamt, rd, rt, rs;
    reg [15:0] imm;
    reg [25:0] addr;
    reg RFWr, EXTOp, Bsel, DMWr, Br, LUIsel, Jal, Jr, Slt;

    always @(posedge clk) begin
        if (clear | reset) begin
            ins <= 0;
            pc <= 0; 
            pc8 <= 0; 
            d1 <= 0;
            d2 <= 0;
            d3 <= 0;
            WRsel <= 0; 
            WDsel <= 0;
            RFWr <= 0;
            EXTOp <= 0;
            Bsel <= 0;
            ALUOp <= 0;
            DMWr <= 0;
            Br <= 0;
            LUIsel <= 0;
            Jal <= 0;
            Jr <= 0;
            Slt <= 0;
            shamt <= 0;
            rd <= 0;
            rt <= 0;
            rs <= 0;
            imm <= 0;
            addr <= 0; 
        end
        else if (we) begin
            ins <= ins_i;
            pc <= pc_i; 
            pc8 <= pc8_i; 
            d1 <= d1_i;
            d2 <= d2_i;
            d3 <= d3_i;
            WRsel <= WRsel_i; 
            WDsel <= WDsel_i;
            RFWr <= RFWr_i;
            EXTOp <= EXTOp_i;
            Bsel <= Bsel_i;
            ALUOp <= ALUOp_i;
            DMWr <= DMWr_i;
            Br <= Br_i;
            LUIsel <= LUIsel_i;
            Jal <= Jal_i;
            Jr <= Jr_i;
            Slt <= Slt_i;
            shamt <= shamt_i;
            rd <= rd_i;
            rt <= rt_i;
            rs <= rs_i;
            imm <= imm_i;
            addr <= addr_i;                     
        end
    end
    
    assign ins_o = ins;
    assign pc_o = pc; 
    assign pc8_o = pc8; 
    assign d1_o = d1;
    assign d2_o = d2;
    assign d3_o = d3;   
    assign WRsel_o = WRsel; 
    assign WDsel_o = WDsel;
    assign RFWr_o = RFWr;
    assign EXTOp_o = EXTOp;
    assign Bsel_o = Bsel;
    assign ALUOp_o = ALUOp;
    assign DMWr_o = DMWr;
    assign Br_o = Br;
    assign LUIsel_o = LUIsel;
    assign Jal_o = Jal;
    assign Jr_o = Jr;
    assign Slt_o = Slt;
    assign shamt_o = shamt;
    assign rd_o = rd;
    assign rt_o = rt;
    assign rs_o = rs;
    assign imm_o = imm;
    assign addr_o = addr;  

    initial begin
        ins <= 0;
        pc <= 0; 
        pc8 <= 0; 
        d1 <= 0;
        d2 <= 0;
        d3 <= 0;
        WRsel <= 0; 
        WDsel <= 0;
        RFWr <= 0;
        EXTOp <= 0;
        Bsel <= 0;
        ALUOp <= 0;
        DMWr <= 0;
        Br <= 0;
        LUIsel <= 0;
        Jal <= 0;
        Jr <= 0;
        Slt <= 0;
        shamt <= 0;
        rd <= 0;
        rt <= 0;
        rs <= 0;
        imm <= 0;
        addr <= 0;         
    end   


endmodule
