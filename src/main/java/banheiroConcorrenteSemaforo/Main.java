package banheiroConcorrenteSemaforo;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        
        ArrayList<Thread> threads = new ArrayList<>();
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        
        Banheiro banheiro = new Banheiro(4); // passa a capacidade por parâmetro
        
        for (int i = 0; i < 10; i++) { // criação das pessoas
            
            Pessoa p;
            
            if (i % 2 == 0) {
                p = new Pessoa(i, "F", banheiro);
            } else {
                p = new Pessoa(i, "M", banheiro);
            }

            Thread t = new Thread(p);
            t.start();
            
            pessoas.add(p);
            threads.add(t);
        }
    }
}

// a fila ficará sempre em rotação, nunca finalizando

// se demorar demais para o banheiro esvaziar, bloquear a entrada e esperar esvaziar para poder trocar de sexo
// tomar cuidado para se caso não tenha ninguém do outro sexo querendo entrar, não deixar o banheiro para sempre em espera
