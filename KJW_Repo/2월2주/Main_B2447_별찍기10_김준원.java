package S7udy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B2447_별찍기10_김준원 {

	private static boolean[][] a;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		StringBuilder txt=new StringBuilder();
		a = new boolean[n][n];
		a[0][0] = true;
		for (int i = 1; i < n; i *= 3) {
			copy(0,1,i);
			copy(0,2,i);
			copy(1,0,i);
			copy(1,2,i);
			copy(2,0,i);
			copy(2,1,i);
			copy(2,2,i);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++)
				txt.append(a[i][j] ? '*' : ' ');
			txt.append('\n');
		}
		System.out.print(txt);
		in.close();
	}

	static void copy(int ci,int cj,int k) {
		for (int i = 0; i < k; i++)
			for (int j = 0; j < k; j++)
				a[i+ci*k][j+cj*k]=a[i][j];
	}
}