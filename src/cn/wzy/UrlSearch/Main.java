package cn.wzy.UrlSearch;

import java.util.*;
/**
 * Create by Wzy
 * on 2018/7/11 14:58
 * 不短不长八字刚好
 */

public class Main {
    static class Handler{
        public String url;
        public String content;

        public Handler(String url, String content) {
            this.url = url;
            this.content = content;
        }

        public String compare(String now) {
            int s1 = 1, s2 = 1, e1, e2;
            String param = "";
            boolean isSolve;
            while (true) {
                if (s1 > now.length() && s2 > url.length()) {
                    isSolve = true;
                    break;
                }
                if (s1 > now.length() || s2 > url.length()) {
                    isSolve = false;
                    break;
                }
                e1 = now.indexOf("/", s1);//下一个‘/’所在位置
                e2 = url.indexOf("/", s2);//下一个‘/’所在位置
                if (e1 == -1)
                    e1 = now.length();
                if (e2 == -1)
                    e2 = url.length();
                if (isRight(now,s1,e1,s2,e2)){//一模一样
                    s1 = e1 + 1;
                    s2 = e2 + 1;//从下一个'/'的下一位开始
                    continue;
                }
                String temp = now.substring(s1,e1);
                if(type(url.substring(s2,e2)) == 1) {//int 参数
                    if (isInt(temp))
                        param += " " + Integer.parseInt(temp);
                    else {
                        isSolve = false;
                        break;
                    }
                    s1 = e1 + 1;
                    s2 = e2 + 1;//从下一个'/'的下一位开始
                    continue;
                }
                if (type(url.substring(s2,e2)) == 2) {//String 参数
                    if (isInt(temp)) {
                        isSolve = false;
                        break;
                    }
                    param += " " + temp;
                    s1 = e1 + 1;
                    s2 = e2 + 1;//从下一个'/'的下一位开始
                    continue;
                }
                if (type(url.substring(s2,e2)) == 3) {//path参数
                    param += " " + now.substring(s1);
                    isSolve = true;
                    break;
                }
                else {
                    isSolve = false;
                    break;
                }
            }
            if (isSolve)
                return this.content + param;
            return null;
        }

        public boolean isInt(String str) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) < '0' || str.charAt(i) > '9')
                    return false;
            }
            return true;
        }

        public int type(String str) {
            switch (str) {
                case "<int>": return 1;
                case "<str>": return 2;
                case "<path>": return 3;
            }
            return -1;
        }

        public boolean isRight(String now,int s1, int e1, int s2, int e2) {
            return now.substring(s1,e1).equals(url.substring(s2,e2));
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        List<Handler> handlers = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            Handler temp = new Handler(scanner.next(), scanner.next());
            handlers.add(temp);
        }
        for (int i = 0; i < m; i++) {
            String now = scanner.next();
            boolean solve = false;
            String ans = "404";
            for (int j = 0; j < n; ++j) {
                ans = handlers.get(j).compare(now);
                if (ans != null) {
                    solve = true;
                    break;
                }
            }
            if (solve == true)
                System.out.println(ans);
            else
                System.out.println("404");
        }
        scanner.close();
    }
}

