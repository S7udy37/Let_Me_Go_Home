package study_0210;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_13458_시험감독 {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(in.readLine());
		long[] testers = new long[n];
		long[] test_stage = new long[n];		
		long total_cnt = 0;		
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i=0; i<n;i++) {
			testers[i] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(in.readLine());
		long m_dir = Long.parseLong(st.nextToken());
		long s_dir = Long.parseLong(st.nextToken());
		
		for(int i=0; i<n;i++) {
			testers[i]-=m_dir;
			if(testers[i]<0) {//이거 존나 매우 겁나 중요 시발......... 1/1/3 2 입력값 0나옴
				testers[i]=0;
			}
			
			if(testers[i]%s_dir==0){
				test_stage[i]=(testers[i]/s_dir);
				total_cnt+=test_stage[i];
			}else {
				test_stage[i] = (testers[i]/s_dir);
				total_cnt+=test_stage[i]+1;
			}
		}
		total_cnt+=n;
		System.out.println(total_cnt);
	}
}
