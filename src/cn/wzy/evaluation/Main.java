package cn.wzy.evaluation;

import java.util.Scanner;

/**
 * Create by Wzy
 * on 2018/7/28 9:41
 * 不短不长八字刚好
 */
public class Main {//棋局评估 25 分
    static int[][] map = new int[3][3];

    static int score() {
        int sum = 1;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (map[i][j] == 0)
                    sum++;
        return sum;
    }

    static boolean win(int person) {
        for (int i = 0; i < 3; i++) {
            if (map[i][0] == map[i][1]
                    && map[i][1] == map[i][2]
                    && map[i][2] == person)
                return true;
        }
        if (map[0][0] == map[1][1]
                && map[1][1] == map[2][2]
                && map[2][2] == person)
            return true;
        if (map[0][2] == map[1][1]
                && map[1][1] == map[2][0]
                && map[2][0] == person)
            return true;
        return false;
    }

    static int dfs(int person) {
        if (score() == 1)
            return 0;
        int Max = -65535, Min = 65535;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[i][j] == 0) { //可以下
                    map[i][j] = person;
                    if (win(person)) {
                        int score = person == 1 ? score() : -score();
                        map[i][j] = 0;
                        if (person == 1) {
                            Max = Max > score ? Max : score;
                            return Max;
                        } else {
                            Min = Min > score ? score : Min;
                            return Min;
                        }
                    }
                    if (person == 1)
                        Max = Math.max(dfs(2), Max);
                    else
                        Min = Math.min(dfs(1), Min);
                    map[i][j] = 0;
                }
            }
        }
        if (person == 1)
            return Max;
        else
            return Min;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        while (n-- != 0) {
            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 3; j++)
                    map[i][j] = scanner.nextInt();

            if (win(1))
                System.out.println(score());
            else if (win(2))
                System.out.println(-score());
            else {
                System.out.println(dfs(1));
            }
        }
    }
}
