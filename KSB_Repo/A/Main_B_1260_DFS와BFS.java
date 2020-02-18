/**
* 19316 KB
* 228 ms
*
*/

package day01_0218;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main_B_1260_DFS와BFS {
	
	static int N, M, V;
	static boolean map[][];
	static boolean visited[];
	static ArrayList<Integer> bfs_ans = new ArrayList<Integer>();
	static ArrayList<Integer> dfs_ans = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// node
		M = Integer.parseInt(st.nextToken());	// edge
		V = Integer.parseInt(st.nextToken());	// start node
	
		map = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		
		int from, to;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			map[from][to] = true;
			map[to][from] = true;
		}
		
		Arrays.fill(visited, true);
		BFS(V);
		Arrays.fill(visited, true);
		DFS(V);
		
		StringBuilder sb = new StringBuilder();
		
		int len = dfs_ans.size();
		for(int i=0; i<len; i++)
			sb.append(dfs_ans.get(i)).append(' ');
		
		sb.append('\n');
		
		len = bfs_ans.size();
		for(int i=0; i<len; i++)
			sb.append(bfs_ans.get(i)).append(' ');
		
		System.out.println(sb.toString());
		
	}

	private static void DFS(int current) {
		// DFS
		visited[current] = false;
		dfs_ans.add(current);
		
		for(int i=1; i<N+1; i++) {
			if(map[current][i] && visited[i]) {
				DFS(i);
			}
		}
	}

	private static void BFS(int current) {
		// BFS
		LinkedList<Integer> queue = new LinkedList<Integer>();
		visited[current] = false; 
		queue.offer(current);
		
		int ad;
		while(!queue.isEmpty()) {
			ad = queue.poll();
			bfs_ans.add(ad);
			
			for(int i=1; i<N+1; i++) {
				if(map[ad][i] && visited[i]) {
					visited[i] = false;
					queue.offer(i);
				}
			}			
		}
	}
}
