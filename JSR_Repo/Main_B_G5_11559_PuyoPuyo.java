package Baekjoon;
/*
 * 12972KB
 * 76ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main_B_G5_11559_PuyoPuyo {

	static Queue<Integer> q = new LinkedList<Integer>();
	static char[][] map = new char[12][6];
	static boolean[][] visit = new boolean[12][6];
	static int totalCnt;
	static int cnt;
	static int[][] dh = { { 0, -1 }, { 0, 1 }, { 1, 0 } }; // 좌우하 순으로 탐색

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력
		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}

		boolean flag = true; // 지울 것이 있음

		while (flag) {
			// 뿌요 내리기 - 아래에서 위로 탐색하며,
			for (int i = 10; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					// 뿌요이며, 그 아래가 '.'일경우
					if (map[i][j] != '.' && map[i + 1][j] == '.') {
						// 뿌요를 내림
						int k = 1;
						while (i + k <= 11 && map[i + k][j] == '.') {
							map[i + k][j] = map[i + k - 1][j];
							map[i + k - 1][j] = '.';
							k++;
						}
					}
				}
			}
			
			flag = false;
			// 4개이상인 것 지우기
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && !visit[i][j]) {
						cnt = 0;
						char color = map[i][j];
						dfs(i, j, color);
						if (cnt >= 4) {
							flag = true; // 지울 것이 있으므로 true;
							q.clear();
						} else { // 원래대로 돌려놓기(4개 미만임)
							while (!q.isEmpty()) {
								int pos = q.poll();
								map[pos / 100][pos % 100] = color;
							}
							q.clear();
						}
					}
				}
			}

			// 지운 것이 아무것도 없다면 break;
			if (!flag) break;
			
			// 지운것이 있다면 totalCnt++
			totalCnt++;
			// visit초기화
			for (int i = 0; i < 12; i++) {
				Arrays.fill(visit[i], false);
			}
		}
		System.out.println(totalCnt);
	}

	static void dfs(int i, int j, char color) {
		// 방문
		map[i][j] = '.';
		q.offer(i * 100 + j);
		cnt++;
		visit[i][j] = true;

		for (int dir = 0; dir < 3; dir++) {
			int ni = i + dh[dir][0];
			int nj = j + dh[dir][1];

			// 배열 벗어나지 않고 미방문, 다음 위치가 같은 색상이면
			if (ni >= 0 && ni < 12 && nj >= 0 && nj < 6 && !visit[ni][nj] && map[ni][nj] == color) {
				dfs(ni, nj, color);
				// 갯수가 4개 미만이면 원래대로
			}
		}
	}

}
