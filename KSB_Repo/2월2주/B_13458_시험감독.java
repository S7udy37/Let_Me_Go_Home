package study_0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_13458_시험감독 {
	
	static int N;
	static long ans;
	static long[] classes = new long[1000000];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		classes = new long[(int) N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			classes[i] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		long x = Long.parseLong(st.nextToken());
		long y = Long.parseLong(st.nextToken());
		
		ans = N;
		long num;
		for(int i=0; i<N; i++) {
			classes[i] -= x;
			if(classes[i]<=0)
				continue;
			
			num = classes[i] / y;
			
			if(classes[i]-(num*y)>0) {
				ans += (num+1);
			}else {
				ans += num;
			}
		}
		
		System.out.print(ans);
	}

}
