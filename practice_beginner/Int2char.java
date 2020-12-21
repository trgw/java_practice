package practice_beginner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class Int2char {
    public static void main(String[] args){
        try{
                File sample3 = new File("C:/Users/egawa/vagrant/java_lessons/sample6.txt");
                FileReader filereader = new FileReader(sample3);
                List<Integer> ch = new ArrayList<>();
                int ch_read;
                while((ch_read = filereader.read()) != -1){
                    ch.add(ch_read);
                }
                filereader.close();
                char[] ch_array = new char[ch.size()];
                for (int i = 0; i < ch.size(); i++){
                    ch_array[i] = (char)(ch.get(i) + '0');
                }
                for (int i = 0; i < ch_array.length; i++){
                    System.out.println(ch.get(i));
                }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}