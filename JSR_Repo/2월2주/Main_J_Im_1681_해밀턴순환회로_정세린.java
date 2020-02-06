import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_Im_1681_해밀턴순환회로_정세린 {
	static int[][] matrix;
	static boolean[] visited;
	static int N;
	static int min;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		matrix = new int[N][N];
		visited = new boolean[N];
		min = 100 * N;
		// 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 출발지 0
		dfs(0, 0, 0);
		System.out.println(min);
	}

	private static void dfs(int v, int sum, int index) {
		visited[v] = true;
		if (index == N - 1) {
			// 회사로 돌아가는 길
			if (matrix[v][0] == 0)
				return;
			min = (sum + matrix[v][0] < min) ? sum + matrix[v][0] : min;
			return;
		}

		for (int i = 1; i < N; i++) {
			if (visited[i] == false && matrix[v][i] != 0) {
				dfs(i, sum + matrix[v][i], index + 1);
				visited[i] = false;
			}
		}
	}

}
