import java.util.*;
import java.io.*;


public class Laberinto {
    private Casilla[][] casillas;
    private int filas;
    private int columnas;
    private Random random;

    public Laberinto(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        casillas = new Casilla[filas][columnas];
        random = new Random();
    
        do {
            crearLaberinto();
            encontrarCamino();
        } while (!existeCamino());
    
        marcarInicioYFin();
        imprimirLaberinto();
    }

    private void asignarInicioYFinAleatorios() {
        int filaInicio = random.nextInt(filas);
        int columnaInicio = random.nextInt(columnas);
        casillas[filaInicio][columnaInicio].setInicio(true);
    
        int filaFin = random.nextInt(filas);
        int columnaFin = random.nextInt(columnas);
        casillas[filaFin][columnaFin].setFin(true);
    }

    private void marcarInicioYFin() {
        int filaInicio = random.nextInt(filas);
        int columnaInicio = random.nextInt(columnas);
        casillas[filaInicio][columnaInicio].setInicio(true);
    
        int filaFin;
        int columnaFin;
        do {
            filaFin = random.nextInt(filas);
            columnaFin = random.nextInt(columnas);
        } while (filaFin == filaInicio && columnaFin == columnaInicio);
    
        casillas[filaFin][columnaFin].setFin(true);
    }
    
    private boolean existeCamino() {
        Casilla inicio = casillas[0][0];
        Casilla fin = casillas[filas - 1][columnas - 1];
    
        return fin.isVisitada(); // Verificar si el final se alcanzó durante la búsqueda del camino
    }


    private void crearLaberinto() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                casillas[i][j] = new Casilla(i, j);
            }
        }

        generarLaberinto();
    }

    private void generarLaberinto() {
        generarCaminoLaberinto(0, 0);
    }

    private void generarCaminoLaberinto(int fila, int columna) {
        Casilla casillaActual = casillas[fila][columna];
        casillaActual.setVisitada(true);

        int[] direcciones = { 0, 1, 2, 3 };
        shuffleArray(direcciones);

        for (int direccion : direcciones) {
            int nuevaFila = fila;
            int nuevaColumna = columna;

            if (direccion == 0 && fila > 0) { // Arriba
                nuevaFila--;
            } else if (direccion == 1 && fila < filas - 1) { // Abajo
                nuevaFila++;
            } else if (direccion == 2 && columna > 0) { // Izquierda
                nuevaColumna--;
            } else if (direccion == 3 && columna < columnas - 1) { // Derecha
                nuevaColumna++;
            }

            if (!casillas[nuevaFila][nuevaColumna].isVisitada()) {
                Casilla nuevaCasilla = casillas[nuevaFila][nuevaColumna];
                removerPared(casillaActual, nuevaCasilla);
                generarCaminoLaberinto(nuevaFila, nuevaColumna);
            }
        }
    }

    private void removerPared(Casilla casilla1, Casilla casilla2) {
        int filaDiferencia = casilla2.getFila() - casilla1.getFila();
        int columnaDiferencia = casilla2.getColumna() - casilla1.getColumna();
    
        if (filaDiferencia == -1) { // casilla2 está arriba de casilla1
            casilla1.setParedArriba(false);
            casilla2.setParedAbajo(false);
        } else if (filaDiferencia == 1) { // casilla2 está abajo de casilla1
            casilla1.setParedAbajo(false);
            casilla2.setParedArriba(false);
        } else if (columnaDiferencia == -1) { // casilla2 está a la izquierda de casilla1
            casilla1.setParedIzquierda(false);
            casilla2.setParedDerecha(false);
        } else if (columnaDiferencia == 1) { // casilla2 está a la derecha de casilla1
            casilla1.setParedDerecha(false);
            casilla2.setParedIzquierda(false);
        }
    }

    public void encontrarCamino() {
        Casilla inicio = casillas[0][0];
        Casilla fin = casillas[filas - 1][columnas - 1];
        boolean[][] visitado = new boolean[filas][columnas];
        encontrarCaminoRecursivo(inicio, fin, visitado);
    }
    
    private void encontrarCaminoRecursivo(Casilla actual, Casilla fin, boolean[][] visitado) {
        actual.setVisitada(true);
        visitado[actual.getFila()][actual.getColumna()] = true;
    
        if (actual == fin) {
            marcarCamino(actual); // Se ha alcanzado el fin, se marca el camino
            return;
        }
    
        int[] direcciones = { 0, 1, 2, 3 };
        shuffleArray(direcciones);
    
        for (int direccion : direcciones) {
            int nuevaFila = actual.getFila();
            int nuevaColumna = actual.getColumna();
    
            if (direccion == 0 && nuevaFila > 0 && !actual.hasParedArriba()) { // Arriba
                nuevaFila--;
            } else if (direccion == 1 && nuevaFila < filas - 1 && !actual.hasParedAbajo()) { // Abajo
                nuevaFila++;
            } else if (direccion == 2 && nuevaColumna > 0 && !actual.hasParedIzquierda()) { // Izquierda
                nuevaColumna--;
            } else if (direccion == 3 && nuevaColumna < columnas - 1 && !actual.hasParedDerecha()) { // Derecha
                nuevaColumna++;
            }
    
            Casilla siguienteCasilla = casillas[nuevaFila][nuevaColumna];
    
            if (!siguienteCasilla.isVisitada() && !visitado[siguienteCasilla.getFila()][siguienteCasilla.getColumna()]) {
                siguienteCasilla.setPadre(actual);
                encontrarCaminoRecursivo(siguienteCasilla, fin, visitado);
            }
        }
    }


    private void marcarCamino(Casilla casilla) {
        while (casilla.getPadre() != null) {
            casilla.setEnCamino(true);
            casilla = casilla.getPadre();
        }
        casilla.setEnCamino(true); // Marcar la casilla de inicio también
    }
    
    private void shuffleArray(int[] array) {
        int index, temp;
        for (int i = array.length - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }


    public void imprimirLaberinto() {
        System.out.println("Laberinto:");
        System.out.print("+");
        for (int j = 0; j < columnas; j++) {
            System.out.print("---+");
        }
        System.out.println();
    
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Casilla casilla = casillas[i][j];
    
                if (casilla.hasParedArriba()) {
                    System.out.print("|");
                } else {
                    System.out.print(" ");
                }
    
                if (casilla.isInicio()) {
                    System.out.print(" I ");
                } else if (casilla.isFin()) {
                    System.out.print(" F ");
                } else if (casilla.isEnCamino()) {
                    System.out.print(" x ");
                } else {
                    System.out.print("   ");
                }
    
                if (j == columnas - 1) {
                    if (casilla.hasParedDerecha()) {
                        System.out.println("|");
                    } else {
                        System.out.println(" ");
                    }
                }
            }
    
            System.out.print("+");
            for (int j = 0; j < columnas; j++) {
                if (casillas[i][j].hasParedAbajo()) {
                    System.out.print("---+");
                } else {
                    System.out.print("   +");
                }
            }
            System.out.println();
        }
    }
    

private boolean caminoContiene(Casilla casilla) {
    while (casilla != null) {
        if (casilla.isEnCamino()) {
            return true;
        }
        casilla = casilla.getPadre();
    }
    return false;
}
}    
