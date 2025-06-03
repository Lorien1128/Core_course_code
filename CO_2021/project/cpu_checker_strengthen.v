`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date:    23:03:51 09/15/2021 
// Design Name: 
// Module Name:    cpu_checker 
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
`define s1 4'd1

`define s3 4'd3

`define s5 4'd5
`define s6 4'd6
`define s7 4'd7
`define s8 4'd8
`define s9 4'd9

`define s11 4'd11
`define p6 4'd12
`define p7 4'd13
`define c0 4'd0 //^
`define c1 4'd1 //@
`define c2 4'd2 //:
`define c3 4'd3 //$
`define c4 4'd4 //star
`define c5 4'd5 //<
`define c6 4'd6 //=
`define c7 4'd7 //#
`define num 4'd8
`define af 4'd9
`define space 4'd10
`define other 4'd11
`define s 1'b0
`define p 1'b1
module cpu_checker(
    input clk,
    input reset,
    input [7:0] char,
    input [15:0] freq,
    output [1:0] format_type,
    output [3:0] error_code
    );
    reg [2:0] Dcount;
    reg [3:0] Hcount;
    reg [3:0] s;
    wire [3:0] wchar;
    reg [31:0] store;
    reg [31:0] storeonce;
    reg [3:0] error;
    wire [7:0] charnum;
    
    reg sp;
    reg [3:0] j;
    reg [3:0] jcount;
	integer i;
    integer k;
    
    always @(clk)
	 begin
	    jcount = 0;
        if (freq[0] == 1) begin
            j = 0;
        end
        else begin
            for (i = 0;i <= 15;i = i + 1) begin
                if (freq[i] == 1) begin
                   j = jcount - 4'd1;
                end
                jcount = jcount + 4'd1;
            end
         end
        
    end
        

    
    assign wchar = (char == "^")? `c0 :
                   (char == "@")? `c1 :
                   (char == ":")? `c2 :
                   (char == "$")? `c3 :
                   (char == 8'd42)? `c4 :
                   (char == "<")? `c5 :
                   (char == "=")? `c6 :
                   (char == "#")? `c7 :
                   (char == " ")? `space :
                   (char >= "0" && char <= "9")? `num :
                   (char >= "a" && char <= "f")? `af : `other;
    assign charnum = (wchar == `num)? char - 8'd48 :
                     (wchar == `af)? char - 8'd97 + 8'd10 : 8'd0;
    
    always @(posedge clk) begin
        if (reset == 1) begin
            Dcount <= 0;
            Hcount <= 0;
            s <= `s0;
            store <= 0;
            error <= 0;
            storeonce <= 0;
        end
        else if (reset == 0) begin
            if (wchar == `c0) begin
                s <= `s1;
                Dcount <= 0;
                Hcount <= 0;
                store <= 0;
                storeonce <= 0;
                error <= 0;
            end
            else if (s == `s1 && wchar ==`num) begin//time
                Dcount <= Dcount + 3'b001;
                storeonce <= ((storeonce << 3) + (storeonce << 1)) + charnum;
            end
            else if (s == `s1 && wchar == `c1) begin
                if (Dcount >= 1 && Dcount <= 4) begin//格式
                    s <= `s3;
                    Dcount <= 0;
                    for (k = 0;k < j;k = k +1) begin//合法�
                        if (storeonce[k] == 1) begin
                            error[0] <= 1'b1;
                        end
                    end
                end
                else begin
                    s <= `s0;
                    Dcount <= 0;
                    error <= 0;
                    storeonce <= 0;
                end
            end
            else if (s == `s3 && (wchar == `num || wchar == `af)) begin//pc
                Hcount <= Hcount + 4'b0001;
                store <= (store << 4) + charnum;
            end
            else if (s == `s3 && wchar == `c2) begin
                if (Hcount == 8) begin//格式
                    s <= `s5;
                    Hcount <= 0;
                    if (store >= 32'h00003000 && store <= 32'h00004fff && store[1] == 0 && store[0] == 0) begin//合法�
                        store <= 0;
                    end
                    else begin
                        error[1] <= 1'b1;
                        store <= 0;
                    end
                end
                else begin
                    s <= `s0;
                    Hcount <= 0;
                    store <= 0;
                    error <= 0;
                end  
            end
            else if (s == `s5 && wchar == `space) begin
                s <= `s5;
            end
            else if (s == `s5 && wchar == `c3) begin
                s <= `s6;
                sp <= `s;
            end
            else if (s == `s5 && wchar == `c4) begin
                s <= `p6;
                sp <= `p;
            end

            else if (s == `s6 && wchar == `num) begin//grf
                Dcount <= Dcount + 3'b001;
                store <= ((store << 3) + (store << 1)) + charnum;
            end
            else if (s == `s6 && wchar == `space) begin
                if (Dcount >= 1 && Dcount <= 4) begin
                    s <= `s7;
                    Dcount <= 0;
                    if (store >= 0 && store <= 31) begin
                        store <= 0;
                    end
                    else begin
                        error[3] <= 1'b1;
                        store <= 0;
                    end
                end
                else begin
                    s <= `s0;
                    Dcount <= 0;
                    store <= 0;
                    error <= 0;
                end
            end
            else if (s == `s7 && wchar == `space) begin
                s <= `s7;
            end
            else if (s == `s7 && wchar == `c5 ) begin
                s <= `s8;
            end
            else if (s == `s6 && wchar == `c5) begin
                if (Dcount >= 1 && Dcount <= 4) begin
                    s <= `s8;
                    Dcount <= 0;
                    if (store >= 0 && store <= 31) begin
                        store <= 0;
                    end
                    else begin
                        error[3] <= 1'b1;
                        store <= 0;
                    end
                end
                else begin
                    s <= `s0;
                    Dcount <= 0;
                    store <= 0;
                    error <= 0;
                end
            end
            
            else if (s == `p6 && (wchar == `num || wchar == `af)) begin//addr
                Hcount <= Hcount + 4'b0001;
                store <= (store << 4) + charnum;
            end
            else if (s == `p6 && wchar == `space) begin
                if (Hcount == 8) begin
                    s <= `p7;
                    Hcount <= 0;
                    if (store >= 32'h00000000 && store <= 32'h00002fff && store[1] == 0 && store[0] == 0) begin//合法�
                        store <= 0;
                    end
                    else begin
                        error[2] <= 1'b1;
                        store <= 0;
                    end
                end
                else begin
                    s <= `s0;
                    Hcount <= 0;
                    store <= 0;
                    error <= 0;
                end
            end
            else if (s == `p7 && wchar == `space) begin
                s <= `p7;
            end
            else if (s == `p7 && wchar == `c5) begin
                s <= `s8;
            end
            else if (s == `p6 && wchar == `c5) begin
                if (Hcount == 8) begin
                    s <= `s8;
                    Hcount <= 0;
                    if (store >= 32'h00000000 && store <= 32'h00002fff && store[1] == 0 && store[0] == 0) begin//合法�
                        store <= 0;
                    end
                    else begin
                        error[2] <= 1'b1;
                        store <= 0;
                    end
                end
                else begin
                    s <= `s0;
                    Hcount <= 0;
                    store <= 0;
                    error <= 0;
                end
            end

            else if (s == `s8 && wchar == `c6) begin
                s <= `s9;
            end
            else if (s == `s9 && wchar == `space && Hcount == 0) begin
                s <= `s9;
            end
            else if (s == `s9 && (wchar == `num || wchar == `af)) begin
                Hcount <= Hcount + 4'b0001;
            end
            else if (s == `s9 && wchar == `c7) begin
                if (Hcount == 8) begin
                    s <= `s11;
                    Hcount <= 0;
                end
                else begin
                    s <= `s0;
                    Hcount <= 0;
                end
            end
            else begin
                s <= `s0;
                Dcount <= 0;
                Hcount <= 0;
                store <= 0;
                error <= 0;
                storeonce <= 0;
            end
            if (s == `s3) begin
                storeonce <= 0;
            end
        end
    end
    
    assign format_type = (s != `s11)? 2'b00 :
                         (sp == `s)? 2'b01 : 2'b10;
    assign error_code = (s == `s11)? error : 4'b0000;
    initial begin
        Dcount <= 0;
        Hcount <= 0;
        s <= `s0;
        sp <= 0;
        store <= 0;
        error <= 0;
        jcount <= 0;
        storeonce <= 0;
		  j <= 0;
    end
    

endmodule
