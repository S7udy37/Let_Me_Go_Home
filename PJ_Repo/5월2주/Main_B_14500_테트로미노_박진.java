// Gold IV - 14500 : 테트로미노

/*
 * 33,008 kb, 436 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_14500_테트로미노_박진 {

	static int N, M;	// 종이의 세로 크기 N과 가로 크기 M  (4 ≤ N, M ≤ 500)
	static int map[][];	// 종이
	static int result = 0;	// 테트로미노가 놓인 칸에 쓰여 있는 수들의 합의 최대값
	
	static int[][][] dir = {
			{ { 0, 1 }, { 0, 2 }, { 0, 3 } },
			{ { 1, 0 }, { 2, 0 }, { 3, 0 } },
			
			{ { 0, 1 }, { 1, 0 }, { 1, 1 } },
			
			{ { 1, 0 }, { 2, 0 }, { 2, 1 } },
			{ { 1, 0 }, { 2, 0 }, { 2, -1 } },
			{ { 0, 1 }, { 1, 0 }, { 2, 0 } },
			{ { 0, 1 }, { 1, 1 }, { 2, 1 } },
			{ { 1, 0 }, { 0, 1 }, { 0, 2 } },
			{ { 0, 1 }, { 0, 2 }, { 1, 2 } },
			{ { 1, 0 }, { 1, 1 }, { 1, 2 } },
			{ { 1, 0 }, { 1, -1 }, { 1, -2 } },
			
			{ { 1, 0 }, { 1, 1 }, { 2, 1 } },
			{ { 1, 0 }, { 1, -1 }, { 2, -1 } },
			{ { 0, 1 }, { 1, 0 }, { 1, -1 } },
			{ { 0, 1 }, { 1, 1 }, { 1, 2 } },
			
			{ { 1, 0 }, { 1, -1 }, { 1, 1 } },
			{ { 0, 1 }, { 0, 2 }, { 1, 1 } },
			{ { 1, 0 }, { 2, 0 }, { 1, 1 } },
			{ { 1, 0 }, { 2, 0 }, { 1, -1 } }
	};
	
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
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 테트로미노에 있는 수들의 합 구하기
				for (int k = 0; k < 19; k++) {
					go(i, j, dir[k]);
				}
			}
		}
		
		System.out.println(result);
	}// end of main

	private static void go(int i, int j, int[][] dir) {
		int temp = map[i][j];
		
		for (int d = 0; d < 3; d++) {
			int nexti = i + dir[d][0];
			int nextj = j + dir[d][1];
			
			if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M) {
				return;
			}
			
			temp += map[nexti][nextj];
		}
		
		// 최대값 갱신
		result = result < temp ? temp : result;
		
		return;
	}
}
