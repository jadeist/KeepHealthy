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



public class AlimentoDao {
   
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
    
     public static  Alimento getAlientoById (int id) throws SQLException{
        
        Alimento a=new Alimento();
        
        
        try{
            Connection con=AlimentoDao.getConnection();
            String q;
            q="Select * from Alimento where idAlimento=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, id);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                a.setIdAlimento(rs.getInt(1));
                a.setIdCategoria(rs.getInt(2));
                a.setNombreAlimento(rs.getString(3));
                a.setPorcion(rs.getString(4));
                a.setCalorias(rs.getFloat(5));
            }
            con.close();
                
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return a;
    }
     
     public static  Alimento getAlientoByNombre (String nombre) throws SQLException{
        
        Alimento a=new Alimento();
        
        
        try{
            Connection con=AlimentoDao.getConnection();
            String q;
            q="Select * from Alimento where NombreAlimento=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setString(1, nombre);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                a.setIdAlimento(rs.getInt(1));
                a.setIdCategoria(rs.getInt(2));
                a.setNombreAlimento(rs.getString(3));
                a.setPorcion(rs.getString(4));
                a.setCalorias(rs.getFloat(5));
            }
            con.close();
                
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return a;
    }
      public static int Eliminar(int id) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=AlimentoDao.getConnection();
        String q;
        q="delete from Alimento where IdAlimento=? ";
        
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
      
    public static List<Alimento> getAllAlimentosByCategoria(int idCategoria) throws SQLException{
        
        List<Alimento> lista =new ArrayList<Alimento>();
        
        try{
            Connection con=AlimentoDao.getConnection();
            String q;
            q="Select * from Alimento where idCategoria =? ";

            PreparedStatement ps =con.prepareStatement(q);
            ps.setInt(1, idCategoria);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){                
                    Alimento a=new Alimento(); 
                    a.setIdAlimento(rs.getInt(1));
                    a.setIdCategoria(rs.getInt(2));
                    a.setNombreAlimento(rs.getString(3));
                    a.setPorcion(rs.getString(4));
                    a.setCalorias(rs.getFloat(5));
                    lista.add(a);
            }

            con.close();


        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        
        return lista;
    }
    public static List<Alimento> getAllAlimentos() throws SQLException{
        
        List<Alimento> lista =new ArrayList<Alimento>();
        
        try{
            Connection con=AlimentoDao.getConnection();
            String q;
            q="Select * from Alimento";

            PreparedStatement ps =con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){                
                    Alimento a=new Alimento(); 
                    a.setIdAlimento(rs.getInt(1));
                    a.setIdCategoria(rs.getInt(2));
                    a.setNombreAlimento(rs.getString(3));
                    a.setPorcion(rs.getString(4));
                    a.setCalorias(rs.getFloat(5));
                    lista.add(a);
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
