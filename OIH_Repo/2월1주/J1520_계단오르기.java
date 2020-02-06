import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] steps = new int[n+1];
        int[] dp = new int[n+1];
        int step1 = 0, step2 = 0;
        for(int i = 1; i <= n; i++)
            steps[i] = sc.nextInt();
         
        dp[1] = steps[1];
        dp[2] = steps[1] + steps[2];
        for(int i = 3; i <= n; i++) {
            step1 = steps[i] + steps[i-1] + dp[i-3];
            step2 = steps[i] + dp[i-2];
                     
            dp[i] = Math.max(step1, step2);
        }
        System.out.println(dp[n]);
    }
}
Memory : 18MB 
Runtime : 383ms
