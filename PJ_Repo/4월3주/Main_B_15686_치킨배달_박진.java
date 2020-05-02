// Gold V - 15686 : 치킨 배달

/*
 * 조합 (DFS)
 * 16,248 kb, 208 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_15686_치킨배달_박진 {

	static class Point {
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Point [c=" + c + ", r=" + r + "]";
		}
	}
	
	static int N, M;	// 치킨집 최대 M개
	static int[][] map;	//0은 빈 칸, 1은 집, 2는 치킨집
	static int result = Integer.MAX_VALUE;
	
	static boolean[] select;	// 선택한 치킨집은 true, 폐쇄할 치킨집은 false;
	static ArrayList<Point> house = new ArrayList<Point>();	// 집들의 위치 저장
	static ArrayList<Point> chicken = new ArrayList<Point>();	// 치킨집들의 위치 저장
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					house.add(new Point(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new Point(i,j));
				}
			}
		}
		select = new boolean[chicken.size()];
		selectChicken(0, 0);	// M개의 치킨집 선택
		
		System.out.println(result);
	}// end of main
	
	// 치킨집과 집 사이의 거리를 구하는 메소드
	private static int getDistance(int hr, int hc, int cr, int cc) {
		return Math.abs(hr - cr) + Math.abs(hc - cc);
	}

	// dfs
	private static void selectChicken(int index, int cnt) {
		if (cnt == M) {
//			for (int i = 0; i < select.length; i++) {
//				System.out.print(select[i] + " ");
//			}
//			System.out.println();
			int cityD = 0;	// 도시의 치킨거리
			for (int i = 0; i < house.size(); i++) {
				int tempD = Integer.MAX_VALUE;
				for (int j = 0; j < chicken.size(); j++) {
					if (select[j]) {
						tempD = Math.min(tempD, getDistance(house.get(i).r, house.get(i).c, chicken.get(j).r, chicken.get(j).c));
					}
				}
				cityD += tempD;
			}
			
			if (result > cityD) {
				result = cityD;
			}
			
			return;
		}
		
		for (int i = index; i < chicken.size(); i++) {
			if (select[i])
				continue;
			select[i] = true;
			selectChicken(i, cnt + 1);
			select[i] = false;
		}
	}
	
}
