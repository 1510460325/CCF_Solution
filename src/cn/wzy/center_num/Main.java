package cn.wzy.center_num;

import java.util.Scanner;

/**
 * Create by Wzy
 * on 2018/7/16 22:56
 * 不短不长八字刚好
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n  = scanner.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++)
            num[i] = scanner.nextInt();
        scanner.close();
        for (int i = 0; i < n; i++) {
            int minSum = 0;
            int maxSum = 0;
            for (int j = 0; j < n; j++) {
                if (num[j] < num[i])
                    minSum++;
                if (num[j] > num[i])
                    maxSum++;
            }
            if (minSum == maxSum) {
                System.out.println(num[i]);
                return;
            }
        }
        System.out.println(-1);
    }
}
