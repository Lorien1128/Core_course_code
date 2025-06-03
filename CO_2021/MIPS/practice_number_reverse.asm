.data
nums: .space 40

.text
li $v0, 5
syscall
move $s0, $v0
li $t0, 0
for_getnum:
  li $t1, 10
  div $s0, $t1
  mflo $s0
  mfhi $t1
  sw $t1, nums($t0)
  addi $t0, $t0, 4
  beqz $s0, for_getnum_out
  nop
  j for_getnum
  nop
for_getnum_out:

move $s1, $t0

li $t0, 0
li $s0, 0
for_reverse:
  beq $t0, $s1, for_reverse_out
  nop
  lw $t1, nums($t0)
  li $t2, 10
  mult $s0, $t2
  mflo $s0
  add $s0, $s0, $t1
  addi $t0, $t0, 4
  j for_reverse
  nop
 for_reverse_out:
 
 move $a0, $s0
 li $v0, 1
 syscall
  
  
  
  


