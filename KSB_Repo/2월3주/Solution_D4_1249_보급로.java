/**
* DFS: 시간초과
* BFS: 
* 31,092 KB
* 136 ms
*/

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Car implements Comparable<Car> {
	int x, y, score;
	Car(int x, int y, int score){
		this.x = x;
		this.y = y;
		this.score = score;
	}
	@Override
	public int compareTo(Car o) {
		// TODO Auto-generated method stub
		return this.score-o.score;
	}
}

public class Solution_D4_1249_보급로 {
	private static int T, N, ans;
	static int[][] ground;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for(int t=0; t<T; t++) {
			N = Integer.parseInt(br.readLine());
			ground = new int[N][N];
			visited = new boolean[N][N];
			for(int i=0; i<N; i++)
				Arrays.fill(visited[i], true);
			
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				for(int j=0; j<N; j++) {
					ground[i][j] = s.charAt(j) - '0';
				}
			}
			
			ans = Integer.MAX_VALUE;
			findPathBFS(0, 0, 0);
			
			sb.append('#').append(t+1).append(' ').append(ans).append('\n');
		}	// Test case
		
		System.out.println(sb.toString());
	}
  
	private static void findPathBFS(int row, int col, int min) {
		PriorityQueue<Car> queue = new PriorityQueue<Car>(); 
		queue.offer(new Car(row, col, ground[row][col]));
		visited[row][col] = false;
		
		Car current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			if(current.score>ans)
				break;
			
			if(current.x == (N-1) && current.y == (N-1)) {
				if(ans>current.score)
					ans=current.score;
				break;
			}
			
			int x, y;
			for(int i=0; i<4; i++) {
				x = current.x+dx[i];
				y = current.y+dy[i];
				if(x<0 || y<0 || x>=N || y>=N) continue;
				if(visited[x][y]) {					
					visited[x][y] = false;					
					queue.offer(new Car(x, y, current.score+ground[x][y]));
				}					
			}		
		}
	}
  
	private static void findPath(int row, int col, int min) {
		if(ans<min) return;
		if(row==(N-1) && col==(N-1)) {
			if(ans>min)
				ans=min;
			
			return;
		}
				
		visited[row][col]=false;
		
		int x, y;
		for(int i=0; i<4; i++) {
			x = row+dx[i];
			y = col+dy[i];
			if(x<0 || y<0 || x>=N || y>=N) continue;
			if(visited[x][y]) {
				if(min+ground[x][y]>ans) continue;
				findPath(x, y, min+ground[x][y]);
			}
		}
		
		visited[row][col]=true;
		
	}
	
}
