

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;

public class MainClass {
    public static void main(String[] argv) throws IOException {
        String filePath = "C:/Users/Dell/Desktop/sj.txt";
        FileInputStream fin = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fin);
        BufferedReader buffReader = new BufferedReader(reader);
        String str = "";
        while((str = buffReader.readLine())!=null){
            str = str.replaceAll("[\\s\\t]+","");
            Parser parser = new Parser(str);
            HashMap<Integer, BigDecimal> result = parser.getResult();
            int zeros = findzeros(result);

            if (zeros == result.size()) {
                System.out.print(0);//  所有项系数全为0
            }
            else {  //  必有一个项不等于0，必有输出
                for (Integer key : result.keySet()) {
                    if (result.get(key).equals(new BigDecimal(0))) {
                        continue;
                    }
                    if (result.get(key).compareTo(new BigDecimal(0)) > 0) {
                        System.out.print("+");
                    }
                    System.out.print(result.get(key));
                    if (key != 0) {
                        System.out.print("*x");
                        if (key != 1) {
                            System.out.print("**" + key);
                        }
                    }
                }
            }
            System.out.print("\n");
        }



    }

    public static int findzeros(HashMap <Integer, BigDecimal> map) {
        int zeronum = 0;
        for (Integer key : map.keySet()) {
            if (map.get(key).compareTo(new BigDecimal(0)) == 0) {
                zeronum++;
            }
        }
        return zeronum;
    }
}
