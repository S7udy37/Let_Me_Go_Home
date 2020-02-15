// D4 - 1249 : [S/W 문제해결 응용] 4일차 - 보급로

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_D4_1249_보급로_박진_TLE {

	static int T; 					// 전체 테스트케이스의 수
	static int N; 					// 지도의 크기 (최대 100)
	static int[][] map; 			// 지도
	static boolean[][] isSelected;	// 지나온 길인지 확인하는 flag
	static int[][] minMap;			// memorization을 위한 맵
	static int minTime;				// 출발지에서 도착지까지 가는 경로 중에 복구 작업에 드는 시간이 가장 작은 경로의 복구 시간
	
	// 상, 하, 좌, 우
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		// 각 테스트 케이스
		for (int tc = 1; tc <= T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			isSelected = new boolean[N][N];
			minMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				Arrays.fill(minMap[i], Integer.MAX_VALUE);
			}
			minTime = Integer.MAX_VALUE;
			
			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = s.charAt(j) - 48;
					// System.out.print(map[i][j]);
				}
				// System.out.println();
			}
			
			// 알고리즘
			dfs(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(minTime).append("\n");
		}
		
		// 출력
		System.out.println(sb);
	}

	private static void dfs(int row, int col, int time) {
		// 기저 조건
		if ( (row == N - 1) && (col == N - 1) ) {	// 도착지점에 도착하면 시간 비교하고 리턴.
			if (minTime > time)
				minTime = time;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nextRow = row + di[i];
			int nextCol = col + dj[i];
			
			if ( (nextRow < 0) || (nextCol < 0) || (nextRow >= N) || (nextCol >= N) )
				continue;	// 범위 벗어나면 무시.
			
			if ((isSelected[row][col] == true))
				continue;	// 이미 선택된 길이면 무시.
			
			if (minMap[nextRow][nextCol] <= (time + map[nextRow][nextCol]))
				continue;	// 이전에 왔던 길보다 시간이 오래걸리는 길이면 무시.
			
			isSelected[row][col] = true;
			if (minMap[row][col] > time)	// minMap에 걸리는 시간 기록
				minMap[row][col] = time;
			dfs(nextRow, nextCol, time + map[nextRow][nextCol]);
			isSelected[row][col] = false;
		}
	}
}