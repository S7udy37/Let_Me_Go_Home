package 알고리즘스터디_2월첫주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_J_1520_계단오르기_이다현 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int[] arrN = new int[N];
		for (int i = 0; i < N; i++) {
			arrN[i] = Integer.parseInt(in.readLine());
		}
		int M1 = 0;
		int M2 = arrN[0];
		int M3 = arrN[1];
		int M4 = arrN[0]+arrN[1];
		int Max;
		for (int i = 2; i < N; i++) {
			int tr1 = M1;
			int tr2 = M2;
			M1 = M3;
			M2 = M4;
			M4 = M3+arrN[i];
			if(tr1<tr2) {
				M3 = tr2 + arrN[i];
			}else {
				M3 = tr1 + arrN[i];
			}
		}
		if(M3<M4) {
			Max = M4;
		}else {
			Max = M3;
		}
		System.out.println(Max);
	}

}
