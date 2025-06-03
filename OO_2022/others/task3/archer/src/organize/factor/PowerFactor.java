package organize.factor;

import java.util.Objects;

public class PowerFactor implements BasicFactor {
    private char variable = 'x';
    private long power;

    public long getPower() {
        return power;
    }

    public PowerFactor(long power) {
        this.power = power;
    }

    public String toString() {
        if (this.power == 0) {
            return "1";
        }
        if (this.power == 1) {
            return "" + this.variable;
        }
        return this.variable + "**" + this.power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PowerFactor that = (PowerFactor) o;
        return variable == that.variable && this.power == that.power;
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, variable);
    }

    @Override
    public boolean isBasic() {
        return true;
    }

}
