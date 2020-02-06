import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_1681_해밀턴순환회로 {
	
	static int N, min;
	static int[][] map;
	static boolean[] isSelected;
	static int[] visited;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");;
		
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		isSelected = new boolean[N];
		visited = new int[N+1];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		min = Integer.MAX_VALUE;
		visited[0] = 0;
		isSelected[0] = true;
		getDelivery(1);
		System.out.println(min);
	}

	private static void getDelivery(int idx) {
		if(idx==N) {
			if(map[visited[N-1]][0]==0)
				return;
			
			int sum=0;
			visited[N] = 0;
			for(int i=0; i<N; i++) {
				sum += map[visited[i]][visited[i+1]];				
			}
			
			if(sum<min) 
				min = sum;
			
			
			return;
		}
		
		for(int i=1; i<N; i++) {
			if(!isSelected[i] && map[visited[idx-1]][i]!=0) {
				isSelected[i] = true;
				visited[idx]=i;
				getDelivery(idx+1);				
				isSelected[i] = false;
			}
		}
	}
}
