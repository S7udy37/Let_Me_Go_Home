/**
* Memory : 12976KB
* Time : 68ms
/

package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B5557_1학년_오인호 {
	static int N;
	static int[] num;
	static long[][] check = new long[101][21];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		num = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		check[1][num[1]]++;
		for(int i=1; i<N; i++) {
			for(int j=0; j<=20; j++) {
				if(check[i][j] != 0) {
					if(j + num[i+1] <= 20) check[i+1][j + num[i+1]] += check[i][j]; 
					if(j - num[i+1] >= 0) check[i+1][j - num[i+1]] += check[i][j];
				}
			}
		}
		System.out.println(check[N-1][num[N]]);
	}

}
