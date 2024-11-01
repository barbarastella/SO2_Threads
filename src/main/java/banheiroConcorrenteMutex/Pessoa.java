package banheiroConcorrenteMutex;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pessoa implements Runnable {

    private Integer id;
    private String sexo;
    private Banheiro banheiro;

    public Pessoa(Integer id, String sexo, Banheiro banheiro) {
        this.id = id;
        this.sexo = sexo;
        this.banheiro = banheiro;
    }

    public String getSexo() {
        return this.sexo;
    }

    public Integer getId() {
        return this.id;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // pessoa entra no banheiro
                banheiro.entrar(this);
            } catch (InterruptedException ex) {
                Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // usa o banheiro
            Integer random = new Random().nextInt(2000);

            try {
                Thread.sleep(random);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            // sai do banheiro
            banheiro.sair(this);
        }
    }
}