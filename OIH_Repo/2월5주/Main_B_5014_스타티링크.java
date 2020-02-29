// memory : 57756KB
// Time : 196ms


package com.acmicpc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_B_5014_스타티링크 {

	static int[] map;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int F = sc.nextInt();  // floor
		int S = sc.nextInt();  // kangho's floor
		int G = sc.nextInt();  // goal floor
		int U = sc.nextInt();  // up to this floor
		int D = sc.nextInt();  // down to this floor
	
		map = new int[F+1];
		Arrays.fill(map, -1);
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(S);
		map[S] = 0;
		while(!q.isEmpty()) {
			if(map[G] != -1) break;
			int f = q.peek();
			q.poll();
			if( f+U <= F && map[f+U] == -1) {
				map[f+U] = map[f] + 1;
				q.add(f+U);
			}
			if(f-D > 0 && map[f-D] == -1) {
				map[f-D] = map[f] + 1;
				q.add(f-D);
			}
		}
		if(map[G] == -1) {
			System.out.println("use the stairs");
		} else {
			System.out.println(map[G]);
		}
	}

}
