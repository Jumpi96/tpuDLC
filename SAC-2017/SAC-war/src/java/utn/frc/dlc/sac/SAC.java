/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utn.frc.dlc.sac;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import utn.frc.dlc.sac.clases.CargaArchivos;
//import utn.frc.dlc.sac.db.DBManager;

/**
 *
 * @author scarafia
 */
public abstract class SAC {
    
    private String origen="../../../DocumentosTP1/";

    public SAC() {
        CargaArchivos carga= new CargaArchivos(origen);
        
    }
    
    
}
