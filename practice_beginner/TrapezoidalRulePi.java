package practice_beginner;

//import java.util.*;
//import java.math.BigDecimal;

class Main {

    private double width;
    private int n;
    private double[] x;
    private double[] height;
    private double[] square;
    private double sum_square;
    private double calculated_pi;

    public Main(String[] args){
        width = 0.1;
        
        if (args.length != 0){
            width = Double.parseDouble(args[0]);
        }
        

        n = (int)(1/width);
        /*
        for (int i = 0; i <= n; i++){
            x[i] = 0;
            height[i] = 0;
            square[i] = 0;
        }
        */
        sum_square = 0;
        calculated_pi = 0;
    }

    public void calculate(){
        x = new double[n+1];
        height = new double[n+1];
        square = new double[n+1];
        x[0] = 0;
        height[0] = 1;
        square[0] = 0;
        for (int i = 1; i <= n; i++){
            x[i] = x[i-1] + width;
            height[i] = Math.sqrt(1 - Math.pow(x[i], 2));
            square[i] = (height[i-1] + height[i])/2*width; 
        }
        
        for (int i = 1; i <= n; i++){
            sum_square += square[i];
            calculated_pi = 4*sum_square;
        }
    }

    public void output(){
        System.out.println("pi = " + calculated_pi);
    }

}

public class TrapezoidalRulePi {
    
    public static void main(String[] args) {
        try {
            Main trp = new Main(args);
            trp.calculate();
            trp.output();
            } catch (NumberFormatException e){
                e.printStackTrace();
                System.out.println("1を割り切れる数値を入力してください");
            }
    }
}