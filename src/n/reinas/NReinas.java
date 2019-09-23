
import java.util.Random;

public class NReinas {

    public static void main(String[] args) {
            int tampob;
            int reinas;
            int semilla;
            int iteracionesMax; 
            float probCruza;
            float probMutacion;
        
        if(args.length == 0){
            tampob = 15;
            reinas =  10;
            semilla = 1;
            iteracionesMax = 30000; 
            probCruza =  (float)0.9;
            probMutacion = (float)0.2;
        }
        //Me aseguro que se entran 6 argumentos
        if(args.length != 6){
            System.out.println("\nFaltan argumentos o puso demasiados al momento de ingresar\n");
            System.exit(0);
        }
        else{
            char c;
            for (int i = 0; i < args[4].length(); i++) {
                c = args[4].charAt(i);
                if(c == ','){
                    System.out.println("Ingrese el valor de la prob de cruza con . no con ,");
                    System.exit(0);
                }
            }
            char d;
            for (int i = 0; i < args[5].length(); i++) {
                d = args[5].charAt(i);
                if(d == ','){
                    System.out.println("Ingrese el valor de la prob de mutación con . no con ,");
                    System.exit(0);
                }
            }
            
            tampob = Integer.parseInt(args[0]);
            reinas = Integer.parseInt(args[1]);
            semilla = Integer.parseInt(args[2]);
            iteracionesMax = Integer.parseInt(args[3]); 
            probCruza = Float.parseFloat(args[4]);
            probMutacion = Float.parseFloat(args[5]);
            
            if(probCruza<0 || probCruza>1){
                System.out.println("La probabilidad de cruza debe estar entre 0 y 1");
                System.exit(0);
            }
            if(probMutacion<0 || probMutacion>1){
                System.out.println("La probabilidad de mutación debe estar entre 0 y 1");
                System.exit(0);
            }
        }
        
        
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
