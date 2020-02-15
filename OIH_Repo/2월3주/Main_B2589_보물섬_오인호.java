// Memory : 148888KB
// Time : 328ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static char[][] map = new char[50][50];
	static boolean[][] temp;
	static int N, M;
	static int ans;
	static int[] dx = { 1, -1, 0, 0};
	static int[] dy = { 0, 0, 1, -1}; 
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<N; i++) {
			String temp = br.readLine();
			for(int j=0; j<M; j++) {
				map[i][j] = temp.charAt(j);
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'L')
					go(i,j);
			}
		}
		System.out.println(ans);
	}

	private static void go(int x, int y) {
		temp = new boolean[N][M];
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x,y,0));
		temp[x][y] = true;
		while(!q.isEmpty()) {
			int xx = q.peek().x; int yy = q.peek().y;
			int cnt = q.peek().cnt;
			q.poll();
			for(int k=0; k<4; k++) {
				int nx = xx + dx[k]; int ny = yy + dy[k];
				if( nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if(temp[nx][ny] == false && map[nx][ny] == 'L') {
						temp[nx][ny] = true;
						q.add(new Point(nx,ny,cnt+1));
					}
				}
			}
			if(ans < cnt) ans = cnt;
		}
	}

}
class Point {
	int x; int y; int cnt;
	public Point(int x, int y, int cnt) {
		this.cnt =cnt; this.x = x; this.y = y;
	}
}
