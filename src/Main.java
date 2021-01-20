import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("GCODE.txt");
            Scanner sc = new Scanner(fis);
            while(sc.hasNextLine()){
                Command cmd = new Command(sc.nextLine());
            }
            sc.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}