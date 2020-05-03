/**
* Memory : 143612KB
* Time : 656ms
*/


package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14502_연구소_오인호 {
	private static int N, M;
	private static int[][] map;
	private static int[] dx = { 1, -1 , 0 , 0};
	private static int[] dy = { 0, 0, -1 , 1};
	private static int[] wall;
	private static int ans;
	/**
	 * 0 : 빈칸
	 * 1 : 벽
	 * 2 : 바이러스
	 * @param args
	 * @throws IOException
	 */
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		Queue<Pair14502> q = new LinkedList<Pair14502>();
		ans = -1;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		wall = new int[N*M];
		wall[N*M-1] = wall[N*M-2] = wall[N*M-3] = 1;
		int[] setWall = new int[3];
		do {
			int[][] copyMap = new int[N][M];
			boolean flag = false;
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					copyMap[i][j] = map[i][j];
					if(map[i][j] == 2) q.add(new Pair14502(i, j));
				}
			}
			for(int i=0; i<N*M; i++) {
				if(wall[i] == 1) setWall[cnt++] = i;
			}
			for(int i=0; i<3; i++) {
				int x = setWall[i] / M;
				int y = setWall[i] % M;
				if(copyMap[x][y] != 0) flag = true;
				else copyMap[x][y] = 1;
			}
			if(flag) continue;
			while(!q.isEmpty()) {
				Pair14502 temp = q.poll();
				for(int k=0; k<4; k++) {
					int nx = temp.x + dx[k];
					int ny = temp.y + dy[k];
					if(0 <= nx && nx < N && 0 <= ny && ny < M) {
						if(copyMap[nx][ny] == 0) {
							q.add(new Pair14502(nx, ny));
							copyMap[nx][ny] = 2;
						}
					}
				}
			}
			int safe = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(copyMap[i][j] == 0) safe++;
				}	
			}
			if(ans < safe) ans = safe;
		} while(next_permutation());
		System.out.println(ans);
	}
	
	private static boolean next_permutation() {
		int size = wall.length;
		int i = size-1;
		while(i>0 && wall[i-1] >= wall[i]) --i;
		if(i == 0) return false;
		int j = size-1;
		while(wall[i-1] >= wall[j]) --j;
		
		int temp = wall[i-1];
		wall[i-1] = wall[j];
		wall[j] = temp;
		
		int k = size-1;
		while(i<k) {
			temp = wall[i];
			wall[i] = wall[k];
			wall[k] = temp;
			i++; k--;
		}
		return true;
	}

}
class Pair14502 {
	int x, y;
	public Pair14502 (int x, int y) {
		this.x = x; this.y = y;
	}
}
