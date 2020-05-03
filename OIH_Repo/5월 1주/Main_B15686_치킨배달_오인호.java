/**
* Memory : 78496KB
* Time : 880ms
/
package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_B15686_치킨배달_오인호 {
	static int[][] map;
	static int N, M, size, size2, ans;
	static int[] check;
	static boolean[] home;
	static List<House> house, shop;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		// 집 저장
		house = new ArrayList<>();
		// 치킨 저장
		shop = new ArrayList<>();
		int cnt = 0;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) house.add(new House(i, j,cnt++));
				else if(map[i][j] == 2) shop.add(new House(i, j));
			}
		}
		// 치킨 집 배열 체크
		check = new int[M];
		size = shop.size();
		size2 = house.size();
		ans = Integer.MAX_VALUE;
		combi(0, 0);
		System.out.println(ans);
		
	}
	private static void combi(int index, int selected) {
		if(selected != M && index >= size) return;
		if(selected == M) {
			PriorityQueue<House> pq = new PriorityQueue<>();
			for(int i=0; i<M; i++) {
				for (int j = 0; j < size2 ; j++) {
					pq.add(new House(Math.abs(shop.get(check[i]).x - house.get(j).x), Math.abs(shop.get(check[i]).y - house.get(j).y), house.get(j).ho));
				}
			}
			int temp = get(pq);
			ans = Math.min(ans,temp);
			return;
		}
		check[selected] = index;
		combi(index+1, selected+1);
		combi(index+1, selected);
	}
	private static int get(PriorityQueue<House> pq) {
		int temp = 0;
		home = new boolean[size2];
		while(!pq.isEmpty()) {
			House t = pq.poll();
			if(home[t.ho]) continue;
			home[t.ho] = true;
			temp += (t.x + t.y);
		}
		return temp;
	}
	private static class House implements Comparable<House>{
		int x, y, ho;
		public House(int x, int y, int ho) {
			this.x = x; this.y = y; this.ho = ho;
		}
		public House(int x, int y) {
			this.x = x; this.y = y;
		}
		@Override
		public int compareTo(House o) {
			return (this.x + this.y) - (o.x + o.y);
		}
		
	}
}
