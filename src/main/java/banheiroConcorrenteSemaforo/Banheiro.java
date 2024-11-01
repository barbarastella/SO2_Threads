package banheiroConcorrenteSemaforo;
import java.util.concurrent.Semaphore;

public class Banheiro {

    private Boolean ocupado;
    private String tipoOcupacao;
    private Integer capacidade;
    private Integer ocupacao;
    
    private Integer cont;
    private Boolean trava;
    private Semaphore semaforo;

    public Banheiro(Integer capacidade) {
        
        this.ocupado = false;
        this.tipoOcupacao = "F"; // iniciando em F para poder garantir a troca
        this.capacidade = capacidade;
        this.ocupacao = 0;
        
        this.cont = 0; 
        this.trava = false;
        this.semaforo = new Semaphore(this.capacidade, true); // capacidade de 4 pessoas no banheiro
    }

    public void entrar(Pessoa p) throws InterruptedException {
        
       System.out.println("- Pessoa [" + p.getSexo() + "]: " + p.getId() + " tentando entrar");
       
       Boolean entrou = false;
        
        do {
            semaforo.acquire(); // semáforo contabiliza a entrada
       
            if (!this.ocupado && this.tipoOcupacao.equals(p.getSexo())) {
                this.ocupado = true;
                this.tipoOcupacao = p.getSexo();
                this.ocupacao++;
                
                this.trava = false;
                this.cont = 1;
                
                entrou = true;
                
                System.out.println("- Sexo atual do banheiro: " + this.tipoOcupacao);
            } else {

                if (this.trava) {
                    System.out.println("/!\\ Banheiro travou para esvaziar!");
                }
                
                if (tipoOcupacao == p.getSexo() && !this.trava) {
                    ocupacao++;
                    this.cont++;
                    
                    if (this.cont == 12) { // a cada 12 pessoas o banheiro deverá ser travado para a troca do sexo
                        this.trava = true;
                    }
                    
                    entrou = true;
                }
                else {
                    semaforo.release();
                }
            }
        } while (!entrou);
        
       System.out.println("- Pessoa[" + p.getSexo() + "] " + p.getId() + " entrou no banheiro[" + tipoOcupacao + "]");
       System.out.println("- Ocupacao: " + ocupacao + " | Capacidade: " + capacidade + "\n");
    }

    public synchronized void sair(Pessoa p) {
        
        semaforo.release();     
        
        if (semaforo.availablePermits() == capacidade) {
            this.trava = false;
                   
            if (this.tipoOcupacao.equals("F")) // garantir troca de sexo ao esvaziar     
                this.tipoOcupacao = "M";
            else this.tipoOcupacao = "F";
                
          System.out.println("\n- Banheiro vazio");
        }   
    }
}