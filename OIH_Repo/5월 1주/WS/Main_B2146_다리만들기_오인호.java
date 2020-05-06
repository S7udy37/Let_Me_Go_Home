/**
* Time :  2092ms
* Memory : 293072KB
* 그냥 다리 만들기 2 코드에서 수정하니... 시간과 메모리가 ㅎㄷㄷ
/
package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B2146_다리만들기_오인호 {
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, -1, 0, 1 };
	static int N;
	static int[][] map;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int cnt = 1;
		map = new int[N][N];
		Queue<Pair> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[][] checked = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !checked[i][j]) {
					q.add(new Pair(i, j));
					map[i][j] = cnt;
					checked[i][j] = true;
					while (!q.isEmpty()) {
						Pair t = q.poll();
						for (int k = 0; k < 4; k++) {
							int nx = t.x + dx[k];
							int ny = t.y + dy[k];
							if (0 <= nx && nx < N && 0 <= ny && ny < N) {
								if (map[nx][ny] == 1 && !checked[nx][ny]) {
									checked[nx][ny] = true;
									map[nx][ny] = cnt;
									q.add(new Pair(nx, ny));
								}
							}
						}
					}
					cnt++;
				}
			}
		}
		cnt--;
		int[][] b = new int[cnt][cnt];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					q.add(new Pair(i, j, 0, map[i][j]));
					checked = new boolean[N][N];
					while (!q.isEmpty()) {
						Pair t = q.poll();
						for (int k = 0; k < 4; k++) {
							int nx = t.x + dx[k];
							int ny = t.y + dy[k];
							int d = t.d + 1;
							if (0 <= nx && nx < N && 0 <= ny && ny < N) {
								if (map[nx][ny] == 0) {
									if (!checked[nx][ny]) {
										checked[nx][ny] = true;
										q.add(new Pair(nx, ny, d, t.k));
									}
								} else {
									if (map[nx][ny] == t.k)
										continue;
									if (map[nx][ny] != map[t.x][t.y]) {
										int goal = map[nx][ny] - 1;
										int start = t.k - 1;
										if (b[start][goal] == 0) {
											b[start][goal] = b[goal][start] = d - 1;
										} else {
											b[start][goal] = b[goal][start] = Math.min(b[start][goal], d - 1);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		for (int i = 0; i < cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				if (b[i][j] != 0)
					pq.add(new Pair(i, j, b[i][j]));
			}
		}
		System.out.println(pq.poll().d);
	}
	public static class Pair implements Comparable<Pair> {
		int x, y, d, k;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public Pair(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public Pair(int x, int y, int d, int k) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.k = k;
		}

		@Override
		public int compareTo(Pair o) {
			return this.d - o.d;
		}

	}
}
