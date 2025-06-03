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
static const char *ng0 = "D:/ISE/pip_CPU_inst50/RF.v";
static int ng1[] = {0, 0};
static int ng2[] = {31, 0};
static int ng3[] = {28, 0};
static unsigned int ng4[] = {6144U, 0U};
static int ng5[] = {29, 0};
static unsigned int ng6[] = {12284U, 0U};
static int ng7[] = {1, 0};



static void Always_37_0(char *t0)
{
    char t13[8];
    char t17[8];
    char t26[8];
    char t27[8];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    unsigned int t6;
    unsigned int t7;
    unsigned int t8;
    unsigned int t9;
    unsigned int t10;
    char *t11;
    char *t12;
    char *t14;
    char *t15;
    char *t16;
    char *t18;
    unsigned int t19;
    unsigned int t20;
    unsigned int t21;
    unsigned int t22;
    unsigned int t23;
    char *t24;
    char *t25;
    char *t28;
    char *t29;
    char *t30;
    char *t31;
    char *t32;
    char *t33;
    char *t34;
    char *t35;
    char *t36;
    char *t37;
    unsigned int t38;
    int t39;
    char *t40;
    unsigned int t41;
    int t42;
    int t43;
    unsigned int t44;
    unsigned int t45;
    int t46;
    int t47;
    unsigned int t48;
    unsigned int t49;
    unsigned int t50;
    unsigned int t51;
    unsigned int t52;
    unsigned int t53;
    unsigned int t54;
    unsigned int t55;
    unsigned int t56;
    unsigned int t57;
    unsigned int t58;
    unsigned int t59;

LAB0:    t1 = (t0 + 3968U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(37, ng0);
    t2 = (t0 + 5032);
    *((int *)t2) = 1;
    t3 = (t0 + 4000);
    *((char **)t3) = t2;
    *((char **)t1) = &&LAB4;

LAB1:    return;
LAB4:    xsi_set_current_line(37, ng0);

LAB5:    xsi_set_current_line(38, ng0);
    t4 = (t0 + 1848U);
    t5 = *((char **)t4);
    t4 = (t5 + 4);
    t6 = *((unsigned int *)t4);
    t7 = (~(t6));
    t8 = *((unsigned int *)t5);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB6;

LAB7:    xsi_set_current_line(51, ng0);
    t2 = (t0 + 2008U);
    t3 = *((char **)t2);
    t2 = (t3 + 4);
    t6 = *((unsigned int *)t2);
    t7 = (~(t6));
    t8 = *((unsigned int *)t3);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB29;

LAB30:
LAB31:
LAB8:    goto LAB2;

LAB6:    xsi_set_current_line(38, ng0);

LAB9:    xsi_set_current_line(39, ng0);
    xsi_set_current_line(39, ng0);
    t11 = ((char*)((ng1)));
    t12 = (t0 + 3048);
    xsi_vlogvar_assign_value(t12, t11, 0, 0, 32);

LAB10:    t2 = (t0 + 3048);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng2)));
    memset(t13, 0, 8);
    xsi_vlog_signed_leq(t13, 32, t4, 32, t5, 32);
    t11 = (t13 + 4);
    t6 = *((unsigned int *)t11);
    t7 = (~(t6));
    t8 = *((unsigned int *)t13);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB11;

LAB12:    goto LAB8;

LAB11:    xsi_set_current_line(39, ng0);

LAB13:    xsi_set_current_line(40, ng0);
    t12 = (t0 + 3048);
    t14 = (t12 + 56U);
    t15 = *((char **)t14);
    t16 = ((char*)((ng3)));
    memset(t17, 0, 8);
    xsi_vlog_signed_equal(t17, 32, t15, 32, t16, 32);
    t18 = (t17 + 4);
    t19 = *((unsigned int *)t18);
    t20 = (~(t19));
    t21 = *((unsigned int *)t17);
    t22 = (t21 & t20);
    t23 = (t22 != 0);
    if (t23 > 0)
        goto LAB14;

LAB15:    xsi_set_current_line(43, ng0);
    t2 = (t0 + 3048);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng5)));
    memset(t13, 0, 8);
    xsi_vlog_signed_equal(t13, 32, t4, 32, t5, 32);
    t11 = (t13 + 4);
    t6 = *((unsigned int *)t11);
    t7 = (~(t6));
    t8 = *((unsigned int *)t13);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB20;

LAB21:    xsi_set_current_line(46, ng0);

LAB26:    xsi_set_current_line(47, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 2888);
    t4 = (t0 + 2888);
    t5 = (t4 + 72U);
    t11 = *((char **)t5);
    t12 = (t0 + 2888);
    t14 = (t12 + 64U);
    t15 = *((char **)t14);
    t16 = (t0 + 3048);
    t18 = (t16 + 56U);
    t24 = *((char **)t18);
    xsi_vlog_generic_convert_array_indices(t13, t17, t11, t15, 2, 1, t24, 32, 1);
    t25 = (t13 + 4);
    t6 = *((unsigned int *)t25);
    t39 = (!(t6));
    t28 = (t17 + 4);
    t7 = *((unsigned int *)t28);
    t42 = (!(t7));
    t43 = (t39 && t42);
    if (t43 == 1)
        goto LAB27;

LAB28:
LAB22:
LAB16:    xsi_set_current_line(39, ng0);
    t2 = (t0 + 3048);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng7)));
    memset(t13, 0, 8);
    xsi_vlog_signed_add(t13, 32, t4, 32, t5, 32);
    t11 = (t0 + 3048);
    xsi_vlogvar_assign_value(t11, t13, 0, 0, 32);
    goto LAB10;

LAB14:    xsi_set_current_line(40, ng0);

LAB17:    xsi_set_current_line(41, ng0);
    t24 = ((char*)((ng4)));
    t25 = (t0 + 2888);
    t28 = (t0 + 2888);
    t29 = (t28 + 72U);
    t30 = *((char **)t29);
    t31 = (t0 + 2888);
    t32 = (t31 + 64U);
    t33 = *((char **)t32);
    t34 = (t0 + 3048);
    t35 = (t34 + 56U);
    t36 = *((char **)t35);
    xsi_vlog_generic_convert_array_indices(t26, t27, t30, t33, 2, 1, t36, 32, 1);
    t37 = (t26 + 4);
    t38 = *((unsigned int *)t37);
    t39 = (!(t38));
    t40 = (t27 + 4);
    t41 = *((unsigned int *)t40);
    t42 = (!(t41));
    t43 = (t39 && t42);
    if (t43 == 1)
        goto LAB18;

LAB19:    goto LAB16;

LAB18:    t44 = *((unsigned int *)t26);
    t45 = *((unsigned int *)t27);
    t46 = (t44 - t45);
    t47 = (t46 + 1);
    xsi_vlogvar_wait_assign_value(t25, t24, 0, *((unsigned int *)t27), t47, 0LL);
    goto LAB19;

LAB20:    xsi_set_current_line(43, ng0);

LAB23:    xsi_set_current_line(44, ng0);
    t12 = ((char*)((ng6)));
    t14 = (t0 + 2888);
    t15 = (t0 + 2888);
    t16 = (t15 + 72U);
    t18 = *((char **)t16);
    t24 = (t0 + 2888);
    t25 = (t24 + 64U);
    t28 = *((char **)t25);
    t29 = (t0 + 3048);
    t30 = (t29 + 56U);
    t31 = *((char **)t30);
    xsi_vlog_generic_convert_array_indices(t17, t26, t18, t28, 2, 1, t31, 32, 1);
    t32 = (t17 + 4);
    t19 = *((unsigned int *)t32);
    t39 = (!(t19));
    t33 = (t26 + 4);
    t20 = *((unsigned int *)t33);
    t42 = (!(t20));
    t43 = (t39 && t42);
    if (t43 == 1)
        goto LAB24;

LAB25:    goto LAB22;

LAB24:    t21 = *((unsigned int *)t17);
    t22 = *((unsigned int *)t26);
    t46 = (t21 - t22);
    t47 = (t46 + 1);
    xsi_vlogvar_wait_assign_value(t14, t12, 0, *((unsigned int *)t26), t47, 0LL);
    goto LAB25;

LAB27:    t8 = *((unsigned int *)t13);
    t9 = *((unsigned int *)t17);
    t46 = (t8 - t9);
    t47 = (t46 + 1);
    xsi_vlogvar_wait_assign_value(t3, t2, 0, *((unsigned int *)t17), t47, 0LL);
    goto LAB28;

LAB29:    xsi_set_current_line(51, ng0);

LAB32:    xsi_set_current_line(52, ng0);
    t4 = (t0 + 1368U);
    t5 = *((char **)t4);
    t4 = ((char*)((ng1)));
    memset(t13, 0, 8);
    t11 = (t5 + 4);
    t12 = (t4 + 4);
    t19 = *((unsigned int *)t5);
    t20 = *((unsigned int *)t4);
    t21 = (t19 ^ t20);
    t22 = *((unsigned int *)t11);
    t23 = *((unsigned int *)t12);
    t38 = (t22 ^ t23);
    t41 = (t21 | t38);
    t44 = *((unsigned int *)t11);
    t45 = *((unsigned int *)t12);
    t48 = (t44 | t45);
    t49 = (~(t48));
    t50 = (t41 & t49);
    if (t50 != 0)
        goto LAB34;

LAB33:    if (t48 != 0)
        goto LAB35;

LAB36:    t15 = (t13 + 4);
    t51 = *((unsigned int *)t15);
    t52 = (~(t51));
    t53 = *((unsigned int *)t13);
    t54 = (t53 & t52);
    t55 = (t54 != 0);
    if (t55 > 0)
        goto LAB37;

LAB38:
LAB39:    goto LAB31;

LAB34:    *((unsigned int *)t13) = 1;
    goto LAB36;

LAB35:    t14 = (t13 + 4);
    *((unsigned int *)t13) = 1;
    *((unsigned int *)t14) = 1;
    goto LAB36;

LAB37:    xsi_set_current_line(52, ng0);

LAB40:    xsi_set_current_line(53, ng0);
    t16 = (t0 + 1528U);
    t18 = *((char **)t16);
    t16 = (t0 + 2888);
    t24 = (t0 + 2888);
    t25 = (t24 + 72U);
    t28 = *((char **)t25);
    t29 = (t0 + 2888);
    t30 = (t29 + 64U);
    t31 = *((char **)t30);
    t32 = (t0 + 1368U);
    t33 = *((char **)t32);
    xsi_vlog_generic_convert_array_indices(t17, t26, t28, t31, 2, 1, t33, 5, 2);
    t32 = (t17 + 4);
    t56 = *((unsigned int *)t32);
    t39 = (!(t56));
    t34 = (t26 + 4);
    t57 = *((unsigned int *)t34);
    t42 = (!(t57));
    t43 = (t39 && t42);
    if (t43 == 1)
        goto LAB41;

LAB42:    goto LAB39;

LAB41:    t58 = *((unsigned int *)t17);
    t59 = *((unsigned int *)t26);
    t46 = (t58 - t59);
    t47 = (t46 + 1);
    xsi_vlogvar_wait_assign_value(t16, t18, 0, *((unsigned int *)t26), t47, 0LL);
    goto LAB42;

}

static void Cont_58_1(char *t0)
{
    char t5[8];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;
    char *t17;
    char *t18;

LAB0:    t1 = (t0 + 4216U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(58, ng0);
    t2 = (t0 + 2888);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t6 = (t0 + 2888);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 2888);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = (t0 + 1048U);
    t13 = *((char **)t12);
    xsi_vlog_generic_get_array_select_value(t5, 32, t4, t8, t11, 2, 1, t13, 5, 2);
    t12 = (t0 + 5144);
    t14 = (t12 + 56U);
    t15 = *((char **)t14);
    t16 = (t15 + 56U);
    t17 = *((char **)t16);
    memcpy(t17, t5, 8);
    xsi_driver_vfirst_trans(t12, 0, 31);
    t18 = (t0 + 5048);
    *((int *)t18) = 1;

LAB1:    return;
}

static void Cont_59_2(char *t0)
{
    char t5[8];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;
    char *t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;
    char *t17;
    char *t18;

LAB0:    t1 = (t0 + 4464U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(59, ng0);
    t2 = (t0 + 2888);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t6 = (t0 + 2888);
    t7 = (t6 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 2888);
    t10 = (t9 + 64U);
    t11 = *((char **)t10);
    t12 = (t0 + 1208U);
    t13 = *((char **)t12);
    xsi_vlog_generic_get_array_select_value(t5, 32, t4, t8, t11, 2, 1, t13, 5, 2);
    t12 = (t0 + 5208);
    t14 = (t12 + 56U);
    t15 = *((char **)t14);
    t16 = (t15 + 56U);
    t17 = *((char **)t16);
    memcpy(t17, t5, 8);
    xsi_driver_vfirst_trans(t12, 0, 31);
    t18 = (t0 + 5064);
    *((int *)t18) = 1;

LAB1:    return;
}

static void Initial_61_3(char *t0)
{
    char t5[8];
    char t16[8];
    char t25[8];
    char t26[8];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t6;
    unsigned int t7;
    unsigned int t8;
    unsigned int t9;
    unsigned int t10;
    unsigned int t11;
    char *t12;
    char *t13;
    char *t14;
    char *t15;
    char *t17;
    unsigned int t18;
    unsigned int t19;
    unsigned int t20;
    unsigned int t21;
    unsigned int t22;
    char *t23;
    char *t24;
    char *t27;
    char *t28;
    char *t29;
    char *t30;
    char *t31;
    char *t32;
    char *t33;
    char *t34;
    char *t35;
    char *t36;
    unsigned int t37;
    int t38;
    char *t39;
    unsigned int t40;
    int t41;
    int t42;
    unsigned int t43;
    unsigned int t44;
    int t45;
    int t46;

LAB0:    xsi_set_current_line(61, ng0);

LAB2:    xsi_set_current_line(62, ng0);
    xsi_set_current_line(62, ng0);
    t1 = ((char*)((ng1)));
    t2 = (t0 + 3048);
    xsi_vlogvar_assign_value(t2, t1, 0, 0, 32);

LAB3:    t1 = (t0 + 3048);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t4 = ((char*)((ng2)));
    memset(t5, 0, 8);
    xsi_vlog_signed_leq(t5, 32, t3, 32, t4, 32);
    t6 = (t5 + 4);
    t7 = *((unsigned int *)t6);
    t8 = (~(t7));
    t9 = *((unsigned int *)t5);
    t10 = (t9 & t8);
    t11 = (t10 != 0);
    if (t11 > 0)
        goto LAB4;

LAB5:
LAB1:    return;
LAB4:    xsi_set_current_line(62, ng0);

LAB6:    xsi_set_current_line(63, ng0);
    t12 = (t0 + 3048);
    t13 = (t12 + 56U);
    t14 = *((char **)t13);
    t15 = ((char*)((ng3)));
    memset(t16, 0, 8);
    xsi_vlog_signed_equal(t16, 32, t14, 32, t15, 32);
    t17 = (t16 + 4);
    t18 = *((unsigned int *)t17);
    t19 = (~(t18));
    t20 = *((unsigned int *)t16);
    t21 = (t20 & t19);
    t22 = (t21 != 0);
    if (t22 > 0)
        goto LAB7;

LAB8:    xsi_set_current_line(66, ng0);
    t1 = (t0 + 3048);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t4 = ((char*)((ng5)));
    memset(t5, 0, 8);
    xsi_vlog_signed_equal(t5, 32, t3, 32, t4, 32);
    t6 = (t5 + 4);
    t7 = *((unsigned int *)t6);
    t8 = (~(t7));
    t9 = *((unsigned int *)t5);
    t10 = (t9 & t8);
    t11 = (t10 != 0);
    if (t11 > 0)
        goto LAB13;

LAB14:    xsi_set_current_line(69, ng0);

LAB19:    xsi_set_current_line(70, ng0);
    t1 = ((char*)((ng1)));
    t2 = (t0 + 2888);
    t3 = (t0 + 2888);
    t4 = (t3 + 72U);
    t6 = *((char **)t4);
    t12 = (t0 + 2888);
    t13 = (t12 + 64U);
    t14 = *((char **)t13);
    t15 = (t0 + 3048);
    t17 = (t15 + 56U);
    t23 = *((char **)t17);
    xsi_vlog_generic_convert_array_indices(t5, t16, t6, t14, 2, 1, t23, 32, 1);
    t24 = (t5 + 4);
    t7 = *((unsigned int *)t24);
    t38 = (!(t7));
    t27 = (t16 + 4);
    t8 = *((unsigned int *)t27);
    t41 = (!(t8));
    t42 = (t38 && t41);
    if (t42 == 1)
        goto LAB20;

LAB21:
LAB15:
LAB9:    xsi_set_current_line(62, ng0);
    t1 = (t0 + 3048);
    t2 = (t1 + 56U);
    t3 = *((char **)t2);
    t4 = ((char*)((ng7)));
    memset(t5, 0, 8);
    xsi_vlog_signed_add(t5, 32, t3, 32, t4, 32);
    t6 = (t0 + 3048);
    xsi_vlogvar_assign_value(t6, t5, 0, 0, 32);
    goto LAB3;

LAB7:    xsi_set_current_line(63, ng0);

LAB10:    xsi_set_current_line(64, ng0);
    t23 = ((char*)((ng4)));
    t24 = (t0 + 2888);
    t27 = (t0 + 2888);
    t28 = (t27 + 72U);
    t29 = *((char **)t28);
    t30 = (t0 + 2888);
    t31 = (t30 + 64U);
    t32 = *((char **)t31);
    t33 = (t0 + 3048);
    t34 = (t33 + 56U);
    t35 = *((char **)t34);
    xsi_vlog_generic_convert_array_indices(t25, t26, t29, t32, 2, 1, t35, 32, 1);
    t36 = (t25 + 4);
    t37 = *((unsigned int *)t36);
    t38 = (!(t37));
    t39 = (t26 + 4);
    t40 = *((unsigned int *)t39);
    t41 = (!(t40));
    t42 = (t38 && t41);
    if (t42 == 1)
        goto LAB11;

LAB12:    goto LAB9;

LAB11:    t43 = *((unsigned int *)t25);
    t44 = *((unsigned int *)t26);
    t45 = (t43 - t44);
    t46 = (t45 + 1);
    xsi_vlogvar_wait_assign_value(t24, t23, 0, *((unsigned int *)t26), t46, 0LL);
    goto LAB12;

LAB13:    xsi_set_current_line(66, ng0);

LAB16:    xsi_set_current_line(67, ng0);
    t12 = ((char*)((ng6)));
    t13 = (t0 + 2888);
    t14 = (t0 + 2888);
    t15 = (t14 + 72U);
    t17 = *((char **)t15);
    t23 = (t0 + 2888);
    t24 = (t23 + 64U);
    t27 = *((char **)t24);
    t28 = (t0 + 3048);
    t29 = (t28 + 56U);
    t30 = *((char **)t29);
    xsi_vlog_generic_convert_array_indices(t16, t25, t17, t27, 2, 1, t30, 32, 1);
    t31 = (t16 + 4);
    t18 = *((unsigned int *)t31);
    t38 = (!(t18));
    t32 = (t25 + 4);
    t19 = *((unsigned int *)t32);
    t41 = (!(t19));
    t42 = (t38 && t41);
    if (t42 == 1)
        goto LAB17;

LAB18:    goto LAB15;

LAB17:    t20 = *((unsigned int *)t16);
    t21 = *((unsigned int *)t25);
    t45 = (t20 - t21);
    t46 = (t45 + 1);
    xsi_vlogvar_wait_assign_value(t13, t12, 0, *((unsigned int *)t25), t46, 0LL);
    goto LAB18;

LAB20:    t9 = *((unsigned int *)t5);
    t10 = *((unsigned int *)t16);
    t45 = (t9 - t10);
    t46 = (t45 + 1);
    xsi_vlogvar_wait_assign_value(t2, t1, 0, *((unsigned int *)t16), t46, 0LL);
    goto LAB21;

}


extern void work_m_00000000002713769870_0621066737_init()
{
	static char *pe[] = {(void *)Always_37_0,(void *)Cont_58_1,(void *)Cont_59_2,(void *)Initial_61_3};
	xsi_register_didat("work_m_00000000002713769870_0621066737", "isim/mips_txt_isim_beh.exe.sim/work/m_00000000002713769870_0621066737.didat");
	xsi_register_executes(pe);
}
