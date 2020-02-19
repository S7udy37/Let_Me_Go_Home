package S7udy;

/*
 * 13056 KB
 * 76 ms
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_B11559_뿌요뿌요_김준원 {

	static char a[][] = new char[12][6];
	static boolean b[][];

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 11; i >= 0; i--) {
			for (int j = 0; j < 6; j++) {
				a[i][j] = (char) in.read(); // 한글자씩 입력
			}
			in.readLine(); // 개행문자 날림
		}
		for (int chain = 0;; chain++) { // for문이 돌때마다 연쇄
			boolean puyo = false; // 뿌요가 터졌는지 체크
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (a[i][j] != '.') {
						b = new boolean[12][6]; // 각각의 뿌요마다 새로 방문 여부 체크
						cnt = 0;
						p = a[i][j];
						if (puyo(i, j)) { // 뿌요가 4개 이상이면 방문했던 뿌요 터트림
							for (int q = 0; q < 12; q++)
								for (int p = 0; p < 6; p++)
									if (b[q][p])
										a[q][p] = '.'; // .으로 만듬
							puyo = true;
						}
					}
				}
			}
			if (!puyo) { // 하나도 못터트렸을경우
				System.out.print(chain); // 출력후 나가리
				return;
			}
			// land check
			for (int j = 0; j < 6; j++) {
				boolean isLand = false; // 바닥인지 여부
				for (int i = 0, land = 0; i < 12; i++) {
					// 바닥여부가 확인이 안되었는데 빈공간 발견시
					if (!isLand && a[i][j] == '.') {
						isLand = true; // 이것은 바닥임
						land = i;
					}
					// 바닥확인했고 뿌요발견시
					if (isLand && a[i][j] != '.') {
						// 바닥에 현재 뿌요를 붙임
						a[land++][j] = a[i][j];
						a[i][j] = '.';
					}
				}
			}
		}
	}

	static int cnt, p;

	private static boolean puyo(int i, int j) {
		// 범위체크
		if (i < 0 || i >= 12 || j < 0 || j >= 6)
			return false;
		// 빈공간과 방문여부
		if (a[i][j] == '.' || b[i][j])
			return false;
		// 찾으려는 뿌요가 맞다면
		if (a[i][j] == p) {
			cnt++;
			b[i][j] = true;
			// 사방탐색
			puyo(i - 1, j);
			puyo(i + 1, j);
			puyo(i, j - 1);
			puyo(i, j + 1);
		}
		// 찾으려는 뿌요가 아니었다면
		else
			return false;
		// 다 돌고 나오면 제일 처음 뿌요에서 총 몇개터졌는지 확인함
		if (cnt >= 4)
			return true;
		return false;
	}
}
