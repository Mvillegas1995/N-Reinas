
package n.reinas;

import java.util.Random;

public class GeneticAlg {

    // Tamaño de la poblacion
    private final int tampob;
    // Cantidad de reinas
    private final int reinas;
    // Matriz usada para almacenar las poblaciones, cada fila es una poblacion
    private int [][] poblacion;

    private int [] fitness;
    //Con el fin de hacer un poco de elitismo
    private int mejorFitness;
    //Para recalcular el fitness y darle más probabilidad de cruza a los tableros con menos choques
    private int peorFitness;
    // Para comprobar si encuentro un tablero con la solución
    private boolean resuelto;
    private float[] ruleta;

    public GeneticAlg(int tampob, int reinas){

        this.tampob = tampob;
        this.reinas = reinas;
        peorFitness = 0;
        mejorFitness = 0;
        resuelto = false;

        poblacion = new int [tampob][reinas];
        for (int i = 0; i < tampob; i++) {
            for (int j = 0; j < reinas; j++) {
                poblacion[i][j] = j;
            }
        }

        fitness = new int [tampob];
        for (int i = 0; i < tampob; i++) {
            fitness[i] = 0;
        }
        ruleta = new float [tampob];
    }

    public GeneticAlg(int tampob, int reinas, int[][] Descendencia){
        this.tampob = tampob;
        this.reinas = reinas;
        peorFitness = 0;
        mejorFitness = 0;
        resuelto = false;
        this.poblacion = Descendencia;
    }

    public void desordenarTablero(Random r){
        for (int i = 0; i < tampob; i++) {
            for (int j=0; j<reinas; j++) {
                int posAleatoria = r.nextInt(reinas);
                int temp = poblacion[i][j];
                poblacion[i][j] = poblacion[i][posAleatoria];
                poblacion[i][posAleatoria] = temp;
            }
        }

    }

    public void imprimirPoblacion(){
        for (int i = 0; i < tampob; i++) {
            for (int j = 0; j < reinas; j++) {
                System.out.print(poblacion[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public void calcularFitness(){
        for (int k = 0; k < tampob; k++) {
            // k itera entre poblaciones
            for (int i = 0; i < reinas-1; i++) {
                // i itera el indice
                for (int j = i+1; j < reinas; j++) {
                    //j itera el hacia la derecha del tablero
                    if(Math.abs(j-i) == Math.abs(poblacion[k][j]-poblacion[k][i]))
                        fitness[k]++;
                }
            }
            // Guardo las posiciones de los tableros con peor y mejor fitness
            if(fitness[k] > fitness[peorFitness])
                peorFitness = k;
            if(fitness[k] < fitness[mejorFitness])
                mejorFitness = k;
        }
        //Comprobar si encontre la solución
        for (int i = 0; i < tampob; i++) {
            if (fitness[i] == 0) {
               resuelto = true;
            }
        }
        //imprimirFitness();
        // Aquí recalculo el fitness para que mientras mejor(más pequeño) tenga más probabilidad
        // Se hace la sustracción del peorFitness(con más choques) con todos los fitness de modo de que
        // ¿se de vuelta la tortilla?
        if(!resuelto){
            int aux = fitness[peorFitness];
            for (int i = 0; i < tampob; i++) {
                fitness[i] = aux - fitness[i];
            }
        }
    }

    public void imprimirFitness(){
        System.out.println("");
        for (int i = 0; i < tampob; i++) {
            System.out.print(fitness[i]+" ");
        }
        //Borrar o comentar luego
        System.out.println("");
        System.out.println("Tablero con mejor fitness: "+mejorFitness);
        System.out.println("Tablero con peor fitness: "+peorFitness);
    }

    public void generarRuleta(){

        int total = 0;
        for (int i = 0; i < tampob; i++) {
            total += fitness[i];
        }
        //Obtengo las proporciones de mi ruleta
        float [] proporcion = new float [tampob];
        for (int i = 0; i < tampob; i++) {
            proporcion[i] = (float)fitness[i]/total;

            if(proporcion[i] == 0)
                proporcion[i] = (float)0.000001;
        }
        //Construyo la ruleta
        ruleta[0] = proporcion[0];
        for (int i = 1; i < tampob; i++) {
            ruleta[i] = ruleta[i-1] + proporcion[i];
        }
        //imprimir ruleta solo para verificación
        //for (int i = 0; i < tampob; i++) {
        //    System.out.print(ruleta[i]+" ");
        //}
    }

    public int escogerTableroCruza(Random r){

        //Definimos un numero random 0 <= Aleatorio <= 1 para seleccionar mediante ruleta a un tablero
        int pos = 0;
        float AleatorioUno = r.nextFloat();
        System.out.println("El Lanzamiento de ruleta dio: "+ AleatorioUno);
        //Imprimir ruleta solo para verificación
        for (int i = 0; i < tampob; i++) {
            System.out.print(ruleta[i]+" ");
        }

        for (int i = 0; i < tampob; i++) {
            if(i != 0 && AleatorioUno > ruleta[i-1] && ruleta[i] > AleatorioUno){
                System.out.println("El tablero escogido es: "+i);
                pos = i;
            }
            else if(i==0 && AleatorioUno > 0 && AleatorioUno < ruleta[i]){
                System.out.println("El tablero escogido es: "+i);
                pos = i;            
            }
        }
        return pos;
    }
        
    public void cruza(Random r, float probCruza){
        
        int[]Descendencia = new int [reinas];
        int puntoCruza = r.nextInt(reinas-1);
        float seCruza = r.nextFloat();
        int posTab1 = escogerTableroCruza(r);
        int posTab2 = escogerTableroCruza(r);
        //Para evitar reproducción entre el mismo individuo
        while(posTab1 == posTab2){
            posTab2 = escogerTableroCruza(r);
        }
        int[] tablero1 = poblacion[posTab1];
        int[] tablero2 = poblacion[posTab2];
        //formula punto de cruza
        if(seCruza <= probCruza){
            for (int j = 0; j <= puntoCruza; j++) {
                Descendencia [j] = tablero1[j]; 
            }
            for (int k = puntoCruza+1; k < reinas; k++) {
                Descendencia[k] = tablero2[k];
            }
            
            System.out.println("");
            System.out.println("Punto de Cruza: "+puntoCruza);
            System.out.print("Tablero 1: ");
            for (int l = 0; l < reinas; l++) {
                
                System.out.print(tablero1[l]+" ");
                if(puntoCruza==l)System.out.print("| ");
            }
            System.out.println("");
            System.out.print("Tablero 2: ");
            for (int l = 0; l < reinas; l++) {
                
                System.out.print(tablero2[l]+" ");
                if(puntoCruza==l)System.out.print("| ");
            }
            System.out.println("");
            System.out.print("Descendencia cruzada: ");
            for (int l = 0; l < reinas; l++) {
                
                System.out.print(Descendencia[l]+" ");
                if(puntoCruza==l)System.out.print("| ");
            }
            //Corregir Tablero
            int[] auxTabCorregido = new int[reinas];
            for (int i = 0; i < reinas; i++) {
                auxTabCorregido[i] = 0;
            }
            System.out.println("");
            
            for (int i = 0; i < reinas; i++) {
                auxTabCorregido[Descendencia[i]]++; 
            }
            //System.out.print("Auxiliar repeticiones: ");
            //for (int i = 0; i < reinas; i++) {
            //    System.out.print(auxTabCorregido[i]+" ");
            //}
            //System.out.println("");
            
            //System.out.println("--------------Area de Trabajo--------------- ");
            int count = 0;
            for (int i = 0; i < reinas; i++) {
                if(auxTabCorregido[i]==2){
                    count++;
                }
            }
            //System.out.println("Count es igual a "+count);
            int i=0,j=0,k=0;
            while(count > 0 && i<8){
            
                if (auxTabCorregido[i]==2 && i<reinas) {
                  
                   //El numero i se repite dos veces
                    if (Descendencia[j]==i && j<reinas) {
                        
                        //j iterara las descendencias
                        //Busca el numero i en descendencia[j]
                        if (auxTabCorregido[k]==0 && k<reinas) {
                            //El numero en espacio k se repite 0 veces
                            Descendencia[j]=k;
                            auxTabCorregido[i]--;
                            auxTabCorregido[k]++;        
                            count--;
                       
                        }else{
                            k++;
                        }
                    }else{
                        j++;
                    }                    
                }else{
                i++;
                }            
            }
            //System.out.println(" ");
            //System.out.println("------------Fin Area de Trabajo-------------- ");
            //System.out.println("");
            //System.out.print("Auxiliar repeticiones corregido: ");
            //for (int p = 0; p < reinas; p++) {
            //    System.out.print(auxTabCorregido[p]+" "); 
            //}
            //System.out.println("");
            System.out.print("Tablero descendencia de cruza corregido: ");
            for (int  a=0; a < reinas; a++) {
                System.out.print(Descendencia[a]+" ");
            }
        }
        
        
    }

    public boolean resuelto(){
        return resuelto;
    }

}
