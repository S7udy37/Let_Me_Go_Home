package S7udy;
// 445904 KB = 435.4MB 깔깔
// 696 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_B7785_회사에있는사람_김준원 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		int max = 53 * 53 * 53 * 53 * 53;
		boolean key[] = new boolean[max];
		int mapping[] = { 53 * 53 * 53 * 53, 53 * 53 * 53, 53 * 53, 53, 1 };
		Queue<Integer> list = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		for (int i = 0; i < n; i++) {
			int crntkey = 0;
			for (int j = 0; j < 6; j++) {
				int t = in.read();
				if (t == ' ')
					break;
				else if ('a' <= t && t <= 'z')
					t = t - 'a' + 27;
				else if ('A' <= t && t <= 'Z')
					t = t - 'A' + 1;
				crntkey += mapping[j] * t;
			}
			String t = in.readLine();
			if (t.charAt(0) == 'e') {// enter
				key[crntkey] = true;
				list.add(crntkey);
			} else if (t.charAt(0) == 'l')// leave
				key[crntkey] = false;
		}
		StringBuilder txt = new StringBuilder();
		int pre = 0;
		while (!list.isEmpty()) {
			int i = list.poll();
			if (pre == i)
				continue;
			if (key[i]) {
				int t[] = { (i / (53 * 53 * 53 * 53)) % 53, (i / (53 * 53 * 53)) % 53, (i / (53 * 53)) % 53,
						(i / 53) % 53, i % 53 };
				for (int j = 0; j < 5; j++) {
					t[j] = (t[j] == 0) ? 0 : t[j] > 26 ? (t[j] - 27 + 'a') : (t[j] - 1 + 'A');
					if (t[j] == 0)
						break;
					txt.append((char) t[j]);
				}
				txt.append('\n');
			}
			pre = i;
		}
		System.out.print(txt);
	}
}