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
public class MenuUsuarioDao {
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
    
    public static int Guardar(MenuUsuario m) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=MenuUsuarioDao.getConnection();
        String q;
        q="insert into MenuUsuario(idMenu,idUsuario,fechaCreacion) values (?,?,?)";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1,m.getIdMenu());
        ps.setInt(2,m.getIdUsuario());
        ps.setDate(3,m.getFechaCreacion());
        
        estatus=ps.executeUpdate();
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
    public static int Actualizar(MenuUsuario m) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=MenuUsuarioDao.getConnection();
        String q;
      q="update MenuUsuario set idMenu=?,"
              + "idUsuario=?,"
              + "fechaCreacion=?,"
              + "where idMenuUsuario=?";
        PreparedStatement ps =con.prepareStatement(q);

        ps.setInt(1,m.getIdMenu());
        ps.setInt(2,m.getIdUsuario());
        ps.setDate(3,m.getFechaCreacion());
        ps.setInt(4,m.getIdMenuusuario());
        
        estatus=ps.executeUpdate();
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
     public static int Eliminar(int idUsuario) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=MenuUsuarioDao.getConnection();
        String q;
        q="delete from MenuUsuario where idUsuario=? ";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1,idUsuario);
        
        estatus=ps.executeUpdate();
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
     
     public static  MenuUsuario getMenuUsuarioByIdUsuario (int idUsuario) throws SQLException{
        
        MenuUsuario m=new MenuUsuario();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=MenuUsuarioDao.getConnection();
        String q;
        q="Select * from MenuUsuario where idUsuario=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1, idUsuario);
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){            
            m.setIdMenuusuario(rs.getInt(1));
            m.setIdMenu(rs.getInt(2));
            m.setIdUsuario(rs.getInt(3));
            m.setFechaCreacion(rs.getDate(4));            
        } 
        else{
            m.setIdMenuusuario(0);
            m.setIdMenu(0);
            m.setIdUsuario(0);            
        }
            
        con.close();

        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return m;
    }
     
       
        
       
}
