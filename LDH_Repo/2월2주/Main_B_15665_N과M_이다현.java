import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_15665_N과M_이다현 {
	static int M,N;
	static int[] arr;
	static int[] arrF;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arrF = new int[N];
		arr = new int[M];
		st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arrF[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arrF);
		sb= new StringBuilder();
		recursion(0);
	}
	
	static void recursion(int index) {
		if (index == M) {
			sb.setLength(0);
			for (int i = 0; i < M; i++) {
				sb.append(arr[i]).append(" ");
			}
			System.out.println(sb.toString());
			return;
		}

		for (int i = 0; i < N; i++) {
			if(arr[index]==arrF[i]) {
				continue;
			}
			arr[index] = arrF[i];
			recursion(index+1);
		}

	}
	
}
