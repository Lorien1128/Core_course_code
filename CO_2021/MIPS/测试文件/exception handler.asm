.text
ori $s0, $0, 0x7f00
ori $s1, $0, 0x7f10
ori $t0, $0, 5
sw $t0, 4($s0)
ori $t1, $0, 9
loop:
	sw $t1, 0($s0)
	nop
	nop
	nop
	nop
	nop
	nop
	j loop
	nop
	