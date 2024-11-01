package comRunnable;

import java.util.ArrayList;

public class MainRunnable {
    
    public MainRunnable() {
       // executaThreads();
    }
    
    private void executaThreads() throws InterruptedException {
        
        String msg1 = "oiiii";
        String msg2 = "tchau";
        
        Integer vezes = 100;
        
        // criar o "main" das threads
        MinhaTarefa t1 = new MinhaTarefa(msg1, "Thread 1", vezes);
        MinhaTarefa t2 = new MinhaTarefa(msg2, "Thread 2", vezes);
        
        // criar as threads para executar "mains" diferentes
        Thread th1 = new Thread(t1);
        Thread th2 = new Thread(t2); 
        
        // executar as threads
        th1.start();
        th2.start();
        
        // espera; comando de join() sobre a Thread
        // o join() sobre a Thread t1 fará o fluxo que executar o Join(), esperar pelo fluxo t1
        th1.join();
        th2.join();
        
        // depois da execução das threads, mostrar mensagens
        ArrayList<String> msgs1 = t1.getMsgs();
        ArrayList<String> msgs2 = t2.getMsgs();
       
       for (String s: msgs1) {
           System.out.println(s);
       }
       
       for (String s: msgs2) {
           System.out.println(s);
       }
    }
    
    public static void main(String[] args) throws InterruptedException {
        new MainRunnable().executaThreads();
    }
}
