/**
* Memory : 84896KB
* Time : 516ms
/
package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B1600_말이되고픈원숭이_오인호 {
	static int[] dx = { 1, -1, 0, 0};
	static int[] dy = { 0, 0, 1, -1};
	static int[] hx = { 1, 1, -1, -1, 2, 2, -2, -2};
	static int[] hy = { 2, -2, 2, -2, 1, -1, 1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][] map = new int[H][W];
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			} 
		}
		int[][][] c = new int[H][W][K+1];
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(0, 0, 0));
		while(!q.isEmpty()) {
			Pair t = q.poll();
			if(t.k < K) {
				for(int l=0; l<8; l++) {
					int nx = t.x + hx[l];
					int ny = t.y + hy[l];
					if(0<=nx && nx<H && 0<=ny && ny<W) {
						if(map[nx][ny] == 0 && c[nx][ny][t.k+1] == 0) {
							c[nx][ny][t.k+1] = c[t.x][t.y][t.k] + 1;
							q.add(new Pair(nx, ny, t.k+1));
						}
					}
				}
			}
			for(int l=0; l<4; l++) {
				int nx = t.x + dx[l];
				int ny = t.y + dy[l];
				if(0<=nx && nx<H && 0<=ny && ny<W) {
					if(map[nx][ny] == 0 && c[nx][ny][t.k] == 0) {
						c[nx][ny][t.k] = c[t.x][t.y][t.k] + 1;
						q.add(new Pair(nx, ny, t.k));
					}
				}
			}
		}
		int ans = -1;
		if(H == 1 && W == 1) ans = 0;
		for(int i=0; i<=K; i++) {
			if(c[H-1][W-1][i] != 0) {
				if(ans == -1) ans = c[H-1][W-1][i]; 
				else ans = Math.min(ans, c[H-1][W-1][i]);
			}
		}
		System.out.println(ans);
	}
	private static class Pair {
		int x, y, k;
		public Pair(int x, int y, int k) {
			this.x = x; this.y = y; this.k = k;
		}
	}
}
