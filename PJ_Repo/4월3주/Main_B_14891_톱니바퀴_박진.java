// Silver I - 14891 : 톱니바퀴

/*
 * 시뮬레이션
 * 13,212 kb, 72 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_14891_톱니바퀴_박진 {
	static char[][] T;	// 4개의 톱니바퀴 (12시방향부터 시계방향 순서 (3시 방향 인덱스 = 2, 9시 방향 인덱스 = 6)) (N극은 0, S극은 1)
	static int K;	// 회전 횟수
	static int[][] INFO;	// INFO[i][0] : 톱니 번호, INFO[i][1] : 회전 방향 (방향이 1인 경우는 시계 방향이고, -1인 경우는 반시계 방향)
	static int[] flag;	// 회전하게 될 톱니바퀴 표시 (정시계방향: 1, 반시계방향: -1, 회전하지않음: 0)
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = new char[4][8];
		for (int i = 0; i < 4; i++) {
			T[i] = br.readLine().toCharArray();
		}
		K = Integer.parseInt(br.readLine());
		INFO = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			INFO[i][0] = Integer.parseInt(st.nextToken()) - 1;
			INFO[i][1] = Integer.parseInt(st.nextToken());
		}
		flag = new int[4];
		
		// K번 회전
		for (int i = 0; i < K; i++) {
			Arrays.fill(flag, 0);
			
			int startNum = INFO[i][0];	// 회전을 시작하는 맨 처음 톱니바퀴 번호
			int left = T[startNum][6];	// 왼쪽 극
			int right = T[startNum][2];	// 오른쪽 극
			flag[startNum] = INFO[i][1];
			// 왼쪽에 있는 톱니바퀴 확인
			for (int j = startNum - 1; j >= 0; j--) {
				if (left != T[j][2]) {	// 극이 다른지 확인
					if (flag[startNum] == 1) {
						flag[j] = -1;
					} else if (flag[startNum] == -1) {
						flag[j] = 1;
					}
					startNum = j;
					left = T[j][6];
				} else {
					break;
				}
			}
			
			startNum = INFO[i][0];
			// 오른쪽에 있는 톱니바퀴 확인
			for (int j = startNum + 1; j <= 3; j++) {
				if (right != T[j][6]) {	// 극이 다른지 확인
					if (flag[startNum] == 1) {
						flag[j] = -1;
					} else if (flag[startNum] == -1) {
						flag[j] = 1;
					}
					startNum = j;
					right = T[j][2];
				} else {
					break;
				}
			}
			
			// 톱니바퀴들 회전
			for (int j = 0; j < 4; j++) {
				if (flag[j] == 1) {	// 시계방향 회전
					rotation(T[j]);
				} else if (flag[j] == -1) {	// 반시계방향 회전
					reverseRotation(T[j]);
				}
			}
		}
		
		// 점수 계산
		for (int i = 0; i < 4; i++) {
			if (T[i][0] == '1') {
				result += Math.pow(2, i);
			}
		}
		
		System.out.println(result);
	}// end of main
	
	// 시계방향 회전
	private static void rotation(char[] c) {
		char temp = c[7];
		for (int i = 7; i > 0; i--) {
			c[i] = c[i-1];
		}
		c[0] = temp;
	}
	
	// 반시계방향 회전
	private static void reverseRotation(char[] c) {
		char temp = c[0];
		for (int i = 0; i < 7; i++) {
			c[i] = c[i+1];
		}
		c[7] = temp;
	}

}
