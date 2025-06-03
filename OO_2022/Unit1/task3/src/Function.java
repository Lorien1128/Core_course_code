import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Function {
    private String  name;
    private ArrayList<String> parameters = new ArrayList<>();
    private String poly;

    public Function(String rawStr) {
        String str = rawStr;
        str = str.replaceAll("[ \\t]","");
        Pattern p1 = Pattern.compile("([fgh])\\((.*?)\\)=(.*)");
        Matcher m1 = p1.matcher(str);
        if (m1.find()) {
            this.name = m1.group(1);
            String[] paras = m1.group(2).split(",");
            this.parameters.addAll(Arrays.asList(paras));
            this.poly = m1.group(3);
        }
        else {
            System.exit(0);
        }
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<String> getParameters() {
        return this.parameters;
    }

    public String getPoly() {
        return this.poly;
    }

    public String substituteIn(String actualPara) {
        String str = this.poly;
        String[] actualParas = actualPara.split("\\$");
        int mark = -1;
        for (int i = 0; i < this.parameters.size(); i++) {
            if (this.parameters.get(i).equals("x")) {
                str = str.replaceAll(this.parameters.get(i),"(" + actualParas[i] + ")");
                mark = i;
                break;
            }
        }
        for (int i = 0; i < this.parameters.size(); i++) {
            if (i != mark) {
                str = str.replaceAll(this.parameters.get(i),"(" + actualParas[i] + ")");
            }
        }
        return str;
    }
}
