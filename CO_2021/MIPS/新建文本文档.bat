@echo off
set/p a=
java -jar mars.jar me a dump .text HexText %a%.txt %a%.asm 2>nul
type %a%.txt
pause