package organize.factor;

import organize.Expr;

import java.util.Objects;

public class SinOrCos implements BasicFactor {
    //参数，也就是sin()括号里面的东西，嗯....这里为了可拓展性选用了一种看起来有些麻烦的方法
    private Expr parameter;
    //这个三角函数的次数
    private long power;
    //sin & cos分开好呢还是放在一个trifun里面好呢？？因为两个形式比较类似，放在一起
    private boolean isSin;

    public SinOrCos(Expr parameter, long power, boolean isSin) {
        this.parameter = parameter;
        this.power = power;
        this.isSin = isSin;
    }

    public Expr getParameter() {
        return parameter;
    }

    public long getPower() {
        return power;
    }

    public void setPower(long power) {
        this.power = power;
    }

    public String toString() {
        String s = "(" + this.parameter.toString() + ")";
        if (!this.getParameter().isBasic()) {
            s = "(" + s + ")";
        }
        if (this.power > 1) {
            s = s + "**" + this.power;
        }
        s = (isSin ? "sin" : "cos") + s;
        return s;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SinOrCos sinOrCos = (SinOrCos) o;
        return isSin == sinOrCos.isSin &&
                Objects.equals(parameter, sinOrCos.parameter) && power == ((SinOrCos) o).power;
    }

    @Override
    public int hashCode() {
        return Objects.hash(parameter, isSin, power);
    }

    @Override
    public boolean isBasic() {
        return true;
    }

    public boolean isSin() {
        return isSin;
    }

    public SinOrCos copy() {
        SinOrCos result = new SinOrCos(this.parameter, this.power, this.isSin);
        return result;
    }
}
