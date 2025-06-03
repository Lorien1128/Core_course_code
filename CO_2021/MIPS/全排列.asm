.data
use: .space 28
num: .space 28
space: .asciiz " "
enter: .asciiz "\n"

.text
li $v0, 5
syscall
move $a0, $v0
move $a1, $v0
jal full
li $v0, 10
syscall



full: #a0 is n, a1 is remain
  addi $sp, $sp, -16
  move $s0, $a0
  move $s1, $a1
  beq $s1, $zero, if1
  j else1
  if1:
     li $t0, 0
     loop_print:
       bge $t0, $s0, loop_print_out
       sll $t1, $t0, 2
       lw $t2, num($t1)
       move $a0, $t2
       li $v0, 1
       syscall
       la $a0, space
       li $v0,4
       syscall
       addi $t0, $t0, 1
       j loop_print
     loop_print_out:
     la $a0, enter
     li $v0, 4
     syscall
     addi $sp, $sp, 16
     jr $ra
  else1:
     li $t0, 1 #t0 is i
     loop:
        bgt $t0, $s0, loop_out
        sll $t1, $t0, 2
        lw $t2, use($t1) #t2 is use[i]
        beqz $t2, if2
        j if2_end
        if2:
           sub $t2, $s0, $s1
           sll $t2, $t2, 2
           sw $t0, num($t2)
           sll $t1, $t0, 2
           li $t2, 1
           sw $t2, use($t1)
           
           sw $s0, 0($sp)
           sw $s1, 4($sp)
           sw $t0, 8($sp)
           sw $ra, 12($sp)
           move $a0, $s0
           addi $t1, $s1, -1
           move $a1, $t1
           jal full
           lw $ra, 12($sp)
           lw $t0, 8($sp)
           lw $s1, 4($sp)
           lw $s0, 0($sp)
           
           sll $t1, $t0, 2
           sw $zero, use($t1)
        if2_end:
        addi $t0, $t0, 1
        j loop
     loop_out:
  addi $sp, $sp, 16
  jr $ra



    
        
            
                
                    
                        
                            
                                
                                    
                                            