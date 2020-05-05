// D4 - 3752 : 가능한 시험 점수

/*
 * 28,800 kb, 205 ms
 */

import java.io.*;
import java.util.*;

public class Solution_D4_3752_가능한시험점수_박진_수정 {

	static int T;
	static int N;	// 문제의 개수
	static int[] score;	// 배점
	static boolean[] visit;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			score = new int[N];
			visit = new boolean[10001];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				score[i] = Integer.parseInt(st.nextToken());
			}
			
			result = 0;
			visit[0] = true;
			for (int i = 0; i < N; i++) {
				for (int j = 10000; j >= 0; j--) {
					if (visit[j]) {
						visit[j + score[i]] = true;
					}
				}
			}
			
			for (int i = 0; i < 10001; i++) {
				if (visit[i])
					result++;
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

}
