// Gold V - 1600 : 말이 되고픈 원숭이

/*
 * BFS
 * 90,860 kb, 472 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_1600_말이되고픈원숭이_BFS {

	static class Point {
		int i, j;
		int cnt;	// 동작 수 카운팅
		int remain;	// 남은 K 횟수
		public Point(int i, int j, int cnt, int remain) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.remain = remain;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", cnt=" + cnt + ", remain=" + remain + "]";
		}
	}
	
	static int K;	// 말처럼 움직일 수 있는 횟수 (0이상 30이하)
	static int W, H;	// 가로길이 W, 세로길이 H (1이상 200이하)
	static int[][] map;	// 0은 아무것도 없는 평지, 1은 장애물
	static boolean[][][] visit;	// 말처럼 이동시 방문체크
	static Queue<Point> q = new LinkedList<>();
	static int result = Integer.MAX_VALUE;
	
	// 원숭이
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	// 말
	static int[] hdi = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] hdj = {-2, -1, 1, 2, 2, 1, -1, -2};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		visit = new boolean[H][W][K+1];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// BFS
		q.offer(new Point(0, 0, 0, K));
		visit[0][0][K] = true;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if (cur.i == H - 1 && cur.j == W - 1) {
				result = cur.cnt;
				break;
			}
			
			if (cur.remain > 0) {
				for (int d = 0; d < 8; d++) {
					int nexti = cur.i + hdi[d];
					int nextj = cur.j + hdj[d];
					if (nexti < 0 || nextj < 0 || nexti >= H || nextj >= W || visit[nexti][nextj][cur.remain - 1] || map[nexti][nextj] == 1)
						continue;
					visit[nexti][nextj][cur.remain - 1] = true;
					q.offer(new Point(nexti, nextj, cur.cnt + 1, cur.remain - 1));
				}
			}
			
			for (int d = 0; d < 4; d++) {
				int nexti = cur.i + di[d];
				int nextj = cur.j + dj[d];
				if (nexti < 0 || nextj < 0 || nexti >= H || nextj >= W || visit[nexti][nextj][cur.remain] || map[nexti][nextj] == 1)
					continue;
				visit[nexti][nextj][cur.remain] = true;
				q.offer(new Point(nexti, nextj, cur.cnt + 1, cur.remain));
			}
		}
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}// end of main

}
