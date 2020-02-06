package study_0204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_15666_N과M12 {
	
	static int N, M;
	static ArrayList<Integer> arr = new ArrayList<>();
	static int[] ans;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		arr.add(Integer.parseInt(st.nextToken()));
		
		boolean token = true;
		for(int i=1; i<N; i++) {
			int num = Integer.parseInt(st.nextToken());
			for(int j=0; j<arr.size(); j++) {
				if(arr.get(j)==num)
					token = false;
			}
			if(token)
				arr.add(num);
			
			token = true;
		}
		
		ans = new int[M];
		arr.sort(null);
		
		getNext(0);
		System.out.print(sb.toString());
		
	}

	private static void getNext(int idx) {	
		
		if(idx==M) {
			for(int i=0; i<M; i++)
				sb.append(ans[i]).append(' ');
			sb.append("\n");
			
			return;
		}
		
		int len = arr.size();
		for(int i=0; i<len; i++) {			
			ans[idx] = arr.get(i);
			if(idx>0)
				if(ans[idx]<ans[idx-1])
					continue;
			
			getNext(idx+1);
		}
	}

}
