package practice_beginner;

import java.util.*;
//import java.util.Random;

class StatsValuesClass {

        private int[] data;
        private int sum;
        private int max;
        private int min;
        private double mean;

        public StatsValuesClass(){
            Random r = new Random();
            data = new int[100];
            for (int i = 0; i < data.length; i++){
                data[i] = r.nextInt(1000);
            }

            sum = 0;
            max = 0;
            min = 0;
            mean = 0;
        }

        public void calculate(){
            sum = 0;
            for (int i = 0; i < data.length; i++) {
                sum += data[i];
            }

            for (int i = 1; i < data.length; i++){
                if (data[i] > max){
                    max = data[i];
                } if (data[i] < min) {
                    min = data[i];
                }
            }

            mean = (double)sum/100;
        }

        //calculate が必要
        public void output(){
            String s = String.format("合計： %d, 最大値： %d, 最小値： %d, 平均値： %3.6f", sum, max, min, mean);
                System.out.println(s);
                for (int i = 0; i < data.length; i += 10){
                    // FIX ME 冗長
                    String t = String.format("%3d %3d %3d %3d %3d %3d %3d %3d %3d %3d", data[i], data[i+1], data[i+2], data[i+3], data[i+4], data[i+5], data[i+6], data[i+7], data[i+8], data[i+9]);
                    System.out.println(t);
        }
    }
}

public class StatsValues {
    public static void main(String[] args){
        StatsValuesClass sv = new StatsValuesClass();
        //sv.initialize();
        sv.calculate();
        //sv.calculate();
        sv.output();
    }
}

    

    /*
    public static void main(String[] args){
            Random r = new Random();
            int[] data = new int[100];
            int sum = 0;
            for (int i = 0; i < data.length; i++){
                data[i] = r.nextInt(1000);
                sum += data[i];
            }
            int max = data[0];
            int min = data[0];
            for (int i = 1; i < data.length; i++){
                if (data[i] > max){
                    max = data[i];
                } if (data[i] < min) {
                    min = data[i];
                }
            }
            double mean = (double)sum/100;
            String s = String.format("合計： %d, 最大値： %d, 最小値： %d, 平均値： %3.6f", sum, max, min, mean);
            System.out.println(s);
            for (int i = 0; i < data.length; i += 10){
                String t = String.format("%3d %3d %3d %3d %3d %3d %3d %3d %3d %3d", data[i], data[i+1], data[i+2], data[i+3], data[i+4], data[i+5], data[i+6], data[i+7], data[i+8], data[i+9]);
                // 冗長
                System.out.println(t);
            }
            //System.out.printf("合計： " + sum + ", 最大値： " + max(data) + ", 最小値： " + min(data));
    }
    */