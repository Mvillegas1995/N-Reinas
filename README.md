# N-Reinas

Colocar N reinas en un tablero de ajedrez de NxN atendiendo que ninguna reina ataque a otra siguiendo las reglas del ajedrez. Heurística posible: Mínimo de conflictos. El backtracking debe realizar verificación anticipada de restricciones.

# Informacion

Proyecto realizado en netbeans ide 8.2 modificado para ser usado en Lubuntu, para poder ser compilado en netbeans debe anteponerse en los archivos NReinas.java y GeneticAlg.java el siguiente package: package n.reinas; 

# Descargar 

> git clone https://github.com/Mvillegas1995/N-Reinas.git

# Compilacion

> javac NReinas.java

# Ejecucion

**Para realizar la ejecucion del programa debe dirigirse a la carpeta:  /NReinas/src/n/reinas**  

> java NReinas TamañoPoblacion CantReinas Semilla IteracionesMax ProbCruza ProbMutacion

- TamañoPoblacion: entero positivo  
- CantReinas: entero positivo  
- Semilla: entero positivo  
- IteracionesMax: entero positivo  
- ProbCruza: valor flotante entre 0 y 1 **(usar . entremedio , ej: 0.9)**
- ProbMutacion: valor flotante entre 0 y 1 **(usar . entremedio , ej: 0.2)**  


# Ejemplo

> java NReinas 10 10 1 20000 0.9 0.2


**Si no se ingresan parametros, se procedera a ejecutar el programa con los siguientes valores por defecto**


- TamañoPoblacion: 15
- CantReinas: 10  
- Semilla: 1  
- IteracionesMax: 30000  
- ProbCruza: 0.9

# Integrantes

- Marcus Villegas
- Julio Cáceres

- ProbMutacion: 0.2  

 
