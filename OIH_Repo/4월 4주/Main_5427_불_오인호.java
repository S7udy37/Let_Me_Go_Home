/**
* Memory : 119660KB
* Time : 820ms
/
package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5427_불_오인호 {
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static char[][] map;
	static int[][] timeMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int W = Integer.parseInt(st.nextToken());
			int H = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			timeMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					timeMap[i][j] = -1;
				}
			}
			Queue<Pair> fq = new LinkedList<>(); // fire queue
			Queue<Pair> pq = new LinkedList<>(); // people queue
			for (int i = 0; i < H; i++) {
				String s = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '*')
						fq.add(new Pair(i, j));
					else if (map[i][j] == '@') {
						map[i][j] = '.';
						timeMap[i][j] = 0;
						pq.add(new Pair(i, j));
					}
				}
			}
			int time = -1;
			L:while (!fq.isEmpty() || !pq.isEmpty()) {
				int size = fq.size();
				while (size-- > 0) {
					Pair f = fq.poll();
					for (int k = 0; k < 4; k++) {
						int nx = f.x + dx[k];
						int ny = f.y + dy[k];
						if (0 <= nx && nx < H && 0 <= ny && ny < W) {
							if (map[nx][ny] == '.') {
								map[nx][ny] = '*';
								fq.add(new Pair(nx, ny));
							}
						}
					}
				}
				size = pq.size();
				while (size-- > 0) {
					Pair p = pq.poll();
					for (int k = 0; k < 4; k++) {
						int nx = p.x + dx[k];
						int ny = p.y + dy[k];
						if (0 <= nx && nx < H && 0 <= ny && ny < W) {
							if (map[nx][ny] == '.' && timeMap[nx][ny] == -1) {
								timeMap[nx][ny] = timeMap[p.x][p.y] + 1; 
								pq.add(new Pair(nx, ny));
							}
						} else {
							time = timeMap[p.x][p.y] + 1;
							break L;
						}
					}
				}
			}
			System.out.println(time == -1 ? "IMPOSSIBLE" : time);
		}
	}

	public static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
