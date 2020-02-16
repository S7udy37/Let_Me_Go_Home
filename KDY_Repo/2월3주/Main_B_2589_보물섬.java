package study_0217;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_2589_보물섬 {
	static int map_y;
	static int map_x;
	static char[][] map;
	static boolean[][] visit;
	static int[][] mapcnt;
	static Queue<int[]> q = new LinkedList<int[]>();
	static int cnt;
	static int max;
	static int[] dy = { 0, -1, 0, 1 };
	static int[] dx = { 1, 0, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());

		map_y = Integer.parseInt(st.nextToken());
		map_x = Integer.parseInt(st.nextToken());
		map = new char[map_y][map_x];
		max = 0;

		for (int i = 0; i < map_y; i++) {
			String line = in.readLine().trim();
			for (int j = 0; j < map_x; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		max = 0;
		for (int i = 0; i < map_y; i++) {
			for (int j = 0; j < map_x; j++) {
				if (map[i][j] == 'L') {
					cnt = 0;
					visit = new boolean[map_y][map_x];
					mapcnt = new int[map_y][map_x];
					q.offer(new int[] { i, j });
					finder();
				}
			}
		}
		System.out.println(max);
	}

	private static void finder() {
		while (!q.isEmpty()) {
			int[] l_point = q.poll();
			int y = l_point[0];
			int x = l_point[1];

			for (int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];

				if (ny >= 0 && nx >= 0 && ny < map_y && nx < map_x) {
					if (map[ny][nx] == 'L' && visit[ny][nx] == false) {
						visit[ny][nx] = true;
						q.offer(new int[] { ny, nx });
						mapcnt[ny][nx] = mapcnt[y][x] + 1;

						cnt = cnt < mapcnt[ny][nx] ? mapcnt[ny][nx] : cnt;
					}
				}
			}
		}
		max = max < cnt ? cnt : max;
	}
}