import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_B_3109_빵집 {
	
	static int R, C, pipe;
	static char[][] ground;
	static boolean[][] visited;
	static int[] dx = {-1, 0, 1};
	static int[] dy = {1, 1, 1};	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		ground = new char[R+2][C];
		visited = new boolean[R+2][C];
		
		for(int i=0; i<R+2; i++)
			Arrays.fill(ground[i], 'x');
		
		String s; char c;
		for(int i=1; i<R+1; i++) {
			s = br.readLine();
			for(int j=0; j<C; j++) {
				c = s.charAt(j);
				ground[i][j] = c;
				
				if(c=='.') visited[i][j] = true;
			}
		}
		
		pipe = 0;
		for(int i=1; i<R+1; i++)
			getPipe(i, 0);
		
		System.out.println(pipe);
		
	}

	private static void getPipe(int row, int col) {
		Stack<Point> stack = new Stack<Point>();
		stack.push(new Point(row, col));
		
		Point p;
		while(!stack.isEmpty()) {
			p = stack.pop();
			visited[p.x][p.y] = false;
			
			if(p.y==C-1) {
				pipe++;
				return;
			}
			
			int x, y;
			for(int i=2; i>=0; i--) {
				x = p.x+dx[i];
				y = p.y+1;
				if(ground[x][y]=='.' && visited[x][y]) {
					stack.push(new Point(x, y));							
				}
			}
			
		}
	}


}
