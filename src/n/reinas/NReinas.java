package n.reinas;

import java.util.Random;

public class NReinas {

    public static void main(String[] args) {

        int tampob = 10;
        int reinas = 8;
        int semilla = 5;
        float probCruza = (float)0.90;
        float probMutacion = (float)0.05;
        Random r = new Random(semilla);

        GeneticAlg x = new GeneticAlg(tampob, reinas);       
        
        
        x.desordenarTablero(r);
        System.out.println("Población inicial");
        System.out.println("");
        x.imprimirPoblacion();
        System.out.println("");
        x.calcularFitness();
        x.generarRuleta();
        int iteracion = 0;
        // si no se llego a la solucion inicialmente se procede a aplicar cruza/mutacion para intentar llegar al optimo
        do{
            System.out.println("Descendencia: "+iteracion);
            
            GeneticAlg y = new GeneticAlg(tampob, reinas);
            int[] cruza = new int[reinas];
            int pobLlena = 0;
            //Elitismo
            //y.ingresarCruza(0, x.elitismo());
            while(pobLlena != tampob){
                cruza = x.cruza(r, probCruza, probMutacion);
                if(cruza != null){
                    
                    y.ingresarCruza(pobLlena, cruza);
                    pobLlena++;
                }
            }
            x = y;
            x.calcularFitness();
            x.generarRuleta();
            iteracion++;
            System.out.println("");
            x.imprimirPoblacion();
        }while(!x.resuelto());
        System.out.println("Tablero Solución: ");
        x.imprimirTablero(x.solucion());
    }

}
