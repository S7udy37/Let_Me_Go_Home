// D4 - 1227 : [S/W 문제해결 기본] 7일차 - 미로2

/*
 * BFS
 * 34,244 kb, 123 ms
 */

import java.util.*;
import java.io.*;

public class Solution_D4_1227_미로2_박진 {

	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static char[][] map;
	static Queue<Point> queue = new LinkedList<Point>();
	static boolean[][] visit;
	static int result;	// 도달 가능 여부 (1 - 가능함, 0 - 가능하지 않음)
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			map = new char[100][100];
			visit = new boolean[100][100];
			for (int i = 0; i < 100; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			result = 0;
			bfs(new Point(1, 1));
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

	private static void bfs(Point point) {
		queue.offer(point);
		visit[point.i][point.j] = true; 
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nexti = cur.i + di[d];
				int nextj = cur.j + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= 100 || nextj >= 100 || visit[nexti][nextj])
					continue;
				if (map[nexti][nextj] == '1')
					continue;
				
				if (map[nexti][nextj] == '3') {
					result = 1;
					queue.clear();
					return;
				}
				
				visit[nexti][nextj] = true;
				queue.offer(new Point(nexti, nextj));
			}
		}
	}

}
