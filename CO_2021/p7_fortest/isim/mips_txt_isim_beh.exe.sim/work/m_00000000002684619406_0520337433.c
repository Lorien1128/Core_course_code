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
static const char *ng0 = "D:/ISE/p7_fortest/EXC.v";
static int ng1[] = {0, 0};
static int ng2[] = {12, 0};
static int ng3[] = {4, 0};
static int ng4[] = {5, 0};



static void Cont_65_0(char *t0)
{
    char t3[8];
    char t4[8];
    char t6[8];
    char t35[8];
    char t40[8];
    char t41[8];
    char t58[8];
    char t59[8];
    char t77[8];
    char t78[8];
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
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    unsigned int t39;
    char *t42;
    unsigned int t43;
    unsigned int t44;
    unsigned int t45;
    unsigned int t46;
    unsigned int t47;
    char *t48;
    char *t49;
    unsigned int t50;
    unsigned int t51;
    unsigned int t52;
    char *t53;
    unsigned int t54;
    unsigned int t55;
    unsigned int t56;
    unsigned int t57;
    char *t60;
    char *t61;
    unsigned int t62;
    unsigned int t63;
    unsigned int t64;
    unsigned int t65;
    unsigned int t66;
    char *t67;
    char *t68;
    unsigned int t69;
    unsigned int t70;
    unsigned int t71;
    char *t72;
    unsigned int t73;
    unsigned int t74;
    unsigned int t75;
    unsigned int t76;
    char *t79;
    char *t80;
    unsigned int t81;
    unsigned int t82;
    unsigned int t83;
    unsigned int t84;
    unsigned int t85;
    char *t86;
    char *t87;
    unsigned int t88;
    unsigned int t89;
    unsigned int t90;
    char *t91;
    unsigned int t92;
    unsigned int t93;
    unsigned int t94;
    unsigned int t95;
    char *t96;
    char *t97;
    char *t98;
    char *t99;
    char *t100;
    char *t101;
    unsigned int t102;
    unsigned int t103;
    char *t104;
    unsigned int t105;
    unsigned int t106;
    char *t107;
    unsigned int t108;
    unsigned int t109;
    char *t110;

LAB0:    t1 = (t0 + 3328U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(65, ng0);
    t2 = (t0 + 1048U);
    t5 = *((char **)t2);
    t2 = ((char*)((ng1)));
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
        goto LAB5;

LAB4:    if (t18 != 0)
        goto LAB6;

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

LAB20:    t97 = (t0 + 3992);
    t98 = (t97 + 56U);
    t99 = *((char **)t98);
    t100 = (t99 + 56U);
    t101 = *((char **)t100);
    memset(t101, 0, 8);
    t102 = 31U;
    t103 = t102;
    t104 = (t3 + 4);
    t105 = *((unsigned int *)t3);
    t102 = (t102 & t105);
    t106 = *((unsigned int *)t104);
    t103 = (t103 & t106);
    t107 = (t101 + 4);
    t108 = *((unsigned int *)t101);
    *((unsigned int *)t101) = (t108 | t102);
    t109 = *((unsigned int *)t107);
    *((unsigned int *)t107) = (t109 | t103);
    xsi_driver_vfirst_trans(t97, 0, 4);
    t110 = (t0 + 3896);
    *((int *)t110) = 1;

LAB1:    return;
LAB5:    *((unsigned int *)t6) = 1;
    goto LAB7;

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

LAB12:    t33 = (t0 + 1048U);
    t34 = *((char **)t33);
    memcpy(t35, t34, 8);
    goto LAB13;

LAB14:    t33 = (t0 + 1528U);
    t42 = *((char **)t33);
    memset(t41, 0, 8);
    t33 = (t42 + 4);
    t43 = *((unsigned int *)t33);
    t44 = (~(t43));
    t45 = *((unsigned int *)t42);
    t46 = (t45 & t44);
    t47 = (t46 & 1U);
    if (t47 != 0)
        goto LAB21;

LAB22:    if (*((unsigned int *)t33) != 0)
        goto LAB23;

LAB24:    t49 = (t41 + 4);
    t50 = *((unsigned int *)t41);
    t51 = *((unsigned int *)t49);
    t52 = (t50 || t51);
    if (t52 > 0)
        goto LAB25;

LAB26:    t54 = *((unsigned int *)t41);
    t55 = (~(t54));
    t56 = *((unsigned int *)t49);
    t57 = (t55 || t56);
    if (t57 > 0)
        goto LAB27;

LAB28:    if (*((unsigned int *)t49) > 0)
        goto LAB29;

LAB30:    if (*((unsigned int *)t41) > 0)
        goto LAB31;

LAB32:    memcpy(t40, t58, 8);

LAB33:    goto LAB15;

LAB16:    xsi_vlog_unsigned_bit_combine(t3, 32, t35, 32, t40, 32);
    goto LAB20;

LAB18:    memcpy(t3, t35, 8);
    goto LAB20;

LAB21:    *((unsigned int *)t41) = 1;
    goto LAB24;

LAB23:    t48 = (t41 + 4);
    *((unsigned int *)t41) = 1;
    *((unsigned int *)t48) = 1;
    goto LAB24;

LAB25:    t53 = ((char*)((ng2)));
    goto LAB26;

LAB27:    t60 = (t0 + 1688U);
    t61 = *((char **)t60);
    memset(t59, 0, 8);
    t60 = (t61 + 4);
    t62 = *((unsigned int *)t60);
    t63 = (~(t62));
    t64 = *((unsigned int *)t61);
    t65 = (t64 & t63);
    t66 = (t65 & 1U);
    if (t66 != 0)
        goto LAB34;

LAB35:    if (*((unsigned int *)t60) != 0)
        goto LAB36;

LAB37:    t68 = (t59 + 4);
    t69 = *((unsigned int *)t59);
    t70 = *((unsigned int *)t68);
    t71 = (t69 || t70);
    if (t71 > 0)
        goto LAB38;

LAB39:    t73 = *((unsigned int *)t59);
    t74 = (~(t73));
    t75 = *((unsigned int *)t68);
    t76 = (t74 || t75);
    if (t76 > 0)
        goto LAB40;

LAB41:    if (*((unsigned int *)t68) > 0)
        goto LAB42;

LAB43:    if (*((unsigned int *)t59) > 0)
        goto LAB44;

LAB45:    memcpy(t58, t77, 8);

LAB46:    goto LAB28;

LAB29:    xsi_vlog_unsigned_bit_combine(t40, 32, t53, 32, t58, 32);
    goto LAB33;

LAB31:    memcpy(t40, t53, 8);
    goto LAB33;

LAB34:    *((unsigned int *)t59) = 1;
    goto LAB37;

LAB36:    t67 = (t59 + 4);
    *((unsigned int *)t59) = 1;
    *((unsigned int *)t67) = 1;
    goto LAB37;

LAB38:    t72 = ((char*)((ng3)));
    goto LAB39;

LAB40:    t79 = (t0 + 1848U);
    t80 = *((char **)t79);
    memset(t78, 0, 8);
    t79 = (t80 + 4);
    t81 = *((unsigned int *)t79);
    t82 = (~(t81));
    t83 = *((unsigned int *)t80);
    t84 = (t83 & t82);
    t85 = (t84 & 1U);
    if (t85 != 0)
        goto LAB47;

LAB48:    if (*((unsigned int *)t79) != 0)
        goto LAB49;

LAB50:    t87 = (t78 + 4);
    t88 = *((unsigned int *)t78);
    t89 = *((unsigned int *)t87);
    t90 = (t88 || t89);
    if (t90 > 0)
        goto LAB51;

LAB52:    t92 = *((unsigned int *)t78);
    t93 = (~(t92));
    t94 = *((unsigned int *)t87);
    t95 = (t93 || t94);
    if (t95 > 0)
        goto LAB53;

LAB54:    if (*((unsigned int *)t87) > 0)
        goto LAB55;

LAB56:    if (*((unsigned int *)t78) > 0)
        goto LAB57;

LAB58:    memcpy(t77, t96, 8);

LAB59:    goto LAB41;

LAB42:    xsi_vlog_unsigned_bit_combine(t58, 32, t72, 32, t77, 32);
    goto LAB46;

LAB44:    memcpy(t58, t72, 8);
    goto LAB46;

LAB47:    *((unsigned int *)t78) = 1;
    goto LAB50;

LAB49:    t86 = (t78 + 4);
    *((unsigned int *)t78) = 1;
    *((unsigned int *)t86) = 1;
    goto LAB50;

LAB51:    t91 = ((char*)((ng4)));
    goto LAB52;

LAB53:    t96 = ((char*)((ng1)));
    goto LAB54;

LAB55:    xsi_vlog_unsigned_bit_combine(t77, 32, t91, 32, t96, 32);
    goto LAB59;

LAB57:    memcpy(t77, t91, 8);
    goto LAB59;

}

static void Cont_70_1(char *t0)
{
    char t3[8];
    char t4[8];
    char t6[8];
    char t39[8];
    char t40[8];
    char t42[8];
    char t56[8];
    char t63[8];
    char t91[8];
    char t106[8];
    char t113[8];
    char t154[8];
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
    unsigned int t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    char *t41;
    unsigned int t43;
    unsigned int t44;
    unsigned int t45;
    unsigned int t46;
    unsigned int t47;
    char *t48;
    char *t49;
    unsigned int t50;
    unsigned int t51;
    unsigned int t52;
    unsigned int t53;
    char *t54;
    char *t55;
    unsigned int t57;
    unsigned int t58;
    unsigned int t59;
    unsigned int t60;
    unsigned int t61;
    char *t62;
    unsigned int t64;
    unsigned int t65;
    unsigned int t66;
    char *t67;
    char *t68;
    char *t69;
    unsigned int t70;
    unsigned int t71;
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
    int t82;
    unsigned int t83;
    unsigned int t84;
    unsigned int t85;
    int t86;
    unsigned int t87;
    unsigned int t88;
    unsigned int t89;
    unsigned int t90;
    char *t92;
    unsigned int t93;
    unsigned int t94;
    unsigned int t95;
    unsigned int t96;
    unsigned int t97;
    char *t98;
    char *t99;
    unsigned int t100;
    unsigned int t101;
    unsigned int t102;
    unsigned int t103;
    char *t104;
    char *t105;
    unsigned int t107;
    unsigned int t108;
    unsigned int t109;
    unsigned int t110;
    unsigned int t111;
    char *t112;
    unsigned int t114;
    unsigned int t115;
    unsigned int t116;
    char *t117;
    char *t118;
    char *t119;
    unsigned int t120;
    unsigned int t121;
    unsigned int t122;
    unsigned int t123;
    unsigned int t124;
    unsigned int t125;
    unsigned int t126;
    char *t127;
    char *t128;
    unsigned int t129;
    unsigned int t130;
    unsigned int t131;
    int t132;
    unsigned int t133;
    unsigned int t134;
    unsigned int t135;
    int t136;
    unsigned int t137;
    unsigned int t138;
    unsigned int t139;
    unsigned int t140;
    char *t141;
    unsigned int t142;
    unsigned int t143;
    unsigned int t144;
    unsigned int t145;
    unsigned int t146;
    char *t147;
    char *t148;
    unsigned int t149;
    unsigned int t150;
    unsigned int t151;
    char *t152;
    char *t153;
    unsigned int t155;
    unsigned int t156;
    unsigned int t157;
    unsigned int t158;
    char *t159;
    char *t160;
    char *t161;
    char *t162;
    char *t163;
    char *t164;
    char *t165;

LAB0:    t1 = (t0 + 3576U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(70, ng0);
    t2 = (t0 + 1048U);
    t5 = *((char **)t2);
    t2 = ((char*)((ng1)));
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
        goto LAB5;

LAB4:    if (t18 != 0)
        goto LAB6;

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

LAB13:    t35 = *((unsigned int *)t4);
    t36 = (~(t35));
    t37 = *((unsigned int *)t29);
    t38 = (t36 || t37);
    if (t38 > 0)
        goto LAB14;

LAB15:    if (*((unsigned int *)t29) > 0)
        goto LAB16;

LAB17:    if (*((unsigned int *)t4) > 0)
        goto LAB18;

LAB19:    memcpy(t3, t39, 8);

LAB20:    t160 = (t0 + 4056);
    t161 = (t160 + 56U);
    t162 = *((char **)t161);
    t163 = (t162 + 56U);
    t164 = *((char **)t163);
    memcpy(t164, t3, 8);
    xsi_driver_vfirst_trans(t160, 0, 31);
    t165 = (t0 + 3912);
    *((int *)t165) = 1;

LAB1:    return;
LAB5:    *((unsigned int *)t6) = 1;
    goto LAB7;

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

LAB12:    t33 = (t0 + 1208U);
    t34 = *((char **)t33);
    goto LAB13;

LAB14:    t33 = (t0 + 1528U);
    t41 = *((char **)t33);
    memset(t42, 0, 8);
    t33 = (t41 + 4);
    t43 = *((unsigned int *)t33);
    t44 = (~(t43));
    t45 = *((unsigned int *)t41);
    t46 = (t45 & t44);
    t47 = (t46 & 1U);
    if (t47 != 0)
        goto LAB21;

LAB22:    if (*((unsigned int *)t33) != 0)
        goto LAB23;

LAB24:    t49 = (t42 + 4);
    t50 = *((unsigned int *)t42);
    t51 = (!(t50));
    t52 = *((unsigned int *)t49);
    t53 = (t51 || t52);
    if (t53 > 0)
        goto LAB25;

LAB26:    memcpy(t63, t42, 8);

LAB27:    memset(t91, 0, 8);
    t92 = (t63 + 4);
    t93 = *((unsigned int *)t92);
    t94 = (~(t93));
    t95 = *((unsigned int *)t63);
    t96 = (t95 & t94);
    t97 = (t96 & 1U);
    if (t97 != 0)
        goto LAB35;

LAB36:    if (*((unsigned int *)t92) != 0)
        goto LAB37;

LAB38:    t99 = (t91 + 4);
    t100 = *((unsigned int *)t91);
    t101 = (!(t100));
    t102 = *((unsigned int *)t99);
    t103 = (t101 || t102);
    if (t103 > 0)
        goto LAB39;

LAB40:    memcpy(t113, t91, 8);

LAB41:    memset(t40, 0, 8);
    t141 = (t113 + 4);
    t142 = *((unsigned int *)t141);
    t143 = (~(t142));
    t144 = *((unsigned int *)t113);
    t145 = (t144 & t143);
    t146 = (t145 & 1U);
    if (t146 != 0)
        goto LAB49;

LAB50:    if (*((unsigned int *)t141) != 0)
        goto LAB51;

LAB52:    t148 = (t40 + 4);
    t149 = *((unsigned int *)t40);
    t150 = *((unsigned int *)t148);
    t151 = (t149 || t150);
    if (t151 > 0)
        goto LAB53;

LAB54:    t155 = *((unsigned int *)t40);
    t156 = (~(t155));
    t157 = *((unsigned int *)t148);
    t158 = (t156 || t157);
    if (t158 > 0)
        goto LAB55;

LAB56:    if (*((unsigned int *)t148) > 0)
        goto LAB57;

LAB58:    if (*((unsigned int *)t40) > 0)
        goto LAB59;

LAB60:    memcpy(t39, t159, 8);

LAB61:    goto LAB15;

LAB16:    xsi_vlog_unsigned_bit_combine(t3, 32, t34, 32, t39, 32);
    goto LAB20;

LAB18:    memcpy(t3, t34, 8);
    goto LAB20;

LAB21:    *((unsigned int *)t42) = 1;
    goto LAB24;

LAB23:    t48 = (t42 + 4);
    *((unsigned int *)t42) = 1;
    *((unsigned int *)t48) = 1;
    goto LAB24;

LAB25:    t54 = (t0 + 1688U);
    t55 = *((char **)t54);
    memset(t56, 0, 8);
    t54 = (t55 + 4);
    t57 = *((unsigned int *)t54);
    t58 = (~(t57));
    t59 = *((unsigned int *)t55);
    t60 = (t59 & t58);
    t61 = (t60 & 1U);
    if (t61 != 0)
        goto LAB28;

LAB29:    if (*((unsigned int *)t54) != 0)
        goto LAB30;

LAB31:    t64 = *((unsigned int *)t42);
    t65 = *((unsigned int *)t56);
    t66 = (t64 | t65);
    *((unsigned int *)t63) = t66;
    t67 = (t42 + 4);
    t68 = (t56 + 4);
    t69 = (t63 + 4);
    t70 = *((unsigned int *)t67);
    t71 = *((unsigned int *)t68);
    t72 = (t70 | t71);
    *((unsigned int *)t69) = t72;
    t73 = *((unsigned int *)t69);
    t74 = (t73 != 0);
    if (t74 == 1)
        goto LAB32;

LAB33:
LAB34:    goto LAB27;

LAB28:    *((unsigned int *)t56) = 1;
    goto LAB31;

LAB30:    t62 = (t56 + 4);
    *((unsigned int *)t56) = 1;
    *((unsigned int *)t62) = 1;
    goto LAB31;

LAB32:    t75 = *((unsigned int *)t63);
    t76 = *((unsigned int *)t69);
    *((unsigned int *)t63) = (t75 | t76);
    t77 = (t42 + 4);
    t78 = (t56 + 4);
    t79 = *((unsigned int *)t77);
    t80 = (~(t79));
    t81 = *((unsigned int *)t42);
    t82 = (t81 & t80);
    t83 = *((unsigned int *)t78);
    t84 = (~(t83));
    t85 = *((unsigned int *)t56);
    t86 = (t85 & t84);
    t87 = (~(t82));
    t88 = (~(t86));
    t89 = *((unsigned int *)t69);
    *((unsigned int *)t69) = (t89 & t87);
    t90 = *((unsigned int *)t69);
    *((unsigned int *)t69) = (t90 & t88);
    goto LAB34;

LAB35:    *((unsigned int *)t91) = 1;
    goto LAB38;

LAB37:    t98 = (t91 + 4);
    *((unsigned int *)t91) = 1;
    *((unsigned int *)t98) = 1;
    goto LAB38;

LAB39:    t104 = (t0 + 1848U);
    t105 = *((char **)t104);
    memset(t106, 0, 8);
    t104 = (t105 + 4);
    t107 = *((unsigned int *)t104);
    t108 = (~(t107));
    t109 = *((unsigned int *)t105);
    t110 = (t109 & t108);
    t111 = (t110 & 1U);
    if (t111 != 0)
        goto LAB42;

LAB43:    if (*((unsigned int *)t104) != 0)
        goto LAB44;

LAB45:    t114 = *((unsigned int *)t91);
    t115 = *((unsigned int *)t106);
    t116 = (t114 | t115);
    *((unsigned int *)t113) = t116;
    t117 = (t91 + 4);
    t118 = (t106 + 4);
    t119 = (t113 + 4);
    t120 = *((unsigned int *)t117);
    t121 = *((unsigned int *)t118);
    t122 = (t120 | t121);
    *((unsigned int *)t119) = t122;
    t123 = *((unsigned int *)t119);
    t124 = (t123 != 0);
    if (t124 == 1)
        goto LAB46;

LAB47:
LAB48:    goto LAB41;

LAB42:    *((unsigned int *)t106) = 1;
    goto LAB45;

LAB44:    t112 = (t106 + 4);
    *((unsigned int *)t106) = 1;
    *((unsigned int *)t112) = 1;
    goto LAB45;

LAB46:    t125 = *((unsigned int *)t113);
    t126 = *((unsigned int *)t119);
    *((unsigned int *)t113) = (t125 | t126);
    t127 = (t91 + 4);
    t128 = (t106 + 4);
    t129 = *((unsigned int *)t127);
    t130 = (~(t129));
    t131 = *((unsigned int *)t91);
    t132 = (t131 & t130);
    t133 = *((unsigned int *)t128);
    t134 = (~(t133));
    t135 = *((unsigned int *)t106);
    t136 = (t135 & t134);
    t137 = (~(t132));
    t138 = (~(t136));
    t139 = *((unsigned int *)t119);
    *((unsigned int *)t119) = (t139 & t137);
    t140 = *((unsigned int *)t119);
    *((unsigned int *)t119) = (t140 & t138);
    goto LAB48;

LAB49:    *((unsigned int *)t40) = 1;
    goto LAB52;

LAB51:    t147 = (t40 + 4);
    *((unsigned int *)t40) = 1;
    *((unsigned int *)t147) = 1;
    goto LAB52;

LAB53:    t152 = (t0 + 1368U);
    t153 = *((char **)t152);
    t152 = ((char*)((ng3)));
    memset(t154, 0, 8);
    xsi_vlog_unsigned_minus(t154, 32, t153, 32, t152, 32);
    goto LAB54;

LAB55:    t159 = ((char*)((ng1)));
    goto LAB56;

LAB57:    xsi_vlog_unsigned_bit_combine(t39, 32, t154, 32, t159, 32);
    goto LAB61;

LAB59:    memcpy(t39, t154, 8);
    goto LAB61;

}


extern void work_m_00000000002684619406_0520337433_init()
{
	static char *pe[] = {(void *)Cont_65_0,(void *)Cont_70_1};
	xsi_register_didat("work_m_00000000002684619406_0520337433", "isim/mips_txt_isim_beh.exe.sim/work/m_00000000002684619406_0520337433.didat");
	xsi_register_executes(pe);
}
