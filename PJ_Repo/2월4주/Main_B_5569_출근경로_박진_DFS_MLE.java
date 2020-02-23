// Gold V - 5569 : 출근 경로

/* 
메모리 초과
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_5569_출근경로_박진_DFS_MLE {
	
	static class Point {
		int i, j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	/* 주어진 힌트의 지도를 오른쪽으로 90도 돌려서 생각했음. */
	static int w, h;
	static int result = 0;
	static boolean[][] map;
	
	// 남쪽, 동쪽
	static int[] di = { 1, 0 };
	static int[] dj = { 0, 1 };
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new boolean[w][h];
		
		// 알고리즘
		dfs(new Point(0,0), 0, 0);
		
		// 출력
		System.out.println(result % 100000);
	}

	static private void dfs(Point point, int southCnt, int eastCnt) {
		if (point.i == w-1 && point.j == h-1) {
			result++;
			return;
		}
		
		if ((point.i)+1 < w) {
			if (eastCnt != 1)
				dfs(new Point((point.i)+1, point.j), southCnt+1, 0);		// 남쪽으로 이동
			else {
				if (point.i == 0)
					dfs(new Point((point.i)+1, point.j), southCnt+1, 0);	// 남쪽으로 이동
			}
		}
		
		if ((point.j)+1 < h) {
			if (southCnt != 1)
				dfs(new Point(point.i, (point.j)+1), 0, eastCnt+1);		// 동쪽으로 이동
			else {
				if (point.j == 0)
					dfs(new Point(point.i, (point.j)+1), 0, eastCnt+1);	// 동쪽으로 이동
			}
		}
	}
}
