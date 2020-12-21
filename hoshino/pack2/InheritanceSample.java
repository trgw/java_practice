// 問1 a オブジェクトをクラスでまとめて管理する.
//    b おおまかな構造や機能をスーパークラスで作り、サブクラス（子クラス）でマイナーチェンジを記述する.

package pack2;

/*
  InheritanceSample.java  継承のためのサンプルプログラム
*/
class PlayStation{
    String CPU = "34MHz";
    String CD = "CD-ROM";
    PlayStation(){}
  
    String getCPU(){
      return CPU;
    }
    String getMedia(){
      return CD;
    }
}
  
class PlayStation2 extends PlayStation{ //PlayStationクラスを継承する
    String DVD = "DVD-ROM";
  
    PlayStation2(){
      CPU = "300MHz";   // CPUの値をおきかえる．
    }
    String getMedia(){
      return CD+" "+DVD;
      //return super.getMedia()+DVD;
    }
}
class PlayStation3 extends PlayStation{ //PlayStationクラスを継承する
    String Media = "BD-ROM";
  
    PlayStation3(){
      CPU = "3.2GHz";   // CPUの値をおきかえる．
    }
    String getMedia(){
      return Media;
      //return super.getMedia()+DVD;
    }
}
  
  
class InheritanceSample{
    public static void main(String args[]){
  
      PlayStation PS = new PlayStation();
      PlayStation2 PS2 = new PlayStation2();
      PlayStation3 PS3 = new PlayStation3();
  
      System.out.println("PS  CPU "+PS.getCPU());
      System.out.println("PS2 CPU "+PS2.getCPU()); //PS2でもgetCPUメソッドが使えることを確認する
      System.out.println("PS3 CPU "+PS3.getCPU());
  
      System.out.println("PS  Media "+PS.getMedia());
      System.out.println("PS2 Media "+PS2.getMedia()); //PS2でもgetMediaメソッドが使えることを確認する
      System.out.println("PS3 Media "+PS3.getMedia());
    }
}