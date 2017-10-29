public class Node {
    int NodeID;
    String status;
    int numConspiratorMessages = 0;
    int totalNumMessages = 0;
    int numMsgsWithConspirator = 0;
    double suspTopicsWithConspWeighted = 0;
    
    // not used...
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

    public int getNumSuspiciousMessages() {
      return numConspiratorMessages;
    }

    public void setNumConspiratorMessages(int numConspiratorMessages) {
      this.numConspiratorMessages = numConspiratorMessages;
    }

    public int getTotalNumMessages() {
      return totalNumMessages;
    }

    public void setTotalNumMessages(int totalNumMessages) {
      this.totalNumMessages = totalNumMessages;
    }
    
    public void incrementTotalNumMessages() {
      this.totalNumMessages++;
    }
    
    public void incrementNumConspiratorMessages() {
      this.numConspiratorMessages++;
    }
    
    public int getNumMsgsWithConspirator() {
      return numMsgsWithConspirator;
    }

    public void setNumMsgsWithConspirator(int numMsgsWithConspirator) {
      this.numMsgsWithConspirator = numMsgsWithConspirator;
    }
    
    
    public void incrementNumMsgsWithConspirator() {
      this.numMsgsWithConspirator++;
    }

    public void incrementSuspTopicsWithConspWeighted(double conspVal) {
      this.suspTopicsWithConspWeighted += conspVal;
    }
    
    public double getSuspTopicsWithConspWeighted() {
      return this.suspTopicsWithConspWeighted;
    }
}
