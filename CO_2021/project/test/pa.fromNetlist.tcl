
# PlanAhead Launch Script for Post-Synthesis pin planning, created by Project Navigator

create_project -name test -dir "D:/ISE/project/test/planAhead_run_4" -part xc7a100tcsg324-3
set_property design_mode GateLvl [get_property srcset [current_run -impl]]
set_property edif_top_file "D:/ISE/project/test/learn.ngc" [ get_property srcset [ current_run ] ]
add_files -norecurse { {D:/ISE/project/test} }
set_param project.pinAheadLayout  yes
set_property target_constrs_file "learn.ucf" [current_fileset -constrset]
add_files [list {learn.ucf}] -fileset [get_property constrset [current_run]]
link_design
