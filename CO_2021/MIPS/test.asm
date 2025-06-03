.data
aa: .space 4000
bb: .space 4000
space: .asciiz " "
enter: .asciiz "\n"

.text
li $v0, 5
syscall
move $s0, $v0 #s0 is n

read:
li $t0, 0
loop_read:
  bge $t0, $s0, loop_read_out
  sll $t1, $t0, 2
  li $v0, 5
  syscall
  sw $v0, aa($t1)
  li $v0, 5
  syscall
  sw $v0, bb($t1) 
  addi $t0, $t0, 1 
  j loop_read
loop_read_out:

sort:
li $t0, 0
loop_i:
  addi $t1, $s0, -1
  bge $t0, $t1, loop_i_out
  li $t1, 0
  loop_j:
    addi $t2, $s0, -1
    sub $t2, $t2, $t0
    bge $t1, $t2, loop_j_out
    sll $t2, $t1, 2
    lw $t3, aa($t2) #t3 is a[j]
    addi $t2, $t2, 4
    lw $t4, aa($t2) #t4 is a[j+1]
    blt $t3, $t4, if_2
    beq $t3, $t4, if_1
    j if_end
    if_1: #if b[j]<b[j+1]
      sll $t2, $t1, 2
      lw $t3, bb($t2) #t3 is b[j]
      addi $t2, $t2, 4
      lw $t4, bb($t2) #t4 is b[j+1] 
      blt $t3, $t4, if_2
      j if_end
    if_2:
      # swap a[j] and a[j+1]
      sll $t2, $t1, 2
      lw $t3, aa($t2)
      addi $t2, $t2, 4
      lw $t4, aa($t2)
      sw $t3, aa($t2)
      addi $t2, $t2, -4
      sw $t4, aa($t2)
      # swap b[j] and b{j+1]
      sll $t2, $t1, 2
      lw $t3, bb($t2)
      addi $t2, $t2, 4
      lw $t4, bb($t2)
      sw $t3, bb($t2)
      addi $t2, $t2, -4
      sw $t4, bb($t2)
    if_end:
    addi $t1, $t1, 1
    j loop_j
  loop_j_out:
  addi $t0, $t0, 1
  j loop_i
loop_i_out:

print:
li $t0, 0
loop_print:
  bge $t0, $s0, loop_print_out
  sll $t1, $t0, 2
  # print a[i]
  lw $a0, aa($t1)
  li $v0, 1
  syscall
  la $a0, space
  li $v0, 4
  syscall
  #print b[i]
  lw $a0, bb($t1)
  li $v0, 1
  syscall
  la $a0, enter
  li $v0, 4
  syscall
  addi $t0, $t0, 1
  j loop_print
loop_print_out:

li $v0, 10
syscall
  
  
  
  
  
  
  
  
  