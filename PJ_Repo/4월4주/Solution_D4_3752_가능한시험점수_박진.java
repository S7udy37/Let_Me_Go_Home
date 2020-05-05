// D4 - 3752 : 가능한 시험 점수

/*
 * 무슨 유형의 문제인건지 모르겠다...
 * 98,768 kb, 324 ms
 */

import java.io.*;
import java.util.*;

public class Solution_D4_3752_가능한시험점수_박진 {

	static int T;
	static int N;	// 문제의 개수
	static int[] score;	// 배점
	static boolean[] visit;
	static ArrayList<Integer> list = new ArrayList<Integer>();
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
				for (int j = 0; j < 10001; j++) {
					if (visit[j]) {
						list.add(j+score[i]);
					}
				}
				for (int j = 0; j < list.size(); j++) {
					visit[list.get(j)] = true;
				}
				list.clear();
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
