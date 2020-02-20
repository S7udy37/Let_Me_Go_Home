// Memory : 14992KB
// Time : 112ms
// 아주 짜증나는 문제구나

package com.acmicpc;
import java.util.Scanner;

public class Main_B5569_출근경로_오인호 {

	// array 1st,2nd : map's size , map 3rd => 0 : north, 1 : east
	// map 4th => is turn? 0 : no, 1 : yes
	static int[][][][] ans = new int[101][101][2][2];
	static final int div = 100000;
  
	public static void main(String[] args) {
  
		Scanner sc = new Scanner(System.in);
		
		int Y = sc.nextInt();
		int X = sc.nextInt();
	
		// when X == 1 || Y == 1, they have only one way 
		for(int i=2; i<=X; i++) ans[i][1][0][0] = 1;
		for(int i=2; i<=Y; i++) ans[1][i][1][0] = 1;
		
		for(int i=2; i<=X; i++) {
			for(int j=2; j<=Y; j++) {
				// go : north , isn't turn 
				// before => go : north, isn't turn + go : north, is turn
				ans[i][j][0][0] = (ans[i-1][j][0][0] + ans[i-1][j][0][1]) % div;
				
				// go : north, is turn 
				// before => go : east, isn't turn
				ans[i][j][0][1] = ans[i-1][j][1][0] % div;
				
				// go : east , isn't turn 
				// before stage => go : east, isn't turn + go : east, is turn
				ans[i][j][1][0] = (ans[i][j-1][1][0] + ans[i][j-1][1][1]) % div;
				
				// go : east, is turn
				// before => go : north, isn't turn
				ans[i][j][1][1] = ans[i][j-1][0][0] % div;
			}
		}
		int result = 0;
		for(int i=0; i<2; i++) {
			for(int j=0; j<2; j++) {
				result += ans[X][Y][i][j];
			}
		}
		System.out.println(result % div);
		sc.close();
	}
	
}
