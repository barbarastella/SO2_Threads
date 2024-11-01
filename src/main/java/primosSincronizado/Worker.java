package primosSincronizado;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Worker implements Runnable {

    Master master;
    Integer id;

    public Worker(Master master, Integer id) {
        this.master = master;
        this.id = id;
    }

    @Override
    public void run() {
        //pegar
        //processar
        //mandar resultado, se primo
        //voltar à labuta
        try {

            while (true) {

                Long tarefa = master.consumirTarefa();
                if (tarefa == null) {
                    System.out.println("Tarefa veio null");
                }
                if (ehPrimo(tarefa)) {
                    master.publicarResultado(tarefa, id);
                    //System.out.println(id+ ": "+ tarefa);
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Boolean ehPrimo(Long numero) {
        if (numero < 2) {
            return false;
        }
        if (numero == 2) {
            return true;
        }
        if (numero % 2 == 0) {
            return false;
        }
        for (Long i = 3L; i <= Math.sqrt(numero); i += 2) { 
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

}
