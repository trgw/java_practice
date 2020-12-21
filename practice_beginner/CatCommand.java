// 課題8 https://ksuap.github.io/2016spring/training/elementary.html#elementary03
// Catコマンドを作る

package practice_beginner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class CatCommand_c {

    private String[] texts_strings;
    private File[] text_files;
    private String[] options = new String[8];
    private int num_files = 0;

    public CatCommand_c(String[] args) {
        options[0] = "-n";
        options[1] = "-number";
        options[2] = "-N";
        options[3] = "-name";
        options[4] = "-b";
        options[5] = "--ignore-blank";
        options[6] = "-h";
        options[7] = "-help";
        for (int i = 0; i < args.length; i++){
            char[] char_argsi = args[i].toCharArray();
            if (char_argsi.length < 4){
                continue;
            }
            if ((char_argsi[char_argsi.length-4] == '.') && (char_argsi[char_argsi.length-3] == 't') && (char_argsi[char_argsi.length-2] == 'x') && (char_argsi[char_argsi.length-1] == 't')) {
                num_files ++;
            }
        }
        texts_strings = new String[num_files];
        text_files = new File[num_files];
        for (int i = args.length-num_files; i < args.length; i++) {
            char[] char_argsi = args[i].toCharArray();
            if (char_argsi[1] == ':'){
                texts_strings[i-args.length+num_files] = args[i];
                text_files[i-args.length+num_files] = new File(texts_strings[i-args.length+num_files]);
            } else {
                String current_directory = new File(".").getAbsoluteFile().getParent();
                texts_strings[i-args.length+num_files] = current_directory + "\\" + args[i];
                text_files[i-args.length+num_files] = new File(texts_strings[i-args.length+num_files]);
            }
        }
    }

    public void output(String[] args) throws FileNotFoundException, IOException {
        if (text_files.length == 0 && args.length > 0){
            System.out.println("指定されたファイルが見つかりません。末尾の .txt も入力してください。");
        }
        if (text_files.length == 0 && args.length == 0){
            System.out.println("ファイルを指定してください。");
        }
        for (int i = 0; i < text_files.length; i++){
            FileReader freader = new FileReader(text_files[i]);
            BufferedReader in = new BufferedReader(freader);
            String keep = "";
            String keep_ = "";
            int roop = 0;
            boolean lineisBlank = false;
            if (Arrays.asList(args).contains("-N") || Arrays.asList(args).contains("-name")){
                System.out.println("===== " + args[i+args.length-num_files] + " =====");
            }
            while (keep != null) {
                if (Arrays.asList(args).contains("-n") || Arrays.asList(args).contains("-number")){
                    lineisBlank = false;
                    keep = in.readLine();
                    roop ++;
                    keep_ = roop + ": " + keep;
                    if (keep == null){
                        break;
                    }
                    if (keep.equals("")){
                        lineisBlank = true;
                    }
                    if (((Arrays.asList(args).contains("-b") || Arrays.asList(args).contains("--ignore-blank")) && lineisBlank == true) == false){
                        System.out.println(keep_);
                    }
                } else {
                    lineisBlank = false;
                    keep = in.readLine();
                    if (keep == null){
                        break;
                    }
                    if (keep.equals("")){
                        lineisBlank = true;
                    }
                    if (((Arrays.asList(args).contains("-b") || Arrays.asList(args).contains("--ignore-blank")) && lineisBlank == true) == false){
                        System.out.println(keep);
                    }
                }
            }
            in.close();
        }
    }
}

public class CatCommand {
    public static void main(String[] args){
        CatCommand_c cc = new CatCommand_c(args);
        try{
            cc.output(args);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println("指定されたファイルが見つかりません。");
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}