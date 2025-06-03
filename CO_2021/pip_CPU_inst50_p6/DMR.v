`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    00:40:06 11/26/2021 
// Design Name: 
// Module Name:    DMR 
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
`define dmrlb 3'd1
`define dmrlbu 3'd2
`define dmrlh 3'd3
`define dmrlhu 3'd4
`define dmrlw 3'd5


module DMR(
    input [31:0] data,
    input [31:0] addr,
    input [2:0] DMROp,
    output reg [31:0] dataout
    );
    wire [1:0] by;
    assign by = addr[1:0];
    always @(*) begin
        if (DMROp == `dmrlb) begin
            case (by)
                2'd0 : dataout = {{24{data[7]}}, data[7:0]}; 
                2'd1 : dataout = {{24{data[15]}}, data[15:8]}; 
                2'd2 : dataout = {{24{data[23]}}, data[23:16]};
                2'd3 : dataout = {{24{data[31]}}, data[31:24]};  
                default: dataout = 32'd1;
            endcase
        end
        else if (DMROp == `dmrlbu) begin
            case (by)
                2'd0 : dataout = {24'b0, data[7:0]}; 
                2'd1 : dataout = {24'b0, data[15:8]}; 
                2'd2 : dataout = {24'b0, data[23:16]};
                2'd3 : dataout = {24'b0, data[31:24]};  
                default: dataout = 32'd1;
            endcase            
        end
        else if (DMROp == `dmrlh) begin
            casex (by)
                2'b0x : dataout = {{16{data[15]}}, data[15:0]}; 
                2'b1x : dataout = {{16{data[31]}}, data[31:16]};
                default: dataout = 32'd1;
            endcase            
        end
        else if (DMROp == `dmrlhu) begin
            casex (by)
                2'b0x : dataout = {16'b0, data[15:0]}; 
                2'b1x : dataout = {16'b0, data[31:16]};
                default: dataout = 32'd1;
            endcase              
        end
        else if (DMROp == `dmrlw) begin
            casex (by)
                2'bxx : dataout = data;
                default: dataout = 32'd1;
            endcase              
        end
        else dataout = 0;
    end

endmodule
