// 메모리 : 146188 KB
// 시간 : 336ms


/**
주의!!!! 자바 Char 초기화 안하면 기본이  NUll이라서 일부화면에서는 공백이 아닌
Null이 찍힐 수 있음~~~~~!!!!!
*/


import java.util.Arrays;
import java.util.Scanner;

public class Main {

	static char[][] star = new char[6561][6561];
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<6561; i++) {
			Arrays.fill(star[i],' ');
		}
		printStar(N,0,0);
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(star[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	private static void printStar(int n, int x, int y) {
		
		if(n==1) {
			star[x][y] = '*'; return;
		}
		
		int div =  n/3;
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(i==1 && j==1) continue;
				printStar(div, x + div*i, y + div*j);
			}
		}
	}

}
