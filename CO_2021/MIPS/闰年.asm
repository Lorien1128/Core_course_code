.data

.text
li $v0, 5
syscall
move $s0, $v0 #s0 is the year
li $t0, 4
div $s0, $t0
mfhi $t1 #t1 is the remain number

beqz $t1, if_1
nop
li $s1, 0 #s1 is ping or run
j if_end
nop
if_1:
  li $t0, 100
  div $s0, $t0
  mfhi $t1
  beqz $t1, if_2
  nop
  li $s1, 1
  j if_end
  nop
  if_2:
    li $t0, 400
    div $s0, $t0
    mfhi $t1
    beqz $t1, if_3
    nop
    li $s1, 0
    j if_end
    nop
    if_3:
      li $s1, 1
      j if_end
      nop
if_end:

move $a0, $s1
li $v0, 1
syscall
li $v0, 10
syscall  
   
    
 
