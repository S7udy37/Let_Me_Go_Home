// Gold IV - 17140 : 이차원 배열과 연산

/*
 * 시뮬레이션
 * 17,320 kb, 136 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_17140_이차원배열과연산_박진 {

	static class Check implements Comparable<Check>{
		int num, cnt;	// (수, 수의 등장 횟수)

		public Check(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Check o) {
			if (this.cnt == o.cnt)
				return Integer.compare(this.num, o.num);
			return Integer.compare(this.cnt, o.cnt);
		}
	}
	
	static int r, c, k;
	static int[][] A = new int[101][101];
	static int rowSize = 3;	// 행 크기
	static int colSize = 3;	// 열 크기
	static int[] counting = new int[101];	// (수, 수의 등장 횟수) 체크할 때 사용할 배열
	static PriorityQueue<Check> pq = new PriorityQueue<Check>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= 3; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= 3; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;	// 시간
		while(true) {
			if (A[r][c] == k)
				break;
			if (time > 100) {	// 시간이 100을 넘어가는 경우에는 -1을 출력
				time = -1;
				break;
			}
			
			if (rowSize >= colSize) {	// R 연산
				for (int i = 1; i <= rowSize; i++) {
					// (수, 수의 등장 횟수) 체크
					Arrays.fill(counting, 0);
					for (int j = 1; j <= colSize; j++) {
						counting[A[i][j]]++;
					}
					// 정렬 순서 정하기
					for (int j = 1; j <= 100; j++) {
						if(counting[j] > 0)
							pq.offer(new Check(j, counting[j]));
					}
					// 정렬하기
					int tempSize = pq.size() * 2;
					if (tempSize > 100)
						tempSize = 100;
					for (int j = 1; j <= tempSize; j = j + 2) {
						Check check = pq.poll();
						A[i][j] = check.num;
						A[i][j+1] = check.cnt;
					}
					// 빈공간 0으로 채우기
					for (int j = tempSize + 1; j <= colSize; j++) {
						A[i][j] = 0;
					}
					// 크기가 100을 넘어가는 경우에는 나머지는 버림
					pq.clear();
					// 가장 큰 크기로 갱신
					if (colSize < tempSize)
						colSize = tempSize;
				}
			} else {	// C 연산
				for (int i = 1; i <= colSize; i++) {
					// (수, 수의 등장 횟수) 체크
					Arrays.fill(counting, 0);
					for (int j = 1; j <= rowSize; j++) {
						counting[A[j][i]]++;
					}
					// 정렬 순서 정하기
					for (int j = 1; j <= 100; j++) {
						if(counting[j] > 0)
							pq.offer(new Check(j, counting[j]));
					}
					// 정렬하기
					int tempSize = pq.size() * 2;
					if (tempSize > 100)
						tempSize = 100;
					for (int j = 1; j <= tempSize; j = j + 2) {
						Check check = pq.poll();
						A[j][i] = check.num;
						A[j+1][i] = check.cnt;
					}
					// 빈공간 0으로 채우기
					for (int j = tempSize + 1; j <= rowSize; j++) {
						A[j][i] = 0;
					}
					// 크기가 100을 넘어가는 경우에는 나머지는 버림
					pq.clear();
					// 가장 큰 크기로 갱신
					if (rowSize < tempSize)
						rowSize = tempSize;
				}
			}
			
			time++;
		}
		
		System.out.println(time);
	}// end of main

}
