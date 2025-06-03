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
lable:
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
  
for_read_out:
move $v0, $s0
move $v1, $s1 #v0-dot, v1-line
jr $ra



road: #if road exists form m to n
      #in:a0-line, a1-m, a2-n
move $s2, $a0
move $s3, $a1
move $s4, $a2
li $t0, 0
for_froad:
  beq $t0, $s2, for_froad_out
  
  sll $t1, $t0, 3
  lw $t2, line($t1)
  
  bne $t2, $s3, if_ntom
  addi $t1, $t1, 4
  lw $t2, line($t1)
  beq $t2, $s4, set_1
  if_ntom:
  bne $t2, $s4, if_toend
  addi $t1, $t1, 4
  lw $t2, line($t1)
  beq $t2, $s3, set_1
    
    
  if_toend:
  addi $t0, $t0, 1
  j for_froad
  
for_froad_out:

set_0:
li $v0, 0
jr $ra


set_1:
li $v0, 1
jr $ra

####v0=1,find road;v0=0, not find 

  
    

findroad: #in:a0-dot,a1-line,a2-n
          #out:v0
          #find a road from n to 1
addi $sp, $sp, -24
move $s0, $a0 #dot
move $s1, $a1 #line
move $t0, $a2 #n

  li $t1,1 #t1 is i
  for_1:
    bgt $t1, $s0, for_1_out
    
    sll $t2, $t1, 2
    lw $t3, use($t2) #t3 is use[i]
    beqz $t3, if_2
    
    j if_2_end
    
    if_2:
       sw $ra, 0($sp)
      
       sw $t0, 12($sp)
       sw $t1, 16($sp)
       sw $v0, 20($sp)
       move $a0, $s1
       move $a1, $t0
       move $a2, $t1
       jal road
       
       move $t3, $v0
       lw $ra, 0($sp)
     
       lw $t0, 12($sp)
       lw $t1, 16($sp) 
       lw $v0, 20($sp)        
       li $t2, 1
       beq $t3, $t2, work
       
       j if_2_end
       work:
         sll $t2, $t1, 2
         li $t3, 1
         sw $t3, use($t2)
         beq $t1, $t3, pd
         
         sw $ra, 0($sp)
         
         sw $t0, 12($sp)
         sw $t1, 16($sp)
         move $a0, $s0
         move $a1, $s1
         move $a2, $t1
         jal findroad
         
         lw $ra, 0($sp)

         lw $t0, 12($sp)
         lw $t1, 16($sp)   
         li $t2, 1
        
     if_2_end:
     li $v0, 0
     addi $t1, $t1, 1
     j for_1
     
   for_1_out:
   sll $t2, $t0, 2
   sw $0, use($t2)
   addi $sp, $sp, 24
   jr $ra
   
   
pd:
li $t5, 1 #t5 is i
li $t4, 0
for_2:
  bgt $t5, $s0, for_2_out
  
  sll $t2, $t5, 2
  lw $t3, use($t2)
  li $t2, 1
  beq $t3, $t2, count
  
  j count_end
  
  count:
    addi $t4, $t4, 1 #t4 counts how many is used  
  count_end:
  addi $t5, $t5, 1
  j for_2
  
for_2_out:
  beq $t4, $s0, set_find
  
  set_find_else:
    li $t2, 4
    sw $0, use($t2)
    li $v0, 0
    j if_2_end
    
  set_find:
    li $v0, 1
    addi $sp, $sp, 24
    j lable
    

       
                        
  
  
  
