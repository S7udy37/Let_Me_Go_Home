import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Path implements Comparable<Path> {
	int x, y, score;

	public Path(int x, int y, int score) {
		this.x=x; this.y=y; this.score=score;
	}

	@Override
	public int compareTo(Path o) {
		return this.score-o.score;
	}
	
}

public class Main {
	static int N, M, ans;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N+2][M+2];
		visited = new boolean[N+2][M+2];
		for(int i=0; i<N+2; i++)
			Arrays.fill(map[i], 'W');
		
		for(int i=1; i<N+1; i++) {
			String s = br.readLine();
			for(int j=1; j<M+1; j++) {
				map[i][j] = s.charAt(j-1);
				if(s.charAt(j-1) == 'L') visited[i][j]=true;
			}
		}
		
		ans = Integer.MIN_VALUE;
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<M+1; j++) {
				if(visited[i][j] && map[i][j]=='L')
					findPath(i, j, 0);
			}
		}
		
		if(ans<1) ans=0;
		System.out.println(ans);
		
		
	}

	private static void findPath(int row, int col, int min) {
		PriorityQueue<Path> queue = new PriorityQueue<Path>();
		queue.offer(new Path(row, col, 1));
		visited[row][col] = false;
		
		Path current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			if(current.score>ans)
				ans = current.score;
			
			int x, y;
			for(int i=0; i<4; i++) {
				x = current.x + dx[i];
				y = current.y + dy[i];
				
				if(x<0 || y<0 || x>=N+2 || y>=M+2) continue;
				
				if(visited[x][y] && map[x][y]=='L') {
					visited[x][y] = false;
					queue.offer(new Path(x, y, current.score+1));
				}
			}			
		}
		
	}
}
