
# PlanAhead Launch Script for Pre-Synthesis Floorplanning, created by Project Navigator

create_project -name test -dir "D:/ISE/project/test/planAhead_run_1" -part xc7a100tcsg324-3
set_param project.pinAheadLayout yes
set srcset [get_property srcset [current_run -impl]]
set_property target_constrs_file "learn.ucf" [current_fileset -constrset]
set hdlfile [add_files [list {learn.v}]]
set_property file_type Verilog $hdlfile
set_property library work $hdlfile
set_property top learn $srcset
add_files [list {learn.ucf}] -fileset [get_property constrset [current_run]]
open_rtl_design -part xc7a100tcsg324-3
