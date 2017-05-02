/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.sac.clases;

import java.io.File;

/**
 *
 * @author juampilorenzo
 */
public class Documento {
    private File archivo;
    private boolean procesado=false;
    private int idDocumento;

    public int getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }

    public boolean isProcesado() {
        return procesado;
    }

    public void setProcesado(boolean procesado) {
        this.procesado = procesado;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public Documento(File archivo, int idDocumento) {
        this.archivo = archivo;
        this.idDocumento=idDocumento;
    }

    
    
}
