// Gold IV - 1726 : 로봇

/*
 * 틀렸습니다. --> 다시 해보기
 */

import java.io.*;
import java.util.*;

public class Main_B_1726_로봇_박진_ING {
	
	static class Point {
		int i, j;
		int cnt;
		int dir;
		public Point(int i, int j, int cnt, int dir) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.dir = dir;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", cnt=" + cnt + ", dir=" + dir + "]";
		}
	}
	
	static int M, N;	// 세로 길이 M과 가로 길이 N
	static int[][] map;
	static int startR, startC, startD;	// 로봇의 출발 지점의 위치 (행과 열의 번호), 바라보는 방향
	static int endR, endC, endD;	// 로봇의 도착 지점의 위치 (행과 열의 번호), 바라보는 방향
	static boolean[][][] visit;
	static Queue<Point> q = new LinkedList<>();
	static int result = Integer.MAX_VALUE;
	
	// 방향은 동쪽이 1, 서쪽이 2, 남쪽이 3, 북쪽이 4
	static int[] di = {0, 0, 0, 1, -1};
	static int[] dj = {0, 1, -1, 0, 0};
	
	/*
	 * 명령 1. Go k: k는 1, 2 또는 3일 수 있다. 현재 향하고 있는 방향으로 k칸 만큼 움직인다.
	 * 명령 2. Turn dir: dir은 left 또는 right 이며, 각각 왼쪽 또는 오른쪽으로 90° 회전한다.
	 */
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M+1][N+1];
		visit = new boolean[M+1][N+1][5];
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		startR = Integer.parseInt(st.nextToken());
		startC = Integer.parseInt(st.nextToken());
		startD = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		endR = Integer.parseInt(st.nextToken());
		endC = Integer.parseInt(st.nextToken());
		endD = Integer.parseInt(st.nextToken());
		
		bfs(startR, startC, startD);
		
		System.out.println(result);
	}// end of main

	private static void bfs(int row, int col, int dir) {
		q.offer(new Point(row, col, 0, dir));
		visit[row][col][dir] = true;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			if (cur.i == endR && cur.j == endC) {
				int temp;
				if (cur.dir == endD) {
					temp = cur.cnt;
				} else {
					temp = cur.cnt + 1;
					if ((cur.dir == 1 && endD == 2) || (cur.dir == 2 && endD == 1) || (cur.dir == 3 && endD == 4) || (cur.dir == 4 && endD == 3)) {	// 180도 회전일 경우
						temp = cur.cnt + 2;
					}
				}
				result = result > temp ? temp : result;
			}
			
			for (int d = 1; d <= 4; d++) {
				int nextCnt = cur.cnt + 1;
				if(cur.dir != d) {	// 방향이 다르면 방향회전에 필요한 횟수만큼 늘려줌.
					nextCnt += 1;	// 90도 회전일 경우
					if ((cur.dir == 1 && d == 2) || (cur.dir == 2 && d == 1) || (cur.dir == 3 && d == 4) || (cur.dir == 4 && d == 3)) {	// 180도 회전일 경우
						nextCnt += 1;
					}
				}
				for (int l = 1; l <= 3; l++) {
					int nexti = cur.i + di[d] * l;
					int nextj = cur.j + dj[d] * l;
					if (nexti < 1 || nextj < 1 || nexti > M || nextj > N)	// 범위 체크
						break;
					if (visit[nexti][nextj][d])	// 방문 체크
						continue;
					if (map[nexti][nextj] == 1)	// 궤도 체크
						break;
					visit[nexti][nextj][d] = true;
					q.offer(new Point(nexti, nextj, nextCnt, d));
				}
			}
		}
	}

}
