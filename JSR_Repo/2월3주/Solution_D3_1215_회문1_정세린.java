/*
19,528 kb
93 ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D3_1215_회문1_정세린 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N;
		char[][] table = new char[8][8];
		int cnt;
		boolean flagi, flagj;
		StringBuilder sb = new StringBuilder();
		
		for (int tc = 1; tc <=10 ; tc++) {
			N = Integer.parseInt(br.readLine());
			cnt = 0;
			for (int i = 0; i<8; i++) {
				table[i] = br.readLine().toCharArray();
			}
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					flagi = true;
					flagj = true;
					if (j <= 8 - N) {
						for (int k = 0; k < N/2; k++) {
							if (table[i][j+k] != table[i][j+N-1-k])
								flagi = false;
							if (table[j+k][i] != table[j+N-1-k][i])
								flagj = false;
						}
						if (flagi) cnt++;
						if (flagj) cnt++;
					}
				}
			}
			sb.append("#" + tc + " " + cnt + "\n");
		}
		System.out.println(sb);
	}
}