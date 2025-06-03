.text
ori $t0, $zero, -3
ori $t1, $zero, 2
mult $t0, $t1
addu $t2, $t1, $t0
mfhi $t2
mflo $t2
multu $t0, $t1
mfhi $t2
mflo $t2
div $t0, $t1
mfhi $t2
mflo $t2
divu $t0, $t1
mfhi $t2
mflo $t2
mthi $t1
mtlo $t0
mfhi $t2
mflo $t2