// 메모리 : 123636KB
// 시간 : 416ms

package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B13458_시험감독 {

	static long[] cla = new long[10000001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		long N = Long.parseLong(st.nextToken());
		long cnt = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) {
			cla[i] = Long.parseLong(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		long Ca = Long.parseLong(st.nextToken());
		long subCa = Long.parseLong(st.nextToken());
		for(int i=1; i<=N; i++) {
			cla[i] -= Ca; cnt++;
			if(cla[i] <= 0) continue;
			cnt += cla[i] / subCa;
			if(cla[i]%subCa != 0) cnt++;
		}
		System.out.println(cnt);
	}

}
