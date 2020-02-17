package study_0215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B_13913_숨바꼭질4 {
	static int N, K;
	static int[][] dp = new int[100001][2];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		
		if(K<N) {
			sb.append(N-K).append('\n');
			for(int i=N; i>=K; i--) {
				sb.append(i); sb.append(' ');
			}
			
		}else {
			int a, b;	// 비교
			int a_idx, b_idx;
			for(int i=0; i<=K; i++) {
				if(i<N) {
					dp[i][0] = N-i;
					dp[i][1] = i+1;
					continue;
				}			
				
				if(i==N) {
					dp[i][0] = 0;
					dp[i][1] = N;
					continue;
				}
				
				if(i%2==0) {
					a = dp[i/2][0] + 1;
					a_idx = i/2;
				}else {
					if(dp[(i-1)/2][0] < dp[(i+1)/2][0]) {
						a = dp[(i-1)/2][0] + 2;
						a_idx = (i-1)/2;
					}else {
						a = dp[(i+1)/2][0] + 2;
						a_idx = (i+1)/2;
					}
				}
				
				b = dp[i-1][0]+1;
				b_idx = i-1;

				if(a<b) {
					dp[i][0] = a;
					dp[i][1] = a_idx;
				}else {
					dp[i][0] = b;
					dp[i][1] = b_idx;				
				}
			}		
			
			sb.append(dp[K][0]).append('\n');
			List<Integer> arr = new ArrayList<Integer>();
			int n = K;
			while(true) {
				if(n==N) {
					arr.add(N);
					break;
				}
				
				arr.add(n);
				n = dp[n][1];
				
			}
			
			
			int len=arr.size()-1;
			for(int i=len; i>=0; i--)
				sb.append(arr.get(i)).append(' ');
			
		}
		
		System.out.println(sb.toString());		
	
	}
	

}