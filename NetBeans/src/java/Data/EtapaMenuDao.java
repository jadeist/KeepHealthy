package Data;

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



public class EtapaMenuDao {
   
    public static Connection getConnection(){
        String url,UserName,password;
         
        url="jdbc:mysql://localhost/KeepHealthyDB";
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
    
       
     public static  EtapaMenu getEtapaMenuById (int idEtapaMenu) throws SQLException{
        
        EtapaMenu em=new EtapaMenu();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=EtapaMenuDao.getConnection();
        String q;
        q="Select * from EtapaMenu where idEtapaMenu=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1, idEtapaMenu);
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){
            em.setIdEtapamenu(rs.getInt(1));
            em.setDescripcionEtapa(rs.getString(2));
            em.setHoraEtapa(rs.getString(3));
            em.setPocentajeCalorias(rs.getFloat(4));
        }
        
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return em;
    }
     
    public static List<EtapaMenu> getAll( ) throws SQLException{
        
        List<EtapaMenu> lista =new ArrayList<EtapaMenu>();
        
        try{
            Connection con=MenuDao.getConnection();
            String q;
            q="Select * from EtapaMenu order by idEtapaMenu";
        
            PreparedStatement ps =con.prepareStatement(q);
                    
            ResultSet rs=ps.executeQuery();
        
            while(rs.next()){
                EtapaMenu em=new EtapaMenu();
                em.setIdEtapamenu(rs.getInt(1));                
                em.setDescripcionEtapa(rs.getString(2));
                em.setHoraEtapa(rs.getString(3));
                em.setPocentajeCalorias(rs.getFloat(4));
                lista.add(em);            
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