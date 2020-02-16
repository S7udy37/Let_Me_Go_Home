package study_0217;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_D3_1215_회문 {
	static char[][] map;
	static int n;
	static String word;
	static int cnt;
	static StringBuffer sb = new StringBuffer();
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		map=new char[8][8];
		for (int t = 1; t <= 10; t++) {
			n = Integer.parseInt(in.readLine());
			
			for(int i=0; i<8;i++) {
				String line = in.readLine().trim();
				for(int j=0; j<8;j++) {
					map[i]=line.toCharArray();
				}			
			}
			
			cnt=0;
			for(int i=0; i<8;i++) {
				for(int j=0; j<8;j++) {
					word = "";
					sb.setLength(0);
					rightfinder(i,j);
				}
			}
			for(int i=0; i<8;i++) {
				for(int j=0; j<8;j++) {
					word = "";
					sb.setLength(0);
					downfinder(i,j);
				}
			}
			System.out.println("#"+t+" "+cnt);
		}
	}
	private static void rightfinder(int y,int x) {		
		for(int i=0; i<n; i++) {
			if(x>=8)return;
			word+=map[y][x++];
		}
		sb.append(word);
		sb.reverse();
		if(sb.toString().equals(word)) {
			cnt++;
		}
	}
	
	private static void downfinder(int y,int x) {		
		for(int i=0; i<n; i++) {
			if(y>=8)return;
			word+=map[y++][x];
		}
		sb.append(word);
		sb.reverse();
		if(sb.toString().equals(word)) {
			cnt++;
		}
	}
	
}
