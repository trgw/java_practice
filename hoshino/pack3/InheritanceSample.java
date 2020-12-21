package pack3;

abstract class PlayStation {
    String CPU;
    String CD;
    PlayStation(){}
}

class PlayStation2 extends PlayStation {
    String DVD = "DVD-ROM";
    PlayStation2(){
        CPU = "34MHz";
    }
    String getCPU(){
        return CPU;
    }
    String getMedia(){
        return DVD;
    }
}

public class InheritanceSample {
    public static void main(String[] args) {
        PlayStation2 PS2 = new PlayStation2();
        System.out.println("PS2 CPU "+PS2.getCPU());
        System.out.println("PS2 Media "+PS2.getMedia());
    }
}