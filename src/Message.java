public class Message {
    int contentId1;
    int contentId2;
    int contentId3;
    
    public Message(int id1, int id2, int id3){
        this.contentId1 = id1;
        this.contentId2 = id2;
        this.contentId3 = id3;
    }
    
    public double getValue(){
        //TODO
        return 0;
    }
    public int getContentId1() {
      return contentId1;
    }
    public int getContentId2() {
      return contentId2;
    }
    public int getContentId3() {
      return contentId3;
    }
}
