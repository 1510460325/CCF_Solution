package cn.wzy.templateGenerator;

import java.util.*;

/**
 * Create by Wzy
 * on 2018/7/17 22:35
 * 不短不长八字刚好
 */
public class Main {//模板生成 90分
    static List<String> template;
    static Map<String, String> names;

    static void init(int n, int m) {
        template = new ArrayList<>(n);
        names = new HashMap<>(m);
    }

    static void addToNames(String string) {
        string = string.replace("\"", "");
        String[] strs = string.split(" ");
        String name = strs[0];
        String value = strs[1];
        for (int i = 2; i < strs.length; i++) {
            value += " " + strs[i];
        }
        names.put(name, value);
    }

    static void print() {
        for (String str:template) {
            int start = 0;
            while ((start = str.indexOf("{{",start)) != -1) {
                int end = str.indexOf("}}",start);
                if (end == -1)
                    break;
                String name = str.substring(start + 3,end - 1).replace(" ","");
                if (names.containsKey(name)) {
                    str = str.substring(0,start) + names.get(name) + str.substring(end + 2);
                }
                else
                    str = str.substring(0,start) + str.substring(end + 2);
            }
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.useDelimiter("\n");
        init(n,m);
        for (int i = 0; i < n; i++) {
            template.add(scanner.next());
        }
        for (int i = 0; i < m; i++) {
            addToNames(scanner.next());
        }
        print();
    }
}
