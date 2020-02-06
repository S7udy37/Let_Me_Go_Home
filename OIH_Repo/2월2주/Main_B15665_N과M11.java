// 메모리 : 113368KB
// 시간 : 396ms
// 기존에 그냥 sysout 했을 때는 시간초과 뜸


package com.acmicpc;

import java.util.Arrays;
import java.util.Scanner;

public class Main_B15665_N과M11 {

	static boolean[] check = new boolean[10001];
	static int[] arr = new int[8];
	static int num;
	static StringBuilder sb;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		sb = new StringBuilder();
		int N = sc.nextInt();
		int M = sc.nextInt();
		int temp = 0;
		num  = 0;
		for(int i=1; i<=N; i++) {
			temp = sc.nextInt();
			if(check[temp] == true) continue;
			check[temp] = true;
			arr[i] = temp;
			num++;
		}
		
		Arrays.sort(arr);
		int[] b = new int[M];
		next_permutation(b,0,M);
		System.out.println(sb);
	}
	private static void next_permutation(int[] arr2, int cnt, int m) {
		if(cnt == m) {
			for(int i: arr2) 
				sb.append(i + " ");
			sb.append('\n');
			return;
		}
		
		for(int i=8-num; i<8; i++) {
			arr2[cnt] = arr[i];
			next_permutation(arr2, cnt+1, m);
		}
	}

}
