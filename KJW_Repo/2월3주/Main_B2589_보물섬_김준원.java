package S7udy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B2589_보물섬_김준원 {

	private static int n;
	private static int m;
	private static boolean[][] way;
	private static boolean[][] init;
	// 직접구현을 왜했을까
	private static data r, f; // Queue
	private static int[][] list; // 시작위치
	private static int cnt;

	private static class data { // LinkedList
		private int i;
		private int j;
		private int len;
		private data next;

		private data(int i, int j, int len) {
			this.i = i;
			this.j = j;
			this.len = len;
			this.next = null;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		{// 입력
			StringTokenizer tk = new StringTokenizer(in.readLine()," ");
			n = Integer.parseInt(tk.nextToken());
			m = Integer.parseInt(tk.nextToken());
			way = new boolean[n][m];
			init = new boolean[n][m];
			list = new int[n * m][2];
			// 지도 입력
			for (int i = 0; i < n; i++) {
				String s = in.readLine();
				for (int j = 0; j < m; j++) {
					// L = true, W = false 로서 거리저장용으로 사용
					init[i][j] = s.charAt(j) == 'L' ? true : false;
					// L 찾으면 스택비스무리하게 저장
					if (init[i][j]) {
						list[cnt][0] = i;
						list[cnt++][1] = j;
					}
				}
				if (i == n - 1)
					break;
			}
		}
		int max = 0;
		for (int lcnt = 0; lcnt < cnt; lcnt++) {
			// 큐 초기화
			r = f = new data(list[lcnt][0], list[lcnt][1], 0);
			// 거리배열 초기화
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					way[i][j] = init[i][j];
			for (;;) {
				// poll
				int i = r.i;
				int j = r.j;
				int len = r.len;
				r = r.next;
				way[i][j] = false;
				// 상하좌우 탐색
				if (i - 1 >= 0 && way[i - 1][j]) {
					way[i - 1][j] = false;
					if (r == null)
						r = f = new data(i - 1, j, len + 1);
					else
						f = f.next = new data(i - 1, j, len + 1);
				}
				if (i + 1 < n && way[i + 1][j]) {
					way[i + 1][j] = false;
					if (r == null)
						r = f = new data(i + 1, j, len + 1);
					else
						f = f.next = new data(i + 1, j, len + 1);
				}
				if (j - 1 >= 0 && way[i][j - 1]) {
					way[i][j - 1] = false;
					if (r == null)
						r = f = new data(i, j - 1, len + 1);
					else
						f = f.next = new data(i, j - 1, len + 1);
				}
				if (j + 1 < m && way[i][j + 1]) {
					way[i][j + 1] = false;
					if (r == null)
						r = f = new data(i, j + 1, len + 1);
					else
						f = f.next = new data(i, j + 1, len + 1);
				}
				max = max < len ? len : max;
				if (r == null)
					break;
			}
		}
		System.out.println(max);
	}

}