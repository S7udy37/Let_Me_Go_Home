package 알고리즘스터디2월2주차;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_1681_해밀턴순환회로_이다현 {
	static int N;
	static int[][] arr;
	static int answer = Integer.MAX_VALUE;
	static boolean[] isVisited;
	static int count;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		arr = new int[N][N];
		isVisited = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		isVisited[0] = true;
		findMin(0,0);
		System.out.println(answer);
	}
	
	public static void findMin(int index, int min) {
		if(answer<min) {
			return;
		}
		if(count == N-1) {
			if(arr[index][0]==0) {
				return;
			}
			min = min+arr[index][0];
			if(min<answer) {
				answer = min;
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if(arr[index][i]==0||isVisited[i]==true) {
				continue;
			}
			isVisited[i] = true;
			count++;
			findMin(i,min+arr[index][i]);
			isVisited[i]=false;
			count--;
		}
	}
	

}
