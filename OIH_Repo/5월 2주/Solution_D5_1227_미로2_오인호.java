/**
* Memory : 38812KB
* Time : 198ms
/
package com.sw;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_D5_1227_미로2_오인호 {
	static int[][] map;
	static boolean[][] check;
	static int dx[] = { 1, -1, 0, 0};
	static int dy[] = { 0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=10; t++) {
			int T = sc.nextInt();
			map = new int[100][100];
			check = new boolean[100][100];
			String[] strs = new String[100];
			for(int i=0; i<100; i++) {
				strs[i] = sc.next();
			}
			Queue<Pair> q = new LinkedList<>();
			Pair goal = null;
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					map[i][j] = strs[i].charAt(j) - '0';
					if(map[i][j] == 2) q.add(new Pair(i, j));
					if(map[i][j] == 3) goal = new Pair(i, j);
				}
			}
			while(!q.isEmpty()) {
				Pair tt = q.poll();
				check[tt.x][tt.y] = true; 
				if(map[tt.x][tt.y] == 3) break;
				for(int k=0; k<4; k++) {
					int nx = tt.x + dx[k];
					int ny = tt.y + dy[k];
					if(0<=nx && nx<100 && 0<=ny && ny<100) {
						if(!check[nx][ny] && map[nx][ny] != 1) {
							q.add(new Pair(nx, ny));
						}
					}
				}
			}
			sb.append("#" + T + " ").append(check[goal.x][goal.y] ? 1 : 0).append("\n");
		}
		System.out.println(sb.toString());
	}
	private static class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}
