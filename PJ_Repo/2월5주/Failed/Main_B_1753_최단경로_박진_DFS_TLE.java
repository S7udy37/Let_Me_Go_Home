// Gold V - 1753 : 최단경로

/*
 * 시간 초과
 */

import java.io.*;
import java.util.*;

public class Main_B_1753_최단경로_박진_DFS_TLE {

	static class Node {
		int to;
		int cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	
	static int V, E;
	static int K;
	static int u, v, w;
	static int[] result;
	static boolean[] isVisited;
	static ArrayList<Node>[] arrList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		result = new int[V+1];
		isVisited = new boolean[V+1];
		Arrays.fill(result, -1);	// 메모값 초기화
		arrList = new ArrayList[V+1];
		for (int i = 1; i < V+1; i++) {
			arrList[i] = new ArrayList<Node>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			arrList[u].add(new Node(v, w));
		}
		
//		for (int i = 1; i <= V; i++) {
//			for (int j = 0; j < arrList[i].size(); j++) {
//				System.out.print(arrList[i].get(j).to + "," + arrList[i].get(j).cost + "\t");
//			}
//			System.out.println();
//		}
		
		dfs(K, 0);
		
		for (int i = 1; i <= V; i++) {
			if (result[i] > -1) {
				sb.append(result[i]).append("\n");
			} else {
				sb.append("INF").append("\n");
			}
		}
		
		System.out.println(sb);
	}// end main

	private static void dfs(int cur, int total) {
		if (result[cur] == -1) {
			result[cur] = total;
		}
		
		if (total < result[cur]) {
			result[cur] = total;
		}
		
		for (int i = 0; i < arrList[cur].size(); i++) {
			if (isVisited[arrList[cur].get(i).to])
				continue;
			
			isVisited[cur] = true;
			dfs(arrList[cur].get(i).to, total + arrList[cur].get(i).cost);
			isVisited[cur] = false;
		}
	}

}
