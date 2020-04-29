/**
* Memory : 31,552KB
* Time : 168ms
/
package com.sw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution_보물상자비밀번호_오인호 {
	
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1; t<=T; t++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			String pwd = sc.next();
			int size = pwd.length();
			char[] value = new char[size];
			// 2차 포문으로 1차원 배열에 순서대로 값 넣기
			for(int i=0; i<4; i++) {
				for(int j=0; j<size/4; j++) {
					int index = size/4 * i + j;
					value[index] = pwd.charAt(index);
				}
			}
			Set<Integer> s = new HashSet<>();
			List<Integer> list = new ArrayList<Integer>();
			for(int k=0; k<N; k++) { // 시계방향으로 돌기때문에 -> 으로 N만큼 이동시 제자리!
				for(int i=0; i<4; i++) {
					int temp = 0;
					int cnt = size/4;
					for(int j=0; j<cnt; j++) {
					// size/4(각 변에 있는 숫자 수) * i(변이 총 4개) + j(1변이 size/4개 만큼의 숫자로 구성)
					// + k 시계방향으로 이동시키는 법 % N은 N을 넘을경우 0 으로 바꿔주는 방법 *pow 는 자릿수에 맞는 값 넣기
						temp += is(value[(cnt*i+j+k)%N])*(int)Math.pow(16, cnt-j-1);
					}
					// set 은 값이 중복값이 아닐경우 넣어주고 참을 반환
					if(s.add(temp)) list.add(temp);
				}
			}
			Collections.sort(list);
			System.out.println("#" + t + " " + list.get(list.size() - K));
		}
	}

	private static int is(char c) {
		if(Character.isDigit(c)) {
			return c-'0';
		} else return 10 + (c-'A');
	}

}
