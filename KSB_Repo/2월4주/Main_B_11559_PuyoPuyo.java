/**
* 18%에서 런타임 에러
* 
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


class Puyo{
	int x, y;
	Puyo(int x, int y){
		this.x=x; this.y=y; 
	}
}

public class Main_B_11559_PuyoPuyo {

	static int turn, total;
	static char[][] map;
	static boolean[][] visited, temp_visited;
	static int[] s_num, num;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		visited = new boolean[14][8];
		temp_visited = new boolean[14][8];
		map = new char[14][8];
		for(int i=0; i<14; i++)
			Arrays.fill(map[i], '.');
			
		String s;
		for(int i=1; i<13; i++) {
			s = br.readLine();
			for(int j=1; j<7; j++) {
				map[i][j] = s.charAt(j-1);
				if(map[i][j] != '.')
					visited[i][j] = true;
			}
		}
		
		total = 0;
		s_num = new int[14];
		num = new int[14];
		while(true) {
			turn = 0;
			for(int i=12; i>=1; i--) {
				for(int j=1; j<7; j++) {
					if(map[i][j]!='.' && visited[i][j]) {
						findPuyo(i, j, map[i][j]);
					}
				}
			}
			
			
			if(turn==0) {
				break;
			}else {
				getDownPuyo();
				total++;
			}
		}
		
		System.out.println(total);
		
	}

	private static void getDownPuyo() {
		int sum;
		for(int j=1; j<7; j++) {
			sum=0;
			for(int i=1; i<13; i++) {
				if(!visited[i][j] && map[i][j]!='.') {
					sum++;
				}
				s_num[j] = sum;				
			}			
		}
	}

	static Queue<Puyo> queue;
	private static void findPuyo(int row, int col, char color) {
		// for initialization
		for(int i=1; i<13; i++)
			for(int j=1; j<7; j++)
				temp_visited[i][j] = visited[i][j];
				
		queue = new LinkedList<Puyo>();
		row += s_num[col];
		queue.offer(new Puyo(row, col));
		visited[row][col] = false;
		int max=0, score=1;
		
		Puyo current;
		while(!queue.isEmpty()) {
			current = queue.poll();
			
			if(score>max) {
				max = score;
			}
			 
			int x, y;
			for(int i=0; i<4; i++) {
				x = current.x+dx[i];
				y = current.y+dy[i];
				
				if(visited[x][y] && map[x][y]==color) {
					queue.offer(new Puyo(x, y));
					score++;
					visited[x][y] = false;
				}
			}
		}
		
		if(max>3) {
			turn++;
		}else {
			// visited 원상태 복구
			for(int i=1; i<13; i++)
				for(int j=1; j<7; j++)
					visited[i][j] = temp_visited[i][j];
		}
		
	}
	
}
