/*
 * 143708 KB
 * 380 ms
 */
import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_2589_보물섬_정세린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N, M, max = 0;
		int[][] dh = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		int[][] memo = new int[N][M];

		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();

		Queue<Point> queue = new LinkedList<Point>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				if (map[i][j] == 'L') {
					for (int[] m : memo)
						Arrays.fill(m, M * N);
					queue.offer(new Point(i, j));
					memo[i][j] = 0;
				}else continue;

				Point current;
				while (!queue.isEmpty()) {
					current = queue.poll();
					for (int dir = 0; dir < 4; dir++) {
						int itmp = current.x + dh[dir][0];
						int jtmp = current.y + dh[dir][1];
						if (itmp >= 0 && itmp < N && jtmp >= 0 && jtmp < M && map[itmp][jtmp] == 'L') {
							if (memo[current.x][current.y] + 1 < memo[itmp][jtmp]) {
								memo[itmp][jtmp] = memo[current.x][current.y] + 1;
								max = Math.max(memo[itmp][jtmp], max);
								queue.offer(new Point(itmp, jtmp));
							}
						}
					}
				}
			}
		}
		System.out.println(max);
	}
}
