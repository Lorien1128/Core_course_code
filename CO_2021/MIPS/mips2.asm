.text
ori $t0 ,$0 ,0xfc01
mtc0 $t0, $12
div $t0, $t0
j a1
mflo $t1
nop
a1: