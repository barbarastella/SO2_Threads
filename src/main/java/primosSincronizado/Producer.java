package primosSincronizado;

public class Producer implements Runnable {

    Master master;

    public Producer(Master master) {
        this.master = master;
    }

    @Override
    public void run() {

        try {

            Long i = 1L;
            while (true) {

                master.produzirTarefa(i);
                i++;
            }
        } catch (InterruptedException e) {

        }
    }

}
