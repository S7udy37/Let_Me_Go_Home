/**
* Memory : 13872KB
* Time : 228ms
/

package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B1062_가르침_오인호 {

	static int N,K,ans;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int[] words = new int[N];
		for(int i=0; i<N; i++) {
			String str = br.readLine();
			int size = str.length();
			for(int j=0; j<size; j++) {
				words[i] |= (1<<(str.charAt(j) - 'a'));
			}
		}
		System.out.println(combination(0,K,0,words));
	}
	private static int combination(int index, int k, int mask, int[] words) {
		if(k < 0) return 0;
		if(index == 26) {
			int cnt = 0;
			for (int word : words) {
				if((word & ((1<<26)-1-mask)) == 0) cnt++;
			}
			return cnt;
		}
		int ans = 0;
		int t1 = combination(index+1, k-1, mask | (1<<index), words);
		if(ans < t1) ans = t1;
		if(index != 'a' - 'a' && index != 'n' - 'a' && index != 't' - 'a'
				&& index != 'i' - 'a' && index != 'c' - 'a') {
			t1 = combination(index+1, k, mask, words);
			if(ans < t1) ans = t1;
		}
		return ans;
	}

}
