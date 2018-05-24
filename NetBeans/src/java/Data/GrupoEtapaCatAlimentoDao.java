package Data;


import Data.*;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class GrupoEtapaCatAlimentoDao {
    public static Connection getConnection(){
        String url,UserName,password;
         
        url="jdbc:mysql://localhost/KeepHealthyDB";
        UserName="root";
        password="n0m3l0";
        
        Connection con=null;
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,UserName,password);
            
            System.out.println("Si se conecto");
            
        }catch(Exception e){
            System.out.println("Error");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
        return con;
        
    }
      
    
    public static int Guardar(GrupoEtapaCatAlimento geca) throws SQLException{
        
        int estatus=0;
        try{
            Connection con=GrupoEtapaCatAlimentoDao.getConnection();
            String q;
            q="insert into GrupoEtapaCatAlimento(idCategoria, idEtapaMenu) values (?,?)";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1,geca.getIdCategoria());
            ps.setInt(2,geca.getIdEtapamenu());            

            estatus=ps.executeUpdate();
            con.close();    
            
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
    public static int Actualizar(GrupoEtapaCatAlimento geca) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=GrupoEtapaCatAlimentoDao.getConnection();
        String q;
        
        q="update GrupoEtapaCatAlimento set "
              + "idCategoria=?,"
              + "idEtapaMenu=? "
              + "where idGrupoEtapaCatAlimento=?";
      
        PreparedStatement ps =con.prepareStatement(q);

        ps.setInt(1,geca.getIdCategoria());
        ps.setInt(2,geca.getIdEtapamenu());
        ps.setInt(3,geca.getIdGrupoEtapaCatAlimento());
                
        estatus=ps.executeUpdate();
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
     public static int Eliminar(int id) throws SQLException{
        
        int estatus=0;
              
        try{
            Connection con=GrupoEtapaCatAlimentoDao.getConnection();
            String q;
            q="delete from GrupoEtapaCatAlimento where idGrupoEtapaCatAlimento=? ";

            PreparedStatement ps =con.prepareStatement(q);
            ps.setInt(1,id);
            estatus=ps.executeUpdate();
            
            con.close();                
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
     
     public static  GrupoEtapaCatAlimento getGrupoEtapaCatAlimentoById (int idGrupoEtapaCatAlimento) throws SQLException{
        
        GrupoEtapaCatAlimento geca = new GrupoEtapaCatAlimento();
        
        try{
            Connection con=GrupoEtapaDao.getConnection();
            String q;
            q="Select idGrupoEtapaCatAlimento, idCategoria, IdEtapaMenu "
                  +"from GrupoEtapaCatAlimento where idGrupoEtapaCataAlimento=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, idGrupoEtapaCatAlimento);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){            
                geca.setIdGrupoEtapaCatAlimento(rs.getInt(1));
                geca.setIdCategoria(rs.getInt(2));
                geca.setIdEtapamenu(rs.getInt(3));            
                
            }                
            con.close();

        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return geca;
    }
     
 public static List<GrupoEtapaCatAlimento> getAllGrupoEtapaCatAlimentobyidEtapaMenu(int idEtapaMenu ) throws SQLException{
        
        List<GrupoEtapaCatAlimento> lista =new ArrayList<GrupoEtapaCatAlimento>();
        
        try{
            Connection con=GrupoEtapaCatAlimentoDao.getConnection();
            String q;
            q="Select idGrupoEtapaCatAlimento, idCategoria, idEtapaMenu "
                    +"from GrupoEtapaCatAlimento where idEtapaMenu=? ";
        
            PreparedStatement ps =con.prepareStatement(q);
            ps.setInt(1, idEtapaMenu);            
        
            ResultSet rs=ps.executeQuery();
        
            while(rs.next()){
                GrupoEtapaCatAlimento geca=new GrupoEtapaCatAlimento();
                geca.setIdGrupoEtapaCatAlimento(rs.getInt(1));
                geca.setIdCategoria(rs.getInt(2));
                geca.setIdEtapamenu(rs.getInt(3));
                lista.add(geca);            
            }
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        
        return lista;
    }
 
 
}
