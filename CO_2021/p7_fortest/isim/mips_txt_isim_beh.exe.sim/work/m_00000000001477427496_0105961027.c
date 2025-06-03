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
static const char *ng0 = "D:/ISE/p7_fortest/E.v";
static int ng1[] = {0, 0};
static int ng2[] = {5, 0};
static int ng3[] = {10, 0};
static unsigned int ng4[] = {1U, 0U};
static unsigned int ng5[] = {2U, 0U};
static int ng6[] = {4, 0};
static int ng7[] = {9, 0};
static int ng8[] = {1, 0};
static unsigned int ng9[] = {4U, 0U};
static unsigned int ng10[] = {3U, 0U};



static void Cont_42_0(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;

LAB0:    t1 = (t0 + 4928U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(42, ng0);
    t2 = (t0 + 3528);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = (t0 + 6104);
    t6 = (t5 + 56U);
    t7 = *((char **)t6);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    memcpy(t9, t4, 8);
    xsi_driver_vfirst_trans(t5, 0, 31);
    t10 = (t0 + 5992);
    *((int *)t10) = 1;

LAB1:    return;
}

static void Cont_43_1(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    char *t10;

LAB0:    t1 = (t0 + 5176U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(43, ng0);
    t2 = (t0 + 3688);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = (t0 + 6168);
    t6 = (t5 + 56U);
    t7 = *((char **)t6);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    memcpy(t9, t4, 8);
    xsi_driver_vfirst_trans(t5, 0, 31);
    t10 = (t0 + 6008);
    *((int *)t10) = 1;

LAB1:    return;
}

static void Initial_45_2(char *t0)
{
    char *t1;
    char *t2;

LAB0:    xsi_set_current_line(45, ng0);

LAB2:    xsi_set_current_line(46, ng0);
    t1 = ((char*)((ng1)));
    t2 = (t0 + 3528);
    xsi_vlogvar_assign_value(t2, t1, 0, 0, 32);
    xsi_set_current_line(46, ng0);
    t1 = ((char*)((ng1)));
    t2 = (t0 + 3688);
    xsi_vlogvar_assign_value(t2, t1, 0, 0, 32);
    xsi_set_current_line(46, ng0);
    t1 = ((char*)((ng1)));
    t2 = (t0 + 3048);
    xsi_vlogvar_assign_value(t2, t1, 0, 0, 1);
    xsi_set_current_line(46, ng0);
    t1 = ((char*)((ng2)));
    t2 = (t0 + 3208);
    xsi_vlogvar_assign_value(t2, t1, 0, 0, 32);
    xsi_set_current_line(46, ng0);
    t1 = ((char*)((ng3)));
    t2 = (t0 + 3368);
    xsi_vlogvar_assign_value(t2, t1, 0, 0, 32);
    xsi_set_current_line(46, ng0);
    t1 = ((char*)((ng1)));
    t2 = (t0 + 3848);
    xsi_vlogvar_assign_value(t2, t1, 0, 0, 32);
    xsi_set_current_line(46, ng0);
    t1 = ((char*)((ng1)));
    t2 = (t0 + 4008);
    xsi_vlogvar_assign_value(t2, t1, 0, 0, 32);

LAB1:    return;
}

static void Always_49_3(char *t0)
{
    char t13[8];
    char t21[8];
    char t32[8];
    char t41[8];
    char t49[8];
    char t89[8];
    char t91[8];
    char t94[8];
    char t114[8];
    char t130[8];
    char t131[8];
    char t139[8];
    char t179[16];
    char t180[16];
    char t181[16];
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
    unsigned int t14;
    unsigned int t15;
    unsigned int t16;
    unsigned int t17;
    unsigned int t18;
    unsigned int t19;
    unsigned int t20;
    unsigned int t22;
    unsigned int t23;
    unsigned int t24;
    unsigned int t25;
    unsigned int t26;
    char *t27;
    char *t28;
    unsigned int t29;
    unsigned int t30;
    unsigned int t31;
    char *t33;
    char *t34;
    unsigned int t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    unsigned int t39;
    char *t40;
    char *t42;
    unsigned int t43;
    unsigned int t44;
    unsigned int t45;
    unsigned int t46;
    unsigned int t47;
    char *t48;
    unsigned int t50;
    unsigned int t51;
    unsigned int t52;
    char *t53;
    char *t54;
    char *t55;
    unsigned int t56;
    unsigned int t57;
    unsigned int t58;
    unsigned int t59;
    unsigned int t60;
    unsigned int t61;
    unsigned int t62;
    char *t63;
    char *t64;
    unsigned int t65;
    unsigned int t66;
    unsigned int t67;
    unsigned int t68;
    unsigned int t69;
    unsigned int t70;
    unsigned int t71;
    unsigned int t72;
    int t73;
    int t74;
    unsigned int t75;
    unsigned int t76;
    unsigned int t77;
    unsigned int t78;
    unsigned int t79;
    unsigned int t80;
    char *t81;
    unsigned int t82;
    unsigned int t83;
    unsigned int t84;
    unsigned int t85;
    unsigned int t86;
    char *t87;
    char *t88;
    char *t90;
    char *t92;
    char *t93;
    char *t95;
    char *t96;
    char *t97;
    char *t98;
    char *t99;
    unsigned int t100;
    unsigned int t101;
    unsigned int t102;
    unsigned int t103;
    unsigned int t104;
    unsigned int t105;
    int t106;
    int t107;
    unsigned int t108;
    unsigned int t109;
    unsigned int t110;
    unsigned int t111;
    unsigned int t112;
    unsigned int t113;
    char *t115;
    unsigned int t116;
    unsigned int t117;
    unsigned int t118;
    unsigned int t119;
    unsigned int t120;
    char *t121;
    char *t122;
    unsigned int t123;
    unsigned int t124;
    unsigned int t125;
    char *t126;
    char *t127;
    char *t128;
    char *t129;
    char *t132;
    unsigned int t133;
    unsigned int t134;
    unsigned int t135;
    unsigned int t136;
    unsigned int t137;
    char *t138;
    unsigned int t140;
    unsigned int t141;
    unsigned int t142;
    char *t143;
    char *t144;
    char *t145;
    unsigned int t146;
    unsigned int t147;
    unsigned int t148;
    unsigned int t149;
    unsigned int t150;
    unsigned int t151;
    unsigned int t152;
    char *t153;
    char *t154;
    unsigned int t155;
    unsigned int t156;
    unsigned int t157;
    unsigned int t158;
    unsigned int t159;
    unsigned int t160;
    unsigned int t161;
    unsigned int t162;
    int t163;
    int t164;
    unsigned int t165;
    unsigned int t166;
    unsigned int t167;
    unsigned int t168;
    unsigned int t169;
    unsigned int t170;
    char *t171;
    unsigned int t172;
    unsigned int t173;
    unsigned int t174;
    unsigned int t175;
    unsigned int t176;
    char *t177;
    char *t178;

LAB0:    t1 = (t0 + 5672U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(49, ng0);
    t2 = (t0 + 6024);
    *((int *)t2) = 1;
    t3 = (t0 + 5704);
    *((char **)t3) = t2;
    *((char **)t1) = &&LAB4;

LAB1:    return;
LAB4:    xsi_set_current_line(49, ng0);

LAB5:    xsi_set_current_line(50, ng0);
    t4 = (t0 + 1368U);
    t5 = *((char **)t4);
    t4 = (t5 + 4);
    t6 = *((unsigned int *)t4);
    t7 = (~(t6));
    t8 = *((unsigned int *)t5);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB6;

LAB7:    xsi_set_current_line(59, ng0);

LAB10:    xsi_set_current_line(61, ng0);
    t2 = (t0 + 2488U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng4)));
    memset(t13, 0, 8);
    t4 = (t3 + 4);
    t5 = (t2 + 4);
    t6 = *((unsigned int *)t3);
    t7 = *((unsigned int *)t2);
    t8 = (t6 ^ t7);
    t9 = *((unsigned int *)t4);
    t10 = *((unsigned int *)t5);
    t14 = (t9 ^ t10);
    t15 = (t8 | t14);
    t16 = *((unsigned int *)t4);
    t17 = *((unsigned int *)t5);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB14;

LAB11:    if (t18 != 0)
        goto LAB13;

LAB12:    *((unsigned int *)t13) = 1;

LAB14:    memset(t21, 0, 8);
    t12 = (t13 + 4);
    t22 = *((unsigned int *)t12);
    t23 = (~(t22));
    t24 = *((unsigned int *)t13);
    t25 = (t24 & t23);
    t26 = (t25 & 1U);
    if (t26 != 0)
        goto LAB15;

LAB16:    if (*((unsigned int *)t12) != 0)
        goto LAB17;

LAB18:    t28 = (t21 + 4);
    t29 = *((unsigned int *)t21);
    t30 = *((unsigned int *)t28);
    t31 = (t29 || t30);
    if (t31 > 0)
        goto LAB19;

LAB20:    memcpy(t49, t21, 8);

LAB21:    t81 = (t49 + 4);
    t82 = *((unsigned int *)t81);
    t83 = (~(t82));
    t84 = *((unsigned int *)t49);
    t85 = (t84 & t83);
    t86 = (t85 != 0);
    if (t86 > 0)
        goto LAB33;

LAB34:    xsi_set_current_line(63, ng0);
    t2 = (t0 + 2488U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng5)));
    memset(t13, 0, 8);
    t4 = (t3 + 4);
    t5 = (t2 + 4);
    t6 = *((unsigned int *)t3);
    t7 = *((unsigned int *)t2);
    t8 = (t6 ^ t7);
    t9 = *((unsigned int *)t4);
    t10 = *((unsigned int *)t5);
    t14 = (t9 ^ t10);
    t15 = (t8 | t14);
    t16 = *((unsigned int *)t4);
    t17 = *((unsigned int *)t5);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB39;

LAB36:    if (t18 != 0)
        goto LAB38;

LAB37:    *((unsigned int *)t13) = 1;

LAB39:    memset(t21, 0, 8);
    t12 = (t13 + 4);
    t22 = *((unsigned int *)t12);
    t23 = (~(t22));
    t24 = *((unsigned int *)t13);
    t25 = (t24 & t23);
    t26 = (t25 & 1U);
    if (t26 != 0)
        goto LAB40;

LAB41:    if (*((unsigned int *)t12) != 0)
        goto LAB42;

LAB43:    t28 = (t21 + 4);
    t29 = *((unsigned int *)t21);
    t30 = *((unsigned int *)t28);
    t31 = (t29 || t30);
    if (t31 > 0)
        goto LAB44;

LAB45:    memcpy(t49, t21, 8);

LAB46:    t81 = (t49 + 4);
    t82 = *((unsigned int *)t81);
    t83 = (~(t82));
    t84 = *((unsigned int *)t49);
    t85 = (t84 & t83);
    t86 = (t85 != 0);
    if (t86 > 0)
        goto LAB58;

LAB59:
LAB60:
LAB35:    xsi_set_current_line(67, ng0);
    t2 = (t0 + 1528U);
    t3 = *((char **)t2);
    memset(t13, 0, 8);
    t2 = (t3 + 4);
    t6 = *((unsigned int *)t2);
    t7 = (~(t6));
    t8 = *((unsigned int *)t3);
    t9 = (t8 & t7);
    t10 = (t9 & 1U);
    if (t10 != 0)
        goto LAB61;

LAB62:    if (*((unsigned int *)t2) != 0)
        goto LAB63;

LAB64:    t5 = (t13 + 4);
    t14 = *((unsigned int *)t13);
    t15 = *((unsigned int *)t5);
    t16 = (t14 || t15);
    if (t16 > 0)
        goto LAB65;

LAB66:    memcpy(t94, t13, 8);

LAB67:    memset(t114, 0, 8);
    t115 = (t94 + 4);
    t116 = *((unsigned int *)t115);
    t117 = (~(t116));
    t118 = *((unsigned int *)t94);
    t119 = (t118 & t117);
    t120 = (t119 & 1U);
    if (t120 != 0)
        goto LAB89;

LAB90:    if (*((unsigned int *)t115) != 0)
        goto LAB91;

LAB92:    t122 = (t114 + 4);
    t123 = *((unsigned int *)t114);
    t124 = *((unsigned int *)t122);
    t125 = (t123 || t124);
    if (t125 > 0)
        goto LAB93;

LAB94:    memcpy(t139, t114, 8);

LAB95:    t171 = (t139 + 4);
    t172 = *((unsigned int *)t171);
    t173 = (~(t172));
    t174 = *((unsigned int *)t139);
    t175 = (t174 & t173);
    t176 = (t175 != 0);
    if (t176 > 0)
        goto LAB103;

LAB104:    xsi_set_current_line(71, ng0);
    t2 = (t0 + 3208);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng1)));
    memset(t13, 0, 8);
    xsi_vlog_signed_equal(t13, 32, t4, 32, t5, 32);
    t11 = (t13 + 4);
    t6 = *((unsigned int *)t11);
    t7 = (~(t6));
    t8 = *((unsigned int *)t13);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB107;

LAB108:    xsi_set_current_line(77, ng0);
    t2 = (t0 + 3208);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng2)));
    memset(t13, 0, 8);
    xsi_vlog_signed_not_equal(t13, 32, t4, 32, t5, 32);
    t11 = (t13 + 4);
    t6 = *((unsigned int *)t11);
    t7 = (~(t6));
    t8 = *((unsigned int *)t13);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB111;

LAB112:    xsi_set_current_line(81, ng0);
    t2 = (t0 + 2648U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng5)));
    memset(t13, 0, 8);
    t4 = (t3 + 4);
    t5 = (t2 + 4);
    t6 = *((unsigned int *)t3);
    t7 = *((unsigned int *)t2);
    t8 = (t6 ^ t7);
    t9 = *((unsigned int *)t4);
    t10 = *((unsigned int *)t5);
    t14 = (t9 ^ t10);
    t15 = (t8 | t14);
    t16 = *((unsigned int *)t4);
    t17 = *((unsigned int *)t5);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB118;

LAB115:    if (t18 != 0)
        goto LAB117;

LAB116:    *((unsigned int *)t13) = 1;

LAB118:    memset(t21, 0, 8);
    t12 = (t13 + 4);
    t22 = *((unsigned int *)t12);
    t23 = (~(t22));
    t24 = *((unsigned int *)t13);
    t25 = (t24 & t23);
    t26 = (t25 & 1U);
    if (t26 != 0)
        goto LAB119;

LAB120:    if (*((unsigned int *)t12) != 0)
        goto LAB121;

LAB122:    t28 = (t21 + 4);
    t29 = *((unsigned int *)t21);
    t30 = *((unsigned int *)t28);
    t31 = (t29 || t30);
    if (t31 > 0)
        goto LAB123;

LAB124:    memcpy(t49, t21, 8);

LAB125:    t81 = (t49 + 4);
    t82 = *((unsigned int *)t81);
    t83 = (~(t82));
    t84 = *((unsigned int *)t49);
    t85 = (t84 & t83);
    t86 = (t85 != 0);
    if (t86 > 0)
        goto LAB137;

LAB138:    xsi_set_current_line(86, ng0);
    t2 = (t0 + 2648U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng4)));
    memset(t13, 0, 8);
    t4 = (t3 + 4);
    t5 = (t2 + 4);
    t6 = *((unsigned int *)t3);
    t7 = *((unsigned int *)t2);
    t8 = (t6 ^ t7);
    t9 = *((unsigned int *)t4);
    t10 = *((unsigned int *)t5);
    t14 = (t9 ^ t10);
    t15 = (t8 | t14);
    t16 = *((unsigned int *)t4);
    t17 = *((unsigned int *)t5);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB144;

LAB141:    if (t18 != 0)
        goto LAB143;

LAB142:    *((unsigned int *)t13) = 1;

LAB144:    memset(t21, 0, 8);
    t12 = (t13 + 4);
    t22 = *((unsigned int *)t12);
    t23 = (~(t22));
    t24 = *((unsigned int *)t13);
    t25 = (t24 & t23);
    t26 = (t25 & 1U);
    if (t26 != 0)
        goto LAB145;

LAB146:    if (*((unsigned int *)t12) != 0)
        goto LAB147;

LAB148:    t28 = (t21 + 4);
    t29 = *((unsigned int *)t21);
    t30 = *((unsigned int *)t28);
    t31 = (t29 || t30);
    if (t31 > 0)
        goto LAB149;

LAB150:    memcpy(t49, t21, 8);

LAB151:    t81 = (t49 + 4);
    t82 = *((unsigned int *)t81);
    t83 = (~(t82));
    t84 = *((unsigned int *)t49);
    t85 = (t84 & t83);
    t86 = (t85 != 0);
    if (t86 > 0)
        goto LAB163;

LAB164:
LAB165:
LAB139:
LAB113:
LAB109:
LAB105:    xsi_set_current_line(93, ng0);
    t2 = (t0 + 1528U);
    t3 = *((char **)t2);
    memset(t13, 0, 8);
    t2 = (t3 + 4);
    t6 = *((unsigned int *)t2);
    t7 = (~(t6));
    t8 = *((unsigned int *)t3);
    t9 = (t8 & t7);
    t10 = (t9 & 1U);
    if (t10 != 0)
        goto LAB167;

LAB168:    if (*((unsigned int *)t2) != 0)
        goto LAB169;

LAB170:    t5 = (t13 + 4);
    t14 = *((unsigned int *)t13);
    t15 = *((unsigned int *)t5);
    t16 = (t14 || t15);
    if (t16 > 0)
        goto LAB171;

LAB172:    memcpy(t94, t13, 8);

LAB173:    memset(t114, 0, 8);
    t115 = (t94 + 4);
    t116 = *((unsigned int *)t115);
    t117 = (~(t116));
    t118 = *((unsigned int *)t94);
    t119 = (t118 & t117);
    t120 = (t119 & 1U);
    if (t120 != 0)
        goto LAB195;

LAB196:    if (*((unsigned int *)t115) != 0)
        goto LAB197;

LAB198:    t122 = (t114 + 4);
    t123 = *((unsigned int *)t114);
    t124 = *((unsigned int *)t122);
    t125 = (t123 || t124);
    if (t125 > 0)
        goto LAB199;

LAB200:    memcpy(t139, t114, 8);

LAB201:    t171 = (t139 + 4);
    t172 = *((unsigned int *)t171);
    t173 = (~(t172));
    t174 = *((unsigned int *)t139);
    t175 = (t174 & t173);
    t176 = (t175 != 0);
    if (t176 > 0)
        goto LAB209;

LAB210:    xsi_set_current_line(97, ng0);
    t2 = (t0 + 3368);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng1)));
    memset(t13, 0, 8);
    xsi_vlog_signed_equal(t13, 32, t4, 32, t5, 32);
    t11 = (t13 + 4);
    t6 = *((unsigned int *)t11);
    t7 = (~(t6));
    t8 = *((unsigned int *)t13);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB213;

LAB214:    xsi_set_current_line(103, ng0);
    t2 = (t0 + 3368);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng3)));
    memset(t13, 0, 8);
    xsi_vlog_signed_not_equal(t13, 32, t4, 32, t5, 32);
    t11 = (t13 + 4);
    t6 = *((unsigned int *)t11);
    t7 = (~(t6));
    t8 = *((unsigned int *)t13);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB217;

LAB218:    xsi_set_current_line(107, ng0);
    t2 = (t0 + 2648U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng9)));
    memset(t13, 0, 8);
    t4 = (t3 + 4);
    t5 = (t2 + 4);
    t6 = *((unsigned int *)t3);
    t7 = *((unsigned int *)t2);
    t8 = (t6 ^ t7);
    t9 = *((unsigned int *)t4);
    t10 = *((unsigned int *)t5);
    t14 = (t9 ^ t10);
    t15 = (t8 | t14);
    t16 = *((unsigned int *)t4);
    t17 = *((unsigned int *)t5);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB224;

LAB221:    if (t18 != 0)
        goto LAB223;

LAB222:    *((unsigned int *)t13) = 1;

LAB224:    memset(t21, 0, 8);
    t12 = (t13 + 4);
    t22 = *((unsigned int *)t12);
    t23 = (~(t22));
    t24 = *((unsigned int *)t13);
    t25 = (t24 & t23);
    t26 = (t25 & 1U);
    if (t26 != 0)
        goto LAB225;

LAB226:    if (*((unsigned int *)t12) != 0)
        goto LAB227;

LAB228:    t28 = (t21 + 4);
    t29 = *((unsigned int *)t21);
    t30 = *((unsigned int *)t28);
    t31 = (t29 || t30);
    if (t31 > 0)
        goto LAB229;

LAB230:    memcpy(t49, t21, 8);

LAB231:    t81 = (t49 + 4);
    t82 = *((unsigned int *)t81);
    t83 = (~(t82));
    t84 = *((unsigned int *)t49);
    t85 = (t84 & t83);
    t86 = (t85 != 0);
    if (t86 > 0)
        goto LAB243;

LAB244:    xsi_set_current_line(113, ng0);
    t2 = (t0 + 2648U);
    t3 = *((char **)t2);
    t2 = ((char*)((ng10)));
    memset(t13, 0, 8);
    t4 = (t3 + 4);
    t5 = (t2 + 4);
    t6 = *((unsigned int *)t3);
    t7 = *((unsigned int *)t2);
    t8 = (t6 ^ t7);
    t9 = *((unsigned int *)t4);
    t10 = *((unsigned int *)t5);
    t14 = (t9 ^ t10);
    t15 = (t8 | t14);
    t16 = *((unsigned int *)t4);
    t17 = *((unsigned int *)t5);
    t18 = (t16 | t17);
    t19 = (~(t18));
    t20 = (t15 & t19);
    if (t20 != 0)
        goto LAB250;

LAB247:    if (t18 != 0)
        goto LAB249;

LAB248:    *((unsigned int *)t13) = 1;

LAB250:    memset(t21, 0, 8);
    t12 = (t13 + 4);
    t22 = *((unsigned int *)t12);
    t23 = (~(t22));
    t24 = *((unsigned int *)t13);
    t25 = (t24 & t23);
    t26 = (t25 & 1U);
    if (t26 != 0)
        goto LAB251;

LAB252:    if (*((unsigned int *)t12) != 0)
        goto LAB253;

LAB254:    t28 = (t21 + 4);
    t29 = *((unsigned int *)t21);
    t30 = *((unsigned int *)t28);
    t31 = (t29 || t30);
    if (t31 > 0)
        goto LAB255;

LAB256:    memcpy(t49, t21, 8);

LAB257:    t81 = (t49 + 4);
    t82 = *((unsigned int *)t81);
    t83 = (~(t82));
    t84 = *((unsigned int *)t49);
    t85 = (t84 & t83);
    t86 = (t85 != 0);
    if (t86 > 0)
        goto LAB269;

LAB270:
LAB271:
LAB245:
LAB219:
LAB215:
LAB211:
LAB8:    goto LAB2;

LAB6:    xsi_set_current_line(50, ng0);

LAB9:    xsi_set_current_line(51, ng0);
    t11 = ((char*)((ng1)));
    t12 = (t0 + 3528);
    xsi_vlogvar_assign_value(t12, t11, 0, 0, 32);
    xsi_set_current_line(52, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 3688);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    xsi_set_current_line(53, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 3848);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    xsi_set_current_line(54, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 4008);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    xsi_set_current_line(55, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 3048);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 1);
    xsi_set_current_line(56, ng0);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 3208);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    xsi_set_current_line(57, ng0);
    t2 = ((char*)((ng3)));
    t3 = (t0 + 3368);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    goto LAB8;

LAB13:    t11 = (t13 + 4);
    *((unsigned int *)t13) = 1;
    *((unsigned int *)t11) = 1;
    goto LAB14;

LAB15:    *((unsigned int *)t21) = 1;
    goto LAB18;

LAB17:    t27 = (t21 + 4);
    *((unsigned int *)t21) = 1;
    *((unsigned int *)t27) = 1;
    goto LAB18;

LAB19:    t33 = (t0 + 1528U);
    t34 = *((char **)t33);
    memset(t32, 0, 8);
    t33 = (t34 + 4);
    t35 = *((unsigned int *)t33);
    t36 = (~(t35));
    t37 = *((unsigned int *)t34);
    t38 = (t37 & t36);
    t39 = (t38 & 1U);
    if (t39 != 0)
        goto LAB25;

LAB23:    if (*((unsigned int *)t33) == 0)
        goto LAB22;

LAB24:    t40 = (t32 + 4);
    *((unsigned int *)t32) = 1;
    *((unsigned int *)t40) = 1;

LAB25:    memset(t41, 0, 8);
    t42 = (t32 + 4);
    t43 = *((unsigned int *)t42);
    t44 = (~(t43));
    t45 = *((unsigned int *)t32);
    t46 = (t45 & t44);
    t47 = (t46 & 1U);
    if (t47 != 0)
        goto LAB26;

LAB27:    if (*((unsigned int *)t42) != 0)
        goto LAB28;

LAB29:    t50 = *((unsigned int *)t21);
    t51 = *((unsigned int *)t41);
    t52 = (t50 & t51);
    *((unsigned int *)t49) = t52;
    t53 = (t21 + 4);
    t54 = (t41 + 4);
    t55 = (t49 + 4);
    t56 = *((unsigned int *)t53);
    t57 = *((unsigned int *)t54);
    t58 = (t56 | t57);
    *((unsigned int *)t55) = t58;
    t59 = *((unsigned int *)t55);
    t60 = (t59 != 0);
    if (t60 == 1)
        goto LAB30;

LAB31:
LAB32:    goto LAB21;

LAB22:    *((unsigned int *)t32) = 1;
    goto LAB25;

LAB26:    *((unsigned int *)t41) = 1;
    goto LAB29;

LAB28:    t48 = (t41 + 4);
    *((unsigned int *)t41) = 1;
    *((unsigned int *)t48) = 1;
    goto LAB29;

LAB30:    t61 = *((unsigned int *)t49);
    t62 = *((unsigned int *)t55);
    *((unsigned int *)t49) = (t61 | t62);
    t63 = (t21 + 4);
    t64 = (t41 + 4);
    t65 = *((unsigned int *)t21);
    t66 = (~(t65));
    t67 = *((unsigned int *)t63);
    t68 = (~(t67));
    t69 = *((unsigned int *)t41);
    t70 = (~(t69));
    t71 = *((unsigned int *)t64);
    t72 = (~(t71));
    t73 = (t66 & t68);
    t74 = (t70 & t72);
    t75 = (~(t73));
    t76 = (~(t74));
    t77 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t77 & t75);
    t78 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t78 & t76);
    t79 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t79 & t75);
    t80 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t80 & t76);
    goto LAB32;

LAB33:    xsi_set_current_line(62, ng0);
    t87 = (t0 + 1688U);
    t88 = *((char **)t87);
    t87 = (t0 + 3528);
    xsi_vlogvar_assign_value(t87, t88, 0, 0, 32);
    goto LAB35;

LAB38:    t11 = (t13 + 4);
    *((unsigned int *)t13) = 1;
    *((unsigned int *)t11) = 1;
    goto LAB39;

LAB40:    *((unsigned int *)t21) = 1;
    goto LAB43;

LAB42:    t27 = (t21 + 4);
    *((unsigned int *)t21) = 1;
    *((unsigned int *)t27) = 1;
    goto LAB43;

LAB44:    t33 = (t0 + 1528U);
    t34 = *((char **)t33);
    memset(t32, 0, 8);
    t33 = (t34 + 4);
    t35 = *((unsigned int *)t33);
    t36 = (~(t35));
    t37 = *((unsigned int *)t34);
    t38 = (t37 & t36);
    t39 = (t38 & 1U);
    if (t39 != 0)
        goto LAB50;

LAB48:    if (*((unsigned int *)t33) == 0)
        goto LAB47;

LAB49:    t40 = (t32 + 4);
    *((unsigned int *)t32) = 1;
    *((unsigned int *)t40) = 1;

LAB50:    memset(t41, 0, 8);
    t42 = (t32 + 4);
    t43 = *((unsigned int *)t42);
    t44 = (~(t43));
    t45 = *((unsigned int *)t32);
    t46 = (t45 & t44);
    t47 = (t46 & 1U);
    if (t47 != 0)
        goto LAB51;

LAB52:    if (*((unsigned int *)t42) != 0)
        goto LAB53;

LAB54:    t50 = *((unsigned int *)t21);
    t51 = *((unsigned int *)t41);
    t52 = (t50 & t51);
    *((unsigned int *)t49) = t52;
    t53 = (t21 + 4);
    t54 = (t41 + 4);
    t55 = (t49 + 4);
    t56 = *((unsigned int *)t53);
    t57 = *((unsigned int *)t54);
    t58 = (t56 | t57);
    *((unsigned int *)t55) = t58;
    t59 = *((unsigned int *)t55);
    t60 = (t59 != 0);
    if (t60 == 1)
        goto LAB55;

LAB56:
LAB57:    goto LAB46;

LAB47:    *((unsigned int *)t32) = 1;
    goto LAB50;

LAB51:    *((unsigned int *)t41) = 1;
    goto LAB54;

LAB53:    t48 = (t41 + 4);
    *((unsigned int *)t41) = 1;
    *((unsigned int *)t48) = 1;
    goto LAB54;

LAB55:    t61 = *((unsigned int *)t49);
    t62 = *((unsigned int *)t55);
    *((unsigned int *)t49) = (t61 | t62);
    t63 = (t21 + 4);
    t64 = (t41 + 4);
    t65 = *((unsigned int *)t21);
    t66 = (~(t65));
    t67 = *((unsigned int *)t63);
    t68 = (~(t67));
    t69 = *((unsigned int *)t41);
    t70 = (~(t69));
    t71 = *((unsigned int *)t64);
    t72 = (~(t71));
    t73 = (t66 & t68);
    t74 = (t70 & t72);
    t75 = (~(t73));
    t76 = (~(t74));
    t77 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t77 & t75);
    t78 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t78 & t76);
    t79 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t79 & t75);
    t80 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t80 & t76);
    goto LAB57;

LAB58:    xsi_set_current_line(64, ng0);
    t87 = (t0 + 1688U);
    t88 = *((char **)t87);
    t87 = (t0 + 3688);
    xsi_vlogvar_assign_value(t87, t88, 0, 0, 32);
    goto LAB60;

LAB61:    *((unsigned int *)t13) = 1;
    goto LAB64;

LAB63:    t4 = (t13 + 4);
    *((unsigned int *)t13) = 1;
    *((unsigned int *)t4) = 1;
    goto LAB64;

LAB65:    t11 = (t0 + 3208);
    t12 = (t11 + 56U);
    t27 = *((char **)t12);
    t28 = ((char*)((ng6)));
    memset(t21, 0, 8);
    xsi_vlog_signed_equal(t21, 32, t27, 32, t28, 32);
    memset(t32, 0, 8);
    t33 = (t21 + 4);
    t17 = *((unsigned int *)t33);
    t18 = (~(t17));
    t19 = *((unsigned int *)t21);
    t20 = (t19 & t18);
    t22 = (t20 & 1U);
    if (t22 != 0)
        goto LAB68;

LAB69:    if (*((unsigned int *)t33) != 0)
        goto LAB70;

LAB71:    t40 = (t32 + 4);
    t23 = *((unsigned int *)t32);
    t24 = (!(t23));
    t25 = *((unsigned int *)t40);
    t26 = (t24 || t25);
    if (t26 > 0)
        goto LAB72;

LAB73:    memcpy(t89, t32, 8);

LAB74:    memset(t91, 0, 8);
    t92 = (t89 + 4);
    t67 = *((unsigned int *)t92);
    t68 = (~(t67));
    t69 = *((unsigned int *)t89);
    t70 = (t69 & t68);
    t71 = (t70 & 1U);
    if (t71 != 0)
        goto LAB82;

LAB83:    if (*((unsigned int *)t92) != 0)
        goto LAB84;

LAB85:    t72 = *((unsigned int *)t13);
    t75 = *((unsigned int *)t91);
    t76 = (t72 & t75);
    *((unsigned int *)t94) = t76;
    t95 = (t13 + 4);
    t96 = (t91 + 4);
    t97 = (t94 + 4);
    t77 = *((unsigned int *)t95);
    t78 = *((unsigned int *)t96);
    t79 = (t77 | t78);
    *((unsigned int *)t97) = t79;
    t80 = *((unsigned int *)t97);
    t82 = (t80 != 0);
    if (t82 == 1)
        goto LAB86;

LAB87:
LAB88:    goto LAB67;

LAB68:    *((unsigned int *)t32) = 1;
    goto LAB71;

LAB70:    t34 = (t32 + 4);
    *((unsigned int *)t32) = 1;
    *((unsigned int *)t34) = 1;
    goto LAB71;

LAB72:    t42 = (t0 + 3368);
    t48 = (t42 + 56U);
    t53 = *((char **)t48);
    t54 = ((char*)((ng7)));
    memset(t41, 0, 8);
    xsi_vlog_signed_equal(t41, 32, t53, 32, t54, 32);
    memset(t49, 0, 8);
    t55 = (t41 + 4);
    t29 = *((unsigned int *)t55);
    t30 = (~(t29));
    t31 = *((unsigned int *)t41);
    t35 = (t31 & t30);
    t36 = (t35 & 1U);
    if (t36 != 0)
        goto LAB75;

LAB76:    if (*((unsigned int *)t55) != 0)
        goto LAB77;

LAB78:    t37 = *((unsigned int *)t32);
    t38 = *((unsigned int *)t49);
    t39 = (t37 | t38);
    *((unsigned int *)t89) = t39;
    t64 = (t32 + 4);
    t81 = (t49 + 4);
    t87 = (t89 + 4);
    t43 = *((unsigned int *)t64);
    t44 = *((unsigned int *)t81);
    t45 = (t43 | t44);
    *((unsigned int *)t87) = t45;
    t46 = *((unsigned int *)t87);
    t47 = (t46 != 0);
    if (t47 == 1)
        goto LAB79;

LAB80:
LAB81:    goto LAB74;

LAB75:    *((unsigned int *)t49) = 1;
    goto LAB78;

LAB77:    t63 = (t49 + 4);
    *((unsigned int *)t49) = 1;
    *((unsigned int *)t63) = 1;
    goto LAB78;

LAB79:    t50 = *((unsigned int *)t89);
    t51 = *((unsigned int *)t87);
    *((unsigned int *)t89) = (t50 | t51);
    t88 = (t32 + 4);
    t90 = (t49 + 4);
    t52 = *((unsigned int *)t88);
    t56 = (~(t52));
    t57 = *((unsigned int *)t32);
    t73 = (t57 & t56);
    t58 = *((unsigned int *)t90);
    t59 = (~(t58));
    t60 = *((unsigned int *)t49);
    t74 = (t60 & t59);
    t61 = (~(t73));
    t62 = (~(t74));
    t65 = *((unsigned int *)t87);
    *((unsigned int *)t87) = (t65 & t61);
    t66 = *((unsigned int *)t87);
    *((unsigned int *)t87) = (t66 & t62);
    goto LAB81;

LAB82:    *((unsigned int *)t91) = 1;
    goto LAB85;

LAB84:    t93 = (t91 + 4);
    *((unsigned int *)t91) = 1;
    *((unsigned int *)t93) = 1;
    goto LAB85;

LAB86:    t83 = *((unsigned int *)t94);
    t84 = *((unsigned int *)t97);
    *((unsigned int *)t94) = (t83 | t84);
    t98 = (t13 + 4);
    t99 = (t91 + 4);
    t85 = *((unsigned int *)t13);
    t86 = (~(t85));
    t100 = *((unsigned int *)t98);
    t101 = (~(t100));
    t102 = *((unsigned int *)t91);
    t103 = (~(t102));
    t104 = *((unsigned int *)t99);
    t105 = (~(t104));
    t106 = (t86 & t101);
    t107 = (t103 & t105);
    t108 = (~(t106));
    t109 = (~(t107));
    t110 = *((unsigned int *)t97);
    *((unsigned int *)t97) = (t110 & t108);
    t111 = *((unsigned int *)t97);
    *((unsigned int *)t97) = (t111 & t109);
    t112 = *((unsigned int *)t94);
    *((unsigned int *)t94) = (t112 & t108);
    t113 = *((unsigned int *)t94);
    *((unsigned int *)t94) = (t113 & t109);
    goto LAB88;

LAB89:    *((unsigned int *)t114) = 1;
    goto LAB92;

LAB91:    t121 = (t114 + 4);
    *((unsigned int *)t114) = 1;
    *((unsigned int *)t121) = 1;
    goto LAB92;

LAB93:    t126 = (t0 + 3208);
    t127 = (t126 + 56U);
    t128 = *((char **)t127);
    t129 = ((char*)((ng2)));
    memset(t130, 0, 8);
    xsi_vlog_signed_not_equal(t130, 32, t128, 32, t129, 32);
    memset(t131, 0, 8);
    t132 = (t130 + 4);
    t133 = *((unsigned int *)t132);
    t134 = (~(t133));
    t135 = *((unsigned int *)t130);
    t136 = (t135 & t134);
    t137 = (t136 & 1U);
    if (t137 != 0)
        goto LAB96;

LAB97:    if (*((unsigned int *)t132) != 0)
        goto LAB98;

LAB99:    t140 = *((unsigned int *)t114);
    t141 = *((unsigned int *)t131);
    t142 = (t140 & t141);
    *((unsigned int *)t139) = t142;
    t143 = (t114 + 4);
    t144 = (t131 + 4);
    t145 = (t139 + 4);
    t146 = *((unsigned int *)t143);
    t147 = *((unsigned int *)t144);
    t148 = (t146 | t147);
    *((unsigned int *)t145) = t148;
    t149 = *((unsigned int *)t145);
    t150 = (t149 != 0);
    if (t150 == 1)
        goto LAB100;

LAB101:
LAB102:    goto LAB95;

LAB96:    *((unsigned int *)t131) = 1;
    goto LAB99;

LAB98:    t138 = (t131 + 4);
    *((unsigned int *)t131) = 1;
    *((unsigned int *)t138) = 1;
    goto LAB99;

LAB100:    t151 = *((unsigned int *)t139);
    t152 = *((unsigned int *)t145);
    *((unsigned int *)t139) = (t151 | t152);
    t153 = (t114 + 4);
    t154 = (t131 + 4);
    t155 = *((unsigned int *)t114);
    t156 = (~(t155));
    t157 = *((unsigned int *)t153);
    t158 = (~(t157));
    t159 = *((unsigned int *)t131);
    t160 = (~(t159));
    t161 = *((unsigned int *)t154);
    t162 = (~(t161));
    t163 = (t156 & t158);
    t164 = (t160 & t162);
    t165 = (~(t163));
    t166 = (~(t164));
    t167 = *((unsigned int *)t145);
    *((unsigned int *)t145) = (t167 & t165);
    t168 = *((unsigned int *)t145);
    *((unsigned int *)t145) = (t168 & t166);
    t169 = *((unsigned int *)t139);
    *((unsigned int *)t139) = (t169 & t165);
    t170 = *((unsigned int *)t139);
    *((unsigned int *)t139) = (t170 & t166);
    goto LAB102;

LAB103:    xsi_set_current_line(67, ng0);

LAB106:    xsi_set_current_line(68, ng0);
    t177 = ((char*)((ng1)));
    t178 = (t0 + 3048);
    xsi_vlogvar_assign_value(t178, t177, 0, 0, 1);
    xsi_set_current_line(69, ng0);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 3208);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    goto LAB105;

LAB107:    xsi_set_current_line(71, ng0);

LAB110:    xsi_set_current_line(72, ng0);
    t12 = (t0 + 3848);
    t27 = (t12 + 56U);
    t28 = *((char **)t27);
    t33 = (t0 + 3528);
    xsi_vlogvar_assign_value(t33, t28, 0, 0, 32);
    xsi_set_current_line(73, ng0);
    t2 = (t0 + 4008);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = (t0 + 3688);
    xsi_vlogvar_assign_value(t5, t4, 0, 0, 32);
    xsi_set_current_line(74, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 3048);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 1);
    xsi_set_current_line(75, ng0);
    t2 = ((char*)((ng2)));
    t3 = (t0 + 3208);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    goto LAB109;

LAB111:    xsi_set_current_line(77, ng0);

LAB114:    xsi_set_current_line(78, ng0);
    t12 = ((char*)((ng8)));
    t27 = (t0 + 3048);
    xsi_vlogvar_assign_value(t27, t12, 0, 0, 1);
    xsi_set_current_line(79, ng0);
    t2 = (t0 + 3208);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng8)));
    memset(t13, 0, 8);
    xsi_vlog_signed_minus(t13, 32, t4, 32, t5, 32);
    t11 = (t0 + 3208);
    xsi_vlogvar_assign_value(t11, t13, 0, 0, 32);
    goto LAB113;

LAB117:    t11 = (t13 + 4);
    *((unsigned int *)t13) = 1;
    *((unsigned int *)t11) = 1;
    goto LAB118;

LAB119:    *((unsigned int *)t21) = 1;
    goto LAB122;

LAB121:    t27 = (t21 + 4);
    *((unsigned int *)t21) = 1;
    *((unsigned int *)t27) = 1;
    goto LAB122;

LAB123:    t33 = (t0 + 1528U);
    t34 = *((char **)t33);
    memset(t32, 0, 8);
    t33 = (t34 + 4);
    t35 = *((unsigned int *)t33);
    t36 = (~(t35));
    t37 = *((unsigned int *)t34);
    t38 = (t37 & t36);
    t39 = (t38 & 1U);
    if (t39 != 0)
        goto LAB129;

LAB127:    if (*((unsigned int *)t33) == 0)
        goto LAB126;

LAB128:    t40 = (t32 + 4);
    *((unsigned int *)t32) = 1;
    *((unsigned int *)t40) = 1;

LAB129:    memset(t41, 0, 8);
    t42 = (t32 + 4);
    t43 = *((unsigned int *)t42);
    t44 = (~(t43));
    t45 = *((unsigned int *)t32);
    t46 = (t45 & t44);
    t47 = (t46 & 1U);
    if (t47 != 0)
        goto LAB130;

LAB131:    if (*((unsigned int *)t42) != 0)
        goto LAB132;

LAB133:    t50 = *((unsigned int *)t21);
    t51 = *((unsigned int *)t41);
    t52 = (t50 & t51);
    *((unsigned int *)t49) = t52;
    t53 = (t21 + 4);
    t54 = (t41 + 4);
    t55 = (t49 + 4);
    t56 = *((unsigned int *)t53);
    t57 = *((unsigned int *)t54);
    t58 = (t56 | t57);
    *((unsigned int *)t55) = t58;
    t59 = *((unsigned int *)t55);
    t60 = (t59 != 0);
    if (t60 == 1)
        goto LAB134;

LAB135:
LAB136:    goto LAB125;

LAB126:    *((unsigned int *)t32) = 1;
    goto LAB129;

LAB130:    *((unsigned int *)t41) = 1;
    goto LAB133;

LAB132:    t48 = (t41 + 4);
    *((unsigned int *)t41) = 1;
    *((unsigned int *)t48) = 1;
    goto LAB133;

LAB134:    t61 = *((unsigned int *)t49);
    t62 = *((unsigned int *)t55);
    *((unsigned int *)t49) = (t61 | t62);
    t63 = (t21 + 4);
    t64 = (t41 + 4);
    t65 = *((unsigned int *)t21);
    t66 = (~(t65));
    t67 = *((unsigned int *)t63);
    t68 = (~(t67));
    t69 = *((unsigned int *)t41);
    t70 = (~(t69));
    t71 = *((unsigned int *)t64);
    t72 = (~(t71));
    t73 = (t66 & t68);
    t74 = (t70 & t72);
    t75 = (~(t73));
    t76 = (~(t74));
    t77 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t77 & t75);
    t78 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t78 & t76);
    t79 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t79 & t75);
    t80 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t80 & t76);
    goto LAB136;

LAB137:    xsi_set_current_line(81, ng0);

LAB140:    xsi_set_current_line(82, ng0);
    t87 = (t0 + 1688U);
    t88 = *((char **)t87);
    t87 = (t0 + 1848U);
    t90 = *((char **)t87);
    xsi_vlog_unsigned_multiply(t179, 64, t88, 32, t90, 32);
    t87 = (t0 + 4008);
    xsi_vlogvar_assign_value(t87, t179, 0, 0, 32);
    t92 = (t0 + 3848);
    xsi_vlogvar_assign_value(t92, t179, 32, 0, 32);
    xsi_set_current_line(83, ng0);
    t2 = ((char*)((ng8)));
    t3 = (t0 + 3048);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 1);
    xsi_set_current_line(84, ng0);
    t2 = (t0 + 3208);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng8)));
    memset(t13, 0, 8);
    xsi_vlog_signed_minus(t13, 32, t4, 32, t5, 32);
    t11 = (t0 + 3208);
    xsi_vlogvar_assign_value(t11, t13, 0, 0, 32);
    goto LAB139;

LAB143:    t11 = (t13 + 4);
    *((unsigned int *)t13) = 1;
    *((unsigned int *)t11) = 1;
    goto LAB144;

LAB145:    *((unsigned int *)t21) = 1;
    goto LAB148;

LAB147:    t27 = (t21 + 4);
    *((unsigned int *)t21) = 1;
    *((unsigned int *)t27) = 1;
    goto LAB148;

LAB149:    t33 = (t0 + 1528U);
    t34 = *((char **)t33);
    memset(t32, 0, 8);
    t33 = (t34 + 4);
    t35 = *((unsigned int *)t33);
    t36 = (~(t35));
    t37 = *((unsigned int *)t34);
    t38 = (t37 & t36);
    t39 = (t38 & 1U);
    if (t39 != 0)
        goto LAB155;

LAB153:    if (*((unsigned int *)t33) == 0)
        goto LAB152;

LAB154:    t40 = (t32 + 4);
    *((unsigned int *)t32) = 1;
    *((unsigned int *)t40) = 1;

LAB155:    memset(t41, 0, 8);
    t42 = (t32 + 4);
    t43 = *((unsigned int *)t42);
    t44 = (~(t43));
    t45 = *((unsigned int *)t32);
    t46 = (t45 & t44);
    t47 = (t46 & 1U);
    if (t47 != 0)
        goto LAB156;

LAB157:    if (*((unsigned int *)t42) != 0)
        goto LAB158;

LAB159:    t50 = *((unsigned int *)t21);
    t51 = *((unsigned int *)t41);
    t52 = (t50 & t51);
    *((unsigned int *)t49) = t52;
    t53 = (t21 + 4);
    t54 = (t41 + 4);
    t55 = (t49 + 4);
    t56 = *((unsigned int *)t53);
    t57 = *((unsigned int *)t54);
    t58 = (t56 | t57);
    *((unsigned int *)t55) = t58;
    t59 = *((unsigned int *)t55);
    t60 = (t59 != 0);
    if (t60 == 1)
        goto LAB160;

LAB161:
LAB162:    goto LAB151;

LAB152:    *((unsigned int *)t32) = 1;
    goto LAB155;

LAB156:    *((unsigned int *)t41) = 1;
    goto LAB159;

LAB158:    t48 = (t41 + 4);
    *((unsigned int *)t41) = 1;
    *((unsigned int *)t48) = 1;
    goto LAB159;

LAB160:    t61 = *((unsigned int *)t49);
    t62 = *((unsigned int *)t55);
    *((unsigned int *)t49) = (t61 | t62);
    t63 = (t21 + 4);
    t64 = (t41 + 4);
    t65 = *((unsigned int *)t21);
    t66 = (~(t65));
    t67 = *((unsigned int *)t63);
    t68 = (~(t67));
    t69 = *((unsigned int *)t41);
    t70 = (~(t69));
    t71 = *((unsigned int *)t64);
    t72 = (~(t71));
    t73 = (t66 & t68);
    t74 = (t70 & t72);
    t75 = (~(t73));
    t76 = (~(t74));
    t77 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t77 & t75);
    t78 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t78 & t76);
    t79 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t79 & t75);
    t80 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t80 & t76);
    goto LAB162;

LAB163:    xsi_set_current_line(86, ng0);

LAB166:    xsi_set_current_line(87, ng0);
    t87 = (t0 + 1688U);
    t88 = *((char **)t87);
    xsi_vlogtype_sign_extend(t179, 64, t88, 32);
    t87 = (t0 + 1848U);
    t90 = *((char **)t87);
    xsi_vlogtype_sign_extend(t180, 64, t90, 32);
    xsi_vlog_signed_multiply(t181, 64, t179, 64, t180, 64);
    t87 = (t0 + 4008);
    xsi_vlogvar_assign_value(t87, t181, 0, 0, 32);
    t92 = (t0 + 3848);
    xsi_vlogvar_assign_value(t92, t181, 32, 0, 32);
    xsi_set_current_line(88, ng0);
    t2 = ((char*)((ng8)));
    t3 = (t0 + 3048);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 1);
    xsi_set_current_line(89, ng0);
    t2 = (t0 + 3208);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng8)));
    memset(t13, 0, 8);
    xsi_vlog_signed_minus(t13, 32, t4, 32, t5, 32);
    t11 = (t0 + 3208);
    xsi_vlogvar_assign_value(t11, t13, 0, 0, 32);
    goto LAB165;

LAB167:    *((unsigned int *)t13) = 1;
    goto LAB170;

LAB169:    t4 = (t13 + 4);
    *((unsigned int *)t13) = 1;
    *((unsigned int *)t4) = 1;
    goto LAB170;

LAB171:    t11 = (t0 + 3208);
    t12 = (t11 + 56U);
    t27 = *((char **)t12);
    t28 = ((char*)((ng6)));
    memset(t21, 0, 8);
    xsi_vlog_signed_equal(t21, 32, t27, 32, t28, 32);
    memset(t32, 0, 8);
    t33 = (t21 + 4);
    t17 = *((unsigned int *)t33);
    t18 = (~(t17));
    t19 = *((unsigned int *)t21);
    t20 = (t19 & t18);
    t22 = (t20 & 1U);
    if (t22 != 0)
        goto LAB174;

LAB175:    if (*((unsigned int *)t33) != 0)
        goto LAB176;

LAB177:    t40 = (t32 + 4);
    t23 = *((unsigned int *)t32);
    t24 = (!(t23));
    t25 = *((unsigned int *)t40);
    t26 = (t24 || t25);
    if (t26 > 0)
        goto LAB178;

LAB179:    memcpy(t89, t32, 8);

LAB180:    memset(t91, 0, 8);
    t92 = (t89 + 4);
    t67 = *((unsigned int *)t92);
    t68 = (~(t67));
    t69 = *((unsigned int *)t89);
    t70 = (t69 & t68);
    t71 = (t70 & 1U);
    if (t71 != 0)
        goto LAB188;

LAB189:    if (*((unsigned int *)t92) != 0)
        goto LAB190;

LAB191:    t72 = *((unsigned int *)t13);
    t75 = *((unsigned int *)t91);
    t76 = (t72 & t75);
    *((unsigned int *)t94) = t76;
    t95 = (t13 + 4);
    t96 = (t91 + 4);
    t97 = (t94 + 4);
    t77 = *((unsigned int *)t95);
    t78 = *((unsigned int *)t96);
    t79 = (t77 | t78);
    *((unsigned int *)t97) = t79;
    t80 = *((unsigned int *)t97);
    t82 = (t80 != 0);
    if (t82 == 1)
        goto LAB192;

LAB193:
LAB194:    goto LAB173;

LAB174:    *((unsigned int *)t32) = 1;
    goto LAB177;

LAB176:    t34 = (t32 + 4);
    *((unsigned int *)t32) = 1;
    *((unsigned int *)t34) = 1;
    goto LAB177;

LAB178:    t42 = (t0 + 3368);
    t48 = (t42 + 56U);
    t53 = *((char **)t48);
    t54 = ((char*)((ng7)));
    memset(t41, 0, 8);
    xsi_vlog_signed_equal(t41, 32, t53, 32, t54, 32);
    memset(t49, 0, 8);
    t55 = (t41 + 4);
    t29 = *((unsigned int *)t55);
    t30 = (~(t29));
    t31 = *((unsigned int *)t41);
    t35 = (t31 & t30);
    t36 = (t35 & 1U);
    if (t36 != 0)
        goto LAB181;

LAB182:    if (*((unsigned int *)t55) != 0)
        goto LAB183;

LAB184:    t37 = *((unsigned int *)t32);
    t38 = *((unsigned int *)t49);
    t39 = (t37 | t38);
    *((unsigned int *)t89) = t39;
    t64 = (t32 + 4);
    t81 = (t49 + 4);
    t87 = (t89 + 4);
    t43 = *((unsigned int *)t64);
    t44 = *((unsigned int *)t81);
    t45 = (t43 | t44);
    *((unsigned int *)t87) = t45;
    t46 = *((unsigned int *)t87);
    t47 = (t46 != 0);
    if (t47 == 1)
        goto LAB185;

LAB186:
LAB187:    goto LAB180;

LAB181:    *((unsigned int *)t49) = 1;
    goto LAB184;

LAB183:    t63 = (t49 + 4);
    *((unsigned int *)t49) = 1;
    *((unsigned int *)t63) = 1;
    goto LAB184;

LAB185:    t50 = *((unsigned int *)t89);
    t51 = *((unsigned int *)t87);
    *((unsigned int *)t89) = (t50 | t51);
    t88 = (t32 + 4);
    t90 = (t49 + 4);
    t52 = *((unsigned int *)t88);
    t56 = (~(t52));
    t57 = *((unsigned int *)t32);
    t73 = (t57 & t56);
    t58 = *((unsigned int *)t90);
    t59 = (~(t58));
    t60 = *((unsigned int *)t49);
    t74 = (t60 & t59);
    t61 = (~(t73));
    t62 = (~(t74));
    t65 = *((unsigned int *)t87);
    *((unsigned int *)t87) = (t65 & t61);
    t66 = *((unsigned int *)t87);
    *((unsigned int *)t87) = (t66 & t62);
    goto LAB187;

LAB188:    *((unsigned int *)t91) = 1;
    goto LAB191;

LAB190:    t93 = (t91 + 4);
    *((unsigned int *)t91) = 1;
    *((unsigned int *)t93) = 1;
    goto LAB191;

LAB192:    t83 = *((unsigned int *)t94);
    t84 = *((unsigned int *)t97);
    *((unsigned int *)t94) = (t83 | t84);
    t98 = (t13 + 4);
    t99 = (t91 + 4);
    t85 = *((unsigned int *)t13);
    t86 = (~(t85));
    t100 = *((unsigned int *)t98);
    t101 = (~(t100));
    t102 = *((unsigned int *)t91);
    t103 = (~(t102));
    t104 = *((unsigned int *)t99);
    t105 = (~(t104));
    t106 = (t86 & t101);
    t107 = (t103 & t105);
    t108 = (~(t106));
    t109 = (~(t107));
    t110 = *((unsigned int *)t97);
    *((unsigned int *)t97) = (t110 & t108);
    t111 = *((unsigned int *)t97);
    *((unsigned int *)t97) = (t111 & t109);
    t112 = *((unsigned int *)t94);
    *((unsigned int *)t94) = (t112 & t108);
    t113 = *((unsigned int *)t94);
    *((unsigned int *)t94) = (t113 & t109);
    goto LAB194;

LAB195:    *((unsigned int *)t114) = 1;
    goto LAB198;

LAB197:    t121 = (t114 + 4);
    *((unsigned int *)t114) = 1;
    *((unsigned int *)t121) = 1;
    goto LAB198;

LAB199:    t126 = (t0 + 3208);
    t127 = (t126 + 56U);
    t128 = *((char **)t127);
    t129 = ((char*)((ng3)));
    memset(t130, 0, 8);
    xsi_vlog_signed_not_equal(t130, 32, t128, 32, t129, 32);
    memset(t131, 0, 8);
    t132 = (t130 + 4);
    t133 = *((unsigned int *)t132);
    t134 = (~(t133));
    t135 = *((unsigned int *)t130);
    t136 = (t135 & t134);
    t137 = (t136 & 1U);
    if (t137 != 0)
        goto LAB202;

LAB203:    if (*((unsigned int *)t132) != 0)
        goto LAB204;

LAB205:    t140 = *((unsigned int *)t114);
    t141 = *((unsigned int *)t131);
    t142 = (t140 & t141);
    *((unsigned int *)t139) = t142;
    t143 = (t114 + 4);
    t144 = (t131 + 4);
    t145 = (t139 + 4);
    t146 = *((unsigned int *)t143);
    t147 = *((unsigned int *)t144);
    t148 = (t146 | t147);
    *((unsigned int *)t145) = t148;
    t149 = *((unsigned int *)t145);
    t150 = (t149 != 0);
    if (t150 == 1)
        goto LAB206;

LAB207:
LAB208:    goto LAB201;

LAB202:    *((unsigned int *)t131) = 1;
    goto LAB205;

LAB204:    t138 = (t131 + 4);
    *((unsigned int *)t131) = 1;
    *((unsigned int *)t138) = 1;
    goto LAB205;

LAB206:    t151 = *((unsigned int *)t139);
    t152 = *((unsigned int *)t145);
    *((unsigned int *)t139) = (t151 | t152);
    t153 = (t114 + 4);
    t154 = (t131 + 4);
    t155 = *((unsigned int *)t114);
    t156 = (~(t155));
    t157 = *((unsigned int *)t153);
    t158 = (~(t157));
    t159 = *((unsigned int *)t131);
    t160 = (~(t159));
    t161 = *((unsigned int *)t154);
    t162 = (~(t161));
    t163 = (t156 & t158);
    t164 = (t160 & t162);
    t165 = (~(t163));
    t166 = (~(t164));
    t167 = *((unsigned int *)t145);
    *((unsigned int *)t145) = (t167 & t165);
    t168 = *((unsigned int *)t145);
    *((unsigned int *)t145) = (t168 & t166);
    t169 = *((unsigned int *)t139);
    *((unsigned int *)t139) = (t169 & t165);
    t170 = *((unsigned int *)t139);
    *((unsigned int *)t139) = (t170 & t166);
    goto LAB208;

LAB209:    xsi_set_current_line(93, ng0);

LAB212:    xsi_set_current_line(94, ng0);
    t177 = ((char*)((ng1)));
    t178 = (t0 + 3048);
    xsi_vlogvar_assign_value(t178, t177, 0, 0, 1);
    xsi_set_current_line(95, ng0);
    t2 = ((char*)((ng3)));
    t3 = (t0 + 3368);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    goto LAB211;

LAB213:    xsi_set_current_line(97, ng0);

LAB216:    xsi_set_current_line(98, ng0);
    t12 = (t0 + 3848);
    t27 = (t12 + 56U);
    t28 = *((char **)t27);
    t33 = (t0 + 3528);
    xsi_vlogvar_assign_value(t33, t28, 0, 0, 32);
    xsi_set_current_line(99, ng0);
    t2 = (t0 + 4008);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = (t0 + 3688);
    xsi_vlogvar_assign_value(t5, t4, 0, 0, 32);
    xsi_set_current_line(100, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 3048);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 1);
    xsi_set_current_line(101, ng0);
    t2 = ((char*)((ng3)));
    t3 = (t0 + 3368);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    goto LAB215;

LAB217:    xsi_set_current_line(103, ng0);

LAB220:    xsi_set_current_line(104, ng0);
    t12 = ((char*)((ng8)));
    t27 = (t0 + 3048);
    xsi_vlogvar_assign_value(t27, t12, 0, 0, 1);
    xsi_set_current_line(105, ng0);
    t2 = (t0 + 3368);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng8)));
    memset(t13, 0, 8);
    xsi_vlog_signed_minus(t13, 32, t4, 32, t5, 32);
    t11 = (t0 + 3368);
    xsi_vlogvar_assign_value(t11, t13, 0, 0, 32);
    goto LAB219;

LAB223:    t11 = (t13 + 4);
    *((unsigned int *)t13) = 1;
    *((unsigned int *)t11) = 1;
    goto LAB224;

LAB225:    *((unsigned int *)t21) = 1;
    goto LAB228;

LAB227:    t27 = (t21 + 4);
    *((unsigned int *)t21) = 1;
    *((unsigned int *)t27) = 1;
    goto LAB228;

LAB229:    t33 = (t0 + 1528U);
    t34 = *((char **)t33);
    memset(t32, 0, 8);
    t33 = (t34 + 4);
    t35 = *((unsigned int *)t33);
    t36 = (~(t35));
    t37 = *((unsigned int *)t34);
    t38 = (t37 & t36);
    t39 = (t38 & 1U);
    if (t39 != 0)
        goto LAB235;

LAB233:    if (*((unsigned int *)t33) == 0)
        goto LAB232;

LAB234:    t40 = (t32 + 4);
    *((unsigned int *)t32) = 1;
    *((unsigned int *)t40) = 1;

LAB235:    memset(t41, 0, 8);
    t42 = (t32 + 4);
    t43 = *((unsigned int *)t42);
    t44 = (~(t43));
    t45 = *((unsigned int *)t32);
    t46 = (t45 & t44);
    t47 = (t46 & 1U);
    if (t47 != 0)
        goto LAB236;

LAB237:    if (*((unsigned int *)t42) != 0)
        goto LAB238;

LAB239:    t50 = *((unsigned int *)t21);
    t51 = *((unsigned int *)t41);
    t52 = (t50 & t51);
    *((unsigned int *)t49) = t52;
    t53 = (t21 + 4);
    t54 = (t41 + 4);
    t55 = (t49 + 4);
    t56 = *((unsigned int *)t53);
    t57 = *((unsigned int *)t54);
    t58 = (t56 | t57);
    *((unsigned int *)t55) = t58;
    t59 = *((unsigned int *)t55);
    t60 = (t59 != 0);
    if (t60 == 1)
        goto LAB240;

LAB241:
LAB242:    goto LAB231;

LAB232:    *((unsigned int *)t32) = 1;
    goto LAB235;

LAB236:    *((unsigned int *)t41) = 1;
    goto LAB239;

LAB238:    t48 = (t41 + 4);
    *((unsigned int *)t41) = 1;
    *((unsigned int *)t48) = 1;
    goto LAB239;

LAB240:    t61 = *((unsigned int *)t49);
    t62 = *((unsigned int *)t55);
    *((unsigned int *)t49) = (t61 | t62);
    t63 = (t21 + 4);
    t64 = (t41 + 4);
    t65 = *((unsigned int *)t21);
    t66 = (~(t65));
    t67 = *((unsigned int *)t63);
    t68 = (~(t67));
    t69 = *((unsigned int *)t41);
    t70 = (~(t69));
    t71 = *((unsigned int *)t64);
    t72 = (~(t71));
    t73 = (t66 & t68);
    t74 = (t70 & t72);
    t75 = (~(t73));
    t76 = (~(t74));
    t77 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t77 & t75);
    t78 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t78 & t76);
    t79 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t79 & t75);
    t80 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t80 & t76);
    goto LAB242;

LAB243:    xsi_set_current_line(107, ng0);

LAB246:    xsi_set_current_line(108, ng0);
    t87 = (t0 + 1688U);
    t88 = *((char **)t87);
    t87 = (t0 + 1848U);
    t90 = *((char **)t87);
    memset(t89, 0, 8);
    xsi_vlog_unsigned_mod(t89, 32, t88, 32, t90, 32);
    t87 = (t0 + 3848);
    xsi_vlogvar_assign_value(t87, t89, 0, 0, 32);
    xsi_set_current_line(109, ng0);
    t2 = (t0 + 1688U);
    t3 = *((char **)t2);
    t2 = (t0 + 1848U);
    t4 = *((char **)t2);
    memset(t13, 0, 8);
    xsi_vlog_unsigned_divide(t13, 32, t3, 32, t4, 32);
    t2 = (t0 + 4008);
    xsi_vlogvar_assign_value(t2, t13, 0, 0, 32);
    xsi_set_current_line(110, ng0);
    t2 = ((char*)((ng8)));
    t3 = (t0 + 3048);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 1);
    xsi_set_current_line(111, ng0);
    t2 = (t0 + 3368);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng8)));
    memset(t13, 0, 8);
    xsi_vlog_signed_minus(t13, 32, t4, 32, t5, 32);
    t11 = (t0 + 3368);
    xsi_vlogvar_assign_value(t11, t13, 0, 0, 32);
    goto LAB245;

LAB249:    t11 = (t13 + 4);
    *((unsigned int *)t13) = 1;
    *((unsigned int *)t11) = 1;
    goto LAB250;

LAB251:    *((unsigned int *)t21) = 1;
    goto LAB254;

LAB253:    t27 = (t21 + 4);
    *((unsigned int *)t21) = 1;
    *((unsigned int *)t27) = 1;
    goto LAB254;

LAB255:    t33 = (t0 + 1528U);
    t34 = *((char **)t33);
    memset(t32, 0, 8);
    t33 = (t34 + 4);
    t35 = *((unsigned int *)t33);
    t36 = (~(t35));
    t37 = *((unsigned int *)t34);
    t38 = (t37 & t36);
    t39 = (t38 & 1U);
    if (t39 != 0)
        goto LAB261;

LAB259:    if (*((unsigned int *)t33) == 0)
        goto LAB258;

LAB260:    t40 = (t32 + 4);
    *((unsigned int *)t32) = 1;
    *((unsigned int *)t40) = 1;

LAB261:    memset(t41, 0, 8);
    t42 = (t32 + 4);
    t43 = *((unsigned int *)t42);
    t44 = (~(t43));
    t45 = *((unsigned int *)t32);
    t46 = (t45 & t44);
    t47 = (t46 & 1U);
    if (t47 != 0)
        goto LAB262;

LAB263:    if (*((unsigned int *)t42) != 0)
        goto LAB264;

LAB265:    t50 = *((unsigned int *)t21);
    t51 = *((unsigned int *)t41);
    t52 = (t50 & t51);
    *((unsigned int *)t49) = t52;
    t53 = (t21 + 4);
    t54 = (t41 + 4);
    t55 = (t49 + 4);
    t56 = *((unsigned int *)t53);
    t57 = *((unsigned int *)t54);
    t58 = (t56 | t57);
    *((unsigned int *)t55) = t58;
    t59 = *((unsigned int *)t55);
    t60 = (t59 != 0);
    if (t60 == 1)
        goto LAB266;

LAB267:
LAB268:    goto LAB257;

LAB258:    *((unsigned int *)t32) = 1;
    goto LAB261;

LAB262:    *((unsigned int *)t41) = 1;
    goto LAB265;

LAB264:    t48 = (t41 + 4);
    *((unsigned int *)t41) = 1;
    *((unsigned int *)t48) = 1;
    goto LAB265;

LAB266:    t61 = *((unsigned int *)t49);
    t62 = *((unsigned int *)t55);
    *((unsigned int *)t49) = (t61 | t62);
    t63 = (t21 + 4);
    t64 = (t41 + 4);
    t65 = *((unsigned int *)t21);
    t66 = (~(t65));
    t67 = *((unsigned int *)t63);
    t68 = (~(t67));
    t69 = *((unsigned int *)t41);
    t70 = (~(t69));
    t71 = *((unsigned int *)t64);
    t72 = (~(t71));
    t73 = (t66 & t68);
    t74 = (t70 & t72);
    t75 = (~(t73));
    t76 = (~(t74));
    t77 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t77 & t75);
    t78 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t78 & t76);
    t79 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t79 & t75);
    t80 = *((unsigned int *)t49);
    *((unsigned int *)t49) = (t80 & t76);
    goto LAB268;

LAB269:    xsi_set_current_line(113, ng0);

LAB272:    xsi_set_current_line(114, ng0);
    t87 = (t0 + 1688U);
    t88 = *((char **)t87);
    t87 = (t0 + 1848U);
    t90 = *((char **)t87);
    memset(t94, 0, 8);
    xsi_vlog_signed_mod(t94, 32, t88, 32, t90, 32);
    t87 = (t0 + 3848);
    xsi_vlogvar_assign_value(t87, t94, 0, 0, 32);
    xsi_set_current_line(115, ng0);
    t2 = (t0 + 1688U);
    t3 = *((char **)t2);
    t2 = (t0 + 1848U);
    t4 = *((char **)t2);
    memset(t32, 0, 8);
    xsi_vlog_signed_divide(t32, 32, t3, 32, t4, 32);
    t2 = (t0 + 4008);
    xsi_vlogvar_assign_value(t2, t32, 0, 0, 32);
    xsi_set_current_line(116, ng0);
    t2 = ((char*)((ng8)));
    t3 = (t0 + 3048);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 1);
    xsi_set_current_line(117, ng0);
    t2 = (t0 + 3368);
    t3 = (t2 + 56U);
    t4 = *((char **)t3);
    t5 = ((char*)((ng8)));
    memset(t13, 0, 8);
    xsi_vlog_signed_minus(t13, 32, t4, 32, t5, 32);
    t11 = (t0 + 3368);
    xsi_vlogvar_assign_value(t11, t13, 0, 0, 32);
    goto LAB271;

}


extern void work_m_00000000001477427496_0105961027_init()
{
	static char *pe[] = {(void *)Cont_42_0,(void *)Cont_43_1,(void *)Initial_45_2,(void *)Always_49_3};
	xsi_register_didat("work_m_00000000001477427496_0105961027", "isim/mips_txt_isim_beh.exe.sim/work/m_00000000001477427496_0105961027.didat");
	xsi_register_executes(pe);
}
