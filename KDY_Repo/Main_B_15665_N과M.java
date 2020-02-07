package algo.baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_15665_N과M{
	static int[] num;
	static int n;
	static int m;
	static int[] result;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		num = new int[n];
		result = new int[m];

		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}		
		Arrays.sort(num);
		
		maker(0);
		System.out.println(sb);
	}

	private static void maker(int index) {
		
		if(index==m) {
			for(int j=0; j<m;j++) {
				sb.append(result[j]).append(" ");
			}sb.append('\n');
			return;
		}
		
		for(int i=0; i<n; i++) {
			if(i>0&&num[i]==num[i-1]) {
			continue;
			}
			result[index]=num[i];
			maker(index+1);
		}		
	}
}
