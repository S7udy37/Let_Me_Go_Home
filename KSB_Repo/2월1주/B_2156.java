package study_0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2156 {
	static int N;
	static int[] wine, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		wine = new int[N];
		dp = new int[N];
		
		for(int i=0; i<N; i++)
			wine[i] = Integer.parseInt(br.readLine());

		dp[0] = wine[0];
		dp[1] = Math.max(wine[0]+wine[1], wine[1]);
		dp[2] = Math.max(wine[1]+wine[2], wine[0]+wine[2]);
		
		for(int i=3; i<N; i++) {
			dp[i] = Math.max(dp[i-3]+wine[i-1]+wine[i], dp[i-2]+wine[i]);
			dp[i] = Math.max(dp[i-1], dp[i]);
		}
		
		int max=-1;
		for(int i=0; i<N; i++)
			if(dp[i]>max)
				max=dp[i];
		System.out.println(max);
		
	}
}
