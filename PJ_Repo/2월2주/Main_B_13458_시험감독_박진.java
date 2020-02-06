// Bronze II - 13458 : 시험 감독

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_13458_시험감독_박진 {

	static int N;
	static long[] numOfPeople;
	static long supervisor1, supervisor2;
	static long result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());	// 시험장의 수
		numOfPeople = new long[N];				// 각 시험장의 응시자의 수를 저장할 배열
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		for (int i = 0; i < N; i++) {
			numOfPeople[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		supervisor1 = Integer.parseInt(st.nextToken());	// 총감독관은 한 방에서 감시할 수 있는 응시자의 수
		supervisor2 = Integer.parseInt(st.nextToken());	// 부감독관은 한 방에서 감시할 수 있는 응시자의 수
		
		result = 0;
		for (int i = 0; i < N; i++) {
			// 총감독수 증가
			numOfPeople[i] = numOfPeople[i] - supervisor1;
			result++;
//			System.out.print(numOfPeople[i] + " ");
			// 부감독관 증가
			if (numOfPeople[i] > 0) {
				result = result + (numOfPeople[i] / supervisor2);
				if (numOfPeople[i] % supervisor2 != 0) {
					result++;
				}
			}
			
		}
		
		System.out.print(result);
	}
}
