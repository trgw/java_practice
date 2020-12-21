package pack3;

/*
  AbstractSample.java  abstractの実装
*/

//ゲームという抽象概念を宣言するクラス
//通常，abstractクラスはそれを継承するサブクラスに必要な機能を
//宣言するために思量するここではGameという抽象概念は
//タイトルと価格を持つとする

abstract class Game{
    String Title;
    abstract int Price(); //メソッド名の定義．このクラスでは実装しない．
}

class DQ7 extends Game{
    DQ7(){
      Title = "DQ7";
    }
    int Price(){  //Gameクラスで定義したメソッドの実装
      return 7800;
    }
}

class FFX extends Game{
    FFX(){
      Title = "FFX";
    }
    int Price(){
      return 8800;
    }
}

class AbstractSample{
    public static void main(String args[]){
      DQ7 dq = new DQ7();
      FFX ff = new FFX();
      System.out.println("Title "+dq.Title+" Price "+dq.Price());
      System.out.println("Title "+ff.Title+" Price "+ff.Price());
    }
}