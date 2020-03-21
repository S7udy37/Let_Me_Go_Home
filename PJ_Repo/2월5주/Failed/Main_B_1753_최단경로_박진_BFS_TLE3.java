// Gold V - 1753 : 최단경로

/*
 * 시간 초과
 */

import java.io.*;
import java.util.*;

public class Main_B_1753_최단경로_박진_BFS_TLE3 {

	static class Node {
		int index;
		int total;
		public Node(int to, int total) {
			this.index = to;
			this.total = total;
		}
	}
	
	static int V, E;
	static int K;
	static int u, v, w;
	static int[] memo;
	static ArrayList<int[]>[] arrList;
	static Queue<Node> queue = new LinkedList<Node>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		memo = new int[V+1];
		Arrays.fill(memo, -1);	// 메모값 초기화
		arrList = new ArrayList[V+1];
		for (int i = 1; i < V+1; i++) {
			arrList[i] = new ArrayList<int[]>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			int[] temp = new int[]{v,w};
			arrList[u].add(temp);
		}
		
		memo[K] = 0;
		bfs(new Node(K, 0));
		
		for (int i = 1; i <= V; i++) {
			if (memo[i] > -1) {
				sb.append(memo[i]).append("\n");
			} else {
				sb.append("INF").append("\n");
			}
		}
		
		System.out.println(sb);
	}// end main

	private static void bfs(Node node) {
		queue.offer(node);
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			int size = arrList[cur.index].size();
			for (int i = 0; i < size; i++) {
				if (arrList[cur.index].get(i)[0] == K)
					continue;
				
				int tempTotal = cur.total + arrList[cur.index].get(i)[1];
				if (memo[arrList[cur.index].get(i)[0]] < 0) {
					memo[arrList[cur.index].get(i)[0]] = tempTotal;
					queue.offer(new Node(arrList[cur.index].get(i)[0], tempTotal));
				} else {
					if (memo[arrList[cur.index].get(i)[0]] > tempTotal) {
						memo[arrList[cur.index].get(i)[0]] = tempTotal;
						queue.offer(new Node(arrList[cur.index].get(i)[0], tempTotal));
					}
				}
			}
		}
	}

}
