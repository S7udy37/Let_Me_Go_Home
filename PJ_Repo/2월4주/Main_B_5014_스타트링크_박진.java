// Gold V - 5014 : 스타트링크

/*
48,964kb
180ms
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_5014_스타트링크_박진 {

	/*
	- 스타트링크는 총 F층
	- 강호가 지금 있는 곳은 S층
	- 스타트링크가 있는 곳의 위치는 G층
	- U버튼은 위로 U층을 가는 버튼
	- D버튼은 아래로 D층을 가는 버튼
	 */
	static int F, S, G, U, D;
	static int result = Integer.MAX_VALUE;	// 강호가 S층에서 G층으로 가기 위해 눌러야 하는 버튼의 수의 최솟값
	
	static boolean[] isVisited;			// dfs를 위한 flag
	static boolean isPosible = false;	// 엘리베이터로 이동가능하면 true, 불가능하면 false
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		isVisited = new boolean[F + 1];	// 0번째 인덱스는 사용x
		
		// 알고리즘
		dfs(S, 0);
		
		
		// 출력
		if (isPosible == true)
			System.out.println(result);
		else
			System.out.println("use the stairs");
	}

	static private void dfs(int current, int count) {
		if (current == G) {		// 기저조건
			isPosible = true;
			
			if (result > count)
				result = count;
			
			return;
		}
		
		isVisited[current] = true;
		
		if ( (current+U <= F) && (isVisited[current+U] == false) ) {
			dfs(current+U, count+1);
		}
		if ( (current-D > 0) && (isVisited[current-D] == false)) {
			dfs(current-D, count+1);
		}
	}
}
