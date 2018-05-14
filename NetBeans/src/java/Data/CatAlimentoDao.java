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



public class CatAlimentoDao {
   
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
    
     public static  CatAlimento getCatAlimentoByIdCategoria (int idCatAlimento) throws SQLException{
        
        CatAlimento ca=new CatAlimento();
        
        
        try{
            Connection con=CatAlimentoDao.getConnection();
            String q;
            q="Select * from CatAlimento where idCatAlimento=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, idCatAlimento);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                ca.setIdCategoria(rs.getInt(1));
                ca.setDescripcionCategoria(rs.getString(2));
            }
            con.close();
                
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return ca;
    }
    
    public static List<CatAlimento> getAllCategorias() throws SQLException{
        
        List<CatAlimento> lista =new ArrayList<CatAlimento>();
        
        try{
            Connection con=CatAlimentoDao.getConnection();
            String q;
            q="Select * from CatAlimento";

            PreparedStatement ps =con.prepareStatement(q);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){                
                    CatAlimento ca=new CatAlimento(); 
                    ca.setIdCategoria(rs.getInt(1));
                    ca.setDescripcionCategoria(rs.getString(2));
                    lista.add(ca);
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
