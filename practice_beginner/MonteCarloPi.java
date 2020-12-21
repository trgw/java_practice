package practice_beginner;

//import java.util.*;
import java.util.Random;

class MonteCarloPi_c {

    private int n;
    private double[] x;
    private double[] y;
    private double[] distance;
    private int count;
    private double calculate_pi;

    public MonteCarloPi_c(String[] args){
        count = 0;
        n = 1000;
        if (args.length != 0){
        n = Integer.parseInt(args[0]);
        }
        x = new double[n];
        y = new double[n];
        distance = new double[n];
    }

    public void calculate(){
        Random r = new Random();
        for (int i = 0; i < n; i++){
            x[i] = r.nextDouble();
            y[i] = r.nextDouble();
            distance[i] = Math.sqrt(Math.pow(x[i], 2) + Math.pow(y[i], 2));
            if (distance[i] < 1) {
                count ++;
            }
        }

        calculate_pi = (double) 4 * count/n;
    }

    public void output(){
        String s = String.format("pi = %f", calculate_pi);
        System.out.println(s);
    }
}

public class MonteCarloPi {
    public static void main(String[] args){
        try{
            MonteCarloPi_c mcp = new MonteCarloPi_c(args);
            mcp.calculate();
            mcp.output();
        } catch (NumberFormatException e){
            e.printStackTrace();
            System.out.println("整数値を入力してください");
        }
    }
}