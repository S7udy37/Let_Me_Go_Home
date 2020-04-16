// Gold IV - 2206 : 벽 부수고 이동하기

/*
 * BFS
 * 157,444 kb
 * 584 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_2206_벽부수고이동하기_박진 {
	
	static class Point {
		int r, c;
		int cnt;
		boolean isBroke;	// 벽을 부수고 온건지(true), 안부수고 온건지(false)의 여부
		public Point(int r, int c, int cnt, boolean isBroke) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.isBroke = isBroke;
		}
	}

	static int N, M;
	static boolean[][] map;
	static boolean[][][] visit;	//  방문 확인
	static Queue<Point> queue = new LinkedList<Point>();	// BFS를 위한 큐
	static int result = Integer.MAX_VALUE;
	
	// 우, 하, 좌, 상
	static int[] di = {0, 1, 0, -1};
	static int[] dj = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		visit = new boolean[N][M][2];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				if (str.charAt(j) == '1') {
					map[i][j] = true;
				}
			}
		}
		
		bfs(new Point(0, 0, 1, false));
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}// end main
	
	public static void bfs(Point point) {
		queue.offer(point);
		visit[point.r][point.c][0] = true;
		visit[point.r][point.c][1] = true;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();

			if (current.r == N - 1 && current.c == M - 1) {	// 기저조건
				result = current.cnt;	// 가장 먼저 도착한게 어짜피 답임.
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nexti = current.r + di[d];
				int nextj = current.c + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)	// 범위 체크
					continue;
				if (!current.isBroke && visit[nexti][nextj][0])	// 방문 체크 (벽을 안부수고 왔을 때)
					continue;
				if (current.isBroke && visit[nexti][nextj][1])	// 방문 체크 (벽을 부수고 왔을 때)
					continue;
				
				if (map[nexti][nextj]) {	// 벽을 만났을 때,
					if (current.isBroke) {	// 이미 벽을 만나고 왔다면 continue.
						continue;
					} else {	// 아직 벽을 안만났었다면 벽을 부수고 진행.
						visit[nexti][nextj][1] = true;
						queue.offer(new Point(nexti, nextj, current.cnt + 1, true));
					}
				} else {	// 이동할 수 있는 공간을 만났고,
					if (current.isBroke) {	// 이미 벽을 만나고 왔을 때.
						visit[nexti][nextj][1] = true;
						queue.offer(new Point(nexti, nextj, current.cnt + 1, true));
					} else {	// 아직 벽을 안만났을 때.
						visit[nexti][nextj][0] = true;
						queue.offer(new Point(nexti, nextj, current.cnt + 1, false));
					}
				}
			}
		}
	}

}
