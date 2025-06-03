public interface Jml {
    /*@
    @ public normal_behavior
    @ requires string != null;
    @ assignable \nothing
    @ ensures (\forall int i; i >= 0 && i < string.length;
    @          (\exist int j; j >= 0 && j < \result.length && j <= i; string.charAt(i) == \result.charAt(j))
    @ ensures \result.matches("^\s+.*?^\s+) || \result == ""
    @ ensures (\exist int i, j; i, j >= 0 && i, j < string.length && i <= j; \result == string.substring(i, j)
    @          && (\forall k; k < i && k > j; string.substring(k, k).matches("\s")))
    @ also
    @ public exceptional_behavior
    @ signals (NullPointerException e) string == null;
    */
    public String trim(String string);
}

