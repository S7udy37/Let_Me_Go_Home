import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_J_Im_2752_개미_정세린 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(br.readLine());

		int[][] dh = { {0, 0}, { 1, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 } };
		int dir = 1;
		int move = 0;

		while (true) {
			switch (dir) {
			// 오른쪽 위
			case 1:
				move = (W - x < H - y) ? W - x : H - y;
				x = x + move;
				y = y + move;
				t = t - move;
				if (t <= 0)
					break;
				// 오른쪽 벽에 닿았으면 왼쪽위, case2
				if (x == W)
					dir = 2;
				// 위 벽에 닿았으면 오른쪽 아래, case4
				if (y == H)
					dir = 4;
				break;

			// 왼쪽 위
			case 2:
				move = (x < H - y) ? x : H - y;
				x = x - move;
				y = y + move;
				t = t - move;
				if (t <= 0)
					break;
				// 왼쪽 벽에 닿았으면 오른쪽위, case1
				if (x == 0)
					dir = 2;
				// 위 벽에 닿았으면 왼쪽 아래, case4
				if (y == H)
					dir = 3;
				break;

			// 왼쪽 아래
			case 3:
				move = (x < y) ? x : y;
				x = x - move;
				y = y - move;
				t = t - move;
				if (t <= 0)
					break;
				// 왼쪽 벽에 닿았으면 오른쪽아래, case4
				if (x == 0)
					dir = 4;
				// 아래 벽에 닿았으면 왼쪽 위, case2
				if (y == 0)
					dir = 2;
				break;

			// 오른쪽 아래
			case 4:
				move = (W - x < H - y) ? W - x : H - y;
				x = x + move;
				y = y + move;
				t = t - move;
				if (t <= 0)
					break;
				// 오른쪽 벽에 닿았으면 왼쪽아래, case3
				if (x == W)
					dir = 3;
				// 아래 벽에 닿았으면 오른쪽 위, case1
				if (y == 0)
					dir = 1;
				break;

			}
			if (t <= 0)
				break;
		}
		if (t < 0) {
			x = x + dh[dir][0] * t;
			y = y + dh[dir][1] * t;
		}

		System.out.println(x + " " + y);
	}

}
