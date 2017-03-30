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
public class Documento {
    private String nombre;

    public Documento(String nombre) {
        this.nombre = nombre;
    }

    public Documento() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
