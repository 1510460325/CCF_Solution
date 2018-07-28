package cn.wzy.key;

import java.util.*;

/**
 * Create by Wzy
 * on 2018/7/28 10:37
 * 不短不长八字刚好
 */
public class Main {//公共钥匙盒
    static class Time {
        int point;
        List<Integer> back = new ArrayList<>();
        List<Integer> lend = new ArrayList<>();

        @Override
        public boolean equals(Object obj) {
            return this.point == ((Time) obj).point;
        }
    }

    static Map<Integer, Time> times = new TreeMap<>();

    static int[] keys;

    static void put(List<Integer> list) {
        int top = 0;
        if (top == list.size())
            return;
        for (int i = 1; i < keys.length; i++) {
            if (keys[i] == 0) {
                keys[i] = list.get(top);
                top++;
                if (top == list.size())
                    return;
            }
        }
    }

    static void get(List<Integer> list) {
        int top = 0;
        if (top == list.size())
            return;
        for (int i = 1; i < keys.length; i++) {
            if (list.contains(keys[i])) {
                keys[i] = 0;
                list.remove((Object) keys[i]);
            }
        }
    }

    static void run() {
        for (Integer i : times.keySet()) {
            Time time = times.get(i);
            if (time.back != null) {
                Collections.sort(time.back);
                put(time.back);
            }
            if (time.lend != null) {
                Collections.sort(time.lend);
                get(time.lend);
            }
        }
        for (int i = 1; i < keys.length; i++) {
            System.out.print(keys[i] + " ");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        keys = new int[n + 1];
        for (int i = 1; i <= n; i++)
            keys[i] = i;
        for (int i = 0; i < m; i++) {
            int x = scanner.nextInt();
            int start = scanner.nextInt();
            int len = scanner.nextInt();
            Time temp = times.get(start);
            if (temp == null) {
                temp = new Time();
                temp.point = start;
                temp.lend.add(x);
                times.put(start, temp);
            } else {
                temp.lend.add(x);
            }

            Time tem = times.get(start + len);
            if (tem == null) {
                tem = new Time();
                tem.point = start + len;
                tem.back.add(x);
                times.put(start + len, tem);
            } else {
                tem.back.add(x);
            }
        }
        run();
    }
}
