import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_2752_개미_MLE30 {
	
	static int width, height, row, col, time;
	static int[][] ground;
	static int[][] direction = {
			{}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}	// 왼위, 오위, 왼아래, 오아래
	};
	static int current_dir;	// 현재 개미 이동방향
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		
		ground = new int[height+3][width+3];
		for(int i=1; i<height+2; i++) {
			for(int j=1; j<width+2; j++) {
				ground[i][j]=1;	// 경계처리
			}
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		col = Integer.parseInt(st.nextToken());
		row = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(br.readLine());
		
		// 초기 row, col 설정
		row = height-row+1;	
		col = col+1;
		current_dir = 2;	//개미 초기 방향 설정(오위)

		int current_time=0;
		while(current_time<time) {
			row += direction[current_dir][0];
			col += direction[current_dir][1];
			current_time++;				
				
			if(ground[row][col]==0) {
				row -= direction[current_dir][0];
				col -= direction[current_dir][1];
				current_time--;
				switchDirection();				
			}
		}
		
		row=height-row+1;	
		col=col-1;
		System.out.print(col+" "+row);
		
		
	}
	
	public static void switchDirection() {
		if(row==1) {
			current_dir+=2;
		}
		
		if(row==height+1) {
			current_dir-=2;
		}
		
		if(col==1) {
			current_dir+=1;
		}
		
		if(col==width+1) {
			current_dir-=1;
		}
		
		if(current_dir<1) current_dir+=4;
		if(current_dir>4) current_dir-=4;
	}

}
