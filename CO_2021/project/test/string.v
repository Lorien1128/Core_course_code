`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    01:02:46 10/21/2021 
// Design Name: 
// Module Name:    string 
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
`define s0 2'b00
`define s1 2'b01
`define s2 2'b10
`define s3 2'b11

module string(
    input clk,
    input clr,
    input [7:0] in,
    output out
    );
    reg [1:0] s;
    wire norc;
    assign norc = (in >= "0" && in <= "9")? 1'b1 :
                  (in == "+" || in == "*")? 1'b0 : 1'bx;
    always @(posedge clk,posedge clr) begin
        if(clr == 1)begin
            s <= `s0;
        end
        else begin
            case(norc)
                1'b0:begin
                    if(s == `s0 || s == `s1 || s == `s3) s <= `s3;
                    else if(s == `s2) s <= `s1;
                end
                1'b1:begin
                    if (s == `s0 || s == `s1) begin
                        s <= `s2;
                    end
                    else if (s == `s2 || s == `s3) begin
                        s <= `s3;
                    end
                end
                default: begin 
                    s <= `s0;
                end
            endcase
        end
    end

    assign out = (s == `s2)? 1'b1 : 1'b0;

    initial begin
        s <= `s0;
    end
endmodule
