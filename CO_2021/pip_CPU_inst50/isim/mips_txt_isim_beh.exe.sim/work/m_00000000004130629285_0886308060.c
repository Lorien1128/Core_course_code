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
static const char *ng0 = "D:/ISE/pip_CPU_inst50/ALU.v";
static unsigned int ng1[] = {0U, 0U};
static unsigned int ng2[] = {1U, 0U};
static unsigned int ng3[] = {2U, 0U};
static int ng4[] = {0, 0};
static int ng5[] = {31, 0};
static int ng6[] = {1, 0};
static unsigned int ng7[] = {3U, 0U};
static int ng8[] = {32, 0};
static unsigned int ng9[] = {4U, 0U};
static unsigned int ng10[] = {5U, 0U};
static unsigned int ng11[] = {6U, 0U};
static unsigned int ng12[] = {7U, 0U};
static unsigned int ng13[] = {8U, 0U};
static unsigned int ng14[] = {9U, 0U};
static unsigned int ng15[] = {10U, 0U};



static void Cont_40_0(char *t0)
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

LAB0:    t1 = (t0 + 3008U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(40, ng0);
    t2 = (t0 + 1048U);
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
    *((unsigned int *)t3) = (t10 & 31U);
    t11 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t11 & 31U);
    t12 = (t0 + 3672);
    t13 = (t12 + 56U);
    t14 = *((char **)t13);
    t15 = (t14 + 56U);
    t16 = *((char **)t15);
    memset(t16, 0, 8);
    t17 = 31U;
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
    xsi_driver_vfirst_trans(t12, 0, 4);
    t25 = (t0 + 3576);
    *((int *)t25) = 1;

LAB1:    return;
}

static void Always_44_1(char *t0)
{
    char t10[8];
    char t20[8];
    char t31[8];
    char t40[8];
    char t42[8];
    char t43[8];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    int t6;
    char *t7;
    char *t8;
    char *t9;
    unsigned int t11;
    unsigned int t12;
    unsigned int t13;
    unsigned int t14;
    unsigned int t15;
    char *t16;
    char *t17;
    char *t18;
    char *t19;
    char *t21;
    char *t22;
    char *t23;
    unsigned int t24;
    unsigned int t25;
    unsigned int t26;
    unsigned int t27;
    unsigned int t28;
    char *t29;
    char *t30;
    char *t32;
    char *t33;
    char *t34;
    char *t35;
    char *t36;
    char *t37;
    char *t38;
    unsigned int t39;
    char *t41;
    char *t44;
    char *t45;
    char *t46;
    char *t47;
    char *t48;
    char *t49;
    char *t50;
    unsigned int t51;
    unsigned int t52;
    unsigned int t53;
    unsigned int t54;
    unsigned int t55;
    unsigned int t56;
    unsigned int t57;
    int t58;
    int t59;
    unsigned int t60;
    unsigned int t61;
    unsigned int t62;
    unsigned int t63;
    unsigned int t64;
    unsigned int t65;
    unsigned int t66;
    unsigned int t67;
    unsigned int t68;
    unsigned int t69;

LAB0:    t1 = (t0 + 3256U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(44, ng0);
    t2 = (t0 + 3592);
    *((int *)t2) = 1;
    t3 = (t0 + 3288);
    *((char **)t3) = t2;
    *((char **)t1) = &&LAB4;

LAB1:    return;
LAB4:    xsi_set_current_line(44, ng0);

LAB5:    xsi_set_current_line(45, ng0);
    t4 = (t0 + 1368U);
    t5 = *((char **)t4);

LAB6:    t4 = ((char*)((ng1)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t4, 4);
    if (t6 == 1)
        goto LAB7;

LAB8:    t2 = ((char*)((ng2)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB9;

LAB10:    t2 = ((char*)((ng3)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB11;

LAB12:    t2 = ((char*)((ng7)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB13;

LAB14:    t2 = ((char*)((ng9)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB15;

LAB16:    t2 = ((char*)((ng10)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB17;

LAB18:    t2 = ((char*)((ng11)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB19;

LAB20:    t2 = ((char*)((ng12)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB21;

LAB22:    t2 = ((char*)((ng13)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB23;

LAB24:    t2 = ((char*)((ng14)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB25;

LAB26:    t2 = ((char*)((ng15)));
    t6 = xsi_vlog_unsigned_case_compare(t5, 4, t2, 4);
    if (t6 == 1)
        goto LAB27;

LAB28:
LAB30:
LAB29:    xsi_set_current_line(78, ng0);
    t2 = ((char*)((ng4)));
    t3 = (t0 + 1928);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);

LAB31:    goto LAB2;

LAB7:    xsi_set_current_line(46, ng0);
    t7 = (t0 + 1048U);
    t8 = *((char **)t7);
    t7 = (t0 + 1208U);
    t9 = *((char **)t7);
    memset(t10, 0, 8);
    xsi_vlog_unsigned_add(t10, 32, t8, 32, t9, 32);
    t7 = (t0 + 1928);
    xsi_vlogvar_assign_value(t7, t10, 0, 0, 32);
    goto LAB31;

LAB9:    xsi_set_current_line(47, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    t3 = (t0 + 1208U);
    t7 = *((char **)t3);
    memset(t10, 0, 8);
    xsi_vlog_unsigned_minus(t10, 32, t4, 32, t7, 32);
    t3 = (t0 + 1928);
    xsi_vlogvar_assign_value(t3, t10, 0, 0, 32);
    goto LAB31;

LAB11:    xsi_set_current_line(48, ng0);

LAB32:    xsi_set_current_line(49, ng0);
    xsi_set_current_line(49, ng0);
    t3 = ((char*)((ng4)));
    t4 = (t0 + 2088);
    xsi_vlogvar_assign_value(t4, t3, 0, 0, 32);

LAB33:    t2 = (t0 + 2088);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t7 = ((char*)((ng5)));
    memset(t10, 0, 8);
    xsi_vlog_signed_leq(t10, 32, t4, 32, t7, 32);
    t8 = (t10 + 4);
    t11 = *((unsigned int *)t8);
    t12 = (~(t11));
    t13 = *((unsigned int *)t10);
    t14 = (t13 & t12);
    t15 = (t14 != 0);
    if (t15 > 0)
        goto LAB34;

LAB35:    goto LAB31;

LAB13:    xsi_set_current_line(56, ng0);

LAB50:    xsi_set_current_line(57, ng0);
    xsi_set_current_line(57, ng0);
    t3 = ((char*)((ng4)));
    t4 = (t0 + 2088);
    xsi_vlogvar_assign_value(t4, t3, 0, 0, 32);

LAB51:    t2 = (t0 + 2088);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t7 = ((char*)((ng5)));
    memset(t10, 0, 8);
    xsi_vlog_signed_leq(t10, 32, t4, 32, t7, 32);
    t8 = (t10 + 4);
    t11 = *((unsigned int *)t8);
    t12 = (~(t11));
    t13 = *((unsigned int *)t10);
    t14 = (t13 & t12);
    t15 = (t14 != 0);
    if (t15 > 0)
        goto LAB52;

LAB53:    goto LAB31;

LAB15:    xsi_set_current_line(64, ng0);

LAB68:    xsi_set_current_line(65, ng0);
    xsi_set_current_line(65, ng0);
    t3 = ((char*)((ng4)));
    t4 = (t0 + 2088);
    xsi_vlogvar_assign_value(t4, t3, 0, 0, 32);

LAB69:    t2 = (t0 + 2088);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t7 = ((char*)((ng5)));
    memset(t10, 0, 8);
    xsi_vlog_signed_leq(t10, 32, t4, 32, t7, 32);
    t8 = (t10 + 4);
    t11 = *((unsigned int *)t8);
    t12 = (~(t11));
    t13 = *((unsigned int *)t10);
    t14 = (t13 & t12);
    t15 = (t14 != 0);
    if (t15 > 0)
        goto LAB70;

LAB71:    goto LAB31;

LAB17:    xsi_set_current_line(72, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    t3 = (t0 + 1208U);
    t7 = *((char **)t3);
    t11 = *((unsigned int *)t4);
    t12 = *((unsigned int *)t7);
    t13 = (t11 & t12);
    *((unsigned int *)t10) = t13;
    t3 = (t4 + 4);
    t8 = (t7 + 4);
    t9 = (t10 + 4);
    t14 = *((unsigned int *)t3);
    t15 = *((unsigned int *)t8);
    t24 = (t14 | t15);
    *((unsigned int *)t9) = t24;
    t25 = *((unsigned int *)t9);
    t26 = (t25 != 0);
    if (t26 == 1)
        goto LAB86;

LAB87:
LAB88:    t18 = (t0 + 1928);
    xsi_vlogvar_assign_value(t18, t10, 0, 0, 32);
    goto LAB31;

LAB19:    xsi_set_current_line(73, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    t3 = (t0 + 1208U);
    t7 = *((char **)t3);
    t11 = *((unsigned int *)t4);
    t12 = *((unsigned int *)t7);
    t13 = (t11 | t12);
    *((unsigned int *)t10) = t13;
    t3 = (t4 + 4);
    t8 = (t7 + 4);
    t9 = (t10 + 4);
    t14 = *((unsigned int *)t3);
    t15 = *((unsigned int *)t8);
    t24 = (t14 | t15);
    *((unsigned int *)t9) = t24;
    t25 = *((unsigned int *)t9);
    t26 = (t25 != 0);
    if (t26 == 1)
        goto LAB89;

LAB90:
LAB91:    t18 = (t0 + 1928);
    xsi_vlogvar_assign_value(t18, t10, 0, 0, 32);
    goto LAB31;

LAB21:    xsi_set_current_line(74, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    t3 = (t0 + 1208U);
    t7 = *((char **)t3);
    t11 = *((unsigned int *)t4);
    t12 = *((unsigned int *)t7);
    t13 = (t11 ^ t12);
    *((unsigned int *)t10) = t13;
    t3 = (t4 + 4);
    t8 = (t7 + 4);
    t9 = (t10 + 4);
    t14 = *((unsigned int *)t3);
    t15 = *((unsigned int *)t8);
    t24 = (t14 | t15);
    *((unsigned int *)t9) = t24;
    t25 = *((unsigned int *)t9);
    t26 = (t25 != 0);
    if (t26 == 1)
        goto LAB92;

LAB93:
LAB94:    t16 = (t0 + 1928);
    xsi_vlogvar_assign_value(t16, t10, 0, 0, 32);
    goto LAB31;

LAB23:    xsi_set_current_line(75, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    t3 = (t0 + 1208U);
    t7 = *((char **)t3);
    t11 = *((unsigned int *)t4);
    t12 = *((unsigned int *)t7);
    t13 = (t11 | t12);
    *((unsigned int *)t20) = t13;
    t3 = (t4 + 4);
    t8 = (t7 + 4);
    t9 = (t20 + 4);
    t14 = *((unsigned int *)t3);
    t15 = *((unsigned int *)t8);
    t24 = (t14 | t15);
    *((unsigned int *)t9) = t24;
    t25 = *((unsigned int *)t9);
    t26 = (t25 != 0);
    if (t26 == 1)
        goto LAB95;

LAB96:
LAB97:    memset(t10, 0, 8);
    t18 = (t10 + 4);
    t19 = (t20 + 4);
    t62 = *((unsigned int *)t20);
    t63 = (~(t62));
    *((unsigned int *)t10) = t63;
    *((unsigned int *)t18) = 0;
    if (*((unsigned int *)t19) != 0)
        goto LAB99;

LAB98:    t68 = *((unsigned int *)t10);
    *((unsigned int *)t10) = (t68 & 4294967295U);
    t69 = *((unsigned int *)t18);
    *((unsigned int *)t18) = (t69 & 4294967295U);
    t21 = (t0 + 1928);
    xsi_vlogvar_assign_value(t21, t10, 0, 0, 32);
    goto LAB31;

LAB25:    xsi_set_current_line(76, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    t3 = (t0 + 1208U);
    t7 = *((char **)t3);
    memset(t40, 0, 8);
    xsi_vlog_signed_less(t40, 32, t4, 32, t7, 32);
    t3 = ((char*)((ng1)));
    xsi_vlogtype_concat(t10, 32, 32, 2U, t3, 31, t40, 1);
    t8 = (t0 + 1928);
    xsi_vlogvar_assign_value(t8, t10, 0, 0, 32);
    goto LAB31;

LAB27:    xsi_set_current_line(77, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    t3 = (t0 + 1208U);
    t7 = *((char **)t3);
    memset(t20, 0, 8);
    t3 = (t4 + 4);
    if (*((unsigned int *)t3) != 0)
        goto LAB101;

LAB100:    t8 = (t7 + 4);
    if (*((unsigned int *)t8) != 0)
        goto LAB101;

LAB104:    if (*((unsigned int *)t4) < *((unsigned int *)t7))
        goto LAB102;

LAB103:    t16 = ((char*)((ng1)));
    xsi_vlogtype_concat(t10, 32, 32, 2U, t16, 31, t20, 1);
    t17 = (t0 + 1928);
    xsi_vlogvar_assign_value(t17, t10, 0, 0, 32);
    goto LAB31;

LAB34:    xsi_set_current_line(49, ng0);

LAB36:    xsi_set_current_line(50, ng0);
    t9 = (t0 + 2088);
    t16 = (t9 + 56U);
    t17 = *((char **)t16);
    t18 = (t0 + 1528U);
    t19 = *((char **)t18);
    memset(t20, 0, 8);
    t18 = (t17 + 4);
    if (*((unsigned int *)t18) != 0)
        goto LAB38;

LAB37:    t21 = (t19 + 4);
    if (*((unsigned int *)t21) != 0)
        goto LAB38;

LAB41:    if (*((unsigned int *)t17) < *((unsigned int *)t19))
        goto LAB39;

LAB40:    t23 = (t20 + 4);
    t24 = *((unsigned int *)t23);
    t25 = (~(t24));
    t26 = *((unsigned int *)t20);
    t27 = (t26 & t25);
    t28 = (t27 != 0);
    if (t28 > 0)
        goto LAB42;

LAB43:    xsi_set_current_line(53, ng0);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    t2 = (t0 + 1168U);
    t4 = (t2 + 72U);
    t7 = *((char **)t4);
    t8 = (t0 + 2088);
    t9 = (t8 + 56U);
    t16 = *((char **)t9);
    t17 = (t0 + 1528U);
    t18 = *((char **)t17);
    memset(t20, 0, 8);
    xsi_vlog_unsigned_minus(t20, 32, t16, 32, t18, 5);
    xsi_vlog_generic_get_index_select_value(t10, 1, t3, t7, 2, t20, 32, 2);
    t17 = (t0 + 1928);
    t19 = (t0 + 1928);
    t21 = (t19 + 72U);
    t22 = *((char **)t21);
    t23 = (t0 + 2088);
    t29 = (t23 + 56U);
    t30 = *((char **)t29);
    xsi_vlog_generic_convert_bit_index(t31, t22, 2, t30, 32, 1);
    t32 = (t31 + 4);
    t11 = *((unsigned int *)t32);
    t6 = (!(t11));
    if (t6 == 1)
        goto LAB48;

LAB49:
LAB44:    xsi_set_current_line(49, ng0);
    t2 = (t0 + 2088);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t7 = ((char*)((ng6)));
    memset(t10, 0, 8);
    xsi_vlog_signed_add(t10, 32, t4, 32, t7, 32);
    t8 = (t0 + 2088);
    xsi_vlogvar_assign_value(t8, t10, 0, 0, 32);
    goto LAB33;

LAB38:    t22 = (t20 + 4);
    *((unsigned int *)t20) = 1;
    *((unsigned int *)t22) = 1;
    goto LAB40;

LAB39:    *((unsigned int *)t20) = 1;
    goto LAB40;

LAB42:    xsi_set_current_line(50, ng0);

LAB45:    xsi_set_current_line(51, ng0);
    t29 = ((char*)((ng1)));
    t30 = (t0 + 1928);
    t32 = (t0 + 1928);
    t33 = (t32 + 72U);
    t34 = *((char **)t33);
    t35 = (t0 + 2088);
    t36 = (t35 + 56U);
    t37 = *((char **)t36);
    xsi_vlog_generic_convert_bit_index(t31, t34, 2, t37, 32, 1);
    t38 = (t31 + 4);
    t39 = *((unsigned int *)t38);
    t6 = (!(t39));
    if (t6 == 1)
        goto LAB46;

LAB47:    goto LAB44;

LAB46:    xsi_vlogvar_assign_value(t30, t29, 0, *((unsigned int *)t31), 1);
    goto LAB47;

LAB48:    xsi_vlogvar_assign_value(t17, t10, 0, *((unsigned int *)t31), 1);
    goto LAB49;

LAB52:    xsi_set_current_line(57, ng0);

LAB54:    xsi_set_current_line(58, ng0);
    t9 = (t0 + 2088);
    t16 = (t9 + 56U);
    t17 = *((char **)t16);
    t18 = ((char*)((ng8)));
    t19 = (t0 + 1528U);
    t21 = *((char **)t19);
    memset(t20, 0, 8);
    xsi_vlog_unsigned_minus(t20, 32, t18, 32, t21, 5);
    memset(t31, 0, 8);
    t19 = (t17 + 4);
    if (*((unsigned int *)t19) != 0)
        goto LAB56;

LAB55:    t22 = (t20 + 4);
    if (*((unsigned int *)t22) != 0)
        goto LAB56;

LAB59:    if (*((unsigned int *)t17) < *((unsigned int *)t20))
        goto LAB57;

LAB58:    t29 = (t31 + 4);
    t24 = *((unsigned int *)t29);
    t25 = (~(t24));
    t26 = *((unsigned int *)t31);
    t27 = (t26 & t25);
    t28 = (t27 != 0);
    if (t28 > 0)
        goto LAB60;

LAB61:    xsi_set_current_line(61, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 1928);
    t4 = (t0 + 1928);
    t7 = (t4 + 72U);
    t8 = *((char **)t7);
    t9 = (t0 + 2088);
    t16 = (t9 + 56U);
    t17 = *((char **)t16);
    xsi_vlog_generic_convert_bit_index(t10, t8, 2, t17, 32, 1);
    t18 = (t10 + 4);
    t11 = *((unsigned int *)t18);
    t6 = (!(t11));
    if (t6 == 1)
        goto LAB66;

LAB67:
LAB62:    xsi_set_current_line(57, ng0);
    t2 = (t0 + 2088);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t7 = ((char*)((ng6)));
    memset(t10, 0, 8);
    xsi_vlog_signed_add(t10, 32, t4, 32, t7, 32);
    t8 = (t0 + 2088);
    xsi_vlogvar_assign_value(t8, t10, 0, 0, 32);
    goto LAB51;

LAB56:    t23 = (t31 + 4);
    *((unsigned int *)t31) = 1;
    *((unsigned int *)t23) = 1;
    goto LAB58;

LAB57:    *((unsigned int *)t31) = 1;
    goto LAB58;

LAB60:    xsi_set_current_line(58, ng0);

LAB63:    xsi_set_current_line(59, ng0);
    t30 = (t0 + 1208U);
    t32 = *((char **)t30);
    t30 = (t0 + 1168U);
    t33 = (t30 + 72U);
    t34 = *((char **)t33);
    t35 = (t0 + 2088);
    t36 = (t35 + 56U);
    t37 = *((char **)t36);
    t38 = (t0 + 1528U);
    t41 = *((char **)t38);
    memset(t42, 0, 8);
    xsi_vlog_unsigned_add(t42, 32, t37, 32, t41, 5);
    xsi_vlog_generic_get_index_select_value(t40, 1, t32, t34, 2, t42, 32, 2);
    t38 = (t0 + 1928);
    t44 = (t0 + 1928);
    t45 = (t44 + 72U);
    t46 = *((char **)t45);
    t47 = (t0 + 2088);
    t48 = (t47 + 56U);
    t49 = *((char **)t48);
    xsi_vlog_generic_convert_bit_index(t43, t46, 2, t49, 32, 1);
    t50 = (t43 + 4);
    t39 = *((unsigned int *)t50);
    t6 = (!(t39));
    if (t6 == 1)
        goto LAB64;

LAB65:    goto LAB62;

LAB64:    xsi_vlogvar_assign_value(t38, t40, 0, *((unsigned int *)t43), 1);
    goto LAB65;

LAB66:    xsi_vlogvar_assign_value(t3, t2, 0, *((unsigned int *)t10), 1);
    goto LAB67;

LAB70:    xsi_set_current_line(65, ng0);

LAB72:    xsi_set_current_line(66, ng0);
    t9 = (t0 + 2088);
    t16 = (t9 + 56U);
    t17 = *((char **)t16);
    t18 = ((char*)((ng5)));
    t19 = (t0 + 1528U);
    t21 = *((char **)t19);
    memset(t20, 0, 8);
    xsi_vlog_unsigned_minus(t20, 32, t18, 32, t21, 5);
    memset(t31, 0, 8);
    t19 = (t17 + 4);
    if (*((unsigned int *)t19) != 0)
        goto LAB74;

LAB73:    t22 = (t20 + 4);
    if (*((unsigned int *)t22) != 0)
        goto LAB74;

LAB77:    if (*((unsigned int *)t17) > *((unsigned int *)t20))
        goto LAB76;

LAB75:    *((unsigned int *)t31) = 1;

LAB76:    t29 = (t31 + 4);
    t24 = *((unsigned int *)t29);
    t25 = (~(t24));
    t26 = *((unsigned int *)t31);
    t27 = (t26 & t25);
    t28 = (t27 != 0);
    if (t28 > 0)
        goto LAB78;

LAB79:    xsi_set_current_line(69, ng0);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    memset(t10, 0, 8);
    t2 = (t10 + 4);
    t4 = (t3 + 4);
    t11 = *((unsigned int *)t3);
    t12 = (t11 >> 31);
    t13 = (t12 & 1);
    *((unsigned int *)t10) = t13;
    t14 = *((unsigned int *)t4);
    t15 = (t14 >> 31);
    t24 = (t15 & 1);
    *((unsigned int *)t2) = t24;
    t7 = (t0 + 1928);
    t8 = (t0 + 1928);
    t9 = (t8 + 72U);
    t16 = *((char **)t9);
    t17 = (t0 + 2088);
    t18 = (t17 + 56U);
    t19 = *((char **)t18);
    xsi_vlog_generic_convert_bit_index(t20, t16, 2, t19, 32, 1);
    t21 = (t20 + 4);
    t25 = *((unsigned int *)t21);
    t6 = (!(t25));
    if (t6 == 1)
        goto LAB84;

LAB85:
LAB80:    xsi_set_current_line(65, ng0);
    t2 = (t0 + 2088);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t7 = ((char*)((ng6)));
    memset(t10, 0, 8);
    xsi_vlog_signed_add(t10, 32, t4, 32, t7, 32);
    t8 = (t0 + 2088);
    xsi_vlogvar_assign_value(t8, t10, 0, 0, 32);
    goto LAB69;

LAB74:    t23 = (t31 + 4);
    *((unsigned int *)t31) = 1;
    *((unsigned int *)t23) = 1;
    goto LAB76;

LAB78:    xsi_set_current_line(66, ng0);

LAB81:    xsi_set_current_line(67, ng0);
    t30 = (t0 + 1208U);
    t32 = *((char **)t30);
    t30 = (t0 + 1168U);
    t33 = (t30 + 72U);
    t34 = *((char **)t33);
    t35 = (t0 + 2088);
    t36 = (t35 + 56U);
    t37 = *((char **)t36);
    t38 = (t0 + 1528U);
    t41 = *((char **)t38);
    memset(t42, 0, 8);
    xsi_vlog_unsigned_add(t42, 32, t37, 32, t41, 5);
    xsi_vlog_generic_get_index_select_value(t40, 1, t32, t34, 2, t42, 32, 2);
    t38 = (t0 + 1928);
    t44 = (t0 + 1928);
    t45 = (t44 + 72U);
    t46 = *((char **)t45);
    t47 = (t0 + 2088);
    t48 = (t47 + 56U);
    t49 = *((char **)t48);
    xsi_vlog_generic_convert_bit_index(t43, t46, 2, t49, 32, 1);
    t50 = (t43 + 4);
    t39 = *((unsigned int *)t50);
    t6 = (!(t39));
    if (t6 == 1)
        goto LAB82;

LAB83:    goto LAB80;

LAB82:    xsi_vlogvar_assign_value(t38, t40, 0, *((unsigned int *)t43), 1);
    goto LAB83;

LAB84:    xsi_vlogvar_assign_value(t7, t10, 0, *((unsigned int *)t20), 1);
    goto LAB85;

LAB86:    t27 = *((unsigned int *)t10);
    t28 = *((unsigned int *)t9);
    *((unsigned int *)t10) = (t27 | t28);
    t16 = (t4 + 4);
    t17 = (t7 + 4);
    t39 = *((unsigned int *)t4);
    t51 = (~(t39));
    t52 = *((unsigned int *)t16);
    t53 = (~(t52));
    t54 = *((unsigned int *)t7);
    t55 = (~(t54));
    t56 = *((unsigned int *)t17);
    t57 = (~(t56));
    t58 = (t51 & t53);
    t59 = (t55 & t57);
    t60 = (~(t58));
    t61 = (~(t59));
    t62 = *((unsigned int *)t9);
    *((unsigned int *)t9) = (t62 & t60);
    t63 = *((unsigned int *)t9);
    *((unsigned int *)t9) = (t63 & t61);
    t64 = *((unsigned int *)t10);
    *((unsigned int *)t10) = (t64 & t60);
    t65 = *((unsigned int *)t10);
    *((unsigned int *)t10) = (t65 & t61);
    goto LAB88;

LAB89:    t27 = *((unsigned int *)t10);
    t28 = *((unsigned int *)t9);
    *((unsigned int *)t10) = (t27 | t28);
    t16 = (t4 + 4);
    t17 = (t7 + 4);
    t39 = *((unsigned int *)t16);
    t51 = (~(t39));
    t52 = *((unsigned int *)t4);
    t58 = (t52 & t51);
    t53 = *((unsigned int *)t17);
    t54 = (~(t53));
    t55 = *((unsigned int *)t7);
    t59 = (t55 & t54);
    t56 = (~(t58));
    t57 = (~(t59));
    t60 = *((unsigned int *)t9);
    *((unsigned int *)t9) = (t60 & t56);
    t61 = *((unsigned int *)t9);
    *((unsigned int *)t9) = (t61 & t57);
    goto LAB91;

LAB92:    t27 = *((unsigned int *)t10);
    t28 = *((unsigned int *)t9);
    *((unsigned int *)t10) = (t27 | t28);
    goto LAB94;

LAB95:    t27 = *((unsigned int *)t20);
    t28 = *((unsigned int *)t9);
    *((unsigned int *)t20) = (t27 | t28);
    t16 = (t4 + 4);
    t17 = (t7 + 4);
    t39 = *((unsigned int *)t16);
    t51 = (~(t39));
    t52 = *((unsigned int *)t4);
    t58 = (t52 & t51);
    t53 = *((unsigned int *)t17);
    t54 = (~(t53));
    t55 = *((unsigned int *)t7);
    t59 = (t55 & t54);
    t56 = (~(t58));
    t57 = (~(t59));
    t60 = *((unsigned int *)t9);
    *((unsigned int *)t9) = (t60 & t56);
    t61 = *((unsigned int *)t9);
    *((unsigned int *)t9) = (t61 & t57);
    goto LAB97;

LAB99:    t64 = *((unsigned int *)t10);
    t65 = *((unsigned int *)t19);
    *((unsigned int *)t10) = (t64 | t65);
    t66 = *((unsigned int *)t18);
    t67 = *((unsigned int *)t19);
    *((unsigned int *)t18) = (t66 | t67);
    goto LAB98;

LAB101:    t9 = (t20 + 4);
    *((unsigned int *)t20) = 1;
    *((unsigned int *)t9) = 1;
    goto LAB103;

LAB102:    *((unsigned int *)t20) = 1;
    goto LAB103;

}


extern void work_m_00000000004130629285_0886308060_init()
{
	static char *pe[] = {(void *)Cont_40_0,(void *)Always_44_1};
	xsi_register_didat("work_m_00000000004130629285_0886308060", "isim/mips_txt_isim_beh.exe.sim/work/m_00000000004130629285_0886308060.didat");
	xsi_register_executes(pe);
}
