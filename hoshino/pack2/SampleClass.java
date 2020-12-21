package pack2;

/*
  SampleClass.java  クラスの作成
*/
class StrClass{
    //データメンバ
    String  subject;  //主語
    String  verb;     //動詞
    String  object;   //目的語
    String  str;
   
    //コンストラクタ
    StrClass(String asub,String averb,String aobject){     
        subject = asub;
        verb    = averb;
        object  = aobject;
    }
   
    //メソッド
    void combine(){
        str = subject+verb+object; //文字列の加算
    }
}

    class SampleClass{
    public static void main(String args[]){
        //StrClassのオブジェクトを生成する
        StrClass sa = new StrClass("They ","arrived at ","the airport.");     

        System.out.println("Subject "+sa.subject);
        System.out.println("Verb    "+sa.verb);
        System.out.println("Object  "+sa.object);
   
        //saオブジェクトにcombineメソッドを適用する
        sa.combine();
   
        System.out.println("Str     "+sa.str);
    }
}