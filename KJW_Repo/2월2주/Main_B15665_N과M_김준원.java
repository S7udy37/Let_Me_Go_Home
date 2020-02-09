package com.ssafy.hw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B15665_N과M_김준원 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer t = new StringTokenizer(in.readLine(), " ");
		int n = Integer.parseInt(t.nextToken());
		int m = Integer.parseInt(t.nextToken());
		// 정렬
		int max = 0, rn = 0;
		boolean sel[] = new boolean[10001];
		t = new StringTokenizer(in.readLine(), " ");
		for (int i = 0; i < n; i++) {
			int tmp = Integer.parseInt(t.nextToken());
			if (sel[tmp]) continue;
			sel[tmp] = true;
			rn++;
			max = tmp > max ? tmp : max;
		}
		int a[] = new int[rn];
		for (int i = 1, cnt = 0; i <= max; i++)
			if (sel[i])
				a[cnt++] = i;
		// 구현
		StringBuilder txt=new StringBuilder();
		int cnt[]=new int[m];
		for(int i=0;i<Math.pow(rn, m);i++) {
			for (int j = 0; j < m; j++)
				txt.append(a[cnt[j]]).append(' ');
			txt.append('\n');
			cnt[m-1]++;
			for(int j=m-1;j>0;j--)
				if(cnt[j]>=rn) {
					cnt[j]=0;
					cnt[j-1]++;
				}
		}
		System.out.println(txt);
	}

}