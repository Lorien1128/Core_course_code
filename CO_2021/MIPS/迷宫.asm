.data 
way: .space 400

.macro getindex(%ans, %i, %j, %n)
  mult %n, %i
  mflo %ans
  add %ans, %ans, %i
  add %ans, %ans, %i
  add %ans, %ans, %j
  sll %ans, %ans, 2
.end_macro

.text

li $v0, 5
syscall
move $s0, $v0 #s0 is m
li $v0, 5
syscall
move $s1, $v0 #s1 is n

initial:
li $t0, 0 #t0 is i
loop_read_i:
  addi $t1, $s0, 2
  bge $t0, $t1, loop_read_i_out
  li $t1, 0 #t1 is j
  loop_read_j:
    addi $t2, $s1, 2
    bge $t1, $t2, loop_read_j_out
    beq $t0, $zero, if_1
    addi $t2, $s0, 1
    beq $t0, $t2, if_1
    beq $t1, $zero, if_1
    addi $t2, $s1, 1
    beq $t1, $t2, if_1
    j else_1
    if_1:
      getindex($t2, $t0, $t1, $s1)
      li $t3, 1
      sw $t3, way($t2)
      j if_1_end
    else_1:
      li $v0, 5
      syscall
      getindex($t2, $t0, $t1, $s1)
      sw $v0, way($t2)
    if_1_end:
    addi $t1, $t1, 1
    j loop_read_j
  loop_read_j_out:
  addi $t0, $t0, 1
  j loop_read_i
loop_read_i_out:

main:
li $v0, 5
syscall
move $a0, $v0 #a0 is m1
li $v0, 5
syscall
move $a1, $v0 #a1 is n1
li $v0, 5
syscall
move $s2, $v0 #s2 is m2
li $v0, 5
syscall
move $s3, $v0 #s3 is n2
li $s4, 0 #s4 is count

jal step
move $a0, $s4
li $v0, 1
syscall
li $v0, 10
syscall



step: #a0 is nowm, a1 is nown
addi $sp, $sp, -12
move $t0, $a0 #t0 is nowm
move $t1, $a1 #t1 is nown
beq $t0, $s2 if_2
j else_2
if_2:
  beq $t1, $s3, if_3
  j else_2
  if_3:
    addi $s4, $s4, 1
    addi $sp, $sp, 12
    jr $ra
else_2:
  getindex($t2, $t0, $t1, $s1)
  li $t3, 1
  sw $t3, way($t2)
  addi $t2, $t1, 1
  getindex($t3, $t0, $t2, $s1)
  lw $t4, way($t3)
  beqz $t4, if_east
  j if_east_end
  if_east:
    sw $ra, 0($sp)
    sw $t0, 4($sp)
    sw $t1, 8($sp)
    addi $a0, $t0, 0
    addi $a1, $t1, 1
    jal step
    lw $t1, 8($sp)
    lw $t0, 4($sp)
    lw $ra, 0($sp)
  if_east_end:
  addi $t2, $t0, 1
  getindex($t3, $t2, $t1, $s1)
  lw $t4, way($t3)
  beqz $t4, if_south
  j if_south_end
  if_south:
    sw $ra, 0($sp)
    sw $t0, 4($sp)
    sw $t1, 8($sp)
    addi $a0, $t0, 1
    addi $a1, $t1, 0
    jal step
    lw $t1, 8($sp)
    lw $t0, 4($sp)
    lw $ra, 0($sp)
  if_south_end:
  addi $t2, $t1, -1
  getindex($t3, $t0, $t2, $s1)
  lw $t4, way($t3)
  beqz $t4, if_west
  j if_west_end
  if_west:
    sw $ra, 0($sp)
    sw $t0, 4($sp)
    sw $t1, 8($sp)
    addi $a0, $t0, 0
    addi $a1, $t1, -1
    jal step
    lw $t1, 8($sp)
    lw $t0, 4($sp)
    lw $ra, 0($sp)
  if_west_end:
  addi $t2, $t0, -1
  getindex($t3, $t2, $t1, $s1)
  lw $t4, way($t3)
  beqz $t4, if_north
  j if_north_end
  if_north:
    sw $ra, 0($sp)
    sw $t0, 4($sp)
    sw $t1, 8($sp)
    addi $a0, $t0, -1
    addi $a1, $t1, 0
    jal step
    lw $t1, 8($sp)
    lw $t0, 4($sp)
    lw $ra, 0($sp)
  if_north_end:
  getindex($t2, $t0, $t1, $s1)
  sw $zero, way($t2)
  addi $sp, $sp, 12
  jr $ra
    
    


















