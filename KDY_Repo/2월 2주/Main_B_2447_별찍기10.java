package study_0210;

import java.util.Scanner;

public class Main_B_2447_별찍기10 {
	public static void main(String[] args) {
		StringBuilder sb= new StringBuilder();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int a = i;
				int b = j;

				while (true) {
					if (a % 3 == 1 && b % 3 == 1) {
						sb.append(" ");
						break;
					} else {
						if(a<2&&b<2) {
							sb.append("*");
							break;
						}
						a/=3;b/=3;						
					}
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
