package task;

public class Formatchange {
    public static void deletoper(String originString, StringBuilder stringBuilder) {
        int add = 0;
        int sub = 0;
        int flag = 0;
        for (int i = 0; i < originString.length(); i++) {
            if (originString.charAt(i) == '+') {
                add++;
            }
            else if (originString.charAt(i) == '-') {
                sub++;
            }
            else {
                if (sub == 0 && add == 0) {
                    stringBuilder.append(originString.charAt(i));
                    flag = 1;
                }
                else {
                    if ((sub & 1) == 1) {
                        if (flag == 1) {
                            stringBuilder.append("+-");
                        }
                        else {
                            stringBuilder.append("-");
                        }
                        stringBuilder.append(originString.charAt(i));
                        flag = 1;
                        sub = 0;
                        add = 0;
                    }
                    else {
                        if (flag == 1) {
                            stringBuilder.append('+');
                        }
                        stringBuilder.append(originString.charAt(i));
                        flag = 1;
                        sub = 0;
                        add = 0;
                    }
                }
                if (originString.charAt(i) == '(' ||
                        originString.charAt(i) == ',' ||
                        originString.charAt(i) == '*') {
                    flag = 0;
                }
            }
        }
    }

    public String formatechange(String string) {
        String originString = string.replaceAll(" ", "");
        originString = originString.replaceAll("\t", "");
        StringBuilder stringBuilder = new StringBuilder();
        deletoper(originString, stringBuilder);
        return stringBuilder.toString();
    }
}
