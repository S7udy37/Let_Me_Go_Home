// Gold V - 2636 : 치즈

/*
 * BFS
 * 15,548 kb, 116 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_2636_치즈_박진 {

	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[][] map;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static Queue<Point> airQueue = new LinkedList<Point>();	// 공기를 파악하기 위해 BFS를 사용할 큐
	static boolean[][] visit;	// BFS를 위한 방문체크
	static Queue<Point> cheeseQueue = new LinkedList<Point>();	// 없어질 치즈 위치를 저장

	static int resultTime = 0;	// 치즈가 모두 녹아서 없어지는 데 걸리는 시간
	static int resultCnt = 0;	// 모두 녹기 한 시간 전에 남아있는 치즈조각이 놓여 있는 칸의 개수
	
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
				if (i == 0 || i == N - 1 || j == 0 || j == M - 1) {	 // 판의 가장자리는 -1로 설정
					map[i][j] = -1;
				}
			}
		}
		
		int flag = cntCheese();	// 남은 치즈가 있으면 양수, 없으면 0
		while(flag > 0) {
			visit = new boolean[N][M];
			resultTime++;
			resultCnt = cntCheese();
			
			// 공기칸과 공기가 없는 칸 구분
			whereIsAir();
			
			// 없어질 치즈 체크
			checkCheease();
			
			// 치즈 제거
			removeCheese();
			
			// 남은 치즈가 있는지 체크
			flag = cntCheese();
		}
		
		System.out.println(resultTime);
		System.out.println(resultCnt);
		
		
	}// end of main
	
	// 치즈가 없는 칸(0)들 중에서 공기인 부분들을 -1로 바꿔주는 메소드
	public static void whereIsAir() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == -1) {
					airQueue.offer(new Point(i,j));
				}
			}
		}
		
		while(!airQueue.isEmpty()) {
			Point point = airQueue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nexti = point.r + di[d];
				int nextj = point.c + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
					continue;
				if (visit[nexti][nextj])
					continue;
				
				if (map[nexti][nextj] == 0) {
					map[nexti][nextj] = -1;
					visit[nexti][nextj] = true;
					airQueue.offer(new Point(nexti, nextj));
				}
			}
		}
	}
	
	// 공기와 접촉한 치즈를 찾아 큐에 넣는 메소드
	private static void checkCheease() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					for (int d = 0; d < 4; d++) {
						int nexti = i + di[d];
						int nextj = j + dj[d];
						
						if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
							continue;
						
						if (map[nexti][nextj] == -1) {	// 주변에 공기(-1)이 있으면 없어질 치즈이므로 큐에 추가.
							cheeseQueue.offer(new Point(i, j));
							break;
						}
					}
				}
			}
		}
	}

	// 치즈 제거
	private static void removeCheese() {
		while(!cheeseQueue.isEmpty()) {
			Point point = cheeseQueue.poll();
			map[point.r][point.c] = -1;
		}
	}

	// 남아있는 치즈의 개수 카운팅
	public static int cntCheese() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1)
					cnt++;
			}
		}
		return cnt;
	}
}
