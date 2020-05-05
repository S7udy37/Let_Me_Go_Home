package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3752_가능한점수_정세린 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <= T; tc++) {
			int totalCnt = 0;
			int sum = 0;
			
			int N = Integer.parseInt(br.readLine());
			int[] numbers = new int[N];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				numbers[i] = Integer.parseInt(st.nextToken());
				sum += numbers[i];
			}

			boolean[] exist = new boolean[sum + 1]; 
			exist[0] = true;
			totalCnt++;
			
			for (int i = 0; i < N; i++) {
				for (int s = sum; s >= 0; s--) {
					if (exist[s] && !exist[numbers[i] + s]) {
						exist[numbers[i] + s] = true;
						totalCnt++;
					}
				}
			}
			
			sb.append("#" + tc + " " + totalCnt + "\n");
		}
		System.out.println(sb);
	}
	
}
