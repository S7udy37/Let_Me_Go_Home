// Gold V - 17144 : 미세먼지 안녕!

/*
16%에서 '틀렸습니다.'
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_17144_미세먼지안녕_박진 {

	static class Point {
		int i, j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static int R, C;			// 집의 크기 R×C
	static int T;				// T초
	static int[][] A;			// 미세먼지 초기 상태를 저장할 배열
	static int[][] afterA;		// 미세먼지가 확산된 후의 상태를 저장할 배열
	static int machineTop;		// 위쪽 공기청정기 위치
	static int machineBottom;	// 아래쪽 공기청정기 위치
	static int result = 0;
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	static Queue<Point> queue = new LinkedList<Point>();
	
	public static void main(String[] args) throws Exception {
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		A = new int[R][C];
		afterA = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		/* 알고리즘 */
		// 공기청정기 위치 파악
		for (int i = 0; i < C; i++) {
			if (A[i][0] == -1) {
				machineTop = i;
				machineBottom = i+1;
				break;
			}
		}
		// 시뮬레이션 시작
		copyA(afterA, A);
		for (int turn = 0; turn < T; turn++) {
			copyA(A, afterA);	// 미세먼지 상태 복사
			
//			System.out.println("=======확산=======");
			
			spread();		// 미세먼지 확산
			
//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(afterA[i]));
//			}
			
//			System.out.println("=======공기청정기 작동=======");
			
			runMachine();	// 공기청정기 작동
			
//			for (int i = 0; i < R; i++) {
//				System.out.println(Arrays.toString(afterA[i]));
//			}
			System.out.println("#######" + (turn+1) + "초 끝#######");
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (afterA[i][j] > 0)
					result += afterA[i][j];
			}
		}
		
		/* 출력 */
		System.out.println(result);
	}

	// 미세먼지 상태 복사
	private static void copyA(int[][] a, int[][] b) {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				a[i][j] = b[i][j];
			}
		}
	}

	// 미세먼지 확산
	private static void spread() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (A[i][j] > 0) {	// 미세먼지를 발견했을 때
					int spreadDirCnt = 0;	// 확산될 방향의 수 카운팅
					for (int d = 0; d < 4; d++) {
						int nexti = i + di[d];
						int nextj = j + dj[d];
						
						//  칸이 없으면 그 방향으로는 확산이 일어나지 않는다.
						if (nexti < 0 || nextj < 0 || nexti >= R || nextj >= C)
							continue;
						
						// 공기청정기가 있으면 그 방향으로는 확산이 일어나지 않는다.
						if (A[nexti][nextj] == -1)
							continue;
						
						spreadDirCnt++;
						queue.offer(new Point(nexti, nextj));
					}
					
					int next = A[i][j] / 5;
					afterA[i][j] = (A[i][j] - (A[i][j]/5) * spreadDirCnt) + (afterA[i][j] - A[i][j]);
					
					for (int k = 0; k < spreadDirCnt; k++) {
						afterA[queue.peek().i][queue.peek().j] += next;
						queue.poll();
					}
				}
			}
		}
	}

	// 공기청정기 작동
	private static void runMachine() {
		// 위쪽 공기청정기 작동
		for (int i = machineTop-1; i > 0; i--)
			afterA[i][0] = afterA[i-1][0];
		for (int i = 0; i < C-1; i++)
			afterA[0][i] = afterA[0][i+1];
		for (int i = 0; i < machineTop; i++)
			afterA[i][C-1] = afterA[i+1][C-1];
		for (int i = C-1; i > 1; i--)
			afterA[machineTop][i] = afterA[machineTop][i-1];
		afterA[machineTop][1] = 0;

		
		// 아래쪽 공기청정기 작동
		for (int i = machineBottom+1; i < R-1; i++) 
			afterA[i][0] = afterA[i+1][0];
		for (int i = 0; i < C-1; i++)
			afterA[R-1][i] = afterA[R-1][i+1];
		for (int i = R-1; i > machineBottom; i--)
			afterA[i][C-1] = afterA[i-1][C-1];
		for (int i = C-1; i > 1; i--)
			afterA[machineBottom][i] = afterA[machineBottom][i-1];
		afterA[machineBottom][1] = 0;
	}
}
