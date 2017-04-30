package utn.frc.dlc.sac.clases;

public class Palabra{
    
    private String palabra;
    private int idPalabra;
    private int nr;
    private int maxTF;

    public Palabra(String palabra,int idPalabra) {
        this.palabra = palabra;
        this.idPalabra=idPalabra;
    }
    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public Palabra() {
    }
    
//    para calcular maxTF me parece que se va a tener que hacer una consulta a
//    la lista posteo preguntando por cuantas veces aparece cada palabra en cada 
//    uno de los docs y del maximo guardarlo
    
    public int calculcarMaxTF(){
        int max = 0;
    
        return max;
    }

    
}
