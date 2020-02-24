/**
* 62312 KB
* 188 ms
*
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

class StartLink implements Comparable<StartLink> {
	int floor, score;
	StartLink(int floor, int score){
		this.floor=floor; this.score=score;
	}
	@Override
	public int compareTo(StartLink o) {
		return this.score-o.score;
	}
}

public class Main_B_5014_스타트링크 {

	static int building, start, office, up, down, ans;
	static boolean[] visited = new boolean[1000001];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		building	= Integer.parseInt(st.nextToken());
		start		= Integer.parseInt(st.nextToken());
		office 		= Integer.parseInt(st.nextToken());
		up			= Integer.parseInt(st.nextToken());
		down		= Integer.parseInt(st.nextToken());
		
		ans = 0;
				
		getOffice();
		if(!visited[office]) {
			System.out.println("use the stairs");
		}else {
			System.out.println(ans);			
		}
		
		
	}

	private static void getOffice() {
		LinkedList<StartLink> queue = new LinkedList<StartLink>();
		queue.offer(new StartLink(start, 0));
		visited[start] = true;
		StartLink current;
		
		while(!queue.isEmpty()) {
			current = queue.poll();

			if(current.floor==office) {
				ans = current.score;
				break;
			}
			
			int i=1, x;
			while(true) {
				x = current.floor+up*i;
				if(x<=building && !visited[x]) {
					queue.offer(new StartLink(x, current.score+i));
					visited[x] = true;
					i++;
				}else {
					break;
				}
			}
			
			i=1;
			while(true) {
				x = current.floor-down*i;
				if(x>0 && !visited[x]) {
					queue.offer(new StartLink(x, current.score+i));
					visited[x] = true;
					i++;
				}else {
					break;
				}
			}
		}
	}
}
