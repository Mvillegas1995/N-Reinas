package n.reinas;

import java.util.Random;

public class NReinas {

    public static void main(String[] args) {

        int tampob = 8;
        int reinas = 5;
        int semilla = 3;
        int iteracionesMax = 10;
        float probCruza = (float)0.90;
        float probMutacion = (float)0.05;
        Random r = new Random(semilla);
        GeneticAlg x = new GeneticAlg(tampob, reinas);              
        
        x.desordenarTablero(r);
        System.out.println("Población inicial");
        System.out.println("");
        x.imprimirPoblacion();
        x.calcularFitness();
        x.generarRuleta();
        int iteracion = 1;
        // si no se llego a la solucion inicialmente se procede a aplicar cruza/mutacion para intentar llegar al optimo
        do{
            System.out.println("");
            System.out.println("Generación: "+iteracion);
            
            GeneticAlg y = new GeneticAlg(tampob, reinas);
            int[] cruza;
            int pobLlena = 0;
            //Elitismo
            //y.ingresarCruza(0, x.elitismo());
            while(pobLlena < tampob){
                float seCruza = r.nextFloat();
                if(seCruza <= probCruza){ 
                    y.ingresarCruza(pobLlena,x.cruza(r, probMutacion));
                    pobLlena++;                    
                }
            }
            x = y;
            x.calcularFitness();
            x.generarRuleta();
            iteracion++;
            System.out.println("");
            x.imprimirPoblacion();

        }while(!x.resuelto() && iteracion < iteracionesMax);
      
        
        //La solucion debe imprimir, tablero solucion, iteracion, valor de fitness        
        System.out.println("Tablero Solución: ");
        x.imprimirTablero(x.solucion());
    }

}
