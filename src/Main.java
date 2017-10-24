import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public final static int NUM_NODES = 83;
  public final static int NUM_MSGS = 400;

  public static void main(String[] args) {
    Arc[] arcs = new Arc[NUM_MSGS];
    
    File f = new File("./Messages.csv");
    if (!f.exists()) {
      System.out.println("Couldn't find file");
    }
    String contents = "";
    int start, end, t1, t2, t3;
    Scanner sc = null;
    try {
      sc = new Scanner(f);
   
      sc.nextLine();
  
      int i = 0;
      while (sc.hasNextLine()) {
        start = sc.nextInt();
        end = sc.nextInt();
        t1 = sc.nextInt();
        t2 = sc.nextInt();
        t3 = sc.nextInt();
        sc.nextLine();
        Arc arc = new Arc(getNode(start), getNode(end), new Message(t1, t2, t3));
        arcs[i] = arc;
        i++;
      }
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static Node getNode(int start) {
    // TODO Auto-generated method stub
    return null;
  }
}
