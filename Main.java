import java.util.Scanner;
import java.util.*;
public class Main{
        public static void main(String[] args) {
            if (args.length < 2) {
                System.out.println("Se requieren 2 argumentos: <filas> <columnas>");
                return;
            }
    
            int filas = Integer.parseInt(args[0]);
            int columnas = Integer.parseInt(args[1]);
    
            Laberinto laberinto = new Laberinto(filas, columnas);
            laberinto.encontrarCamino();
            laberinto.imprimirLaberinto();
        }
    }
   
        
 



