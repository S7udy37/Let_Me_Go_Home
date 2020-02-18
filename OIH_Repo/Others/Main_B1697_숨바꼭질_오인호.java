// Memory : 19000KB
// Time : 148ms
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	static int[] map = new int[100001];
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt(); int K = sc.nextInt();
		Arrays.fill(map, -1);
		Deque<Integer> dq = new LinkedList<Integer>();
		dq.add(N);
		map[N] = 0;
		while(!dq.isEmpty()) {
			if(map[K] != -1) break;
			int x = dq.poll();
			if(x*2 <= 100000 && map[x*2] == -1) {
				dq.addFirst(x*2);
				map[x*2] = map[x] + 1;
			}
			if(x+1 <= 100000 && map[x+1] == -1) {
				dq.addLast(x+1);
				map[x+1] = map[x] + 1;
			}
			if(x-1 >= 0 && map[x-1] == -1) {
				dq.addLast(x-1);
				map[x-1] = map[x] + 1;
			}
		}
		System.out.println(map[K]);
	}

}
