/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;
import java.util.*;

/**
 *
 * @author Diego
 */
public class Usuario {
     private String nombreUsuario,Contrasena,Nickname,Ocupacion;
     private java.sql.Date FechaRegistro,FechaUltVez,FechaNacimiento;
    private int idUsuario,idperfil,idsexo,idActividad;
    private float pesoUsuario,Estatura,CaloriasDiarias;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public String getNickname() {
        return Nickname;
    }

    public void setNickname(String Nickname) {
        this.Nickname = Nickname;
    }

    

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String Ocupacion) {
        this.Ocupacion = Ocupacion;
    }

  

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(int idperfil) {
        this.idperfil = idperfil;
    }

    public int getIdsexo() {
        return idsexo;
    }

    public void setIdsexo(int idsexo) {
        this.idsexo = idsexo;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public float getPesoUsuario() {
        return pesoUsuario;
    }

    public void setPesoUsuario(float pesoUsuario) {
        this.pesoUsuario = pesoUsuario;
    }

    public float getEstatura() {
        return Estatura;
    }

    public void setEstatura(float Estatura) {
        this.Estatura = Estatura;
    }

    public float getCaloriasDiarias() {
        return CaloriasDiarias;
    }

    public void setCaloriasDiarias(float CaloriasDiarias) {
        this.CaloriasDiarias = CaloriasDiarias;
    }

    public java.sql.Date getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(java.sql.Date FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public java.sql.Date getFechaUltVez() {
        return FechaUltVez;
    }

    public void setFechaUltVez(java.sql.Date FechaUltVez) {
        this.FechaUltVez = FechaUltVez;
    }

    public java.sql.Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(java.sql.Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

  


    
}