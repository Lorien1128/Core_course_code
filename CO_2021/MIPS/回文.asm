.data
str: .space 80

.text
li $v0, 5
syscall
move $s0, $v0 #s0 is n
addi $t0, $s0, 1
li $t1, 2
div $t0, $t1
mflo $s1 #s1 is [(n+1)/2]

li $t0, 0 #t0 is i
read:
  bge $t0, $s0, read_out
  sll $t1, $t0, 2
  li $v0, 12
  syscall
  sw $v0, str($t1)
  addi $t0, $t0, 1
  j read
read_out:

li $t0, 0
li $s2, 1 #huiwne-->s2==1
loop:
  bge $t0, $s1, loop_out
  sub $t1, $s0, $t0
  subi $t1, $t1, 1 #t1 is n-1-i
  sll $t2, $t0, 2
  lw $t3, str($t2)
  sll $t2, $t1, 2
  lw $t4, str($t2)
  bne $t3, $t4, if
  j if_end
  if:
    li $s2, 0
  if_end:
  addi $t0, $t0, 1
  j loop
loop_out:

move $a0, $s2
li $v0 ,1
syscall
li $v0, 10
syscall


  
    
    
        
                