package study_0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Intermediate_1077 {
	
	static int N, limit, max_money;
	static int[][] gem;
	static int[] dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		limit = Integer.parseInt(st.nextToken());
		gem = new int[N][2];
		dp = new int[limit+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			gem[i][0] = Integer.parseInt(st.nextToken());
			gem[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		for(int i=0; i<N; i++) {
			for(int j=gem[i][0]; j<=limit; j++) {
				dp[j] = Math.max(dp[j], dp[j-gem[i][0]] + gem[i][1]);
			}
		}
		
		System.out.println(dp[limit]);
		
	}


}
