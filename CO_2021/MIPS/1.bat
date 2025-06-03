@echo off
set/p a=
java -jar Mars4_5.jar me a db mc CompactDataAtZero dump 0x00003000-0x00013000 .text HexText %a%.txt %a%.asm 2>nul
type %a%.txt
pause