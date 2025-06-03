package mips;

import tools.MyIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PeepholeOptimize {
    public void optimize() {
        List<String> list = Arrays.asList(MyIO.getMips().split("\n"));
        ArrayList<String> mips = new ArrayList<>(list);
        Pattern pattern = Pattern.compile("(addiu|subiu) \\$(\\w\\w), \\$(\\w\\w), 0");
        Iterator<String> iterator = mips.iterator();
        while (iterator.hasNext()) {
            String code = iterator.next();
            Matcher matcher = pattern.matcher(code);
            if (matcher.find()) {
                String reg1 = matcher.group(2);
                String reg2 = matcher.group(3);
                if (reg1.equals(reg2)) {
                    iterator.remove();
                }
            }
        }
        //pattern = Pattern.compile("")
        for (String code : mips) {
            MyIO.writeToFile("mips", code + "\n");
        }
    }
}
