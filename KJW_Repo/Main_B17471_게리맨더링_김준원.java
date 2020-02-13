package 과제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B17471_게리맨더링_김준원 {
	private static short bitmask;
	private static byte n;
	private static byte[] pop;
	private static boolean[][] a;

	// 분류 후 연결성 탐색
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = (byte) Integer.parseInt(in.readLine());
		pop = new byte[n];
		StringTokenizer tk = new StringTokenizer(in.readLine(), " ");
		for (byte i = 0; i < n; i++)
			pop[i] = (byte) Integer.parseInt(tk.nextToken());
		a = new boolean[n][n];
		for (byte i = 0; i < n; i++) {
			tk = new StringTokenizer(in.readLine(), " ");
			byte k = (byte) Integer.parseInt(tk.nextToken());
			for (byte j = 0; j < k; j++)
				a[i][Integer.parseInt(tk.nextToken()) - 1] = true;
		}
		short minpopdiff = (1 << 15) - 1; // 최대값
		for (bitmask = 1; bitmask < (1 << n); bitmask++) {
			if (!isConnect())
				continue;
			short zerocnt = 0, onecnt = 0;
			for (byte i = 0; i < n; i++) {
				if ((bitmask & (1 << i)) == 0)
					zerocnt += pop[i];
				else
					onecnt += pop[i];
			}
			short diff = (short) (zerocnt > onecnt ? zerocnt - onecnt : onecnt - zerocnt);
			if (minpopdiff > diff)
				minpopdiff = diff;
		}
		if (minpopdiff == (1 << 15) - 1)
			minpopdiff = -1;
		System.out.println(minpopdiff);
	}

	static boolean isConnect() {
		byte zerocnt = 0, onecnt = 0;
		// 선거구 개수
		for (byte i = 0; i < n; i++)
			if ((bitmask & (1 << i)) == 0)
				zerocnt++;
		onecnt = (byte) (n - zerocnt);
		// 0 선거구 연결여부
		byte success = 0;
		short visit = 0;
		byte st[][] = new byte[n][2], top = 0;
		for (byte crnt = 0, to = 1, cnt = 1; crnt < n;) {
			visit |= (1 << crnt); // 현재위치 가본 곳으로 저장
			// 수가 맞았고 다 이어졌다.
			if (cnt == zerocnt) {
				success++;
				break;
			}
			// 현재위치가 0선거구가 아니면 다음구역(처음 위치 선정)
			if ((bitmask & (1 << crnt)) != 0) {
				visit -= (1 << crnt);
				crnt++;
				to = 0;
				continue;
			}
			// to를 넘었다? 이전 위치로
			if (to >= n) {
				if (top == 0) {
					cnt = 0;
					crnt++;
					continue;
				}
				crnt = st[--top][0];
				to = st[top][1];
				// visit -= (1 << crnt);
				to++;
				continue;
			}
			// 갈위치가 0선거구가 아니거나 연결되지 않았거나 가봤던 곳이면 다음 구역
			if (!a[crnt][to] || (bitmask & (1 << to)) != 0 || (visit & (1 << to)) != 0) {
				to++;
				continue;
			}
			// 모든 경우를 뚫고 다음으로
			st[top][0] = crnt; // 스택에 저장
			st[top++][1] = to;
			cnt++; // 이어진 구역 개수 추가
			crnt = to; // 이동
			to = 0; // 가볼곳 초기화
			continue;
		}
		// 초기화
		visit = top = 1;
		// 1선거구 연결여부
		for (byte crnt = 0, to = 1, cnt = 1; crnt < n;) {
			visit |= (1 << crnt); // 현재위치 가본 곳으로 저장
			// 수가 맞았고 다 이어졌다.
			if (cnt == onecnt) {
				success++;
				break;
			}
			// 현재위치가 1선거구가 아니면 다음구역(처음 위치 선정)
			if ((bitmask & (1 << crnt)) == 0) {
				visit -= (1 << crnt);
				crnt++;
				to = 0;
				continue;
			}
			// to를 넘었다? 이전 위치로
			if (to >= n) {
				if (top == 0) {
					cnt = 0;
					crnt++;
					continue;
				}
				crnt = st[--top][0];
				to = st[top][1];
				//
				to++;
				continue;
			}
			// 갈위치가 1선거구가 아니거나 연결되지 않았거나 가봤던 곳이면 다음 구역
			if (!a[crnt][to] || (bitmask & (1 << to)) == 0 || (visit & (1 << to)) != 0) {
				to++;
				continue;
			}
			// 모든 경우를 뚫고 다음으로
			st[top][0] = crnt; // 스택에 저장
			st[top++][1] = to;
			cnt++; // 이어진 구역 개수 추가
			crnt = to; // 이동
			to = 0; // 가볼곳 초기화
			continue;
		}
		if (success >= 2)
			return true;
		return false;
	}
}
