package n.reinas;

import java.util.Random;

public class NReinas {

    public static void main(String[] args) {
        
        int tampob = 10;
        int reinas = 8;
        int semilla = 2;
        Random r = new Random(semilla);
        
        GeneticAlg x = new GeneticAlg(tampob, reinas);
        GeneticAlg y = new GeneticAlg(tampob, reinas);
        
        x.desordenarTablero(r);
        x.imprimirPoblacion();        
        x.calcularFitness();
        x.imprimirFitness();
        if(x.resuelto())System.out.println("termine");
        else x.generarRuleta();
        
        System.out.println("");
        System.out.println(x.random0y1(r));
        System.out.println(x.random0y1(r));
        System.out.println(x.random0y1(r));
        System.out.println(x.random0y1(r));
        
    }
    
}
