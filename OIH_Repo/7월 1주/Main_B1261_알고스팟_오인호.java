import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] map;
	static int[][] value;
	static int dx[] = { 1, -1, 0, 0};
	static int dy[] = { 0, 0, 1, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		value = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				value[i][j] = -1;
			}
		}
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] =  temp.charAt(j) - '0';
			}
		}
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(0,0,0));
		value[0][0] = 0;
		while(!q.isEmpty()) {
			Pair tt = q.poll();
			for(int k=0; k<4; k++) {
				int tx = tt.x + dx[k];
				int ty = tt.y + dy[k];
				int tcnt = tt.cnt;
				if( tx < 0 || tx >= N || ty < 0 || ty >= M) continue;
				if(map[tx][ty] == 0) {
					if(value[tx][ty] == -1) {
						value[tx][ty] = tcnt;
						q.add(new Pair(tx, ty, tcnt));
					} else {
						if(value[tx][ty] > tcnt) {
							value[tx][ty] = tcnt;
							q.add(new Pair(tx, ty, tcnt));
						}
					}
				} else {
					if(value[tx][ty] == -1) {
						value[tx][ty] = tcnt+1;
						q.add(new Pair(tx, ty, tcnt+1));
					} else {
						if(value[tx][ty] > tcnt+1) {
							value[tx][ty] = tcnt+1;
							q.add(new Pair(tx, ty, tcnt+1));
						}
					}
				}
			}
		}
		System.out.println(value[N-1][M-1]);
	}
	static private class Pair {
		int x, y, cnt;
		public Pair(int x, int y, int cnt) {
			this.x = x; this.y = y; this.cnt = cnt;
		}
	}
}
