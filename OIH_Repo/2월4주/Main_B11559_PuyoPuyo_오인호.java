// Memory : 12956KB
// Time : 80ms

package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_B11559_PuyoPuyo_오인호 {

	static char[][] map = new char[12][6];
	static boolean[][] check;
	static int ans = 0;
	static boolean flag;
	static int[] dx = { 1, -1, 0, 0};
	static int[] dy = { 0, 0, 1, -1};
	static ArrayList<puyo> al;
	
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = null;
		
		for(int i=0; i<12; i++) {
			str = br.readLine();
			for(int j=0; j<6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		while(true) {
			check = new boolean[12][6];
			flag = false;
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(check[i][j] == false && map[i][j] != '.') {
						find(i,j,map[i][j]);
					}
				}
			}
			if(!flag) break;
			else ans++;
			down();
		}
		
		System.out.println(ans);
		
	}
	private static void down() {
		for(int i=10; i>=0; i--) {
			for(int j=0; j<6; j++) {
				if(map[i][j] != '.') {
					int temp = 1;
					int tx = i;
					while(true) {
						if(i+temp > 11 || map[i+temp][j] != '.') break;
						map[i+temp][j] = map[tx+temp-1][j];
						map[tx+temp-1][j] = '.';
						temp++;
					}
				}
			}
		}
	}

	private static void find(int x, int y, char c) {
		int cnt = 0;
		al = new ArrayList<puyo>();
		check[x][y] = true;
		al.add(new puyo(x,y,c));
		while(true) {
			if(cnt >= al.size()) break;
			for(int k=0; k<4; k++) {
				int nx = al.get(cnt).x + dx[k];
				int ny = al.get(cnt).y + dy[k];
				if( 0 <= nx && nx < 12 && 0 <= ny && ny < 6) {
					if(check[nx][ny] == false && map[nx][ny] == c) {
						check[nx][ny] = true;
						al.add(new puyo(nx,ny,c));
					}
				}
			}
			cnt++;
		}
		
		if(al.size() >= 4) {
			for(int i=0; i<al.size(); i++) {
				map[al.get(i).x][al.get(i).y] = '.';
			}
			flag = true;
		}
	}

}
class puyo {
	int x;
	int y;
	char color;
	public puyo(int x, int y, char color) {
		this.x = x; this.y = y; this.color = color;
	}
}
