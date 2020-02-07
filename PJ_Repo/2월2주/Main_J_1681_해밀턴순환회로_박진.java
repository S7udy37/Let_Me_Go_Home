// Intermediate Coder - 1681 : 해밀턴 순환회로

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_1681_해밀턴순환회로_박진 {

	static int N;				// 배달해야 하는 장소의 수
	static int[][] adjArr;		// 인접행렬
	static boolean[] isVisited;
	static int minCost = Integer.MAX_VALUE;	// 모든 장소를 한 번씩 들러 물건을 배달하고 회사에 돌아오기 위한 최소의 비용
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adjArr = new int[N][N];
		isVisited = new boolean[N];
		
		// 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				adjArr[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(adjArr[i][j] + " ");
			}
//			System.out.println();
		}
		
		dfs(0, 0, 0);
		
		System.out.println(minCost);
	}
	
	private static void dfs(int current, int numOfVisit, int cost) {
		
		if (numOfVisit >= N-1) {
			if (adjArr[current][0] > 0 && minCost > cost + adjArr[current][0]) {
				minCost = cost + adjArr[current][0];
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (adjArr[current][i] > 0 && isVisited[i] == false) {
				isVisited[current] = true;
				dfs(i, numOfVisit + 1, cost + adjArr[current][i]);
				isVisited[current] = false;
			}
		}
	}
}
