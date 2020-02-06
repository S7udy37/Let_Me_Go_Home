import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_S2_15665_N과M_정세린 {
	static int N, M;
	static int[] numbers, per;
	static StringBuilder sb = new StringBuilder();;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("Main_B_S2_15665_N과M_정세린_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[N];
		per = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(numbers);

		permutation(0);
		System.out.println(sb.toString());
	}

	private static void permutation(int index) {
		if (index == M) {
			for (int p : per) {
				sb.append(p).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = 0; i < N; i++) {
			if (i > 0 && numbers[i] == numbers[i - 1]) {
				continue;
			}
			per[index] = numbers[i];
			permutation(index + 1);
		}
	}

}