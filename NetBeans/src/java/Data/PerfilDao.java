package Data;

import static java.lang.System.out;
import java.util.*;
import java.sql.*;
import javax.servlet.ServletException;
/*
Esta es la clase que se envcargara
de la conexion con la BD y es
la que se encarga de realizar las acciones por 
parte del usuario

Guardar un empleado
Consultar los datos de un empleado
Actualizar los datos del empleado
Dar de baja a los empleados
Buscar un solo empleado
*/



public class PerfilDao {
   
    public static Connection getConnection(){
        String url,UserName,password;
         
        url="jdbc:mysql://localhost/keephealthy";
        UserName="root";
        password="n0m3l0";
        
        Connection con=null;
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,UserName,password);
            
            System.out.println("Si se conecto perro");
            
        }catch(Exception e){
            System.out.println("Chingo a su puta madre");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
        return con;
        
    }
    
    
     public static  Perfil getPerfilById (int id) throws SQLException{
        
        Perfil p=new Perfil();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=PerfilDao.getConnection();
        String q;
        q="Select * from Perfil where id_perfil=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1, id);
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){
            p.setIdPerfil(rs.getInt(1));
            p.setDescripcionPerfil(rs.getString(2));
            
        }
        
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Chingo a su puta madre");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return p;
    }
     
     
    public static List<Perfil> getAllPerfiles() throws SQLException{
        
        List<Perfil> lista =new ArrayList<Perfil>();
        
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=PerfilDao.getConnection();
        String q;
        q="Select * from Perfil ";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
            Perfil p=new Perfil();
            p.setIdPerfil(rs.getInt(1));
            p.setDescripcionPerfil(rs.getString(2));
            lista.add(p);
            
        }
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Chingo a su puta madre");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        
        return lista;
    }
 
    public static  Perfil getPerfilByName(String descripcion) throws SQLException{
        
        Perfil u=new Perfil();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=PerfilDao.getConnection();
        String q;
        q="Select * from Perfil where descripcionPerfil=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setString(1, descripcion);
        
        ResultSet rs=ps.executeQuery();
       
        if(rs.next()){
           
            u.setIdPerfil(rs.getInt(1));
            u.setDescripcionPerfil(rs.getString(2));
        }
        else{
            out.println("Perfil no encontrado");
        }
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return u;
    }
}
