// Gold V - 15683 : 감시

/*
69,460 kb
344 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_15683_감시_박진 {

	static class Point {
		int i, j;
		int cctv;
		
		public Point(int i, int j, int cctv) {
			this.i = i;
			this.j = j;
			this.cctv = cctv;
		}
	}
	
	static int N, M; 		// 사무실의 세로 크기 N과 가로 크기 M
	static int[][] map;		// 사무실의 상태를 저장할 배열 (-1: 감시지역, 0: 빈 칸, 6: 벽, 1~5: CCTV)
	static int result;
	static Queue<Point> queue = new LinkedList<Point>();
	static ArrayList<Point> arrList = new ArrayList<Point>();
	
	// 상, 하, 좌, 우
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 5) {
					arrList.add(new Point(i, j, map[i][j]));
				}
			}
		}
		
		result = Integer.MAX_VALUE;
		dfs(0, map);
		
		System.out.print(result);
	}// end main
	
	private static void dfs(int cnt, int[][] map) {
		if (cnt == arrList.size()) {
			int tempResult = countingArea(map);
			if (result > tempResult)
				result = tempResult;
			return;
		}
		int[][] tempMap = new int[N][M];
		switch (arrList.get(cnt).cctv) {
		case 1:
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv1(0, arrList.get(cnt), tempMap));
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv1(1, arrList.get(cnt), tempMap));
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv1(2, arrList.get(cnt), tempMap));
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv1(3, arrList.get(cnt), tempMap));
			break;
		case 2:
			// 좌,우
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv2And3(0, 1, arrList.get(cnt), tempMap));
			// 상,하
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv2And3(2, 3, arrList.get(cnt), tempMap));
			break;
		case 3:
			// 상,우(0,3)
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv2And3(1, 2, arrList.get(cnt), tempMap));
			// 하,우(1,3)
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv2And3(0, 2, arrList.get(cnt), tempMap));
			// 상,좌(0,2)
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv2And3(1, 3, arrList.get(cnt), tempMap));
			// 하,좌(1,2)
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv2And3(0, 3, arrList.get(cnt), tempMap));
			break;
		case 4:
			// 좌,상,우 (2,0,3)
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv4(1, arrList.get(cnt), tempMap));
			// 상,우,하 (0,3,1)
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv4(2, arrList.get(cnt), tempMap));
			// 좌,하,우 (2,1,3)
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv4(0, arrList.get(cnt), tempMap));
			// 상,좌,하 (0,2,1)
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv4(3, arrList.get(cnt), tempMap));
			break;
		case 5:
			tempMap = copyMap(map);
			dfs(cnt + 1, cctv5(arrList.get(cnt), tempMap));
			break;
		}
	}
	
	private static int[][] cctv1(int d1, Point point, int[][] m) {
		int nexti = point.i;
		int nextj = point.j;
		while(true) {
			nexti = nexti + di[d1];
			nextj = nextj + dj[d1];
			
			if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
				break;
			if (m[nexti][nextj] == 6)
				break;
			
			m[nexti][nextj] = -1;
		}
		return m;
	}
	
	private static int[][] cctv2And3(int d1, int d2, Point point, int[][] m) {
		for (int d = 0; d < 4; d++) {
			if (d == d1 || d == d2)
				continue;
			
			int nexti = point.i;
			int nextj = point.j;
			while(true) {
				nexti = nexti + di[d];
				nextj = nextj + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
					break;
				if (m[nexti][nextj] == 6)
					break;
				
				m[nexti][nextj] = -1;
			}
		}
		return m;
	}
	
	private static int[][] cctv4(int d1, Point point, int[][] m) {
		for (int d = 0; d < 4; d++) {
			if (d == d1)
				continue;
			
			int nexti = point.i;
			int nextj = point.j;
			while(true) {
				nexti = nexti + di[d];
				nextj = nextj + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
					break;
				if (m[nexti][nextj] == 6)
					break;
				
				m[nexti][nextj] = -1;
			}
		}
		return m;
	}
	
	private static int[][] cctv5(Point point, int[][] m) {
		for (int d = 0; d < 4; d++) {
			int nexti = point.i;
			int nextj = point.j;
			while(true) {
				nexti = nexti + di[d];
				nextj = nextj + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
					break;
				if (m[nexti][nextj] == 6)
					break;
				
				m[nexti][nextj] = -1;
			}
		}
		return m;
	}

	public static int[][] copyMap(int[][] m) {
		int[][] tempMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempMap[i][j] = m[i][j];
			}
		}
		return tempMap;
	}
	
	public static int countingArea(int[][] m) {
		int tempCnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (m[i][j] == 0)
					tempCnt++;
			}
		}
		return tempCnt;
	}
	
}// end class
