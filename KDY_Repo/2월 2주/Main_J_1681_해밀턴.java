package study_0210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_1681_해밀턴 {
	static boolean[] visited;
	static int[][] map;
	static int sum;
	static int n;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine().trim());
		map = new int[n][n];
		min = Integer.MAX_VALUE;
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i < n; i++) {
			visited = new boolean[n];
			sum = 0;
			if (map[0][i] != 0) {
				dfs(0, i, 1);
			}
		}
		System.out.println(min);
	}

	public static void dfs(int x, int y, int cnt) {
		if (sum > min) {
			return;
		}

		sum = sum + map[x][y];
		visited[x] = true;

		if (cnt == (n - 1)) {
			sum = sum + map[y][0];
			if (map[y][0] == 0) {
				return;
			}
			min = Math.min(min, sum);
			sum = sum - map[y][0];
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i] && map[y][i] != 0 && (sum < min)) {
				dfs(y, i, cnt + 1);
				sum = sum - map[y][i];
				visited[i] = false;
			}
		}
	}
}