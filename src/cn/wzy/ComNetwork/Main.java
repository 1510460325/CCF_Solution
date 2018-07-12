package cn.wzy.ComNetwork;

import java.util.*;

/**
 * Create by Wzy
 * on 2018/7/12 16:39
 * 不短不长八字刚好
 */
public class Main {
    static boolean[][] conn;
    static Map<Integer,List<Integer>> map;
    static int size;
    static boolean[] visit;

    public static void init(int n) {
        conn = new boolean[n][n];
        visit = new boolean[n];
        map = new HashMap<>();
        size = n;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                conn[i][j] = false;
                if (i == j)
                    conn[i][j] = true;
            }
        }
    }

    public static void initvisit() {
        for (int i = 0; i < size; i++) {
            visit[i] = false;
        }
    }

    public static void addToMap(int a, int b) {
        List<Integer> list = map.get(a);
        if (list == null) {
            list = new ArrayList<>();
            list.add(a);
            map.put(a,list);
        }
        if (!list.contains(b))
            list.add(b);
    }

    public static boolean isConnect(int a, int b) {
        List<Integer> list = map.get(a);
        if (list == null) {
            return false;
        }
        return list.contains(b);
    }

    public static void dfs(int origin, int now) {
        visit[now] = true;
        conn[origin][now] = conn[now][origin] = true;
        List<Integer> list = map.get(now);
        if (list == null)
            return;
        for (int i = 0; i < list.size(); i++) {
            if (!visit[list.get(i)])
                dfs(origin, list.get(i));
        }
    }

    public static int getAns() {
        int count = 0;
        for (int i = 0; i < size; i++) {
            boolean flag = true;
            for (int j = 0; j < size; j++) {
                if (!conn[i][j]) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        init(n);
        int m = scanner.nextInt();
        while (m-- != 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            addToMap(a - 1, b - 1);
        }
        for (int i = 0; i < n; i++) {
            initvisit();
            dfs(i,i);
        }
        System.out.println(getAns());
    }
}
