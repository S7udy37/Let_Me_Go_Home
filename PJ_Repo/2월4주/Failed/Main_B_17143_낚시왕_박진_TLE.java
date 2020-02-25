// Gold III - 17143 : 낚시왕

/*
10%에서 시간 초과
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_17143_낚시왕_박진_TLE {

	static class Shark {
		int r;	// 상어의 위치
		int c;	// 상어의 위치
		int s;	// 속력
		int d;	// 방향 (1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽)
		int z;	// 크기
		
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}
	
	static int R, C;		// 격자판의 크기 R, C
	static Shark[][] board;	// 크기가 R×C인 격자판
	static int M;			// 상어의 수
	
	static int result = 0;	// 낚시왕이 잡은 상어 크기의 합
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new Shark[R+1][C+1];
		
		int r, c, s, d, z;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			board[r][c] = new Shark(r, c, s, d, z);
		}
		
		// 알고리즘
		for (int man = 1; man <= C; man++) {	// 낚시왕이 오른쪽으로 한 칸씩 이동.
			catchShark(man);	// 낚시왕이 제일 가까운 상어를 잡음.
			getNextLocation();	// 상어들이 이동하게 될 위치 계산.
			moveShark();		// 상어들 이동.
			
//			for (int i = 1; i <= R; i++) {
//				for (int j = 1; j <= C; j++) {
//					if (board[i][j] != null) {
//						System.out.println(board[i][j].r + " / " + board[i][j].c + " / " + board[i][j].s + " / " + board[i][j].d + " / " + board[i][j].z);
//					}
//				}
//			}
//			for (int i = 1; i <= R; i++) {
//				for (int j = 1; j <= C; j++) {
//					if (board[i][j] == null)
//						System.out.print("0 ");
//					else
//						System.out.print("1 ");
//				}
//				System.out.println();
//			}
		}
		
		// 출력
		System.out.println(result);
	}

	private static void catchShark(int col) {
		for (int i = 1; i <= R; i++) {
			if (board[i][col] != null) {
				result += board[i][col].z;
				board[i][col] = null;
				return;
			}
		}
	}

	private static void getNextLocation() {
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (board[i][j] != null) {
					int speed = board[i][j].s;
					while (speed-- > 0) {
						switch(board[i][j].d) {
						case 1:	// 위쪽
							if (board[i][j].r - 1 > 0) {	// 이동
								board[i][j].r -= 1;
							}
							else {	// 아래쪽으로 방향전환
								board[i][j].d = 2;
								board[i][j].r += 1;
							}
							break;
						case 2:	// 아래쪽
							if (board[i][j].r + 1 <= R) {	// 이동
								board[i][j].r += 1;
							}
							else {	// 위쪽으로 방향전환
								board[i][j].d = 1;
								board[i][j].r -= 1;
							}
							break;
						case 3:	// 오른쪽
							if (board[i][j].c + 1 <= C) {	// 이동
								board[i][j].c += 1;
							}
							else {	// 왼쪽으로 방향전환
								board[i][j].d = 4;
								board[i][j].c -= 1;
							}
							break;
						case 4:	// 왼쪽
							if (board[i][j].c - 1 > 0) {	// 이동
								board[i][j].c -= 1;
							}
							else {	// 오른쪽으로 방향전환
								board[i][j].d = 3;
								board[i][j].c += 1;
							}
							break;
						default:
							break;
						}
						
					}
				}
			}
		}
	}
	
	private static void moveShark() {
		// 겹치게 될 상어들 없애기
		for (int i = 1; i <= R; i++) {
		L:	for (int j = 1; j <= C; j++) {
				if (board[i][j] != null) {
					for (int k = 1; k <= R; k++) {
						for (int s = 1; s <= C; s++) {
							if (board[k][s] != null) {
								if ((board[i][j].r == board[k][s].r) && (board[i][j].c == board[k][s].c)) {
									if (i == k && j == s)
										continue;
									if (board[i][j].z > board[k][s].z) {
										board[k][s] = null;
									}
									else {
										board[i][j] = null;
										continue L;
									}
								}
							}
						}
					}
				}
			}
		}
		
		Queue<Shark> queue = new LinkedList<Shark>();
		
		// 상어들 이동
		for (int i = 1; i <= R; i++) {
			for (int j = 1; j <= C; j++) {
				if (board[i][j] != null) {
					queue.offer(new Shark(board[i][j].r, board[i][j].c, board[i][j].s, board[i][j].d, board[i][j].z));
					board[i][j] = null;
				}
			}
		}
		Shark shark;
		while(!queue.isEmpty()) {
			shark = queue.poll();
			board[shark.r][shark.c] = new Shark(shark.r, shark.c, shark.s, shark.d, shark.z);
		}
	}
}
