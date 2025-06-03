.text
ori $t2, $zero, 0xc
ori $t0, $zero, 4
subu $t5, $t2, $t0
ori $s0, $zero, 2
sw $t5, 4($t2)