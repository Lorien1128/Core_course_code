/**********************************************************************/
/*   ____  ____                                                       */
/*  /   /\/   /                                                       */
/* /___/  \  /                                                        */
/* \   \   \/                                                       */
/*  \   \        Copyright (c) 2003-2009 Xilinx, Inc.                */
/*  /   /          All Right Reserved.                                 */
/* /---/   /\                                                         */
/* \   \  /  \                                                      */
/*  \___\/\___\                                                    */
/***********************************************************************/

/* This file is designed for use with ISim build 0x7708f090 */

#define XSI_HIDE_SYMBOL_SPEC true
#include "xsi.h"
#include <memory.h>
#ifdef __GNUC__
#include <stdlib.h>
#else
#include <malloc.h>
#define alloca _alloca
#endif
static const char *ng0 = "D:/ISE/pip_CPU_inst50_p6/DMW.v";
static unsigned int ng1[] = {1U, 0U};
static unsigned int ng2[] = {0U, 0U};
static unsigned int ng3[] = {2U, 0U};
static unsigned int ng4[] = {4U, 0U};
static unsigned int ng5[] = {3U, 0U};
static unsigned int ng6[] = {8U, 0U};
static int ng7[] = {0, 0};
static unsigned int ng8[] = {1U, 1U};
static unsigned int ng9[] = {3U, 1U};
static unsigned int ng10[] = {12U, 0U};
static unsigned int ng11[] = {3U, 3U};
static unsigned int ng12[] = {15U, 0U};



static void Cont_32_0(char *t0)
{
    char t3[8];
    char *t1;
    char *t2;
    char *t4;
    char *t5;
    unsigned int t6;
    unsigned int t7;
    unsigned int t8;
    unsigned int t9;
    unsigned int t10;
    unsigned int t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;
    unsigned int t17;
    unsigned int t18;
    char *t19;
    unsigned int t20;
    unsigned int t21;
    char *t22;
    unsigned int t23;
    unsigned int t24;
    char *t25;

LAB0:    t1 = (t0 + 2688U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(32, ng0);
    t2 = (t0 + 1208U);
    t4 = *((char **)t2);
    memset(t3, 0, 8);
    t2 = (t3 + 4);
    t5 = (t4 + 4);
    t6 = *((unsigned int *)t4);
    t7 = (t6 >> 0);
    *((unsigned int *)t3) = t7;
    t8 = *((unsigned int *)t5);
    t9 = (t8 >> 0);
    *((unsigned int *)t2) = t9;
    t10 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t10 & 3U);
    t11 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t11 & 3U);
    t12 = (t0 + 3352);
    t13 = (t12 + 56U);
    t14 = *((char **)t13);
    t15 = (t14 + 56U);
    t16 = *((char **)t15);
    memset(t16, 0, 8);
    t17 = 3U;
    t18 = t17;
    t19 = (t3 + 4);
    t20 = *((unsigned int *)t3);
    t17 = (t17 & t20);
    t21 = *((unsigned int *)t19);
    t18 = (t18 & t21);
    t22 = (t16 + 4);
    t23 = *((unsigned int *)t16);
    *((unsigned int *)t16) = (t23 | t17);
    t24 = *((unsigned int *)t22);
    *((unsigned int *)t22) = (t24 | t18);
    xsi_driver_vfirst_trans(t12, 0, 1);
    t25 = (t0 + 3256);
    *((int *)t25) = 1;

LAB1:    return;
}

static void Always_34_1(char *t0)
{
    char t6[8];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t7;
    char *t8;
    unsigned int t9;
    unsigned int t10;
    unsigned int t11;
    unsigned int t12;
    unsigned int t13;
    unsigned int t14;
    unsigned int t15;
    unsigned int t16;
    unsigned int t17;
    unsigned int t18;
    unsigned int t19;
    unsigned int t20;
    char *t21;
    char *t22;
    unsigned int t23;
    unsigned int t24;
    unsigned int t25;
    unsigned int t26;
    unsigned int t27;
    char *t28;
    char *t29;
    int t30;
    char *t31;
    char *t32;

LAB0:    t1 = (t0 + 2936U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(34, ng0);
    t2 = (t0 + 3272);
    *((int *)t2) = 1;
    t3 = (t0 + 2968);
    *((char **)t3) = t2;
    *((char **)t1) = &&LAB4;

LAB1:    return;
LAB4:    xsi_set_current_line(34, ng0);

LAB5:    xsi_set_current_line(35, ng0);
    t4 = (t0 + 1048U);
    t5 = *((char **)t4);
    t4 = ((char*)((ng1)));
    memset(t6, 0, 8);
    t7 = (t5 + 4);
    t8 = (t4 + 4);
    t9 = *((unsigned int *)t5);
    t10 = *((unsigned int *)t4);
    t11 = (t9 ^ t10);
    t12 = *((unsigned int *)t7);
    t13 = *((unsigned int *)t8);
    t14 = (t12 ^ t13);
    t15 = (t11 | t14);
    t16 = *((unsigned int *)t7);
    t17 = *((unsigned int *)t8);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB9;

LAB6:    if (t18 != 0)
        goto LAB8;

LAB7:    *((unsigned int *)t6) = 1;

LAB9:    t22 = (t6 + 4);
    t23 = *((unsigned int *)t22);
    t24 = (~(t23));
    t25 = *((unsigned int *)t6);
    t26 = (t25 & t24);
    t27 = (t26 != 0);
    if (t27 > 0)
        goto LAB10;

LAB11:    xsi_set_current_line(44, ng0);
    t2 = (t0 + 1048U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng3)));
    memset(t6, 0, 8);
    t4 = (t3 + 4);
    t5 = (t2 + 4);
    t9 = *((unsigned int *)t3);
    t10 = *((unsigned int *)t2);
    t11 = (t9 ^ t10);
    t12 = *((unsigned int *)t4);
    t13 = *((unsigned int *)t5);
    t14 = (t12 ^ t13);
    t15 = (t11 | t14);
    t16 = *((unsigned int *)t4);
    t17 = *((unsigned int *)t5);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB29;

LAB26:    if (t18 != 0)
        goto LAB28;

LAB27:    *((unsigned int *)t6) = 1;

LAB29:    t8 = (t6 + 4);
    t23 = *((unsigned int *)t8);
    t24 = (~(t23));
    t25 = *((unsigned int *)t6);
    t26 = (t25 & t24);
    t27 = (t26 != 0);
    if (t27 > 0)
        goto LAB30;

LAB31:    xsi_set_current_line(51, ng0);
    t2 = (t0 + 1048U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng5)));
    memset(t6, 0, 8);
    t4 = (t3 + 4);
    t5 = (t2 + 4);
    t9 = *((unsigned int *)t3);
    t10 = *((unsigned int *)t2);
    t11 = (t9 ^ t10);
    t12 = *((unsigned int *)t4);
    t13 = *((unsigned int *)t5);
    t14 = (t12 ^ t13);
    t15 = (t11 | t14);
    t16 = *((unsigned int *)t4);
    t17 = *((unsigned int *)t5);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB45;

LAB42:    if (t18 != 0)
        goto LAB44;

LAB43:    *((unsigned int *)t6) = 1;

LAB45:    t8 = (t6 + 4);
    t23 = *((unsigned int *)t8);
    t24 = (~(t23));
    t25 = *((unsigned int *)t6);
    t26 = (t25 & t24);
    t27 = (t26 != 0);
    if (t27 > 0)
        goto LAB46;

LAB47:    xsi_set_current_line(57, ng0);
    t2 = ((char*)((ng7)));
    t3 = (t0 + 1768);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 4);

LAB48:
LAB32:
LAB12:    goto LAB2;

LAB8:    t21 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t21) = 1;
    goto LAB9;

LAB10:    xsi_set_current_line(35, ng0);

LAB13:    xsi_set_current_line(36, ng0);
    t28 = (t0 + 1368U);
    t29 = *((char **)t28);

LAB14:    t28 = ((char*)((ng2)));
    t30 = xsi_vlog_unsigned_case_compare(t29, 2, t28, 2);
    if (t30 == 1)
        goto LAB15;

LAB16:    t2 = ((char*)((ng1)));
    t30 = xsi_vlog_unsigned_case_compare(t29, 2, t2, 2);
    if (t30 == 1)
        goto LAB17;

LAB18:    t2 = ((char*)((ng3)));
    t30 = xsi_vlog_unsigned_case_compare(t29, 2, t2, 2);
    if (t30 == 1)
        goto LAB19;

LAB20:    t2 = ((char*)((ng5)));
    t30 = xsi_vlog_unsigned_case_compare(t29, 2, t2, 2);
    if (t30 == 1)
        goto LAB21;

LAB22:
LAB24:
LAB23:    xsi_set_current_line(41, ng0);
    t2 = ((char*)((ng7)));
    t3 = (t0 + 1768);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 4);

LAB25:    goto LAB12;

LAB15:    xsi_set_current_line(37, ng0);
    t31 = ((char*)((ng1)));
    t32 = (t0 + 1768);
    xsi_vlogvar_assign_value(t32, t31, 0, 0, 4);
    goto LAB25;

LAB17:    xsi_set_current_line(38, ng0);
    t3 = ((char*)((ng3)));
    t4 = (t0 + 1768);
    xsi_vlogvar_assign_value(t4, t3, 0, 0, 4);
    goto LAB25;

LAB19:    xsi_set_current_line(39, ng0);
    t3 = ((char*)((ng4)));
    t4 = (t0 + 1768);
    xsi_vlogvar_assign_value(t4, t3, 0, 0, 4);
    goto LAB25;

LAB21:    xsi_set_current_line(40, ng0);
    t3 = ((char*)((ng6)));
    t4 = (t0 + 1768);
    xsi_vlogvar_assign_value(t4, t3, 0, 0, 4);
    goto LAB25;

LAB28:    t7 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t7) = 1;
    goto LAB29;

LAB30:    xsi_set_current_line(44, ng0);

LAB33:    xsi_set_current_line(45, ng0);
    t21 = (t0 + 1368U);
    t22 = *((char **)t21);

LAB34:    t21 = ((char*)((ng8)));
    t30 = xsi_vlog_unsigned_case_xcompare(t22, 2, t21, 2);
    if (t30 == 1)
        goto LAB35;

LAB36:    t2 = ((char*)((ng9)));
    t30 = xsi_vlog_unsigned_case_xcompare(t22, 2, t2, 2);
    if (t30 == 1)
        goto LAB37;

LAB38:
LAB40:
LAB39:    xsi_set_current_line(48, ng0);
    t2 = ((char*)((ng7)));
    t3 = (t0 + 1768);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 4);

LAB41:    goto LAB32;

LAB35:    xsi_set_current_line(46, ng0);
    t28 = ((char*)((ng5)));
    t31 = (t0 + 1768);
    xsi_vlogvar_assign_value(t31, t28, 0, 0, 4);
    goto LAB41;

LAB37:    xsi_set_current_line(47, ng0);
    t3 = ((char*)((ng10)));
    t4 = (t0 + 1768);
    xsi_vlogvar_assign_value(t4, t3, 0, 0, 4);
    goto LAB41;

LAB44:    t7 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t7) = 1;
    goto LAB45;

LAB46:    xsi_set_current_line(51, ng0);

LAB49:    xsi_set_current_line(52, ng0);
    t21 = (t0 + 1368U);
    t28 = *((char **)t21);

LAB50:    t21 = ((char*)((ng11)));
    t30 = xsi_vlog_unsigned_case_xcompare(t28, 2, t21, 2);
    if (t30 == 1)
        goto LAB51;

LAB52:
LAB54:
LAB53:    xsi_set_current_line(54, ng0);
    t2 = ((char*)((ng7)));
    t3 = (t0 + 1768);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 4);

LAB55:    goto LAB48;

LAB51:    xsi_set_current_line(53, ng0);
    t31 = ((char*)((ng12)));
    t32 = (t0 + 1768);
    xsi_vlogvar_assign_value(t32, t31, 0, 0, 4);
    goto LAB55;

}


extern void work_m_00000000000468346776_3305991770_init()
{
	static char *pe[] = {(void *)Cont_32_0,(void *)Always_34_1};
	xsi_register_didat("work_m_00000000000468346776_3305991770", "isim/mips_txt_isim_beh.exe.sim/work/m_00000000000468346776_3305991770.didat");
	xsi_register_executes(pe);
}
