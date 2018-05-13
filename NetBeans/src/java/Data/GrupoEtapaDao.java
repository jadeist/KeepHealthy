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
public class GrupoEtapaDao {
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
    
    /*Primer metodo Guardar Empleado*/
    
    public static int Guardar(GrupoEtapa ge) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=GrupoEtapaDao.getConnection();
        String q;
        q="insert into GrupoEtapa(idEtapaMenu,idAlimento,noMenu) values (?,?,?)";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1,ge.getIdEtapaMenu());
        ps.setInt(2,ge.getIdAlimento());
        ps.setInt(3,ge.getNoMenu());
        
        estatus=ps.executeUpdate();
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
    public static int Actualizar(GrupoEtapa ge) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=GrupoEtapaDao.getConnection();
        String q;
        
      q="update GrupoEtapa set idEtapamenu=?,"
              + "idAlimento=?,"
              + "noMenu=?,"
              + "where idGrupoEtapa=?";
        PreparedStatement ps =con.prepareStatement(q);

        ps.setInt(1,ge.getIdEtapaMenu());
        ps.setInt(2,ge.getIdAlimento());
        ps.setInt(3,ge.getNoMenu());
        ps.setInt(4,ge.getIdGrupoEtapa());
                
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
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=GrupoEtapaDao.getConnection();
        String q;
        q="delete from GrupoEtapa where idGrupoEtapa=? ";
        
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
     
     public static  GrupoEtapa getGrupoEtapaById (int idGrupoEtapa) throws SQLException{
        
        GrupoEtapa ge=new GrupoEtapa();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=GrupoEtapaDao.getConnection();
        String q;
        q="Select * from GrupoEtapa where idGrupoEtapa=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1, idGrupoEtapa);
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){            
            ge.setIdGrupoEtapa(rs.getInt(1));
            ge.setIdEtapaMenu(rs.getInt(2));
            ge.setIdAlimento(rs.getInt(3));            
            ge.setNoMenu(rs.getInt(4));            
        }                
        con.close();

        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return ge;
    }
     
 public static List<GrupoEtapa> getAllGrupoEtapabyNoMenu(int noMenu, int idEtapaMenu ) throws SQLException{
        
        List<GrupoEtapa> lista =new ArrayList<GrupoEtapa>();
        
        try{
            Connection con=GrupoEtapaDao.getConnection();
            String q;
            q="Select * from GrupoEtapa where noMenu=? and idEtapaMenu =?";
        
            PreparedStatement ps =con.prepareStatement(q);
            ps.setInt(1, noMenu);
            ps.setInt(2, idEtapaMenu);
        
            ResultSet rs=ps.executeQuery();
        
            while(rs.next()){
                GrupoEtapa ge=new GrupoEtapa();
                ge.setIdGrupoEtapa(rs.getInt(1));
                ge.setIdEtapaMenu(rs.getInt(2));
                ge.setIdAlimento(rs.getInt(3));
                ge.setNoMenu(rs.getInt(4));
                lista.add(ge);            
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
