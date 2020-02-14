// 메모리 : 35,976 kb
// 실행시간 : 173 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static int[][] map = new int[100][100];
	static int[][] map2 = new int[100][100];
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int N;
	static int ans;
	static Queue<Point> q;
	static int cnt = 0;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			ans = -1;
			N = Integer.parseInt(br.readLine());
			for(int i=0; i<N; i++) {
				Arrays.fill(map2[i], -1);
			}
			for (int i = 0; i < N; i++) {
				String st = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = st.charAt(j) - '0';
				}
			}

			q = new LinkedList<Point>();
			q.add(new Point(0, 0));
			map2[0][0] = map[0][0];
			while (!q.isEmpty()) {
				int x = q.peek().x;
				int y = q.peek().y;
				q.poll();
				for (int k = 0; k < 4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];
					if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
							if(map2[nx][ny] == -1 || map2[nx][ny] > map2[x][y] + map[nx][ny]) {
								q.add(new Point(nx, ny));
								map2[nx][ny] = map2[x][y] + map[nx][ny];
							}
					}
				}
			}
			System.out.println("#" + t + " " + map2[N-1][N-1]);
		}
	}
}
class Point {
	int x;
	int y;
	public Point(int x, int y) {
		this.x = x; this.y = y;
	}
}
