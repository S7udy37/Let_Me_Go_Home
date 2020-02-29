// memory : 18328KB
// time : 248ms


package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1976_여행가자_오인호 {

	
	static int[][] map;
	static int[] route;
	static boolean flag;
	static int N,M;
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		route = new int[M];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][k] == 1 && map[k][j] == 1) {
						map[i][j] = 1;
					}
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<M; i++) {
			route[i] = (Integer.parseInt(st.nextToken())-1);
		}
		for(int i=0; i<M-1; i++) {
			if(route[i] == route[i+1]) continue;
			if(map[route[i]][route[i+1]] != 1) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}
}
