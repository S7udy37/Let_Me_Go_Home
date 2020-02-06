import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class IM_1077_배낭채우기_김대용 {
	static int n; // 보석의 총 개수
	static int w; // 최대무게
	static int[] wi; // 보석무게
	static int[] pi; // 보석가격
	static int best_price;
	static int[] dp; // 최소무게 최대가격배열

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("1077_ex.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		wi = new int[n];
		pi = new int[n];
		dp = new int[w+1];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			wi[i] = Integer.parseInt(st.nextToken());
			pi[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) { //보석 종류별로 더하기
			for (int j = wi[i]; j <= w; j++) {//최소무게 최대가격 저장배열 생성
				dp[j]=Math.max(dp[j], dp[j-wi[i]]+pi[i]);
				//무게가 j일때 최대가격 vs j에서 현재 보석의 무게를 뺸 무게에 내 보석의 값어치를 +
			}
		}
		System.out.println(dp[w]);
	}

}
