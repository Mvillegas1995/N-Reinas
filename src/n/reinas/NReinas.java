package n.reinas;

public class NReinas {

    public static void main(String[] args) {
        
        int tampob = 10;
        int reinas = 8;
        
        GeneticAlg x = new GeneticAlg(tampob, reinas);
        
        x.desordenarTablero();
        x.imprimirPoblacion();
        
        x.calcularFitness();
        x.imprimirFitness();

    }
    
}
