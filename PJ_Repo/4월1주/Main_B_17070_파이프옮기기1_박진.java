// Gold V - 17070 : 파이프 옮기기 1

/*
 * DFS
 * 169,652 kb
 * 392 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_17070_파이프옮기기1_박진 {

	static class Pipe {
		int taili, tailj;
		int type;	// 파이프가 놓여있는 방향 (0:가로, 1:대각선, 2:세로)
		public Pipe(int taili, int tailj, int type) {
			this.taili = taili;
			this.tailj = tailj;
			this.type = type;
		}
	}
	
	static int N;
	static int[][] map;	// 빈 칸은 0, 벽은 1
	static int result = 0;
	
	// →, ↘, ↓
	static int[] di = {0, 1, 1};
	static int[] dj = {1, 1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		StringTokenizer st = null;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(new Pipe(1, 2, 0));
		
		System.out.println(result);
	}

	public static void dfs(Pipe p) {
		if (p.taili == N && p.tailj == N) {	// 기저조건
			result++;
			return;
		}
		
		for (int d = 0; d < 3; d++) {	// →, ↘, ↓
			if (p.type == 0 && d == 2)	// 가로 파이프는 ↓로 이동 불가능
				continue;
			if (p.type == 2 && d == 0)	// 세로 파이프는 →로 이동 불가능
				continue;
			
			int nexti = p.taili + di[d];
			int nextj = p.tailj + dj[d];
			
			if (nexti < 1 || nextj < 1 || nexti > N || nextj > N)	// 범위 체크
				continue;
			if (map[nexti][nextj] == 1)	// 벽 체크
				continue;
			if (d == 1) {	// ↘로 이동할 때의 추가적인 벽 체크
				if (map[nexti-1][nextj] == 1 || map[nexti][nextj-1] == 1)
					continue;
			}
			
			switch(d) {
			case 0:	// →
				dfs(new Pipe(nexti, nextj, 0));
				break;
			case 1:	// ↘
				dfs(new Pipe(nexti, nextj, 1));
				break;
			case 2:	// ↓
				dfs(new Pipe(nexti, nextj, 2));
				break;
			}
		}
	}
}
