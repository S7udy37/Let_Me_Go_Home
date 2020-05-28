/**
* Memory : 20728KB
* Time : 136ms
* 백준 경사로랑 동일
/
package com.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4014_활주로건설_오인호 {
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int ans = 0;
			for(int i=0; i<N; i++) {
				// 행 전용 인덱스
				int row = 1;
				boolean rowCheck = true;
				// 열 전용 인덱스
				int col = 1;
				boolean colCheck = true;
				// 좌 => 우 또는 위 => 아래
				for(int j=0; j<N-1; j++) {
					// 같은 평지면 칸 수 증가
					if(map[i][j] == map[i][j+1]) row++;
					
					// 높이차가 2이상이면 해당 열과 행은 건설 불가능
					if(Math.abs(map[i][j] - map[i][j+1]) > 1) {
						rowCheck = false;
						break;
					}
					
					// 높이차가 1인경우
					if(map[i][j] - map[i][j+1] == 1) {
						int k = j+1;
						row = 1;
						while(k<N-1) {
							if(map[i][k] != map[i][k+1]) break;
							else {
								k++; row++;
							}
						}
						if(row >= X) {
							j = j + X - 1;
							row = 0;
						}
						else {
							rowCheck = false;
							break;
						}
					}
					
					// 높이차가 -1인경우
					if(map[i][j] - map[i][j+1] == -1) {
						if(row >= X) row = 1;
						else {
							rowCheck = false;
							break;
						}
					}
				}
				// 우 => 좌 또는 아래 => 위
				for(int j=0; j<N-1; j++) {
					// 같은 평지면 칸 수 증가
					if(map[j][i] == map[j+1][i]) col++;
					
					// 높이차가 2이상이면 해당 열과 행은 건설 불가능
					if(Math.abs(map[j][i] - map[j+1][i]) > 1) {
						colCheck = false;
						break;
					}
					
					// 높이차가 1인경우
					if(map[j][i] - map[j+1][i] == 1) {
						int k = j+1;
						col = 1;
						while(k<N-1) {
							if(map[k][i] != map[k+1][i]) break;
							else {
								k++; col++;
							}
						}
						if(col >= X) {
							j = j + X - 1;
							col = 0;
						}
						else {
							colCheck = false;
							break;
						}
					}
					
					// 높이차가 -1인경우
					if(map[j][i] - map[j+1][i] == -1) {
						if(col >= X) col = 1;
						else {
							colCheck = false;
							break;
						}
					}
				}
				if(colCheck) {
					ans++;
//					System.out.println("세로방향 " + i + "번째 : " + ans);
				}
				if(rowCheck) { 
					ans++;
//					System.out.println("가로방향 " + i + "번째 : " + ans);
				}
				
			}
			System.out.println("#" + t + " " + ans);
		}
	}

}
