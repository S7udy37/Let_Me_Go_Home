// Silver I - 1389 : 케빈 베이컨의 6단계 법칙

/*
 * 플로이드-워셜
 * 13,228 kb, 80 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_1389_케빈베이컨의6단계법칙_박진 {

	static int N, M; // 유저의 수 N (2 ≤ N ≤ 100), 친구 관계의 수 M (1 ≤ M ≤ 5,000)
	static int[][] adjList;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(adjList[i], Integer.MAX_VALUE);
		}
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			adjList[a][b] = 1;
			adjList[b][a] = 1;
		}
		
		// 플로이드-워셜
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (adjList[i][k] != Integer.MAX_VALUE && adjList[k][j] != Integer.MAX_VALUE) {
						if (adjList[i][j] > adjList[i][k] + adjList[k][j]) {
							adjList[i][j] = adjList[i][k] + adjList[k][j];
						}
					}
				}
			}
		}
		
		int result = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int temp = 0;
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				temp += adjList[i][j];
			}
			if (min > temp) {
				min = temp;
				result = i;
			}
		}
		System.out.println(result);
		
	}// end of main

}
