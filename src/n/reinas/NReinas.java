package n.reinas;

import java.util.Random;

public class NReinas {

    public static void main(String[] args) {
        //Me aseguro que se entran 6 argumentos
        if(args.length != 6){
            System.out.println("Faltan argumentos o puso demasiados al momento de ingresar");
            System.exit(0);
        }
        int tampob = Integer.parseInt(args[0]);
        int reinas = Integer.parseInt(args[1]);
        int semilla = Integer.parseInt(args[2]);
        int iteracionesMax = Integer.parseInt(args[3]); 
        float probCruza = Float.parseFloat(args[4]);
        float probMutacion = Float.parseFloat(args[5]);
        
        /*int tampob = 15;
        int reinas =  15;
        int semilla = 1;
        int iteracionesMax = 30000; 
        float probCruza =  (float)0.90;
        float probMutacion = (float)0.2;*/
        
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
        System.out.println("");
        if (x.resuelto()) {     
            x.imprimirTablero(x.solucion(iteracion));
        }
        else{
            x.imprimirTablero(x.solucionNoEncontrada(iteracionesMax));
        }
    }

}
