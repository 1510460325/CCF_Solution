package cn.wzy.jsonSearch;

import java.util.*;

/**
 * Create by Wzy
 * on 2018/7/17 14:10
 * 不短不长八字刚好
 */
public class Main {//40
    static List<String> strs;
    static Map<String, String> map;
    static void handler(){
        map = new HashMap<>();
        String pre = "";
        for (int i = 1; i < strs.size(); i++) {
            String now = strs.get(i);
            if (now.equals("}"))
                break;
            if (now.equals("},")) {
                int point = pre.lastIndexOf(".");
                if (point == -1)
                    pre = "";
                else
                    pre = pre.substring(0,point);
                continue;
            }
            int middle = now.indexOf("\":");
            String key = now.substring(1,middle);
            if (now.charAt(now.length() - 1) == '{') {
                if (pre.equals(""))
                    map.put(pre + key,"OBJECT");
                else
                    map.put(pre + "." + key,"OBJECT");
                if (pre.equals(""))
                    pre += key;
                else
                    pre += "." + key;
            }
            else if (now.charAt(now.length() - 1) == ','){
                String value = now.substring(middle + 3,now.length() - 2);
                if (pre.equals(""))
                    map.put(pre + key,"STRING " + value);
                else
                    map.put(pre + "." + key,"STRING " + value);
            }
            else {
                String value = now.substring(middle + 3,now.length() - 1);
                if (pre.equals(""))
                    map.put(pre + key,"STRING " + value);
                else
                    map.put(pre + "." + key,"STRING " + value);
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        strs = new ArrayList<>(n);
        scanner.useDelimiter("\n");

        for (int i = 0; i < n; i++) {
            String string = scanner.next();
            string = string.replace(" ","");
            string = string.replace("\\\"","\"");
            string = string.replace("\\\\","\\");
            strs.add(string);
        }
        handler();
        for (int i = 0; i < m; i++) {
            String key = scanner.next();
            if (map.get(key) != null)
                System.out.println(map.get(key));
            else
                System.out.println("NOTEXIST");
        }
        scanner.close();
    }
}
