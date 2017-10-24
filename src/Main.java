import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static final int NUM_NODES = 83;

    public static void populateArray(Node[] nodes) {
        for(int k = 0; k<nodes.length; k++){
            nodes[k] = new Node(k);
        }
    }

    public static void main(String[] args) {
        Node[] nodeList = new Node[NUM_NODES];
        populateArray(nodeList);
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

            while (sc.hasNextLine()) {
                start = sc.nextInt();
                end = sc.nextInt();
                t1 = sc.nextInt();
                t2 = sc.nextInt();
                t3 = sc.nextInt();
                Arc arc = new Arc(null, null, contents);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
