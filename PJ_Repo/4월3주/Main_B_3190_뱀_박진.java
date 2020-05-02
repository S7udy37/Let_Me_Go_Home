// Gold V - 3190 : 뱀

/*
 * 13,224 kb, 88 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_3190_뱀_박진 {

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int N; // 보드의 크기 N (2 ≤ N ≤ 100)
	static int map[][]; // 0:빈칸, 1:뱀, 2:사과
	static int K; // 사과의 개수 K (0 ≤ K ≤ 100)
	static int L; // 뱀의 방향 변환 횟수 L (1 ≤ L ≤ 100)
	// 뱀의 방향 변환 정보 (X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻)
	static int[] X;
	static char[] C;

	// 상,우,하,좌
	static int[] di = { -1, 0, 1, 0 };
	static int[] dj = { 0, 1, 0, -1 };

	static ArrayList<Point> snake = new ArrayList<Point>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map = new int[N + 1][N + 1];
		map[1][1] = 1; // 뱀 처음 위치 표시
		snake.add(new Point(1, 1));
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) { // 맵에 사과 표시
			st = new StringTokenizer(br.readLine(), " ");
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
		}
		L = Integer.parseInt(br.readLine());
		X = new int[L];
		C = new char[L];
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			X[i] = Integer.parseInt(st.nextToken());
			C[i] = st.nextToken().charAt(0);
		}

		int dir = 1; // 뱀의 방향 (초기값 오른쪽)

		int time = 0; // 초
		int index = 0; // 뱀의 방향 변환 정보를 위한 인덱스
		while (true) {
			time++;

			// 다음 머리 위치
			int nexti = snake.get(snake.size()-1).i + di[dir];
			int nextj = snake.get(snake.size()-1).j + dj[dir];

			if (nexti <= 0 || nextj <= 0 || nexti > N || nextj > N) // 벽에 부딪힘
				break;
			if (map[nexti][nextj] == 1) // 몸에 부딪힘
				break;

			if (map[nexti][nextj] == 2) {	// 사과 먹었을 경우
				// 머리 이동
				map[nexti][nextj] = 1;
				snake.add(new Point(nexti, nextj));
			} else if (map[nexti][nextj] == 0) { // 사과 못 먹었을 경우
				// 머리 이동
				map[nexti][nextj] = 1;
				snake.add(new Point(nexti, nextj));
				// 꼬리 이동
				map[snake.get(0).i][snake.get(0).j] = 0;
				snake.remove(0);
			}
			
			// 방향 전환
			if (X.length > index && X[index] == time) {
				if (C[index] == 'L') { // 왼쪽으로 방향 전환
					dir--;
				} else { // 오른쪽으로 방향 전환
					dir++;
				}
				if (dir == -1) {
					dir = 3;
				} else if (dir == 4) {
					dir = 0;
				}
				index++;
			}
		}

		System.out.println(time);
	}// end of main
}
