package exp;

public interface Term {
    //因子组成的arraylist
    boolean combinable(Term term);

    Term plus(Term term) throws Exception;

    Term mult(Term term) throws Exception;

    Factor getFactor();

    SignedInt getCoefficient();

    Term reverse();

    String toString();
}
