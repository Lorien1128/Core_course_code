.data
nums: .space 400 #max 100 int
str_space: .asciiz " "
out: .asciiz "The numbers are:"

.text
li    $v0, 5
syscall
move  $s0, $v0 #s0 store the amount of nums
li    $t0, 0 #t0 is i

loop_read:
beq   $t0, $s0, loop_read_out #break if i equals n
nop
li    $v0, 5
syscall
sll   $t1, $t0, 2 #t1=4*i
sw    $v0, nums($t1) #store a number
addi  $t0, $t0, 1
j loop_read
nop
loop_read_out:

li    $t0, 0 #t0 is i
subi  $s1, $s0, 1 #s1 is n-1
for_i:
beq   $t0, $s1, for_i_out
nop
sll   $t1, $t0, 2 #t1 is i*4
lw    $t2, nums($t1) #t2 is the min num form i to n
move  $t3, $t0 #t3 is j
for_j:
beq   $t3, $s0, for_j_out
nop
sll   $t1, $t3, 2 #t1 is j*4
lw    $t4, nums($t1)
bgt   $t2, $t4, setmin
nop
j setmin_end
nop
setmin: #set jnum as max
move  $t2, $t4
move  $t5, $t3 #set t5 as where min
setmin_end:
addi  $t3, $t3, 1
j for_j
nop
for_j_out:
swap:
sll   $t1, $t0, 2
lw    $t6, nums($t1) #t6 is nums[i]
sw    $t2, nums($t1) #put min to nums[i]
sll   $t1, $t5, 2
sw    $t6, nums($t1) #put nums[i] to nums[wheremin]
swap_end:
addi  $t0, $t0, 1
j for_i
nop
for_i_out:

li    $t0, 0 #t0 is i
la    $a0, out
li    $v0, 4
syscall
print:
beq   $t0, $s0, print_end
nop
sll   $t1, $t0, 2
lw    $a0, nums($t1)
li    $v0, 1
syscall #print nums[i]
la    $a0, str_space
li    $v0, 4
syscall #print " "
addi  $t0, $t0, 1
j print
nop
print_end:

li    $v0, 10
syscall


