/*
 * 58200KB
 * 176ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_G5_5014_스타트링크_정세린 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int F = Integer.parseInt(st.nextToken());		// 건물 꼭대기
		int S = Integer.parseInt(st.nextToken());		// 시작위치
		int G = Integer.parseInt(st.nextToken());		// 목적위치
		int U = Integer.parseInt(st.nextToken());		// 위로가는 크기
		int D = Integer.parseInt(st.nextToken());		// 아래로가는 크기
		int[] dh = {1 * U, -1 * D};
		
		if ((S > G && D == 0) || (S < G && U == 0)) {
			System.out.println("use the stairs");
			return;
		}
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(S);
		
		int current;
		int pos;
		int[] building = new int[F + 1];
		Arrays.fill(building, Integer.MAX_VALUE);
		
		building[S] = 0;
		
		while(!q.isEmpty()) {
			current = q.poll();
			
			for (int dir = 0; dir < 2; dir++) {
				pos = current + dh[dir];
				
				if (pos >= 1 && pos <= F && building[current] + 1 < building[pos]) {
					building[pos] = building[current] + 1;
					q.offer(pos);
				}
			}
			
		}
		
		if (building[G] == Integer.MAX_VALUE) {
			System.out.println("use the stairs");
			return;
		}
		
		System.out.println(building[G]);
	}


}
