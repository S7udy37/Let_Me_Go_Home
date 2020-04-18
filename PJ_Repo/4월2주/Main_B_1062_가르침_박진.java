// Gold IV - 1062 : 가르침

/*
 * Combination(조합)과 HashSet 사용.
 * 84,140 kb, 544 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_1062_가르침_박진 {

	static int N, K;
	static String[] str;
	static boolean[] select = new boolean[26];
	static Set<Integer> set = new HashSet<Integer>();
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
//		System.out.println('a'-'a');
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		if(K < 5) {	// K가 5보다 작으면 한 단어도 읽을 수 없음.
			System.out.println(0);
			return;
		}
		
		str = new String[N];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine();
//			System.out.println(str[i]);
		}
		
		// "anta"와 "tica"를 읽는데 필요한 문자 a,c,i,n,t는 무조건 배워야함.
		select['a'-'a'] = true;
		select['c'-'a'] = true;
		select['i'-'a'] = true;
		select['n'-'a'] = true;
		select['t'-'a'] = true;
		set.add('a'-'a');
		set.add('c'-'a');
		set.add('i'-'a');
		set.add('n'-'a');
		set.add('t'-'a');
//		System.out.println(Arrays.toString(select));
		
		combination(0, 5);
		
		System.out.println(result);
	}

	/**
	 * 배울 알파벳의 조합을 구하는 메소드
	 * @param index
	 * @param cnt: 선택한 알파벳의 숫자
	 */
	public static void combination(int index, int cnt) {
		if (cnt == K) {
			int tempResult = 0;
			for (int i = 0; i < N; i++) {
				int size = str[i].length() - 4;
				boolean possible = true;	// 읽을 수 있는 단어인지 체크 (true:읽을수있음, false:읽을수없음)
				for (int j = 3; j < size; j++) {	// "anta"의 맨뒤'a'부터 "tica"의 맨앞't'까지 확인
					if (!set.contains(str[i].charAt(j)-'a')) {
						possible = false;
						break;
					}
				}
				if(possible) {
					tempResult++;
				}
			}
			
			result = Integer.max(result, tempResult);
			return;
		}
		
		for (int i = index; i < 26; i++) {
			if (select[i])
				continue;
			select[i] = true;
			set.add(i);
			combination(i, cnt + 1);
			select[i] = false;
			set.remove(i);
		}
	}
}

/*
26_C_K --> 21_C_K

K = 6
anta  rc  tica
anta  hello  tica
anta  car tica

a c i n t

abcdefghijklmnopqrstuvwxyz
*/