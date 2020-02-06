package 알고리즘스터디_2월첫주;

import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main_J_1809_탑_이다현 {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(in.readLine());

		StringTokenizer st = new StringTokenizer(in.readLine());

		int[] numbers = new int[N];

		StringBuilder strBuf = new StringBuilder("0 ");

		for (int i = 0; i < N; i++) {

			numbers[i] = Integer.parseInt(st.nextToken());

		}

		here: for (int i = 1; i < N; i++) {

			for (int j = i - 1; 0 <= j; j--) {

				if (numbers[i] < numbers[j]) {

					strBuf.append(j + 1).append(" ");

					continue here;

				}

			}

			strBuf.append("0 ");

		}

		System.out.println(strBuf);

	}

}
