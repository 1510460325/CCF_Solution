package cn.wzy.Tetris;

import java.util.*;

/**
 * Create by Wzy
 * on 2018/7/24 14:40
 * 不短不长八字刚好
 */
public class Main {

    static int[][] map = new int[16][10];

    static int[][] block = new int[4][4];

    static int col;

    static class Point implements Comparable<Point> {
        public int col;
        public int row;

        public Point( int row,int col) {
            this.col = col;
            this.row = row;
        }

        public Point() {
        }

        @Override
        public int compareTo(Point o) {
            return this.col - o.col ;
        }
    }

    static List<Point> points;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        points = new ArrayList<>();
        for (int i = 0; i < 15; i++)
            for (int j = 0 ; j < 10; j++)
                map[i][j] = scanner.nextInt();
        for (int j = 0; j < 10; j++) {
            map[15][j] = 1;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                block[i][j] = scanner.nextInt();
                if (block[i][j] == 1) {
                    points.add(new Point(i,j));
                }
            }
        }
        Collections.sort(points);
        col = scanner.nextInt();
        int grap = col - points.get(0).col;
        grap = col - 1;
        int row = 0;
        while (true) {
            boolean flag = false;
            for (int i = 0; i < points.size(); i++) {
                Point now = points.get(i);
                if (map[now.row + row][grap + now.col] == 1) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
            row++;
        }
        row--;
        for (int i = 0; i < points.size(); i++) {
            map[points.get(i).row + row][grap + points.get(i).col] = 1;
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
