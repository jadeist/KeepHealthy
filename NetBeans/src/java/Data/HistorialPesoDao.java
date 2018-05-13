package Data;

import Data.*;
import static java.lang.System.out;
import java.util.*;
import java.sql.*;
import javax.servlet.ServletException;

public class HistorialPesoDao {
   
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
    
    
    public static int Guardar(HistorialPeso hp) throws SQLException{
        
        int estatus=0;

        try{
            Connection con=HistorialPesoDao.getConnection();
            String q;
            q="insert into HistorialPeso(FechaHistorial,idUsuario,PesoHistorial,idHistorialMenu) values (?,?,?,?)";

            PreparedStatement ps =con.prepareStatement(q);
            ps.setDate(1, hp.getFechaHistorial());
            ps.setInt(2, hp.getIdUsuario());
            ps.setFloat(3, hp.getPesoHistorial());
            ps.setInt(4, hp.getIdHistorialMenu());
            
            estatus=ps.executeUpdate();
            con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
    public static int Actualizar(HistorialPeso hp) throws SQLException{
        
        int estatus=0;
        
        try{
            Connection con=HistorialPesoDao.getConnection();
            String q;
            q="update HistorialPeso set "
                  + "fechaHistorial=?,"
                  + "idUsuario=?,"
                  + "PesoHistorial=?,"
                  + "idHistorialMenu=? "
                  + "where idHistorialPeso=?";
            PreparedStatement ps =con.prepareStatement(q);

            ps.setDate(1,hp.getFechaHistorial());
            ps.setInt(2,hp.getIdUsuario());
            ps.setFloat(3,hp.getPesoHistorial());
            ps.setInt(4,hp.getIdHistorialMenu());
            ps.setInt(5,hp.getIdHistorialPeso());
            
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
            Connection con=HistorialPesoDao.getConnection();
            String q;
            q="delete from HistorialPeso where idHistorialPeso=? ";

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
     
     public static  HistorialPeso getHistorialPesoByIdHistorialMenu(int idHistorialMenu) throws SQLException{
        
        HistorialPeso hp=new HistorialPeso();
        
        try{
            Connection con=HistorialPesoDao.getConnection();
            String q;
            q="Select * from HistorialPeso where idHistorialMenu=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, idHistorialMenu);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                
                hp.setIdHistorialPeso(rs.getInt(1));
                hp.setFechaHistorial(rs.getDate(2));
                hp.setIdUsuario(rs.getInt(3));
                hp.setPesoHistorial(rs.getFloat(4));                
                hp.setIdHistorialMenu(rs.getInt(5));                
            }

            con.close();

        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return hp;
    }
            
     
    public static List<HistorialPeso> getAllHistorialPesobyidUsuario(int idUsuario) throws SQLException{
        
        List<HistorialPeso> lista =new ArrayList<HistorialPeso>();
        
      
        try{
            Connection con=HistorialPesoDao.getConnection();
            String q;
            q="Select *from HistorialPeso where idUsuario = ? order by fechaHistorial";

            PreparedStatement ps =con.prepareStatement(q);
             ps.setInt(1, idUsuario);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                    HistorialPeso hp=new HistorialPeso(); 
                    hp.setIdHistorialPeso(rs.getInt(1));
                    hp.setFechaHistorial(rs.getDate(2));
                    hp.setIdUsuario(rs.getInt(3));
                    hp.setPesoHistorial(rs.getFloat(4));                
                    hp.setIdHistorialMenu(rs.getInt(5));   
                    
                   lista.add(hp);
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
