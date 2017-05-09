/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author juampilorenzo
 */
public class Documento implements Comparable<Documento> {
    private File archivo;
    private boolean procesado=false;
    private int idDocumento;
    private int contador;

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

    public int getContador() {
        return contador;
    }
    
    public String getTitulo() throws FileNotFoundException, IOException{
        
        FileInputStream fis=new FileInputStream(archivo);
        Scanner scanner=new Scanner(fis);
        String linea=scanner.nextLine();
        return linea;
       
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
        this.contador=0;
    }

    public void contar(){
        contador++;
    }

    @Override
    public int compareTo(Documento o) {
        if (this.contador>o.getContador()) return 1;
                else if (this.contador<o.getContador()) return -1;
                        else return 0;
    }
    
    public static Comparator<Documento> DocumentoComparator
                          = new Comparator<Documento>() {

        public int compare(Documento doc1, Documento doc2) {

          return doc2.compareTo(doc1);

        }

    };
    
}
