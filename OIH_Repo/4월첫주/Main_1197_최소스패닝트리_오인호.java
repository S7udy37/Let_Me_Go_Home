/**
* Memory : 50476KB
* Time : 476ms
*/
package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197_최소스패닝트리_오인호 {
	private static int[] parents;
	private static int[] ranks;
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		else return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int ta = find(a);
		int tb = find(b);
		if(ta == tb) return false;
		else {
			if(ranks[ta] >= ranks[tb]) {
				ranks[ta] += ranks[tb];
				parents[tb] = ta;
				return true;
			} else {
				ranks[tb] += ranks[ta];
				parents[ta] = tb;
				return true;
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int ans = 0;
		parents = new int[V+1];
		ranks = new int[V+1];
		for(int i=1; i<=V; i++) {
			parents[i] = i;
			ranks[i] = 1;
		}
		PriorityQueue<Pair1197> pq = new PriorityQueue<>();
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.add(new Pair1197(a, b, w));
		}
		int cnt = 0;
		while(!pq.isEmpty()) {
			if(cnt == V-1) break;
			Pair1197 t = pq.poll();
			if(union(t.a,t.b)) {
				ans += t.w;
				cnt++;
			}
		}
		System.out.println(ans);
	}

}
class Pair1197 implements Comparable<Pair1197>{
	int a, b, w;
	public Pair1197(int a, int b, int w) {
		this.a = a; this.b = b; this.w = w;
	}
	@Override
	public int compareTo(Pair1197 o) {
		return Integer.compare(this.w, o.w);
	}
	
}
