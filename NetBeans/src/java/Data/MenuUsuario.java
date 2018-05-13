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
public class MenuUsuario {
    private int idMenuusuario,idMenu,idUsuario;
     private java.sql.Date FechaCreacion;

    public int getIdMenuusuario() {
        return idMenuusuario;
    }

    public void setIdMenuusuario(int idMenuusuario) {
        this.idMenuusuario = idMenuusuario;
    }

  
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

  
     
}
