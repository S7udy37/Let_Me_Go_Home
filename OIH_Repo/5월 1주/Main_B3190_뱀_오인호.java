/**
* Memory : 13340KB
* Time : 76ms
/
package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_B3190_뱀_오인호 {
	static int[][] map;
	static List<Snake> snakes;
	static char[] order;
	// 우 상 좌 하 
	static int dx[] = { 0, 0, -1, 0, 1};
	static int dy[] = { 0, 1, 0, -1, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		st = new StringTokenizer(br.readLine().trim());
		int K = Integer.parseInt(st.nextToken());
		for(int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x-1][y-1] = 1;
		}
		st = new StringTokenizer(br.readLine().trim());
		int L = Integer.parseInt(st.nextToken());
		order = new char[10001];
		Arrays.fill(order, '0');
		for(int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			order[t] = d;
		}
		int time = 0;
		map[0][0] = -1;
		Snake head = new Snake(0, 0, 1, 1);
		Snake tail = new Snake(0, 0);
		boolean flag = false;
		while(true) {
			if(flag) break; 
			int d = head.d;
			int nx = head.x + dx[d];
			int ny = head.y + dy[d];
			int s = head.s;
			// 뱀이 맵을 벗어났다면
			if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
				flag = true; ++time; continue;
			}
			// 뱀의 몸체라면
			if(map[nx][ny] < 0) {
				flag = true; ++time; continue;
			}
			// 사과라면
			if(map[nx][ny] == 1) {
				map[nx][ny] = -d;
				s++;
			}
			else {
				int tx = tail.x; 
				int ty = tail.y;
				map[nx][ny] = -d;
				int nd = -1 * map[tx][ty];
				map[tx][ty] = 0;
				int ntx = tx + dx[nd];
				int nty = ty + dy[nd];
				tail = new Snake(ntx, nty);
			}
			if(order[++time] != '0') {
				d = set(head.d,time);
				map[nx][ny] = -d;
			}
			head = new Snake(nx, ny, d, s);
		}
		System.out.println(time);
	}
	private static int set(int d, int time) {
		int td = d;
		if(order[time] == 'L') td = (td + 1 == 5 ? 1 : td + 1 ) % 5;
		else td = (td - 1 == 0 ? 4 : td - 1) % 5;
		return td;
	}
	private static class Snake {
		int x,y,d,s;
		public Snake(int x, int y, int d, int s) {
			this.x = x; this.y = y; this.d = d; this.s = s;
		}
		public Snake(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}
