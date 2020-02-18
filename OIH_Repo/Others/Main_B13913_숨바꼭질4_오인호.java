// Memory : 47828KB
// Time : 624ms

package com.acmicpc;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Main_B13913_숨바꼭질4_오인호 {

	static int[] map = new int[100001];
	static int[] map2 = new int[100001];
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); int K = sc.nextInt();
		Arrays.fill(map, -1);
		Deque<Integer> dq = new LinkedList<Integer>();
		dq.add(N);
		map[N] = 0;
		map2[N] = -1;
		while(!dq.isEmpty()) {
			if(map[K] != -1) break;
			int x = dq.poll();
			if(x*2 <= 100000 && map[x*2] == -1) {
				dq.addFirst(x*2);
				map[x*2] = map[x] + 1;
				map2[x*2] = x;
			}
			if(x+1 <= 100000 && map[x+1] == -1) {
				dq.addLast(x+1);
				map[x+1] = map[x] + 1;
				map2[x+1] = x;
			}
			if(x-1 >= 0 && map[x-1] == -1) {
				dq.addLast(x-1);
				map[x-1] = map[x] + 1;
				map2[x-1] = x;
			}
		}
		Stack<Integer> st = new Stack<Integer>();
		int x = K;
		while(true) {
			if(map2[x] == -1) {
				st.push(x);
				break;
			}
			st.push(x);
			x = map2[x];
		}
		System.out.println(map[K]);
		int size = st.size();
		for(int i=0; i<size; i++) {
			System.out.print(st.pop() + " ");
		}
		System.out.println();
	}

}
