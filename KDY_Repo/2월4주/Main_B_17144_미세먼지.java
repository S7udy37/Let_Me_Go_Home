import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 34120memory
 * 432ms
 * @param args
 * @throws IOException
 */

public class Main_B_17144_미세먼지 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		int[][] map = new int[r][c];
		
		int[] dy = { 1, 0, -1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		int[] cleaner = new int[2];

		// 입력
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {// 청정기 밑의좌표 y,x
					cleaner[0] = i;// 청정기 y
					cleaner[1] = j;// 청정기 x
				}
			}
		}

		int total=0;
		for (int tc = 0; tc < t; tc++) {
			int[][] a_map = new int[r][c];
			// 미세먼지 확산
			for (int y = 0; y < r; y++) {
				for (int x = 0; x < c; x++) {
					int dust = map[y][x] / 5;
					int cnt = 0;
					if (map[y][x] != -1) {
						for (int k = 0; k < 4; k++) {
							int ny = y + dy[k];
							int nx = x + dx[k];
							if (ny >= 0 && nx >= 0 && ny < r && nx < c && map[ny][nx] != -1) {
								a_map[ny][nx] += dust;
								cnt++;
							}
						}
					}
					map[y][x] -= dust * cnt;
				}
			}
			for (int y = 0; y < r; y++) {
				for (int x = 0; x < c; x++) {
					map[y][x] += a_map[y][x];
				}
			}

			// 공기청정기 작동

			// 공기청정기랑 같은l일떄
			for (int y = cleaner[0] - 2; y > 0; y--) {
				map[y][0] = map[y - 1][0];
				map[y - 1][0] = 0;
			}
			for (int y = cleaner[0] + 1; y < r - 1; y++) {
				map[y][0] = map[y + 1][0];
				map[y + 1][0] = 0;
			}

			// 맨위, 맨 아래라인일때
			for (int x = 0; x < c - 1; x++) {
				map[0][x] = map[0][x + 1];
				map[0][x + 1] = 0;
				map[r - 1][x] = map[r - 1][x + 1];
				map[r - 1][x + 1] = 0;
			}

			// 맨 오른쪽라인일때
			for (int y = 0; y < cleaner[0] - 1; y++) {
				map[y][c - 1] = map[y + 1][c - 1];
				map[y + 1][c - 1] = 0;
			}
			for (int y = r - 1; y >= cleaner[0]; y--) {
				map[y][c - 1] = map[y - 1][c - 1];
				map[y - 1][c - 1] = 0;
			}

			// 공기청정기랑 같은ㅡ일떄
			for (int y = 0; y < r; y++) {
				if (y == cleaner[0] || y == cleaner[0] - 1) {
					for (int x = c - 1; x > 1; x--) {
						map[y][x] = map[y][x - 1];
						map[y][x - 1] = 0;
					}
				}
			}
		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
//				System.out.print(map[i][j] + " ");
				total+=map[i][j];
			}
//			System.out.println();
		}
		System.out.println(total+2);
	}
}