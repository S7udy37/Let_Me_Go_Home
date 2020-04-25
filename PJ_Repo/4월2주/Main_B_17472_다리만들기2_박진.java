// Gold III - 17472 : 다리 만들기 2

/*
 * BFS & MST : Kruskal (Union-Find)
 * 13,148 kb, 76 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_17472_다리만들기2_박진 {

	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int start, end, length;	// 시작 섬, 도착 섬, 다리 길이
		public Edge(int start, int end, int length) {
			this.start = start;
			this.end = end;
			this.length = length;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.length, o.length);
		}
		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", length=" + length + "]";
		}
	}
	
	static int N, M;
	static int[][] map;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static int islandNum = 1;	// 섬 번호
	static Queue<Point> queue = new LinkedList<Point>();	// BFS를 위한 큐
	static PriorityQueue<Edge> pq = new PriorityQueue<Edge>();	// Kruskal을 위한 우선순위 큐
	static boolean[][] visit;
	
	static int[] parent = new int[7];	// union-find
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬마다 번호 붙이기 (BFS)
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1 && !visit[i][j]) {
					map[i][j] = islandNum;
					queue.offer(new Point(i, j));
					visit[i][j] = true;
					while(!queue.isEmpty()) {
						Point point = queue.poll();
						
						for (int d = 0; d < 4; d++) {
							int nexti = point.i + di[d];
							int nextj = point.j + dj[d];
							
							if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
								continue;
							if (visit[nexti][nextj])
								continue;
							if (map[nexti][nextj] == 0)
								continue;
							
							map[nexti][nextj] = islandNum;
							queue.offer(new Point(nexti, nextj));
							visit[nexti][nextj] = true;
						}
					}
					islandNum++;
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		// 모든 다리(edge) 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0) {
					int start = map[i][j];	// 시작섬
					for (int d = 0; d < 4; d++) {
						int cnt = 0;	// 시작섬과 도착섬 사이의 거리 (다리의 길이)
						int nexti = i;
						int nextj = j;
						while(true) {
							nexti = nexti + di[d];
							nextj = nextj + dj[d];
							cnt++;
							
							if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
								break;
							if (map[nexti][nextj] == map[i][j])
								break;
							
							if (map[nexti][nextj] > 0) {
								int end = map[nexti][nextj];
								if (cnt - 1 > 1) {
//									System.out.println(start + " /  " + end + " / " + (cnt - 1));
									pq.offer(new Edge(start, end, cnt - 1));
								}
								break;
							}
						}
						
					}
				}
			}
		}
		
		int cntEdge = 0;
		
//		while(!pq.isEmpty()) {
//			System.out.println(pq.poll());
//		}
		
		makeSet();
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			
			if(union(edge.start, edge.end)) {
				cntEdge++;
				result += edge.length;
			}
			
			if (cntEdge == islandNum-2)
				break;
		}
		
		System.out.println(cntEdge == islandNum-2 ? result : -1);
		
	}// end of main

	private static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot != bRoot) {
			parent[aRoot] = bRoot;
			return true;
		}
		
		return false;
	}

	private static int findSet(int a) {
		if (parent[a] < 0)
			return a;
		return parent[a] = findSet(parent[a]);
	}

	private static void makeSet() {
		Arrays.fill(parent, -1);
	}

}
