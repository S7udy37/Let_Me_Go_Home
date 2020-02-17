package S7udy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B10993_별찍기18_김준원 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder txt = new StringBuilder();
		// 입력효율을 높여보려는 뻘짓
		int n = (in.read() - '0') + (in.read() == '0' ? 9 : 0);

		int len = (int) Math.pow(2, n) - 1;
		boolean a[][] = new boolean[len][len * 2 - 1];
		// 높이는 2의 n제곱-1이었고, 너비는 높이 2배 -1. 불리언 배열에 별을 그릴 자리를 true로 만듬
		/*
		 
		1×1 = 2¹-1 , 1*2-1
		 *
		 
		3×5 = 2²-1 , 3*2-1
		 *****
		  * * 
		   *
		  
		7×13 = 2³-1 , 7*2-1
		 	   * 
		      * *
		  	 *   *
		    *     *
		   *       *
		  *         *
		 *************

		 */
		// 포문으로만 짤거임
		/*
		for : 삼각형 차수 
		
			for : 삼각형 껍데기를 만듬
		 	* 
		 	* *
		 	*   *
		 	*     *
		 	*       *
		 	*         *
		 	         
			for : 삼각형 아래를 닫음
		 	*************
		 
		 	. 
		 	. .
		 	.   .
		 	.*****.			이렇게 다음 삼각형은 위로 올라감
		 	.  * *  .		마지막 점이 찍힐때까지
		 	.    #    .
		 	.............
		  	
		  	# : 다음 삼각형 시작위치 지정
		  	정삼각형이 아닌 직각삼각형을 쓰는 이유는 j값을 쉽게 하기 위함.
		  	출력할때 공백 추가해서 정상적으로 나오게 할거임
			
		 */
		int toggle = -1; // 홀짝일때 위아래 바꿔주는 키
		for (int t = n, si = 0, sj = 0, i = 0, j = 0; t >= 1; t--) {
			toggle *= -1;
			// 삼각형 껍데기를 만듬
			for (i = 0; i < (int) Math.pow(2, t) - 2; i++) {
				a[si + (i * toggle)][sj] = a[si + (i * toggle)][sj + 2 * i * toggle] = true;
			}
			// 삼각형 바닥을 닫음
			for (j = 0; j < (int) Math.pow(2, t + 1) - 3; j++) {
				a[si + (i * toggle)][sj + (j * toggle)] = true;
			}
			// 삼각형대가리 최신화. 다음 삼각형시작위치를 si, sj에 저장
			si += (i - 1) * toggle;
			sj += (j / 2 - 1) * toggle;

		}
		// 출력. 홀짝이든 정삼각형모양이므로, 짝수삼각형은 역순으로 출력하게 함
		if ((n & 1) == 1) {
			for (int i = 0; i < len; i++) {
				for (int j = i; j < len - 1; j++) {
					txt.append(' ');
				}
				for (int j = 0; j < i * 2 + 1; j++) {
					txt.append(a[i][j] ? '*' : ' ');
				}
				txt.append('\n');
			}
		} else {
			for (int i = 0; i < len; i++) {
				for (int j = len - i; j < len; j++) {
					txt.append(' ');
				}
				for (int j = 0; j < (len - 1 - i) * 2 + 1; j++) {
					txt.append(a[len - 1 - i][j] ? '*' : ' ');
				}
				txt.append('\n');
			}
		}
		System.out.println(txt);
		in.close();
	}

}