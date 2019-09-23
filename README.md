# N-Reinas

Colocar N reinas en un tablero de ajedrez de NxN atendiendo que ninguna reina ataque a otra siguiendo las reglas del ajedrez. Heurística posible: Mínimo de conflictos. El backtracking debe realizar verificación anticipada de restricciones.

# Descargar 

> git clone https://github.com/Mvillegas1995/N-Reinas.git

# Compilacion

> javac NReinas.java

# Ejecucion

> java NReinas TamañoPoblacion CantReinas Semilla IteracionesMax ProbCruza ProbMutacion

TamañoPoblacion: entero positivo  
Cant Reina: entero positivo  
Semilla: entero positivo  
IteracionesMax: entero positivo  
ProbCruza: valor flotante entre 0 y 1 (usar . entreme dio.., ej: 0.9)  
ProbMutacion: valor flotante entre 0 y 1 (usar . entremedio.., ej: 0.2)  


# Ejemplo

> java NReinas 10 10 1 20000 0.9 0.2


 
