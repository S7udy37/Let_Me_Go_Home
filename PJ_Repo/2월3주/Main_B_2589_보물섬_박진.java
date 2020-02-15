// Gold V - 2589 : 보물섬

/*
48,896KB
504ms
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_2589_보물섬_박진 {

	static int R;	// 보물 지도의 세로
	static int C;	// 보물 지도의 가로
	static char[][] map;	// 보물 지도 ( 육지(L), 바다(W) )
	static boolean[][] isSelected;
	
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int[] point;
	static int size;
	
	static int maxDistance = 0;
	
	// 상, 하 , 좌, 우
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 입력
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
//		for(char[] a : map)
//			System.out.println(Arrays.toString(a));
		
		// 알고리즘 (BFS)
		for (int i = 0; i < R; i++) {		// 맵 탐색
			for (int j = 0; j < C; j++) {	// 맵 탐색
				
				if (map[i][j] == 'L') {		// 육지(L)를 발견하면 queue에 넣고, BFS 탐색
					int distance = 0;
					isSelected = new boolean[R][C];
					point = new int[]{i,j};
					isSelected[i][j] = true;
					queue.offer(point);
					
					while ( !queue.isEmpty() ) {
						size = queue.size();
						
						while ( size-- > 0 ) {
							int currenti = queue.peek()[0];
							int currentj = queue.peek()[1];
							queue.poll();
							
							for (int d = 0; d < 4; d++) {
								int nexti = currenti + di[d];
								int nextj = currentj + dj[d];
								
								if ( nexti < 0 || nextj < 0 || nexti >= R || nextj >= C )	// 배열 넘어가면 무시.
									continue;
								if ( map[nexti][nextj] == 'W')	// 바다(W)이면 무시.
									continue;
								if ( isSelected[nexti][nextj] == true )	// 이미 간 육지이면 무시.
									continue;
								
								point = new int[]{nexti,nextj};
								queue.offer(point);
								isSelected[nexti][nextj] = true;
							}
						}
						
						distance++;
					}
					
					if (maxDistance < distance)
						maxDistance = distance;
				}
			}
		}
		
		// 출력
		System.out.println(maxDistance-1);	// 마지막 도착해서도 카운팅이 됐으므로 -1을 해줌
	}
}
