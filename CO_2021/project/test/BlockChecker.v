`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    17:06:21 10/21/2021 
// Design Name: 
// Module Name:    BlockChecker 
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
`define s0 4'd0
`define b1 4'd1
`define b2 4'd2
`define b3 4'd3
`define b4 4'd4
`define b5 4'd5
`define e1 4'd6
`define e2 4'd7
`define e3 4'd8
`define s1 4'd9

module BlockChecker(
    input clk,
    input reset,
    input [7:0] in,
    output result
    );
    reg [3:0] s;
    reg fail;
    reg [31:0] count;
    wire [7:0] ch;
    
    assign ch = (in >= "a" && in <= "z")? in :
                (in >= "A" && in <= "Z")? in - "A" + "a" : " " ;

    always @(posedge clk, posedge reset) begin
        if (reset) begin
            s <= `s0;
            fail <= 0;
            count <= 0;
        end
        else begin
            case(s)
                `s0: s <= (ch == "b")? `b1 :
                          (ch == "e")? `e1 :
                          (ch == " ")? `s0 : `s1;
                `s1: s <= (ch == " ")? `s0 : `s1;
                `b1: s <= (ch == "e")? `b2 :
                          (ch == " ")? `s0 : `s1;
                `b2: s <= (ch == "g")? `b3 :
                          (ch == " ")? `s0 : `s1;
                `b3: s <= (ch == "i")? `b4 :
                          (ch == " ")? `s0 : `s1;
                `b4: begin
                     s <= (ch == "n")? `b5 :
                          (ch == " ")? `s0 : `s1;
                     count <= count + (ch == "n");    
                end
                `b5: begin
                     s <= (ch == " ")? `s0 : `s1;
                     count <= count - (ch != " ");                     
                end
                `e1: s <= (ch == "n")? `e2 :
                          (ch == " ")? `s0 : `s1;
                `e2: begin
                     s <= (ch == "d")? `e3 :
                          (ch == " ")? `s0 : `s1;
                     count <= $signed(count - (ch == "d"));
                end
                `e3: begin
                     s <= (ch == " ")? `s0 : `s1;
                     fail <= (count == -1 && ch == " ")? 1'b1 : fail;
                     count <= (ch == " ")? count : $signed($signed(count) + $signed(1));
                end
                default: s <= `s0;
            endcase
				$monitor("count=%h",count);
        end
    end

    assign result = (fail == 1)? 0 :
                    (count == 0)? 1 : 0;
    
    initial begin
        s <= `s0;
        fail <= 0;
        count <= 0;
		  
    end

endmodule
