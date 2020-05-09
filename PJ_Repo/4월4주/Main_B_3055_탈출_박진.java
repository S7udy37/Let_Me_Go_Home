// Gold V - 3055 : 탈출

/*
 * 시뮬레이션 (BFS)
 * 13,216 kb, 76 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_3055_탈출_박진 {
	
	static class Point {
		int i, j;
		int cnt;
		public Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
	
	static int R, C;
	// 비어있는 곳은 '.', 물이 차있는 지역은 '*', 돌은 'X'
	// 비버의 굴은 'D', 고슴도치의 위치는 'S'
	static char[][] map;
	static int result = Integer.MAX_VALUE;
	
	static Queue<Point> dochiQueue = new LinkedList<>();	// 고슴도치 이동을 위한 큐
	static Queue<Point> waterQueue = new LinkedList<>();	// 물 확장을 위한 큐
	static boolean[][] dochiVisit;
	static boolean[][] waterVisit;
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		dochiVisit = new boolean[R][C];
		waterVisit = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'S') {
					dochiQueue.offer(new Point(i, j, 0));
					map[i][j] = '.';
				} else if (map[i][j] == '*') {
					waterQueue.offer(new Point(i, j, 0));
					waterVisit[i][j] = true;
				}
			}
		}
		
	L:	while(dochiQueue.size() > 0) {	// 고슴도치가 이동 불가능할 때 까지 반복
			// 물 확장
			int waterSize = waterQueue.size();
			while(waterSize-- > 0) {
				Point cur = waterQueue.poll();
				for (int d = 0; d < 4; d++) {
					int nexti = cur.i + di[d];
					int nextj = cur.j + dj[d];
					if (nexti < 0 || nextj < 0 || nexti >= R || nextj >= C || waterVisit[nexti][nextj] || map[nexti][nextj] == 'X')
						continue;
					if (map[nexti][nextj] == 'D')	// 물은 비버의 소굴로 이동할 수 없다.
						continue;
					waterVisit[nexti][nextj] = true;
					waterQueue.offer(new Point(nexti, nextj, 0));
					map[nexti][nextj] = '*';
				}
			}
			// 고슴도치 이동
			int dochiSize = dochiQueue.size();
			while(dochiSize-- > 0) {
				Point cur = dochiQueue.poll();
				for (int d = 0; d < 4; d++) {
					int nexti = cur.i + di[d];
					int nextj = cur.j + dj[d];
					if (nexti < 0 || nextj < 0 || nexti >= R || nextj >= C || dochiVisit[nexti][nextj] || map[nexti][nextj] == 'X')
						continue;
					if (map[nexti][nextj] == '*')	// 고슴도치는 물로 차있는 구역으로 이동할 수 없다.
						continue;
					if (map[nexti][nextj] == 'D') {	// 비버소굴 발견
						result = cur.cnt + 1;
						break L;
					}
					dochiVisit[nexti][nextj] = true;
					dochiQueue.offer(new Point(nexti, nextj, cur.cnt + 1));
				}
			}
			
		}
		
		System.out.println(result == Integer.MAX_VALUE ? "KAKTUS" : result);
	}// end of main

}
