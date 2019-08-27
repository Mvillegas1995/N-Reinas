package n.reinas;

import java.util.ArrayList;
import java.util.Random;

public class NReinas {

    public static void main(String[] args) {
        
        int tampob, reinas;
        tampob = 8;
        reinas = 8;
        
        int [] fitness = new int [tampob];
        for (int i = 0; i < tampob; i++) {
            fitness[i] = 0;
        }
        // ASIGNAR POBLACION
        int [][] poblacion = new int [tampob][reinas];
        ArrayList<Integer> aux = new ArrayList<>();
        
        for (int i = 0; i < tampob; i++) {
            for (int j = 0; j < reinas; j++) {
                poblacion[i][j] = j;
            }            
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
        
        for (int i = 0; i < tampob; i++) {
            // i itera entre poblaciones
            for (int k = 0; k <= reinas; k++) {
                // k itera el indice 
                for (int j = 0; j <= reinas -1; j++) {
                    //k itera el hacia la derecha del tablero
                    
                    
                }
                
            }
            
        }
    }
    
}
