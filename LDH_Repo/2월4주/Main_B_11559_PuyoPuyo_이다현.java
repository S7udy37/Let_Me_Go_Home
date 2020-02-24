package 알고리즘스터디_2월셋째주;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_B_11559_PuyoPuyo_이다현 {

	static final int N = 12, M = 6;
	static Queue<int[]> q = new LinkedList<int[]>();
	static char[][] map = new char[N][M];
	static boolean[][] visit = new boolean[N][M];
	static Queue<int[]> delete = new LinkedList<int[]>();
	static int[] dn = { 0, 0, -1, 1 };
	static int[] dm = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int answer = -1;
		int tempcount = 0;
		int temp[] = { -1, -1 };
		boolean keepgoing = true;
		for (int i = 0; i < N; i++) {
			map[i] = in.readLine().toCharArray();
		}
		while (keepgoing) {
			keepgoing = false;
			for(boolean[] t : visit) {
				Arrays.fill(t, false);
			}
			for (int i = N - 1; 0 <= i; i--) { // 4개이상 찾고 바꾸기
				for (int j = M - 1; 0 <= j; j--) {
					if (map[i][j] != '.' && !visit[i][j]) {
						delete.clear();
						tempcount = bfs(i, j);
						if (tempcount >= 4) {
							keepgoing = true;// 4개 이상짜리가 있으면 true;
							while (!delete.isEmpty()) {
								temp = delete.poll();
								map[temp[0]][temp[1]] = '.';
							}
						}
					}
				}
			}
			answer++;
			boolean keep = true;
			char tempC = '.';
			while (keep) {
				keep = false;
				for (int j = M - 1; 0 <= j; j--) {
					for (int i = N - 1; 0 < i; i--) {
						if (map[i][j] == '.' && map[i - 1][j] != '.') {
							keep = true;
							tempC = map[i][j];
							map[i][j] = map[i - 1][j];
							map[i - 1][j] = tempC;
						}
					}
				}
			}
		}
		System.out.println(answer);
	}

	private static int bfs(int i, int j) {
		int temp[] = new int[] { i, j };
		int count = 0;
		q.offer(temp);
		visit[i][j] = true;
		delete.offer(temp);
		int n, m, nN, nM;
		while (!q.isEmpty()) {
			temp = q.poll();
			count++;
			n = temp[0];
			m = temp[1];
			for (int k = 0; k < 4; k++) {
				nN = n + dn[k];
				nM = m + dm[k];
				if (0 <= nN && nN < 12 && 0 <= nM && nM < 6) {
					if (visit[nN][nM] == false && map[n][m] == map[nN][nM]) {
						temp = new int[2];
						temp[0] = nN;
						temp[1] = nM;
						q.offer(temp);
						delete.offer(temp);
						visit[nN][nM] = true;
					}
				}
			}
		}
		return count;
	}

}
