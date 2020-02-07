package S7udy;

import java.io.IOException;
import java.util.Scanner;

public class Main_J2752_개미_김준원 {

	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		int w = in.nextInt();
		int h = in.nextInt();
		int p = in.nextInt();
		int q = in.nextInt();
		long t = in.nextInt();
		long x,y;
		x=(((t+p)%(2*w)>=w?(w-((t+p)%w)):(t+p)%w));
		y=(((t+q)%(2*h)>=h?(h-((t+q)%h)):(t+q)%h));
		System.out.print(x+" "+y);
		in.close();
	}
}