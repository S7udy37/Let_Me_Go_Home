// Silver IV - 1347 : 미로 만들기

/*
 * 시뮬레이션
 * 14,368 kb, 104 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_1347_미로만들기_박진 {

	static int N;	// 적은 내용의 길이
	// ‘F’는 한칸 전진, ‘L'과 ’R'은 90도로 방향 전환
	static char[] info;
	static char[][] map = new char[100][100];	// ‘.’은 이동할 수 있는 칸이고, ‘#’는 벽
	static int r , c;	// 홍준이 위치
	static int dir; // 바라보고 있는 방향 (0 ~ 3)
	// 북, 동, 남, 서
	static int[] di = {-1, 0, 1, 0};
	static int[] dj = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		info = new char[N];
		info = sc.next().toCharArray();
		
		// 초기화
		r = c = 49;
		dir = 2;	// 처음에는 남쪽을 바라보고 있음
		map[r][c] = '.';
		
		int minRow, minCol, maxRow, maxCol;
		minRow = maxRow = r;
		minCol = maxCol = c;
		
		// 출발
		for (int i = 0; i < N; i++) {
			switch(info[i]) {
			case 'F':	// 전진
				r = r + di[dir];
				c = c + dj[dir];
				break;
			case 'L':	// 왼쪽으로 전환
				dir = (dir - 1);
				if (dir == -1)
					dir = 3;
				break;
			case 'R':	// 오른쪽으로 전환
				dir = (dir + 1) % 4;
				break;
			}
			
			map[r][c] = '.';
			minRow = minRow > r ? r : minRow;
			minCol = minCol > c ? c : minCol;
			maxRow = maxRow < r ? r : maxRow;
			maxCol = maxCol < c ? c : maxCol;
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		for (int i = minRow; i <= maxRow; i++) {
			for (int j = minCol; j <= maxCol; j++) {
				if (map[i][j] == '.') {
					sb.append('.');
				} else {
					sb.append("#");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}// end of main

}
