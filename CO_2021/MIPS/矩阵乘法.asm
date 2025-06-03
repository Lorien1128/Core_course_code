.data
mata: .space 256
matb: .space 256
matc: .space 256
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
move $s0, $v0 #so is n

read_A:
li $t0, 0 #t0 is i
loop_i:
  bge $t0, $s0, loop_i_out
  li $t1, 0 #t1 is j
  loop_j:
    bge $t1, $s0, loop_j_out
    getindex($t2, $t0, $t1, $s0)
    li $v0, 5
    syscall
    sw $v0, mata($t2)
    addi $t1, $t1, 1
    j loop_j
  loop_j_out:
  addi $t0, $t0, 1
  j loop_i
loop_i_out:

read_B:
li $t0, 0 #t0 is i
loop_i_2:
  bge $t0, $s0, loop_i_out_2
  li $t1, 0 #t1 is j
  loop_j_2:
    bge $t1, $s0, loop_j_out_2
    getindex($t2, $t0, $t1, $s0)
    li $v0, 5
    syscall
    sw $v0, matb($t2)
    addi $t1, $t1, 1
    j loop_j_2
  loop_j_out_2:
  addi $t0, $t0, 1
  j loop_i_2
loop_i_out_2:
    
    
li $t0, 0 #t0 is i
loop_i_3:
  bge $t0, $s0, loop_i_out_3
  li $t1, 0 #t1 is j
  loop_j_3:
    bge $t1, $s0, loop_j_out_3
    li $t2, 0 #t2 is k
    li $t5, 0 #t5 is c[i][j]
    loop_k_3:
      bge $t2, $s0, loop_k_out_3
      getindex($t3, $t0, $t2, $s0) 
      lw $t3, mata($t3) #t3 is a[i][k]
      getindex($t4, $t2, $t1, $s0)
      lw $t4, matb($t4) #t4 is b[k][j]
      mult $t3, $t4
      mflo $t3 #t3 is Aik*Bkj
      add $t5, $t5, $t3
      addi $t2, $t2, 1
      j loop_k_3
    loop_k_out_3:
    getindex($t3, $t0, $t1, $s0)
    sw $t5, matc($t3)
    addi $t1, $t1, 1 
    j loop_j_3
  loop_j_out_3:
  addi $t0, $t0, 1
  j loop_i_3
loop_i_out_3:

print:
li $t0, 0 #t0 is i
loop_i_4:
  bge $t0, $s0, loop_i_out_4
  li $t1, 0 #t1 is j
  loop_j_4:
    bge $t1, $s0, loop_j_out_4
    getindex($t2, $t0, $t1, $s0)
    lw $a0, matc($t2)
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
    
    
    
    
    
    
    
    
    
    
    
    
   
  