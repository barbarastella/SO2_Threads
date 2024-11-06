package projetoJantarFamintos;

public class Faminto implements Runnable {

    private int id;
    private Garfo garfoEsquerdo;
    private Garfo garfoDireito;
    private int numFamintos;
    private boolean temDireito, temEsquerdo;

    public Faminto(int id, Garfo garfoEsquerdo, Garfo garfoDireito, int numFamintos) {
        this.id = id;
        this.garfoEsquerdo = garfoEsquerdo;
        this.garfoDireito = garfoDireito;
        this.numFamintos = numFamintos;
        
        // iniciar sem garfos
        this.temDireito = false;
        this.temEsquerdo = false;
    }

    private void pensar() throws InterruptedException {
        System.out.println("Faminto[" + id + "]: pensando");
        Thread.sleep(2000);
        System.out.println("Faminto[" + id + "]: terminou de pensar");
    }

    private void comer() throws InterruptedException {
        while (true) {
            
            if (this.temDireito && this.temEsquerdo) { // precisa ter os dois garfos para poder comer
                
                System.out.println("Faminto[" + id + "]: comendo");
                Thread.sleep(2000);
                System.out.println("Faminto[" + id + "]: terminou de comer");

                garfoEsquerdo.soltar();
                garfoDireito.soltar();
                
                this.temDireito = false; 
                this.temEsquerdo = false;

                break;
            }
            
           // lógica 1 para evitar deadlock: famintos pares tentam pegar o garfo esquerdo primeiro, faminto ímpares tentam pegar o garfo direito primeiro

            if (id % 2 == 0) { 
                if (!garfoEsquerdo.isOcupado()) {
                    garfoEsquerdo.pegar();
                    temEsquerdo = true;

                    if (!garfoDireito.isOcupado()) {
                        garfoDireito.pegar();
                        temDireito = true;
                    } else {
                        garfoEsquerdo.soltar();
                        temEsquerdo = false;
                    }
                }
            } else {
                if (!garfoDireito.isOcupado()) {
                    garfoDireito.pegar();
                    temDireito = true;

                    if (!garfoEsquerdo.isOcupado()) {
                        garfoEsquerdo.pegar();
                        temEsquerdo = true;
                    } else {
                        garfoDireito.soltar();
                        temDireito = false;
                    }
                }
            }
            
            /* // lógica 2 para evitar deadlock: o último faminto começa pegando o garfo contrário aos outros

            if (id == numFamintos - 1) {
               garfoEsquerdo.pegar();
               temEsquerdo = true;
               
               garfoDireito.pegar();
               temDireito = true;   
           }
           else {
               garfoDireito.pegar();
               temDireito = true;               
              
               garfoEsquerdo.pegar();
               temEsquerdo = true;
           } */
        }
    }

    @Override
    public void run() {
        
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Garfo getGarfoEsquerdo() {
        return garfoEsquerdo;
    }

    public Garfo getGarfoDireito() {
        return garfoDireito;
    }
}
