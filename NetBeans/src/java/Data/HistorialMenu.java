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
public class HistorialMenu {
    private int idHistorialMenu,idMenu,idUsuario, Calificacion;
    private java.sql.Date FechaCreacion;
    private String ComentarioHistorial;

    public int getIdHistorialMenu() {
        return idHistorialMenu;
    }

    public void setIdHistorialMenu(int idHistorialMenu) {
        this.idHistorialMenu = idHistorialMenu;
    }

   

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int Calificacion) {
        this.Calificacion = Calificacion;
    }

    public java.sql.Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(java.sql.Date FechaCreacion) {
        this.FechaCreacion = FechaCreacion;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getComentarioHistorial() {
        return ComentarioHistorial;
    }

    public void setComentarioHistorial(String ComentarioHistorial) {
        this.ComentarioHistorial = ComentarioHistorial;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
