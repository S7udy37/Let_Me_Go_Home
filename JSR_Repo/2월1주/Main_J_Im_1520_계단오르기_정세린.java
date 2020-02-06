import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_J_Im_1520_계단오르기_정세린 {
	static int[] stair;
	static int N, max;
	static int flag;
	static int[][] memo;

	public static void main(String[] args) throws IOException {
// 		System.setIn(new FileInputStream("Main_J_Im_1520_계단오르기_정세린_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		stair = new int[N + 1];
		memo = new int[N + 1][2];
		for (int i = 1; i < N + 1; i++) {
			stair[i] = Integer.parseInt(br.readLine());
		}
		max = step(N, 1);
		System.out.println(max);
	}

	private static int step(int index, int flag) {
		if (index == 0 || index == 1) {
			return memo[index][flag] = stair[index];
		}
		if (memo[index][flag] != 0)
			return memo[index][flag];
		if (flag == 0) {
			return memo[index][flag] = step(index - 2, 1) + stair[index];
		}
		return memo[index][flag] = maximum(step(index - 1, 0) + stair[index], step(index - 2, 1) + stair[index]);
	}

	private static int maximum(int a, int b) {
		return (a > b) ? a : b;
	}

}
