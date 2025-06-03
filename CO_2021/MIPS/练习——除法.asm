.data
num: .space 132
dot: .asciiz "."

.text
li $v0, 5
syscall
move $s0, $v0 #s0 is m
li $v0, 5
syscall
move $s1, $v0 #s1 is n
div $s0, $s1
mflo $t0
mfhi $t1 #t1 is r
sw $t0, num($zero)

li $t0, 1 #t0 is i
loop_cal:
  li $t2, 32
  bgt $t0, $t2, loop_cal_out
  li $t2, 10
  mult $t1, $t2
  mflo $t1
  div $t1, $s1
  mfhi $t1
  mflo $t2 #t2 is r//n
  sll $t3, $t0, 2
  sw $t2, num($t3)
  addi $t0, $t0, 1
  j loop_cal
loop_cal_out:

li $t2, 10
mult $t1, $t2
mflo $t1
div $t1, $s1
mflo $t2
li $t3, 5
bge $t2, $t3, if
j if_end
if:
  li $t2, 128
  lw $t3, num($t2)
  addi $t3, $t3, 1
  sw $t3, num($t2)
if_end:

lw $a0, num($zero)
li $v0, 1
syscall
la $a0, dot
li $v0, 4
syscall
li $t0, 1
loop_print:
  li $t1, 32
  bgt $t0, $t1, loop_print_out
  sll $t1, $t0, 2
  lw $a0, num($t1)
  li $v0, 1
  syscall
  addi $t0, $t0, 1
  j loop_print
loop_print_out:

li $v0, 10
syscall

  
  
  
  
  
  
  
  
  