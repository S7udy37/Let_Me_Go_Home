// Silver I - 2447 : 별 찍기 - 10

import java.util.Arrays;
import java.util.Scanner;

public class Main_B_2447_별찍기10_박진 {

	static int N;
	static char[][] arr;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new char[N][N];
		
		// 배열을 *으로 모두 채움
		for (int i = 0; i < N; i++) {
			Arrays.fill(arr[i], '*');
		}
		
		// 알고리즘
		star(N);
		
		// 배열 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.print(sb);
		
		sc.close();
	}

	private static void star(int n) {

		while (true) {
			n = n/3;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if ((i / n % 3 == 1) && (j / n % 3 == 1)) {
						arr[i][j] = ' ';
					}
				}
			}
			
			if (n == 1)
				break;
		}
	}
}

/* for문 횟수 조금이라도 줄이려면 이렇게 줄일 수 있음

for (int i = n; i < N-n; i++) {
	for (int j = n; j < N-n; j++) {
		if ((i / n % 3 == 1) && (j / n % 3 == 1)) {
			arr[i][j] = ' ';
		}
	}
}
				
*/