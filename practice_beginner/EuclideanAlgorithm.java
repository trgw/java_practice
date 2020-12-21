// https://ksuap.github.io/2016spring/training/elementary.html#elementary03 問5
// ユークリッドの互除法により2整数の最大公約数を表示

package practice_beginner;

//import java.math.*;

class EuclideanAlgorithm_c{

    private int input1;
    private int input2;
    //private int remainder;
    //private int quotient;
    //private int big;
    //private int small;
    private int ans;

    public EuclideanAlgorithm_c(String[] args){
        input1 = Integer.parseInt(args[0]);
        input2 = Integer.parseInt(args[1]);
        //ans = 0; //
    }

    public void calculate(){
        int remainder = input2;
        int quotient;
        int big = input1;
        int small = input2;
        ans = 0;
        while (remainder != 0){
            //System.out.println(remainder + " remainder");
            ans = remainder;
            remainder = big % small;
            if (remainder == 0){
                break;
            }
            //System.out.println(remainder + " remainder");
            //System.out.println(quotient + " quotient");
            quotient = big / small;
            //System.out.println(quotient + " quotient");
            //System.out.println(big + " big");
            big = small;
            //System.out.println(big + " big");
            //System.out.println(small + " small");
            small = remainder;
            //System.out.println(small + " small");
        }
    }

    public void output(String[] args){
        if (args.length > 2){
            System.out.println("2整数を入力してください");
        } else {
            String s = String.format("gcd(%d, %d)=%d", input1, input2, ans);
            System.out.println(s);
        }
    }
}

public class EuclideanAlgorithm {
    public static void main(String[] args){
        try{
            EuclideanAlgorithm_c ea = new EuclideanAlgorithm_c(args);
            ea.calculate();
            ea.output(args);
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println("整数を2つスペース区切りで入力してください");
        } catch (NumberFormatException e){
            e.printStackTrace();
            System.out.println("整数を2つスペース区切りで入力してください");
        }
    }
}