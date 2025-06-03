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
static const char *ng0 = "D:/ISE/pip_CPU_inst50/DMR.v";
static unsigned int ng1[] = {1U, 0U};
static unsigned int ng2[] = {0U, 0U};
static int ng3[] = {24, 0};
static unsigned int ng4[] = {2U, 0U};
static unsigned int ng5[] = {3U, 0U};
static unsigned int ng6[] = {1U, 1U};
static int ng7[] = {16, 0};
static unsigned int ng8[] = {3U, 1U};
static unsigned int ng9[] = {4U, 0U};
static unsigned int ng10[] = {5U, 0U};
static unsigned int ng11[] = {3U, 3U};
static int ng12[] = {0, 0};



static void Cont_35_0(char *t0)
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

LAB0:    t1 = (t0 + 2848U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(35, ng0);
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
    t12 = (t0 + 3512);
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
    t25 = (t0 + 3416);
    *((int *)t25) = 1;

LAB1:    return;
}

static void Always_36_1(char *t0)
{
    char t6[8];
    char t31[8];
    char t32[8];
    char t42[8];
    char t46[8];
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
    char *t33;
    char *t34;
    char *t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    unsigned int t39;
    unsigned int t40;
    unsigned int t41;
    char *t43;
    char *t44;
    char *t45;
    char *t47;
    unsigned int t48;
    unsigned int t49;
    unsigned int t50;
    unsigned int t51;
    unsigned int t52;
    unsigned int t53;
    char *t54;

LAB0:    t1 = (t0 + 3096U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(36, ng0);
    t2 = (t0 + 3432);
    *((int *)t2) = 1;
    t3 = (t0 + 3128);
    *((char **)t3) = t2;
    *((char **)t1) = &&LAB4;

LAB1:    return;
LAB4:    xsi_set_current_line(36, ng0);

LAB5:    xsi_set_current_line(37, ng0);
    t4 = (t0 + 1368U);
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

LAB11:    xsi_set_current_line(46, ng0);
    t2 = (t0 + 1368U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng4)));
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

LAB31:    xsi_set_current_line(55, ng0);
    t2 = (t0 + 1368U);
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
        goto LAB49;

LAB46:    if (t18 != 0)
        goto LAB48;

LAB47:    *((unsigned int *)t6) = 1;

LAB49:    t8 = (t6 + 4);
    t23 = *((unsigned int *)t8);
    t24 = (~(t23));
    t25 = *((unsigned int *)t6);
    t26 = (t25 & t24);
    t27 = (t26 != 0);
    if (t27 > 0)
        goto LAB50;

LAB51:    xsi_set_current_line(62, ng0);
    t2 = (t0 + 1368U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng9)));
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
        goto LAB65;

LAB62:    if (t18 != 0)
        goto LAB64;

LAB63:    *((unsigned int *)t6) = 1;

LAB65:    t8 = (t6 + 4);
    t23 = *((unsigned int *)t8);
    t24 = (~(t23));
    t25 = *((unsigned int *)t6);
    t26 = (t25 & t24);
    t27 = (t26 != 0);
    if (t27 > 0)
        goto LAB66;

LAB67:    xsi_set_current_line(69, ng0);
    t2 = (t0 + 1368U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng10)));
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
        goto LAB81;

LAB78:    if (t18 != 0)
        goto LAB80;

LAB79:    *((unsigned int *)t6) = 1;

LAB81:    t8 = (t6 + 4);
    t23 = *((unsigned int *)t8);
    t24 = (~(t23));
    t25 = *((unsigned int *)t6);
    t26 = (t25 & t24);
    t27 = (t26 != 0);
    if (t27 > 0)
        goto LAB82;

LAB83:    xsi_set_current_line(75, ng0);
    t2 = ((char*)((ng12)));
    t3 = (t0 + 1928);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);

LAB84:
LAB68:
LAB52:
LAB32:
LAB12:    goto LAB2;

LAB8:    t21 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t21) = 1;
    goto LAB9;

LAB10:    xsi_set_current_line(37, ng0);

LAB13:    xsi_set_current_line(38, ng0);
    t28 = (t0 + 1528U);
    t29 = *((char **)t28);

LAB14:    t28 = ((char*)((ng2)));
    t30 = xsi_vlog_unsigned_case_compare(t29, 2, t28, 2);
    if (t30 == 1)
        goto LAB15;

LAB16:    t2 = ((char*)((ng1)));
    t30 = xsi_vlog_unsigned_case_compare(t29, 2, t2, 2);
    if (t30 == 1)
        goto LAB17;

LAB18:    t2 = ((char*)((ng4)));
    t30 = xsi_vlog_unsigned_case_compare(t29, 2, t2, 2);
    if (t30 == 1)
        goto LAB19;

LAB20:    t2 = ((char*)((ng5)));
    t30 = xsi_vlog_unsigned_case_compare(t29, 2, t2, 2);
    if (t30 == 1)
        goto LAB21;

LAB22:
LAB24:
LAB23:    xsi_set_current_line(43, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 1928);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);

LAB25:    goto LAB12;

LAB15:    xsi_set_current_line(39, ng0);
    t33 = (t0 + 1048U);
    t34 = *((char **)t33);
    memset(t32, 0, 8);
    t33 = (t32 + 4);
    t35 = (t34 + 4);
    t36 = *((unsigned int *)t34);
    t37 = (t36 >> 0);
    *((unsigned int *)t32) = t37;
    t38 = *((unsigned int *)t35);
    t39 = (t38 >> 0);
    *((unsigned int *)t33) = t39;
    t40 = *((unsigned int *)t32);
    *((unsigned int *)t32) = (t40 & 255U);
    t41 = *((unsigned int *)t33);
    *((unsigned int *)t33) = (t41 & 255U);
    t43 = ((char*)((ng3)));
    t44 = (t0 + 1048U);
    t45 = *((char **)t44);
    memset(t46, 0, 8);
    t44 = (t46 + 4);
    t47 = (t45 + 4);
    t48 = *((unsigned int *)t45);
    t49 = (t48 >> 7);
    t50 = (t49 & 1);
    *((unsigned int *)t46) = t50;
    t51 = *((unsigned int *)t47);
    t52 = (t51 >> 7);
    t53 = (t52 & 1);
    *((unsigned int *)t44) = t53;
    xsi_vlog_mul_concat(t42, 24, 1, t43, 1U, t46, 1);
    xsi_vlogtype_concat(t31, 32, 32, 2U, t42, 24, t32, 8);
    t54 = (t0 + 1928);
    xsi_vlogvar_assign_value(t54, t31, 0, 0, 32);
    goto LAB25;

LAB17:    xsi_set_current_line(40, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    memset(t31, 0, 8);
    t3 = (t31 + 4);
    t5 = (t4 + 4);
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 8);
    *((unsigned int *)t31) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 8);
    *((unsigned int *)t3) = t12;
    t13 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t13 & 255U);
    t14 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t14 & 255U);
    t7 = ((char*)((ng3)));
    t8 = (t0 + 1048U);
    t21 = *((char **)t8);
    memset(t42, 0, 8);
    t8 = (t42 + 4);
    t22 = (t21 + 4);
    t15 = *((unsigned int *)t21);
    t16 = (t15 >> 15);
    t17 = (t16 & 1);
    *((unsigned int *)t42) = t17;
    t18 = *((unsigned int *)t22);
    t19 = (t18 >> 15);
    t20 = (t19 & 1);
    *((unsigned int *)t8) = t20;
    xsi_vlog_mul_concat(t32, 24, 1, t7, 1U, t42, 1);
    xsi_vlogtype_concat(t6, 32, 32, 2U, t32, 24, t31, 8);
    t28 = (t0 + 1928);
    xsi_vlogvar_assign_value(t28, t6, 0, 0, 32);
    goto LAB25;

LAB19:    xsi_set_current_line(41, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    memset(t31, 0, 8);
    t3 = (t31 + 4);
    t5 = (t4 + 4);
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 16);
    *((unsigned int *)t31) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 16);
    *((unsigned int *)t3) = t12;
    t13 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t13 & 255U);
    t14 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t14 & 255U);
    t7 = ((char*)((ng3)));
    t8 = (t0 + 1048U);
    t21 = *((char **)t8);
    memset(t42, 0, 8);
    t8 = (t42 + 4);
    t22 = (t21 + 4);
    t15 = *((unsigned int *)t21);
    t16 = (t15 >> 23);
    t17 = (t16 & 1);
    *((unsigned int *)t42) = t17;
    t18 = *((unsigned int *)t22);
    t19 = (t18 >> 23);
    t20 = (t19 & 1);
    *((unsigned int *)t8) = t20;
    xsi_vlog_mul_concat(t32, 24, 1, t7, 1U, t42, 1);
    xsi_vlogtype_concat(t6, 32, 32, 2U, t32, 24, t31, 8);
    t28 = (t0 + 1928);
    xsi_vlogvar_assign_value(t28, t6, 0, 0, 32);
    goto LAB25;

LAB21:    xsi_set_current_line(42, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    memset(t31, 0, 8);
    t3 = (t31 + 4);
    t5 = (t4 + 4);
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 24);
    *((unsigned int *)t31) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 24);
    *((unsigned int *)t3) = t12;
    t13 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t13 & 255U);
    t14 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t14 & 255U);
    t7 = ((char*)((ng3)));
    t8 = (t0 + 1048U);
    t21 = *((char **)t8);
    memset(t42, 0, 8);
    t8 = (t42 + 4);
    t22 = (t21 + 4);
    t15 = *((unsigned int *)t21);
    t16 = (t15 >> 31);
    t17 = (t16 & 1);
    *((unsigned int *)t42) = t17;
    t18 = *((unsigned int *)t22);
    t19 = (t18 >> 31);
    t20 = (t19 & 1);
    *((unsigned int *)t8) = t20;
    xsi_vlog_mul_concat(t32, 24, 1, t7, 1U, t42, 1);
    xsi_vlogtype_concat(t6, 32, 32, 2U, t32, 24, t31, 8);
    t28 = (t0 + 1928);
    xsi_vlogvar_assign_value(t28, t6, 0, 0, 32);
    goto LAB25;

LAB28:    t7 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t7) = 1;
    goto LAB29;

LAB30:    xsi_set_current_line(46, ng0);

LAB33:    xsi_set_current_line(47, ng0);
    t21 = (t0 + 1528U);
    t22 = *((char **)t21);

LAB34:    t21 = ((char*)((ng2)));
    t30 = xsi_vlog_unsigned_case_compare(t22, 2, t21, 2);
    if (t30 == 1)
        goto LAB35;

LAB36:    t2 = ((char*)((ng1)));
    t30 = xsi_vlog_unsigned_case_compare(t22, 2, t2, 2);
    if (t30 == 1)
        goto LAB37;

LAB38:    t2 = ((char*)((ng4)));
    t30 = xsi_vlog_unsigned_case_compare(t22, 2, t2, 2);
    if (t30 == 1)
        goto LAB39;

LAB40:    t2 = ((char*)((ng5)));
    t30 = xsi_vlog_unsigned_case_compare(t22, 2, t2, 2);
    if (t30 == 1)
        goto LAB41;

LAB42:
LAB44:
LAB43:    xsi_set_current_line(52, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 1928);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);

LAB45:    goto LAB32;

LAB35:    xsi_set_current_line(48, ng0);
    t28 = (t0 + 1048U);
    t33 = *((char **)t28);
    memset(t32, 0, 8);
    t28 = (t32 + 4);
    t34 = (t33 + 4);
    t36 = *((unsigned int *)t33);
    t37 = (t36 >> 0);
    *((unsigned int *)t32) = t37;
    t38 = *((unsigned int *)t34);
    t39 = (t38 >> 0);
    *((unsigned int *)t28) = t39;
    t40 = *((unsigned int *)t32);
    *((unsigned int *)t32) = (t40 & 255U);
    t41 = *((unsigned int *)t28);
    *((unsigned int *)t28) = (t41 & 255U);
    t35 = ((char*)((ng2)));
    xsi_vlogtype_concat(t31, 32, 32, 2U, t35, 24, t32, 8);
    t43 = (t0 + 1928);
    xsi_vlogvar_assign_value(t43, t31, 0, 0, 32);
    goto LAB45;

LAB37:    xsi_set_current_line(49, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    memset(t31, 0, 8);
    t3 = (t31 + 4);
    t5 = (t4 + 4);
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 8);
    *((unsigned int *)t31) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 8);
    *((unsigned int *)t3) = t12;
    t13 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t13 & 255U);
    t14 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t14 & 255U);
    t7 = ((char*)((ng2)));
    xsi_vlogtype_concat(t6, 32, 32, 2U, t7, 24, t31, 8);
    t8 = (t0 + 1928);
    xsi_vlogvar_assign_value(t8, t6, 0, 0, 32);
    goto LAB45;

LAB39:    xsi_set_current_line(50, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    memset(t31, 0, 8);
    t3 = (t31 + 4);
    t5 = (t4 + 4);
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 16);
    *((unsigned int *)t31) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 16);
    *((unsigned int *)t3) = t12;
    t13 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t13 & 255U);
    t14 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t14 & 255U);
    t7 = ((char*)((ng2)));
    xsi_vlogtype_concat(t6, 32, 32, 2U, t7, 24, t31, 8);
    t8 = (t0 + 1928);
    xsi_vlogvar_assign_value(t8, t6, 0, 0, 32);
    goto LAB45;

LAB41:    xsi_set_current_line(51, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    memset(t31, 0, 8);
    t3 = (t31 + 4);
    t5 = (t4 + 4);
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 24);
    *((unsigned int *)t31) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 24);
    *((unsigned int *)t3) = t12;
    t13 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t13 & 255U);
    t14 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t14 & 255U);
    t7 = ((char*)((ng2)));
    xsi_vlogtype_concat(t6, 32, 32, 2U, t7, 24, t31, 8);
    t8 = (t0 + 1928);
    xsi_vlogvar_assign_value(t8, t6, 0, 0, 32);
    goto LAB45;

LAB48:    t7 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t7) = 1;
    goto LAB49;

LAB50:    xsi_set_current_line(55, ng0);

LAB53:    xsi_set_current_line(56, ng0);
    t21 = (t0 + 1528U);
    t28 = *((char **)t21);

LAB54:    t21 = ((char*)((ng6)));
    t30 = xsi_vlog_unsigned_case_xcompare(t28, 2, t21, 2);
    if (t30 == 1)
        goto LAB55;

LAB56:    t2 = ((char*)((ng8)));
    t30 = xsi_vlog_unsigned_case_xcompare(t28, 2, t2, 2);
    if (t30 == 1)
        goto LAB57;

LAB58:
LAB60:
LAB59:    xsi_set_current_line(59, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 1928);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);

LAB61:    goto LAB52;

LAB55:    xsi_set_current_line(57, ng0);
    t33 = (t0 + 1048U);
    t34 = *((char **)t33);
    memset(t32, 0, 8);
    t33 = (t32 + 4);
    t35 = (t34 + 4);
    t36 = *((unsigned int *)t34);
    t37 = (t36 >> 0);
    *((unsigned int *)t32) = t37;
    t38 = *((unsigned int *)t35);
    t39 = (t38 >> 0);
    *((unsigned int *)t33) = t39;
    t40 = *((unsigned int *)t32);
    *((unsigned int *)t32) = (t40 & 65535U);
    t41 = *((unsigned int *)t33);
    *((unsigned int *)t33) = (t41 & 65535U);
    t43 = ((char*)((ng7)));
    t44 = (t0 + 1048U);
    t45 = *((char **)t44);
    memset(t46, 0, 8);
    t44 = (t46 + 4);
    t47 = (t45 + 4);
    t48 = *((unsigned int *)t45);
    t49 = (t48 >> 15);
    t50 = (t49 & 1);
    *((unsigned int *)t46) = t50;
    t51 = *((unsigned int *)t47);
    t52 = (t51 >> 15);
    t53 = (t52 & 1);
    *((unsigned int *)t44) = t53;
    xsi_vlog_mul_concat(t42, 16, 1, t43, 1U, t46, 1);
    xsi_vlogtype_concat(t31, 32, 32, 2U, t42, 16, t32, 16);
    t54 = (t0 + 1928);
    xsi_vlogvar_assign_value(t54, t31, 0, 0, 32);
    goto LAB61;

LAB57:    xsi_set_current_line(58, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    memset(t31, 0, 8);
    t3 = (t31 + 4);
    t5 = (t4 + 4);
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 16);
    *((unsigned int *)t31) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 16);
    *((unsigned int *)t3) = t12;
    t13 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t13 & 65535U);
    t14 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t14 & 65535U);
    t7 = ((char*)((ng7)));
    t8 = (t0 + 1048U);
    t21 = *((char **)t8);
    memset(t42, 0, 8);
    t8 = (t42 + 4);
    t33 = (t21 + 4);
    t15 = *((unsigned int *)t21);
    t16 = (t15 >> 31);
    t17 = (t16 & 1);
    *((unsigned int *)t42) = t17;
    t18 = *((unsigned int *)t33);
    t19 = (t18 >> 31);
    t20 = (t19 & 1);
    *((unsigned int *)t8) = t20;
    xsi_vlog_mul_concat(t32, 16, 1, t7, 1U, t42, 1);
    xsi_vlogtype_concat(t6, 32, 32, 2U, t32, 16, t31, 16);
    t34 = (t0 + 1928);
    xsi_vlogvar_assign_value(t34, t6, 0, 0, 32);
    goto LAB61;

LAB64:    t7 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t7) = 1;
    goto LAB65;

LAB66:    xsi_set_current_line(62, ng0);

LAB69:    xsi_set_current_line(63, ng0);
    t21 = (t0 + 1528U);
    t33 = *((char **)t21);

LAB70:    t21 = ((char*)((ng6)));
    t30 = xsi_vlog_unsigned_case_xcompare(t33, 2, t21, 2);
    if (t30 == 1)
        goto LAB71;

LAB72:    t2 = ((char*)((ng8)));
    t30 = xsi_vlog_unsigned_case_xcompare(t33, 2, t2, 2);
    if (t30 == 1)
        goto LAB73;

LAB74:
LAB76:
LAB75:    xsi_set_current_line(66, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 1928);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);

LAB77:    goto LAB68;

LAB71:    xsi_set_current_line(64, ng0);
    t34 = (t0 + 1048U);
    t35 = *((char **)t34);
    memset(t32, 0, 8);
    t34 = (t32 + 4);
    t43 = (t35 + 4);
    t36 = *((unsigned int *)t35);
    t37 = (t36 >> 0);
    *((unsigned int *)t32) = t37;
    t38 = *((unsigned int *)t43);
    t39 = (t38 >> 0);
    *((unsigned int *)t34) = t39;
    t40 = *((unsigned int *)t32);
    *((unsigned int *)t32) = (t40 & 65535U);
    t41 = *((unsigned int *)t34);
    *((unsigned int *)t34) = (t41 & 65535U);
    t44 = ((char*)((ng2)));
    xsi_vlogtype_concat(t31, 32, 32, 2U, t44, 16, t32, 16);
    t45 = (t0 + 1928);
    xsi_vlogvar_assign_value(t45, t31, 0, 0, 32);
    goto LAB77;

LAB73:    xsi_set_current_line(65, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    memset(t31, 0, 8);
    t3 = (t31 + 4);
    t5 = (t4 + 4);
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 16);
    *((unsigned int *)t31) = t10;
    t11 = *((unsigned int *)t5);
    t12 = (t11 >> 16);
    *((unsigned int *)t3) = t12;
    t13 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t13 & 65535U);
    t14 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t14 & 65535U);
    t7 = ((char*)((ng2)));
    xsi_vlogtype_concat(t6, 32, 32, 2U, t7, 16, t31, 16);
    t8 = (t0 + 1928);
    xsi_vlogvar_assign_value(t8, t6, 0, 0, 32);
    goto LAB77;

LAB80:    t7 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t7) = 1;
    goto LAB81;

LAB82:    xsi_set_current_line(69, ng0);

LAB85:    xsi_set_current_line(70, ng0);
    t21 = (t0 + 1528U);
    t34 = *((char **)t21);

LAB86:    t21 = ((char*)((ng11)));
    t30 = xsi_vlog_unsigned_case_xcompare(t34, 2, t21, 2);
    if (t30 == 1)
        goto LAB87;

LAB88:
LAB90:
LAB89:    xsi_set_current_line(72, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 1928);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);

LAB91:    goto LAB84;

LAB87:    xsi_set_current_line(71, ng0);
    t35 = (t0 + 1048U);
    t43 = *((char **)t35);
    t35 = (t0 + 1928);
    xsi_vlogvar_assign_value(t35, t43, 0, 0, 32);
    goto LAB91;

}


extern void work_m_00000000001583840928_3043465941_init()
{
	static char *pe[] = {(void *)Cont_35_0,(void *)Always_36_1};
	xsi_register_didat("work_m_00000000001583840928_3043465941", "isim/mips_txt_isim_beh.exe.sim/work/m_00000000001583840928_3043465941.didat");
	xsi_register_executes(pe);
}
