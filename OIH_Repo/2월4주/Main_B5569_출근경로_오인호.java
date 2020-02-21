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
				// 북쪽으로 가고 있는데 이번 단계에서 바향을 바꾸지 않았다. 
				// 이전 단계에서 가능한 경우 1) 이전 단계에서도 북쪽으로 가고 있으며 방향을 바꾸지 않았다.
				// 		       2) 이전단계에서 방향을 북쪽으로 바꿨다.
				ans[i][j][0][0] = (ans[i-1][j][0][0] + ans[i-1][j][0][1]) % div;
				
				// 북쪽으로 가고 있는데 이번 단계에서 방향을 바꿨다.
				// 이전 단계에서 가능한 경우 1) 이전 단계에서 방향을 바꾸지 않았으며, 동쪽으로 가고있었다.
				ans[i][j][0][1] = ans[i-1][j][1][0] % div;
				
				// 동쪽으로 가고 있는데 이번 단계에서 방향을 바꾸지 않았다.
				// 이번 단계에서 가능한 경우 1) 이전 단계에서도 동쪽으로 가고있고 방향을 바꾸지 않았다.
				// 		       2) 이전 단계에서 동쪽으로 방향을 바꿨다.
				ans[i][j][1][0] = (ans[i][j-1][1][0] + ans[i][j-1][1][1]) % div;
				
				// 동쪽으로 가고 있는데 이번 단계에서 방향을 바꾸지 않았다.
				// 이전 단계에서 가능한 경우 1) 이전 단계에서 방향을 바꾸지 않았으며, 북쪽으로 가고있었다.
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
