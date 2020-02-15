// Memory : 48408KB
// Time : 508ms

package com.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_B7785_회사에있는사람_오인호 {
	
	public static void main(String[] args) throws IOException{
		String name, iS;
		StringBuilder sb = new StringBuilder();
		Map<String,Boolean> map = new TreeMap<String,Boolean>(Collections.reverseOrder());
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			name = st.nextToken();
			iS = st.nextToken();
			if(iS.equals("enter")) {
				map.put(name,true);
			} else {
				map.remove(name);
			}
		}
		Iterator<String> i = map.keySet().iterator();
		while(i.hasNext()) {
			sb.append(i.next()).append('\n');
		}
		System.out.println(sb);
	}
}
