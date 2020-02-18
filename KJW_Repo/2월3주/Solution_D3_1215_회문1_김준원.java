package S7udy;
/*
 * 23424 kb
 * 95 ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D3_1215_회문1_김준원 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder txt = new StringBuilder();
		char a[][] = new char[8][8];
		for (int t = 1; t <= 10; t++) {
			int n = in.read() - '0';
			in.read(); // '\r'
			//in.read(); // '\n'
			for (int i = 0; i < 8; i++) {
				a[i]= in.readLine().toCharArray();
			}
			int cnt = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					// 가로
					if (j + n - 1 < 8 && a[i][j] == a[i][j + n - 1]) {
						boolean sw = false;
						for (int k = 0; k < n / 2; k++) {
							if (a[i][j + k] != a[i][j + n - 1 - k]) {
								sw = true;
								break;
							}
						}
						if (!sw)
							cnt++;
					}
					// 세로
					if (i + n - 1 < 8 && a[i][j] == a[i + n - 1][j]) {
						boolean sw = false;
						for (int k = 0; k < n / 2; k++) {
							if (a[i + k][j] != a[i + n - 1 - k][j]) {
								sw = true;
								break;
							}
						}
						if (!sw)
							cnt++;
					}
				}
			}
			txt.append('#').append(t).append(' ').append(cnt).append('\n');
		}
		System.out.print(txt);
	}
}