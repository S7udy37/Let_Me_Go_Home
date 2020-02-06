import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_J1809_탑_김준원 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = stoi(in.readLine());
		int[] tower = new int[n + 1], ans = new int[n + 1];
		String[] bf = in.readLine().split(" ");
		StringBuffer txt = new StringBuffer();
		tower[0] = Integer.MAX_VALUE;
		for (int i = 1; i <= n; i++) {
			tower[i] = stoi(bf[i - 1]);
			if (tower[i - 1] >= tower[i])
				ans[i] = i - 1;
			else {
				for (int j = i - 1; j >= 0; j--) {
					if (tower[ans[j]] >= tower[i]) {
						ans[i] = ans[j];
						break;
					}
				}
			}
			txt.append(ans[i] + " ");
		}
		System.out.println(txt);
		in.close();
	}

	static int stoi(String a) {
		return Integer.parseInt(a);
	}
}
