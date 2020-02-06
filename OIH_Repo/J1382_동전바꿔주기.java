/**************************************************************
    Problem: 1382
    Language: Java
    Result: Success
    Time:378 ms
    Memory:18964 kb
****************************************************************/

import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static Pair[] h = new Pair[101];
    static int[] coin = new int[10001];
    static int money, num;
    static int left, right;  // Point라는 클래스를 사용하면 더 편함
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        money = sc.nextInt();
        num = sc.nextInt();
        for(int i=0; i<num; i++) {   
            left = sc.nextInt();
            right = sc.nextInt();
            h[i] = new Pair(left, right);
        }
        coin[0] = 1;
        for(int i=0; i < num; i++) {
            for(int j = money; j > 0; j--) {
                for(int k=1; k <= h[i].right; k++) {             // 동전의 개수 (1개부터 그 동전을 가지고 있는 수)
                    if( (j - (h[i].left)*k) >= 0) {              // 동전의 종류 (얼마 짜리 동전인가 또한, 그 동전으로 현재 돈을 바꿔도 양수인가(즉 바꿀 수 있는가)
                        coin[j] += coin[j- (h[i].left * k)];     // Coin이라는 배열에 우리가 바꾸고 싶은 돈(Max)부터 
                                                                 // 0원까지 교환 가능한 방법을 탑-다운 방식으로 진행하면서 저장
                    }
                }
            }
        }
        System.out.println(coin[money]);
    }
}
class Pair<L,R> {
    final Integer left;
    final Integer right;
     
    public Pair(Integer left, Integer right) {
        this.left = left;
        this.right = right;
    }
}
