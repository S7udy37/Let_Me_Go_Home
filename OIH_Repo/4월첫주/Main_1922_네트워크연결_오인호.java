/**
* Memory : 46552KB
* Time : 360ms
*/

package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1922_네트워크연결_오인호 {
	private static int[] parents;
	
	private static int find(int a) {
		if(parents[a] == a) return a;
		else {
			return parents[a] = find(parents[a]);
		}
	}
	
	private static boolean union(int a, int b) {
		int ta = find(a);
		int tb = find(b);
		if(ta == tb) {
			return false;
		} else {
			parents[tb] = ta;
			return true;
		}
	}
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine().trim());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Kruskal> pq = new PriorityQueue<>();
		parents = new int[N+1];
		for(int i=1; i<N+1; i++) parents[i]=i;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.add(new Kruskal(a, b, w));
		}
		int ans = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			if(cnt == N-1) break;
			Kruskal kruskal = pq.poll();
			if(union(kruskal.first,kruskal.second)) {
				ans += kruskal.weight;
				cnt++;
			}
		}
		System.out.println(ans);
	}

}
class Kruskal implements Comparable<Kruskal>{
	int first,second,weight;
	public Kruskal(int first, int second, int weight) {
		this.first = first; this.second = second; this.weight = weight;
	}
	@Override
	public int compareTo(Kruskal o) {
		return this.weight - o.weight;
	}
	
}
