// Intermediate Coder - 2752 : 개미
  
import java.util.Scanner;
  
public class Main_J_2752_개미_박진_TLE_95 {
  
    static int w, h;
    static int p, q;
    static long t;
      
    // 북서(0), 북동(1), 남서(2), 남동(3)
    static int[] di = { -1, -1, 1, 1 };
    static int[] dj = { -1, 1, -1, 1 };
    static int d = 3;       // di, dy의 인덱스
    static int jump = 1;    // 방향전환 없이 쭉 갈 수있는 거리
      
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // w × h인 격자 공간
        w = sc.nextInt();
        h = sc.nextInt();
        // 처음에 (p,q)에서 출발
        p = sc.nextInt();
        q = sc.nextInt();
        // 개미가 움직일 시간 t
        t = sc.nextLong();
          
        for(long i = 0; i < t; i = i++) {
              
            // 방향전환 없이 쭉 갈 수 있는 거리 계산
            // 북서(0), 북동(1), 남서(2), 남동(3)
            switch(d) {
            case 0:
                jump = Math.min(p, q);
                break;
            case 1:
                jump = Math.min(p, h - q);
                break;
            case 2:
                jump = Math.min(w - p, q);
                break;
            case 3:
                jump = Math.min(w - p, h - q);
                break;
            }
              
            if (t - i < jump)
                jump = (int) (t-i);
              
            int nexti = p + di[d] * jump;
            int nextj = q + dj[d] * jump;
            t = t - jump;
              
            if (nexti >= 0 && nextj >= 0 && nexti <= w && nextj <= h ) {
                p = nexti;
                q = nextj;
                  
                if (nexti != 0 && nextj != 0 && nexti != w && nextj != h) {
                    continue;
                }
            }
                  
            // 구석에 부딕칠 경우, 반대방향으로 방향 전환
            if (nexti == 0 && nextj == 0) {
                d = 3;
            }
            else if ((nexti == w && nextj == h)) {
                d = 0;
            }
            else if ((nexti == w && nextj == 0)) {
                d = 1;
            }
            else if ((nexti == 0 && nextj == h)) {
                d = 2;
            }
            // 경계면에 부딪칠 경우
            else if (nexti == 0) {
                if (d == 0) {   // 북서쪽으로 진행하고 있었다면,
                    d = 2;      // 남서쪽으로 방향 전환.
                }
                else {          // 북동쪽으로 진행하고 있었다면,
                    d = 3;      // 남동쪽으로 방향 전환.
                }
            }
            else if (nexti == w) {
                if (d == 2) {   // 남서쪽으로 진행하고 있었다면,
                    d = 0;      // 북서쪽으로 방향 전환.
                }
                else {          // 남동쪽으로 진행하고 있었다면,
                    d = 1;      // 북동쪽으로 방향 전환.
                }
            }
            else if (nextj == 0) {
                if (d == 2) {   // 남서쪽으로 진행하고 있었다면,
                    d = 3;      // 남동쪽으로 방향 전환.
                }
                else {          // 북서쪽으로 진행하고 있었다면,
                    d = 1;      // 북동쪽으로 방향 전환.
                }
            }
            else if (nextj == h) {
                if (d == 3) {   // 남동쪽으로 진행하고 있었다면,
                    d = 2;      // 남서쪽으로 방향 전환.
                }
                else {          // 북동쪽으로 진행하고 있었다면,
                    d = 0;      // 북서쪽으로 방향 전환.
                }
            }
        }
          
        System.out.println(p + " " + q);
          
        sc.close();
    }
}
/*

Time Limit Exceed(95)

time_space_table:
  d1.in : mem=7508k time=204ms
  d2.in : mem=8872k time=205ms
  d3.in : mem=10100k time=208ms
  d4.in : mem=10100k time=195ms
  d5.in : mem=10100k time=214ms
  d6.in : mem=10100k time=237ms
  d7.in : mem=10100k time=212ms
  d8.in : mem=10100k time=201ms
  d9.in : mem=10604k time=6401ms
 d10.in : mem=10604k time=2083ms
 d11.in : mem=10604k time=481ms
 d12.in : mem=10604k time=439ms
 d13.in : mem=10604k time=490ms
 d14.in : mem=10604k time=441ms
 d15.in : mem=10604k time=294ms
 d16.in : mem=10636k time=296ms
 d17.in : mem=10636k time=276ms
 d18.in : mem=10636k time=291ms
 d19.in : mem=10636k time=288ms
 d20.in : mem=10636k time=283ms

*/
