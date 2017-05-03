package clases;

public class Palabra{
    
    private String palabra;
    private int idPalabra;
    private int nr;
    private int maxTF;

    public Palabra(String palabra,int idPalabra) {
        this.palabra = palabra;
        this.idPalabra=idPalabra;
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
    
}
