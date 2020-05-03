/**
* Memory : 18456KB
* Time : 208ms
/
package com.acmicpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main_B17140_이차원배열과연산_오인호 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt()-1;
		int C = sc.nextInt()-1;
		int K = sc.nextInt();
		int[][] map = new int[3][3];
		int[] count = new int[101];
		List<Integer> list = new ArrayList<>();
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int t = 101;
		boolean flag = false;
		while(t-->0) {
			int row = map.length;
			int col = map[0].length;
			if(R < row && C < col && map[R][C] == K) {
				flag = true;
				break;
			}
			int[][] newMap;
			PriorityQueue<Pair> pq = new PriorityQueue<>();
			if(row >= col) {
				// R연산
				newMap = new int[row][];
				int maxLen = 0;
				for(int i=0; i<row; i++) {
					Arrays.fill(count, 0);
					list.clear();
					for(int j=0; j<col; j++) {
						count[map[i][j]]++;
					}
					for(int j=1; j<=100; j++) {
						if(count[j] != 0) pq.add(new Pair(j, count[j]));
					}
					while(!pq.isEmpty()) {
						if(list.size() == 100) break;
						int num = pq.peek().num;
						int cnt = pq.poll().cnt;
						list.add(num);
						list.add(cnt);
					}
					newMap[i] = new int[list.size()];
					int cnt = 0;
					for (int l : list) {
						newMap[i][cnt++] = l;
					}
					maxLen = Math.max(maxLen, list.size());
				}
				map = new int[row][maxLen];
				for(int i=0; i<row; i++) {
					for(int j=0; j<newMap[i].length; j++) {
						map[i][j] = newMap[i][j];
					}
				}
			} else {
				// C 연산
				newMap = new int[col][];
				int maxLen = 0;
				for(int i=0; i<col; i++) {
					Arrays.fill(count, 0);
					list.clear();
					for(int j=0; j<row; j++) {
						count[map[j][i]]++;
					}
					for(int j=1; j<=100; j++) {
						if(count[j] != 0) pq.add(new Pair(j, count[j]));
					}
					while(!pq.isEmpty()) {
						if(list.size() == 100) break;
						int num = pq.peek().num;
						int cnt = pq.poll().cnt;
						list.add(num);
						list.add(cnt);
					}
					newMap[i] = new int[list.size()];
					int cnt = 0;
					for (int l : list) {
						newMap[i][cnt++] = l;
					}
					maxLen = Math.max(maxLen, list.size());
				}
				map = new int[maxLen][col];
				for(int i=0; i<col; i++) {
					for(int j=0; j<newMap[i].length; j++) {
						map[j][i] = newMap[i][j];
					}
				}
			}
		
		}
		System.out.println(flag?100-t:-1);
	}
	private static class Pair implements Comparable<Pair> {
		int num, cnt;
		public Pair(int num, int cnt) {
			this.num = num; this.cnt = cnt;
		}
		@Override
		public int compareTo(Pair o) {
			int dis = this.cnt - o.cnt;
			if(dis == 0) return this.num - o.num;
			return dis;
		}
		
	}
}
