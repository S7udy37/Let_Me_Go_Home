// Silver II - 6603 : 로또

/*
 * 조합
 * 13236 kb, 80 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_6603_로또 {

	static int[] S;
	static int[] number;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String str = br.readLine();
			if (str.equals("0"))
				break;
			
			// 입력
			StringTokenizer st = new StringTokenizer(str, " ");
			int N = Integer.parseInt(st.nextToken());
			S = new int[N];
			for (int i = 0; i < N; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			
//			System.out.print(N + " ");
//			for (int i = 0; i < N; i++) {
//				System.out.print(S[i] + " ");
//			}
//			System.out.println();
			
			// 알고리즘
			number = new int[6];
			combination(0, 0);
			sb.append("\n");
		}// end of tc
		// 출력
		System.out.println(sb);
	}// end of main

	private static void combination(int cnt, int index) {
		if (cnt == 6) {
			for (int i = 0; i < 6; i++) {
				sb.append(number[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = index; i < S.length; i++) {
			number[cnt] = S[i];
			combination(cnt + 1, i + 1);
		}
	}
}
