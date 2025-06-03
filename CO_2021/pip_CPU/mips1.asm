.text
ori $t0, $zero, 0x1234
jal label
ori $a1, $t0, 0xffff
addu $t3, $t0, $a1
subu $t4, $t3, $t3
subu $k1, $t3, $t4
addu $zero, $t3, $t4
addu $t9, $zero, $zero
ori $t2, $zero, 0x300c
sw $t2, 12($zero)
lw $ra, 12($zero)
j spot
nop
nop
addu $t4, $k1, $t0
spot:
addu $t4, $k1, $k1
subu $t5, $t0, $t4
subu $t6, $k1, $t4
lui $t1, 0x1010
ori $t1, $t1, 0xffff
sw $t1, 8($zero)
sw $k1, 4092($zero)
lw $s2, 4092($zero)
addu $t7, $zero, $s2
ori $s5, $s2, 0x1357
lw $s0, 8($zero)
lw $s0, 4092($zero)
addu $s0, $s0, $s0
sw $s0, 8($zero)
lw $s0, 4092($zero)
beq $s0, $t0, label
nop
lw $s1, 4092($zero)
beq $s0, $s1, lab
nop
label:
lui $v0, 0xffff
nop
ori $zero, $zero, 0xffff
jr $ra
nop
lab:

