.data
num: .space 1000

.text
li $v0, 5
syscall
move $s0, $v0 #s0 is n

li $t0, 1
li $t1, 0
sb $t0, num($t1)

li $s2, 1 #s2 is bit
li $t0, 1
loop_i:
  bgt $t0, $s0, loop_i_out
  li $t1, 1
  move $t2, $t0 #t2 is m
  loop_j_1:
    li $t3, 10
    div $t2, $t3
    mflo $t2
    beqz $t2, if_1
    j if_1_end
    if_1:
      move $s1, $t1 #s1 is bit[i]
      j loop_j_1_out
    if_1_end:
    addi $t1, $t1, 1
    j loop_j_1
  loop_j_1_out:
  li $t2, 0 #t2 is c
  li $t1, 0 #t1 is j
  loop_j_2:
    add $t3, $s1, $s2
    bge $t1, $t3, loop_j_2_out
    lb $t3, num($t1)
    mult $t3, $t0
    mflo $t3
    add $t3, $t3, $t2
    li $t4, 10
    div $t3, $t4
    mfhi $t3
    sb $t3, num($t1)
    mflo $t2
    addi $t1, $t1, 1
    j loop_j_2
  loop_j_2_out:
  add $t1, $s1, $s2
  loop_j_3:
    lb $t2, num($t1)
    bnez $t2, if_2
    j if_2_end
    if_2:
      addi $s2, $t1, 1
      j loop_j_3_out
    if_2_end:
    addi $t1, $t1, -1
    j loop_j_3
  loop_j_3_out:
  addi $t0, $t0, 1
  j loop_i
loop_i_out:

print:
addi $t0, $s2, -1
loop_print:
  bltz $t0, loop_print_out
  lb $a0, num($t0)
  li $v0, 1
  syscall
  addi $t0, $t0, -1
  j loop_print
loop_print_out:

li $v0, 10
syscall













