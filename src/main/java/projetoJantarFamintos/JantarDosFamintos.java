package projetoJantarFamintos;

public class JantarDosFamintos {

    public static void main(String[] args) {
        int numFamintos = 5; 
        
        Garfo[] garfos = new Garfo[numFamintos];
        Thread[] threadsFamintos = new Thread[numFamintos];

        for (int i = 0; i < numFamintos; i++) { // criação dos garfos
            garfos[i] = new Garfo();
        }

        for (int i = 0; i < numFamintos; i++) { // criação dos famintos com seus garfos
            Garfo garfoEsquerdo = garfos[i];
            Garfo garfoDireito = garfos[(i + 1) % numFamintos];
            
            Faminto faminto = new Faminto(i, garfoEsquerdo, garfoDireito, numFamintos);
            threadsFamintos[i] = new Thread(faminto);
            threadsFamintos[i].start();
        }
    }
}
