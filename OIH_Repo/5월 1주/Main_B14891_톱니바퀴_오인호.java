/**
* Memory : 14488KB
* Time : 124ms
/
import java.util.Scanner;

public class Main_B14891_톱니바퀴_오인호 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[][] gear = new int[4][8];
		for(int i=0; i<4; i++) {
			String str = sc.next();
			for(int j=0; j<8; j++) {
				gear[i][j] = str.charAt(j) - '0';
			}
		}
		int K = sc.nextInt();
		for(int i=0; i<K; i++) {
			int num = sc.nextInt() - 1;
			int dir = sc.nextInt();
			int[] d = new int[4];
			d[num] = dir;
			for(int j=num-1; j>=0; j--) {
				if(gear[j][2] != gear[j+1][6]) d[j] = -1*d[j+1]; 
				else break;
			}
			for(int j=num+1; j<4; j++) {
				if(gear[j-1][2] != gear[j][6]) d[j] = -1*d[j-1];
				else break;
			}
			for(int j=0; j<4; j++) {
				int temp = 0;
				if(d[j] == 1) {
					temp = gear[j][7];
					for(int k=7; k>0; k--) {
						gear[j][k] = gear[j][k-1];
					}
					gear[j][0] = temp;
				}
				else if(d[j] == -1){
					temp = gear[j][0];
					for(int k=1; k<8; k++) {
						gear[j][k-1] = gear[j][k];
					}
					gear[j][7] = temp;
				}
			}
		}
		int ans = 0;
		for(int i=0; i<4; i++) {
			if(gear[i][0] == 1) {
				ans |= 1<<i;
			}
		}
		System.out.println(ans);
	}
}
