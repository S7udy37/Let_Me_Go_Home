// Gold IV : 1976 - 여행 가자

/*
 * DFS
 * 17,148 kb
 * 128 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_1976_여행가자_박진2 {

	static int N;	// 도시의 수 N (200이하)
	static int M;	// 여행 계획에 속한 도시들의 수 M (1000이하)
	static int[][] adjArr;	// 도시 연결 정보
	static boolean[] isVisited;
	static int[] plan;		// 여행 계획
	static boolean result;	// 가능하면 true 불가능하면 false
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adjArr = new int[N+1][N+1];
		isVisited = new boolean[N+1];
		plan = new int[M+1];
		StringTokenizer st = null;
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < N+1; j++) {
				adjArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < M+1; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}// end input
		
		go(plan[1]);
		result = true;
		for (int i = 1; i < M+1; i++) {
			if (isVisited[plan[i]] == false)
				result = false;
		}
		
		System.out.print(result ? "YES" : "NO");
	}// end main

	private static void go(int current) {
		isVisited[current] = true;
		for (int i = 1; i < N+1; i++) {
			if (adjArr[current][i] == 0 || isVisited[i] == true)
				continue;
			go(i);
		}
	}
}
