/**
* Memory : 14656KB
* Time : 116ms
/
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int dx[] = { 1, -1, 0, 0};
	static int dy[] = { 0, 0, 1, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int R = sc.nextInt();
		int C = sc.nextInt();
		char[][] map = new char[R][C];
		Queue<Pair> q1 = new LinkedList<Pair>();
		Queue<Pair> q2 = new LinkedList<Pair>();
		for(int i=0; i<R; i++) {
			String s = sc.next();
			for(int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
				if(map[i][j] == 'S') {
					q1.add(new Pair(i, j));
					map[i][j] = '^';
				}
				else if(map[i][j] == '*') q2.add(new Pair(i, j));
			}
		}
		int time = 0;
		boolean flag = false;
		L:while(!q1.isEmpty()) {
			time++;
			int size = q2.size();
			while(size-->0) {
				Pair t = q2.poll();
				for(int k=0; k<4; k++) {
					int nx = t.x + dx[k];
					int ny = t.y + dy[k];
					if(0<=nx && nx<R && 0<=ny && ny<C) {
						if(map[nx][ny] == '.' || map[nx][ny] == '^') {
							map[nx][ny] = '*';
							q2.add(new Pair(nx, ny));
						}
					}
				}
			}
			size = q1.size();
			while(size-->0) {
				Pair t = q1.poll();
				for(int k=0; k<4; k++) {
					int nx = t.x + dx[k];
					int ny = t.y + dy[k];
					if(0<=nx && nx<R && 0<=ny && ny<C) {
						if(map[nx][ny] == 'D') {
							flag = true;
							break L;
						}
						if(map[nx][ny] == '.') {
							map[nx][ny] = '^';
							q1.add(new Pair(nx, ny));
						}
					}
				}
			}
		}
		System.out.println(flag?time:"KAKTUS");
	}
	private static class Pair {
		int x, y;
		public Pair(int x, int y) {
			this.x = x; this.y = y;
		}
	}
}
