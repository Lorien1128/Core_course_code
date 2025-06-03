.text
ori $s0, $0, 0x7f00
ori $s1, $0, 0x7f10
addi $t0, $0, 5
sw $t0, 4($s0)
sw $t0, 4($s1)
lw $t1, 4($s0)
lw $t1, 4($s1)