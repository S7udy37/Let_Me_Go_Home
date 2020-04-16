// Gold IV - 2206 : 벽 부수고 이동하기

/*
 * BFS
 * 7%에서 시간초과
 */

import java.util.*;
import java.io.*;

public class Main_B_2206_벽부수고이동하기_박진_BFS_TLE {
	
	static class Point {
		int r, c;
		int cnt;
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int N, M;
	static boolean[][] map;
	static boolean[][] visit;	//  방문 확인
	static Queue<Point> queue = new LinkedList<Point>();	// BFS를 위한 큐
	static ArrayList<Point> wall = new ArrayList<Point>();	// 벽의 위치들을 저장.
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
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				if (str.charAt(j) == '1') {
					map[i][j] = true;
					wall.add(new Point(i, j, 0));
				}
			}
		}
		
		// 1. 벽을 부수지 않았을 때
		bfs(new Point(0, 0, 1));	// BFS
		
		// 2. 벽 한 개를 부쉈을 때
		int size = wall.size();
		for (int i = 0; i < size; i++) {
			initVisit();	// visit 초기화
			map[wall.get(i).r][wall.get(i).c] = false;	// 벽 부수기
			bfs(new Point(0, 0, 1));					// BFS
			map[wall.get(i).r][wall.get(i).c] = true;	// 벽 원상복구
		}
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}// end main
	
	public static void bfs(Point point) {
		queue.clear();
		queue.offer(point);
		visit[point.r][point.c] = true;
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			if (result <= current.cnt) {	// 가지치기
				break;
			}

			if (current.r == N - 1 && current.c == M - 1) {	// 기저조건
				if (result > current.cnt)
					result = current.cnt;
				break;
			}
			
			for (int d = 0; d < 4; d++) {
				int nexti = current.r + di[d];
				int nextj = current.c + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)	// 범위 체크
					continue;
				if (visit[nexti][nextj])	// 방문 체크
					continue;
				if (map[nexti][nextj])	// 벽 체크
					continue;
				
				visit[nexti][nextj] = true;
				queue.offer(new Point(nexti, nextj, current.cnt + 1));
			}
		}
	}
	
	// visit 초기화
	public static void initVisit() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visit[i][j] = false;
			}
		}
	}

}
