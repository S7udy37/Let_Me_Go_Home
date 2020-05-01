/**
* Memory : 13228KB
* Time : 80ms
/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int dx[] = { -1, 0, 1, 0};
	static int dy[] = { 0, -1, 0, 1};
	static int N, M;
	static int[][] map;
	static int[] parents;
	
	private static int find(int a) {
		if(-1 == parents[a]) return a;
		else return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aa = find(a);
		int bb = find(b);
		if(aa == bb) {
			return false;
		} else {
			parents[bb] = aa;
			return true;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int cnt = 1;
		map = new int[N][M];
		List<ArrayList<Pair>> ll = new ArrayList<ArrayList<Pair>>();
		for(int i=0; i<6; i++) {
			ll.add(new ArrayList<>());
		}
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<Pair> q = new LinkedList<>();
		boolean[][] checked = new boolean[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 1 && !checked[i][j]) {
					q.add(new Pair(i, j));
					map[i][j] = cnt;
					ll.get(cnt-1).add(new Pair(i, j));
					checked[i][j] = true;
					while(!q.isEmpty()) {
						Pair t = q.poll();
						for(int k=0; k<4; k++) {
							int nx = t.x + dx[k];
							int ny = t.y + dy[k];
							if(0<=nx && nx<N && 0<=ny && ny<M) {
								if(map[nx][ny] == 1 && !checked[nx][ny]) {
									checked[nx][ny] = true;
									map[nx][ny] = cnt;
									q.add(new Pair(nx, ny));
									ll.get(cnt-1).add(new Pair(nx, ny));
								}
							}
						}
					}
					cnt++;
				}
			}
		}
		cnt--;
		int[][] b = new int[cnt][cnt];
		parents = new int[cnt];
		for(int i=0; i<cnt; i++) {
			parents[i] = -1;
		}
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for(int i=0; i<cnt; i++) {
			for(int j=0; j<ll.get(i).size(); j++) {
				q.add(new Pair(ll.get(i).get(j).x, ll.get(i).get(j).y, 0, 0));
				q.add(new Pair(ll.get(i).get(j).x, ll.get(i).get(j).y, 0, 1));
				q.add(new Pair(ll.get(i).get(j).x, ll.get(i).get(j).y, 0, 2));
				q.add(new Pair(ll.get(i).get(j).x, ll.get(i).get(j).y, 0, 3));
			}
			checked = new boolean[N][M];
			while(!q.isEmpty()) {
				Pair t = q.poll();
				for(int k=0; k<4; k++) {
					int nx = t.x + dx[k];
					int ny = t.y + dy[k];
					int d = t.d + 1;
					if(0<=nx && nx<N && 0<=ny && ny<M) {
						if(map[nx][ny] == 0 ) {
							if(!checked[nx][ny] && t.k == k) {
								checked[nx][ny] = true;
								q.add(new Pair(nx, ny, d, k));
							}
						} else {
							if(map[nx][ny] - 1 == i) continue;
							if(d <= 2) continue;
							if(map[nx][ny] != map[t.x][t.y] && t.k == k) {
								int goal = map[nx][ny]-1;
								if(b[i][goal] == 0) {
									b[i][goal] = b[goal][i] = d-1;
								} else {
									b[i][goal] = b[goal][i] = Math.min(b[i][goal], d-1);
								}
							}
						}
					}
				}
			}
		}
		for(int i=0; i<cnt; i++) {
			for (int j = 0; j < cnt; j++) {
				if(b[i][j] != 0) pq.add(new Pair(i, j, b[i][j]));
			}
		}
		int ans = 0;
		while(!pq.isEmpty()) {
			Pair t = pq.poll();
			if(union(t.x, t.y)) {
				ans += t.d;
			}
		}
		if(!check(cnt)) System.out.println(-1);
		else System.out.println(ans);
	}
	private static boolean check(int cnt) {
		int k = 0;
		for(int i=0; i<cnt; i++) {
			if(parents[i] == -1) k++;
		}
		if(k == 1) return true;
		return false;
	}
	public static class Pair implements Comparable<Pair>{
		int x, y, d, k;
		public Pair(int x, int y) {
			this.x = x; this.y = y;
		}
		public Pair(int x, int y, int d) {
			this.x = x; this.y = y; this.d = d;
		}
		public Pair(int x, int y, int d, int k) {
			this.x = x; this.y = y; this.d = d; this.k = k;
		}
		@Override
		public int compareTo(Pair o) {
			return this.d - o.d;
		}
		
	}
}
