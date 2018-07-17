package cn.wzy.highway;

        import java.util.*;

/**
 * Create by Wzy
 * on 2018/7/17 22:06
 * 不短不长八字刚好
 */
public class Main {//60

    static Map<Integer,List<Integer>> map = new HashMap<>();//邻接表
    static boolean[] visit;
    static boolean[][] conn;
    static void addTomap(int a, int b) {
        List<Integer> list = map.get(a);
        if (list == null) {
            list = new ArrayList<>();
            list.add(b);
            map.put(a,list);
        }
        else {
            list.add(b);
        }
    }

    static void dfs(int origin, int now) {
        visit[now] = true;
        conn[origin][now] = true;
        List<Integer> list = map.get(now);
        if (list == null) {
            return;
        }
        for (Integer i : list) {
            if (!visit[i]) {
                dfs(origin, i);
            }
        }
    }
    static void initVisit(){
        for (int i = 0; i < visit.length; i++) {
            visit[i] = false;
        }
    }
    static int getAns(int n) {
        int sum = 0;
        for (int i = 1; i <= n - 1; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (conn[i][j] && conn[j][i]) {
                    sum++;
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        conn = new boolean[n + 1][n + 1];
        visit = new boolean[n + 1];
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            addTomap(a, b);
        }
        scanner.close();
        for (int i = 1; i <= n; i++) {
            initVisit();
            dfs(i,i);
        }
        System.out.println(getAns(n));
    }
}
