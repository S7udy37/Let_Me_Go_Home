/*
 * 57204KB
 * 288ms  
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_B_S3_1463_1로만들기_정세린 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] memo = new int[N + 1];
		Arrays.fill(memo, N);

		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(N);
		int current;
		memo[N] = 0;

		while (!queue.isEmpty()) {
			current = queue.poll();

			for (int c = 1; c <= 3; c++) {
				switch (c) {
				case 1:
					int tmp1 = current - 1;
					if (tmp1 >= 1 && memo[current] + 1 < memo[tmp1]) {
						memo[tmp1] = memo[current] + 1;
						queue.offer(tmp1);
					}

					break;
				case 2:
					if (current >= 2 && current % 2 == 0) {
						int tmp2 = current / 2;
						if (tmp2 >= 1 && memo[current] + 1 < memo[tmp2]) {
							memo[tmp2] = memo[current] + 1;
							queue.offer(tmp2);
						}
					}

					break;
				case 3:
					if (current >= 3 && current % 3 == 0) {
						int tmp3 = current / 3;
						if (tmp3 >= 1 && memo[current] + 1 < memo[tmp3]) {
							memo[tmp3] = memo[current] + 1;
							queue.offer(tmp3);
						}
					}

					break;
				}
			}
		}
		System.out.println(memo[1]);
	}
}
