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
		
		// 입력 부분
		for(int i=0; i<12; i++) {
			str = br.readLine();
			for(int j=0; j<6; j++) {
				map[i][j] = str.charAt(j);
			}
		}
		while(true) {
			check = new boolean[12][6]; // 한 번에 여러번 깨질 수 있으므로 매번 초기화
			flag = false;
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(check[i][j] == false && map[i][j] != '.') {
						find(i,j,map[i][j]);
					}
				}
			}
			// Flag를 전역변수로 설정해 다른 메소드에서 접근 가능하게 했으며, 깨질 구슬이 없으면 flag의 값은 변경되지않음.
			if(!flag) break; // 깨질 구슬이 없으면 무한루프 탈출!!
			else ans++; // 구슬이 깨졌으면 1회 카운트
			down();
		}
		
		System.out.println(ans);
		
	}
	// 구슬을 아래로 내려주는 메서드 (이런 유형 문제는 그냥 횟수마다 맵을 바꿔가면서 해줘야 나중에 디버깅 하기도 편하고, 
	// 이러한 연산 추가한다고해서 시간초가 잘 안뜸!
	private static void down() {
		for(int i=10; i>=0; i--) {
			for(int j=0; j<6; j++) {
				if(map[i][j] != '.') { // 만일 빈공간일경우
					int temp = 1; // 아래까지 쭉 내려야해서
					int tx = i; // i를 사용했을 때 오류가 있었는데 왜 쓴지 
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
	// 깨질 구슬 있나 찾는 메서드 
	private static void find(int x, int y, char c) {
		int cnt = 0;
		// bfs를 위해서 큐를 쓰려다가 만일 구슬이 깨질 수 있으면 깨질 수 있는 구슬의 모든 정보를 담아야 하는데 큐를 쓸경우 poll을 하지 않아서
		// 데이터를 인덱스 형태로 접근하는데 불편함이 있어 어레이리스트를 사용함
		al = new ArrayList<puyo>();
		check[x][y] = true; // 방문했으면 체크!
		al.add(new puyo(x,y,c)); // 어레이리스트에 넣기!
		while(true) { // 넣을 구슬이 없을때까지 돌려!
			if(cnt >= al.size()) break; // 마땅한 브레이크 포인트가 없어서 방향탐색 1번할때마다 1증가시켜주는 카운트 변수를 만듦.
// 카운트는 무조건 1씩 증가하니까 워스트 경우 12*6번 반복
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
		// 연결된 구슬이 4개 이상이면 깨질 수 있음
		if(al.size() >= 4) {
			for(int i=0; i<al.size(); i++) {
				map[al.get(i).x][al.get(i).y] = '.';
			}
			flag = true;
		}
	}

}
// x : 구슬의 위치 , y : 구슬의 위치 , color 구슬 색 , 코딩할 때 주로 X = Row, Y = Col
class puyo {
	int x;
	int y;
	char color;
	public puyo(int x, int y, char color) {
		this.x = x; this.y = y; this.color = color;
	}
}
