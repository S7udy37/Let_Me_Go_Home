// Gold V - 5569 : 출근 경로

/* 
메모리 초과
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_5569_출근경로_박진_BFS_MLE {
	
	static class Point {
		int i, j;
		int southCnt, eastCnt;
		
		public Point(int i, int j, int southCnt, int eastCnt) {
			this.i = i;
			this.j = j;
			this.southCnt = southCnt;
			this.eastCnt = eastCnt;
		}
	}
	
	/* 주어진 힌트의 지도를 오른쪽으로 90도 돌려서 생각했음. */
	static int w, h;
	static int result = 0;
	static boolean[][] map;
	static Queue<Point> queue = new LinkedList<Point>();
	
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
		bfs(new Point(0,0,0,0));
		
		// 출력
		System.out.println(result % 100000);
	}

	static private void bfs(Point point)  {
		queue.offer(point);
		
		Point current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			if (current.i == w-1 && current.j == h-1) {
				result++;
				continue;
			}
			
			for (int d = 0; d < 2; d++) {
				int nexti = current.i + di[d];
				int nextj = current.j + dj[d];
				
				if (nexti >= w || nextj >= h)
					continue;
				
				switch(d) {
				case 0:
					if (current.eastCnt != 1)
						queue.offer(new Point(nexti, nextj, current.southCnt + 1, 0));
					else {
						if (current.i == 0)
							queue.offer(new Point(nexti, nextj, current.southCnt + 1, 0));
					}
					break;
				case 1:
					if (current.southCnt != 1)
						queue.offer(new Point(nexti, nextj, 0, current.eastCnt + 1));
					else {
						if (current.j == 0)
							queue.offer(new Point(nexti, nextj, 0, current.eastCnt + 1));
					}
					break;
				default:
					break;
				}
			}
			
		}
	}
}
