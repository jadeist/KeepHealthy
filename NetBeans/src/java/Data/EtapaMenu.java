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
public class EtapaMenu {
    private int idEtapamenu;
    private float PocentajeCalorias;
    private String DescripcionEtapa, HoraEtapa;

    public int getIdEtapamenu() {
        return idEtapamenu;
    }

    public void setIdEtapamenu(int idEtapamenu) {
        this.idEtapamenu = idEtapamenu;
    }

   

    public String getDescripcionEtapa() {
        return DescripcionEtapa;
    }

    public void setDescripcionEtapa(String DescripcionEtapa) {
        this.DescripcionEtapa = DescripcionEtapa;
    }

    public String getHoraEtapa() {
        return HoraEtapa;
    }

    public void setHoraEtapa(String HoraEtapa) {
        this.HoraEtapa = HoraEtapa;
    }

    public float getPocentajeCalorias() {
        return PocentajeCalorias;
    }

    public void setPocentajeCalorias(float PocentajeCalorias) {
        this.PocentajeCalorias = PocentajeCalorias;
    }
    
}
