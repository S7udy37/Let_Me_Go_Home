package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J1681_해밀턴순환회로_김준원 {

	private static int[][] a;
	private static int n;
	private static boolean[] visit;
	private static int min;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		a = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer t = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < n; j++)
				a[i][j] = Integer.parseInt(t.nextToken());
		}
		visit = new boolean[n];
		min = (1 << 31) - 1;
		ham(0, 0, 0);
		System.out.println(min);
		in.close();
	}

	static void ham(int crn, int cost, int vcnt) {
		if (crn == 0 && vcnt == n) {
			min = min > cost ? cost : min;
			return;
		}
		for (int i = n - 1; i >= 0; i--) {
			if (a[crn][i] == 0 || visit[i])
				continue;
			if ((i == 0 && vcnt < n - 1) || (vcnt < n - 1 && cost > min))
				break;
			visit[i] = true;
			ham(i, cost + a[crn][i], vcnt + 1);
			visit[i] = false;
		}
	}
}