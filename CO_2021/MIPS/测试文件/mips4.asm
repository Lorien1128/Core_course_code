.text
ori $t0, $zero, 0x3020
ori $t1, $zero, 0x2021
sw $t0, 8($zero)
lw $ra, 8($zero)
jr $ra
sw $t0, 12($zero)
nop
nop
subu $t2, $t1, $t1
sw $ra, 16($zero)
addu $t1, $t0, $t0
sw $t1, 4($zero)
lw $t2, 4($zero)
lw $t3, 4($zero)
beq $t2, $t3, lab
sw $t2, 16($zero)
subu $s0, $t3, $t0
lab:
lw $t3, 8($zero)
beq $t2, $t3, lab
nop
lw $t2, 8($zero)
beq $t2, $t3, lab2
nop
subu $s1, $t2, $zero
lab2:
lw $t2, 0($zero)
beq $t2, $t3, lab










