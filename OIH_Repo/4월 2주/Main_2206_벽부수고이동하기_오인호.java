package com.acmicpc;
/**
 * Memory : 180336KB
 * Time : 1372ms
 * BFS => 메모리 초과 => 방문한 배열이면 컨티뉴~
 * => 벽을 부수고 더 빠르게 왔는데 방문했다고 해서 컷당함 => 100% 정답 아님
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2206_벽부수고이동하기_오인호 {
	static int[] dx = { 1, -1, 0, 0};
	static int[] dy = { 0, 0, 1, -1};
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		int[][][] dis = new int[N][M][2];
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
		for(int i=0; i<N; i++) {
			for (int j = 0; j < M; j++) {
				for(int k=0; k<2; k++) {
					dis[i][j][k] = -1;
				}
			}
		}
		PriorityQueue<Pair2206> pq = new PriorityQueue<>();
		pq.add(new Pair2206(0, 0, 0, 1));
		int ans = -1;
		while(!pq.isEmpty()) {
			Pair2206 t = pq.poll();
			if(t.wall > 1) continue;
			if(dis[t.x][t.y][t.wall] == -1) dis[t.x][t.y][t.wall] = t.cnt;
			else continue;
			for(int k=0; k<4; k++) {
				int nx = t.x + dx[k];
				int ny = t.y + dy[k];
				if(0<=nx && nx<N && 0<=ny && ny<M) {
					if(map[nx][ny] == 1) {
						if(t.wall == 0) {
							pq.add(new Pair2206(nx, ny, t.wall+1, t.cnt+1));
						}
					} else {
						pq.add(new Pair2206(nx, ny, t.wall, t.cnt+1));
					}
				}
			}
		}
		if(dis[N-1][M-1][0] != -1 && dis[N-1][M-1][1] != -1) {
			ans = (int)Math.min(dis[N-1][M-1][0], dis[N-1][M-1][1]);
		} else if(dis[N-1][M-1][0] == -1 && dis[N-1][M-1][1] == -1){
			ans = -1;
		} else {
			ans = (int)Math.max(dis[N-1][M-1][0], dis[N-1][M-1][1]);
		}
		System.out.println(ans);
	}

}
class Pair2206 implements Comparable<Pair2206>{
	int x, y, wall, cnt;
	public Pair2206(int x, int y, int wall, int cnt) {
		this.x = x; this.y = y; this.wall = wall; this.cnt = cnt;
	}
	@Override
	public int compareTo(Pair2206 o) {
		int cnt = this.cnt - o.cnt;
		if(cnt == 0) return this.wall - o.wall;
		return cnt;
	}
	
}
