// 메모리 : 19,468 kb
// 실행시간 : 103 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static char[][] map = new char[8][8]; 
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=1; t<=10; t++) {
			int N = Integer.parseInt(br.readLine());
			boolean flag = true;
			int cnt = 0;
			for(int i=0;i<8;i++) {
				String st = br.readLine();
				for(int j=0;j<8;j++) {
					map[i][j] = st.charAt(j);
				}
			}
			for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					if(j <= 8-N) {
						flag = true;
						for(int k=0; k<N/2; k++) {
							if(map[i][j+k] != map[i][j+N-1-k]) {
								flag = false; break;
							}
						}
						if(flag) cnt++;
					}
					if(i <= 8-N) {
						flag = true;
						for(int k=0; k<N/2; k++) {
							if(map[i+k][j] != map[i+N-1-k][j]) {
								flag = false; break;
							}
						}
						if(flag) cnt++;
					}
				}
			}
			System.out.println("#" + t + " " + cnt);
		}
	}

}
