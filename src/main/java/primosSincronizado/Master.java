package primosSincronizado;
import java.util.ArrayList;

public class Master {

    ArrayList<Producer> producers;
    ArrayList<Thread> tProducers;
    ArrayList<Thread> tWorkers;
    ArrayList<Worker> workers;
    Long tarefa;
    Boolean temTarefa;
    ArrayList<Long> resultados;
    Integer qtdProducers, qtdWorkers;

    public Master(int qtdProducers, int qtdWorkers, int tamTarefas) {
        producers = new ArrayList<>();
        workers = new ArrayList<>();
        resultados = new ArrayList<>();
        this.qtdProducers = qtdProducers;
        this.qtdWorkers = qtdWorkers;
        this.temTarefa = false;
        this.tProducers = new ArrayList<>();
        this.tWorkers = new ArrayList<>();
    }

    protected void init() throws InterruptedException {
        /**
         * 1.criar producers 2.criar workers 3.passar referências para os
         * objetos;
         */
        Producer p = new Producer(this);
        Thread tP = new Thread(p);
        producers.add(p);
        tProducers.add(tP);
        tP.start();

        //criar workers
        for (int i = 0; i < qtdWorkers; i++) {
            Worker k = new Worker(this, i);
            Thread tK = new Thread(k);
            workers.add(k);
            tWorkers.add(tK);
            tK.start();
        }

        for (Thread t : tWorkers) {
            t.join();
        }
        tP.join();

    }

    public synchronized void produzirTarefa(Long tarefa) throws InterruptedException {

        /*
        Problema 1: concorrência  ---> SOLUÇÃO --> SEMAFOROS OU SIMILIAR
          ** somente um processo poderá acessar o buffer para inserção, por vez
        Problema 2: produtor/consumidor
          - pré-condição: buffer precisa estar vazion
          - Nem sempre o buffer vai estar vazio
          - pós: buffer deve ser preenchido
         */
        while (temTarefa) {
            wait();
        }

        this.tarefa = tarefa;
        temTarefa = true;
        //System.out.println("Produção: " + tarefa);
        notifyAll();

    }

    public synchronized Long consumirTarefa() throws InterruptedException {
        /*
        Problema 1: concorrência
          ** somente um processo poderá acessar o buffer para consumo, por vez
        Problema 2: produtor/consumidor
          - pré-condição: buffer precisa estar cheio
          - Nem sempre o buffer vai estar cheio
          - pós: buffer deve ser consumido e liberado
         */
        while (!temTarefa) {
            wait();
        }
        try {
            temTarefa = false;
            //System.out.println("Consumo: " + tarefa);
            return tarefa;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return null;
        } finally {
            notifyAll();
        }
    }

    public synchronized void publicarResultado(Long tarefa, Integer id) {
        System.out.println(tarefa + ": " + id);
        resultados.add(tarefa);
    }

    public static void main(String[] args) throws InterruptedException {
        final double tI = System.currentTimeMillis();
        Master master = new Master(1, 10, 1);
        master.init();

        System.out.println("Tempo: " + (System.currentTimeMillis() - tI));

    }

}
