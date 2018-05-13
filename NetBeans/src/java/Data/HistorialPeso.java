/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

/**
 *
 * @author Diego
 */
public class HistorialPeso {
    private int idHistorialPeso, idUsuario,  idHistorialMenu;
    private float PesoHistorial;
     private java.sql.Date FechaHistorial;

    public int getIdHistorialPeso() {
        return idHistorialPeso;
    }

    public void setIdHistorialPeso(int idHistorialPeso) {
        this.idHistorialPeso = idHistorialPeso;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdHistorialMenu() {
        return idHistorialMenu;
    }

    public void setIdHistorialMenu(int idHistorialMenu) {
        this.idHistorialMenu = idHistorialMenu;
    }

    public java.sql.Date getFechaHistorial() {
        return FechaHistorial;
    }

    public void setFechaHistorial(java.sql.Date FechaHistorial) {
        this.FechaHistorial = FechaHistorial;
    }

    public float getPesoHistorial() {
        return PesoHistorial;
    }

    public void setPesoHistorial(float PesoHistorial) {
        this.PesoHistorial = PesoHistorial;
    }

     
}
