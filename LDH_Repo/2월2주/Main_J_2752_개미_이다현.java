package 알고리즘스터디2월2주차;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_2752_개미_이다현 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input_개미.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(in.readLine());
		int count = Integer.parseInt(st.nextToken());
		int dx = 1;
		int dy = 1;
		int min;
		if((W-x)<(H-y)) {
			min = W-x;
		}else {
			min = H-y;
		}
		int startX = x+min;
		int startY = y+min;
		count = count-min;
//		int startX2 = 0;
//		int startY2 = 0;
//		int countUP =0;
		while (0 < count) {
			if((x == W&&y==H)||(x == W&&y==0)||(x == 0&&y==H)||(x == 0&&y==0)){
				dx = -dx;
				dy = -dy;
				startX = x;
				startY= y;
//				startX2 = x-dx;
//				startY2 = y-dy;
				//countUP=0;
			} else if (y == H || y == 0) {
				dy = -dy;
			}else if (x == W || x == 0)  {
				dx = -dx;
			}
			x = x + dx;
			y = y + dy;
			count--;
			//countUP++;
//			System.out.println(x+" "+y);
//			System.out.println("count : "+count);
//			System.out.println("countUp : "+countUP);
//			System.out.println(x+" "+y);
			if(startX==x&&startY == y/*&&startX2==(x+dx)&&startY2==(y+dy)*/) {
//				System.out.println(countUP);
//				System.out.println(count);
				//count = count%countUP;
			//	countUP=0;
//				System.out.println(count);
				

			}
		}
		System.out.println(x + " " + y);

	}

}
