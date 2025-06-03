li $t0, 1
sw $t0, 0($zero)
li $t0, -1
sw $t0, 4($zero)
li $t0, -2
sw $t0, 8($zero)
li $t0, -3
sw $t0, 12($zero)
li $t0, 111
sw $t0, 16($zero)
li $t0, 222
sw $t0, 20($zero)
li $t0, 333
sw $t0, 24($zero)
li $t0, 1
sw $t0, 28($zero)
li $t0, 32
sw $t0, 32($zero)
li $t0, 2309
sw $t0, 36($zero)
li $t0, 8
sw $t0, 68($zero)
li $t0, 7
sw $t0, 64($zero)
li $t0, 6
sw $t0, 60($zero)
li $t0, 5
sw $t0, 56($zero)
li $t0, 4
sw $t0, 52($zero)
li $t0, 3
sw $t0, 48($zero)
li $t0, 2
sw $t0, 44($zero)
li $t0, 1
sw $t0, 40($zero)
j main
add1:
sw $a0, -4($sp)
lw $t2, -4($sp)
li $a0, 97
li $v0, 11
syscall
li $a0, 100
li $v0, 11
syscall
li $a0, 100
li $v0, 11
syscall
li $a0, 58
li $v0, 11
syscall
move $a0, $t2
li $v0, 1
syscall
li $a0, 32
li $v0, 11
syscall
lw $t2, -4($sp)
addiu $t2, $t2, 1
addiu $v0, $t2, 0
jr $ra
fun:
move $s3, $a0
move $s6, $a1
move $s5, $a2
move $s7, $a3
lw $s4, -20($sp)
lw $s2, -24($sp)
addu $t2, $s3, $s6
addu $t2, $t2, $s5
lw $t3, 4($s7)
mul $t4, $s3, $s5
mul $t4, $t4, $s2
addu $t2, $t2, $t3
sll $t0, $t2, 2
add $t0, $t0, $s4
sw $t4, 0($t0)
jr $ra
foo:
move $s6, $a0
move $s7, $a1
lw $t2, 4($s6)
sll $t0, $s7, 2
add $t0, $t0, $s6
lw $t3, 0($t0)
addu $t2, $t2, $t3
addiu $t2, $t2, 8
sw $t2, 4($s6)
lw $t2, 28($zero)
sw $t2, -16($sp)
lw $t2, 32($zero)
sw $t2, -12($sp)
addi $sp, $sp, -24
li $a0, -34
sw $s6, 4($sp)
sw $ra, 0($sp)
jal add1
lw $ra, 0($sp)
lw $s6, 4($sp)
addi $sp, $sp, 24
addiu $t2, $v0, 0
addi $t0, $sp, -16
addiu $t3, $t0, 0
addiu $t4, $s6, 0
addi $sp, $sp, -32
li $a0, -3
li $a1, 4
move $a2, $t2
move $a3, $t3
sw $t4, -20($sp)
li $t0, 114514
sw $t0, -24($sp)
sw $s6, 4($sp)
sw $ra, 0($sp)
jal fun
lw $ra, 0($sp)
lw $s6, 4($sp)
addi $sp, $sp, 32
lw $t2, 0($s6)
addiu $v0, $t2, 0
jr $ra
bar:
sw $a0, -4($sp)
lw $t0, -4($sp)
lw $t2, 12($t0)
sw $t2, -12($sp)
lw $t0, -4($sp)
lw $t2, 8($t0)
sw $t2, -8($sp)
lw $t0, -12284($sp)
lw $t1, -12284($sp)
addu $t2, $t0, $t1
addiu $s6, $t2, 0
lw $t0, -12284($sp)
addiu $s7, $t0, 0
addi $t0, $sp, -12
addiu $t2, $t0, 0
addi $sp, $sp, -32
move $a0, $t2
li $a1, 0
sw $s6, 8($sp)
sw $s7, 4($sp)
sw $ra, 0($sp)
jal foo
lw $ra, 0($sp)
lw $s7, 4($sp)
lw $s6, 8($sp)
addi $sp, $sp, 32
addiu $t2, $v0, 0
move $a0, $t2
li $v0, 1
syscall
li $a0, 32
li $v0, 11
syscall
lw $t2, -8($sp)
sll $t2, $t2, 1
move $a0, $t2
li $v0, 1
syscall
li $a0, 32
li $v0, 11
syscall
addi $sp, $sp, -44
li $a0, 4
li $a1, 1
sw $s6, 8($sp)
sw $s7, 4($sp)
sw $ra, 0($sp)
jal foo
lw $ra, 0($sp)
lw $s7, 4($sp)
lw $s6, 8($sp)
addi $sp, $sp, 44
addi $sp, $sp, -56
li $a0, 16
li $a1, 2
sw $s6, 8($sp)
sw $s7, 4($sp)
sw $ra, 0($sp)
jal foo
lw $ra, 0($sp)
lw $s7, 4($sp)
lw $s6, 8($sp)
addi $sp, $sp, 56
lw $t2, 20($zero)
lw $t3, 8($zero)
move $a0, $t3
li $v0, 1
syscall
li $a0, 32
li $v0, 11
syscall
move $a0, $t2
li $v0, 1
syscall
li $a0, 32
li $v0, 11
syscall
mul $t2, $s7, $s6
subu $t2, $0, $t2
addiu $t2, $t2, 2
addiu $v0, $t2, 0
jr $ra
main:
li $t0, 1
sw $t0, -24($sp)
li $t0, 2
sw $t0, -20($sp)
li $t0, 3
sw $t0, -16($sp)
li $t0, 4
sw $t0, -12($sp)
li $t0, 5
sw $t0, -8($sp)
li $t0, 6
sw $t0, -4($sp)
lw $t2, 20($zero)
sw $t2, -32($sp)
li $t0, 3
sw $t0, -28($sp)
li $v0, 5
syscall
sw $v0, -36($sp)
lw $t2, -4($sp)
lw $t3, -36($sp)
addiu $t3, $t3, 23
addiu $t2, $t2, -5
li $t0, 3
mul $t2, $t2, $t0
addiu $t2, $t2, 0
sll $t0, $t2, 2
sw $t3, 4($t0)
lw $t2, -36($sp)
addu $t3, $t2, $t2
li $t0, 21819
mul $t4, $t3, $t0
subu $t5, $0, $t2
addu $t5, $t5, $t2
addu $t4, $t5, $t4
addu $t4, $t3, $t4
subu $t5, $0, $t2
subu $t5, $t5, $t2
addu $t4, $t5, $t4
addiu $t5, $0, 0
addu $t4, $t5, $t4
subu $t5, $0, $t2
addu $t5, $t5, $t2
addu $t4, $t5, $t4
subu $t5, $0, $t2
subu $t5, $t5, $t2
addu $t4, $t5, $t4
addu $t4, $t3, $t4
subu $t5, $0, $t2
addu $t2, $t5, $t2
addu $t2, $t2, $t4
addu $t4, $t3, $t2
sw $t4, -12284($sp)
li $a0, 116
li $v0, 11
syscall
li $a0, 61
li $v0, 11
syscall
move $a0, $t4
li $v0, 1
syscall
li $a0, 32
li $v0, 11
syscall
lw $t2, -12284($sp)
addiu $t3, $t2, 1
addiu $t2, $t3, 1
addiu $t4, $t2, 1
addiu $t5, $t4, 1
addiu $t6, $t5, 1
addiu $t7, $t6, 1
addiu $t8, $t7, 1
addu $t9, $t8, $t7
subu $t9, $0, $t9
addiu $t9, $t9, 1
sw $t9, -68($sp)
addiu $s0, $t8, 0
addiu $s3, $t7, 0
sw $t6, -56($sp)
addiu $s4, $t5, 0
addiu $s7, $t4, 0
addiu $s5, $t2, 0
addiu $s6, $t3, 0
lw $t2, -28($sp)
addiu $t2, $t2, 1
addiu $s2, $t2, 0
li $v0, 5
syscall
sw $v0, 24($zero)
addi $t0, $sp, -24
addiu $t2, $t0, 0
addi $sp, $sp, -108
move $a0, $t2
sw $s0, 32($sp)
sw $s1, 28($sp)
sw $s2, 24($sp)
sw $s3, 20($sp)
sw $s4, 16($sp)
sw $s5, 12($sp)
sw $s6, 8($sp)
sw $s7, 4($sp)
sw $ra, 0($sp)
jal bar
lw $ra, 0($sp)
lw $s7, 4($sp)
lw $s6, 8($sp)
lw $s5, 12($sp)
lw $s4, 16($sp)
lw $s3, 20($sp)
lw $s2, 24($sp)
lw $s1, 28($sp)
lw $s0, 32($sp)
addi $sp, $sp, 108
addiu $t2, $v0, 0
lw $t3, 32($zero)
addu $t2, $t2, $t3
lw $t3, -32($sp)
addu $t2, $t2, $t3
li $a0, 113
li $v0, 11
syscall
li $a0, 119
li $v0, 11
syscall
li $a0, 101
li $v0, 11
syscall
li $a0, 32
li $v0, 11
syscall
move $a0, $t2
li $v0, 1
syscall
li $a0, 32
li $v0, 11
syscall
lw $t2, -12284($sp)
addu $t3, $t2, $t2
li $t0, 1342
mul $t4, $t3, $t0
subu $t5, $0, $t2
addu $t5, $t5, $t2
addu $t4, $t5, $t4
addu $t4, $t3, $t4
subu $t5, $0, $t2
subu $t5, $t5, $t2
addu $t4, $t5, $t4
addiu $t5, $0, 0
addu $t4, $t5, $t4
subu $t5, $0, $t2
addu $t5, $t5, $t2
addu $t4, $t5, $t4
subu $t5, $0, $t2
subu $t5, $t5, $t2
addu $t4, $t5, $t4
addu $t4, $t3, $t4
subu $t5, $0, $t2
addu $t2, $t5, $t2
addu $t2, $t2, $t4
addu $t2, $t3, $t2
addi $sp, $sp, -144
move $a0, $t2
sw $s0, 32($sp)
sw $s1, 28($sp)
sw $s2, 24($sp)
sw $s3, 20($sp)
sw $s4, 16($sp)
sw $s5, 12($sp)
sw $s6, 8($sp)
sw $s7, 4($sp)
sw $ra, 0($sp)
jal add1
lw $ra, 0($sp)
lw $s7, 4($sp)
lw $s6, 8($sp)
lw $s5, 12($sp)
lw $s4, 16($sp)
lw $s3, 20($sp)
lw $s2, 24($sp)
lw $s1, 28($sp)
lw $s0, 32($sp)
addi $sp, $sp, 144
lw $t2, -12284($sp)
lw $t3, -56($sp)
lw $t4, -68($sp)
addu $t4, $t4, $s2
mul $t3, $t3, $s3
div $t3, $s0
mflo $t3
div $t3, $t4
mfhi $t3
mul $t4, $s6, $s5
div $t4, $s7
mflo $t4
div $t4, $s4
mfhi $t4
addu $t2, $t2, $t4
subu $t2, $t2, $t3
lw $t3, 24($zero)
lw $t4, -68($sp)
addu $t2, $t2, $t3
addu $t2, $t2, $t4
move $a0, $t2
li $v0, 1
syscall
li $a0, 32
li $v0, 11
syscall
