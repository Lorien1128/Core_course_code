
li $0, 0x2aa511bb
li $1, 0x38940ed9
li $2, 0x33ae74bf
li $3, 0x700b8adf
li $4, 0x6697ab06
li $5, 0x6e21da
lhu $2, 26($0)
divu $0, $5
slti $5, $1, 0x503b
xori $3, $3, 0x5713
and $0, $5, $2
andi $0, $3, 0x754e
divu $0, $1
div $4, $1
sh $3, 40($0)
multu $0, $3
mflo $5
divu $2, $0
nor $4, $4, $1
mthi $1
sllv $4, $4, $0
sh $2, 50($0)
xor $1, $1, $5
multu $1, $1
sllv $2, $2, $1
lw $1, 0($0)
srav $1, $2, $2
mtlo $3
lbu $1, 13($0)
mfhi $4
subu $3, $3, $2
mfc0 $t9, $14
addiu $t9, $t9, 4
mtc0 $t9, $14
eret
ori $2, $4, 0x2101