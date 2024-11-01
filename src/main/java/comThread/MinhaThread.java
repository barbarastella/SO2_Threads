package comThread;

import java.util.ArrayList;
import java.util.Random;

public class MinhaThread extends Thread {

       private String msg;
       private String id;
       private Integer vezes;
       
       ArrayList<String> msgsSalvas;
       
       public MinhaThread(String msg, String id, Integer vezes)
       {
           this.msg = msg;
           this.id = id;
           this.vezes = vezes;
           
           msgsSalvas = new ArrayList<>();
       }
       
       @Override
       public void run() 
       {
           for (int i = 0; i < vezes; i++) {
               
               // try {
                   msgsSalvas.add("Thread [" + id + "]: " + msg + " | i: " + i);
                   
                //   Thread.sleep(new Random().nextInt(1000));
             //  } catch (InterruptedException ex) {}
               
           }
       }

    public ArrayList<String> getMsgs() {
        return this.msgsSalvas;
    }
}
