import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_D3_1215_회문1 {

	private static int len, total;
	static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<10; t++) {
			len = Integer.parseInt(br.readLine());
			
			map = new char[8][8];
			for(int i=0; i<8; i++) {
				map[i] = br.readLine().toCharArray();
			}
			
			total = 0;
			for(int i=0; i<8; i++) {
				for(int j=0; j<(8-len+1); j++)
					getPalindromeRow(i, j);
			}
			
			for(int i=0; i<8; i++) {
				for(int j=0; j<(8-len+1); j++)
					getPalindromeCol(j, i);
			}
			
			sb.append('#').append(t+1).append(' ').append(total).append('\n');
		}	// Test case
		
		System.out.println(sb.toString());
		
	}

	private static void getPalindromeRow(int row, int col) {
		boolean token=true; 
		for(int i=0; i<len/2; i++) {
			if(map[row][col+i]!=map[row][col+len-i-1]) {
				token=false;
				break;
			}
		}
		if(token) total++;
	}
	
	private static void getPalindromeCol(int row, int col) {
		boolean token=true; 
		for(int i=0; i<len/2; i++) {
			if(map[row+i][col]!=map[row+len-i-1][col]) {
				token=false;
				break;
			}
		}
		if(token) total++;
	}

}
