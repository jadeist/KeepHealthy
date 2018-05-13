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
public class Alimento {
    private int idAlimento, idCategoria;
    private String NombreAlimento,porcion;
    private float Calorias;

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreAlimento() {
        return NombreAlimento;
    }

    public void setNombreAlimento(String NombreAlimento) {
        this.NombreAlimento = NombreAlimento;
    }

    public String getPorcion() {
        return porcion;
    }

    public void setPorcion(String porcion) {
        this.porcion = porcion;
    }

    public float getCalorias() {
        return Calorias;
    }

    public void setCalorias(float Calorias) {
        this.Calorias = Calorias;
    }
    
    
}
