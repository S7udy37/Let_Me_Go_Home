// Gold V - 14499 : 주사위 굴리기

/*
 * 시뮬레이션
 * 13480 kb, 84 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_14499_주사위굴리기_박진 {

	static int N, M;	// 지도의 세로 크기 N, 가로 크기 M (1 ≤ N, M ≤ 20)
	static int x, y;	// 주사위를 놓은 곳의 좌표 x y(0 ≤ x ≤ N-1, 0 ≤ y ≤ M-1)
	static int K;	// 명령의 개수 K (1 ≤ K ≤ 1,000)
	static int[][] map;	// 지도
	static int[] order;	// 명령 (동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4)
	
	static int[] dice = new int[7];	// (주사위의 위치 1 ~ 6에 적혀있는 숫자 기록)
	static int bottom;	// 현재 지도와 접해있는 주사위의 바닥 면에 적힌 숫자
	
	// dummy, 동, 서, 북, 남
	static int[] di = {0, 0, 0, -1, 1};
	static int[] dj = {0, 1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[K];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {
			order[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int k = 0; k < K; k++) {	// 명령 K번 수행
			int nexti = x + di[order[k]];
			int nextj = y + dj[order[k]];
			
			if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
				continue;
			
			// 주사위 현재 위치 갱신
			x = nexti;
			y = nextj;
			
			// 주사위 bottom에 적힌 숫자 찾아내기
			int temp;
			switch (order[k]) {
			case 1:	// 동
				temp = dice[3];
				dice[3] = dice[1];
				dice[1] = dice[4];
				dice[4] = dice[6];
				dice[6] = temp;
				break;
			case 2:	// 서
				temp = dice[4];
				dice[4] = dice[1];
				dice[1] = dice[3];
				dice[3] = dice[6];
				dice[6] = temp;
				break;
			case 3:	// 북
				temp = dice[2];
				dice[2] = dice[1];
				dice[1] = dice[5];
				dice[5] = dice[6];
				dice[6] = temp;
				break;
			case 4:	// 남
				temp = dice[5];
				dice[5] = dice[1];
				dice[1] = dice[2];
				dice[2] = dice[6];
				dice[6] = temp;
				break;
			}
			bottom = dice[6];
			
			// 주사위와 맵 갱신
			if (map[nexti][nextj] == 0) {	// 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 칸에 복사된다.
				map[nexti][nextj] = bottom;
			} else {	// 이동한 칸에 쓰여 있는 수가 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위의 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.
				dice[6] = map[nexti][nextj];
				map[nexti][nextj] = 0;
			}
			
			// 윗 면에 쓰여 있는 수
			sb.append(dice[1]).append("\n");
		}
		
		System.out.println(sb);
	}// end of main

}
