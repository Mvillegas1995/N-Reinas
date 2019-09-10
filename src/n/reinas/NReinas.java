package n.reinas;

import java.util.Random;

public class NReinas {

    public static void main(String[] args) {

        int tampob = 10;
        int reinas = 8;
<<<<<<< Updated upstream
        int semilla = 3;
        float probMutacion = (float) 0.6;
=======
        int semilla =  9;
        float probCruza = (float)0.90;
        float probMutacion = (float)0.05;
>>>>>>> Stashed changes
        Random r = new Random(semilla);

        GeneticAlg x = new GeneticAlg(tampob, reinas);
        GeneticAlg y = new GeneticAlg(tampob, reinas);

        x.desordenarTablero(r);
        x.imprimirPoblacion();
        x.calcularFitness();
        x.imprimirFitness();
        // si no se llego a la solucion inicialmente se procede a aplicar cruza/mutacion para intentar llegar al optimo
        if(x.resuelto())System.out.println("termine");
        else x.generarRuleta();

        System.out.println("");
        System.out.println("El tablero escogido para la cruza es: "+x.escogerTableroCruza(r));
       
            
<<<<<<< Updated upstream
        System.out.println("");
        //System.out.println("El tablero escogido para la mutacion es: "+x.escogerTableroCruza(r));
        
        x.mutacion(r, probMutacion);
        
        

=======
        }while(!x.resuelto());
        System.out.println("Tablero Solución: ");
        System.out.println(" ");
        x.imprimirTablero(x.solucion());
>>>>>>> Stashed changes
    }

}
