package cn.wzy.tarjan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Create by Wzy
 * on 2018/7/23 21:24
 * 不短不长八字刚好
 */
public class Main {//高速公路 80分

    static List<Integer>[] lists;

    static boolean[] visit;

    static boolean[] contains;

    static Stack<Integer> stack;

    static int[] DFN;

    static int[] LOW;

    static int time = 0;

    static int ans = 0;

    static void init(int n) {
        lists = new List[n + 1];
        visit = new boolean[n + 1];
        contains = new boolean[n + 1];
        stack = new Stack<>();
        DFN = new int[n + 1];
        LOW = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            lists[i] = new ArrayList<>();
        }
    }

    static void tarjan(int u) {
        stack.push(u);
        DFN[u] = LOW[u] = ++time;
        visit[u] = contains[u] = true;
        List<Integer> list = lists[u];
        for (int i = 0; i < list.size(); i++) {
            int v = list.get(i);
            if (!visit[v]) {
                tarjan(v);
                LOW[u] = Math.min(LOW[u],LOW[v]);
            }
            else if (stack.contains(v)) {
                LOW[u] = Math.min(LOW[u],DFN[v]);
            }
        }
        if (LOW[u] == DFN[u]) {
            int i, num = 0;
            do{
                i = stack.pop();
                num ++;
            }while (i != u);
            ans += num * (num - 1) / 2;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),m = sc.nextInt();
        init(n);
        for (int i = 0; i < m; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            lists[a].add(b);
        }
        for (int i = 1; i <= n; i++) {
            if (!visit[i]) {
                tarjan(i);
            }
        }
        System.out.println(ans);
    }
}
