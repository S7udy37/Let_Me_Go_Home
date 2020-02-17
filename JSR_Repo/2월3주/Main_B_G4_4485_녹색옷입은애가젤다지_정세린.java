import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G4_4485_녹색옷입은애가젤다지_정세린 {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int tc = 0, N;
		int[][] map, memo;
		int[][] dh = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			tc++;
			N = Integer.parseInt(br.readLine());
			if (N == 0) break;
			map = new int[N][N];
			memo = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					memo[i][j] = N*N*9;
				}
			}
			
			Queue<Point> queue = new LinkedList<Point>();
			queue.offer(new Point(0, 0));
			memo[0][0] = map[0][0];
			Point current;
			
			while(!queue.isEmpty()) {
				current = queue.poll();
				for(int dir = 0; dir < 4; dir++) {
					int itmp = current.x + dh[dir][0];
					int jtmp = current.y + dh[dir][1];
					if (itmp >= 0 && itmp < N && jtmp >= 0 && jtmp < N) {
						if (memo[current.x][current.y] + map[itmp][jtmp] < memo[itmp][jtmp]) {
							memo[itmp][jtmp] = memo[current.x][current.y] + map[itmp][jtmp];
							queue.offer(new Point(itmp, jtmp));
						}	
					}
				}
			}
			sb.append("Problem " + tc + ": " + memo[N-1][N-1] + "\n");
		}
		System.out.println(sb);
	}
	
}
