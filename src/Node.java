import java.util.ArrayList;

public class Node {
    int NodeID;
    String status;
    ArrayList<Arc> SentMessages;
    ArrayList<Arc> ReceivedMessages;
    public Node(int ID){
        this.NodeID = ID;
    }
}
