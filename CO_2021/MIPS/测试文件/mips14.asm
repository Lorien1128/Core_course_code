.text
ori $s0, $0, 0x7f00
ori $s1, $0, 0x7f10
ori $t0, $0, 5
nop
addu $t1, $t0, $t0
sw $t1, 5($0)
sh $t1, 7($0)
sh $t1, 4($s0)
sw $t1, 8($s1)
sw $t1, 40($s0)
lhu $t2, 9($0)
lb $t2, 4($s0)
lw $t2, 0x3000($0)
ori $s2, 0x7fffff00
lw $t2, 0x7fff($s2)
add $t2, $s2, $s2
ori $s3, 0x80000000
sub $t2, $s3, $s2