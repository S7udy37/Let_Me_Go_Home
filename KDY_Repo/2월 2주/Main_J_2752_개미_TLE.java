package study_0210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_2752_개미_TLE {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		long map_x = Long.parseLong(st.nextToken())+1;
		long map_y = Long.parseLong(st.nextToken())+1;
		
		st = new StringTokenizer(in.readLine());
		long s_x = Long.parseLong(st.nextToken()); 
		long s_y = map_y-Long.parseLong(st.nextToken())-1;
		long t = Long.parseLong(in.readLine());
		
		
		long y=s_y;
		long x=s_x;
		
		int x_move=1;
		int y_move=-1;

		long minus = (map_x-1)*(map_y-1);
		if(map_x%2==1&&map_y%2==1) {
			t=t%(minus);
		}else {
			t=t%(minus*2);
		}
		
		
		for(int i=0; i<t;i++) {		
			if(y==0||y==map_y-1) {
				y_move*=(-1);
			}
			if(x==0||x==map_x-1) {
				x_move*=(-1);
			}
			y+=y_move;
			x+=x_move;
			
		}
		
		System.out.print(x+" "+(map_y-y-1));
		
	}
}


