// Gold IV - 3980 : 선발 명단

/*
조합 (combination)
14,812 kb
112 ms
*/

import java.util.*;
import java.io.*;

public class Main_B_3980_선발명단_박진 {

	static int T;
	static int[][] S;
	static boolean[] visit;
	static int[] ability;
	static int result;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			result = 0;
			S = new int[11][11];
			visit = new boolean[11];
			ability = new int[11];
			StringTokenizer st = null;
			for (int i = 0; i < 11; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 11; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			combination(0, 0);
			
			sb.append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

	public static void combination(int row, int cnt) {
		if (cnt == 11) {	// 기저조건
			int sum = 0;
			for (int a : ability) {
				sum += a;
			}
			
			if (result < sum) {
				result = sum;
			}
			
			return;
		}
		
		for (int col = 0; col < 11; col++) {
			if (S[row][col] > 0 && !visit[col]) {
				visit[col] = true;
				ability[row] = S[row][col];
				combination(row + 1, cnt + 1);
				visit[col] = false;
			}
		}
	}
}
