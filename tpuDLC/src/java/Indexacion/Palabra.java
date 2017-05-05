package Indexacion;

import java.util.Comparator;

public class Palabra implements Comparable<Palabra>{
    
    private String palabra;
    private int idPalabra;
    private int nr;
    private int maxTF;

    public Palabra(String palabra,int idPalabra) {
        this.palabra = palabra;
        this.idPalabra=idPalabra;
    }
    
    public Palabra(String palabra, int idPalabra, int nr, int maxTF) {
        this.palabra = palabra;
        this.idPalabra = idPalabra;
        this.nr = nr;
        this.maxTF = maxTF;
    }

    public int getIdPalabra() {
        return idPalabra;
    }

    public void setIdPalabra(int idPalabra) {
        this.idPalabra = idPalabra;
    }


    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public Palabra() {
    }

    public int getNr() {
        return nr;
    }

    @Override
    public int compareTo(Palabra o) {
        if (this.nr>o.getNr()) return 1;
                else if (this.nr<o.getNr()) return -1;
                        else return 0;
    }
    
    public static Comparator<Palabra> PalabraComparator
                          = new Comparator<Palabra>() {

        public int compare(Palabra palabra1, Palabra palabra2) {

          return palabra1.compareTo(palabra2);

        }

    };
}


