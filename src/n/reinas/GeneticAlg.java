
package n.reinas;

import java.util.Random;

public class GeneticAlg {
    
    int tampob;
    int reinas;
    int [][] poblacion;
    int [] fitness;
    
    public GeneticAlg(int tampob, int reinas){
        
        this.tampob = tampob;
        this.reinas = reinas;
        
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
      
    }
    
    public void desordenarTablero(){    
        Random r = new Random();
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
        }
    }
    
    public void imprimirFitness(){
        for (int i = 0; i < tampob; i++) {
            System.out.println(fitness[i]);
        }
    }
}
