// Intermediate Coder - 1809 : 탑

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_1809_탑_박진_TLE {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// N: 탑의 개수
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] tower = new int[N+1];	// tower[]: N개의 탑들의 높이를 저장할 배열 (첫번째 타워의 index는 1, 마지막 타워의 index는 N)
		tower[0] = 100000000;	// 레이저 신호를 수신하는 탑이 존재하지 않으면 0을 출력하게 하기 위해서
		for (int i = 1; i <= N; i++) {
			tower[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = i-1; j >= 0; j--) {
				if (tower[j] >= tower[i]) {
					System.out.print(j + " ");
					break;
				}
			}
		}
	}
}

/*
Time Limit Exceed(60)
time_space_table:
  d1.in : mem=7080k time=109ms
  d2.in : mem=7080k time=132ms
  d3.in : mem=8212k time=160ms
  d4.in : mem=23040k time=819ms
  d5.in : mem=23040k time=1621ms
  d6.in : mem=23040k time=2582ms
  d7.in : mem=24200k time=3923ms
  d8.in : mem=24200k time=3811ms
  d9.in : mem=24200k time=3735ms
 d10.in : mem=24200k time=3267ms
*/
