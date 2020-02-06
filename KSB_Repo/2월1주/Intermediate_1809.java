package study_0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Tower{
	int number;
	int height;
	
	public Tower(int number, int height) {
		this.number = number;
		this.height = height;
	}
}

public class Intermediate_1809 {	
	static int N;
	static int[][] tower; 
	static ArrayList<Integer> ans = new ArrayList<Integer>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;		
		N = Integer.parseInt(br.readLine());
		tower = new int[N+1][2];
				
		Stack<Tower> stack = new Stack<Tower>();
		st = new StringTokenizer(br.readLine()," ");
		for(int i=1; i<N+1; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if(stack.isEmpty()) {
				ans.add(0);				
			}else {
				while(!stack.isEmpty()) {
					if(stack.peek().height<height) {
						stack.pop();
						
						if(stack.isEmpty()) ans.add(0);
					}else {
						ans.add(stack.peek().number);
						break;
					}
					
				}				
			}
			
			stack.push(new Tower(i, height));
		}
		
		for(int i=0; i<ans.size(); i++)
			System.out.print(ans.get(i)+" ");
		
	}
}
