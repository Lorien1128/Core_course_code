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
static const char *ng0 = "D:/ISE/p7_fortest/reg_pipeline.v";
static int ng1[] = {0, 0};



static void Always_41_0(char *t0)
{
    char t6[8];
    char t20[8];
    char t27[8];
    char t55[8];
    char t70[8];
    char t83[8];
    char t90[8];
    char t122[8];
    char t130[8];
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    unsigned int t7;
    unsigned int t8;
    unsigned int t9;
    unsigned int t10;
    unsigned int t11;
    char *t12;
    char *t13;
    unsigned int t14;
    unsigned int t15;
    unsigned int t16;
    unsigned int t17;
    char *t18;
    char *t19;
    unsigned int t21;
    unsigned int t22;
    unsigned int t23;
    unsigned int t24;
    unsigned int t25;
    char *t26;
    unsigned int t28;
    unsigned int t29;
    unsigned int t30;
    char *t31;
    char *t32;
    char *t33;
    unsigned int t34;
    unsigned int t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    unsigned int t39;
    unsigned int t40;
    char *t41;
    char *t42;
    unsigned int t43;
    unsigned int t44;
    unsigned int t45;
    int t46;
    unsigned int t47;
    unsigned int t48;
    unsigned int t49;
    int t50;
    unsigned int t51;
    unsigned int t52;
    unsigned int t53;
    unsigned int t54;
    char *t56;
    unsigned int t57;
    unsigned int t58;
    unsigned int t59;
    unsigned int t60;
    unsigned int t61;
    char *t62;
    char *t63;
    unsigned int t64;
    unsigned int t65;
    unsigned int t66;
    unsigned int t67;
    char *t68;
    char *t69;
    unsigned int t71;
    unsigned int t72;
    unsigned int t73;
    unsigned int t74;
    unsigned int t75;
    char *t76;
    char *t77;
    unsigned int t78;
    unsigned int t79;
    unsigned int t80;
    char *t81;
    char *t82;
    unsigned int t84;
    unsigned int t85;
    unsigned int t86;
    unsigned int t87;
    unsigned int t88;
    char *t89;
    unsigned int t91;
    unsigned int t92;
    unsigned int t93;
    char *t94;
    char *t95;
    char *t96;
    unsigned int t97;
    unsigned int t98;
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
    unsigned int t109;
    unsigned int t110;
    unsigned int t111;
    unsigned int t112;
    unsigned int t113;
    int t114;
    int t115;
    unsigned int t116;
    unsigned int t117;
    unsigned int t118;
    unsigned int t119;
    unsigned int t120;
    unsigned int t121;
    char *t123;
    unsigned int t124;
    unsigned int t125;
    unsigned int t126;
    unsigned int t127;
    unsigned int t128;
    char *t129;
    unsigned int t131;
    unsigned int t132;
    unsigned int t133;
    char *t134;
    char *t135;
    char *t136;
    unsigned int t137;
    unsigned int t138;
    unsigned int t139;
    unsigned int t140;
    unsigned int t141;
    unsigned int t142;
    unsigned int t143;
    char *t144;
    char *t145;
    unsigned int t146;
    unsigned int t147;
    unsigned int t148;
    int t149;
    unsigned int t150;
    unsigned int t151;
    unsigned int t152;
    int t153;
    unsigned int t154;
    unsigned int t155;
    unsigned int t156;
    unsigned int t157;
    char *t158;
    unsigned int t159;
    unsigned int t160;
    unsigned int t161;
    unsigned int t162;
    unsigned int t163;
    char *t164;
    char *t165;

LAB0:    t1 = (t0 + 4608U);
    t2 = *((char **)t1);
    if (t2 == 0)
        goto LAB2;

LAB3:    goto *t2;

LAB2:    xsi_set_current_line(41, ng0);
    t2 = (t0 + 4928);
    *((int *)t2) = 1;
    t3 = (t0 + 4640);
    *((char **)t3) = t2;
    *((char **)t1) = &&LAB4;

LAB1:    return;
LAB4:    xsi_set_current_line(41, ng0);

LAB5:    xsi_set_current_line(42, ng0);
    t4 = (t0 + 2008U);
    t5 = *((char **)t4);
    memset(t6, 0, 8);
    t4 = (t5 + 4);
    t7 = *((unsigned int *)t4);
    t8 = (~(t7));
    t9 = *((unsigned int *)t5);
    t10 = (t9 & t8);
    t11 = (t10 & 1U);
    if (t11 != 0)
        goto LAB6;

LAB7:    if (*((unsigned int *)t4) != 0)
        goto LAB8;

LAB9:    t13 = (t6 + 4);
    t14 = *((unsigned int *)t6);
    t15 = (!(t14));
    t16 = *((unsigned int *)t13);
    t17 = (t15 || t16);
    if (t17 > 0)
        goto LAB10;

LAB11:    memcpy(t27, t6, 8);

LAB12:    memset(t55, 0, 8);
    t56 = (t27 + 4);
    t57 = *((unsigned int *)t56);
    t58 = (~(t57));
    t59 = *((unsigned int *)t27);
    t60 = (t59 & t58);
    t61 = (t60 & 1U);
    if (t61 != 0)
        goto LAB20;

LAB21:    if (*((unsigned int *)t56) != 0)
        goto LAB22;

LAB23:    t63 = (t55 + 4);
    t64 = *((unsigned int *)t55);
    t65 = (!(t64));
    t66 = *((unsigned int *)t63);
    t67 = (t65 || t66);
    if (t67 > 0)
        goto LAB24;

LAB25:    memcpy(t130, t55, 8);

LAB26:    t158 = (t130 + 4);
    t159 = *((unsigned int *)t158);
    t160 = (~(t159));
    t161 = *((unsigned int *)t130);
    t162 = (t161 & t160);
    t163 = (t162 != 0);
    if (t163 > 0)
        goto LAB48;

LAB49:    xsi_set_current_line(48, ng0);
    t2 = (t0 + 1688U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t3 + 4);
    t7 = *((unsigned int *)t2);
    t8 = (~(t7));
    t9 = *((unsigned int *)t3);
    t10 = (t9 & t8);
    t11 = (t10 & 1U);
    if (t11 != 0)
        goto LAB52;

LAB53:    if (*((unsigned int *)t2) != 0)
        goto LAB54;

LAB55:    t5 = (t6 + 4);
    t14 = *((unsigned int *)t6);
    t15 = *((unsigned int *)t5);
    t16 = (t14 || t15);
    if (t16 > 0)
        goto LAB56;

LAB57:    memcpy(t27, t6, 8);

LAB58:    t41 = (t27 + 4);
    t60 = *((unsigned int *)t41);
    t61 = (~(t60));
    t64 = *((unsigned int *)t27);
    t65 = (t64 & t61);
    t66 = (t65 != 0);
    if (t66 > 0)
        goto LAB66;

LAB67:    xsi_set_current_line(50, ng0);
    t2 = (t0 + 2168U);
    t3 = *((char **)t2);
    memset(t6, 0, 8);
    t2 = (t3 + 4);
    t7 = *((unsigned int *)t2);
    t8 = (~(t7));
    t9 = *((unsigned int *)t3);
    t10 = (t9 & t8);
    t11 = (t10 & 1U);
    if (t11 != 0)
        goto LAB69;

LAB70:    if (*((unsigned int *)t2) != 0)
        goto LAB71;

LAB72:    t5 = (t6 + 4);
    t14 = *((unsigned int *)t6);
    t15 = *((unsigned int *)t5);
    t16 = (t14 || t15);
    if (t16 > 0)
        goto LAB73;

LAB74:    memcpy(t55, t6, 8);

LAB75:    memset(t70, 0, 8);
    t56 = (t55 + 4);
    t67 = *((unsigned int *)t56);
    t71 = (~(t67));
    t72 = *((unsigned int *)t55);
    t73 = (t72 & t71);
    t74 = (t73 & 1U);
    if (t74 != 0)
        goto LAB87;

LAB88:    if (*((unsigned int *)t56) != 0)
        goto LAB89;

LAB90:    t63 = (t70 + 4);
    t75 = *((unsigned int *)t70);
    t78 = *((unsigned int *)t63);
    t79 = (t75 || t78);
    if (t79 > 0)
        goto LAB91;

LAB92:    memcpy(t122, t70, 8);

LAB93:    t104 = (t122 + 4);
    t128 = *((unsigned int *)t104);
    t131 = (~(t128));
    t132 = *((unsigned int *)t122);
    t133 = (t132 & t131);
    t137 = (t133 != 0);
    if (t137 > 0)
        goto LAB105;

LAB106:
LAB107:
LAB68:
LAB50:    goto LAB2;

LAB6:    *((unsigned int *)t6) = 1;
    goto LAB9;

LAB8:    t12 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t12) = 1;
    goto LAB9;

LAB10:    t18 = (t0 + 2488U);
    t19 = *((char **)t18);
    memset(t20, 0, 8);
    t18 = (t19 + 4);
    t21 = *((unsigned int *)t18);
    t22 = (~(t21));
    t23 = *((unsigned int *)t19);
    t24 = (t23 & t22);
    t25 = (t24 & 1U);
    if (t25 != 0)
        goto LAB13;

LAB14:    if (*((unsigned int *)t18) != 0)
        goto LAB15;

LAB16:    t28 = *((unsigned int *)t6);
    t29 = *((unsigned int *)t20);
    t30 = (t28 | t29);
    *((unsigned int *)t27) = t30;
    t31 = (t6 + 4);
    t32 = (t20 + 4);
    t33 = (t27 + 4);
    t34 = *((unsigned int *)t31);
    t35 = *((unsigned int *)t32);
    t36 = (t34 | t35);
    *((unsigned int *)t33) = t36;
    t37 = *((unsigned int *)t33);
    t38 = (t37 != 0);
    if (t38 == 1)
        goto LAB17;

LAB18:
LAB19:    goto LAB12;

LAB13:    *((unsigned int *)t20) = 1;
    goto LAB16;

LAB15:    t26 = (t20 + 4);
    *((unsigned int *)t20) = 1;
    *((unsigned int *)t26) = 1;
    goto LAB16;

LAB17:    t39 = *((unsigned int *)t27);
    t40 = *((unsigned int *)t33);
    *((unsigned int *)t27) = (t39 | t40);
    t41 = (t6 + 4);
    t42 = (t20 + 4);
    t43 = *((unsigned int *)t41);
    t44 = (~(t43));
    t45 = *((unsigned int *)t6);
    t46 = (t45 & t44);
    t47 = *((unsigned int *)t42);
    t48 = (~(t47));
    t49 = *((unsigned int *)t20);
    t50 = (t49 & t48);
    t51 = (~(t46));
    t52 = (~(t50));
    t53 = *((unsigned int *)t33);
    *((unsigned int *)t33) = (t53 & t51);
    t54 = *((unsigned int *)t33);
    *((unsigned int *)t33) = (t54 & t52);
    goto LAB19;

LAB20:    *((unsigned int *)t55) = 1;
    goto LAB23;

LAB22:    t62 = (t55 + 4);
    *((unsigned int *)t55) = 1;
    *((unsigned int *)t62) = 1;
    goto LAB23;

LAB24:    t68 = (t0 + 2808U);
    t69 = *((char **)t68);
    memset(t70, 0, 8);
    t68 = (t69 + 4);
    t71 = *((unsigned int *)t68);
    t72 = (~(t71));
    t73 = *((unsigned int *)t69);
    t74 = (t73 & t72);
    t75 = (t74 & 1U);
    if (t75 != 0)
        goto LAB27;

LAB28:    if (*((unsigned int *)t68) != 0)
        goto LAB29;

LAB30:    t77 = (t70 + 4);
    t78 = *((unsigned int *)t70);
    t79 = *((unsigned int *)t77);
    t80 = (t78 || t79);
    if (t80 > 0)
        goto LAB31;

LAB32:    memcpy(t90, t70, 8);

LAB33:    memset(t122, 0, 8);
    t123 = (t90 + 4);
    t124 = *((unsigned int *)t123);
    t125 = (~(t124));
    t126 = *((unsigned int *)t90);
    t127 = (t126 & t125);
    t128 = (t127 & 1U);
    if (t128 != 0)
        goto LAB41;

LAB42:    if (*((unsigned int *)t123) != 0)
        goto LAB43;

LAB44:    t131 = *((unsigned int *)t55);
    t132 = *((unsigned int *)t122);
    t133 = (t131 | t132);
    *((unsigned int *)t130) = t133;
    t134 = (t55 + 4);
    t135 = (t122 + 4);
    t136 = (t130 + 4);
    t137 = *((unsigned int *)t134);
    t138 = *((unsigned int *)t135);
    t139 = (t137 | t138);
    *((unsigned int *)t136) = t139;
    t140 = *((unsigned int *)t136);
    t141 = (t140 != 0);
    if (t141 == 1)
        goto LAB45;

LAB46:
LAB47:    goto LAB26;

LAB27:    *((unsigned int *)t70) = 1;
    goto LAB30;

LAB29:    t76 = (t70 + 4);
    *((unsigned int *)t70) = 1;
    *((unsigned int *)t76) = 1;
    goto LAB30;

LAB31:    t81 = (t0 + 2168U);
    t82 = *((char **)t81);
    memset(t83, 0, 8);
    t81 = (t82 + 4);
    t84 = *((unsigned int *)t81);
    t85 = (~(t84));
    t86 = *((unsigned int *)t82);
    t87 = (t86 & t85);
    t88 = (t87 & 1U);
    if (t88 != 0)
        goto LAB34;

LAB35:    if (*((unsigned int *)t81) != 0)
        goto LAB36;

LAB37:    t91 = *((unsigned int *)t70);
    t92 = *((unsigned int *)t83);
    t93 = (t91 & t92);
    *((unsigned int *)t90) = t93;
    t94 = (t70 + 4);
    t95 = (t83 + 4);
    t96 = (t90 + 4);
    t97 = *((unsigned int *)t94);
    t98 = *((unsigned int *)t95);
    t99 = (t97 | t98);
    *((unsigned int *)t96) = t99;
    t100 = *((unsigned int *)t96);
    t101 = (t100 != 0);
    if (t101 == 1)
        goto LAB38;

LAB39:
LAB40:    goto LAB33;

LAB34:    *((unsigned int *)t83) = 1;
    goto LAB37;

LAB36:    t89 = (t83 + 4);
    *((unsigned int *)t83) = 1;
    *((unsigned int *)t89) = 1;
    goto LAB37;

LAB38:    t102 = *((unsigned int *)t90);
    t103 = *((unsigned int *)t96);
    *((unsigned int *)t90) = (t102 | t103);
    t104 = (t70 + 4);
    t105 = (t83 + 4);
    t106 = *((unsigned int *)t70);
    t107 = (~(t106));
    t108 = *((unsigned int *)t104);
    t109 = (~(t108));
    t110 = *((unsigned int *)t83);
    t111 = (~(t110));
    t112 = *((unsigned int *)t105);
    t113 = (~(t112));
    t114 = (t107 & t109);
    t115 = (t111 & t113);
    t116 = (~(t114));
    t117 = (~(t115));
    t118 = *((unsigned int *)t96);
    *((unsigned int *)t96) = (t118 & t116);
    t119 = *((unsigned int *)t96);
    *((unsigned int *)t96) = (t119 & t117);
    t120 = *((unsigned int *)t90);
    *((unsigned int *)t90) = (t120 & t116);
    t121 = *((unsigned int *)t90);
    *((unsigned int *)t90) = (t121 & t117);
    goto LAB40;

LAB41:    *((unsigned int *)t122) = 1;
    goto LAB44;

LAB43:    t129 = (t122 + 4);
    *((unsigned int *)t122) = 1;
    *((unsigned int *)t129) = 1;
    goto LAB44;

LAB45:    t142 = *((unsigned int *)t130);
    t143 = *((unsigned int *)t136);
    *((unsigned int *)t130) = (t142 | t143);
    t144 = (t55 + 4);
    t145 = (t122 + 4);
    t146 = *((unsigned int *)t144);
    t147 = (~(t146));
    t148 = *((unsigned int *)t55);
    t149 = (t148 & t147);
    t150 = *((unsigned int *)t145);
    t151 = (~(t150));
    t152 = *((unsigned int *)t122);
    t153 = (t152 & t151);
    t154 = (~(t149));
    t155 = (~(t153));
    t156 = *((unsigned int *)t136);
    *((unsigned int *)t136) = (t156 & t154);
    t157 = *((unsigned int *)t136);
    *((unsigned int *)t136) = (t157 & t155);
    goto LAB47;

LAB48:    xsi_set_current_line(42, ng0);

LAB51:    xsi_set_current_line(43, ng0);
    t164 = ((char*)((ng1)));
    t165 = (t0 + 3368);
    xsi_vlogvar_assign_value(t165, t164, 0, 0, 32);
    xsi_set_current_line(44, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 3208);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    xsi_set_current_line(45, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 3528);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 32);
    xsi_set_current_line(46, ng0);
    t2 = ((char*)((ng1)));
    t3 = (t0 + 3688);
    xsi_vlogvar_assign_value(t3, t2, 0, 0, 5);
    goto LAB50;

LAB52:    *((unsigned int *)t6) = 1;
    goto LAB55;

LAB54:    t4 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t4) = 1;
    goto LAB55;

LAB56:    t12 = (t0 + 2168U);
    t13 = *((char **)t12);
    memset(t20, 0, 8);
    t12 = (t13 + 4);
    t17 = *((unsigned int *)t12);
    t21 = (~(t17));
    t22 = *((unsigned int *)t13);
    t23 = (t22 & t21);
    t24 = (t23 & 1U);
    if (t24 != 0)
        goto LAB59;

LAB60:    if (*((unsigned int *)t12) != 0)
        goto LAB61;

LAB62:    t25 = *((unsigned int *)t6);
    t28 = *((unsigned int *)t20);
    t29 = (t25 & t28);
    *((unsigned int *)t27) = t29;
    t19 = (t6 + 4);
    t26 = (t20 + 4);
    t31 = (t27 + 4);
    t30 = *((unsigned int *)t19);
    t34 = *((unsigned int *)t26);
    t35 = (t30 | t34);
    *((unsigned int *)t31) = t35;
    t36 = *((unsigned int *)t31);
    t37 = (t36 != 0);
    if (t37 == 1)
        goto LAB63;

LAB64:
LAB65:    goto LAB58;

LAB59:    *((unsigned int *)t20) = 1;
    goto LAB62;

LAB61:    t18 = (t20 + 4);
    *((unsigned int *)t20) = 1;
    *((unsigned int *)t18) = 1;
    goto LAB62;

LAB63:    t38 = *((unsigned int *)t27);
    t39 = *((unsigned int *)t31);
    *((unsigned int *)t27) = (t38 | t39);
    t32 = (t6 + 4);
    t33 = (t20 + 4);
    t40 = *((unsigned int *)t6);
    t43 = (~(t40));
    t44 = *((unsigned int *)t32);
    t45 = (~(t44));
    t47 = *((unsigned int *)t20);
    t48 = (~(t47));
    t49 = *((unsigned int *)t33);
    t51 = (~(t49));
    t46 = (t43 & t45);
    t50 = (t48 & t51);
    t52 = (~(t46));
    t53 = (~(t50));
    t54 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t54 & t52);
    t57 = *((unsigned int *)t31);
    *((unsigned int *)t31) = (t57 & t53);
    t58 = *((unsigned int *)t27);
    *((unsigned int *)t27) = (t58 & t52);
    t59 = *((unsigned int *)t27);
    *((unsigned int *)t27) = (t59 & t53);
    goto LAB65;

LAB66:    xsi_set_current_line(49, ng0);
    t42 = ((char*)((ng1)));
    t56 = (t0 + 3368);
    xsi_vlogvar_assign_value(t56, t42, 0, 0, 32);
    goto LAB68;

LAB69:    *((unsigned int *)t6) = 1;
    goto LAB72;

LAB71:    t4 = (t6 + 4);
    *((unsigned int *)t6) = 1;
    *((unsigned int *)t4) = 1;
    goto LAB72;

LAB73:    t12 = (t0 + 2328U);
    t13 = *((char **)t12);
    memset(t20, 0, 8);
    t12 = (t13 + 4);
    t17 = *((unsigned int *)t12);
    t21 = (~(t17));
    t22 = *((unsigned int *)t13);
    t23 = (t22 & t21);
    t24 = (t23 & 1U);
    if (t24 != 0)
        goto LAB79;

LAB77:    if (*((unsigned int *)t12) == 0)
        goto LAB76;

LAB78:    t18 = (t20 + 4);
    *((unsigned int *)t20) = 1;
    *((unsigned int *)t18) = 1;

LAB79:    memset(t27, 0, 8);
    t19 = (t20 + 4);
    t25 = *((unsigned int *)t19);
    t28 = (~(t25));
    t29 = *((unsigned int *)t20);
    t30 = (t29 & t28);
    t34 = (t30 & 1U);
    if (t34 != 0)
        goto LAB80;

LAB81:    if (*((unsigned int *)t19) != 0)
        goto LAB82;

LAB83:    t35 = *((unsigned int *)t6);
    t36 = *((unsigned int *)t27);
    t37 = (t35 & t36);
    *((unsigned int *)t55) = t37;
    t31 = (t6 + 4);
    t32 = (t27 + 4);
    t33 = (t55 + 4);
    t38 = *((unsigned int *)t31);
    t39 = *((unsigned int *)t32);
    t40 = (t38 | t39);
    *((unsigned int *)t33) = t40;
    t43 = *((unsigned int *)t33);
    t44 = (t43 != 0);
    if (t44 == 1)
        goto LAB84;

LAB85:
LAB86:    goto LAB75;

LAB76:    *((unsigned int *)t20) = 1;
    goto LAB79;

LAB80:    *((unsigned int *)t27) = 1;
    goto LAB83;

LAB82:    t26 = (t27 + 4);
    *((unsigned int *)t27) = 1;
    *((unsigned int *)t26) = 1;
    goto LAB83;

LAB84:    t45 = *((unsigned int *)t55);
    t47 = *((unsigned int *)t33);
    *((unsigned int *)t55) = (t45 | t47);
    t41 = (t6 + 4);
    t42 = (t27 + 4);
    t48 = *((unsigned int *)t6);
    t49 = (~(t48));
    t51 = *((unsigned int *)t41);
    t52 = (~(t51));
    t53 = *((unsigned int *)t27);
    t54 = (~(t53));
    t57 = *((unsigned int *)t42);
    t58 = (~(t57));
    t46 = (t49 & t52);
    t50 = (t54 & t58);
    t59 = (~(t46));
    t60 = (~(t50));
    t61 = *((unsigned int *)t33);
    *((unsigned int *)t33) = (t61 & t59);
    t64 = *((unsigned int *)t33);
    *((unsigned int *)t33) = (t64 & t60);
    t65 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t65 & t59);
    t66 = *((unsigned int *)t55);
    *((unsigned int *)t55) = (t66 & t60);
    goto LAB86;

LAB87:    *((unsigned int *)t70) = 1;
    goto LAB90;

LAB89:    t62 = (t70 + 4);
    *((unsigned int *)t70) = 1;
    *((unsigned int *)t62) = 1;
    goto LAB90;

LAB91:    t68 = (t0 + 2808U);
    t69 = *((char **)t68);
    memset(t83, 0, 8);
    t68 = (t69 + 4);
    t80 = *((unsigned int *)t68);
    t84 = (~(t80));
    t85 = *((unsigned int *)t69);
    t86 = (t85 & t84);
    t87 = (t86 & 1U);
    if (t87 != 0)
        goto LAB97;

LAB95:    if (*((unsigned int *)t68) == 0)
        goto LAB94;

LAB96:    t76 = (t83 + 4);
    *((unsigned int *)t83) = 1;
    *((unsigned int *)t76) = 1;

LAB97:    memset(t90, 0, 8);
    t77 = (t83 + 4);
    t88 = *((unsigned int *)t77);
    t91 = (~(t88));
    t92 = *((unsigned int *)t83);
    t93 = (t92 & t91);
    t97 = (t93 & 1U);
    if (t97 != 0)
        goto LAB98;

LAB99:    if (*((unsigned int *)t77) != 0)
        goto LAB100;

LAB101:    t98 = *((unsigned int *)t70);
    t99 = *((unsigned int *)t90);
    t100 = (t98 & t99);
    *((unsigned int *)t122) = t100;
    t82 = (t70 + 4);
    t89 = (t90 + 4);
    t94 = (t122 + 4);
    t101 = *((unsigned int *)t82);
    t102 = *((unsigned int *)t89);
    t103 = (t101 | t102);
    *((unsigned int *)t94) = t103;
    t106 = *((unsigned int *)t94);
    t107 = (t106 != 0);
    if (t107 == 1)
        goto LAB102;

LAB103:
LAB104:    goto LAB93;

LAB94:    *((unsigned int *)t83) = 1;
    goto LAB97;

LAB98:    *((unsigned int *)t90) = 1;
    goto LAB101;

LAB100:    t81 = (t90 + 4);
    *((unsigned int *)t90) = 1;
    *((unsigned int *)t81) = 1;
    goto LAB101;

LAB102:    t108 = *((unsigned int *)t122);
    t109 = *((unsigned int *)t94);
    *((unsigned int *)t122) = (t108 | t109);
    t95 = (t70 + 4);
    t96 = (t90 + 4);
    t110 = *((unsigned int *)t70);
    t111 = (~(t110));
    t112 = *((unsigned int *)t95);
    t113 = (~(t112));
    t116 = *((unsigned int *)t90);
    t117 = (~(t116));
    t118 = *((unsigned int *)t96);
    t119 = (~(t118));
    t114 = (t111 & t113);
    t115 = (t117 & t119);
    t120 = (~(t114));
    t121 = (~(t115));
    t124 = *((unsigned int *)t94);
    *((unsigned int *)t94) = (t124 & t120);
    t125 = *((unsigned int *)t94);
    *((unsigned int *)t94) = (t125 & t121);
    t126 = *((unsigned int *)t122);
    *((unsigned int *)t122) = (t126 & t120);
    t127 = *((unsigned int *)t122);
    *((unsigned int *)t122) = (t127 & t121);
    goto LAB104;

LAB105:    xsi_set_current_line(50, ng0);

LAB108:    xsi_set_current_line(51, ng0);
    t105 = (t0 + 1048U);
    t123 = *((char **)t105);
    t105 = (t0 + 3368);
    xsi_vlogvar_assign_value(t105, t123, 0, 0, 32);
    xsi_set_current_line(52, ng0);
    t2 = (t0 + 1208U);
    t3 = *((char **)t2);
    t2 = (t0 + 3208);
    xsi_vlogvar_assign_value(t2, t3, 0, 0, 32);
    xsi_set_current_line(53, ng0);
    t2 = (t0 + 1368U);
    t3 = *((char **)t2);
    t2 = (t0 + 3528);
    xsi_vlogvar_assign_value(t2, t3, 0, 0, 32);
    xsi_set_current_line(54, ng0);
    t2 = (t0 + 1528U);
    t3 = *((char **)t2);
    t2 = (t0 + 3688);
    xsi_vlogvar_assign_value(t2, t3, 0, 0, 5);
    goto LAB107;

}


extern void work_m_00000000002055322354_1531285090_init()
{
	static char *pe[] = {(void *)Always_41_0};
	xsi_register_didat("work_m_00000000002055322354_1531285090", "isim/mips_txt_isim_beh.exe.sim/work/m_00000000002055322354_1531285090.didat");
	xsi_register_executes(pe);
}
