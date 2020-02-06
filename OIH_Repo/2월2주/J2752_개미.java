
/**************************************************************
    Problem: 2752
    User: a1031405
    Language: Java
    Result: Success
    Time:213 ms
    Memory:10600 kb
****************************************************************/
 
 
import java.util.Scanner;
 
public class Main {
 
    static int X, Y, antX, antY;
    public static void main(String[] args) {
 
        Scanner sc = new Scanner(System.in);
        X = sc.nextInt();
        Y = sc.nextInt();
        antX = sc.nextInt();
        antY = sc.nextInt();
        int T = sc.nextInt();
        antX = get(X,antX,T);
        antY = get(Y,antY,T);
        System.out.println(antX + " " + antY);
    }
 
    private static int get(int t, int tar, int T) {
        if(T - (t-tar) > 0) {
            T -= (t-tar);
            if(T/t %2 == 0) {
                if(T%t ==0)  {
                    tar = t;
                } else {
                    tar = t - T%t;
                }
            }
            else {
                if(T%t ==0) {
                    tar = 0;
                } else {
                    tar = T%t;
                }
            }
        } else {
            tar += T;
        }
        return tar;
    }
 
}
