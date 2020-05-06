/**
* Memory : 71636KB
* Time : 230ms
/
package com.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_3752_가능한시험점수_오인호 {
	static int[] test;
	static boolean[][] score;
	static int N, MAX;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			score = new boolean[10001][N+1];
			test = new int[N+1];
			MAX = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine(), " ");
			for(int i=1; i<=N; i++) {
				test[i] = Integer.parseInt(st.nextToken());
			}
			dfs(0,0);
			int ans = 0;
			for(int i=0; i<=MAX; i++) {
				if(score[i][N]) ans++;
			}
			System.out.println("#" + t + " " + ans);
		}
	}

	private static void dfs(int index, int sum) {
		if(index == N) {
			if(sum > MAX) MAX = sum;
			score[sum][N] = true;
			return;
		}
		score[sum][index] = true;
		if(index <= N && !score[sum + test[index+1]][index+1]) dfs(index+1, sum + test[index+1]);
		if(index <= N && !score[sum][index+1]) dfs(index+1, sum);
	}

}
