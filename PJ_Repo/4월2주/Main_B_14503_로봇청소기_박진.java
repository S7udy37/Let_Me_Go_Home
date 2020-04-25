// Gold V - 14503 : 로봇 청소기

/*
 * 13,260 kb, 84 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_14503_로봇청소기_박진 {
	
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	
	// 북, 동, 남, 서
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	static int result = 0;	// 로봇 청소기가 청소하는 칸의 개수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		st = new StringTokenizer(br.readLine(), " ");
		int r = Integer.parseInt(st.nextToken());	// 로봇의 위치 (행)
		int c = Integer.parseInt(st.nextToken());	// 로봇의 위치 (열)
		int d = Integer.parseInt(st.nextToken());	// 로봇이 바라보고 있는 방향 d. (d가 0인 경우에는 북쪽을, 1인 경우에는 동쪽을, 2인 경우에는 남쪽을, 3인 경우에는 서쪽을 바라보고 있는 것)
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}// end input
		
		int tempd = d;	// 로봇이 탐색할 방향
	W1:	while(true) {
//			1. 현재 위치를 청소한다.
			visit[r][c] = true;
			result++;
			tempd = d;
			
		W2:	while(true) {
//				2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다.
				tempd--;
				if (tempd < 0)
					tempd = 3;
				
				int nexti = r + di[tempd];
				int nextj = c + dj[tempd];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
					continue W2;
				
//				a. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
				if (map[nexti][nextj] == 0 && !visit[nexti][nextj]) {
					r = nexti;
					c = nextj;
					visit[r][c] = true;
					d = tempd;
					continue W1;
				}
//				c. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
				if (d == tempd) {
					nexti = r - di[d];
					nextj = c - dj[d];
					
//					d. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
					if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
						break W1;
					if (map[nexti][nextj] == 1)
						break W1;
					
					r = nexti;
					c = nextj;
					continue;
				}
//				b. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
				if (map[nexti][nextj] == 1 || visit[nexti][nextj]) {
					continue W2;
				}
			}//end of W2
		}// end of W1
		System.out.println(result);
	}// end of main
}
