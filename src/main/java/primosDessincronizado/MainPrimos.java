package primosDessincronizado;

import java.util.ArrayList;

public class MainPrimos {

    ArrayList<Integer> primes = new ArrayList<>();

    public MainPrimos() {
        // executaThreads(); 
    }

    private void executaThreads() throws InterruptedException {

        Integer total = 10000000;

        //Integer threadCount = Runtime.getRuntime().availableProcessors();
        //System.out.println("Número de núcleos disponíveis: " + threadCount);
        
        Integer threadCount = 16;
        
        Thread[] threads = new Thread[threadCount]; // vetor para poder iterar com o join()
        Integer range = total / threadCount;

        long startTime = System.nanoTime(); // início da contagem de tempo

        for (int i = 0; i < threadCount; i++) {

            int start = range * i;
            int end = (i == threadCount - 1) ? total : start + range - 1; // atribui uma parte para cada thread

            threads[i] = new Thread(new ThreadPrimos(start, end, primes));
            threads[i].start();
        }

        for (Thread T : threads) {
            T.join(); // faz a thread main esperar as outras para continuar
        }

        long endTime = System.nanoTime(); // final da contagem de tempo
        long totalTime = (endTime - startTime) / 1000000;

        System.out.println("Tempo de execução das threads: " + totalTime + " ms");
        
        //for (Integer P : primes) {
       //     System.out.println(P);
        //}
        
        System.out.println("Finalizado!");
    }

    public static void main(String[] args) throws InterruptedException {
        new MainPrimos().executaThreads();
    }
}
