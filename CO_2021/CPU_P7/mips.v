`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    18:36:26 12/10/2021 
// Design Name: 
// Module Name:    mips 
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
module mips(
    input clk,                       
    input reset,                     
    input interrupt,                
    output [31:0] macroscopic_pc,    

    output [31:0] i_inst_addr,       
    input  [31:0] i_inst_rdata,     

    output [31:0] m_data_addr,       
    input  [31:0] m_data_rdata,      
    output [31:0] m_data_wdata,      
    output [3 :0] m_data_byteen,     

    output [31:0] m_inst_addr,       

    output w_grf_we,                 
    output [4 :0] w_grf_addr,        
    output [31:0] w_grf_wdata,      

    output [31:0] w_inst_addr        
    );

    assign macroscopic_pc = macro_pc;
    assign i_inst_addr = i_inst_addr_CPU;
    assign m_data_addr = (interrupt & answer_interrupt)? 32'h0000_7f20 : DEV_addr;
    assign m_data_wdata = DEV_wd;
    assign m_data_byteen = (interrupt & answer_interrupt)? 4'b1111 : DM_byteen;
    assign m_inst_addr = m_inst_addr_CPU;
    assign w_grf_we = w_grf_we_CPU;
    assign w_grf_addr = w_grf_addr_CPU;
    assign w_grf_wdata = w_grf_wdata_CPU;
    assign w_inst_addr = w_inst_addr_CPU;


    //cpu
    wire [5:0] HwInt; 
    wire [31:0] i_inst_addr_CPU, m_data_addr_CPU, m_data_wdata_CPU, m_inst_addr_CPU, w_grf_wdata_CPU, w_inst_addr_CPU, macro_pc;
    wire [3:0] m_data_byteen_CPU;
    wire [4:0] w_grf_addr_CPU;
    wire w_grf_we_CPU, answer_interrupt;
    //bridge
    wire [31:0] m_data_rdata_CPU, DEV_wd, DEV_addr;
    wire [3:0] DM_byteen;
    wire T0_we, T1_we;
    //T0
    wire [31:0] T0_dout;
    wire T0_IRQ;
    //T1
    wire [31:0] T1_dout;
    wire T1_IRQ;


    assign HwInt = {3'b0, interrupt, T1_IRQ, T0_IRQ};
    CPU cpu (
    .clk(clk), 
    .reset(reset), 
    .HwInt(HwInt), 
    .i_inst_rdata(i_inst_rdata), 
    .m_data_rdata(m_data_rdata_CPU), 
    .i_inst_addr(i_inst_addr_CPU), 
    .m_data_addr(m_data_addr_CPU), 
    .m_data_wdata(m_data_wdata_CPU), 
    .m_data_byteen(m_data_byteen_CPU), 
    .m_inst_addr(m_inst_addr_CPU), 
    .w_grf_we(w_grf_we_CPU), 
    .w_grf_addr(w_grf_addr_CPU), 
    .w_grf_wdata(w_grf_wdata_CPU), 
    .w_inst_addr(w_inst_addr_CPU),
    .macro_pc(macro_pc),
    .answer_interrupt(answer_interrupt)
    );






    Bridge bridge (
    .CPU_addr(m_data_addr_CPU), 
    .CPU_wd(m_data_wdata_CPU), 
    .DM_rd(m_data_rdata), 
    .T0_rd(T0_dout), 
    .T1_rd(T1_dout), 
    .CPU_byteen(m_data_byteen_CPU), 
    .CPU_rd(m_data_rdata_CPU), 
    .DEV_wd(DEV_wd), 
    .DEV_addr(DEV_addr), 
    .DM_byteen(DM_byteen), 
    .T0_we(T0_we), 
    .T1_we(T1_we)
    );




    TC T0 (
    .clk(clk), 
    .reset(reset), 
    .Addr(DEV_addr[31:2]), 
    .WE(T0_we), 
    .Din(DEV_wd), 
    .Dout(T0_dout), 
    .IRQ(T0_IRQ)
    );




    TC T1 (
    .clk(clk), 
    .reset(reset), 
    .Addr(DEV_addr[31:2]), 
    .WE(T1_we), 
    .Din(DEV_wd), 
    .Dout(T1_dout), 
    .IRQ(T1_IRQ)
    );






endmodule
