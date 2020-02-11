import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main{
 
    public static void main(String[] args) throws IOException {
         
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(in.readLine());
        int count = Integer.parseInt(st.nextToken());
        int dx = 1;
        int dy = 1;
        count = count%(W*H);
        while (0 < count) {
            if((x == W&&y==H)||(x == W&&y==0)||(x == 0&&y==H)||(x == 0&&y==0)){
                dx = -dx;
                dy = -dy;
            } else if (y == H || y == 0) {
                dy = -dy;
            }else if (x == W || x == 0)  {
                dx = -dx;
            }
            x = x + dx;
            y = y + dy;
            count--;
        }
        System.out.println(x + " " + y);
 
    }
 
}
