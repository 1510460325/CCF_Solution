package cn.wzy.draw;

import java.util.Scanner;

/**
 * Create by Wzy
 * on 2018/7/25 18:45
 * 不短不长八字刚好
 */
public class Main {//画图 90分

    static char[][] map;

    static class Point {
        int x;
        int y;

        public Point() {
        }

        public Point(int x, int y) {

            this.x = x;
            this.y = y;
        }
    }

    static void init(int n, int m) {
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = '.';
            }
        }
    }
    static void compare(Point a, Point b) {
        if (a.x == b.x) {
            if (a.y > b.y) {
                int temp = a.y;
                a.y = b.y;
                b.y = temp;
            }
        }
        if (a.y == b.y) {
            if (a.x > b.x) {
                int temp = a.x;
                a.x = b.x;
                b.x = temp;
            }
        }
    }
    static void drawLine(Point start, Point end) {
        compare(start, end);
        if (start.x == end.x) {//纵向划线‘|’
            for (int i = start.y; i <= end.y; i++) {
                if (map[start.x][i] == '-')
                    map[start.x][i] = '+';
                else
                    map[start.x][i] = '|';
            }
        }
        else {//横向划线‘—’
            for (int i = start.x; i <= end.x; i++) {
                if (map[i][start.y] == '|')
                    map[i][start.y] = '+';
                else
                    map[i][start.y] = '-';
            }
        }
    }

    static void cover(int x, int y, char c) {
        if (x < 0 || x >= map.length
                || y < 0 || y >= map[0].length
                || map[x][y] == '+'
                || map[x][y] == '-'
                || map[x][y] == '|'
                || map[x][y] == c
                ) {
            return;
        }
        map[x][y] = c;
        cover(x - 1, y, c);
        cover(x + 1, y, c);
        cover(x, y + 1, c);
        cover(x, y - 1, c);
    }

    static void print(int n, int m) {
        for (int j = m - 1; j >= 0; j--) {
            for (int i = 0; i < n; i++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
//        System.out.println();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();//height
        int m = scanner.nextInt();//width
        int time = scanner.nextInt();
        init(n, m);
        for (int i = 0; i < time; i++) {
            int type = scanner.nextInt();
            if (type == 0) {//划线
                Point start = new Point(scanner.nextInt(), scanner.nextInt());
                Point end = new Point(scanner.nextInt(), scanner.nextInt());
                drawLine(start, end);
//                print(n,m);
            }
            else {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                char c = scanner.next().charAt(0);
                cover(x,y,c);
//                print(n,m);
            }
        }
        print(n,m);
    }
}
