// Gold III - 17143 : 낚시왕

/*
28,488kb
684ms
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_17143_낚시왕_박진 {

	static class Shark implements Comparable<Shark>{
		int r;	// 상어의 위치
		int c;	// 상어의 위치
		int s;	// 속력
		int d;	// 방향 (1인 경우는 위, 2인 경우는 아래, 3인 경우는 오른쪽, 4인 경우는 왼쪽)
		int z;	// 크기
		
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			if (this.r == o.r) {
				if (this.c == o.c) {
					return this.z - o.z;
				} else {
					return this.c - o.c;
				}
			}
			return this.r - o.r;
		}
	}
	
	static int R, C;		// 격자판의 크기 R, C
	static int M;			// 상어의 수
	
	static int result = 0;	// 낚시왕이 잡은 상어 크기의 합
	
	static ArrayList<Shark> arrList = new ArrayList<Shark>(); 
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int r, c, s, d, z;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			z = Integer.parseInt(st.nextToken());
			arrList.add(new Shark(r, c, s, d, z));
		}
		Collections.sort(arrList);
		
		// 알고리즘
		for (int man = 1; man <= C; man++) {	// 낚시왕이 오른쪽으로 한 칸씩 이동.
			catchShark(man);	// 낚시왕이 제일 가까운 상어를 잡음.
			getNextLocation();	// 상어들이 이동하게 될 위치 계산.
			removeShark();		// 겹치게 될 상어들 없애기.
		}
		
		// 출력
		System.out.println(result);
	}

	/** 낚시왕이 제일 가까운 상어를 잡는 메소드 */
	private static void catchShark(int col) {
		int size = arrList.size();
		int minRow = 101;
		int tempIndex = -1;
		
		// 잡아야할 상어 찾기.
		for (int i = 0; i < size; i++) {
			if (arrList.get(i).c == col) {
				if (minRow > arrList.get(i).r) {
					minRow = arrList.get(i).r;
					tempIndex = i;
				}
			}
		}
		
		// 잡은 상어 무게를 result에 더해줌.
		if (tempIndex > -1) {
			result += arrList.get(tempIndex).z;
			arrList.remove(tempIndex);
		}
	}

	/** 상어들이 이동하게 될 위치를 계산하는 메소드 */
	private static void getNextLocation() {
		int size = arrList.size();
		for (int i = 0; i < size; i++) {
			int speed = arrList.get(i).s;
			while (true) {
				switch(arrList.get(i).d) {
				case 1:	// 위쪽
					if (arrList.get(i).r - speed > 0) {
						arrList.get(i).r -= speed;
						speed = 0;
					} else {
						arrList.get(i).d = 2;
						speed = speed - arrList.get(i).r + 1;
						arrList.get(i).r = 1;
					}
					break;
				case 2:	// 아래쪽
					if (arrList.get(i).r + speed <= R) {
						arrList.get(i).r += speed;
						speed = 0;
					} else {
						arrList.get(i).d = 1;
						speed = arrList.get(i).r + speed - R;
						arrList.get(i).r = R;
					}
					break;
				case 3:	// 오른쪽
					if (arrList.get(i).c + speed <= C) {
						arrList.get(i).c += speed;
						speed = 0;
					} else {
						arrList.get(i).d = 4;
						speed = arrList.get(i).c + speed - C;
						arrList.get(i).c = C;
					}
					break;
				case 4:	// 왼쪽
					if (arrList.get(i).c - speed > 0) {
						arrList.get(i).c -= speed;
						speed = 0;
					} else {
						arrList.get(i).d = 3;
						speed = speed - arrList.get(i).c + 1;
						arrList.get(i).c = 1;
					}
					break;
				default:
					break;
				}
				
				if (speed < 1)
					break;
			}
		}
	}
	
	/** 겹치게 될 상어들을 없애는 메소드 */
	private static void removeShark() {
		// 겹치는 상어들 중에서 큰 상어에게 잡아먹힐 작은 상어들을 queue에 넣음.
		Collections.sort(arrList);
		Queue<Shark> queue = new LinkedList<Shark>();
		int size = arrList.size();
	L:	for (int i = 0; i < size-1; i++) {
			for (int j = i+1; j < size; j++) {
				if (arrList.get(i).r < arrList.get(j).r)
					continue L;
				
				if ((arrList.get(i).r == arrList.get(j).r) && (arrList.get(i).c == arrList.get(j).c)) {
					if (arrList.get(i).z > arrList.get(j).z) {
						queue.offer(arrList.get(j));
					}
					else {
						queue.offer(arrList.get(i));
						continue L;
					}
				}
			}
		}
		// 큐에 넣은 상어들을 제거.
		Shark shark1;
		while(!queue.isEmpty()) {
			shark1 = queue.poll();
			arrList.remove(shark1);
		}
	}
}
