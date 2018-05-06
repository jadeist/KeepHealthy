package Data;

import Data.*;
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



public class SexoDao {
   
    public static Connection getConnection(){
        String url,UserName,password;
         
        url="jdbc:mysql://localhost/keephealthydb";
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
    
     
     public static  Sexo getSexoById (int id) throws SQLException{
        
        Sexo m=new Sexo();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=SexoDao.getConnection();
        String q;
        q="Select * from Sexo where idSexo=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1, id);
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){
            m.setIdSexo(rs.getInt(1));
            m.setDescripcionSexo(rs.getString(2));
        }
        
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return m;
    }
     
     
    public static List<Sexo> getAllSexo() throws SQLException{
        
        List<Sexo> lista =new ArrayList<Sexo>();
        
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=SexoDao.getConnection();
        String q;
        q="Select * from Sexo";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
                Sexo m=new Sexo();
                m.setIdSexo(rs.getInt(1));
                m.setDescripcionSexo(rs.getString(2));
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
    
      public static  Sexo getSexoByName(String descripcion) throws SQLException{
        
        Sexo u=new Sexo();                
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
            Connection con=SexoDao.getConnection();
            String q;
            q="Select idSexo,descripcionSexo from Sexo where descripcionSexo=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setString(1, descripcion);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){          
                u.setIdSexo(rs.getInt(1));
                u.setDescripcionSexo(rs.getString(2));
            }
            else{               
                 System.out.println("Sexo no encontrado");
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