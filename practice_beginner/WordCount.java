// 課題7 https://ksuap.github.io/2016spring/training/elementary.html#elementary03
// wcコマンドを作る

package practice_beginner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WordCount_c {

    private String[] texts_string;
    private File[] text_files;

    private int num_line = 0;

    private String[] options = new String[8];

    public WordCount_c(String[] args) {
        options[0] = "-l";
        options[1] = "--lines";
        options[2] = "-w";
        options[3] = "--words";
        options[4] = "-c";
        options[5] = "--chars";
        options[6] = "-h";
        options[7] = "--help";
        if (Arrays.asList(options).contains(args[0])){
            texts_string = new String[args.length - 1];
            text_files = new File[args.length-1];
            for (int i = 1; i < args.length; i++) {
                char[] char_argsi = args[i].toCharArray();
                if (char_argsi[1] == ':'){
                    texts_string[i-1] = args[i];
                    text_files[i-1] = new File(texts_string[i-1]);
                } else {
                    String current_directory = new File(".").getAbsoluteFile().getParent();
                    texts_string[i-1] = current_directory + "\\" + args[i];
                    text_files[i-1] = new File(texts_string[i-1]);
                }
            }
        } else {
            texts_string = new String[args.length];
            text_files = new File[args.length];
            for (int i = 0; i < args.length; i++) {
                char[] char_argsi = args[i].toCharArray();
                if (char_argsi[1] == ':'){
                    texts_string[i] = args[i];
                    text_files[i] = new File(texts_string[i]);
                } else {
                    String current_directory = new File(".").getAbsoluteFile().getParent();
                    texts_string[i] = current_directory + "\\" + args[i];
                    text_files[i] = new File(texts_string[i]);
                }
            }
        }
    }

    public String file2string(File file) throws FileNotFoundException, IOException {
        //（テキストファイルである）file の文字列を line に入れる
        FileReader freader = new FileReader(file);
        BufferedReader in = new BufferedReader(freader);
        String line = "";
        String keep = "";
        while (keep != null) {
            keep = in.readLine();
            if (keep == null) {
                break;
            }
            num_line ++;
            line += keep + " ";
        }
        in.close();
        return line;
    }

    public int getNumChar(String str) {
        String excluded_line = str.replace(" ", "");
        char[] getnumcahr_char;
        getnumcahr_char = excluded_line.toCharArray();
        return getnumcahr_char.length;
    }

    public int getNumWord(String str){
        char[] getnumword_char = str.toCharArray();
        List<Character> getnumword_char_list = new ArrayList<>();
        for (int i = 0; i < getnumword_char.length - 1; i++){
            if ( ((getnumword_char[i] == (' ')) && (getnumword_char[i + 1] == (' ')))   == false){
                getnumword_char_list.add(getnumword_char[i]);
            }
        }
        int count_space = 0;
        for (int i = 0; i < getnumword_char_list.size(); i++){
            if (getnumword_char_list.get(i) == ' '){
                count_space ++;
            }
        }
        return count_space + 1;
    }

    // file2string する前提
    public int getNumLine(String str){
        return num_line;
    }

    public void output(String[] args){
        if (args.length == 0){
            System.out.println("テキストファイルを指定して下さい");
        } else {
            int total_char = 0;
            int total_word = 0;
            int total_line = 0;
            try{
                if (args[0].equals(options[0]) || args[0].equals(options[1])){ //line
                    System.out.println("Line");
                    for (int i = 0; i < text_files.length; i++){
                        String str = file2string(text_files[i]);
                        System.out.printf("%4d " + text_files[i].getName() + "\n",getNumLine(str));
                        total_line += getNumLine(str);
                    }
                    if (text_files.length > 1) {
                    System.out.printf("%4d Total",total_line);
                    }
                } else if (args[0].equals(options[2]) || args[0].equals(options[3])){ // word
                    System.out.println("Word");
                    for (int i = 0; i < text_files.length; i++){
                        String str = file2string(text_files[i]);
                        System.out.printf("%4d " + text_files[i].getName() + "\n",getNumWord(str));
                        total_word += getNumWord(str);
                    }
                    if (text_files.length > 1) {
                    System.out.printf("%4d Total",total_word);
                    }
                } else if (args[0].equals(options[4]) || args[0].equals(options[5])){ // char
                    System.out.println("Char");
                    for (int i = 0; i < text_files.length; i++){
                        String str = file2string(text_files[i]);
                        System.out.printf("%4d " + text_files[i].getName() + "\n",getNumChar(str));
                        total_char += getNumChar(str);
                    }
                    if (text_files.length > 1) {
                    System.out.printf("%4d Total",total_char);
                    }
                } else if (args[0].equals(options[6]) || args[0].equals(options[7])){ // help
                    System.out.println("テキストファイルを指定してください。複数ファイル指定することも可能です。\n最初に -l または --lines を入力した際には行数のみが、\n-w または --words を入力した際には単語数のみが、\n-c または --chars を入力した際には文字数のみが表示されます。");
                } else {
                    System.out.println("Char Word Line");
                    for (int i = 0; i < text_files.length; i++){
                        String str = file2string(text_files[i]);
                        System.out.printf("%4d %4d %4d " + text_files[i].getName() + "\n", getNumChar(str), getNumWord(str), getNumLine(str));
                        total_char += getNumChar(str);
                        total_word += getNumWord(str);
                        total_line += getNumLine(str);
                    }
                    if (text_files.length > 1) {
                    System.out.printf("%4d %4d %4d Total", total_char, total_word, total_line);
                    }
                }
            } catch (FileNotFoundException e){
                e.printStackTrace();
                System.out.println("指定されたファイルが見つかりません。");
            } catch (IOException e){
                e.printStackTrace();
            } catch (OutOfMemoryError e){
                e.printStackTrace();
                System.out.println("ファイルが重すぎます。");
            }
        }
    }
}

public class WordCount {
    public static void main(String[] args){
        WordCount_c wc = new WordCount_c(args);
        wc.output(args);
    }
}