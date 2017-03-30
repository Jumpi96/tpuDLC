package utn.frc.dlc.sac.clases;

public class Palabra{
    
    private String palabra;
    private long nPosteo;
    private int nr;
    private int maxTF;

    public Palabra(String palabra, Long nPosteo) {
        this.palabra = palabra;
        this.nPosteo = nPosteo;
    }

    public Long getnPosteo() {
        return nPosteo;
    }

    public void setnPosteo(Long nPosteo) {
        this.nPosteo = nPosteo;
    }

    public Palabra(String palabra) {
        this.palabra = palabra;
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
