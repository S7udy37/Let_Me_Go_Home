package 알고리즘스터디2월3주차;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_7785_회사에있는사람_이다현 {

	static String[] names;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		names = new String[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			String name = st.nextToken();
			String state = st.nextToken();
			if (state.equals("enter")) {
				names[i] = name;
			} else {
				leave(name);
				i = i - 2;
				n = n - 2;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < names.length && names[i] != null; i++) {
			sb.insert(0, "\n").insert(0, names[i]);
		}
		System.out.println(sb.toString());
	}

	static void leave(String name) {
		int i;
		for (i = 0; i < names.length; i++) {
			if (names[i].equals(name)) {
				break;
			}
		}
		for (int j=i; j < names.length - 1; j++) {
			names[j] = names[j + 1];
			if((j<names.length-2)&&names[j+2]==null) {
				names[j] = names[j+1];
				names[j+1] = null;
				break;
			}
		}


	}

}
