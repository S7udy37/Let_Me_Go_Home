// Gold IV - 2206 : 벽 부수고 이동하기

/*
 * DFS + 가지치기
 * 7%에서 시간초과
 */

import java.util.*;
import java.io.*;

public class Main_B_2206_벽부수고이동하기_박진_DFS_TLE2 {

	static int N, M;
	static boolean[][] map;
	static boolean[][] visit;	// dfs 방문 확인
	static boolean isBroke;		// 벽돌 한개를 부쉈는지 안부쉈는지 확인
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
				}
//				System.out.print(map[i][j] + " ");
			}
//			System.out.println();
		}
		
		dfs(0, 0, 1);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}// end main
	
	public static void dfs(int row, int col, int cnt) {
		if (cnt >= result)	// 가지치기
			return;
		
		if (row == N - 1 && col == M - 1) {	// 기저조건
			if (result > cnt)
				result = cnt;
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int nexti = row + di[d];
			int nextj = col + dj[d];
			
			if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
				continue;
			if (visit[nexti][nextj])
				continue;
			
			if (map[nexti][nextj]) {	// 벽을 만났을 때
				if (isBroke) {	// 이미 벽을 부쉈었다면, 못지나감.
					continue;
				} else {	// 아직 벽을 부순적이 없다면, 벽을 부수고 지나감.
					isBroke = true;
					visit[nexti][nextj] = true;
					dfs(nexti, nextj, cnt + 1);
					isBroke = false;
					visit[nexti][nextj] = false;
				}
			} else {	// 이동할 수 있는 곳일 때
				visit[nexti][nextj] = true;
				dfs(nexti, nextj, cnt + 1);
				visit[nexti][nextj] = false;
			}
			
		}
	}

}
