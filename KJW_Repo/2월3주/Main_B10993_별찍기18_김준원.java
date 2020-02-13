/**
* 메모리 : 28700kb
* 속도 : 176ms
* 코드수 : 2636
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B10993_별찍기18_김준원 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		// 입력.. 정신나간생각.. readline도 비효율, parseint도 비효율.. java는 비효율 덩어리
		byte n = (byte)((in.read() - '0') + (in.read() == '0' ? 9 : 0));
		//        첫번째글자를 입력받음               두번째글자가 혹시나 0이면? 첫번째글자가 1이니까 9를 더해 10이되게 함
		// 정신이 멀쩡하다면 그냥 아래처럼 쓰자
		// int n = Integer.parseInt(in.readLine());
		
		// 포문으로만 짤거임
		StringBuilder txt = new StringBuilder();
		int len = (int) Math.pow(2, n) - 1;
		// 세보니 높이는 2의 n제곱-1이었고, 너비는 길이의 2배-1 이었음. 불리언 배열에 별을 그릴 자리를 true로 만듬
		boolean a[][] = new boolean[len][len * 2 - 1];
		byte toggle = -1; // 홀짝일때 위아래 바꿔주는 키
		for (int t = n, si = 0, sj = 0, i = 0, j = 0; t >= 1; t--) {
			// t는 적을 삼각형 수, si,sj는 삼각형대가리가 있는 위치, i,j는 for문 밖에서 쓰게 미리 선언
			toggle *= -1;
			// t번째 그릴 삼각형의 높이는 2의 t제곱-1이지만 바닥은 새로운 포문으로 그릴거라 2의 t제곱-2 만큼 돌림
			for (i = 0; i < (int) Math.pow(2, t) - 2; i++)
				// 왼쪽 벽과 오른쪽 벽을 true로 변환. 한쪽은 시작위치, 한쪽은 ±2i 씩 차이남
				a[si + (i * toggle)][sj] = a[si + (i * toggle)][sj + 2 * i * toggle] = true;
			// 바닥. 2의 t+1제곱 -3 말고 단순하게 계산을 못하겠음
			for (j = 0; j < (int) Math.pow(2, t + 1) - 3; j++)
				a[si + (i * toggle)][sj + (j * toggle)] = true;
			// 삼각형대가리 최신화. 다음 삼각형은 지금 그려진 삼각형 바닥을 기준으로 -1,-1 에서부터 시작함
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
