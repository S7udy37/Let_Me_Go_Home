package com.acmicpc;
/**
 * 1. 인접행렬시 메모리 초과 발생
 * Memory : 55184KB
 * Time : 792ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
 class Main_1197_최소스패닝트리_오인호2 {
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int ans = 0;
		ArrayList<ArrayList<Pair1197v2>> l = new ArrayList<ArrayList<Pair1197v2>>();
		for(int i=0; i<V; i++) {
			l.add(new ArrayList<Pair1197v2>());
		}
		int[] dist = new int[V];
		boolean[] check = new boolean[V];
		Arrays.fill(dist, Integer.MAX_VALUE);
		for(int i=0; i<E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			l.get(a-1).add(new Pair1197v2(b-1, w));
			l.get(b-1).add(new Pair1197v2(a-1, w));
		}
		int min; 
		int index;
		dist[0] = 0;
		for(int i=0; i<V; i++) {
			min = Integer.MAX_VALUE;
			index = -1;
			for(int j=0; j<V; j++) {
				if(!check[j] && dist[j] < min) {
					min = dist[j];
					index = j;
				}
			}
			int size = l.get(index).size();
			for(int j=0; j<size; j++)
				if(!check[l.get(index).get(j).b] && l.get(index).get(j).w != 0 && dist[l.get(index).get(j).b] > l.get(index).get(j).w) 
					dist[l.get(index).get(j).b] = l.get(index).get(j).w;
			check[index] = true;
		}
		for(int i=0; i<V; i++) {
			ans += dist[i];
		}
		System.out.println(ans);
	}

}
class Pair1197v2 {
	int b, w;
	public Pair1197v2(int b, int w) {
		this.b = b; this.w = w;
	}
}
