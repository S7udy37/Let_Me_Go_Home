// D3 - 1215 : [S/W 문제해결 기본] 3일차 - 회문1

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution_D3_1215_회문1_박진 {

	static int lengthOfPalindrome;	// 찾아야하는 회문의 길이
	static char[][] charArr = new char[8][8]; // 8x8 평면 글자판
	static int count;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 총 10개의 테스트 케이스
		for (int tc = 1; tc <= 10; tc++) {
			count = 0;
			lengthOfPalindrome = Integer.parseInt(br.readLine());
			for (int i = 0; i < 8; i++) {
				charArr[i] = br.readLine().toCharArray();
			}
			
			// 세로 회문 확인
			for (int i = 0; i <= 8-lengthOfPalindrome; i++) {
				for (int j = 0; j < 8; j++) {
					int iStart = i;
					int iEnd = i + lengthOfPalindrome - 1;
					while(true) {
						if (iStart >= iEnd) {
							count++;
//							System.out.println("2. "+ i + "," + j);
							break;
						}
						
						if (charArr[iStart][j] == charArr[iEnd][j]) {
							iStart++;
							iEnd--;
						}
						else
							break;
					}
				}
			}
			
			// 가로 회문 확인
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j <= 8-lengthOfPalindrome; j++) {
					int jStart = j;
					int jEnd = j + lengthOfPalindrome - 1;
					while(true) {
						if (jStart >= jEnd) {
							count++;
//							System.out.println("1. "+ i + "," + j);
							break;
						}
						
						if (charArr[i][jStart] == charArr[i][jEnd]) {
							jStart++;
							jEnd--;
						}
						else
							break;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}
		System.out.println(sb);
	}
}
