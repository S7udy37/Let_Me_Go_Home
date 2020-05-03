/**
* Memory : 14072KB
* Time : 164ms
*/
package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1_오인호 {
	/**
	 * 1 : 가로 => 가로, 대각선 2 : 세로 => 세로, 대각선 3 : 대각선 => 가로, 세로, 대각선
	 */
	private static int[][] map;
	private static int N, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = 0;
		map[1][1] = map[1][2] = 1;
		dfs(1, 2, 1);
		System.out.println(ans);
	}

	private static void dfs(int x, int y, int t) {
		if (x == N && y == N) {
			ans++;
			return;
		}
		if (t == 1) {
			// 가로방향일때
			// 1. 가로로 놓는법
			if ( y + 1 <= N && map[x][y + 1] == 0) {
				dfs(x, y + 1, t);
			}
			// 2. 대각선으로 놓는법
			if (x + 1 <= N && y + 1 <= N && map[x][y + 1] + map[x + 1][y + 1] + map[x + 1][y] == 0) {
				dfs(x + 1, y + 1, 3);
			}
		} else if (t == 2) {
			// 세로방향일때
			// 1. 세로로
			if (x + 1 <= N && map[x + 1][y] == 0 ) {
				dfs(x + 1, y, t);
			}
			// 2. 대각선으로 놓는법
			if (x + 1 <= N && y + 1 <= N && map[x][y + 1] + map[x + 1][y + 1] + map[x + 1][y] == 0) {
				dfs(x + 1, y + 1, 3);
			}
		} else {
			// 대각선
			// 1. 가로로 놓는법
			if ( y + 1 <= N && map[x][y + 1] == 0) {
				dfs(x, y + 1, 1);
			}
			// 2. 대각선으로 놓는법
			if (x + 1 <= N && y + 1 <= N && map[x][y + 1] + map[x + 1][y + 1] + map[x + 1][y] == 0) {
				dfs(x + 1, y + 1, t);
			}
			// 3. 세로로
			if (x + 1 <= N && map[x + 1][y] == 0 ) {
				dfs(x + 1, y, 2);
			}

		}

	}

}
