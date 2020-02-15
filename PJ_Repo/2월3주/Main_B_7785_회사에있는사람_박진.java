// Silver V - 7785 : 회사에 있는 사람

/*
48,896 kb
504 ms
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_B_7785_회사에있는사람_박진 {

	static int n;
	static String[] people;
	static TreeMap<String, String> treemap = new TreeMap<String, String>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		people = new String[2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			people[0] = st.nextToken();
			people[1] = st.nextToken();
//			System.out.println(people[i][0] + " / " + people[i][1]);
			
			if (people[1].equals("enter")) {	// enter
				treemap.put(people[0], people[0]);
			}
			else {	// leave
				treemap.remove(people[0]);
			}
		}
		
		int size = treemap.size();
		Object[] set = treemap.keySet().toArray();
		for(int i = size-1; i >= 0; i--) {
			sb.append(set[i]).append("\n");
		}
		
//		for(String key : treemap.keySet())
//			sb.append(treemap.get(key)).append("\n");
		
		System.out.println(sb);
	}

}
