package tools;

import mcode.MidCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyIO {
    private static final ArrayList<ErrorMsg> ERROR_MSG = new ArrayList<>();
    private static final ArrayList<MidCode> MID_CODES = new ArrayList<>();
    private static final StringBuilder BUILDER = new StringBuilder();
    private static final StringBuilder MIPS = new StringBuilder();
    private static boolean errorBufferOn = true;
    private static boolean midCodeBufferOn = true;
    private static boolean init = true;

    public static String readIn() {
        String path = "testfile.txt";
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
            String str;
            while ((str = br.readLine()) != null) {
                stringBuilder.append(str).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static void writeToFile(String name, String ans) {
        writeToFile(name, ans, true);
    }

    public static void writeToFile(String name, String ans, boolean append) {
        BUILDER.append(ans);
        File file = new File(name + ".txt");
        if (init && append) {
            boolean r = file.delete();
            if (!r) {
                System.err.println("文件初始化失败");
            }
            init = false;
        }
        file = new File(name + ".txt");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(file, append);
            fos.write(ans.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToMipsBuffer(String str) {
        MIPS.append(str);
    }

    public static String getMips() {
        return MIPS.toString();
    }

    public static void closeErrorBuffer() {
        errorBufferOn = false;
    }

    public static void openErrorBuffer() {
        errorBufferOn = true;
    }

    public static void closeMidCodeBuffer() {
        midCodeBufferOn = false;
    }

    public static void openMidCodeBuffer() {
        midCodeBufferOn = true;
    }

    public static void writeToErrorBuffer(int line, String sort) {
        if (errorBufferOn) {
            for (ErrorMsg msg : ERROR_MSG) {
                if (msg.getLine() == line) {
                    return;
                }
            }
            ERROR_MSG.add(new ErrorMsg(line, sort));
        }
    }

    public static String readErrorBuffer() {
        Collections.sort(ERROR_MSG);
        StringBuilder sb = new StringBuilder();
        for (ErrorMsg msg : ERROR_MSG) {
            sb.append(msg).append("\n");
        }
        return sb.toString();
    }

    public static void writeInMidCode(MidCode midCode) {
        if (midCodeBufferOn) {
            MID_CODES.add(midCode);
        }
    }

    public static void clearMidCode() {
        MID_CODES.clear();
    }

    public static MidCode getLastMidCode() {
        return MID_CODES.get(MID_CODES.size() - 1);
    }

    public static ArrayList<MidCode> getMidCodes() {
        return MID_CODES;
    }

    public static String printMidCode() {
        StringBuilder stringBuilder = new StringBuilder();
        for (MidCode midCode : MID_CODES) {
            stringBuilder.append(midCode);
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    public static void check() throws Exception {
        String[] strings = BUILDER.toString().split("\n");
        for (String s : strings) {
            final Matcher m = Pattern.compile("\\$(\\w\\d)").matcher(s);
            String[] o = new String[3];
            o[0] = null;
            o[1] = null;
            o[2] = null;
            int i = 0;
            while (m.find()) {
                o[i] = m.group(1);
                i++;
            }
            if (o[1] != null && o[2] != null && o[1].equals(o[2])) {
                if (o[1].equals("t0") || o[1].equals("t1")) {
                    throw new Exception();
                }
            }
            else if (o[0] != null && o[1] != null && o[0].equals(o[1])) {
                if (s.contains("sw")) {
                    throw new Exception();
                }
            }
        }
    }

    public static boolean isMidCodeBufferOn() {
        return midCodeBufferOn;
    }
}
