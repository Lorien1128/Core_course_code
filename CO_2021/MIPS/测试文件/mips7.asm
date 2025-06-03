.text
a1:
ori $t0, $zero,3
ori $t1, $zero, -2
addi $t2, $t1, 4
addiu $t2, $t1, 4
add $t2, $t0, $t1
addu $t2, $t0, $t1
sub $t2, $t0, $t1
subu $t2, $t0, $t1
lui $t0, 0xffff
ori $t0, $t0, 0xffff
sll $t2, $t0, 4
srl $t2, $t0, 4
sra $t2, $t0, 4
ori $t1, $zero, 4
sllv $t2, $t0, $t1
srlv $t2, $t0, $t1
srav $t2, $t0, $t1
ori $t0, $zero, 123
ori $t1, $zero, 456
and $t2, $t0, $t1
andi $t2, $t0, 456
or $t2, $t0, $t1
ori $t2, $t0, 456
xor $t2, $t0, $t1
xori $t2, $t0, 456
nor $t2, $t1, $t0
ori $t0, $zero, 3
ori $t1, $zero, -2
slt $t2, $t0, $t1
sltu $t2, $t0, $t1
slti $t2, $t0, -2
sltiu $t2, $t0, -2













