import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

  public final static int NUM_NODES = 83;
  public final static int NUM_MSGS = 400;
  public final static int[] SUSP_TOPICS = {7, 11, 13};
  public final static int[] KNOWN_CONSPS = {18, 21, 37, 54, 43, 49, 67, 7}; // added 7
  public final static int[] KNOWN_NON_CONSPS = {78, 48, 64, 65, 68, 74, 0, 2};
  
  public static void main(String[] args) {
    Message[][] messages  = new Message[NUM_NODES][NUM_NODES];
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
   
      System.out.println(sc.nextLine());
  
      int i = 0;
      while (sc.hasNextLine()) {
        start = sc.nextInt();
        end = sc.nextInt();
        t1 = sc.nextInt();
        t2 = sc.nextInt();
        t3 = sc.nextInt();
        sc.nextLine();
        Message message = new Message(t1,t2,t3);
        //Arc arc = new Arc(nodes[start], nodes[end], new Message(t1, t2, t3));
        messages[start][end] = message;
        i++;
      }
      sc.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    // loop through messages and add to nodes value
    for(int i = 0; i < messages.length; i++) {
      for(int j = 0; j < messages[i].length; j++) {
        if(messages[i][j] != null) {
          nodes[i].incrementTotalNumMessages();
          nodes[j].incrementTotalNumMessages();
          
          // susp topics
          for(int k = 0; k < SUSP_TOPICS.length; k++) {
            if(messages[i][j].getContentId1() == SUSP_TOPICS[k]) {
              nodes[i].incrementNumConspiratorMessages();
              nodes[j].incrementNumConspiratorMessages();
            }
            if(messages[i][j].getContentId2() == SUSP_TOPICS[k]) {
              nodes[i].incrementNumConspiratorMessages();
              nodes[j].incrementNumConspiratorMessages();
            }
            if(messages[i][j].getContentId3() == SUSP_TOPICS[k]) {
              nodes[i].incrementNumConspiratorMessages();
              nodes[j].incrementNumConspiratorMessages();
            }
          }
          // talking to conspirators
          for(int k = 0; k < KNOWN_CONSPS.length; k++) {
            if(i == KNOWN_CONSPS[k]) {
              nodes[j].incrementNumMsgsWithConspirator();
            }
            if(j == KNOWN_CONSPS[k]) {
              nodes[i].incrementNumMsgsWithConspirator();
            }
          }
        }
      }
    }
    
    // loop through nodes and get base conspirator score (conspMsgs / numMsgs)
    double[] suspMessagesScore = new double[NUM_NODES];
    double[] talkWithConspScore = new double[NUM_NODES];
    
    double averageScore = 0;
    
    for(int i = 0; i < NUM_NODES; i++) {
      double susp = nodes[i].getNumSuspiciousMessages();
      double talkConsp = nodes[i].getNumMsgsWithConspirator();
      double total = nodes[i].getTotalNumMessages();
      suspMessagesScore[i] =  susp/total;
      talkWithConspScore[i] = talkConsp/total;
//      System.out.println(nodes[i].getNumConspiratorMessages() + " " + nodes[i].getTotalNumMessages());
      System.out.println("Index " + i + " Score " + suspMessagesScore[i] + " Consp Msgs " + susp + " Msgs " + total);
      averageScore += suspMessagesScore[i];
    }
    averageScore = averageScore/NUM_NODES;
    System.out.println("Average " + averageScore);
    
    System.out.println("Starting new list:  above average consp score");
    // 0.592285 is average score of known conspirators
    // loop through nodes and print ones that are above consp average and have at least 3 msgs
    for(int i = 0; i < NUM_NODES; i++) {
      if(nodes[i].getTotalNumMessages() >= 3 &&  suspMessagesScore[i] >= 0.592285) {
        System.out.println("Index " + i + " Score " + suspMessagesScore[i] + " Suspicious Msgs " + nodes[i].getNumSuspiciousMessages()  + " Msgs " + nodes[i].getTotalNumMessages());
      }
    }
    
    System.out.println("Starting new list: Scores based on talking with conspirators");
    // loop through and print talking with consps score
    for(int i = 0; i < NUM_NODES; i++) {
      System.out.println("Index " + i + " Score " + talkWithConspScore[i] + " With Conspirator Msgs " + nodes[i].getNumMsgsWithConspirator()  + " Msgs " + nodes[i].getTotalNumMessages());
    }
    
    
    for(int i = 0; i < messages.length; i++) {
      for(int j = 0; j < messages[i].length; j++) {
        if(messages[i][j] != null) {
          
          // susp topics
          for(int k = 0; k < SUSP_TOPICS.length; k++) {
            if(messages[i][j].getContentId1() == SUSP_TOPICS[k]) {
              // talking to conspirators
              for(int m = 0; m < KNOWN_CONSPS.length; m++) {
                if(i == KNOWN_CONSPS[m]) {
                  nodes[j].incrementSuspTopicsWithConspWeighted(suspMessagesScore[i]);
                }
                if(j == KNOWN_CONSPS[m]) {
                  nodes[i].incrementSuspTopicsWithConspWeighted(suspMessagesScore[j]);
                }
              }
            }
            if(messages[i][j].getContentId2() == SUSP_TOPICS[k]) {
              // talking to conspirators
              for(int m = 0; m < KNOWN_CONSPS.length; m++) {
                if(i == KNOWN_CONSPS[m]) {
                  nodes[j].incrementSuspTopicsWithConspWeighted(suspMessagesScore[i]);
                }
                if(j == KNOWN_CONSPS[m]) {
                  nodes[i].incrementSuspTopicsWithConspWeighted(suspMessagesScore[j]);
                }
              }
            }
            if(messages[i][j].getContentId3() == SUSP_TOPICS[k]) {
              // talking to conspirators
              for(int m = 0; m < KNOWN_CONSPS.length; m++) {
                if(i == KNOWN_CONSPS[m]) {
                  nodes[j].incrementSuspTopicsWithConspWeighted(suspMessagesScore[i]);
                }
                if(j == KNOWN_CONSPS[m]) {
                  nodes[i].incrementSuspTopicsWithConspWeighted(suspMessagesScore[j]);
                }
              }
            }
          }

        }
      }
    }
    
    
    double[] suspTopicsWithConspWeighted = new double[NUM_NODES];

    System.out.println("Starting new list:  Suspicious topics with conspirators weighted by conspirator's message score");
    for(int i = 0; i < NUM_NODES; i++) {
      System.out.println("Index " + i + " Score " + nodes[i].getSuspTopicsWithConspWeighted());
    }
    
    // 2.412 is median of above scores
    // people above this
    System.out.println("Starting new list: People above median score");
    for(int i = 0; i < NUM_NODES; i++) {
      if(nodes[i].getSuspTopicsWithConspWeighted() >= 2.412) {
        System.out.println("Index " + i + " Score " + nodes[i].getSuspTopicsWithConspWeighted());
      }
    }
    
    System.out.println("Starting new list: People above first normalized score");
    for(int i = 0; i < NUM_NODES; i++) {
      if(nodes[i].getSuspTopicsWithConspWeighted() >= 2.05) {
        System.out.println("Index " + i + " Score " + nodes[i].getSuspTopicsWithConspWeighted());
      }
    }
    
    System.out.println("Starting new list: People above second normalized score");
    for(int i = 0; i < NUM_NODES; i++) {
      if(nodes[i].getSuspTopicsWithConspWeighted() >= 1.88) {
        System.out.println("Index " + i + " Score " + nodes[i].getSuspTopicsWithConspWeighted());
      }
    }
    
    System.out.println("Starting new list: People above third normalized score");
    for(int i = 0; i < NUM_NODES; i++) {
      if(nodes[i].getSuspTopicsWithConspWeighted() >= 2) {
        System.out.println("Index " + i + " Score " + nodes[i].getSuspTopicsWithConspWeighted());
      }
    }
    
    // index, m, n, z
    File file = new File("./results.csv");
    try {
      PrintWriter writer = new PrintWriter(new FileWriter(file));
      writer.println("index,m,n,z");
      for(int i = 0; i < NUM_NODES; i++) {
        writer.println(i + "," + suspMessagesScore[i] + "," + talkWithConspScore[i] + "," + nodes[i].getSuspTopicsWithConspWeighted());
      }
      
      writer.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    
  }

  public static void populateArray(Node[] nodes) {
    for(int k = 0; k<nodes.length; k++){
        nodes[k] = new Node(k);
    }
  }
}