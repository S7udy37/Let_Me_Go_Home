import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_5569_출근경로_TLE {
	static int w;
	static int h;
	static int[] dy = { 1, 0 };
	static int[] dx = { 0, 1 };
	static boolean[][] visit;
	static int[][] map;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		visit = new boolean[h][w];
		map = new int[h][w];
		cnt = 0;
		boolean curve=false;
		dfs(0,0,2,curve);
		System.out.println(cnt);
	}

	private static void dfs(int y, int x,int nt,boolean curve) {
		visit[y][x]=true;
		map[y][x]=1;
		
		if(y==h-1&&x==w-1) {//도착하면 경로 출력
			cnt++;			
		}
		
		
		for (int t = 0; t < 2; t++) {
			if(curve&&nt!=t)continue;			
			int ny = y + dy[t];
			int nx = x + dx[t];
			if (ny >= 0 && nx >= 0 && ny < h && nx < w &&!visit[ny][nx]) {
				if(nt!=t &&nt!=2) {curve=true;}
				else {curve=false;}
				
				dfs(ny,nx,t,curve);		
				map[ny][nx]=0;
				visit[ny][nx]=false;
			}
		}
	}
}
