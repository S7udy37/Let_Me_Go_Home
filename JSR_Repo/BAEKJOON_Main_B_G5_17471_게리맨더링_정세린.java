/*13524KB
 * 80ms*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B_G5_17471_게리맨더링_정세린 {
	static boolean[][] adj;
	static int[] num;
	static boolean[] visited;
	static int N, total = 0;
	static int[] region;
	static Stack<Integer> s = new Stack<Integer>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int diff, min, subsum = 0, subsum2;
		N = Integer.parseInt(br.readLine());
		adj = new boolean[N + 1][N + 1];
		num = new int[N + 1];
		region = new int[N + 1];
		visited = new boolean[N + 1];
		min = 100 * N;
		int reg0 = 0, reg1 = 0;
		st = new StringTokenizer(br.readLine());

		// 인구수 입력 및 총합
		for (int n = 1; n <= N; n++) {
			num[n] = Integer.parseInt(st.nextToken());
			total += num[n];
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int jj = Integer.parseInt(st.nextToken());
			for (int j = 0; j < jj; j++) {
				int jtmp = Integer.parseInt(st.nextToken());
				adj[i][jtmp] = true;
			}
		}

		L: for (int i = 1; i < (1 << (N - 1)); i++) {
			
			// 새로 조합 만들면 visited, subsum초기화
			subsum = 0;
			Arrays.fill(visited, false);

			for (int r = 1; r < N + 1; r++) {
				region[r] = (i >> (r - 1)) % 2;
				if (region[r] == 0) reg0 = r;
				else if (region[r] == 1) reg1 = r;
			}
			
			// 각 구역 탐색
			dfs(reg0, 0);
			dfs(reg1, 1);

			// visit확인 미방문 발견되면 다시 조합 만듦
			for (int j = 1; j < N + 1; j++) {
				if (!visited[j]) {
					continue L;
				}
			}

			// 구역 나누기 성공하면 각 구역합 구함
			for (int k = 1; k < N + 1; k++) {
				if (region[k] == 0) {
					subsum += num[k];
				}
			}
			subsum2 = total - subsum;

			// 구역 차이 구함
			diff = Math.abs(subsum - subsum2);
			min = Math.min(min, diff);

		}
		// 구역 나누기 실패하면 -1
		if (min == 100 * N) min = -1;
		System.out.println(min);
	}

	private static void dfs(int v, int reg) {
		visited[v] = true;

		for (int i = 1; i < N + 1; i++) {
			if (!visited[i] && region[i] == reg && adj[v][i]) {
				dfs(i, reg);
			}
		}
	}

}
