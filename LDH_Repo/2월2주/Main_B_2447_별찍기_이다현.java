import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B_2447_별찍기_이다현 {

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		int M = 0;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 8; i++) {
			if (Math.pow(3, i) == N) {
				M = i;
			}
		}
		for (int i = 1; i <= Math.pow(3, M); i++) {
			sb.setLength(0);
			for (int j = 1; j <= Math.pow(3, M); j++) {
				if((730 <= i % 2187) && (i % 2187 < 1459)&&(730 <= j % 2187) && (j % 2187 < 1459)) {
					sb.append(" ");
				}
				else if((244 <= i % 729) && (i % 729 < 487)&&(244 <= j % 729) && (j % 729 < 487)) {
					sb.append(" ");
				}
				else if((82 <= i % 243) && (i % 243 < 163)&&(82<= j % 243) && (j % 243 < 163)) {
					sb.append(" ");
				}
				else if((28 <= i % 81) && (i % 81 < 55)&&(28 <= j % 81) && (j % 81 < 55)) {
					sb.append(" ");
				}
				else if((10 <= i % 27) && (i % 27 < 19)&&(10 <= j % 27) && (j % 27 < 19)) {
					sb.append(" ");
				}
				else if ((4 <= i % 9) && (i % 9 < 7) && (4 <= j % 9) && (j % 9 < 7)) {
					sb.append(" ");
				} else if (i % 3 == 2 && j % 3 == 2) {
					sb.append(" ");
				} else
					sb.append("*");
			}
			System.out.println(sb.toString());
		}

	}

}
