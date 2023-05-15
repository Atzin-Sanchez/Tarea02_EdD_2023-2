public class Casilla {
    
    
        private int fila;
        private int columna;
        private boolean paredArriba;
        private boolean paredAbajo;
        private boolean paredIzquierda;
        private boolean paredDerecha;
        private boolean visitada;
        private boolean enCamino;
        private boolean inicio; 
        private boolean fin; 
        private Casilla padre;
    
        public Casilla(int fila, int columna) {
            this.fila = fila;
            this.columna = columna;
            paredArriba = true;
            paredAbajo = true;
            paredIzquierda = true;
            paredDerecha = true;
            visitada = false;
            enCamino = false;
            inicio = false;
            fin = false;
            padre = null;
        }

    // Getters y setters

    public void setInicio(boolean inicio) {
        this.inicio = inicio;
    }

    public boolean isInicio() {
        return inicio;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    public boolean isFin() {
        return fin;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean isFinal() {
        return fin;
    }

    public void setFinal(boolean fin) {
        this.fin = fin;
    }

    public boolean isVisitada() {
        return visitada;
    }

    public void setVisitada(boolean visitada) {
        this.visitada = visitada;
    }

    public boolean hasParedArriba() {
        return paredArriba;
    }

    public void setParedArriba(boolean paredArriba) {
        this.paredArriba = paredArriba;
    }

    public boolean hasParedAbajo() {
        return paredAbajo;
    }

    public void setParedAbajo(boolean paredAbajo) {
        this.paredAbajo = paredAbajo;
    }

    public boolean hasParedIzquierda() {
        return paredIzquierda;
    }

    public void setParedIzquierda(boolean paredIzquierda) {
        this.paredIzquierda = paredIzquierda;
    }

    public boolean hasParedDerecha() {
        return paredDerecha;
    }

    public void setParedDerecha(boolean paredDerecha) {
        this.paredDerecha = paredDerecha;
    }

    public boolean isEnCamino() {
        return padre != null;
    }

    public void setEnCamino(boolean enCamino) {
        this.enCamino = enCamino;
    }

    public Casilla getPadre() {
        return padre;
    }

    public void setPadre(Casilla padre) {
        this.padre = padre;
    }
    

    public boolean isFinal(int filas, int columnas) {
        return fila == filas - 1 && columna == columnas - 1;
    }

    @Override
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }
}
