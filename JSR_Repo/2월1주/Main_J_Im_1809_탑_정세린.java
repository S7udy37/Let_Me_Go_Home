package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_Im_1809_탑_정세린 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] height = new int[N];
		int[] res = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = N - 1; i >= 1; i--) {
			for (int j = i - 1; j >= 0; j--)
				if (height[i] < height[j]) {
					res[i] = j + 1;
					break;
				}
		}
		for (int r : res)
			System.out.print(r + " ");
	}
}
