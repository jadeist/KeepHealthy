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
public class Perfil {
    private int id_perfil;
    private String descripcionPerfil;

    public int getId_perfil() {
        return id_perfil;
    }

    public void setId_perfil(int id_perfil) {
        this.id_perfil = id_perfil;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }


    
}
//CREATE TABLE Perfil (id_perfil int not null auto_increment, descripcionPerfil varchar(20) not null, PRIMARY KEY (id_perfil)) ;

