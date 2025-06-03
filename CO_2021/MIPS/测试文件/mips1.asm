.text
ori $t0, $zero, 0x1234
lui $a1, 0xffff
ori $a1, $a1, 0xffff
slt $t9, $t0, $a1
slt $t9, $a1, $t0
slt $t9, $a1, $a1
jal label
addu $t3, $t0, $a1
subu $t4, $a1, $t0
subu $k1, $t0, $a1
addu $t4, $k1, $t0
addu $t4, $k1, $k1
subu $t5, $t0, $t4
subu $t6, $k1, $t4
lui $t1, 0x1010
ori $t1, $t1, 0xffff
sw $t1, 8($zero)
sw $k1, 4092($zero)
lw $s0, 8($zero)
lw $s1, 8($zero)
lw $s2, 4092($zero)
beq $s0, $t0, label
beq $s0, $s1, lab
label:
lui $v0, 0xffff
nop
ori $zero, $zero, 0xffff
jr $ra
lab:
