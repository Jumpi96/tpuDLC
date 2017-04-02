/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.sac.clases;

/**
 *
 * @author juampilorenzo
 */
public class AparicionPalabra {
    private Palabra palabra;
    private int contador;
    private Documento documento;

    public Palabra getPalabra() {
        return palabra;
    }

    public void setPalabra(Palabra palabra) {
        this.palabra = palabra;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public AparicionPalabra(Palabra palabra, Documento documento) {
        this.palabra = palabra;
        contador=1;
        this.documento = documento;
    }
    
    public void contar(){
        this.contador++;
    }
}
