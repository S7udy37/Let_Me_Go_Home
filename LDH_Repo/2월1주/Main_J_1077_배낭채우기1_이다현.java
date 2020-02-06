package 알고리즘스터디_2월첫주;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_1077_배낭채우기1_이다현 {

	static int Jewl[][];
	static int N;
	static int MaxW;
	static int Max=0;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		MaxW = Integer.parseInt(st.nextToken()); 
		Jewl = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			Jewl[i][0] = Integer.parseInt(st.nextToken());
			Jewl[i][1] = Integer.parseInt(st.nextToken());
		}
		jewlValue(0,0,0);
		System.out.println(Max);
	}
	
	private static void jewlValue(int index,int W, int V) {
		if(W>MaxW) {
			return;
		}
		
		if(index == N) {
			if(Max<V) {
				Max=V;
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			jewlValue(index+1,W+Jewl[i][0],V+Jewl[i][1]);
			jewlValue(index+1,W,V);
		}
		
		
	}
}
