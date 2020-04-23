// 17208 kb
// 392 ms
public class Main_B16234_인구이동_김준원 {
	public static void main(String[] args) throws Exception {
		int n, l, r, t, m;
		n = m = l = r = 0;
		for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
			n = n * 10 + t - '0';
		for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
			l = l * 10 + t - '0';
		for (t = System.in.read(); t != '\n'; t = System.in.read())
			if ('0' <= t && t <= '9')
				r = r * 10 + t - '0';
		m = n * n;
		int pop[] = new int[m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (t = System.in.read(); '0' <= t && t <= '9'; t = System.in.read())
					pop[i * n + j] = pop[i * n + j] * 10 + t - '0';
			}
			if (t == '\r')
				System.in.read();
		}
		int cnt = 0;
		int union[] = new int[m];
		int sum[] = new int[m];
		int num[] = new int[m];
		for (int x, i, j;;) {
			for (i = 0; i < m; i++) {
				union[i] = i;
				sum[i] = num[i] = 0;
			}
			boolean sw = false;
			for (i = 0; i < n; i++) {
				for (j = 0; j < n; j++) {
					if (j + 1 < n) {
						int tmp = pop[i * n + j] - pop[i * n + j + 1];
						if (tmp < 0)
							tmp *= -1;
						if (l <= tmp && tmp <= r) {
							sw = true;
							int to = union[i * n + j];
							int from = union[i * n + j + 1];
							for (x = 0; x < m; x++)
								if (union[x] == from)
									union[x] = to;
						}
					}
					if (i + 1 < n) {
						int tmp = pop[i * n + j] - pop[(i + 1) * n + j];
						if (tmp < 0)
							tmp *= -1;
						if (l <= tmp && tmp <= r) {
							sw = true;
							int to = union[i * n + j];
							int from = union[(i + 1) * n + j];
							for (x = 0; x < m; x++)
								if (union[x] == from)
									union[x] = to;
						}
					}
				}
			}
			if (sw) {
				cnt++;
				for (x = 0; x < m; x++) {
					num[union[x]]++;
					sum[union[x]] += pop[x];
				}
				for (x = 0; x < m; x++)
					pop[x] = sum[union[x]] / num[union[x]];
			} else
				break;
		}
		System.out.println(cnt);
	}
}
