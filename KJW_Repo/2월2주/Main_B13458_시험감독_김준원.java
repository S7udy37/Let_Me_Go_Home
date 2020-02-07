package S7udy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B13458_시험감독_김준원 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		long cnt = 0;
		StringTokenizer a = new StringTokenizer(in.readLine());
		String bf[] = in.readLine().split(" ");
		int b = Integer.parseInt(bf[0]);
		int c = Integer.parseInt(bf[1]);
		int ai_b;
		for (int i = 0; i < n; i++) {
			ai_b = (Integer.parseInt(a.nextToken()) - b);
			if (ai_b < 0) {cnt++;continue;}			
			cnt += 1 + ai_b / c + (ai_b % c == 0 ? 0 : 1);
		}
		System.out.print(cnt);
		in.close();
	}
}