package n.reinas;

import java.util.ArrayList;
import java.util.Random;

public class NReinas {

    public static void main(String[] args) {
        
        int tampob, reinas;
        tampob = 8;
        reinas = 8;
        
        int [][] poblacion = new int [tampob][reinas];
        ArrayList<Integer> aux = new ArrayList<>();
        
        for (int i = 0; i < tampob; i++) {
            for (int j = 1; j < reinas+1; j++) {
                poblacion[i][j-1] = j;
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
    }
    
}
