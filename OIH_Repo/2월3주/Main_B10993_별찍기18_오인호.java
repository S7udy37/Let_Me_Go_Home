// Memory : 31944KB
// Time : 224ms

package com.acmicpc;

import java.util.Arrays;
import java.util.Scanner;

public class Main_B10993_별찍기18_오인호 {

	static int[] len = {0, 1 ,3, 7, 15 ,31, 63, 127, 255, 511, 1023};
	static char[][] map = new char[1024][2048];
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int i =1; i<1024; i++) {
			Arrays.fill(map[i], ' ');
		}
		StringBuilder sb = new StringBuilder();
		int k = 0;
		if(N%2 == 1) {
			printing(0,len[N],N,0);
			for(int i=1; i<=len[N]; i++) {
				for(int j=1; j<=len[N]+k; j++) {
					sb.append(map[i][j]);
				}
				if(i == len[N]) continue;
				sb.append('\n');
				k++;
			}
		}
		else {
			k = 1;
			printing(1,len[N],N,0);
			for(int i=1; i<=len[N]; i++) {
				for(int j=1; j<=2*len[N]-k; j++) {
					sb.append(map[i][j]);
				}
				if(i == len[N]) continue;
				sb.append('\n');
				k++;
			}
		}
		
		System.out.println(sb);
	}
	private static void printing(int x, int y, int l, int cnt) {
		int type = l%2;
		int xx = 0, yy = 0;
		if(type == 1) {
			xx = x + 1; yy = y;
			map[xx][yy] = '*';
			for(int i = len[l]-1; i >=1; i--) {
				if(i==len[l]-1) {
					map[xx+i][yy] = '*';
				    for(int j =1; j<=2*len[l]-1;j++) {
				    	map[xx+i][yy-len[l]+j] = '*';
				    }
				    continue;
				}
				   map[xx+i][yy-i] = '*';
				   map[xx+i][yy+i] = '*';

				}
			xx = x + len[l];
		} else {
			xx = x - cnt/2; yy = y;
			map[xx+len[l]-1][yy] = '*';
			for(int i = 1; i<len[l]; i++) {
				if(i==len[l]-1) {
				    for(int j=1; j<=2* len[l]-1;j++) {
				    	map[xx][yy-len[l]+j] = '*';
				    }
				    continue;	
				}
				   map[xx-i+len[l]-1][yy-i] = '*';
				   map[xx-i+len[l]-1][yy+i] = '*';
				}
		}
		if(l>=2) {
			printing(xx,yy,l-1, len[l]);
		}
	}

}
