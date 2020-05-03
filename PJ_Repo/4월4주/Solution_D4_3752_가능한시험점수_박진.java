// D4 - 3752 : 가능한 시험 점수

import java.util.*;
import java.io.*;

public class Solution_D4_3752_가능한시험점수_박진 {

	static int T;
	static int N;	// 문제의 개수 (1 ≤ N ≤ 100)
	static int[] score;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			score = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				score[i] = Integer.parseInt(st.nextToken());
			}
			
			
			
			
		}// end of tc
		System.out.println(sb);
	}// end of main

}
