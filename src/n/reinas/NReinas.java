package n.reinas;

import java.util.ArrayList;
import java.util.Random;

public class NReinas {

    public static void main(String[] args) {
        
        int tampob, reinas;
        tampob = 10;
        reinas = 8;
        
        // ASIGNAR POBLACION
        int [][] poblacion = new int [tampob][reinas];    
        for (int i = 0; i < tampob; i++) {
            for (int j = 0; j < reinas; j++) {
                poblacion[i][j] = j;
            }            
        }
        
        // INICIALIZAR FITNESS
        int [] fitness = new int [tampob];
        for (int i = 0; i < tampob; i++) {
            fitness[i] = 0;
        }
        
        Random r = new Random();
        for (int i = 0; i < tampob; i++) {
            for (int j=0; j<reinas; j++) {
                int posAleatoria = r.nextInt(reinas);
                int temp = poblacion[i][j];
                poblacion[i][j] = poblacion[i][posAleatoria];
                poblacion[i][posAleatoria] = temp;
            }
        }
        
        for (int i = 0; i < tampob; i++) {
            for (int j = 0; j < reinas; j++) {
                System.out.print(poblacion[i][j]+" ");
            }
            System.out.println("");
        }
        
        //CALCULAR FITNESS
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
        //IMPRIME EL FITNESS
        for (int i = 0; i < tampob; i++) {
            System.out.println(fitness[i]);
        }
    }
    
}
