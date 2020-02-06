import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class Main {
    static int N;
    static Stack<Point> Tower = new Stack<Point>();
    static int[] result = new int[500001];
 
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine(), " ");
        StringBuffer sb = new StringBuffer();
        int x = 0;
        Point temp;
        for (int i = 1; i <= N; i++) {
            x = Integer.parseInt(st.nextToken());
            while (true) {
                if (Tower.isEmpty()) {
                    Tower.push(new Point(x, i));
                    result[i] = 0; break;
                }
                temp = Tower.peek();
                if (temp.x > x) {
                    result[i] = temp.y;
                    Tower.push(new Point(x, i));
                    break;
                }
                Tower.pop();
            }
        }
        for(int i=1; i<=N; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
Memory : 29MB 
Runtime : 1789ms
