package 알고리즘스터디_2월_3주차;

import java.util.Scanner;

public class Main_B_별찍기18_이다현 {
	static int N;
	static int I;
	static int J;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		I = (int) Math.pow(2, N) - 1;
		J = (int) Math.pow(2, N + 1) - 3;
		for (int i = 0; i < I; i++) {
			for (int j = 0; j < J; j++) {
				drawTriangle(i, j, 0, 0,N);
			}
			System.out.println();
		}
	}

	static void drawTriangle(int i, int j, int n, int m, int N) {
		
		int newI = I + n;
		int newJ = J + m;
		if (N % 2 == 1) {
			if (n <= i && i < newI + n) {
				if (m <= j && j < newI - n - m) {
					if (i == newI - 1) {
						System.out.print("*");
					} else if (i + j == I + m - 1) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				if (newI - n + m <= j && j < newJ) {
					if (i == newI - 1) {
						System.out.print("*");
					} else if (i - n + newI - n - 1 == j - m) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
			}
		} else {
			if(n<=i&&i<newI +n){
				if(m<=j&&j<newI-n+m) {
					if (i == n) {
						System.out.print("*");
					} else if (i - n == j - m) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
				if(newI-n+m<=j&&j<newJ) {
					if (i == n) {
						System.out.print("*");
					} else if (i - n + j == newJ - 1) {
						System.out.print("*");
					} else {
						System.out.print(" ");
					}
				}
			}
		}
	}
}
