package study_0217;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_1249_보급로 {
	static int[][] map;
	static int n;
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };
	static int[][] mapcnt;
	static Queue<int[]> q = new LinkedList<int[]>();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("보급로.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(in.readLine());
		StringTokenizer st = null;

		for (int t = 1; t <= tc; t++) {
			n = Integer.parseInt(in.readLine());

			map = new int[n][n];
			mapcnt = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(mapcnt[i], -1); //방문 체크
			}
			for (int i = 0; i < n; i++) {
				String line = in.readLine();
				for (int j = 0; j < n; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			mapcnt[0][0] = 0; //시작점은 0으로 해줌
			q.offer(new int[] {0, 0}); //0,0부터 시작
			while (!q.isEmpty()) { //방문 안한곳이 없을때.
				int[] now = q.poll();
				pathfinder(now[0], now[1]);
			}
			System.out.println("#" + t + " " + mapcnt[n - 1][n - 1]);
		}
	}

	private static void pathfinder(int y, int x) {

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (ny >= 0 && nx >= 0 && ny < n && nx < n) {
				if (mapcnt[ny][nx] == -1) { //방문 안한곳일때는 이전방문점+현재점의 카운트를 더함
					mapcnt[ny][nx] = mapcnt[y][x] + map[ny][nx];
					q.offer(new int[] { ny, nx }); //다음에 방문할곳 예약
				}//방문 한곳이지만 내가 가는법이 더 빠르면 교체
				if (mapcnt[ny][nx] > map[ny][nx] + mapcnt[y][x]) {
					mapcnt[ny][nx] = map[ny][nx] + mapcnt[y][x];
					q.offer(new int[] { ny, nx });
				}
			}
		}
	}
}
