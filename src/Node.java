public class Node {
    int NodeID;
    String status;
    double conspiratorScore = 0;
    double nonConspiratorScore = 0;
    double receivingMessageScore = 0;
    double senderMessageScore = 0;

    public Node(int ID) {
        this.NodeID = ID;
    }

    public void setConspiratorScore(double val) {
        this.conspiratorScore = val;
    }

    public void setNonConspiratorScore(double val) {
        this.nonConspiratorScore = val;
    }

    public void setReceivingMessageScore(double val) {
        this.receivingMessageScore = val;
    }

    public void setSenderMessageScore(double val) {
        this.senderMessageScore = val;
    }
}
