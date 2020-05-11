/**
* Memory : 289688KB
* Time : 1528ms
* Duration : 0:35:27
/
package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B16235_나무재테크_오인호 {
	static int dx[] = { 1, -1, 0, 0, 1, 1, -1, -1};
	static int dy[] = { 0, 0, 1, -1, 1, -1, 1, -1}; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// 나무가 자라는 지도
		int[][] map = new int[N][N];
		// 양분용
		int[][] give = new int[N][N];
		// 죽은 나무용
		int[][] dead = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = 5;
				give[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		PriorityQueue<Pair> pq2 = new PriorityQueue<>();
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int year = Integer.parseInt(st.nextToken());
			pq.add(new Pair(x, y, year));
		}
		int time = 0;
		while(!pq.isEmpty() || !pq2.isEmpty()) {
			if(time == K) break;
			int x = pq.peek().x;
			int y = pq.peek().y;
			int year = pq.poll().year;
			if(map[x][y] >= year) {
				// 양분을 먹을 수 있다면
				map[x][y] -= year;
				pq2.add(new Pair(x, y, year+1));
				// 나이먹은 나무 (가을이므로)가 5의 배수라면 번식
				if((year+1) % 5 == 0) {
					for(int k=0; k<8; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						if(0<=nx && nx<N && 0<=ny && ny<N) {
							pq2.add(new Pair(nx, ny, 1));
						}
					}
				}
			} else {
				// 양분이 부족하다면 자신의 나이 나누기 2
				dead[x][y] += (year/2);
			}
			// 겨울이 오면
			if(pq.isEmpty()) {
				// 양분 추가
				for(int i=0; i<N; i++) {
					for(int j=0; j<N; j++) {
						map[i][j] = map[i][j] + give[i][j] + dead[i][j];
						dead[i][j] = 0;
					}
				}
				// pq2 -> pq로 이동
				while(!pq2.isEmpty()) {
					pq.add(pq2.poll());
				}
				time++;
			}
		}
		System.out.println(pq.size());
	}
	private static class Pair implements Comparable<Pair>{
		int x, y, year;
		public Pair(int x, int y, int year) {
			this.x = x; this.y = y; this.year = year;
		}
		@Override
		public int compareTo(Pair o) {
			return this.year - o.year;
		}
		
	}
}
