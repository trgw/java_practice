package practice_beginner;

public class ComparingString{
    
    public static void main(String[] args) {
        for (String s : args) {
            if (s.equals("KSU_AP")) {
                System.out.println("渡された文字列は\"KSU_AP\"です.");
            } else {
                System.out.println("渡された文字列は\"KSU_AP\"ではなく\"" + s + "\"です.");
            }
        }
    }
}