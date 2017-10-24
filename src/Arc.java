public class Arc {
    Message  contents;
    Node sender;
    Node receiver;
    public Arc(Node start, Node end, Message message){
        this.sender = start;
        this.receiver = end;
        this.contents = message;
    }

}
