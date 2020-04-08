package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_3980_선발명단_오인호 {
	static boolean[] isSelected;
	static int[][] lineup;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			lineup = new int[11][11];
			isSelected = new boolean[11];
			ans = -1;
			
			for (int i = 0; i < 11; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 11; j++) {
					lineup[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			getScore(0,0,0);
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void getScore(int index, int total, int cnt) {

		if (cnt == 11) {
			if (ans < total) {
				ans = total;
			}
			return;
		}

		for (int j = 0; j < 11; j++) {
			if ( lineup[index][j] > 0 && !isSelected[j]) {
				isSelected[j] = true;
				getScore(index + 1, total + lineup[index][j], cnt + 1);
				isSelected[j] = false;
			} else continue;
		}
		return;
	}

}
