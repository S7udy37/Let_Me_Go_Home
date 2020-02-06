// Intermediate Coder - 1809 : 탑

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Tower {
	int index;	// 인덱스
	int height;	// 탑의 높이
	
	public Tower(int index, int height) {
		this.index = index;
		this.height = height;
	}
}

public class Main_J_1809_탑_박진 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());	// N: 탑의 개수
		
		int heightOfCurrentTower;	// height: 현재 탑의 높이
		int[] result = new int[N+1];	// result[]: 각각의 탑에서 발사한 레이저 신호를 어느 탑에서 수신하는지 저장할 배열 (0번째 인덱스는 사용하지 않음)
		Stack<Tower> stack = new Stack<Tower>();
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			heightOfCurrentTower = Integer.parseInt(st.nextToken());
			
			while(true) {
				if (stack.isEmpty()) {	// 스택이 비어 있으면,
					result[i] = 0;	// 현재의 탑 앞에는 신호를 수신할 수 있는 탑이 존재하지 않다는 것이기 때문에, 0을 출력할 수 있도록 결과값에 0을 넣어줌.
					break;
				}
				else {
					if (stack.peek().height > heightOfCurrentTower) {	// 스택의 가장 위에 있는 높이가 현재 탑의 높이보다 높으면,
						result[i] = stack.peek().index;			// 그 위치(인덱스)를 결과값에 넣어줌.
						break;
					}
					else {			// 스택의 가장 위에 있는 높이가 현재 탑의 높이보다 낮으면,
						stack.pop();	// 필요없으므로 버리고,
						continue;	// 현재 탑의 높이보다 높은 탑을 찾기 위해 다시 되돌아감.
					}
				}
			}
			
			stack.push(new Tower(i, heightOfCurrentTower));
		}
		
		for (int i = 1; i <= N; i++) {
			System.out.print(result[i] + " ");
		}
	}
}

/* 
Success(100)
time_space_table:
  d1.in : mem=7852k time=118ms
  d2.in : mem=7852k time=141ms
  d3.in : mem=8128k time=169ms
  d4.in : mem=18456k time=836ms
  d5.in : mem=18456k time=815ms
  d6.in : mem=21692k time=1526ms
  d7.in : mem=30268k time=1490ms
  d8.in : mem=30268k time=1791ms
  d9.in : mem=30268k time=1680ms
 d10.in : mem=30268k time=1669ms
*/
