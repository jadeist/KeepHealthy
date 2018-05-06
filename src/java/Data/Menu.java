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
public class Menu {
    private int id_Menu,idGrupoEtapamenu,noMenu;
    private float caloriasMenu;

    public int getId_Menu() {
        return id_Menu;
    }

    public void setId_Menu(int id_Menu) {
        this.id_Menu = id_Menu;
    }

    public int getIdGrupoEtapamenu() {
        return idGrupoEtapamenu;
    }

    public void setIdGrupoEtapamenu(int idGrupoEtapamenu) {
        this.idGrupoEtapamenu = idGrupoEtapamenu;
    }

    public int getNoMenu() {
        return noMenu;
    }

    public void setNoMenu(int noMenu) {
        this.noMenu = noMenu;
    }

    public float getCaloriasMenu() {
        return caloriasMenu;
    }

    public void setCaloriasMenu(float caloriasMenu) {
        this.caloriasMenu = caloriasMenu;
    }

   
    
}
//CREATE TABLE Menu (id_Menu int not null, 
//idGrupoEtapamenu int not null, 
//noMenu int 
//caloriasMenu int not null, 
//FOREIGN KEY (idgrupoetapamenu) REFERENCES GrupoEtapaMenu(id_GrupoEtapamenu), FOREIGN KEY (idalimento) REFERENCES Alimento(id_alimento) ON DELETE CASCADE);