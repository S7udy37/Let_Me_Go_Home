/**
* 5%에서 시간초과 
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
	int v, w;
	Point(int v, int w){
		this.v=v; this.w=w;
	}
	
	@Override
	public int compareTo(Point o) {
		return this.w-o.w;
	}
	
}

public class Main_B_1753_최단경로 {
	
	static int V, E, K;
	static ArrayList<Point>[] map;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[E+1];
		for(int i=0; i<E+1; i++) {
			map[i] = new ArrayList<Point>();
		}
		visited = new boolean[V+1];
		
		K = Integer.parseInt(br.readLine());
		
		int v1, v2, w;
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			v1 = Integer.parseInt(st.nextToken());
			v2 = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
				
			map[v1].add(new Point(v2, w));
		}
		
		for(int i=1; i<V+1; i++) {
			Collections.sort(map[i]);
		}
		
		for(int i=1; i<V+1; i++) {
			getWeight(K, i);
		}
		
		System.out.println(sb.toString());
		
	}

	
	private static void getWeight(int start, int to) {		
		if(start==to) {
			sb.append(0).append('\n');
			return;
		}
		
		Arrays.fill(visited, true);	// visited initialization		
		PriorityQueue<Point> queue = new PriorityQueue<Point>();	// create stack for DFS
		queue.offer(new Point(start, 0));
		visited[start]=false;
		
		Point current; int from, score, min=Integer.MAX_VALUE;
		while(!queue.isEmpty()) {
			current = queue.poll();
			from = current.v;
			score = current.w;
			
			if(from==to) {
				min=score;
				break;
			}
			
			int len= map[from].size();
			for(int i=0; i<len; i++) {
				if(visited[map[from].get(i).v]) {
					queue.offer(new Point(map[from].get(i).v, map[from].get(i).w+score));
					visited[map[from].get(i).v] = false;
				}
			}
			
		}
		
		if(visited[to]) {
			sb.append("INF").append('\n');
		}else {
			sb.append(min).append('\n');
		}
		
	}
	
}
