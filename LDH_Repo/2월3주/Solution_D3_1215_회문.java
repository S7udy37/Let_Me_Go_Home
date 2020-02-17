/*
23,312 kb
메모리
102 ms
실행시간
*/

package 알고리즘스터디2월3주차;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_D3_1215_회문 {
	static int N;
	public static void main(String[] args) throws Exception{
		int[] dn = {0,1};
		int[] dm = {1,0};
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		boolean find;
		char[][] map = new char[8][8];
		int answer;
		for (int tests = 1; tests <=10;tests++) {
			answer =0;
			N = Integer.parseInt(in.readLine());
			char[] arrShort = new char[N]; 
			for (int i = 0; i < 8; i++) {
				map[i]=in.readLine().toCharArray();
			}
			for (int n = 0; n < 9; n++) {
				for (int m = 0; m <9; m++) {
					that :for (int i = 0; i < 2; i++) {
						for (int j = 0; j < N; j++) {
							int tempN = n +dn[i]*j;
							int tempM = m +dm[i]*j;
							//System.out.println(tempN +" "+tempM);
							if(tempN<0||8<=tempN||tempM<0||8<=tempM) {
								continue that;
							}
							arrShort[j]=map[tempN][tempM];
						}
						//System.out.println(Arrays.toString(arrShort));
						find = find(arrShort);
						//System.out.println(find);
						if(find) {
							answer++;
						}
					}
				}
			}
			System.out.println("#"+tests+" "+answer);
		}
	}
	static boolean find(char[] arr) {
		for (int k = 0; k < N/2; k++) {
			if(arr[k]!=arr[N-1-k]) {
				return false;
			}
		}
		return true;
	}
	
}
