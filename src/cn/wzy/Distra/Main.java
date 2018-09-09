package cn.wzy.Distra;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author wzy 不短不长八字刚好.
 * @since 2018/9/9 16:01
 */
public class Main {

	static int[] dis;//最短距离
	static int[][] map;//邻接矩阵
	static boolean[] comfirm;

	static void init(int n) {
		map = new int[n + 1][n + 1];
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= n; j++)
				if (i != j)
					map[i][j] = 65535;
		dis = new int[n + 1];
		comfirm = new boolean[n + 1];
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt(), m = scan.nextInt();
		init(n);
		for (int i = 0; i < m; i++) {
			int u = scan.nextInt(), v = scan.nextInt(), len = scan.nextInt();
			map[u][v] = map[v][u] = len;
		}
		dijistra(1, n);
	}

	private static void dijistra(int u, int n) {
		dis[u] = 0;
		for (int i = 1; i <= n; i++) {
			dis[i] = map[u][i];
		}

		for (int time = 1; time < n; time++) {
			int min = 1;
			for (int i = 1; i <= n; i++) { //确定最近的一个点加入s集合
				if (!comfirm[i]) {
					if (dis[i] < dis[min] || comfirm[min])
					min = i;
				}
			}
			comfirm[min] = true;
			for (int i = 1; i <= n; i++) {
				if (map[min][i] != 65535 && !comfirm[i] && i != min) {
					dis[i] = dis[i] < dis[min] + map[i][min] ? dis[i] : dis[min] + map[i][min];
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			System.out.print(dis[i] + " ");
		}
	}
}
