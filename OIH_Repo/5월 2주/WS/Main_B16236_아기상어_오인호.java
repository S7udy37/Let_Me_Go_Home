/**
* Memory : 17392KB
* Time : 148ms
/
package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B16236_아기상어_오인호 {
	static int[][] map;
	static int dx[] = { 1, -1, 0, 0};
	static int dy[] = { 0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		Pair shark = new Pair();
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		PriorityQueue<Pair> pq1 = new PriorityQueue<>();
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++)  {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) {
					shark = new Pair(i,j,2,0);
					map[i][j] = 0;
				}
			}
		}
		int time = 0;
		while(true) {
			int cnt = 0;
			int size = shark.s;
			int ate = shark.d;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] < size) cnt++;
				}
			}
			if(cnt == 0) break;
			boolean[][] check = new boolean[N][N];
			pq.add(new Pair(shark.x, shark.y, 0, ate));
			check[shark.x][shark.y] = true; 
			pq1.clear();
			while(!pq.isEmpty()) {
				Pair t = pq.poll();
				for(int k=0; k<4; k++) {
					int nx = t.x + dx[k];
					int ny = t.y + dy[k];
					if(0<= nx && nx < N && 0<=ny && ny<N) {
						if(map[nx][ny] <= size && !check[nx][ny]) {
							check[nx][ny] = true;
							if(map[nx][ny] < size && map[nx][ny] > 0) {
								pq1.add(new Pair(nx, ny, t.s+1));
							} else {
								pq.add(new Pair(nx,ny, t.s + 1));
							}
						}
					}
				}
			}
			if(pq1.isEmpty()) break;
			Pair temp = pq1.poll();
			if(ate + 1 == size) shark = new Pair(temp.x, temp.y, size + 1, 0);
			else shark = new Pair(temp.x, temp.y, size, ate + 1);
			time += temp.s;
			map[temp.x][temp.y] = 0; 
		}
		System.out.println(time);
	}
	private static class Pair implements Comparable<Pair>{
		int x, y, s, d;
		public Pair() {}
		public Pair(int x, int y, int s) {
			this.x = x; this.y = y; this.s = s;
		}
		public Pair(int x, int y, int s, int d) {
			this.x = x; this.y = y; this.s = s; this.d = d;
		}
		@Override
		public int compareTo(Pair o) {
			int dis = this.s - o.s;
			int dis2 = this.x - o.x;
			if(dis == 0 ) {
				if(dis2 == 0) return this.y - o.y;
				else return dis2;
			}
			return dis;
		}
		
	}
}
