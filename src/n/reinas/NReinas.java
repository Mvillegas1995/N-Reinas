package n.reinas;

public class NReinas {

    public static void main(String[] args) {
        
        int tampob = 10;
        int reinas = 8;
        
        GeneticAlg x = new GeneticAlg(tampob, reinas);
        x.desordenarTablero(1);
        System.out.println("tablero desordenado");
        x.imprimirPoblacion();        
        x.calcularFitness();
        x.imprimirFitness();
        // si no se llego a la solucion inicialmente se procede a aplicar cruza/mutacion para intentar llegar al optimo
        if(x.resuelto())System.out.println("termine");
        else x.generarRuleta();
    
        System.out.println("El tablero escogido para la cruza es: "+x.escogerTableroCruza());
    }
    
}
