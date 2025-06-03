package task;

import java.util.ArrayList;

public class SumAndSelf {
    public String sumandself(String originString, ArrayList<SelfDefine> selfDefines) {
        SelfDefine kind = new SelfDefine();
        String[] element;
        String result;
        String name;
        String forward;
        String queen;
        String middle;
        ArrayList<String> actualPara = new ArrayList<>();
        result = originString;
        ArrayList<Integer> locat = new ArrayList<>();

        while (true) {
            name = findSumSelf(result, locat, 0);
            if (locat.get(0) == -1) {
                break;
            }
            for (SelfDefine selfDefine : selfDefines) {
                if (name.equals(selfDefine.getName())) {
                    kind = selfDefine;
                    break;
                }
            }
            getActualpara(result.substring(locat.get(1) + 1, locat.get(2)), actualPara);
            if (actualPara.size() == 1) {
                actualPara.set(0, sumandself(actualPara.get(0), selfDefines));
                middle = kind.operation(actualPara.get(0), "", "");
            }
            else if (actualPara.size() == 2) {
                actualPara.set(0, sumandself(actualPara.get(0), selfDefines));
                actualPara.set(1, sumandself(actualPara.get(1), selfDefines));
                middle = kind.operation(actualPara.get(0), actualPara.get(1), "");
            }
            else {
                actualPara.set(0, sumandself(actualPara.get(0), selfDefines));
                actualPara.set(1, sumandself(actualPara.get(1), selfDefines));
                actualPara.set(2, sumandself(actualPara.get(2), selfDefines));
                middle = kind.operation(actualPara.get(0), actualPara.get(1), actualPara.get(2));
            }
            forward = result.substring(0, locat.get(0));
            queen = result.substring(locat.get(2) + 1);
            result = forward + "(" + middle
                    + ")" + queen;
        }


        while (true) {
            name = findSumSelf(result, locat, 1);
            if (locat.get(0) == -1) {
                break;
            }
            String temp = result.substring(locat.get(1) + 1, locat.get(2));
            element = result.substring(locat.get(1) + 1, locat.get(2)).split(",");
            Sum sum = new Sum();
            int start = Integer.parseInt(element[1]);
            int end = Integer.parseInt(element[2]);
            forward = result.substring(0, locat.get(0));
            queen = result.substring(locat.get(2) + 1);
            result = forward + "(" + sum.operation(start, end, element[3]) + ")" + queen;
        }
        return result;
    }

    public static String findSumSelf(String string, ArrayList<Integer> locat, int type) {
        int bracket = 0;
        int flag = 0;
        int mark = 0;
        String name = "";
        char m;
        locat.removeAll(locat);
        if (type == 0) {
            for (int i = 0; i < string.length(); i++) {
                m = string.charAt(i);
                if ((m == 'f' || m == 'g' || m == 'h') && flag == 0) {
                    flag = 1;
                    name = Character.toString(m);
                    locat.add(i);
                } else if (flag == 1 && m == '(') {
                    if (mark == 0) {
                        locat.add(i);
                    }
                    bracket++;
                    mark = 1;
                } else if (flag == 1 && m == ')') {
                    bracket--;
                }
                if (mark == 1 && bracket == 0) {
                    locat.add(i);
                    return name;
                }
            }
            locat.add(-1);
            return name;
        }
        else {
            locat.add(string.indexOf("sum"));
            if (locat.get(0) == -1) {
                return name;
            }
            else {
                for (int i = locat.get(0); i < string.length(); i++) {
                    m = string.charAt(i);
                    if (m == '(') {
                        if (mark == 0) {
                            locat.add(i);
                        }
                        bracket++;
                        mark = 1;
                    } else if (m == ')') {
                        bracket--;
                    }
                    if (mark == 1 && bracket == 0) {
                        locat.add(i);
                        return name;
                    }
                }
            }
            locat.add(-1);
            return name;
        }
    }

    public void getActualpara(String s, ArrayList<String> actualParas) {
        actualParas.removeAll(actualParas);
        int bracket = 0;
        ArrayList<Integer> locat = new ArrayList<>();
        char m;
        for (int i = 0; i < s.length(); i++) {
            m = s.charAt(i);
            if (m == '(') {
                bracket++;
            }
            else if (m == ')') {
                bracket--;
            }
            else if (m == ',' && bracket == 0) {
                locat.add(i);
            }
        }
        if (locat.size() == 0) {
            actualParas.add(s);
        }
        else {
            actualParas.add(s.substring(0, locat.get(0)));
            for (int i = 0; i < locat.size(); i++) {
                if (i == locat.size() - 1) {
                    actualParas.add(s.substring(locat.get(i) + 1));
                }
                else {
                    actualParas.add(s.substring(locat.get(i) + 1, locat.get(i + 1)));
                }
            }
        }
    }
}
