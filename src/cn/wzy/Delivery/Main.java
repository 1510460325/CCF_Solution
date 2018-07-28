package cn.wzy.Delivery;

import java.util.*;

/**
 * Create by Wzy
 * on 2018/7/25 19:46
 * 不短不长八字刚好
 */
public class Main {//送货 30分 超时

    static List<Integer>[] map;

    static boolean[][] visit;

    static Stack<Integer> path = new Stack<>();

    static List<Integer> ans = new ArrayList<>();

    static void init(int n) {
        map = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            map[i] = new ArrayList<>();
        }
        visit = new boolean[n + 1][n + 1];
    }


    static boolean check() {
        if (ans.size() == 0) {
            return true;
        }
        for (int i = 0; i < path.size(); i++) {
            if (path.get(i) > ans.get(i))
                return false;
            if (path.get(i) < ans.get(i))
                return true;
        }
        return true;
    }

    static void dfs(int u, int n) {
        path.push(u);
        boolean result = check();
        if (!result) {
            path.pop();
            return;
        }
        if (path.size() == n + 1) {
            ans.clear();
            for (Integer i : path) {
                ans.add(i);
            }
            path.pop();
            return;
        }

        List<Integer> list = map[u];
        for (int i = 0; i < list.size(); i++) {
            int v = list.get(i);
            if (!visit[u][v]) {
                visit[u][v] = visit[v][u] = true;
                dfs(v,n);
                visit[u][v] = visit[v][u] = false;
            }
        }
        path.pop();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        init(n);
        int[] times = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            map[a].add(b);
            map[b].add(a);
            times[a] ++ ;
            times[b] ++;
        }
        scanner.close();
        int num = 0;
        for (int i = 1; i <= n; i++) {
            if (times[i] % 2 == 1) {
                num++;
            }
            if (times[i] == 0) {
                System.out.println(-1);
                return;
            }
        }
        if (num != 2 && num != 0) {
            System.out.println(-1);
            return;
        }
        dfs(1,m);
        if (ans.size() == 0) {
            System.out.println(-1);
        }
        else {
            for (Integer i : ans) {
                System.out.print(i + " ");
            }
        }
    }
}
