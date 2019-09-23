


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
    private int tableroSolucion;
    private int fitnessNoSolucion;
    private float[] ruleta;

    public GeneticAlg(int tampob, int reinas){

        this.tampob = tampob;
        this.reinas = reinas;
        mejorFitness = 0;
        resuelto = false;
        peorFitness = 0;
        for (int i = reinas-1; i > 0; i--) {
            peorFitness += i ;
        }
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
            // Guardo la posición con mejor fitness
            if(fitness[k] < fitness[mejorFitness]){
                mejorFitness = k;
                fitnessNoSolucion = fitness[k];
            }
        }
        //Comprobar si encontre la solución
        for (int i = 0; i < tampob; i++) {
            if (fitness[i] == 0) {
               resuelto = true;
               tableroSolucion = i;
            }
        }
        //imprimirFitness();
        // Aquí recalculo el fitness para que mientras mejor(más pequeño) tenga más probabilidad
        // Se hace la sustracción del peorFitness posible con todos los fitness de modo de que
        // ¿se de vuelta la tortilla?
        if(!resuelto){
            for (int i = 0; i < tampob; i++) {
                fitness[i] = peorFitness - fitness[i];
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
        //System.out.println("El Lanzamiento de ruleta dio: "+ AleatorioUno);
        /*Imprimir ruleta solo para verificación
        for (int i = 0; i < tampob; i++) {
            System.out.print(ruleta[i]+" ");
        }*/

        for (int i = 0; i < tampob; i++) {
            if(i != 0 && AleatorioUno > ruleta[i-1] && ruleta[i] > AleatorioUno){
                //System.out.println("El tablero escogido es: "+i);
                pos = i;
            }
            else if(i==0 && AleatorioUno > 0 && AleatorioUno < ruleta[i]){
                //System.out.println("El tablero escogido es: "+i);
                pos = i;            
            }
        }
        return pos;
    }
        
    public int[] cruza(Random r, float probMutacion){
        
        int[]Descendencia = new int [reinas];
        int puntoCruza = r.nextInt(reinas-1);       
        int posTab1 = escogerTableroCruza(r);
        int posTab2 = escogerTableroCruza(r);           
        //Para evitar reproducción entre el mismo individuo
        while(posTab1 == posTab2){
            posTab2 = escogerTableroCruza(r);
        }
        int[] tablero1 = poblacion[posTab1];
        int[] tablero2 = poblacion[posTab2];
        //Cruzando
        for (int j = 0; j <= puntoCruza; j++) {
            Descendencia [j] = tablero1[j]; 
        }
        for (int k = puntoCruza+1; k < reinas; k++) {
            Descendencia[k] = tablero2[k];
        }           
        //Corregir Tablero
        //Se necesita de un auxiliar para saber cuantas números se repiten
        int[] auxTabCorregido = new int[reinas];
        for (int i = 0; i < reinas; i++) {
            auxTabCorregido[i] = 0;
        }
        for (int i = 0; i < reinas; i++) {
            auxTabCorregido[Descendencia[i]]++; 
        }
        //Para efectuar las correciones se necesita saber cuantos números repetidos hay
        //se guardará el la variable count.
        int count = 0;
        for (int i = 0; i < reinas; i++) {
            if(auxTabCorregido[i] == 2){
                count++;
            }
        }
        //Comienza la corrección, Pos2 = Número repetido, pos0 = Posición de número que no está
        int pos2 = Integer.MAX_VALUE; 
        int pos0 = Integer.MAX_VALUE;
        while(count != 0){
            for (int i = 0; i < reinas; i++) {
                //Guardo el número repetido
                if(auxTabCorregido[i] == 2)
                    pos2 = i;
            }
            for (int i = 0; i < reinas; i++) {
                //Guardo el número que no está en el tablero
                if(auxTabCorregido[i] == 0)
                    pos0 = i;
            }
            int j = 0;
            while(true){
                //Guardo el número que no está en la posición del número repetido
                if(Descendencia[j] == pos2){
                    Descendencia[j] = pos0;
                    auxTabCorregido[pos2]--;
                    auxTabCorregido[pos0]++;
                    count--;
                    break;
                }
                j++;
            }        
        }          
        //Mutación
        float probElegida = r.nextFloat();
        if(probElegida <= probMutacion){
            Descendencia = mutacion(r,Descendencia);
        }
        return Descendencia;
    }

    public int[] mutacion(Random r , int[] Descendencia){
        
        int[] DescendenciaMutada = Descendencia;               
        //Se escoge las posiciones a mutar
        int AleatorioUno = r.nextInt(reinas);
        int AleatorioDos = r.nextInt(reinas);
        //Para que no se repitan los números escogidos
        while(AleatorioUno == AleatorioDos){   
            AleatorioDos = r.nextInt(reinas);
        }
        int CambioUno = Descendencia[AleatorioDos];
        int CambioDos = Descendencia[AleatorioUno];
       
        DescendenciaMutada[AleatorioUno] = CambioUno;
        DescendenciaMutada[AleatorioDos] = CambioDos;
  
        return DescendenciaMutada;       

    }
    
    public boolean resuelto(){
        return resuelto;
    }
    
    public int[] elitismo(){
        return poblacion[mejorFitness];
    }
    
    public void ingresarCruza(int pos, int[] cruza){
        poblacion[pos] = cruza;
    }
    
    public void imprimirTablero(int pos){
        for (int i = 0; i < reinas; i++) {
            System.out.print(poblacion[pos][i]+" ");
        }
    }
    
    public int solucion(int iteracion){
        System.out.println("La solución se encontro en la iteración: "+iteracion);
        System.out.println("El tablero que se encontró tiene : "+fitnessNoSolucion+" choques(fitness)");
        System.out.print("El tablero con la solución es: ");
        return tableroSolucion;
    }
    
    public int solucionNoEncontrada(int iteracionesMax){
        System.out.println("No se encontro la solución en las "+iteracionesMax+" iteraciones dadas");
        System.out.println("El mejor tablero que se encontró tiene : "+fitnessNoSolucion+" choques(fitness)");
        System.out.print("El mejor tablero encontrado es: ");
        return mejorFitness;
    }

}
