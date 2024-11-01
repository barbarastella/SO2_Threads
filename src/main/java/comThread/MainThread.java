package comThread;

import java.util.ArrayList;

public class MainThread {
    
    public MainThread() {
       // executaThreads();
    }
    
    private void executaThreads() throws InterruptedException {
        
        // criar as threads
        MinhaThread t1 = new MinhaThread("oiiii", "Thread 1", 100); // usando MinhaThread para declarar para poder usar os métodos
        MinhaThread t2 = new MinhaThread("tchau", "Thread 2", 100); // se declarar só como Thread não terá acesso aos métodos
        
        // executar as threads
        t1.start();
        t2.start();
        
        // espera; comando de join() sobre a Thread
        // o join() sobre a Thread t1 fará o fluxo que executar o Join(), esperar pelo fluxo t1
        t1.join();
        t2.join();
        
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
        new MainThread().executaThreads();
    }
}
