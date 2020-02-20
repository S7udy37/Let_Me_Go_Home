/**
*
* 53036	KB
* 264 ms
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 최대 안전 영역 얻기 위해서 물의 높이 1~(max-1)까지의 
 * 모든 안전 영역을 구한 후 그 중 max값을 찾는다.
 * 
 * 1. map과 visit배열을 크기만큼 선언 후 데이터 입력 받기
 * 
 * 2. 영역의 max값을 찾아 넣는다
 * 
 * 3. 물의 높이 1~(max-1)까지 반복해서 안전영역을 dfs 또는 bfs로 찾는다
 * 	3.1. 배열의 행, 열을 반복해서 물보다 높고, 방문하지 않은 영역을 검사
 *    3.1.1 안전영역 개수 늘리고
 *    3.1.2 dfs또는 bfs
 *  3.2 visit배열 초기화
 *  3.3 안전영역
 * 
 * 4. 안전영역 max값 출력
 * 
 * 
 */

class POINT{
	int x, y;
	POINT(int x, int y){
		this.x=x;
		this.y=y;
	}
}

public class Main_B_2468_안전영역 {

	static int N, ans, temp_zone;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx= {-1, 1, 0, 0};
	static int[] dy= {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N+2][N+2];
		visited = new boolean[N+2][N+2];
		
		int min = 100, max = 0, num;
		StringTokenizer st;
		for(int i=1; i<N+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<N+1; j++) {
				num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num>max) max=num;
				if(num<min) min=num;
			}
		}
		
		ans=0;
		int rain=min-1;
		while(rain<max) {
			wetBuilding(rain);	// 비에 젖은 빌딩 visited false
			
			temp_zone=0;
			for(int i=1; i<N+1; i++) {
				for(int j=1; j<N+1; j++) {
					if(visited[i][j]) {
						getSafeZone(i, j);						
					}
				}
			}
			
			if(ans<temp_zone) {
				ans=temp_zone;
			}
					
			rain++;
		}
		
		
		System.out.println(ans);
		
	}

	private static void getSafeZone(int row, int col) {
		LinkedList<POINT> queue = new LinkedList<POINT>();
		queue.offer(new POINT(row, col));
		visited[row][col]=false;
		
		POINT p; 
		int x, y;
		while(!queue.isEmpty()) {
			p = queue.poll();			
			
			for(int i=0; i<4; i++) {
				x = p.x+dx[i];
				y = p.y+dy[i];
				
				if(visited[x][y]) {
					queue.offer(new POINT(x, y));
					visited[x][y]=false;
				}
			}
			
		}
		
		temp_zone++;
		
	}

	private static void wetBuilding(int rain) {
		for(int i=1; i<N+1; i++) {
			for(int j=1; j<N+1; j++) {
				if(map[i][j]>rain) {
					visited[i][j] = true;
				}
			}
		}
	}
}
