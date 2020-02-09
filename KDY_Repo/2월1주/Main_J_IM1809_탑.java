package study_0203;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_J_IM1809_탑{
	public static void main(String args[]) throws Exception{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> index = new Stack<Integer>();
		
		int n = Integer.parseInt(in.readLine());
		
		int[] top = new int[n];
		int[] arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for (int i = 0; i < n; i++)
			top[i] = Integer.parseInt(st.nextToken());
		stack.push(top[n - 1]);
		index.push(n - 1);
		for (int i = n - 1; i > 0; i--) {
			if (top[i] < top[i - 1]) {
				while (stack.size() != 0 && stack.peek() < top[i - 1]) {
					arr[index.peek()] = i;
					stack.pop();
					index.pop();
				}
				stack.push(top[i - 1]);
				index.push(i - 1);
			} else {
				stack.push(top[i - 1]);
				index.push(i - 1);
			}
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<n;i++) {
			sb.append(arr[i]+" ");
		}
		System.out.println(sb);
	}
}