/**
* Memory : 44036KB
* Time : 280ms
/

package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B17142_연구소3_오인호 {
	static int N, M;
	static int[][] map, mapCopy;
	static int[] virus;
	static List<Pair> virusPosition;
	static int dx[] = { 1, -1, 0, 0};
	static int dy[] = { 0, 0, 1, -1}; 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		virusPosition = new ArrayList<>();
		map = new int[N][N];
		int ans = -1;
		int wallCnt = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) virusPosition.add(new Pair(i, j));
				if(map[i][j] == 1) wallCnt++;
			}
		}
		virus = new int[virusPosition.size()];
		for(int i=1; i<=M; i++) {
			virus[virusPosition.size() - i] = 1;
		}
		do {
			mapCopy = new int[N][N];
			Queue<Pair> q = new LinkedList<>();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j] == 1) mapCopy[i][j] = -2;
					else mapCopy[i][j] = -1;
				}
			}
			for(int i = 0; i<virus.length; i++) {
				if(virus[i] == 1) {
					int x = virusPosition.get(i).x;
					int y = virusPosition.get(i).y;
					q.add(new Pair(x, y));
					mapCopy[x][y] = 0;
				}
			}
			int max = 0;
			int virusCnt = virusPosition.size();
			while(!q.isEmpty()) {
				if(N*N - wallCnt == virusCnt) break;
				Pair tt = q.poll();
				for(int k=0; k<4; k++) {
					int nx = tt.x + dx[k];
					int ny = tt.y + dy[k];
					if(0<=nx && nx<N && 0<=ny && ny<N) {
						if(map[nx][ny] != 1 && mapCopy[nx][ny] == -1) {
							q.add(new Pair(nx, ny));
							if(map[nx][ny] != 2) virusCnt++;
							mapCopy[nx][ny] = mapCopy[tt.x][tt.y] + 1; 
						}
					}
				}
			}
			if(N*N - wallCnt == virusCnt) {
				for(int i=0; i<N ;i++) {
					for(int j=0; j<N; j++) {
						if(max < mapCopy[i][j]) max = mapCopy[i][j];
					}
				}
				if(( ans == -1 ||  ans > max)) ans = max;
			}
		} while(next_permutation());
		System.out.println(ans);
	}
	private static boolean next_permutation() {
		int i = virus.length - 1;
		while(i > 0 && virus[i-1] >= virus[i]) i--;
		if(i == 0) return false;
		int j = virus.length - 1;
		while(virus[i-1] >= virus[j]) j--;
		int temp = virus[i-1];
		virus[i-1] = virus[j];
		virus[j] = temp;
		int k = virus.length - 1;
		while(i < k) {
			temp = virus[i];
			virus[i] = virus[k];
			virus[k] = temp;
			i++; k--;
		}
		return true;
	}
	private static class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}
