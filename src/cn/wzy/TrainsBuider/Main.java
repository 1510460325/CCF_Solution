package cn.wzy.TrainsBuider;

import java.util.*;

/**
 * Create by Wzy
 * on 2018/7/17 13:38
 * 不短不长八字刚好
 */
public class Main {//铁路修建 100

    static class Line{
        public int start;
        public int end;
        public int len;
        public Line(int start, int end, int len) {
            this.start = start;
            this.end = end;
            this.len = len;
        }
        public Line(){

        }
    }

    static List<Line> lines;
    static int[] root;
    static void init(){
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }
    static int findRoot(int i) {
        if (root[i] == i)
            return i;
        else
            return root[i] = findRoot(root[i]);
    }
    static void merge(int x, int y) {
        int i = findRoot(x);
        int j = findRoot(y);
        if (i != j) {
            root[i] = j;
        }
    }
    static boolean isConn(int x, int y) {
        int i = findRoot(x);
        int j = findRoot(y);
        return i == j;
    }
    static int solve(int n){
        lines.sort(new Comparator<Line>() {
            @Override
            public int compare(Line o1, Line o2) {
                return o1.len - o2.len;
            }
        });
        for (Line line:lines) {
            int start = line.start, end = line.end;
            if (!isConn(start,end)) {
                merge(start,end);
                if (isConn(1,n)) {
                    return line.len;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        root = new int[n + 1];
        init();
        lines = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            Line line = new Line();
            line.start = scanner.nextInt();
            line.end = scanner.nextInt();
            line.len = scanner.nextInt();
            lines.add(line);
        }
        System.out.println(solve(n));
    }
}
