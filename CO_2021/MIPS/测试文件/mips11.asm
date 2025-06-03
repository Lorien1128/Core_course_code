.text
ori $t0, $zero , 3
ori $t1, $zero, 4
bne $t0, $t1, a1
nop
ori $t0, $zero , 3

a1:
ori $t0, $zero, -2
blez $t0, a2
nop
ori $t0, $zero , 3

a2:
ori $t0, $zero , 0
bgtz $t0, a3
nop
ori $t0, $zero , 3

a3:
ori $t0, $zero , 0
bgez $t0, a4
nop
ori $t0, $zero , 3
a4: