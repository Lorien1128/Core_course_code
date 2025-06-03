.text
ori $t0, 2
ori $t1, -3
mult $t0, $t1
mflo $t2
bltz $t2, a1
addi $t2, $t2, 5
nop
ori $t3, $zero, 4
a1:
sb $t1, 3($zero)
lh $t4, 2($zero)
sll $t4, $t4, 2
or $t5, $t4, $zero
bne $t4, $t5, a1
sh $t5, 6($zero)
jal a2
nop
ori $s0, $zero, 0
a2:
ori $t0, 7
ori $t1, 2
div $t0, $t1
mtlo $t0
mflo $s1