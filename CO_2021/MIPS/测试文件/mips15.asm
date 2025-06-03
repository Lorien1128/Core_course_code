.text
ori $t0, $0, 0xfc01
mtc0 $t0, $12
nop
j a1
addu $t1, $t0, $0
nop
sub $t3, $0, $0
a1:
ori $t3, $0, 123

.ktext 0x4180
mfc0 $k0, $14
addiu $k0, $k0, 4
mtc0 $k0, $14
eret
