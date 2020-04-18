// Gold IV - 5427 : 불

/*
 * BFS
 * 117,028 kb, 852 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_5427_불_박진 {
/*
'.': 빈 공간
'#': 벽
'@': 상근이의 시작 위치
'*': 불
 */
	static public class Point {
		int i, j;
		int cnt;
		public Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
	
	static int T;
	static int w, h;
	static char[][] map;
	static boolean[][] visit;
	static int result;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static Queue<Point> fireQueue;	// 불 번지는 메소드를 위한 큐
	static Queue<Point> sg;	// 상근이의 이동을 위한 큐
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			fireQueue = new LinkedList<Point>();
			sg = new LinkedList<Point>();
			result = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			visit = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '*') {
						fireQueue.offer(new Point(i, j, 0));
					}
					if (map[i][j] == '@') {
						sg.offer(new Point(i, j, 1));	// 탈출하는데 무조건 1번은 걸리므로 1로 시작.
						map[i][j] = '.';
					}
				}
			}// end of input
			
		L:	while(!sg.isEmpty()) {
				fire();	// 불 번짐
				int size = sg.size();
				while(size-- > 0) {	// 상근이 이동
					Point cur = sg.poll();
					
					if (cur.i == 0 || cur.i == h - 1 || cur.j == 0 || cur.j == w - 1) {	// 탈출
						result = cur.cnt;
						break L;
					}
					
					for (int d = 0; d < 4; d++) {
						int nexti = cur.i + di[d];
						int nextj = cur.j + dj[d];
						
						if (nexti < 0 || nextj < 0 || nexti >= h || nextj >= w)
							continue;
						if (visit[nexti][nextj])
							continue;
						if (map[nexti][nextj] != '.')	// 빈공간이 아니면 무시.
							continue;
						
						visit[nexti][nextj] = true;
						sg.offer(new Point(nexti, nextj, cur.cnt + 1));
					}
				}
			}
			
			sb.append(result == Integer.MAX_VALUE ? "IMPOSSIBLE" : result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

	// 불이 번지는 메소드
	public static void fire() {
		int size = fireQueue.size();
		for (int i = 0; i < size; i++) {
			Point cur = fireQueue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nexti = cur.i + di[d];
				int nextj = cur.j + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= h || nextj >= w)
					continue;
				if (map[nexti][nextj] != '.')	// 빈공간이 아니면 무시.
					continue;
				
				map[nexti][nextj] = '*'; 
				fireQueue.offer(new Point(nexti, nextj, 0));
			}
		}
	}
}
