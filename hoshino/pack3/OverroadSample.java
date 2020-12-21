package pack3;

/*
  OverloadSample.java  オーバーロードの利用
*/
class StrClass{
    String  subject;  //主語
    String  verb;     //動詞
    String  object;   //目的語
    String  str;
  
  
    StrClass(){} //コンストラクタ（１）
    StrClass(String subject,String verb,String object){   //コンストラクタ（２）
      this.subject = subject;
      this.verb    = verb;
      this.object  = object;
    }
  
  
    void combine(){ //メソッド（１）
      str = subject+verb+object;
    }
    void combine(String subject,String verb,String object){  //メソッド（２）
      str = subject+verb+object;
    }
}

class OverloadSample{
    public static void main(String args[]){
      StrClass sa[] = new StrClass[2];
  
  
      sa[0] = new StrClass();    //コンストラクタ（１）を呼び出している
      sa[1] = new StrClass("They ","arrived at  ","the airport.");  //コンストラクタ（２）を呼び出している
  
      for(int i=0;i<2;i++)
        System.out.println(i+" Subject "+sa[i].subject+" Verb "+sa[i].verb
                            +" Object "+sa[i].object);
  
      sa[0].combine("They ","arrived at ","the airport.");  //メソッド（２）を呼び出している
      sa[1].combine(); //メソッド（１）を呼び出している
  
      for(int i=0;i<2;i++) System.out.println(i+" Str     "+sa[i].str);
    }
}