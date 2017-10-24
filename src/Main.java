import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

  public final static int NUM_NODES = 83;
  public final static int NUM_MSGS = 400;

  public static void main(String[] args) {
    Arc[] arcs = new Arc[NUM_MSGS];
    Node[] nodes = new Node[NUM_NODES];
    populateArray(nodes);
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
        Arc arc = new Arc(nodes[start], nodes[end], new Message(t1, t2, t3));
        arcs[i] = arc;
        i++;
      }
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void populateArray(Node[] nodes) {
    for(int k = 0; k<nodes.length; k++){
        nodes[k] = new Node(k);
    }
  }
}