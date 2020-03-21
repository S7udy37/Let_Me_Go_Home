// Gold V - 1753 : 최단경로

/*
 * 117,500 kb
 * 1,000 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_1753_최단경로_박진 {

	static class Node implements Comparable<Node> {
		int index;
		int cost;
		public Node(int to, int cost) {
			this.index = to;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	
	static int V, E;
	static int K;
	static int u, v, w;
	static int[] memo;
	static ArrayList<Node>[] arrList;
	static PriorityQueue<Node> queue = new PriorityQueue<Node>();
	
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
			arrList[i] = new ArrayList<Node>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			arrList[u].add(new Node(v, w));
		}
		for (int i = 1; i < V+1; i++) {
			Collections.sort(arrList[i]);
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
				if (arrList[cur.index].get(i).index == K)
					continue;
				
				int tempTotal = cur.cost + arrList[cur.index].get(i).cost;
				if (memo[arrList[cur.index].get(i).index] < 0) {
					memo[arrList[cur.index].get(i).index] = tempTotal;
					queue.offer(new Node(arrList[cur.index].get(i).index, tempTotal));
				} else {
					if (memo[arrList[cur.index].get(i).index] > tempTotal) {
						memo[arrList[cur.index].get(i).index] = tempTotal;
						queue.offer(new Node(arrList[cur.index].get(i).index, tempTotal));
					}
				}
			}
		}
	}

}
