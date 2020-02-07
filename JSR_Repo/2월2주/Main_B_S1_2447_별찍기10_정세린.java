import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B_S1_2447_별찍기10_정세린 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (blank(i, j, 1))
					sb.append(' ');
				else
					sb.append('*');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	private static boolean blank(int i, int j, int n) {
		if (n == N / 3)
			return (i >= n && i < n * 2 && j >= n && j < n * 2);

		return (i % (n * 3) >= n && i % (n * 3) < n * 2 && j % (n * 3) >= n && j % (n * 3) < n * 2)
				|| blank(i, j, n * 3);
	}

}
