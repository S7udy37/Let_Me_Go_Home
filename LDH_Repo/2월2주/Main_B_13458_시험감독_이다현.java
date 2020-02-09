import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_13458_시험감독_이다현 {

	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arr= new int[N];
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long sum =0;
		for (int i = 0; i < N; i++) {
			arr[i] = arr[i]-B;
			if(arr[i]>0) {
				if(arr[i]%C == 0) {
					sum = sum+arr[i]/C+1;
				}else {
					sum = sum+arr[i]/C+2;
				}
			}else {
				sum++;
			}
			
		}
		System.out.println(sum);
	}
}
