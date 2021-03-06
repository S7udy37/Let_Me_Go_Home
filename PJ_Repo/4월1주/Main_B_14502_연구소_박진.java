// Gold V - 14502 : 연구소

/*
122,040 kb
436 ms
*/

import java.util.*;
import java.io.*;

public class Main_B_14502_연구소_박진_수정 {

	static class Point {
		int r, c;
		public Point (int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int[][] map2;
	
	static int result = 0;	// 안전 영역의 최대 크기
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static ArrayList<Point> virus = new ArrayList<Point>();	// 바이러스들의 위치
	static Queue<Point> queue = new LinkedList<Point>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		map2 = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new Point(i, j));
				}
			}
		}
		
		combination(0, 0);
		
		System.out.println(result);
	}// end main
	
	public static void bfs(Point point) {
		queue.offer(point);
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nexti = cur.r + di[d];
				int nextj = cur.c + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
					continue;
				if (map2[nexti][nextj] > 0)
					continue;
				
				map2[nexti][nextj] = 2;
				queue.offer(new Point(nexti, nextj));
			}
		}
	}
	
	public static void combination(int index, int cnt) {
		if (cnt == 3) {
			for (int i = 0; i < N; i++) {	// copy map
				for (int j = 0; j < M; j++) {
					map2[i][j] = map[i][j];
				}
			}
			
			for (Point point : virus) {
				bfs(point);
			}
			
			int temp = countZero();
			if (result < temp) {
				result = temp;
			}
			return;
		}
		
		for (int i = index; i < N * M; i++) {
			int row = i / M;
			int col = i % M;
			
			if (map[row][col] == 0) {
				map[row][col] = 1;
				combination(i + 1, cnt + 1);
				map[row][col] = 0;
			}
		}
	}
	
	public static int countZero() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map2[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
}
