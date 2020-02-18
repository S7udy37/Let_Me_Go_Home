// Memory : 43208KB
// Time : 244ms

package com.acmicpc;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main_B12851_숨바꼭질2_오인호 {

	static int[] map = new int[100001];
	static int[] cnt = new int[100001];
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); int K = sc.nextInt();
		Arrays.fill(map, -1);
		Deque<Integer> dq = new LinkedList<Integer>();
		dq.add(N);
		map[N] = 0;
		while(!dq.isEmpty()) {
			int x = dq.poll();
			if(x*2 <= 100000 && (map[x*2] == -1 || map[x*2] == map[x] +1)) {
				dq.addFirst(x*2);
				map[x*2] = map[x] + 1;
				cnt[x*2]++;
			}
			if(x+1 <= 100000 && (map[x+1] == -1 || map[x+1] == map[x] +1)) {
				dq.addLast(x+1);
				map[x+1] = map[x] + 1;
				cnt[x+1]++;
			}
			if(x-1 >= 0 && (map[x-1] == -1 || map[x-1] == map[x] +1)) {
				dq.addLast(x-1);
				map[x-1] = map[x] + 1;
				cnt[x-1]++;
			}
		}
		if(N==K) cnt[K] = 1;
		System.out.println(map[K]);
		System.out.println(cnt[K]);
	}

}
