// Silver III - 9372 : 상근이의 여행

/*
 * MST의 성질
 * 47,100 kb, 316 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_9372_상근이의여행_박진 {

	static int T;
	static int N, M; // 국가의 수 N(2 ≤ N ≤ 1 000), 비행기의 종류 M(1 ≤ M ≤ 10 000)
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				Integer.parseInt(st.nextToken());
				Integer.parseInt(st.nextToken());
			}
			
			int result = N - 1;	// 항상 연결 그래프이고, 모든 국가를 방문하기 위해서는 MST가 되면 되므로 답은 (노드의 개수 - 1)개
			
			sb.append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

}
