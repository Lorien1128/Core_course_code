.data
nums: .space 10000
str_space: .asciiz " "
str_enter: .asciiz "\n"
stack: .space 100

.text
main:
la $sp stack
addi $sp, $sp, 100
addi $sp, $sp, -28
jal input
nop
move $s0, $v0
move $s1, $v1

move $a0, $s0
move $a1, $s1
jal findout
nop
addi $sp, $sp, 28
li $v0, 10
syscall


input: 
addi $sp, $sp, -36
li $v0, 5
syscall
move $s0, $v0 #s0 is hang
li $v0, 5
syscall
move $s1, $v0 #s1 is lie

li $t0, 0 #t0 is i
for_i:
  beq $t0, $s0, for_i_end
  nop
  li $t1, 0 #t1 is j
  for_j:
    beq $t1, $s1, for_j_end
    nop
    
    sw $ra, 16($sp)
    sw $s0, 20($sp)
    sw $s1, 24($sp)
    sw $t0, 28($sp)
    sw $t1, 32($sp)
    move $a0, $t0
    move $a1, $t1
    move $a2, $s1
    jal cal
    nop
    lw $t1, 32($sp)
    lw $t0, 28($sp)
    lw $s1, 24($sp)
    lw $s0, 20($sp)
    lw $ra, 16($sp)
    move $t2, $v0
    
    li $v0, 5
    syscall
    sw $v0, nums($t2)
    addi $t1, $t1, 1
    j for_j
    nop
  for_j_end:
  addi $t0, $t0, 1
  j for_i
  nop
for_i_end:
move $v0, $s0
move $v1, $s1 #v0 hang v1 lie
addi $sp, $sp, 36
jr $ra
nop
    

    
cal: #calculate (i*lie+j)*4 
     #a0 is i, a1 is j, a2 is lie
mult $a0, $a2
mflo $t0
add $t0, $t0, $a1
sll $t0, $t0, 2
move $v0, $t0
jr $ra #v0 is result
nop
 


findout: #a0 is hang, a1 is lie
addi $sp, $sp, -36
addi $t0, $a0, -1 #t0 is i, form hang-1 to 0
for_1:
  bltz $t0, for_1_out
  nop
  addi $t1, $a1, -1
  for_2:
    bltz $t1, for_2_out
    nop
    
    sw $a1, 4($sp)
    sw $ra, 16($sp)
    sw $t0, 28($sp)
    sw $t1, 32($sp)
    move $a0, $t0
    move $a1, $t1
    move $a2, $s1
    jal cal
    nop
    lw $t1, 32($sp)
    lw $t0, 28($sp)
    lw $ra, 16($sp)
    lw $a1, 4($sp)
    move $t2, $v0
    
    lw $t3, nums($t2)
    beqz $t3, out
    nop
    
    addi $a0, $t0, 1
    li $v0, 1
    syscall
    la $a0, str_space
    li $v0, 4
    syscall
    addi $a0, $t1, 1
    li $v0, 1
    syscall
    la $a0, str_space
    li $v0, 4
    syscall
    move $a0, $t3
    li $v0, 1
    syscall
    la $a0, str_enter
    li $v0, 4
    syscall
    out:
    addi $t1, $t1, -1
    j for_2
    nop
  for_2_out:
  
  addi $t0, $t0, -1
  j for_1
  nop
for_1_out:
addi $sp, $sp, 36
jr $ra
nop







   
     