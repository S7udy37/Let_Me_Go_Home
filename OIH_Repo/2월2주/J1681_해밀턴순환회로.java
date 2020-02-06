/**************************************************************
    Problem: 1681
    User: a1031405
    Language: Java
    Result: Success
    Time:1464 ms
    Memory:8948 kb
****************************************************************/
 
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
    static int[][] map = new int[12][12];
    static boolean[] check = new boolean[12];
    static int N, ans = Integer.MAX_VALUE;
     
    public static void main(String[] args) throws IOException{
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
         
        go(0,0,0);
        System.out.println(ans);
    }
 
    private static void go(int start, int cnt, int min) {
        check[start] = true;
        if(cnt == N-1) {
            if(map[start][0] == 0) return;
            min += map[start][0];
            if(ans > min) ans = min;
            return;
        }
        for(int i=0; i<N; i++) {
            if(check[i] == true) continue;
            if(map[start][i] != 0) {
                go(i, cnt+1, min + map[start][i]);
            }
            check[i] = false;
        }
         
    }
 
}
