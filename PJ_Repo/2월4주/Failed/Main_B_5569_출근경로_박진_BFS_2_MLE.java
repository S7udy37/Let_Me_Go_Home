// Gold V - 5569 : 출근 경로

/* 
메모리 초과
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_5569_출근경로_박진_BFS_2_MLE {

	/* 주어진 힌트의 지도를 오른쪽으로 90도 돌려서 생각했음. */
	static int w, h;
	static int result = 0;
	static boolean[][] map;
	static Queue<Integer> iQueue = new LinkedList<Integer>();
	static Queue<Integer> jQueue = new LinkedList<Integer>();
	static Queue<Integer> southCntQueue = new LinkedList<Integer>();
	static Queue<Integer> eastCntQueue = new LinkedList<Integer>();

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
		bfs(0, 0, 0, 0);

		// 출력
		System.out.println(result % 100000);
	}

	static private void bfs(int i, int j, int southCnt, int eastCnt) {
		iQueue.offer(i);
		jQueue.offer(j);
		southCntQueue.offer(southCnt);
		eastCntQueue.offer(eastCnt);

		int iCurrent;
		int jCurrent;
		int soutCntCurrent;
		int eastCntCurrent;
		while (!iQueue.isEmpty()) {
			iCurrent = iQueue.poll();
			jCurrent = jQueue.poll();
			soutCntCurrent = southCntQueue.poll();
			eastCntCurrent = eastCntQueue.poll();

			if (iCurrent == w - 1 && jCurrent == h - 1) {
				result++;
				continue;
			}

			for (int d = 0; d < 2; d++) {
				int nexti = iCurrent + di[d];
				int nextj = jCurrent + dj[d];

				if (nexti >= w || nextj >= h)
					continue;

				switch (d) {
				case 0:
					if (eastCntCurrent != 1) {
						iQueue.offer(nexti);
						jQueue.offer(nextj);
						southCntQueue.offer(soutCntCurrent + 1);
						eastCntQueue.offer(0);
					} else {
						if (iCurrent == 0) {
							iQueue.offer(nexti);
							jQueue.offer(nextj);
							southCntQueue.offer(soutCntCurrent + 1);
							eastCntQueue.offer(0);
						}
					}
					break;
				case 1:
					if (soutCntCurrent != 1) {
						iQueue.offer(nexti);
						jQueue.offer(nextj);
						southCntQueue.offer(0);
						eastCntQueue.offer(eastCntCurrent + 1);
					} else {
						if (jCurrent == 0) {
							iQueue.offer(nexti);
							jQueue.offer(nextj);
							southCntQueue.offer(0);
							eastCntQueue.offer(eastCntCurrent + 1);
						}
					}
					break;
				default:
					break;
				}
			}
		}
	}
}
