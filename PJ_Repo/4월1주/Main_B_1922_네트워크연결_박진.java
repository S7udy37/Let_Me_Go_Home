// Gold IV - 1922 : 네트워크 연결

/*
 * MST : Kruskal 알고리즘
 * 48,828 kb
 * 452 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_1922_네트워크연결_박진 {

	static int N;
	static int M;
	static int[][] E;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		E = new int[M][3];
		parents = new int[N+1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			E[i][0] = Integer.parseInt(st.nextToken());
			E[i][1] = Integer.parseInt(st.nextToken());
			E[i][2] = Integer.parseInt(st.nextToken());
		}// end input
		
		// Kruskal 알고리즘
		Arrays.sort(E, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		makeSet();
		int cnt = 0;
		int result = 0;
		for (int i = 0; i < M; i++) {
			if (union(E[i][0], E[i][1])) {
				cnt++;
				result += E[i][2];
			}
			
			if (cnt == N - 1)
				break;
		}
		
		System.out.println(result);
	}// end of main
	
	public static void makeSet() {
		Arrays.fill(parents, -1);
	}
	
	public static int findSet(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot != bRoot) {
			parents[aRoot] = bRoot;
			return true;
		}
		return false;
	}
}
