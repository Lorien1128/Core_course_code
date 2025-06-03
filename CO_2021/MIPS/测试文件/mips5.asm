.text
ori $t0, $zero, 1
sw $t0, 0($zero)
ori $t1, $zero, 2
addu $t0, $t0, $t1
sw $t0, 0($zero)
subu $t0, $t0, $t1
sw $t0, 0($zero)
lw $t0, 0($zero)
sw $t0, 0($zero)
lui $t0, 1
sw $t0, 0($zero)
jal a
sw $ra, 0($zero)
nop
a:

