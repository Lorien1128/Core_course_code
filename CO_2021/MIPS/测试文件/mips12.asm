.text
ori $t0, $zero, 2
j a1
nop
ori $t0, $zero, 2
a1:
jal a2
nop
ori $t0, $zero, 2
a2:
ori $t1, 0x302c
jr $t1
nop
ori $t0, $zero, 2

ori $t1, $zero, 0x303c
jalr $t2, $t1
nop
ori $t0, $zero, 2
ori $t0, $zero, 4

