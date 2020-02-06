import java.awt.Point;
import java.util.Scanner;
 
public class Main {
    static int N, W;
    static int Bound[] = new int[10001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        W = sc.nextInt();
        Point[] Bag = new Point[N];
        for(int i=0; i<N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            Bag[i] = new Point(x,y);
        }
        for (int j = 0; j < N; j++) {
            for (int k = 0; k <= W; k++) {
                if (k + Bag[j].x <= W) {
                    if(Bound[k+Bag[j].x] < Bound[k] + Bag[j].y)
                        Bound[k+Bag[j].x] = Bound[k] + Bag[j].y;
                }
            }
        }
        System.out.println(Bound[W]);
    }
}
Memory : 16MB 
Runtime : 364ms
