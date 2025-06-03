public class StrProcess {
    public String process(String raw) {
        String res;
        res = raw.replaceAll("\\*\\*","^");
        res = res.replaceAll("\\s","");
        StringBuilder sb = new StringBuilder();
        boolean hasOp = false;
        int cnt = 0;                // 记录前面减号的个数，用于合并
        for (int pos = 0;pos < res.length();++pos) {
            char c = res.charAt(pos);
            if ("+-".indexOf(c) == -1) {
                if (hasOp) {
                    if (cnt % 2 == 0) {
                        sb.append("+");
                    }
                    else {
                        sb.append("-");
                    }
                }
                cnt = 0;
                hasOp = false;
                sb.append(c);
            }
            else {
                hasOp = true;
                if (c == '-') {
                    ++cnt;
                }
            }
        }
        return sb.toString();
    }

}
