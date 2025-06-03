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
static const char *ng0 = "D:/ISE/p7_fortest/CP0.v";
static int ng1[] = {0, 0};
static int ng2[] = {12, 0};
static int ng3[] = {13, 0};
static int ng4[] = {14, 0};
static int ng5[] = {15, 0};
static unsigned int ng6[] = {404951111U, 0U};
static int ng7[] = {10, 0};
static int ng8[] = {1, 0};
static int ng9[] = {31, 0};
static int ng10[] = {6, 0};
static int ng11[] = {2, 0};
static unsigned int ng12[] = {0U, 0U};



static void Cont_62_0(char *t0)
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

LAB0:    t1 = (t0 + 6048U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(62, ng0);
    t2 = (t0 + 2168U);
    t4 = *((char **)t2);
    memset(t3, 0, 8);
    t2 = (t3 + 4);
    t5 = (t4 + 4);
    t6 = *((unsigned int *)t4);
    t7 = (t6 >> 11);
    *((unsigned int *)t3) = t7;
    t8 = *((unsigned int *)t5);
    t9 = (t8 >> 11);
    *((unsigned int *)t2) = t9;
    t10 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t10 & 31U);
    t11 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t11 & 31U);
    t12 = (t0 + 8032);
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
    t25 = (t0 + 7856);
    *((int *)t25) = 1;

LAB1:    return;
}

static void Cont_63_1(char *t0)
{
    char t3[8];
    char *t1;
    char *t2;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    unsigned int t8;
    unsigned int t9;
    unsigned int t10;
    unsigned int t11;
    unsigned int t12;
    unsigned int t13;
    char *t14;
    char *t15;
    char *t16;
    char *t17;
    char *t18;
    char *t19;

LAB0:    t1 = (t0 + 6296U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(63, ng0);
    t2 = (t0 + 4968);
    t4 = (t2 + 56U);
    t5 = *((char **)t4);
    memset(t3, 0, 8);
    t6 = (t3 + 4);
    t7 = (t5 + 4);
    t8 = *((unsigned int *)t5);
    t9 = (t8 >> 0);
    *((unsigned int *)t3) = t9;
    t10 = *((unsigned int *)t7);
    t11 = (t10 >> 0);
    *((unsigned int *)t6) = t11;
    t12 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t12 & 4294967295U);
    t13 = *((unsigned int *)t6);
    *((unsigned int *)t6) = (t13 & 4294967295U);
    t14 = (t0 + 8096);
    t15 = (t14 + 56U);
    t16 = *((char **)t15);
    t17 = (t16 + 56U);
    t18 = *((char **)t17);
    memcpy(t18, t3, 8);
    xsi_driver_vfirst_trans(t14, 0, 31);
    t19 = (t0 + 7872);
    *((int *)t19) = 1;

LAB1:    return;
}

static void Cont_64_2(char *t0)
{
    char t4[8];
    char t18[8];
    char t25[8];
    char t53[8];
    char t68[8];
    char t75[8];
    char *t1;
    char *t2;
    char *t3;
    unsigned int t5;
    unsigned int t6;
    unsigned int t7;
    unsigned int t8;
    unsigned int t9;
    char *t10;
    char *t11;
    unsigned int t12;
    unsigned int t13;
    unsigned int t14;
    unsigned int t15;
    char *t16;
    char *t17;
    unsigned int t19;
    unsigned int t20;
    unsigned int t21;
    unsigned int t22;
    unsigned int t23;
    char *t24;
    unsigned int t26;
    unsigned int t27;
    unsigned int t28;
    char *t29;
    char *t30;
    char *t31;
    unsigned int t32;
    unsigned int t33;
    unsigned int t34;
    unsigned int t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    char *t39;
    char *t40;
    unsigned int t41;
    unsigned int t42;
    unsigned int t43;
    int t44;
    unsigned int t45;
    unsigned int t46;
    unsigned int t47;
    int t48;
    unsigned int t49;
    unsigned int t50;
    unsigned int t51;
    unsigned int t52;
    char *t54;
    unsigned int t55;
    unsigned int t56;
    unsigned int t57;
    unsigned int t58;
    unsigned int t59;
    char *t60;
    char *t61;
    unsigned int t62;
    unsigned int t63;
    unsigned int t64;
    unsigned int t65;
    char *t66;
    char *t67;
    unsigned int t69;
    unsigned int t70;
    unsigned int t71;
    unsigned int t72;
    unsigned int t73;
    char *t74;
    unsigned int t76;
    unsigned int t77;
    unsigned int t78;
    char *t79;
    char *t80;
    char *t81;
    unsigned int t82;
    unsigned int t83;
    unsigned int t84;
    unsigned int t85;
    unsigned int t86;
    unsigned int t87;
    unsigned int t88;
    char *t89;
    char *t90;
    unsigned int t91;
    unsigned int t92;
    unsigned int t93;
    int t94;
    unsigned int t95;
    unsigned int t96;
    unsigned int t97;
    int t98;
    unsigned int t99;
    unsigned int t100;
    unsigned int t101;
    unsigned int t102;
    char *t103;
    char *t104;
    char *t105;
    char *t106;
    char *t107;
    unsigned int t108;
    unsigned int t109;
    char *t110;
    unsigned int t111;
    unsigned int t112;
    char *t113;
    unsigned int t114;
    unsigned int t115;
    char *t116;

LAB0:    t1 = (t0 + 6544U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(64, ng0);
    t2 = (t0 + 3608U);
    t3 = *((char **)t2);
    memset(t4, 0, 8);
    t2 = (t3 + 4);
    t5 = *((unsigned int *)t2);
    t6 = (~(t5));
    t7 = *((unsigned int *)t3);
    t8 = (t7 & t6);
    t9 = (t8 & 1U);
    if (t9 != 0)
        goto LAB4;

LAB5:    if (*((unsigned int *)t2) != 0)
        goto LAB6;

LAB7:    t11 = (t4 + 4);
    t12 = *((unsigned int *)t4);
    t13 = (!(t12));
    t14 = *((unsigned int *)t11);
    t15 = (t13 || t14);
    if (t15 > 0)
        goto LAB8;

LAB9:    memcpy(t25, t4, 8);

LAB10:    memset(t53, 0, 8);
    t54 = (t25 + 4);
    t55 = *((unsigned int *)t54);
    t56 = (~(t55));
    t57 = *((unsigned int *)t25);
    t58 = (t57 & t56);
    t59 = (t58 & 1U);
    if (t59 != 0)
        goto LAB18;

LAB19:    if (*((unsigned int *)t54) != 0)
        goto LAB20;

LAB21:    t61 = (t53 + 4);
    t62 = *((unsigned int *)t53);
    t63 = (!(t62));
    t64 = *((unsigned int *)t61);
    t65 = (t63 || t64);
    if (t65 > 0)
        goto LAB22;

LAB23:    memcpy(t75, t53, 8);

LAB24:    t103 = (t0 + 8160);
    t104 = (t103 + 56U);
    t105 = *((char **)t104);
    t106 = (t105 + 56U);
    t107 = *((char **)t106);
    memset(t107, 0, 8);
    t108 = 1U;
    t109 = t108;
    t110 = (t75 + 4);
    t111 = *((unsigned int *)t75);
    t108 = (t108 & t111);
    t112 = *((unsigned int *)t110);
    t109 = (t109 & t112);
    t113 = (t107 + 4);
    t114 = *((unsigned int *)t107);
    *((unsigned int *)t107) = (t114 | t108);
    t115 = *((unsigned int *)t113);
    *((unsigned int *)t113) = (t115 | t109);
    xsi_driver_vfirst_trans(t103, 0, 0);
    t116 = (t0 + 7888);
    *((int *)t116) = 1;

LAB1:    return;
LAB4:    *((unsigned int *)t4) = 1;
    goto LAB7;

LAB6:    t10 = (t4 + 4);
    *((unsigned int *)t4) = 1;
    *((unsigned int *)t10) = 1;
    goto LAB7;

LAB8:    t16 = (t0 + 3768U);
    t17 = *((char **)t16);
    memset(t18, 0, 8);
    t16 = (t17 + 4);
    t19 = *((unsigned int *)t16);
    t20 = (~(t19));
    t21 = *((unsigned int *)t17);
    t22 = (t21 & t20);
    t23 = (t22 & 1U);
    if (t23 != 0)
        goto LAB11;

LAB12:    if (*((unsigned int *)t16) != 0)
        goto LAB13;

LAB14:    t26 = *((unsigned int *)t4);
    t27 = *((unsigned int *)t18);
    t28 = (t26 | t27);
    *((unsigned int *)t25) = t28;
    t29 = (t4 + 4);
    t30 = (t18 + 4);
    t31 = (t25 + 4);
    t32 = *((unsigned int *)t29);
    t33 = *((unsigned int *)t30);
    t34 = (t32 | t33);
    *((unsigned int *)t31) = t34;
    t35 = *((unsigned int *)t31);
    t36 = (t35 != 0);
    if (t36 == 1)
        goto LAB15;

LAB16:
LAB17:    goto LAB10;

LAB11:    *((unsigned int *)t18) = 1;
    goto LAB14;

LAB13:    t24 = (t18 + 4);
    *((unsigned int *)t18) = 1;
    *((unsigned int *)t24) = 1;
    goto LAB14;

LAB15:    t37 = *((unsigned int *)t25);
    t38 = *((unsigned int *)t31);
    *((unsigned int *)t25) = (t37 | t38);
    t39 = (t4 + 4);
    t40 = (t18 + 4);
    t41 = *((unsigned int *)t39);
    t42 = (~(t41));
    t43 = *((unsigned int *)t4);
    t44 = (t43 & t42);
    t45 = *((unsigned int *)t40);
    t46 = (~(t45));
    t47 = *((unsigned int *)t18);
    t48 = (t47 & t46);
    t49 = (~(t44));
    t50 = (~(t48));
    t51 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t51 & t49);
    t52 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t52 & t50);
    goto LAB17;

LAB18:    *((unsigned int *)t53) = 1;
    goto LAB21;

LAB20:    t60 = (t53 + 4);
    *((unsigned int *)t53) = 1;
    *((unsigned int *)t60) = 1;
    goto LAB21;

LAB22:    t66 = (t0 + 3928U);
    t67 = *((char **)t66);
    memset(t68, 0, 8);
    t66 = (t67 + 4);
    t69 = *((unsigned int *)t66);
    t70 = (~(t69));
    t71 = *((unsigned int *)t67);
    t72 = (t71 & t70);
    t73 = (t72 & 1U);
    if (t73 != 0)
        goto LAB25;

LAB26:    if (*((unsigned int *)t66) != 0)
        goto LAB27;

LAB28:    t76 = *((unsigned int *)t53);
    t77 = *((unsigned int *)t68);
    t78 = (t76 | t77);
    *((unsigned int *)t75) = t78;
    t79 = (t53 + 4);
    t80 = (t68 + 4);
    t81 = (t75 + 4);
    t82 = *((unsigned int *)t79);
    t83 = *((unsigned int *)t80);
    t84 = (t82 | t83);
    *((unsigned int *)t81) = t84;
    t85 = *((unsigned int *)t81);
    t86 = (t85 != 0);
    if (t86 == 1)
        goto LAB29;

LAB30:
LAB31:    goto LAB24;

LAB25:    *((unsigned int *)t68) = 1;
    goto LAB28;

LAB27:    t74 = (t68 + 4);
    *((unsigned int *)t68) = 1;
    *((unsigned int *)t74) = 1;
    goto LAB28;

LAB29:    t87 = *((unsigned int *)t75);
    t88 = *((unsigned int *)t81);
    *((unsigned int *)t75) = (t87 | t88);
    t89 = (t53 + 4);
    t90 = (t68 + 4);
    t91 = *((unsigned int *)t89);
    t92 = (~(t91));
    t93 = *((unsigned int *)t53);
    t94 = (t93 & t92);
    t95 = *((unsigned int *)t90);
    t96 = (~(t95));
    t97 = *((unsigned int *)t68);
    t98 = (t97 & t96);
    t99 = (~(t94));
    t100 = (~(t98));
    t101 = *((unsigned int *)t81);
    *((unsigned int *)t81) = (t101 & t99);
    t102 = *((unsigned int *)t81);
    *((unsigned int *)t81) = (t102 & t100);
    goto LAB31;

}

static void Cont_65_3(char *t0)
{
    char t4[8];
    char t18[8];
    char t34[8];
    char t42[8];
    char t70[8];
    char t84[8];
    char t91[8];
    char *t1;
    char *t2;
    char *t3;
    unsigned int t5;
    unsigned int t6;
    unsigned int t7;
    unsigned int t8;
    unsigned int t9;
    char *t10;
    char *t11;
    unsigned int t12;
    unsigned int t13;
    unsigned int t14;
    unsigned int t15;
    char *t16;
    char *t17;
    char *t19;
    char *t20;
    unsigned int t21;
    unsigned int t22;
    unsigned int t23;
    unsigned int t24;
    unsigned int t25;
    unsigned int t26;
    unsigned int t27;
    unsigned int t28;
    unsigned int t29;
    unsigned int t30;
    unsigned int t31;
    unsigned int t32;
    char *t33;
    char *t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    unsigned int t39;
    unsigned int t40;
    char *t41;
    unsigned int t43;
    unsigned int t44;
    unsigned int t45;
    char *t46;
    char *t47;
    char *t48;
    unsigned int t49;
    unsigned int t50;
    unsigned int t51;
    unsigned int t52;
    unsigned int t53;
    unsigned int t54;
    unsigned int t55;
    char *t56;
    char *t57;
    unsigned int t58;
    unsigned int t59;
    unsigned int t60;
    int t61;
    unsigned int t62;
    unsigned int t63;
    unsigned int t64;
    int t65;
    unsigned int t66;
    unsigned int t67;
    unsigned int t68;
    unsigned int t69;
    char *t71;
    unsigned int t72;
    unsigned int t73;
    unsigned int t74;
    unsigned int t75;
    unsigned int t76;
    char *t77;
    char *t78;
    unsigned int t79;
    unsigned int t80;
    unsigned int t81;
    char *t82;
    char *t83;
    unsigned int t85;
    unsigned int t86;
    unsigned int t87;
    unsigned int t88;
    unsigned int t89;
    char *t90;
    unsigned int t92;
    unsigned int t93;
    unsigned int t94;
    char *t95;
    char *t96;
    char *t97;
    unsigned int t98;
    unsigned int t99;
    unsigned int t100;
    unsigned int t101;
    unsigned int t102;
    unsigned int t103;
    unsigned int t104;
    char *t105;
    char *t106;
    unsigned int t107;
    unsigned int t108;
    unsigned int t109;
    unsigned int t110;
    unsigned int t111;
    unsigned int t112;
    unsigned int t113;
    unsigned int t114;
    int t115;
    int t116;
    unsigned int t117;
    unsigned int t118;
    unsigned int t119;
    unsigned int t120;
    unsigned int t121;
    unsigned int t122;
    char *t123;
    char *t124;
    char *t125;
    char *t126;
    char *t127;
    unsigned int t128;
    unsigned int t129;
    char *t130;
    unsigned int t131;
    unsigned int t132;
    char *t133;
    unsigned int t134;
    unsigned int t135;
    char *t136;

LAB0:    t1 = (t0 + 6792U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(65, ng0);
    t2 = (t0 + 1688U);
    t3 = *((char **)t2);
    memset(t4, 0, 8);
    t2 = (t3 + 4);
    t5 = *((unsigned int *)t2);
    t6 = (~(t5));
    t7 = *((unsigned int *)t3);
    t8 = (t7 & t6);
    t9 = (t8 & 1U);
    if (t9 != 0)
        goto LAB4;

LAB5:    if (*((unsigned int *)t2) != 0)
        goto LAB6;

LAB7:    t11 = (t4 + 4);
    t12 = *((unsigned int *)t4);
    t13 = (!(t12));
    t14 = *((unsigned int *)t11);
    t15 = (t13 || t14);
    if (t15 > 0)
        goto LAB8;

LAB9:    memcpy(t42, t4, 8);

LAB10:    memset(t70, 0, 8);
    t71 = (t42 + 4);
    t72 = *((unsigned int *)t71);
    t73 = (~(t72));
    t74 = *((unsigned int *)t42);
    t75 = (t74 & t73);
    t76 = (t75 & 1U);
    if (t76 != 0)
        goto LAB22;

LAB23:    if (*((unsigned int *)t71) != 0)
        goto LAB24;

LAB25:    t78 = (t70 + 4);
    t79 = *((unsigned int *)t70);
    t80 = *((unsigned int *)t78);
    t81 = (t79 || t80);
    if (t81 > 0)
        goto LAB26;

LAB27:    memcpy(t91, t70, 8);

LAB28:    t123 = (t0 + 8224);
    t124 = (t123 + 56U);
    t125 = *((char **)t124);
    t126 = (t125 + 56U);
    t127 = *((char **)t126);
    memset(t127, 0, 8);
    t128 = 1U;
    t129 = t128;
    t130 = (t91 + 4);
    t131 = *((unsigned int *)t91);
    t128 = (t128 & t131);
    t132 = *((unsigned int *)t130);
    t129 = (t129 & t132);
    t133 = (t127 + 4);
    t134 = *((unsigned int *)t127);
    *((unsigned int *)t127) = (t134 | t128);
    t135 = *((unsigned int *)t133);
    *((unsigned int *)t133) = (t135 | t129);
    xsi_driver_vfirst_trans(t123, 0, 0);
    t136 = (t0 + 7904);
    *((int *)t136) = 1;

LAB1:    return;
LAB4:    *((unsigned int *)t4) = 1;
    goto LAB7;

LAB6:    t10 = (t4 + 4);
    *((unsigned int *)t4) = 1;
    *((unsigned int *)t10) = 1;
    goto LAB7;

LAB8:    t16 = (t0 + 1368U);
    t17 = *((char **)t16);
    t16 = ((char*)((ng1)));
    memset(t18, 0, 8);
    t19 = (t17 + 4);
    t20 = (t16 + 4);
    t21 = *((unsigned int *)t17);
    t22 = *((unsigned int *)t16);
    t23 = (t21 ^ t22);
    t24 = *((unsigned int *)t19);
    t25 = *((unsigned int *)t20);
    t26 = (t24 ^ t25);
    t27 = (t23 | t26);
    t28 = *((unsigned int *)t19);
    t29 = *((unsigned int *)t20);
    t30 = (t28 | t29);
    t31 = (~(t30));
    t32 = (t27 & t31);
    if (t32 != 0)
        goto LAB12;

LAB11:    if (t30 != 0)
        goto LAB13;

LAB14:    memset(t34, 0, 8);
    t35 = (t18 + 4);
    t36 = *((unsigned int *)t35);
    t37 = (~(t36));
    t38 = *((unsigned int *)t18);
    t39 = (t38 & t37);
    t40 = (t39 & 1U);
    if (t40 != 0)
        goto LAB15;

LAB16:    if (*((unsigned int *)t35) != 0)
        goto LAB17;

LAB18:    t43 = *((unsigned int *)t4);
    t44 = *((unsigned int *)t34);
    t45 = (t43 | t44);
    *((unsigned int *)t42) = t45;
    t46 = (t4 + 4);
    t47 = (t34 + 4);
    t48 = (t42 + 4);
    t49 = *((unsigned int *)t46);
    t50 = *((unsigned int *)t47);
    t51 = (t49 | t50);
    *((unsigned int *)t48) = t51;
    t52 = *((unsigned int *)t48);
    t53 = (t52 != 0);
    if (t53 == 1)
        goto LAB19;

LAB20:
LAB21:    goto LAB10;

LAB12:    *((unsigned int *)t18) = 1;
    goto LAB14;

LAB13:    t33 = (t18 + 4);
    *((unsigned int *)t18) = 1;
    *((unsigned int *)t33) = 1;
    goto LAB14;

LAB15:    *((unsigned int *)t34) = 1;
    goto LAB18;

LAB17:    t41 = (t34 + 4);
    *((unsigned int *)t34) = 1;
    *((unsigned int *)t41) = 1;
    goto LAB18;

LAB19:    t54 = *((unsigned int *)t42);
    t55 = *((unsigned int *)t48);
    *((unsigned int *)t42) = (t54 | t55);
    t56 = (t4 + 4);
    t57 = (t34 + 4);
    t58 = *((unsigned int *)t56);
    t59 = (~(t58));
    t60 = *((unsigned int *)t4);
    t61 = (t60 & t59);
    t62 = *((unsigned int *)t57);
    t63 = (~(t62));
    t64 = *((unsigned int *)t34);
    t65 = (t64 & t63);
    t66 = (~(t61));
    t67 = (~(t65));
    t68 = *((unsigned int *)t48);
    *((unsigned int *)t48) = (t68 & t66);
    t69 = *((unsigned int *)t48);
    *((unsigned int *)t48) = (t69 & t67);
    goto LAB21;

LAB22:    *((unsigned int *)t70) = 1;
    goto LAB25;

LAB24:    t77 = (t70 + 4);
    *((unsigned int *)t70) = 1;
    *((unsigned int *)t77) = 1;
    goto LAB25;

LAB26:    t82 = (t0 + 2488U);
    t83 = *((char **)t82);
    memset(t84, 0, 8);
    t82 = (t83 + 4);
    t85 = *((unsigned int *)t82);
    t86 = (~(t85));
    t87 = *((unsigned int *)t83);
    t88 = (t87 & t86);
    t89 = (t88 & 1U);
    if (t89 != 0)
        goto LAB29;

LAB30:    if (*((unsigned int *)t82) != 0)
        goto LAB31;

LAB32:    t92 = *((unsigned int *)t70);
    t93 = *((unsigned int *)t84);
    t94 = (t92 & t93);
    *((unsigned int *)t91) = t94;
    t95 = (t70 + 4);
    t96 = (t84 + 4);
    t97 = (t91 + 4);
    t98 = *((unsigned int *)t95);
    t99 = *((unsigned int *)t96);
    t100 = (t98 | t99);
    *((unsigned int *)t97) = t100;
    t101 = *((unsigned int *)t97);
    t102 = (t101 != 0);
    if (t102 == 1)
        goto LAB33;

LAB34:
LAB35:    goto LAB28;

LAB29:    *((unsigned int *)t84) = 1;
    goto LAB32;

LAB31:    t90 = (t84 + 4);
    *((unsigned int *)t84) = 1;
    *((unsigned int *)t90) = 1;
    goto LAB32;

LAB33:    t103 = *((unsigned int *)t91);
    t104 = *((unsigned int *)t97);
    *((unsigned int *)t91) = (t103 | t104);
    t105 = (t70 + 4);
    t106 = (t84 + 4);
    t107 = *((unsigned int *)t70);
    t108 = (~(t107));
    t109 = *((unsigned int *)t105);
    t110 = (~(t109));
    t111 = *((unsigned int *)t84);
    t112 = (~(t111));
    t113 = *((unsigned int *)t106);
    t114 = (~(t113));
    t115 = (t108 & t110);
    t116 = (t112 & t114);
    t117 = (~(t115));
    t118 = (~(t116));
    t119 = *((unsigned int *)t97);
    *((unsigned int *)t97) = (t119 & t117);
    t120 = *((unsigned int *)t97);
    *((unsigned int *)t97) = (t120 & t118);
    t121 = *((unsigned int *)t91);
    *((unsigned int *)t91) = (t121 & t117);
    t122 = *((unsigned int *)t91);
    *((unsigned int *)t91) = (t122 & t118);
    goto LAB35;

}

static void Cont_67_4(char *t0)
{
    char t3[8];
    char t4[8];
    char t13[8];
    char t25[8];
    char t64[8];
    char t76[8];
    char t80[8];
    char t96[8];
    char t104[8];
    char t136[8];
    char t151[8];
    char t160[8];
    char t168[8];
    char t200[8];
    char t215[8];
    char t231[8];
    char t239[8];
    char *t1;
    char *t2;
    char *t5;
    char *t6;
    unsigned int t7;
    unsigned int t8;
    unsigned int t9;
    unsigned int t10;
    unsigned int t11;
    unsigned int t12;
    char *t14;
    char *t15;
    char *t16;
    char *t17;
    char *t18;
    unsigned int t19;
    unsigned int t20;
    unsigned int t21;
    unsigned int t22;
    unsigned int t23;
    unsigned int t24;
    unsigned int t26;
    unsigned int t27;
    unsigned int t28;
    char *t29;
    char *t30;
    char *t31;
    unsigned int t32;
    unsigned int t33;
    unsigned int t34;
    unsigned int t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    char *t39;
    char *t40;
    unsigned int t41;
    unsigned int t42;
    unsigned int t43;
    unsigned int t44;
    unsigned int t45;
    unsigned int t46;
    unsigned int t47;
    unsigned int t48;
    int t49;
    int t50;
    unsigned int t51;
    unsigned int t52;
    unsigned int t53;
    unsigned int t54;
    unsigned int t55;
    unsigned int t56;
    char *t57;
    unsigned int t58;
    unsigned int t59;
    unsigned int t60;
    unsigned int t61;
    unsigned int t62;
    char *t63;
    char *t65;
    unsigned int t66;
    unsigned int t67;
    unsigned int t68;
    unsigned int t69;
    unsigned int t70;
    char *t71;
    char *t72;
    unsigned int t73;
    unsigned int t74;
    unsigned int t75;
    char *t77;
    char *t78;
    char *t79;
    char *t81;
    char *t82;
    unsigned int t83;
    unsigned int t84;
    unsigned int t85;
    unsigned int t86;
    unsigned int t87;
    unsigned int t88;
    char *t89;
    unsigned int t90;
    unsigned int t91;
    unsigned int t92;
    unsigned int t93;
    unsigned int t94;
    char *t95;
    char *t97;
    unsigned int t98;
    unsigned int t99;
    unsigned int t100;
    unsigned int t101;
    unsigned int t102;
    char *t103;
    unsigned int t105;
    unsigned int t106;
    unsigned int t107;
    char *t108;
    char *t109;
    char *t110;
    unsigned int t111;
    unsigned int t112;
    unsigned int t113;
    unsigned int t114;
    unsigned int t115;
    unsigned int t116;
    unsigned int t117;
    char *t118;
    char *t119;
    unsigned int t120;
    unsigned int t121;
    unsigned int t122;
    unsigned int t123;
    unsigned int t124;
    unsigned int t125;
    unsigned int t126;
    unsigned int t127;
    int t128;
    int t129;
    unsigned int t130;
    unsigned int t131;
    unsigned int t132;
    unsigned int t133;
    unsigned int t134;
    unsigned int t135;
    char *t137;
    unsigned int t138;
    unsigned int t139;
    unsigned int t140;
    unsigned int t141;
    unsigned int t142;
    char *t143;
    char *t144;
    unsigned int t145;
    unsigned int t146;
    unsigned int t147;
    char *t148;
    char *t149;
    char *t150;
    char *t152;
    char *t153;
    unsigned int t154;
    unsigned int t155;
    unsigned int t156;
    unsigned int t157;
    unsigned int t158;
    unsigned int t159;
    char *t161;
    unsigned int t162;
    unsigned int t163;
    unsigned int t164;
    unsigned int t165;
    unsigned int t166;
    char *t167;
    unsigned int t169;
    unsigned int t170;
    unsigned int t171;
    char *t172;
    char *t173;
    char *t174;
    unsigned int t175;
    unsigned int t176;
    unsigned int t177;
    unsigned int t178;
    unsigned int t179;
    unsigned int t180;
    unsigned int t181;
    char *t182;
    char *t183;
    unsigned int t184;
    unsigned int t185;
    unsigned int t186;
    unsigned int t187;
    unsigned int t188;
    unsigned int t189;
    unsigned int t190;
    unsigned int t191;
    int t192;
    int t193;
    unsigned int t194;
    unsigned int t195;
    unsigned int t196;
    unsigned int t197;
    unsigned int t198;
    unsigned int t199;
    char *t201;
    unsigned int t202;
    unsigned int t203;
    unsigned int t204;
    unsigned int t205;
    unsigned int t206;
    char *t207;
    char *t208;
    unsigned int t209;
    unsigned int t210;
    unsigned int t211;
    unsigned int t212;
    char *t213;
    char *t214;
    char *t216;
    char *t217;
    unsigned int t218;
    unsigned int t219;
    unsigned int t220;
    unsigned int t221;
    unsigned int t222;
    unsigned int t223;
    unsigned int t224;
    unsigned int t225;
    unsigned int t226;
    unsigned int t227;
    unsigned int t228;
    unsigned int t229;
    char *t230;
    char *t232;
    unsigned int t233;
    unsigned int t234;
    unsigned int t235;
    unsigned int t236;
    unsigned int t237;
    char *t238;
    unsigned int t240;
    unsigned int t241;
    unsigned int t242;
    char *t243;
    char *t244;
    char *t245;
    unsigned int t246;
    unsigned int t247;
    unsigned int t248;
    unsigned int t249;
    unsigned int t250;
    unsigned int t251;
    unsigned int t252;
    char *t253;
    char *t254;
    unsigned int t255;
    unsigned int t256;
    unsigned int t257;
    int t258;
    unsigned int t259;
    unsigned int t260;
    unsigned int t261;
    int t262;
    unsigned int t263;
    unsigned int t264;
    unsigned int t265;
    unsigned int t266;
    char *t267;
    char *t268;
    char *t269;
    char *t270;
    char *t271;
    unsigned int t272;
    unsigned int t273;
    char *t274;
    unsigned int t275;
    unsigned int t276;
    char *t277;
    unsigned int t278;
    unsigned int t279;
    char *t280;

LAB0:    t1 = (t0 + 7040U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(67, ng0);
    t2 = (t0 + 1528U);
    t5 = *((char **)t2);
    memset(t4, 0, 8);
    t2 = (t4 + 4);
    t6 = (t5 + 4);
    t7 = *((unsigned int *)t5);
    t8 = (t7 >> 0);
    *((unsigned int *)t4) = t8;
    t9 = *((unsigned int *)t6);
    t10 = (t9 >> 0);
    *((unsigned int *)t2) = t10;
    t11 = *((unsigned int *)t4);
    *((unsigned int *)t4) = (t11 & 63U);
    t12 = *((unsigned int *)t2);
    *((unsigned int *)t2) = (t12 & 63U);
    t14 = (t0 + 4648);
    t15 = (t14 + 56U);
    t16 = *((char **)t15);
    memset(t13, 0, 8);
    t17 = (t13 + 4);
    t18 = (t16 + 4);
    t19 = *((unsigned int *)t16);
    t20 = (t19 >> 10);
    *((unsigned int *)t13) = t20;
    t21 = *((unsigned int *)t18);
    t22 = (t21 >> 10);
    *((unsigned int *)t17) = t22;
    t23 = *((unsigned int *)t13);
    *((unsigned int *)t13) = (t23 & 63U);
    t24 = *((unsigned int *)t17);
    *((unsigned int *)t17) = (t24 & 63U);
    t26 = *((unsigned int *)t4);
    t27 = *((unsigned int *)t13);
    t28 = (t26 & t27);
    *((unsigned int *)t25) = t28;
    t29 = (t4 + 4);
    t30 = (t13 + 4);
    t31 = (t25 + 4);
    t32 = *((unsigned int *)t29);
    t33 = *((unsigned int *)t30);
    t34 = (t32 | t33);
    *((unsigned int *)t31) = t34;
    t35 = *((unsigned int *)t31);
    t36 = (t35 != 0);
    if (t36 == 1)
        goto LAB4;

LAB5:
LAB6:    memset(t3, 0, 8);
    t57 = (t25 + 4);
    t58 = *((unsigned int *)t57);
    t59 = (~(t58));
    t60 = *((unsigned int *)t25);
    t61 = (t60 & t59);
    t62 = (t61 & 63U);
    if (t62 != 0)
        goto LAB7;

LAB8:    if (*((unsigned int *)t57) != 0)
        goto LAB9;

LAB10:    memset(t64, 0, 8);
    t65 = (t3 + 4);
    t66 = *((unsigned int *)t65);
    t67 = (~(t66));
    t68 = *((unsigned int *)t3);
    t69 = (t68 & t67);
    t70 = (t69 & 1U);
    if (t70 != 0)
        goto LAB11;

LAB12:    if (*((unsigned int *)t65) != 0)
        goto LAB13;

LAB14:    t72 = (t64 + 4);
    t73 = *((unsigned int *)t64);
    t74 = *((unsigned int *)t72);
    t75 = (t73 || t74);
    if (t75 > 0)
        goto LAB15;

LAB16:    memcpy(t104, t64, 8);

LAB17:    memset(t136, 0, 8);
    t137 = (t104 + 4);
    t138 = *((unsigned int *)t137);
    t139 = (~(t138));
    t140 = *((unsigned int *)t104);
    t141 = (t140 & t139);
    t142 = (t141 & 1U);
    if (t142 != 0)
        goto LAB29;

LAB30:    if (*((unsigned int *)t137) != 0)
        goto LAB31;

LAB32:    t144 = (t136 + 4);
    t145 = *((unsigned int *)t136);
    t146 = *((unsigned int *)t144);
    t147 = (t145 || t146);
    if (t147 > 0)
        goto LAB33;

LAB34:    memcpy(t168, t136, 8);

LAB35:    memset(t200, 0, 8);
    t201 = (t168 + 4);
    t202 = *((unsigned int *)t201);
    t203 = (~(t202));
    t204 = *((unsigned int *)t168);
    t205 = (t204 & t203);
    t206 = (t205 & 1U);
    if (t206 != 0)
        goto LAB43;

LAB44:    if (*((unsigned int *)t201) != 0)
        goto LAB45;

LAB46:    t208 = (t200 + 4);
    t209 = *((unsigned int *)t200);
    t210 = (!(t209));
    t211 = *((unsigned int *)t208);
    t212 = (t210 || t211);
    if (t212 > 0)
        goto LAB47;

LAB48:    memcpy(t239, t200, 8);

LAB49:    t267 = (t0 + 8288);
    t268 = (t267 + 56U);
    t269 = *((char **)t268);
    t270 = (t269 + 56U);
    t271 = *((char **)t270);
    memset(t271, 0, 8);
    t272 = 1U;
    t273 = t272;
    t274 = (t239 + 4);
    t275 = *((unsigned int *)t239);
    t272 = (t272 & t275);
    t276 = *((unsigned int *)t274);
    t273 = (t273 & t276);
    t277 = (t271 + 4);
    t278 = *((unsigned int *)t271);
    *((unsigned int *)t271) = (t278 | t272);
    t279 = *((unsigned int *)t277);
    *((unsigned int *)t277) = (t279 | t273);
    xsi_driver_vfirst_trans(t267, 0, 0);
    t280 = (t0 + 7920);
    *((int *)t280) = 1;

LAB1:    return;
LAB4:    t37 = *((unsigned int *)t25);
    t38 = *((unsigned int *)t31);
    *((unsigned int *)t25) = (t37 | t38);
    t39 = (t4 + 4);
    t40 = (t13 + 4);
    t41 = *((unsigned int *)t4);
    t42 = (~(t41));
    t43 = *((unsigned int *)t39);
    t44 = (~(t43));
    t45 = *((unsigned int *)t13);
    t46 = (~(t45));
    t47 = *((unsigned int *)t40);
    t48 = (~(t47));
    t49 = (t42 & t44);
    t50 = (t46 & t48);
    t51 = (~(t49));
    t52 = (~(t50));
    t53 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t53 & t51);
    t54 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t54 & t52);
    t55 = *((unsigned int *)t25);
    *((unsigned int *)t25) = (t55 & t51);
    t56 = *((unsigned int *)t25);
    *((unsigned int *)t25) = (t56 & t52);
    goto LAB6;

LAB7:    *((unsigned int *)t3) = 1;
    goto LAB10;

LAB9:    t63 = (t3 + 4);
    *((unsigned int *)t3) = 1;
    *((unsigned int *)t63) = 1;
    goto LAB10;

LAB11:    *((unsigned int *)t64) = 1;
    goto LAB14;

LAB13:    t71 = (t64 + 4);
    *((unsigned int *)t64) = 1;
    *((unsigned int *)t71) = 1;
    goto LAB14;

LAB15:    t77 = (t0 + 4648);
    t78 = (t77 + 56U);
    t79 = *((char **)t78);
    memset(t80, 0, 8);
    t81 = (t80 + 4);
    t82 = (t79 + 4);
    t83 = *((unsigned int *)t79);
    t84 = (t83 >> 1);
    t85 = (t84 & 1);
    *((unsigned int *)t80) = t85;
    t86 = *((unsigned int *)t82);
    t87 = (t86 >> 1);
    t88 = (t87 & 1);
    *((unsigned int *)t81) = t88;
    memset(t76, 0, 8);
    t89 = (t80 + 4);
    t90 = *((unsigned int *)t89);
    t91 = (~(t90));
    t92 = *((unsigned int *)t80);
    t93 = (t92 & t91);
    t94 = (t93 & 1U);
    if (t94 != 0)
        goto LAB21;

LAB19:    if (*((unsigned int *)t89) == 0)
        goto LAB18;

LAB20:    t95 = (t76 + 4);
    *((unsigned int *)t76) = 1;
    *((unsigned int *)t95) = 1;

LAB21:    memset(t96, 0, 8);
    t97 = (t76 + 4);
    t98 = *((unsigned int *)t97);
    t99 = (~(t98));
    t100 = *((unsigned int *)t76);
    t101 = (t100 & t99);
    t102 = (t101 & 1U);
    if (t102 != 0)
        goto LAB22;

LAB23:    if (*((unsigned int *)t97) != 0)
        goto LAB24;

LAB25:    t105 = *((unsigned int *)t64);
    t106 = *((unsigned int *)t96);
    t107 = (t105 & t106);
    *((unsigned int *)t104) = t107;
    t108 = (t64 + 4);
    t109 = (t96 + 4);
    t110 = (t104 + 4);
    t111 = *((unsigned int *)t108);
    t112 = *((unsigned int *)t109);
    t113 = (t111 | t112);
    *((unsigned int *)t110) = t113;
    t114 = *((unsigned int *)t110);
    t115 = (t114 != 0);
    if (t115 == 1)
        goto LAB26;

LAB27:
LAB28:    goto LAB17;

LAB18:    *((unsigned int *)t76) = 1;
    goto LAB21;

LAB22:    *((unsigned int *)t96) = 1;
    goto LAB25;

LAB24:    t103 = (t96 + 4);
    *((unsigned int *)t96) = 1;
    *((unsigned int *)t103) = 1;
    goto LAB25;

LAB26:    t116 = *((unsigned int *)t104);
    t117 = *((unsigned int *)t110);
    *((unsigned int *)t104) = (t116 | t117);
    t118 = (t64 + 4);
    t119 = (t96 + 4);
    t120 = *((unsigned int *)t64);
    t121 = (~(t120));
    t122 = *((unsigned int *)t118);
    t123 = (~(t122));
    t124 = *((unsigned int *)t96);
    t125 = (~(t124));
    t126 = *((unsigned int *)t119);
    t127 = (~(t126));
    t128 = (t121 & t123);
    t129 = (t125 & t127);
    t130 = (~(t128));
    t131 = (~(t129));
    t132 = *((unsigned int *)t110);
    *((unsigned int *)t110) = (t132 & t130);
    t133 = *((unsigned int *)t110);
    *((unsigned int *)t110) = (t133 & t131);
    t134 = *((unsigned int *)t104);
    *((unsigned int *)t104) = (t134 & t130);
    t135 = *((unsigned int *)t104);
    *((unsigned int *)t104) = (t135 & t131);
    goto LAB28;

LAB29:    *((unsigned int *)t136) = 1;
    goto LAB32;

LAB31:    t143 = (t136 + 4);
    *((unsigned int *)t136) = 1;
    *((unsigned int *)t143) = 1;
    goto LAB32;

LAB33:    t148 = (t0 + 4648);
    t149 = (t148 + 56U);
    t150 = *((char **)t149);
    memset(t151, 0, 8);
    t152 = (t151 + 4);
    t153 = (t150 + 4);
    t154 = *((unsigned int *)t150);
    t155 = (t154 >> 0);
    t156 = (t155 & 1);
    *((unsigned int *)t151) = t156;
    t157 = *((unsigned int *)t153);
    t158 = (t157 >> 0);
    t159 = (t158 & 1);
    *((unsigned int *)t152) = t159;
    memset(t160, 0, 8);
    t161 = (t151 + 4);
    t162 = *((unsigned int *)t161);
    t163 = (~(t162));
    t164 = *((unsigned int *)t151);
    t165 = (t164 & t163);
    t166 = (t165 & 1U);
    if (t166 != 0)
        goto LAB36;

LAB37:    if (*((unsigned int *)t161) != 0)
        goto LAB38;

LAB39:    t169 = *((unsigned int *)t136);
    t170 = *((unsigned int *)t160);
    t171 = (t169 & t170);
    *((unsigned int *)t168) = t171;
    t172 = (t136 + 4);
    t173 = (t160 + 4);
    t174 = (t168 + 4);
    t175 = *((unsigned int *)t172);
    t176 = *((unsigned int *)t173);
    t177 = (t175 | t176);
    *((unsigned int *)t174) = t177;
    t178 = *((unsigned int *)t174);
    t179 = (t178 != 0);
    if (t179 == 1)
        goto LAB40;

LAB41:
LAB42:    goto LAB35;

LAB36:    *((unsigned int *)t160) = 1;
    goto LAB39;

LAB38:    t167 = (t160 + 4);
    *((unsigned int *)t160) = 1;
    *((unsigned int *)t167) = 1;
    goto LAB39;

LAB40:    t180 = *((unsigned int *)t168);
    t181 = *((unsigned int *)t174);
    *((unsigned int *)t168) = (t180 | t181);
    t182 = (t136 + 4);
    t183 = (t160 + 4);
    t184 = *((unsigned int *)t136);
    t185 = (~(t184));
    t186 = *((unsigned int *)t182);
    t187 = (~(t186));
    t188 = *((unsigned int *)t160);
    t189 = (~(t188));
    t190 = *((unsigned int *)t183);
    t191 = (~(t190));
    t192 = (t185 & t187);
    t193 = (t189 & t191);
    t194 = (~(t192));
    t195 = (~(t193));
    t196 = *((unsigned int *)t174);
    *((unsigned int *)t174) = (t196 & t194);
    t197 = *((unsigned int *)t174);
    *((unsigned int *)t174) = (t197 & t195);
    t198 = *((unsigned int *)t168);
    *((unsigned int *)t168) = (t198 & t194);
    t199 = *((unsigned int *)t168);
    *((unsigned int *)t168) = (t199 & t195);
    goto LAB42;

LAB43:    *((unsigned int *)t200) = 1;
    goto LAB46;

LAB45:    t207 = (t200 + 4);
    *((unsigned int *)t200) = 1;
    *((unsigned int *)t207) = 1;
    goto LAB46;

LAB47:    t213 = (t0 + 1368U);
    t214 = *((char **)t213);
    t213 = ((char*)((ng1)));
    memset(t215, 0, 8);
    t216 = (t214 + 4);
    t217 = (t213 + 4);
    t218 = *((unsigned int *)t214);
    t219 = *((unsigned int *)t213);
    t220 = (t218 ^ t219);
    t221 = *((unsigned int *)t216);
    t222 = *((unsigned int *)t217);
    t223 = (t221 ^ t222);
    t224 = (t220 | t223);
    t225 = *((unsigned int *)t216);
    t226 = *((unsigned int *)t217);
    t227 = (t225 | t226);
    t228 = (~(t227));
    t229 = (t224 & t228);
    if (t229 != 0)
        goto LAB51;

LAB50:    if (t227 != 0)
        goto LAB52;

LAB53:    memset(t231, 0, 8);
    t232 = (t215 + 4);
    t233 = *((unsigned int *)t232);
    t234 = (~(t233));
    t235 = *((unsigned int *)t215);
    t236 = (t235 & t234);
    t237 = (t236 & 1U);
    if (t237 != 0)
        goto LAB54;

LAB55:    if (*((unsigned int *)t232) != 0)
        goto LAB56;

LAB57:    t240 = *((unsigned int *)t200);
    t241 = *((unsigned int *)t231);
    t242 = (t240 | t241);
    *((unsigned int *)t239) = t242;
    t243 = (t200 + 4);
    t244 = (t231 + 4);
    t245 = (t239 + 4);
    t246 = *((unsigned int *)t243);
    t247 = *((unsigned int *)t244);
    t248 = (t246 | t247);
    *((unsigned int *)t245) = t248;
    t249 = *((unsigned int *)t245);
    t250 = (t249 != 0);
    if (t250 == 1)
        goto LAB58;

LAB59:
LAB60:    goto LAB49;

LAB51:    *((unsigned int *)t215) = 1;
    goto LAB53;

LAB52:    t230 = (t215 + 4);
    *((unsigned int *)t215) = 1;
    *((unsigned int *)t230) = 1;
    goto LAB53;

LAB54:    *((unsigned int *)t231) = 1;
    goto LAB57;

LAB56:    t238 = (t231 + 4);
    *((unsigned int *)t231) = 1;
    *((unsigned int *)t238) = 1;
    goto LAB57;

LAB58:    t251 = *((unsigned int *)t239);
    t252 = *((unsigned int *)t245);
    *((unsigned int *)t239) = (t251 | t252);
    t253 = (t200 + 4);
    t254 = (t231 + 4);
    t255 = *((unsigned int *)t253);
    t256 = (~(t255));
    t257 = *((unsigned int *)t200);
    t258 = (t257 & t256);
    t259 = *((unsigned int *)t254);
    t260 = (~(t259));
    t261 = *((unsigned int *)t231);
    t262 = (t261 & t260);
    t263 = (~(t258));
    t264 = (~(t262));
    t265 = *((unsigned int *)t245);
    *((unsigned int *)t245) = (t265 & t263);
    t266 = *((unsigned int *)t245);
    *((unsigned int *)t245) = (t266 & t264);
    goto LAB60;

}

static void Cont_68_5(char *t0)
{
    char t3[8];
    char t4[8];
    char t6[8];
    char t40[8];
    char t41[8];
    char t44[8];
    char t78[8];
    char t79[8];
    char t82[8];
    char t116[8];
    char t117[8];
    char t120[8];
    char *t1;
    char *t2;
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
    unsigned int t30;
    unsigned int t31;
    unsigned int t32;
    char *t33;
    char *t34;
    char *t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    unsigned int t39;
    char *t42;
    char *t43;
    char *t45;
    char *t46;
    unsigned int t47;
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
    char *t59;
    char *t60;
    unsigned int t61;
    unsigned int t62;
    unsigned int t63;
    unsigned int t64;
    unsigned int t65;
    char *t66;
    char *t67;
    unsigned int t68;
    unsigned int t69;
    unsigned int t70;
    char *t71;
    char *t72;
    char *t73;
    unsigned int t74;
    unsigned int t75;
    unsigned int t76;
    unsigned int t77;
    char *t80;
    char *t81;
    char *t83;
    char *t84;
    unsigned int t85;
    unsigned int t86;
    unsigned int t87;
    unsigned int t88;
    unsigned int t89;
    unsigned int t90;
    unsigned int t91;
    unsigned int t92;
    unsigned int t93;
    unsigned int t94;
    unsigned int t95;
    unsigned int t96;
    char *t97;
    char *t98;
    unsigned int t99;
    unsigned int t100;
    unsigned int t101;
    unsigned int t102;
    unsigned int t103;
    char *t104;
    char *t105;
    unsigned int t106;
    unsigned int t107;
    unsigned int t108;
    char *t109;
    char *t110;
    char *t111;
    unsigned int t112;
    unsigned int t113;
    unsigned int t114;
    unsigned int t115;
    char *t118;
    char *t119;
    char *t121;
    char *t122;
    unsigned int t123;
    unsigned int t124;
    unsigned int t125;
    unsigned int t126;
    unsigned int t127;
    unsigned int t128;
    unsigned int t129;
    unsigned int t130;
    unsigned int t131;
    unsigned int t132;
    unsigned int t133;
    unsigned int t134;
    char *t135;
    char *t136;
    unsigned int t137;
    unsigned int t138;
    unsigned int t139;
    unsigned int t140;
    unsigned int t141;
    char *t142;
    char *t143;
    unsigned int t144;
    unsigned int t145;
    unsigned int t146;
    char *t147;
    char *t148;
    char *t149;
    unsigned int t150;
    unsigned int t151;
    unsigned int t152;
    unsigned int t153;
    char *t154;
    char *t155;
    char *t156;
    char *t157;
    char *t158;
    char *t159;
    char *t160;

LAB0:    t1 = (t0 + 7288U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(68, ng0);
    t2 = (t0 + 3128U);
    t5 = *((char **)t2);
    t2 = ((char*)((ng2)));
    memset(t6, 0, 8);
    t7 = (t5 + 4);
    t8 = (t2 + 4);
    t9 = *((unsigned int *)t5);
    t10 = *((unsigned int *)t2);
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
        goto LAB7;

LAB4:    if (t18 != 0)
        goto LAB6;

LAB5:    *((unsigned int *)t6) = 1;

LAB7:    memset(t4, 0, 8);
    t22 = (t6 + 4);
    t23 = *((unsigned int *)t22);
    t24 = (~(t23));
    t25 = *((unsigned int *)t6);
    t26 = (t25 & t24);
    t27 = (t26 & 1U);
    if (t27 != 0)
        goto LAB8;

LAB9:    if (*((unsigned int *)t22) != 0)
        goto LAB10;

LAB11:    t29 = (t4 + 4);
    t30 = *((unsigned int *)t4);
    t31 = *((unsigned int *)t29);
    t32 = (t30 || t31);
    if (t32 > 0)
        goto LAB12;

LAB13:    t36 = *((unsigned int *)t4);
    t37 = (~(t36));
    t38 = *((unsigned int *)t29);
    t39 = (t37 || t38);
    if (t39 > 0)
        goto LAB14;

LAB15:    if (*((unsigned int *)t29) > 0)
        goto LAB16;

LAB17:    if (*((unsigned int *)t4) > 0)
        goto LAB18;

LAB19:    memcpy(t3, t40, 8);

LAB20:    t155 = (t0 + 8352);
    t156 = (t155 + 56U);
    t157 = *((char **)t156);
    t158 = (t157 + 56U);
    t159 = *((char **)t158);
    memcpy(t159, t3, 8);
    xsi_driver_vfirst_trans(t155, 0, 31);
    t160 = (t0 + 7936);
    *((int *)t160) = 1;

LAB1:    return;
LAB6:    t21 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t21) = 1;
    goto LAB7;

LAB8:    *((unsigned int *)t4) = 1;
    goto LAB11;

LAB10:    t28 = (t4 + 4);
    *((unsigned int *)t4) = 1;
    *((unsigned int *)t28) = 1;
    goto LAB11;

LAB12:    t33 = (t0 + 4648);
    t34 = (t33 + 56U);
    t35 = *((char **)t34);
    goto LAB13;

LAB14:    t42 = (t0 + 3128U);
    t43 = *((char **)t42);
    t42 = ((char*)((ng3)));
    memset(t44, 0, 8);
    t45 = (t43 + 4);
    t46 = (t42 + 4);
    t47 = *((unsigned int *)t43);
    t48 = *((unsigned int *)t42);
    t49 = (t47 ^ t48);
    t50 = *((unsigned int *)t45);
    t51 = *((unsigned int *)t46);
    t52 = (t50 ^ t51);
    t53 = (t49 | t52);
    t54 = *((unsigned int *)t45);
    t55 = *((unsigned int *)t46);
    t56 = (t54 | t55);
    t57 = (~(t56));
    t58 = (t53 & t57);
    if (t58 != 0)
        goto LAB24;

LAB21:    if (t56 != 0)
        goto LAB23;

LAB22:    *((unsigned int *)t44) = 1;

LAB24:    memset(t41, 0, 8);
    t60 = (t44 + 4);
    t61 = *((unsigned int *)t60);
    t62 = (~(t61));
    t63 = *((unsigned int *)t44);
    t64 = (t63 & t62);
    t65 = (t64 & 1U);
    if (t65 != 0)
        goto LAB25;

LAB26:    if (*((unsigned int *)t60) != 0)
        goto LAB27;

LAB28:    t67 = (t41 + 4);
    t68 = *((unsigned int *)t41);
    t69 = *((unsigned int *)t67);
    t70 = (t68 || t69);
    if (t70 > 0)
        goto LAB29;

LAB30:    t74 = *((unsigned int *)t41);
    t75 = (~(t74));
    t76 = *((unsigned int *)t67);
    t77 = (t75 || t76);
    if (t77 > 0)
        goto LAB31;

LAB32:    if (*((unsigned int *)t67) > 0)
        goto LAB33;

LAB34:    if (*((unsigned int *)t41) > 0)
        goto LAB35;

LAB36:    memcpy(t40, t78, 8);

LAB37:    goto LAB15;

LAB16:    xsi_vlog_unsigned_bit_combine(t3, 32, t35, 32, t40, 32);
    goto LAB20;

LAB18:    memcpy(t3, t35, 8);
    goto LAB20;

LAB23:    t59 = (t44 + 4);
    *((unsigned int *)t44) = 1;
    *((unsigned int *)t59) = 1;
    goto LAB24;

LAB25:    *((unsigned int *)t41) = 1;
    goto LAB28;

LAB27:    t66 = (t41 + 4);
    *((unsigned int *)t41) = 1;
    *((unsigned int *)t66) = 1;
    goto LAB28;

LAB29:    t71 = (t0 + 4808);
    t72 = (t71 + 56U);
    t73 = *((char **)t72);
    goto LAB30;

LAB31:    t80 = (t0 + 3128U);
    t81 = *((char **)t80);
    t80 = ((char*)((ng4)));
    memset(t82, 0, 8);
    t83 = (t81 + 4);
    t84 = (t80 + 4);
    t85 = *((unsigned int *)t81);
    t86 = *((unsigned int *)t80);
    t87 = (t85 ^ t86);
    t88 = *((unsigned int *)t83);
    t89 = *((unsigned int *)t84);
    t90 = (t88 ^ t89);
    t91 = (t87 | t90);
    t92 = *((unsigned int *)t83);
    t93 = *((unsigned int *)t84);
    t94 = (t92 | t93);
    t95 = (~(t94));
    t96 = (t91 & t95);
    if (t96 != 0)
        goto LAB41;

LAB38:    if (t94 != 0)
        goto LAB40;

LAB39:    *((unsigned int *)t82) = 1;

LAB41:    memset(t79, 0, 8);
    t98 = (t82 + 4);
    t99 = *((unsigned int *)t98);
    t100 = (~(t99));
    t101 = *((unsigned int *)t82);
    t102 = (t101 & t100);
    t103 = (t102 & 1U);
    if (t103 != 0)
        goto LAB42;

LAB43:    if (*((unsigned int *)t98) != 0)
        goto LAB44;

LAB45:    t105 = (t79 + 4);
    t106 = *((unsigned int *)t79);
    t107 = *((unsigned int *)t105);
    t108 = (t106 || t107);
    if (t108 > 0)
        goto LAB46;

LAB47:    t112 = *((unsigned int *)t79);
    t113 = (~(t112));
    t114 = *((unsigned int *)t105);
    t115 = (t113 || t114);
    if (t115 > 0)
        goto LAB48;

LAB49:    if (*((unsigned int *)t105) > 0)
        goto LAB50;

LAB51:    if (*((unsigned int *)t79) > 0)
        goto LAB52;

LAB53:    memcpy(t78, t116, 8);

LAB54:    goto LAB32;

LAB33:    xsi_vlog_unsigned_bit_combine(t40, 32, t73, 32, t78, 32);
    goto LAB37;

LAB35:    memcpy(t40, t73, 8);
    goto LAB37;

LAB40:    t97 = (t82 + 4);
    *((unsigned int *)t82) = 1;
    *((unsigned int *)t97) = 1;
    goto LAB41;

LAB42:    *((unsigned int *)t79) = 1;
    goto LAB45;

LAB44:    t104 = (t79 + 4);
    *((unsigned int *)t79) = 1;
    *((unsigned int *)t104) = 1;
    goto LAB45;

LAB46:    t109 = (t0 + 4968);
    t110 = (t109 + 56U);
    t111 = *((char **)t110);
    goto LAB47;

LAB48:    t118 = (t0 + 3128U);
    t119 = *((char **)t118);
    t118 = ((char*)((ng5)));
    memset(t120, 0, 8);
    t121 = (t119 + 4);
    t122 = (t118 + 4);
    t123 = *((unsigned int *)t119);
    t124 = *((unsigned int *)t118);
    t125 = (t123 ^ t124);
    t126 = *((unsigned int *)t121);
    t127 = *((unsigned int *)t122);
    t128 = (t126 ^ t127);
    t129 = (t125 | t128);
    t130 = *((unsigned int *)t121);
    t131 = *((unsigned int *)t122);
    t132 = (t130 | t131);
    t133 = (~(t132));
    t134 = (t129 & t133);
    if (t134 != 0)
        goto LAB58;

LAB55:    if (t132 != 0)
        goto LAB57;

LAB56:    *((unsigned int *)t120) = 1;

LAB58:    memset(t117, 0, 8);
    t136 = (t120 + 4);
    t137 = *((unsigned int *)t136);
    t138 = (~(t137));
    t139 = *((unsigned int *)t120);
    t140 = (t139 & t138);
    t141 = (t140 & 1U);
    if (t141 != 0)
        goto LAB59;

LAB60:    if (*((unsigned int *)t136) != 0)
        goto LAB61;

LAB62:    t143 = (t117 + 4);
    t144 = *((unsigned int *)t117);
    t145 = *((unsigned int *)t143);
    t146 = (t144 || t145);
    if (t146 > 0)
        goto LAB63;

LAB64:    t150 = *((unsigned int *)t117);
    t151 = (~(t150));
    t152 = *((unsigned int *)t143);
    t153 = (t151 || t152);
    if (t153 > 0)
        goto LAB65;

LAB66:    if (*((unsigned int *)t143) > 0)
        goto LAB67;

LAB68:    if (*((unsigned int *)t117) > 0)
        goto LAB69;

LAB70:    memcpy(t116, t154, 8);

LAB71:    goto LAB49;

LAB50:    xsi_vlog_unsigned_bit_combine(t78, 32, t111, 32, t116, 32);
    goto LAB54;

LAB52:    memcpy(t78, t111, 8);
    goto LAB54;

LAB57:    t135 = (t120 + 4);
    *((unsigned int *)t120) = 1;
    *((unsigned int *)t135) = 1;
    goto LAB58;

LAB59:    *((unsigned int *)t117) = 1;
    goto LAB62;

LAB61:    t142 = (t117 + 4);
    *((unsigned int *)t117) = 1;
    *((unsigned int *)t142) = 1;
    goto LAB62;

LAB63:    t147 = (t0 + 5128);
    t148 = (t147 + 56U);
    t149 = *((char **)t148);
    goto LAB64;

LAB65:    t154 = ((char*)((ng6)));
    goto LAB66;

LAB67:    xsi_vlog_unsigned_bit_combine(t116, 32, t149, 32, t154, 32);
    goto LAB71;

LAB69:    memcpy(t116, t149, 8);
    goto LAB71;

}

static void Always_74_6(char *t0)
{
    char t14[8];
    char t23[8];
    char t24[8];
    char t25[8];
    char t60[8];
    char t66[16];
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
    int t13;
    char *t15;
    unsigned int t16;
    unsigned int t17;
    unsigned int t18;
    unsigned int t19;
    unsigned int t20;
    unsigned int t21;
    char *t22;
    char *t26;
    char *t27;
    char *t28;
    char *t29;
    char *t30;
    char *t31;
    unsigned int t32;
    int t33;
    char *t34;
    unsigned int t35;
    int t36;
    int t37;
    char *t38;
    unsigned int t39;
    int t40;
    int t41;
    unsigned int t42;
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
    char *t61;
    char *t62;
    char *t63;
    char *t64;
    unsigned int t65;

LAB0:    t1 = (t0 + 7536U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(74, ng0);
    t2 = (t0 + 7952);
    *((int *)t2) = 1;
    t3 = (t0 + 7568);
    *((char **)t3) = t2;
    *((char **)t1) = &&LAB4;

LAB1:    return;
LAB4:    xsi_set_current_line(74, ng0);

LAB5:    xsi_set_current_line(75, ng0);
    t4 = (t0 + 2008U);
    t5 = *((char **)t4);
    t4 = (t5 + 4);
    t6 = *((unsigned int *)t4);
    t7 = (~(t6));
    t8 = *((unsigned int *)t5);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB6;

LAB7:    xsi_set_current_line(80, ng0);
    t2 = (t0 + 3448U);
    t3 = *((char **)t2);
    t2 = (t3 + 4);
    t6 = *((unsigned int *)t2);
    t7 = (~(t6));
    t8 = *((unsigned int *)t3);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB10;

LAB11:    xsi_set_current_line(92, ng0);

LAB29:    xsi_set_current_line(93, ng0);
    t2 = (t0 + 4088U);
    t3 = *((char **)t2);
    t2 = (t3 + 4);
    t6 = *((unsigned int *)t2);
    t7 = (~(t6));
    t8 = *((unsigned int *)t3);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB30;

LAB31:    xsi_set_current_line(95, ng0);
    t2 = (t0 + 4248U);
    t3 = *((char **)t2);
    t2 = (t3 + 4);
    t6 = *((unsigned int *)t2);
    t7 = (~(t6));
    t8 = *((unsigned int *)t3);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB35;

LAB36:
LAB37:
LAB32:    xsi_set_current_line(98, ng0);
    t2 = (t0 + 4248U);
    t3 = *((char **)t2);
    t2 = (t3 + 4);
    t6 = *((unsigned int *)t2);
    t7 = (~(t6));
    t8 = *((unsigned int *)t3);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB40;

LAB41:    xsi_set_current_line(106, ng0);
    t2 = (t0 + 1528U);
    t3 = *((char **)t2);
    t2 = (t0 + 4808);
    t4 = (t0 + 4808);
    t11 = (t4 + 72U);
    t12 = *((char **)t11);
    t15 = ((char*)((ng5)));
    t22 = ((char*)((ng7)));
    xsi_vlog_convert_partindices(t14, t23, t24, ((int*)(t12)), 2, t15, 32, 1, t22, 32, 1);
    t26 = (t14 + 4);
    t6 = *((unsigned int *)t26);
    t13 = (!(t6));
    t27 = (t23 + 4);
    t7 = *((unsigned int *)t27);
    t33 = (!(t7));
    t36 = (t13 && t33);
    t28 = (t24 + 4);
    t8 = *((unsigned int *)t28);
    t37 = (!(t8));
    t40 = (t36 && t37);
    if (t40 == 1)
        goto LAB76;

LAB77:
LAB42:    xsi_set_current_line(108, ng0);
    t2 = (t0 + 4248U);
    t3 = *((char **)t2);
    t2 = (t3 + 4);
    t6 = *((unsigned int *)t2);
    t7 = (~(t6));
    t8 = *((unsigned int *)t3);
    t9 = (t8 & t7);
    t10 = (t9 != 0);
    if (t10 > 0)
        goto LAB78;

LAB79:
LAB80:
LAB12:
LAB8:    goto LAB2;

LAB6:    xsi_set_current_line(75, ng0);

LAB9:    xsi_set_current_line(76, ng0);
    t11 = ((char*)((ng1)));
    t12 = (t0 + 4648);
    xsi_vlogvar_assign_value(t12, t11, 0, 0, 32);
    xsi_set_current_line(77, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 4808);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    xsi_set_current_line(78, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 4968);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    goto LAB8;

LAB10:    xsi_set_current_line(80, ng0);

LAB13:    xsi_set_current_line(81, ng0);
    t4 = (t0 + 3128U);
    t5 = *((char **)t4);

LAB14:    t4 = ((char*)((ng2)));
    t13 = xsi_vlog_unsigned_case_compare(t5, 5, t4, 32);
    if (t13 == 1)
        goto LAB15;

LAB16:    t2 = ((char*)((ng4)));
    t13 = xsi_vlog_unsigned_case_compare(t5, 5, t2, 32);
    if (t13 == 1)
        goto LAB17;

LAB18:
LAB20:
LAB19:
LAB21:    goto LAB12;

LAB15:    xsi_set_current_line(82, ng0);

LAB22:    xsi_set_current_line(83, ng0);
    t11 = (t0 + 1048U);
    t12 = *((char **)t11);
    memset(t14, 0, 8);
    t11 = (t14 + 4);
    t15 = (t12 + 4);
    t16 = *((unsigned int *)t12);
    t17 = (t16 >> 10);
    *((unsigned int *)t14) = t17;
    t18 = *((unsigned int *)t15);
    t19 = (t18 >> 10);
    *((unsigned int *)t11) = t19;
    t20 = *((unsigned int *)t14);
    *((unsigned int *)t14) = (t20 & 63U);
    t21 = *((unsigned int *)t11);
    *((unsigned int *)t11) = (t21 & 63U);
    t22 = (t0 + 4648);
    t26 = (t0 + 4648);
    t27 = (t26 + 72U);
    t28 = *((char **)t27);
    t29 = ((char*)((ng5)));
    t30 = ((char*)((ng7)));
    xsi_vlog_convert_partindices(t23, t24, t25, ((int*)(t28)), 2, t29, 32, 1, t30, 32, 1);
    t31 = (t23 + 4);
    t32 = *((unsigned int *)t31);
    t33 = (!(t32));
    t34 = (t24 + 4);
    t35 = *((unsigned int *)t34);
    t36 = (!(t35));
    t37 = (t33 && t36);
    t38 = (t25 + 4);
    t39 = *((unsigned int *)t38);
    t40 = (!(t39));
    t41 = (t37 && t40);
    if (t41 == 1)
        goto LAB23;

LAB24:    xsi_set_current_line(84, ng0);
    t2 = (t0 + 1048U);
    t3 = *((char **)t2);
    memset(t14, 0, 8);
    t2 = (t14 + 4);
    t4 = (t3 + 4);
    t6 = *((unsigned int *)t3);
    t7 = (t6 >> 1);
    t8 = (t7 & 1);
    *((unsigned int *)t14) = t8;
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 1);
    t16 = (t10 & 1);
    *((unsigned int *)t2) = t16;
    t11 = (t0 + 4648);
    t12 = (t0 + 4648);
    t15 = (t12 + 72U);
    t22 = *((char **)t15);
    t26 = ((char*)((ng8)));
    xsi_vlog_generic_convert_bit_index(t23, t22, 2, t26, 32, 1);
    t27 = (t23 + 4);
    t17 = *((unsigned int *)t27);
    t13 = (!(t17));
    if (t13 == 1)
        goto LAB25;

LAB26:    xsi_set_current_line(85, ng0);
    t2 = (t0 + 1048U);
    t3 = *((char **)t2);
    memset(t14, 0, 8);
    t2 = (t14 + 4);
    t4 = (t3 + 4);
    t6 = *((unsigned int *)t3);
    t7 = (t6 >> 0);
    t8 = (t7 & 1);
    *((unsigned int *)t14) = t8;
    t9 = *((unsigned int *)t4);
    t10 = (t9 >> 0);
    t16 = (t10 & 1);
    *((unsigned int *)t2) = t16;
    t11 = (t0 + 4648);
    t12 = (t0 + 4648);
    t15 = (t12 + 72U);
    t22 = *((char **)t15);
    t26 = ((char*)((ng1)));
    xsi_vlog_generic_convert_bit_index(t23, t22, 2, t26, 32, 1);
    t27 = (t23 + 4);
    t17 = *((unsigned int *)t27);
    t13 = (!(t17));
    if (t13 == 1)
        goto LAB27;

LAB28:    goto LAB21;

LAB17:    xsi_set_current_line(87, ng0);
    t3 = (t0 + 1048U);
    t4 = *((char **)t3);
    t3 = (t0 + 4968);
    xsi_vlogvar_assign_value(t3, t4, 0, 0, 32);
    goto LAB21;

LAB23:    t42 = *((unsigned int *)t25);
    t43 = (t42 + 0);
    t44 = *((unsigned int *)t23);
    t45 = *((unsigned int *)t24);
    t46 = (t44 - t45);
    t47 = (t46 + 1);
    xsi_vlogvar_assign_value(t22, t14, t43, *((unsigned int *)t24), t47);
    goto LAB24;

LAB25:    xsi_vlogvar_assign_value(t11, t14, 0, *((unsigned int *)t23), 1);
    goto LAB26;

LAB27:    xsi_vlogvar_assign_value(t11, t14, 0, *((unsigned int *)t23), 1);
    goto LAB28;

LAB30:    xsi_set_current_line(94, ng0);
    t4 = ((char*)((ng1)));
    t11 = (t0 + 4648);
    t12 = (t0 + 4648);
    t15 = (t12 + 72U);
    t22 = *((char **)t15);
    t26 = ((char*)((ng8)));
    xsi_vlog_generic_convert_bit_index(t14, t22, 2, t26, 32, 1);
    t27 = (t14 + 4);
    t16 = *((unsigned int *)t27);
    t13 = (!(t16));
    if (t13 == 1)
        goto LAB33;

LAB34:    goto LAB32;

LAB33:    xsi_vlogvar_assign_value(t11, t4, 0, *((unsigned int *)t14), 1);
    goto LAB34;

LAB35:    xsi_set_current_line(96, ng0);
    t4 = ((char*)((ng8)));
    t11 = (t0 + 4648);
    t12 = (t0 + 4648);
    t15 = (t12 + 72U);
    t22 = *((char **)t15);
    t26 = ((char*)((ng8)));
    xsi_vlog_generic_convert_bit_index(t14, t22, 2, t26, 32, 1);
    t27 = (t14 + 4);
    t16 = *((unsigned int *)t27);
    t13 = (!(t16));
    if (t13 == 1)
        goto LAB38;

LAB39:    goto LAB37;

LAB38:    xsi_vlogvar_assign_value(t11, t4, 0, *((unsigned int *)t14), 1);
    goto LAB39;

LAB40:    xsi_set_current_line(98, ng0);

LAB43:    xsi_set_current_line(99, ng0);
    t4 = (t0 + 4088U);
    t11 = *((char **)t4);
    memset(t23, 0, 8);
    t4 = (t11 + 4);
    t16 = *((unsigned int *)t4);
    t17 = (~(t16));
    t18 = *((unsigned int *)t11);
    t19 = (t18 & t17);
    t20 = (t19 & 1U);
    if (t20 != 0)
        goto LAB44;

LAB45:    if (*((unsigned int *)t4) != 0)
        goto LAB46;

LAB47:    t15 = (t23 + 4);
    t21 = *((unsigned int *)t23);
    t32 = *((unsigned int *)t15);
    t35 = (t21 || t32);
    if (t35 > 0)
        goto LAB48;

LAB49:    t39 = *((unsigned int *)t23);
    t42 = (~(t39));
    t44 = *((unsigned int *)t15);
    t45 = (t42 || t44);
    if (t45 > 0)
        goto LAB50;

LAB51:    if (*((unsigned int *)t15) > 0)
        goto LAB52;

LAB53:    if (*((unsigned int *)t23) > 0)
        goto LAB54;

LAB55:    memcpy(t14, t24, 8);

LAB56:    t34 = (t0 + 4808);
    t38 = (t0 + 4808);
    t61 = (t38 + 72U);
    t62 = *((char **)t61);
    t63 = ((char*)((ng9)));
    xsi_vlog_generic_convert_bit_index(t60, t62, 2, t63, 32, 1);
    t64 = (t60 + 4);
    t65 = *((unsigned int *)t64);
    t13 = (!(t65));
    if (t13 == 1)
        goto LAB70;

LAB71:    xsi_set_current_line(102, ng0);
    t2 = (t0 + 1528U);
    t3 = *((char **)t2);
    t2 = (t0 + 4808);
    t4 = (t0 + 4808);
    t11 = (t4 + 72U);
    t12 = *((char **)t11);
    t15 = ((char*)((ng5)));
    t22 = ((char*)((ng7)));
    xsi_vlog_convert_partindices(t14, t23, t24, ((int*)(t12)), 2, t15, 32, 1, t22, 32, 1);
    t26 = (t14 + 4);
    t6 = *((unsigned int *)t26);
    t13 = (!(t6));
    t27 = (t23 + 4);
    t7 = *((unsigned int *)t27);
    t33 = (!(t7));
    t36 = (t13 && t33);
    t28 = (t24 + 4);
    t8 = *((unsigned int *)t28);
    t37 = (!(t8));
    t40 = (t36 && t37);
    if (t40 == 1)
        goto LAB72;

LAB73:    xsi_set_current_line(103, ng0);
    t2 = (t0 + 1368U);
    t3 = *((char **)t2);
    t2 = (t0 + 4808);
    t4 = (t0 + 4808);
    t11 = (t4 + 72U);
    t12 = *((char **)t11);
    t15 = ((char*)((ng10)));
    t22 = ((char*)((ng11)));
    xsi_vlog_convert_partindices(t14, t23, t24, ((int*)(t12)), 2, t15, 32, 1, t22, 32, 1);
    t26 = (t14 + 4);
    t6 = *((unsigned int *)t26);
    t13 = (!(t6));
    t27 = (t23 + 4);
    t7 = *((unsigned int *)t27);
    t33 = (!(t7));
    t36 = (t13 && t33);
    t28 = (t24 + 4);
    t8 = *((unsigned int *)t28);
    t37 = (!(t8));
    t40 = (t36 && t37);
    if (t40 == 1)
        goto LAB74;

LAB75:    goto LAB42;

LAB44:    *((unsigned int *)t23) = 1;
    goto LAB47;

LAB46:    t12 = (t23 + 4);
    *((unsigned int *)t23) = 1;
    *((unsigned int *)t12) = 1;
    goto LAB47;

LAB48:    t22 = ((char*)((ng1)));
    goto LAB49;

LAB50:    t26 = (t0 + 3288U);
    t27 = *((char **)t26);
    memset(t25, 0, 8);
    t26 = (t27 + 4);
    t48 = *((unsigned int *)t26);
    t49 = (~(t48));
    t50 = *((unsigned int *)t27);
    t51 = (t50 & t49);
    t52 = (t51 & 1U);
    if (t52 != 0)
        goto LAB57;

LAB58:    if (*((unsigned int *)t26) != 0)
        goto LAB59;

LAB60:    t29 = (t25 + 4);
    t53 = *((unsigned int *)t25);
    t54 = *((unsigned int *)t29);
    t55 = (t53 || t54);
    if (t55 > 0)
        goto LAB61;

LAB62:    t56 = *((unsigned int *)t25);
    t57 = (~(t56));
    t58 = *((unsigned int *)t29);
    t59 = (t57 || t58);
    if (t59 > 0)
        goto LAB63;

LAB64:    if (*((unsigned int *)t29) > 0)
        goto LAB65;

LAB66:    if (*((unsigned int *)t25) > 0)
        goto LAB67;

LAB68:    memcpy(t24, t31, 8);

LAB69:    goto LAB51;

LAB52:    xsi_vlog_unsigned_bit_combine(t14, 32, t22, 32, t24, 32);
    goto LAB56;

LAB54:    memcpy(t14, t22, 8);
    goto LAB56;

LAB57:    *((unsigned int *)t25) = 1;
    goto LAB60;

LAB59:    t28 = (t25 + 4);
    *((unsigned int *)t25) = 1;
    *((unsigned int *)t28) = 1;
    goto LAB60;

LAB61:    t30 = ((char*)((ng8)));
    goto LAB62;

LAB63:    t31 = ((char*)((ng1)));
    goto LAB64;

LAB65:    xsi_vlog_unsigned_bit_combine(t24, 32, t30, 32, t31, 32);
    goto LAB69;

LAB67:    memcpy(t24, t30, 8);
    goto LAB69;

LAB70:    xsi_vlogvar_assign_value(t34, t14, 0, *((unsigned int *)t60), 1);
    goto LAB71;

LAB72:    t9 = *((unsigned int *)t24);
    t41 = (t9 + 0);
    t10 = *((unsigned int *)t14);
    t16 = *((unsigned int *)t23);
    t43 = (t10 - t16);
    t46 = (t43 + 1);
    xsi_vlogvar_assign_value(t2, t3, t41, *((unsigned int *)t23), t46);
    goto LAB73;

LAB74:    t9 = *((unsigned int *)t24);
    t41 = (t9 + 0);
    t10 = *((unsigned int *)t14);
    t16 = *((unsigned int *)t23);
    t43 = (t10 - t16);
    t46 = (t43 + 1);
    xsi_vlogvar_assign_value(t2, t3, t41, *((unsigned int *)t23), t46);
    goto LAB75;

LAB76:    t9 = *((unsigned int *)t24);
    t41 = (t9 + 0);
    t10 = *((unsigned int *)t14);
    t16 = *((unsigned int *)t23);
    t43 = (t10 - t16);
    t46 = (t43 + 1);
    xsi_vlogvar_assign_value(t2, t3, t41, *((unsigned int *)t23), t46);
    goto LAB77;

LAB78:    xsi_set_current_line(108, ng0);

LAB81:    xsi_set_current_line(109, ng0);
    t4 = (t0 + 3288U);
    t11 = *((char **)t4);
    t4 = (t11 + 4);
    t16 = *((unsigned int *)t4);
    t17 = (~(t16));
    t18 = *((unsigned int *)t11);
    t19 = (t18 & t17);
    t20 = (t19 != 0);
    if (t20 > 0)
        goto LAB82;

LAB83:    xsi_set_current_line(112, ng0);
    t2 = ((char*)((ng12)));
    t3 = (t0 + 1208U);
    t4 = *((char **)t3);
    memset(t23, 0, 8);
    t3 = (t23 + 4);
    t11 = (t4 + 4);
    t6 = *((unsigned int *)t4);
    t7 = (t6 >> 2);
    *((unsigned int *)t23) = t7;
    t8 = *((unsigned int *)t11);
    t9 = (t8 >> 2);
    *((unsigned int *)t3) = t9;
    t10 = *((unsigned int *)t23);
    *((unsigned int *)t23) = (t10 & 1073741823U);
    t16 = *((unsigned int *)t3);
    *((unsigned int *)t3) = (t16 & 1073741823U);
    xsi_vlogtype_concat(t14, 32, 32, 2U, t23, 30, t2, 2);
    t12 = (t0 + 4968);
    xsi_vlogvar_assign_value(t12, t14, 0, 0, 32);

LAB84:    goto LAB80;

LAB82:    xsi_set_current_line(110, ng0);
    t12 = ((char*)((ng12)));
    t15 = (t0 + 1208U);
    t22 = *((char **)t15);
    memset(t14, 0, 8);
    t15 = (t14 + 4);
    t26 = (t22 + 4);
    t21 = *((unsigned int *)t22);
    t32 = (t21 >> 2);
    *((unsigned int *)t14) = t32;
    t35 = *((unsigned int *)t26);
    t39 = (t35 >> 2);
    *((unsigned int *)t15) = t39;
    t42 = *((unsigned int *)t14);
    *((unsigned int *)t14) = (t42 & 1073741823U);
    t44 = *((unsigned int *)t15);
    *((unsigned int *)t15) = (t44 & 1073741823U);
    t27 = ((char*)((ng8)));
    memset(t23, 0, 8);
    xsi_vlog_unsigned_minus(t23, 32, t14, 32, t27, 32);
    xsi_vlogtype_concat(t66, 34, 34, 2U, t23, 32, t12, 2);
    t28 = (t0 + 4968);
    xsi_vlogvar_assign_value(t28, t66, 0, 0, 32);
    goto LAB84;

}


extern void work_m_00000000000995294205_3975733304_init()
{
	static char *pe[] = {(void *)Cont_62_0,(void *)Cont_63_1,(void *)Cont_64_2,(void *)Cont_65_3,(void *)Cont_67_4,(void *)Cont_68_5,(void *)Always_74_6};
	xsi_register_didat("work_m_00000000000995294205_3975733304", "isim/mips_txt_isim_beh.exe.sim/work/m_00000000000995294205_3975733304.didat");
	xsi_register_executes(pe);
}
