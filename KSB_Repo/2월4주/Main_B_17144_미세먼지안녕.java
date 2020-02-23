/**
* 18604 KB
* 400 ms
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_17144_미세먼지안녕 {
	
	static int R, C, T, clean_row;
	static int[][] map, temp_map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		temp_map = new int[R][C];
		
		int temp;
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<C; j++) {
				temp = Integer.parseInt(st.nextToken());
				map[i][j] = temp;
				
				if(temp==-1) {	// 공기청정기 위치 확인
					clean_row = i;
				}
			}
		}
		
		int time=T;
		while(time-->0) {

			// 1. 미세먼지 확산 using temp_map
			int row, col, num, dust;
			for(int i=0; i<R; i++) {
				for(int j=0; j<C; j++) {
					
					if(map[i][j]>0) {	// 미세먼지 있을 때
						dust = map[i][j]/5;
						
						// 사방탐색
						num=0;
						for(int dir=0; dir<4; dir++) {
							row = i+dx[dir];
							col = j+dy[dir];
						
							if(row<0||col<0||row>=R||col>=C||map[row][col]<0)	// 공기청정기쪽은 확산안됨
								continue;
							
							num++;	// 확산되는 방향수
							temp_map[row][col] += dust;
						}
						
						temp_map[i][j] -= dust*num;
					}
				}
			}
			
			// 2. map update
			updateMap();
			
			// 3. 공기청정기 돌리기
			getAirClean();		
		}
		
		
		// 4. 남은 값 다 더하기
		int sum=2;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				sum += map[i][j];
			}
		}
		System.out.println(sum);
		
	}

	private static void getAirClean() {
		for(int i=clean_row-2; i>0; i--) {	// V
			map[i][0] = map[i-1][0];
		}
		
		for(int j=0; j<C-1; j++) {	// <
			map[0][j] = map[0][j+1];
		}
		
		for(int i=0; i<clean_row-1; i++) { 	//^
			map[i][C-1] = map[i+1][C-1];
		}
		
		for(int j=C-1; j>1; j--) {	// >
			map[clean_row-1][j] = map[clean_row-1][j-1];
		}
		map[clean_row-1][1]=0;
		
		// 아래쪽 공기청정기
		for(int i=clean_row+1; i<R-1; i++) { 	//^
			map[i][0] = map[i+1][0];
		}
		
		for(int j=0; j<C-1; j++) {	// <
			map[R-1][j] = map[R-1][j+1];
		}
		
		for(int i=R-1; i>clean_row; i--) {	// V
			map[i][C-1] = map[i-1][C-1];
		}
		
		for(int j=C-1; j>1; j--) {	// >
			map[clean_row][j] = map[clean_row][j-1];
		}		
		map[clean_row][1]=0;
		
	}

	private static void updateMap() {
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]<0)
					continue;
				
				map[i][j] += temp_map[i][j];
			}
		}		
		
		for(int i=0; i<R; i++)
			Arrays.fill(temp_map[i], 0);
	}

}
