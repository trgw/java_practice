package practice_beginner;

import java.io.File;

class FileListRewrite{

    private String current_directory_string;
    private File current_directory;
    private String given_directory_string;
    private File given_directory;
    private File[] filelist;

    public FileListRewrite(){
        current_directory_string = new File(".").getAbsoluteFile().getParent(); //コードは理解していない, cdの絶対パスを String で取得する
        current_directory = new File(current_directory_string);
    }

    // 指定されたパス（フォルダ）のファイルリストを読込む
    public void read(String[] args){
        if (args.length == 0){
            filelist = current_directory.listFiles();
        } else {
            given_directory_string = current_directory_string + "\\" + args[0];
            given_directory = new File(given_directory_string);
            filelist = given_directory.listFiles();
        }
    }


    public void output(){
        //System.out.println(given_directory_string);
        for (int i = 0; i < filelist.length; i++){
            System.out.println(filelist[i]);
        }
    }

    public static void main(String[] args){
        try{
            FileListRewrite fl = new FileListRewrite();
            fl.read(args);
            fl.output();
        } catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("そのようなファイルまたはフォルダは存在しません");
        }
    }
}