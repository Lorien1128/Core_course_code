import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "D:/Java/bin/stdout.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String str = "";
        HashMap<Integer, ArrayList<String>> mess = new HashMap<>();
        ArrayList<Double> times = new ArrayList<>();
        while ((str = buffReader.readLine()) != null) {
            Pattern p = Pattern.compile("(IN|OUT)-(\\d+)");
            Matcher m = p.matcher(str);
            if (m.find()) {
                int id = Integer.parseInt(m.group(2));
                if (mess.containsKey(id)) {
                    ArrayList<String> list = mess.get(id);
                    list.add(m.group(1));
                }
                else {
                    ArrayList<String> list = new ArrayList<>();
                    list.add(m.group(1));
                    mess.put(id, list);
                }
            }
            Matcher m1 = Pattern.compile("\\[ *(\\d+\\.\\d+)\\]").matcher(str);
            if (m1.find()) {
                times.add(Double.parseDouble(m1.group(1)));
            }
        }
        for (int id : mess.keySet()) {
            System.out.print(id + "   :  ");
            for (String s : mess.get(id)) {
                System.out.print(s + "    ");
            }
            System.out.print("\n");
        }
        for (int i = 0; i < times.size() - 1; i++) {
            if (times.get(i) > times.get(i + 1)) {
                System.out.println("up error.");
            }
        }
    }

}
