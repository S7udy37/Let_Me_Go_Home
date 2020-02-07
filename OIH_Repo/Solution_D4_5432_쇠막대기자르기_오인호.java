/**
() => 레이저가 빠앙
) => 막대가 끄읏
알고리즘 : 레이저가 나오면 스택의 사이즈를 계산해 현재 막대가 몇개 겹쳐져 있는지 계산하고 그만 큼 잘리니까 정답에 추가!
만일 레이져가 안나왔는데 ) 만 나올경우 막대가 끝났기때문에 막대기 조각 1개 추가(막대기가 절대 안겹치기에 1 증가해주는게 가능)!!
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_D4_5432_쇠막대기자르기_오인호 {
	
	static Stack<Character> s = new Stack<Character>();
	static int ans = 0;
	public static void main(String[] args) throws  IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			ans = 0;
			String str = br.readLine();
			int size = str.length();
			char prev = '0';
			for(int i=0; i<size; i++) {
				if(str.charAt(i) == '(') {
					s.add('('); 
				} else {
					if(prev == '(') {
						s.pop();
						ans += s.size();
					} else {
						s.pop();
						ans++;
					}
				}
				prev = str.charAt(i);
			}
			System.out.println("#" + t + " " + ans);
		}
	}
}
