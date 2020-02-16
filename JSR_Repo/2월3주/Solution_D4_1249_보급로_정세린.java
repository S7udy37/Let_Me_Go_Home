/*
 *45,564 kb
 *167 ms
 */

package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_D4_1249_보급로_정세린 {

	static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String str;
		int[][] dh = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		int[][] map, memo;
		int N, max = 0;
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			memo = new int[N][N];
			max = N * N * 9;

			for (int i = 0; i < N; i++) {
				str = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
					memo[i][j] = max;
				}
			}

			Queue<Point> queue = new LinkedList<Point>();
			queue.offer(new Point(0, 0));
			memo[0][0] = map[0][0];
			Point current;

			while (!queue.isEmpty()) {
				current = queue.poll();
				for (int dir = 0; dir < 4; dir++) {
					int itmp = current.i + dh[dir][0];
					int jtmp = current.j + dh[dir][1];
					if (itmp >= 0 && itmp < N && jtmp >= 0 && jtmp < N) {
						if (memo[current.i][current.j] + map[itmp][jtmp] < memo[itmp][jtmp]) {
							memo[itmp][jtmp] = memo[current.i][current.j] + map[itmp][jtmp];
							queue.offer(new Point(itmp, jtmp));
						}
					}
				}
			}
			sb.append("#" + tc + " " + memo[N - 1][N - 1] + "\n");
		}
		System.out.print(sb);
	}

}