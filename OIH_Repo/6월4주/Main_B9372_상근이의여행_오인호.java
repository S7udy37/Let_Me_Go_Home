package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B9372_상근이의여행_오인호 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken()) - 1;
			}
			System.out.println(N-1);
		}
	}
}
