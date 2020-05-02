// Gold V - 16235 : 나무 재테크

/*
281,568 kb, 1,108 ms
*/

import java.util.*;
import java.io.*;

public class Main {

	static int N, M, K;	// N: 땅의 크기, M: 심은 나무의 수, K: K년 후
	static int[][] A;	// 해당 칸에 추가될 양분의 양
	static int[][] map;	// 해당 칸의 양분 정보
	static PriorityQueue<Integer>[][] pq;	// 해당 칸에 있는 나무들의 나이
	static Queue<Integer>[][] deadTree;	// 해당 칸에서 죽은 나무들의 나이
	static int[][] plusTree;	// 해당 칸에서 나이가 5의 배수인 나무들의 수 저장
	
	static int[] di = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dj = {0, 0, -1, 1, -1, 1, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N+1][N+1];
		map = new int[N+1][N+1];
		pq = new PriorityQueue[N+1][N+1];
		deadTree = new LinkedList[N+1][N+1];
		plusTree = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			Arrays.fill(map[i], 5);	// 가장 처음에 양분은 모든 칸에 5
			for (int j = 1; j <= N; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				pq[i][j] = new PriorityQueue<Integer>();
				deadTree[i][j] = new LinkedList<Integer>();
			}
		}
		int x, y, z;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			pq[x][y].offer(z);
		}// end input
		
		// K년동안 반복
		for (int k = 0; k < K; k++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.println(getNumOfTree());
	}// end main

	private static void spring() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (pq[i][j].size() != 0) {
					int size = pq[i][j].size();
					Queue<Integer> temp = new LinkedList<Integer>();
					for(int k = 0; k < size; k++) {
						int curTree = pq[i][j].poll();
						if (curTree > map[i][j]) {
							deadTree[i][j].offer(curTree);
						} else {
							map[i][j] -= curTree;
							temp.offer(curTree + 1);
							if((curTree+1) % 5 == 0) {
								plusTree[i][j]++;
							}
						}
					}
					while(!temp.isEmpty()) {
						pq[i][j].offer(temp.poll());
					}
				}
			}
		}
	}
	
	private static void summer() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (deadTree[i][j].size() != 0) {
					while(!deadTree[i][j].isEmpty()) {
						int curTree = deadTree[i][j].poll();
						curTree /= 2;
						map[i][j] += curTree;
					}
				}
			}
		}
	}

	private static void fall() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (plusTree[i][j] > 0) {
					for (int k = 0; k < plusTree[i][j]; k++) {
						// 인접한 8개의 칸에 나이가 1인 나무 추가
						for (int d = 0; d < 8; d++) {
							int nexti = i + di[d];
							int nextj = j + dj[d];
							
							if(nexti < 1 || nextj < 1 || nexti > N || nextj > N)
								continue;
							
							pq[nexti][nextj].offer(1);
						}
					}
					plusTree[i][j] = 0;	// 초기화
				}
			}
		}
	}
	
	private static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}

	// 살아있는 나무의 개수 리턴
	private static int getNumOfTree() {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cnt += pq[i][j].size();
			}
		}
		return cnt;
	}
	
}
