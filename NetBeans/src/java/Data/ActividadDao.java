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



public class ActividadDao {
   
    public static Connection getConnection(){
        String url,UserName,password;
         
        url="jdbc:mysql://localhost/keephealthy";
        UserName="root";
        password="n0m3l0";
        
        Connection con=null;
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            con= DriverManager.getConnection(url,UserName,password);
            
            System.out.println("Si se conecto ");
            
        }catch(Exception e){
            System.out.println("Error");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        
        return con;
        
    }
    
    /*Primer metodo Guardar Empleado*/
    
     
     public static  Actividad getActividadById (int id) throws SQLException{
        
        Actividad m=new Actividad();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=ActividadDao.getConnection();
        String q;
        q="Select * from Actividad where idActividad=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1, id);
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){
            m.setIdActividad(rs.getInt(1));
            m.setDescripcionActividad(rs.getString(2));
        }
        
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return m;
    }
     
     
    public static List<Actividad> getAllActividad() throws SQLException{
        
        List<Actividad> lista =new ArrayList<Actividad>();
        
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=ActividadDao.getConnection();
        String q;
        q="Select * from Actividad";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
                Actividad m=new Actividad();
                m.setIdActividad(rs.getInt(1));
                m.setDescripcionActividad(rs.getString(2));
                lista.add(m);
            
        }
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        
        return lista;
    }
    
      public static  Actividad getActividadByName(String descripcion) throws SQLException{
        
        Actividad u=new Actividad();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=ActividadDao.getConnection();
        String q;
        q="Select * from Actividad where descripcionActividad=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setString(1, descripcion);
        
        ResultSet rs=ps.executeQuery();
       
        if(rs.next()){
           
            u.setIdActividad(rs.getInt(1));
            u.setDescripcionActividad(rs.getString(2));
        }
        else{
            out.println("Actividad no encontrada");
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