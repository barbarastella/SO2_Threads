package projetoJantarFamintos;

public class Garfo {

    private boolean ocupado;

    public Garfo() {
        this.ocupado = false;
    }

    public synchronized boolean isOcupado() {
        return ocupado;
    }

    public synchronized void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public synchronized void pegar() throws InterruptedException {
        
        while (true) {
            
            if (!this.isOcupado()) { // se o garfo está desocupado, ele pega
                this.setOcupado(true);
                break;
            } else {
                wait(); // se estiver ocupado, aguarda
            }
        }
    }

    public synchronized void soltar() {
        this.setOcupado(false); 
        notifyAll(); // aviso para as threads de que o garfo está desocupado
    }
}
