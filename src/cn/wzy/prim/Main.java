package cn.wzy.prim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Create by Wzy
 * on 2018/7/24 16:47
 * 不短不长八字刚好
 */
public class Main {

    static int[][] map;

    static List<Integer> tree;

    static boolean[] visit;

    static int refresh(int[][] min, int v) {
        int minIndex = 1;
        for (int i = 1; i < min.length; i++) {
            if (visit[i]) {
                continue;
            }
            if (map[v][i] < min[i][1] && map[v][i] != 0) {
                min[i][0] = v;
                min[i][1] = map[v][i];
            }
            if (min[minIndex][1] > min[i][1] || visit[minIndex])
                minIndex = i;
        }
        return minIndex;
    }
    static void prim(int u, int n) {
        int[][] min = new int[n + 1][2];
        for (int i = 0; i < min.length; i++) {
            min[i][0] = 0;//表示到i最近是u
            min[i][1] = 65535;//表示u->i的长度
        }
        int now = u;
        visit[now] = true;
        tree.add(now);
        while(n-- != 1) {
            now = refresh(min,now);
            visit[now] = true;
            tree.add(now);
            System.out.println(min[now][0] + "->" + now + ":"  + min[now][1]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m =scanner.nextInt();
        map = new int[n + 1][n + 1];
        visit = new boolean[n + 1];
        tree = new ArrayList<>(n);
        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            map[a][b] = c;
            map[b][a] = c;
        }
        scanner.close();
        prim(1,5);
    }
}
/**
5 6
1 4 2
1 5 1
1 2 3
2 3 4
3 5 5
4 5 3
 */
