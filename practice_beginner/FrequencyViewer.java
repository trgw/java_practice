//用途 テキストファイルの文章の各単語の出現回数を大文字、小文字を区別せずにカウントする。
//2016年度 発展プログラミング演習 練習問題 初級 https://ksuap.github.io/2016spring/training/elementary.html#elementary06 問9
//File file に読み込むテキストファイルを指定してください L

package practice_beginner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

class FrequencyViewwer_c {

    
    private static List<String> terms = new ArrayList<>();
    private String[] terms_duplicated_array;

    private boolean error = false;
    private HashMap<String, Integer> terms_hashmap = new HashMap<>();

	//comment 受け取ったテキストを space(" ") で split し、小文字にして重複を許して並べる：String[] term。
    public FrequencyViewwer_c(String filepath) throws FileNotFoundException, IOException{
        String line = "";
        String keep = "";
        
        File file = new File (filepath);
		FileReader freader = new FileReader(file);
		BufferedReader in = new BufferedReader(freader);

        while (keep != null) {
            //keep = in.readLine();
            keep = in.readLine();
            if (keep == null) {
                break;
            }
            line += keep + " ";
        }
        in.close();

        /*
        try {
            
        } catch (FileNotFoundException e) {
			error = true;
			e.printStackTrace();
		} catch (IOException e) {
			error = true;
			e.printStackTrace();
        }
        */
        
        String excluded_line = line.toLowerCase().replace(",", "").replace(".", "").replace(":", "").replace(";", "");
        terms_duplicated_array  = new String[excluded_line.split(" ").length];
        terms_duplicated_array = excluded_line.split(" ");
    	for (int i = 0; i < excluded_line.split(" ").length; i++){
            terms.add(terms_duplicated_array[i]);
        }
        Collections.sort(terms);

    }

    //
    // count という名前から連想できない
    public void count() {
        // duplicated_num_frequency_array に各語の出現回数を記録（単語は重複あり）
        // ↓ 例
        // abc are are are as as
        //   1   1   2   3  1  2
        Arrays.sort(terms_duplicated_array);
        int[] duplicated_num_frequency_array = new int[terms_duplicated_array.length];
        int count = 1;
        for (int i = 0; i < terms.size(); i++){
            if (i == 0){
                duplicated_num_frequency_array[i] = 1;
            } else {
                if (terms_duplicated_array[i].equals(terms_duplicated_array[i-1])){
                    count ++;
                    duplicated_num_frequency_array[i] = count;
                } else {
                    if (duplicated_num_frequency_array[i-1] == 1) {
                        duplicated_num_frequency_array[i] = count;
                        count = 1;
                    } else {
                        count = 1;
                        duplicated_num_frequency_array[i] = count;
                    }
                }
            }
        }

        // terms_duplicated_array をセット
        HashSet<String> set_terms_set = new HashSet<>();
        for (int i = 0; i < terms_duplicated_array.length; i++) {
    		set_terms_set.add(terms_duplicated_array[i]);
        }
        // HashSet である set_terms_set を配列に変換し sort
        String[] set_terms_array = new String[set_terms_set.size()];
    	set_terms_array = set_terms_set.toArray(new String[set_terms_set.size()]);
        Arrays.sort(set_terms_array);
        
        // list である set_num_frequency_list に各語の出現回数を追加（重複なし）
        // abc are as
        //   1   3  2
        List<Integer> set_num_frequency_list = new ArrayList<Integer>();
        for (int i = 0; i < terms_duplicated_array.length-1; i++) {
    		if (duplicated_num_frequency_array[i] >= duplicated_num_frequency_array[i+1]) {
				set_num_frequency_list.add(duplicated_num_frequency_array[i]);
			}
    	}
        set_num_frequency_list.add(duplicated_num_frequency_array[duplicated_num_frequency_array.length-1]);
        // list である set_num_frequency_list を配列に変換
        int[] set_num_frequency_array = new int[set_num_frequency_list.size()];
        for (int i = 0; i < set_num_frequency_array.length; i++){
            set_num_frequency_array[i] = set_num_frequency_list.get(i);
        }

        /*
        //2つの配列から HashMap を作成
        for (int i = 0; i < set_terms_array.length; i++){
            terms_hashmap.put(set_terms_array[i], set_num_frequency_array[i]);
        }
        */

        //2つのリストから HashMap を作成
        //リストを作成
        List<String> set_terms_list = new ArrayList<>();
        for (int i = 0; i < set_terms_array.length; i++){
            set_terms_list.add(set_terms_array[i]);
        }
        // HashMap terms_hashmap に put
        for (int i = 0; i < set_terms_list.size(); i++){
            terms_hashmap.put(set_terms_list.get(i), set_num_frequency_list.get(i));
        }

        /*
        //後に output() を呼ぶ前提・・・×
        if (error == true) {
            terms_hashmap.clear();
        }
        */
    }

    public void output() {
        for(Iterator<String> iterator = terms_hashmap.keySet().iterator(); iterator.hasNext(); ) {
            String key = iterator.next();
            System.out.println(key + ": " + terms_hashmap.get(key));
        }
    }
}

public class FrequencyViewer {

    
    public static final String INPUTFILEPATH = "D:/workspace/file.txt"; //定数はすべて大文字で書く

    public static void main(String[] args){
        try{
            FrequencyViewwer_c fv = new FrequencyViewwer_c(INPUTFILEPATH);
            fv.count();
            fv.output();
        } catch (FileNotFoundException e){
			//error = true;
			e.printStackTrace();
			System.out.println("ファイルが見つかりません。 \nD:/workspace に file.txt があるか確認してください。" + INPUTFILEPATH + "が存在しません。");
        } catch (IOException e) {
			//error = true;
			e.printStackTrace();
			System.out.println("BufferedReaderクラスのオブジェクトを close した後に readLine() しています。"); //起こりえない
        }
    }
}

        /*
        Map<String, Integer> wordFrequency; //空のHashmap 
		List<String> wordList; //読み込んだ単語のリスト
		int value = 0;
		for (int i = 0; i < wordList.size(); i++) {
			if (wordFrequency.containsKey(wordList.get(i))) {
				value = wordFrequency.get(wordList.get(i));
				wordFrequency.put(wordList.get(i), value + 1);
			}else{
				wordFrequency.put(wordList.get(i), 1);
			}
        }
        */
        