.data
line: .space 800
use: .space 32
stack: .space 200

.text
main:
jal input
nop
move $a0, $v0 #dot
move $a1, $v1 #line
li $a2, 1 #n
jal findroad
nop
move $a0, $v0

li $v0, 1
syscall
li $v0, 10
syscall



input:
li $v0, 5
syscall
move $s0, $v0 #s0 is num of dot
li $v0, 5
syscall
move $s1, $v0 #s1 is num of line
li $t0, 0 #t0 is i
for_read:
  beq $t0, $s1, for_read_out
  nop
  sll $t1, $t0, 3
  li $v0, 5
  syscall
  sw $v0, line($t1)
  addi $t1, $t1, 4
  li $v0, 5
  syscall
  sw $v0, line($t1)
  addi $t0, $t0, 1
  j for_read
  nop
for_read_out:
move $v0, $s0
move $v1, $s1 #v0-dot, v1-line
jr $ra
nop


road: #if road exists form m to n
      #in:a0-line, a1-m, a2-n
move $s0, $a0
move $s1, $a1
move $s2, $a2
li $t0, 0
for_froad:
  beq $t0, $s0, for_froad_out
  nop
  sll $t1, $t0, 3
  lw $t2, line($t1)
  beq $t2, $s1, if_mton
  nop
  beq $t2, $s2, if_ntom
  nop
  j if_toend
  if_mton:
    addi $t1, $t1, 4
    lw $t2, line($t1)
    beq $t2, $s2, set_1
    nop
    j if_toend
    nop
  if_ntom:
    addi $t1, $t1, 4
    lw $t2, line($t1)
    beq $t2, $s1, set_1
    nop
    j if_toend
    nop
  if_toend:
  addi $t0, $t0, 1
  j for_froad
  nop
for_froad_out:

set_0:
li $v0, 0
jr $ra
nop

set_1:
li $v0, 1
jr $ra
nop
####v0=1,find road;v0=0, not find 

  
    

findroad: #in:a0-dot,a1-line,a2-n
          #out:v0
          #find a road from n to 1
addi $sp, $sp, -24
move $s0, $a0 #dot
move $s1, $a1 #line
move $t0, $a2 #n
li $t1, 1
beq $t0, $t1, if_1
nop
j else_1
nop
if_1:
  li $t1, 4
  lw $t2, use($t1)
  li $t1, 1
  beq $t2, $t1, pd
  nop
  j else_1
  nop
else_1:
  li $t1,1 #t1 is i
  for_1:
    bgt $t1, $s0, for_1_out
    nop
    sll $t2, $t1, 2
    lw $t3, use($t2) #t3 is use[i]
    beqz $t3, if_2
    nop
    j if_2_end
    nop
    if_2:
       sw $ra, 0($sp)
       sw $s0, 4($sp)
       sw $s1, 8($sp)
       sw $t0, 12($sp)
       sw $t1, 16($sp)
       sw $v0, 20($sp)
       move $a0, $s1
       move $a1, $t0
       move $a2, $t1
       jal road
       nop
       move $t3, $v0
       lw $ra, 0($sp)
       lw $s0, 4($sp)
       lw $s1, 8($sp)
       lw $t0, 12($sp)
       lw $t1, 16($sp) 
       lw $v0, 20($sp)        
       li $t2, 1
       beq $t3, $t2, work
       nop
       j if_2_end
       work:
         sll $t2, $t1, 2
         li $t3, 1
         sw $t3, use($t2)
         sw $ra, 0($sp)
         sw $s0, 4($sp)
         sw $s1, 8($sp)
         sw $t0, 12($sp)
         sw $t1, 16($sp)
         move $a0, $s0
         move $a1, $s1
         move $a2, $t1
         jal findroad
         nop
         lw $ra, 0($sp)
         lw $s0, 4($sp)
         lw $s1, 8($sp)
         lw $t0, 12($sp)
         lw $t1, 16($sp)   
         li $t2, 1
         beq $v0, $t2, if_3
         nop
         j if_2_end
         nop
         if_3:
           addi $sp, $sp, 24
           jr $ra
           nop
     if_2_end:
     li $v0, 0
     addi $t1, $t1, 1
     j for_1
     nop
   for_1_out:
   sll $t2, $t0, 2
   sw $0, use($t2)
   addi $sp, $sp, 24
   jr $ra
   nop
   
pd:
li $t1, 1 #t1 is i
li $t4, 0
for_2:
  bgt $t1, $s0, for_2_out
  nop
  sll $t2, $t1, 2
  lw $t3, use($t2)
  li $t2, 1
  beq $t3, $t2, count
  nop
  j count_end
  nop
  count:
    addi $t4, $t4, 1 #t4 counts how many is used  
  count_end:
  addi $t1, $t1, 1
  j for_2
  nop
for_2_out:
  beq $t4, $s0, set_find
  nop
  set_find_else:
    sll $t2, $t0, 2
    sw $0, use($t2)
    li $v0, 0
    addi $sp, $sp, 24
    jr $ra
    nop
  set_find:
    li $v0, 1
    addi $sp, $sp, 24
    jr $ra
    nop

       
                        
  
  
  