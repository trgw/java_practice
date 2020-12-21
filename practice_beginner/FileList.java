// Java 課題6 https://ksuap.github.io/2016spring/training/elementary.html#elementary03 lsコマンドを作る

package practice_beginner;

import java.io.File;

class FileList_c{

    private String current_directory_string;
    private File current_directory;
    private String given_directory_string;
    private File given_directory;
    private File[] filelist;
    private char[] char_args0;

    public FileList_c(){
        current_directory_string = new File(".").getAbsoluteFile().getParent(); //コードは理解していない, cdの絶対パスを String で取得する
        current_directory = new File(current_directory_string);
    }

    // 指定されたパス（フォルダ）のファイルリストを読込む
    public void read(String[] args){
        char_args0 = args[0].toCharArray();
        if (args.length == 0){
            filelist = current_directory.listFiles();
        } else {
            // コマンドライン引数の2文字目が":"だった場合には絶対パスを取得
            if ((char_args0.length > 1) && (char_args0[1] == ':')){
                given_directory_string = args[0];
                given_directory = new File(given_directory_string);
                filelist = given_directory.listFiles();
            } else{
                given_directory_string = current_directory_string + "\\" + args[0];
                given_directory = new File(given_directory_string);
                filelist = given_directory.listFiles();
            }
        }
    }

    //read() 呼ぶ前提
    public void output(){
        //System.out.println(given_directory_string);
        for (int i = 0; i < filelist.length; i++){
            System.out.println(filelist[i]);
        }
    }
}

public class FileList {
    public static void main(String[] args){
        if (args.length > 1){
            System.out.println("ファイルはひとつだけ指定してください");
        } else {
            try{
                FileList_c fl = new FileList_c();
                fl.read(args);
                fl.output();
            } catch (NullPointerException e){
                e.printStackTrace();
                System.out.println("そのようなファイルまたはフォルダは存在しません");
            }
        }
    }
}