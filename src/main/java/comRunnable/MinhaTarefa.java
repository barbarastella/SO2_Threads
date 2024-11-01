package comRunnable;

import java.util.ArrayList;
import java.util.Random;

public class MinhaTarefa implements Runnable {

       private String msg;
       private String id;
       private Integer vezes;
       
       ArrayList<String> msgsSalvas;
       
       public MinhaTarefa(String msg, String id, Integer vezes)
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
               
                try {
                   msgsSalvas.add("Thread [" + id + "]: " + msg + " | i: " + i);
                   Thread.sleep(new Random().nextInt(1000));
              } catch (InterruptedException ex) {}
           }
           
           this.msg = "Tarefa [" + id + "] finalizou!";
       }

    public ArrayList<String> getMsgs() {
        return this.msgsSalvas;
    }
}
