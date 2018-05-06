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
public class Usuario {
     private String Clave,Password,NombreUsuario,FechaNacimiento,Ocupacion,FechaRegistro,FechaUltVez;
    private int id_Usuario,idperfil,idsexo,IdActividad;
    private float Peso,Estatura,CaloriasDiarias;

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNombreUsuario() {
        return NombreUsuario;
    }

    public void setNombreUsuario(String NombreUsuario) {
        this.NombreUsuario = NombreUsuario;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getOcupacion() {
        return Ocupacion;
    }

    public void setOcupacion(String Ocupacion) {
        this.Ocupacion = Ocupacion;
    }

    public String getFechaRegistro() {
        return FechaRegistro;
    }

    public void setFechaRegistro(String FechaRegistro) {
        this.FechaRegistro = FechaRegistro;
    }

    public String getFechaUltVez() {
        return FechaUltVez;
    }

    public void setFechaUltVez(String FechaUltVez) {
        this.FechaUltVez = FechaUltVez;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
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
        return IdActividad;
    }

    public void setIdActividad(int IdActividad) {
        this.IdActividad = IdActividad;
    }

    public float getPeso() {
        return Peso;
    }

    public void setPeso(float Peso) {
        this.Peso = Peso;
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
    
    
}
//CREATE TABLE Usuario (id_Usuario int not null auto_increment,
//Clave varchar(15) not null, 
//Password varchar(15) not null,
//idperfil int not null,
//NombreUsuario varchar(50) not null, 
//idsexo int not null,
//FechaNacimiento varchar(10) not null,
//Peso float(6,2) not null, 
//Estatura float(6,2) not null, 
//Ocupacion varchar(20), 
//IdActividad int not null, 
//FechaRegistro varchar(8) not null,
//FechaUltVez varchar(8), 
//CaloriasDiarias float(6,2) not null, 
//PRIMARY KEY (id_Usuario), FOREIGN KEY (idPerfil) REFERENCES Perfil(id_Perfil), FOREIGN KEY (idsexo) REFERENCES Sexo(id_sexo), FOREIGN KEY (idactividad) REFERENCES ActividadFisica(id_actividad) ON DELETE CASCADE);
