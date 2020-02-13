/*
42796 KB	
512ms
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Main_B_S5_7785_회사에있는사람_정세린 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str, name;
		HashMap<String, String> map = new HashMap<String, String>();
		StringBuilder sb = new StringBuilder();

		for (int n = 0; n < N; n++) {
			str = br.readLine();
			name = str.substring(0, str.indexOf(' '));

			if (str.endsWith("enter")) map.put(name, name);
			if (str.endsWith("leave")) map.remove(name, name);
		}

		ArrayList<String> company = new ArrayList<String>(map.keySet());
		Collections.sort(company, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});

		for (int i = 0; i < company.size(); i++)
			sb.append(company.get(i)).append('\n');

		System.out.println(sb);
	}

}
