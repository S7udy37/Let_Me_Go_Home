/*
* Memory : 13776KB
* Time : 96ms
*/

package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_치즈_오인호 {
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };
	static int N, M;
	static int[][] map;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		boolean[][] check = new boolean[N][M];
		Queue<Pair2636> q = new LinkedList<>();
		List<Pair2636> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		int prev = 0;
		int size = 0;
		check[0][0] = true;
		q.add(new Pair2636(0, 0));
		while(true) {
			size = check_map();
			if(size == 0) break;
			list.clear();
			while (!q.isEmpty()) {
				Pair2636 t = q.poll();
				for (int k = 0; k < 4; k++) {
					int nx = t.x + dx[k];
					int ny = t.y + dy[k];
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (!check[nx][ny]) {
							if (map[nx][ny] == 0) {
								q.add(new Pair2636(nx, ny));
							} else {
								list.add(new Pair2636(nx, ny));
							}
							check[nx][ny] = true;
						}
					}
				}
			}
			for (Pair2636 p : list) {
				q.add(new Pair2636(p.x, p.y));
				map[p.x][p.y] = 0;
			}
			prev = size;
			cnt++;
		}
		System.out.println(cnt);
		System.out.println(prev);
	}

	private static int check_map() {
		int cnt = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1) cnt++;
			}
		}
		return cnt;
	}

}

class Pair2636 {
	int x, y;

	public Pair2636(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
