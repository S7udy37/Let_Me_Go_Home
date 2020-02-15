// Silver V - 7785 : 회사에 있는 사람

// 시간초과

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_B_7785_회사에있는사람_박진_TLE {

	static int n;
	static String[][] people;
	static ArrayList<String> arrayList = new ArrayList<String>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		people = new String[n][2];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			people[i][0] = st.nextToken();
			people[i][1] = st.nextToken();
//			System.out.println(people[i][0] + " / " + people[i][1]);
			
			if (people[i][1].equals("enter")) {	// enter
				arrayList.add(people[i][0]);
			}
			else {	// leave
				for (int j = 0; j < n; j++) {
					if (arrayList.get(j).equals(people[i][0])) {
						arrayList.remove(j);
						break;
					}
				}
			}
		}
		Collections.sort(arrayList);
		
		int lengthOfArrayList = arrayList.size();
		for(int i = lengthOfArrayList-1; i >= 0; i--) {
			sb.append(arrayList.get(i)).append("\n");
		}
		System.out.println(sb);
	}

}
