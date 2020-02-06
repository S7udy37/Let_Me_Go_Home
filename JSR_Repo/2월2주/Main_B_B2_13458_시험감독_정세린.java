import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_B2_13458_시험감독_정세린 {

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] classNum = new int[N];
		long cnt = 0;
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			classNum[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			classNum[i] -= B;
			cnt++;

			if (classNum[i] <= 0)
				continue;

			cnt = cnt + (classNum[i] / C);

			if (classNum[i] % C != 0)
				cnt++;
		}
		System.out.println(cnt);
	}

}
