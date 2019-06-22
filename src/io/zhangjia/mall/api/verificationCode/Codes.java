package io.zhangjia.mall.api.verificationCode;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class Codes {
    static int fileCount = 0; // 文件数
    static int codeCount = 0; // 代码数
    static int classCount = 0; // 类的个数
    static int interfaceCount = 0;
    static int method = 0;
    static int co = 0;
    static Map<String, Integer> codes = new TreeMap<>();
    static Map<String, Integer> methods = new TreeMap<>();

    public void exeMethod(String f, File file) {

        // Scanner input = new Scanner(System.in);
        // File file = null;
        // while (true) {
        // // f = input.nextLine();
        // file = new File(f);
        // if (!file.exists()) {
        // System.out.print("输入地址不合法，请重新输入：");
        // } else {
        // break;
        // }
        //
        // }

        statistics(file);
        int i = 1;
//		System.out.println(codes);
        //根据值升序
        List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(codes.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return (o1.getValue() - o2.getValue());
            }
        });
        /*
         * for (Map.Entry<String, Integer> entry : codes.entrySet()) {
         * System.out.print(i++ + ":\t"); System.out.printf("%-20s", entry.getKey());
         * System.out.print("\t有  " + entry.getValue() + " 行代码"); method +=
         * methods.get(entry.getKey()); System.out.println("\t有  " +
         * methods.get(entry.getKey()) + "个方法"); codeCount += entry.getValue(); }
         *
         */
        for (Entry<String, Integer> code : list) {
            System.out.print(i++ + ":\t");
            System.out.printf("%-20s", code.getKey());
            System.out.print("\t有  " + code.getValue() + " 行代码");
            method += methods.get(code.getKey());
            System.out.println("\t有  " + methods.get(code.getKey()) + "个方法");
            codeCount += code.getValue();
        }
        System.out.println("\n\n--------------------本项目统计结果如下--------------------");
        System.out.print(
                "一共有：\n" + fileCount + "个文件\n" + classCount + "个类\n" + interfaceCount + "个接口\n" + method + "个方法\n");
        System.out.println(codeCount + "行代码\n代码多并不代表写的好，可能十行就能解决的功能，你写了100行，继续加油吧！");

        // input.close();
    }

    public void statistics(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                statistics(f);
            }

        } else {
            Reader reader = null;
            Reader reader2 = null;
            co = 0;
            try {
                reader = new FileReader(file);
                reader2 = new FileReader(file);
                ;
                char[] cbuf = new char[32];
                int len = 0;
                StringBuilder sb = new StringBuilder();
                while ((len = reader.read(cbuf)) != -1) {
                    String str = new String(cbuf, 0, len);
                    sb.append(str);
                }
                String str = sb.toString();
                if (str.contains("public class")) {
                    classCount++;
                }

                if (!file.getName().contains("Codes") && (str.contains("public interface")
                        || str.contains("private interface") || str.contains("protected interface"))) {
                    interfaceCount++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            LineNumberReader l = new LineNumberReader(reader2);
            try {
                while (l.readLine() != null) {
                    co++;
                }
                codes.put(file.getName(), co);
            } catch (IOException e) {
                e.printStackTrace();
            }
            fileCount++;

            // 去掉Java
            String s1 = file.getPath().replaceAll(".java", "");
            // 去掉src前面的字
            String s2 = s1.substring(s1.indexOf("src") + 4);
            // 去掉\
            String s3 = s2.replaceAll("\\\\", ".");

            Method[] ms = null;
            try {
                ms = Class.forName(s3).getDeclaredMethods();
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 准备一个字符串数组，用来保存字段名
            methods.put(file.getName(), ms.length);

        }

    }

}