public class Arc {
    Message  contents;
    Node sender;
    Node receiver;
    public Arc(Node start, Node end, String message){
        this.sender = start;
        this.receiver = end;
        this.contents = new Message(message);
    }

}
