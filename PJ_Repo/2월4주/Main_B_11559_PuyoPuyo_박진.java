// Gold V - 11559 : Puyo Puyo

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	int i, j;
	
	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}
}

public class Main_B_11559_PuyoPuyo_박진 {

	static char[][] gameBoard = new char[12][6];
	
	static boolean[][] isSelected;				// BFS를 위한 flag
	static Queue<Point> queue = new LinkedList<Point>();	// BFS를 위한 큐
	
	// 상, 하, 좌, 우
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	static boolean isPosible = false;	// 터뜨릴 뿌요가 있는지 확인하는 flag (있으면 true, 없으면 false)
	static int result = 0;			// 결과값
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		for (int i = 0; i < 12; i++) {
			gameBoard[i] = br.readLine().toCharArray();
		}
		
		// 알고리즘
		do {
			isSelected = new boolean[12][6];	// isSelected 초기화
			isPosible = false;			// isPosible 초기화
			removePuyo();	// 뿌요 제거
			
			if (isPosible == true) {
				result++;	// 연쇄(결과값) 증가
				movePuyo(); 	// 뿌요 이동
			}
		} while(isPosible == true);
		
		// 출력
		System.out.println(result);
	}

	// 뿌요들이 중력의 영향을 받아 차례대로 아래로 이동하게 하는 메소드
	static private void movePuyo() {
		for (int j = 0; j < 6; j++) {
		L:	for (int i = 11; i >= 0; i--) {
				if (gameBoard[i][j] == '.' && i-1 >= 0) {
					for (int i2 = i-1; i2 >= 0; i2--) {
						if (gameBoard[i2][j] != '.') {
							gameBoard[i][j] = gameBoard[i2][j];
							gameBoard[i2][j] = '.';
							continue L;
						}
					}
				}
			}
		}
	}
	
	// 4개 이상의 뿌요들을 모두 터뜨리는 메소드
	static private void removePuyo() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (gameBoard[i][j] != '.' && isSelected[i][j] == false) {
					bfs(new Point(i, j));
				}
			}
		}
	}
	
	// 터뜨릴 수 있는 4개 이상의 뿌요인지 확인하고 터뜨리는 메소드
	static private void bfs(Point point) {
		int count = 1;
		Queue<Point> locationOfPuyo = new LinkedList<Point>();
		locationOfPuyo.offer(point);
		
		queue.offer(point);
		isSelected[point.i][point.j] = true;
		char color = gameBoard[point.i][point.j];
		
		while(!queue.isEmpty()) {
			Point current = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nexti = current.i + di[d];
				int nextj = current.j + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= 12 || nextj >= 6 || isSelected[nexti][nextj] == true)
					continue;
				
				if (gameBoard[nexti][nextj] != color)
					continue;
				
				isSelected[nexti][nextj] = true;
				count++;
				queue.offer(new Point(nexti, nextj));
				
				locationOfPuyo.offer(new Point(nexti, nextj));
			}
		}
		
		if (count > 3) {	// 같은 색 뿌요가 4개 이상 연결되어 있으면 터뜨린다.
			isPosible = true;
			int size = locationOfPuyo.size();
			for (int i = 0; i < size; i++) {
				gameBoard[locationOfPuyo.peek().i][locationOfPuyo.peek().j] = '.';
				locationOfPuyo.poll();
			}
		}
	}
}
