.data
matf: .space 484
math: .space 484
matg: .space 484
space: .asciiz " "
enter: .asciiz "\n"

.macro getindex(%index, %i, %j, %n)
   mult %i, %n
   mflo %index
   add %index, %index, %j
   sll %index, %index, 2
.end_macro

.text
li $v0, 5
syscall
move $s0, $v0 #s0 is m1
li $v0, 5
syscall
move $s1, $v0 #s1 is n1
li $v0, 5
syscall
move $s2, $v0 #s2 is m2
li $v0, 5
syscall
move $s3, $v0 #s3 is n2

read_f:
li $t0, 0 #t0 is i
loop_i:
  bge $t0, $s0, loop_i_out
  li $t1, 0 #t1 is j
  loop_j:
    bge $t1, $s1, loop_j_out
    getindex($t2, $t0, $t1, $s1)
    li $v0, 5
    syscall
    sw $v0, matf($t2)
    addi $t1, $t1, 1
    j loop_j
  loop_j_out:
  addi $t0, $t0, 1
  j loop_i
loop_i_out:

read_h:
li $t0, 0 #t0 is i
loop_i_2:
  bge $t0, $s2, loop_i_out_2
  li $t1, 0 #t1 is j
  loop_j_2:
    bge $t1, $s3, loop_j_out_2
    getindex($t2, $t0, $t1, $s3)
    li $v0, 5
    syscall
    sw $v0, math($t2)
    addi $t1, $t1, 1
    j loop_j_2
  loop_j_out_2:
  addi $t0, $t0, 1
  j loop_i_2
loop_i_out_2:

li $t0, 0 #t0 is i
loop_i_3:
  sub $t1, $s0, $s2
  bgt $t0, $t1, loop_i_out_3
  li $t1, 0 #t1 is j
  loop_j_3:
    sub $t2, $s1, $s3
    bgt $t1, $t2, loop_j_out_3
    li $s4, 0 #s4 is g[i][j]
    li $t2, 0 #t2 is k
    loop_k_3:
      bge $t2, $s2, loop_k_out_3
      li $t3, 0 #t3 is l
      loop_l_3:
        bge $t3, $s3, loop_l_out_3
        add $t4, $t0, $t2
        add $t5, $t1, $t3
        getindex($t6, $t4, $t5, $s1)
        lw $t4, matf($t6) #t4 is f[i+k][j+l]
        getindex($t6, $t2, $t3, $s3)
        lw $t5, math($t6) #t5 is h[k][l]
        mult $t4, $t5
        mflo $t4
        add $s4, $s4, $t4
        addi $t3, $t3, 1
        j loop_l_3
      loop_l_out_3:
      addi $t2, $t2, 1
      j loop_k_3
    loop_k_out_3:
    sub $t4, $s1, $s3
    addi $t4, $t4, 1
    getindex($t5, $t0, $t1, $t4)
    sw $s4, matg($t5)
    addi $t1, $t1, 1 
    j loop_j_3
  loop_j_out_3:
  addi $t0, $t0, 1
  j loop_i_3
loop_i_out_3:

print:
li $t0, 0 #t0 is i
loop_i_4:
  sub $t2, $s0, $s2
  bgt $t0, $t2, loop_i_out_4
  li $t1, 0 #t1 is j
  loop_j_4:
    sub $t2, $s1, $s3
    bgt $t1, $t2, loop_j_out_4
    addi $t2, $t2, 1
    getindex($t3, $t0, $t1, $t2)
    lw $a0, matg($t3)
    li $v0, 1
    syscall
    la $a0, space
    li $v0, 4
    syscall
    addi $t1, $t1, 1
    j loop_j_4
  loop_j_out_4:
  la $a0, enter
  li $v0, 4
  syscall
  addi $t0, $t0, 1
  j loop_i_4
loop_i_out_4:

li $v0, 10
syscall  
















