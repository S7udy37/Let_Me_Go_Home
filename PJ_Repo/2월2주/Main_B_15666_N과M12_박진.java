// Silver II - 15666 : N과 M (12)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_15666_N과M12_박진 {

	static int N, M;
	static int[] num_temp;	// N개의 숫자를 입력받을 배열
	static int[] num;		// N개의 숫자 중에서 중복된 숫자들을 제거한 후에 저장할 배열
	static int[] result;
	static int indexForNum = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		num_temp = new int[N];
		num = new int[N];
		result = new int[M];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			num_temp[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(num_temp);
		
		num[0] = num_temp[0];
		for (int i = 1; i < N; i++) {
			if (num[indexForNum] != num_temp[i]) {
				indexForNum++;
				num[indexForNum] = num_temp[i];
			}
		}
		
		permutation(0);
	}

	private static void permutation(int index) {
		StringBuilder sb = new StringBuilder();
		if (index == M) {
			for (int i = 0; i < M; i++) {
				if (i == 0) {
					sb.append(result[i]);
					sb.append(" ");
				}
				else {
					if (result[i] >= result[i-1]) {
						sb.append(result[i]);
						sb.append(" ");
					} else {
						return;
					}
				}
			}
			System.out.println(sb);
			return;
		}
		
		for (int i = 0; i <= indexForNum; i++) {
			result[index] = num[i];
			permutation(index + 1);
		}
	}
}
