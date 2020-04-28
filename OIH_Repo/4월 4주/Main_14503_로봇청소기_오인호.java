/**
* Memory : 13256KB
* Time : 80ms
*/

package com.acmicpc;

import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14503_로봇청소기_오인호 {
// 북 동 남 서
	
	private static int dx[] = { -1, 0, 1, 0};
	private static int dy[] = { 0, 1, 0, -1};
	static int[][] map;
	static int N, M;
// 북 => 0인덱스부터 남 => Max-1 인데스 부터 동 row 0 인덱스 부터 서=> row Max-1 인덱스부터
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int dir = D;
		int x = R, y = C;
		while(true) {
			if(map[x][y] == 0) map[x][y] = 2;
			if(map[x+1][y] != 0 && map[x-1][y] != 0 && map[x][y+1] != 0 && map[x][y-1] != 0) {
				if(map[x-dx[dir]][y-dy[dir]] == 1) break;
				else {
					x -= dx[dir];
					y -= dy[dir];
				}
			} else {
				dir = (dir + 3) % 4;
				if(map[x + dx[dir]][y + dy[dir]] == 0) {
					x += dx[dir];
					y += dy[dir];
				}
			}
		}
		int ans = 0;
		for(int i=0; i<N; i++) {
			for (int j = 0; j < M; j++) {
				if(map[i][j] == 2) ans++;
			}
		}
		System.out.println(ans);
	}
}
