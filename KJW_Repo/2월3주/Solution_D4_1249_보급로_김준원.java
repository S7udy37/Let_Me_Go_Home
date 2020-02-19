package S7udy;
/*
 * 89,208 kb
 * 334 ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D4_1249_보급로_김준원 {
	static class node {
		int i;
		int j;
		int time;
		node next;

		private node(int i, int j, int time) {
			this.i = i;
			this.j = j;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder txt = new StringBuilder();
		int tc = Integer.parseInt(in.readLine());
		int a[][] = new int[100][100];
		int b[][] = new int[100][100];
		int intmax = (1 << 31) - 1;
		for (int t = 1; t <= tc; t++) {
			int n = Integer.parseInt(in.readLine());
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					a[i][j] = in.read() - '0';
					b[i][j] = intmax;
				}
				in.readLine();
			}
			node r, f;
			r = f = new node(0, 0, 0);
			b[0][0] = 0;
			for (;;) {
				node crnt = r;
				r = r.next;
				int i = crnt.i;
				int j = crnt.j;
				int time = crnt.time;
				if (i - 1 >= 0 && b[i - 1][j] > a[i - 1][j] + time) {
					b[i - 1][j] = a[i - 1][j] + time;
					if (r == null)
						r = f = new node(i - 1, j, b[i - 1][j]);
					else
						f = f.next = new node(i - 1, j, b[i - 1][j]);
				}
				if (i + 1 < n && b[i + 1][j] > a[i + 1][j] + time) {
					b[i + 1][j] = a[i + 1][j] + time;
					if (r == null)
						r = f = new node(i + 1, j, b[i + 1][j]);
					else
						f = f.next = new node(i + 1, j, b[i + 1][j]);
				}
				if (j - 1 >= 0 && b[i][j - 1] > a[i][j - 1] + time) {
					b[i][j - 1] = a[i][j - 1] + time;
					if (r == null)
						r = f = new node(i, j - 1, b[i][j - 1]);
					else
						f = f.next = new node(i, j - 1, b[i][j - 1]);
				}
				if (j + 1 < n && b[i][j + 1] > a[i][j + 1] + time) {
					b[i][j + 1] = a[i][j + 1] + time;
					if (r == null)
						r = f = new node(i, j + 1, b[i][j + 1]);
					else
						f = f.next = new node(i, j + 1, b[i][j + 1]);
				}
				if (r == null)
					break;
			}
			txt.append('#').append(t).append(' ').append(b[n - 1][n - 1]).append('\n');
		}
		System.out.print(txt);
	}
}