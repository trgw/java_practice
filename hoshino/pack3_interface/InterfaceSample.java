package pack3_interface;

/*
  InterfaceSample.java  インターフェイスの実装
*/
//abstractクラスは変数の宣言やメソッドの実装もできる
abstract class PSGame{
    String Title;
    abstract int Price();  //メソッド名の定義
    String Hardware(){
      return "PS";
    }
}

//interfaceでは定数やメソッドの仕様のみを宣言できる
interface Media{
    String CD = "CD-ROM";//定数
    //String CD; のように変数を定義するとError
  
    String Media(); //メソッド名の定義
}
  
class DQ7 extends PSGame implements Media{  
    DQ7(){
      Title = "DQ7";
    }
    int Price(){  //PSGameクラスで定義したメソッドの実装
      return 7800;
    }
    public String Media(){ //Mediaインターフェイスで定義したメソッドの実装
      return CD;
    }
}
  
class FF9 extends PSGame implements Media{
    FF9(){
      Title = "FF9";
    }
    int Price(){
      return 7800;
    }
    public String Media(){
      return CD;
    }
}
  
class InterfaceSample{
    public static void main(String args[]){
      DQ7 dq = new DQ7();
      FF9 ff = new FF9();
  
      System.out.println("Title "+dq.Title+" Price "+dq.Price()
                        +" Hardware "+dq.Hardware()+" Media "+dq.Media());
  
      System.out.println("Title "+ff.Title+" Price "+ff.Price()
                        +" Hardware "+ff.Hardware()+" Media "+ff.Media());
    }
}