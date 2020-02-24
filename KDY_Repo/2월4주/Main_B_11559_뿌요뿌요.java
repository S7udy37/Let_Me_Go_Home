package study0219;
/**
 * 메모리 14488/ 시간 84
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B_11559_뿌요뿌요 {
	static char[][] map = new char[12][6];
	static int[][] die = new int[12][6];
	static boolean[][] checkmap;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 12; i++) {
			String line = in.readLine();
			for (int j = 0; j < 6; j++) {
				map[i] = line.toCharArray();
			}
		}
		int t_cnt = 0;

		int status = 0;
		while (true) {
			die = new int[12][6];
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.') { // 문자면 시작
						checkmap = new boolean[12][6];
						cnt = 1;
						finder(i, j);
						if (cnt >= 4) {
							status = 1;
						}
						die[i][j] = cnt; // 몇개뭉쳐있는지 카운트배열만듬
					}
				}
			}
			for (int i = 11; i >= 0; i--) { // 4개이상 뭉쳐있으면 파괴!
				for (int j = 0; j < 6; j++) {
					if (die[i][j] >= 4) {
						map[i][j] = '.';
					}
				}
			}

			for (int c = 0; c < 11; c++) {
				for (int i = 11; i > 0; i--) { // 공백메꾸기
					for (int j = 0; j < 6; j++) {
						if (map[i][j] == '.') {
							map[i][j] = map[i - 1][j];
							map[i - 1][j] = '.';
						}
					}
				}
			}
		
			if (status == 1) {
				t_cnt++;
			}else {
				break;
			}
			status = 0;
		}
		System.out.println(t_cnt);

	}

	private static void finder(int y, int x) {

		checkmap[y][x] = true;

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (ny >= 0 && nx >= 0 && ny < 12 && nx < 6 && map[ny][nx] != '.') {
				if (map[ny][nx] == map[y][x] && checkmap[ny][nx] == false) {
					cnt++;
					finder(ny, nx);
				}
			}
		}
	}
}
