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
public class AlimentoRestriccionDao {
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
        
    
    public static int Guardar(AlimentoRestriccion ar) throws SQLException{
        
        int estatus=0;
        
        
        try{
            Connection con=AlimentoRestriccionDao.getConnection();
            String q;
            q="insert into AlimentoRestriccion(idAlimento,idUsuario) values (?,?)";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1,ar.getIdAlimento());
            ps.setInt(2,ar.getIdUsuario());            

            estatus=ps.executeUpdate();
            con.close();


        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
    public static int Actualizar(AlimentoRestriccion ar) throws SQLException{
        
        int estatus=0;
        
        try{
            Connection con=AlimentoRestriccionDao.getConnection();
            String q;
            q="update AlimentoRestriccion set "
                  + "idAlimento=?,"  
                  + "idUsuario=? "
                  + "where idAlimentoRestriccion=?";
            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1,ar.getIdAlimento());
            ps.setInt(2,ar.getIdUsuario());
            ps.setInt(3,ar.getIdAlimentoRestriccion());
            
            estatus=ps.executeUpdate();
            con.close();


        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
     public static int Eliminar(int idAlimentoRestriccion) throws SQLException{
        
        int estatus=0;
        
        try{
            Connection con=MenuUsuarioDao.getConnection();
            String q;
            q="delete from AlimentoRestriccion where idAlimentoRestriccion=? ";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1,idAlimentoRestriccion);

            estatus=ps.executeUpdate();
            con.close();

        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
     
     public static List<AlimentoRestriccion> getAllAlimentoRestriccionByIdUsuario(int idUsuario) throws SQLException{
        
        List<AlimentoRestriccion> lista =new ArrayList<AlimentoRestriccion>();
                
        try{
            Connection con=AlimentoRestriccionDao.getConnection();
            String q;
            q="Select idAlimentoRestriccion, idAlimento, idUsuario from AlimentoRestriccion where idUsuario = ? ";

            PreparedStatement ps =con.prepareStatement(q);
             ps.setInt(1, idUsuario);
             
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                    AlimentoRestriccion ar=new AlimentoRestriccion(); 
                    ar.setIdAlimentoRestriccion(rs.getInt(1));
                    ar.setIdAlimento(rs.getInt(2));
                    ar.setIdUsuario(rs.getInt(3));                    
                   lista.add(ar);
            }

            con.close();

        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        
        return lista;
    }
     
   public static  AlimentoRestriccion getAlimentoRestriccionByIdAlimentoUsuario (int idAlimento, int idUsuario) throws SQLException{
        
        AlimentoRestriccion ar=new AlimentoRestriccion();
                
        try{
            Connection con=AlimentoRestriccionDao.getConnection();
            String q;
            q="Select idAlimentoRestriccion, idAlimento, idUsuario from AlimentoRestriccion where idAlimento=? and idUsuario=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, idAlimento);
            ps.setInt(2, idUsuario);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                ar.setIdAlimentoRestriccion(rs.getInt(1));
                ar.setIdAlimento(rs.getInt(2));
                ar.setIdUsuario(rs.getInt(3));
            }
            else{
                ar.setIdAlimentoRestriccion(0);
                ar.setIdAlimento(0);
                ar.setIdUsuario(0);            
            }

            con.close();
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return ar;
    }      
       
}
