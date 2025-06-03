.text
li $v0, 5
syscall
move $s0, $v0 #s0 is m
li $v0, 5
syscall
move $s1, $v0 #s1 is n
move $t0, $s0 #t0 is a
move $t1, $s1 #t1 is b
loop:
  beqz $t1, loop_out
  div $t0, $t1
  mfhi $t2 #t2 is r
  move $t0, $t1
  move $t1, $t2
  j loop
loop_out:
move $a0, $t0
li $v0, 1
syscall
li $v0, 10
syscall