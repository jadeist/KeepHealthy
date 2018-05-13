package Data;

import Data.*;
import static java.lang.System.out;
import java.util.*;
import java.sql.*;
import javax.servlet.ServletException;



public class HistorialMenuDao {
   
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
    
    public static int Guardar(HistorialMenu hm) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
            Connection con=HistorialMenuDao.getConnection();
            String q;
            q="insert into HistorialMenu(idMenu, idUsuario, fechaCreacion,Calificacion,ComentarioHistorial ) values (?,?,?,?,?)";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1,hm.getIdMenu());
            ps.setInt(2,hm.getIdUsuario());
            ps.setDate(3, hm.getFechaCreacion());
            ps.setInt(4,hm.getCalificacion());
            ps.setString(5,hm.getComentarioHistorial());
            
            estatus=ps.executeUpdate();
            con.close();        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
    public static int Actualizar(HistorialMenu hm) throws SQLException{
        
        int estatus=0;
        
        try{
            Connection con=HistorialMenuDao.getConnection();
            String q;
            q="update HistorialMenu set "
                  + "idMenu=?,"
                  + "idUsuario=?,"
                  + "fechaCreacion=?,"
                  + "Calificacion=?, "
                  + "ComentarioHistorial=? "  
                  + "where idHistorialMenu=?";
            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1,hm.getIdMenu());
            ps.setInt(2,hm.getIdUsuario());
            ps.setDate(3,hm.getFechaCreacion());
            ps.setInt(4,hm.getCalificacion());
            ps.setString(5,hm.getComentarioHistorial());
            ps.setInt(6,hm.getIdHistorialMenu());
            
            estatus=ps.executeUpdate();
            con.close();

        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
     public static int Eliminar(int idHistorialMenu) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=UsuarioDao.getConnection();
        String q;
        q="delete from HistorialMenu where idHistorialMenu=? ";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1,idHistorialMenu);
        
        estatus=ps.executeUpdate();
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
     
     public static  HistorialMenu getHistorialMenuById (int idHistorialMenu) throws SQLException{
        
        HistorialMenu hm=new HistorialMenu();
                
        try{
            Connection con=HistorialMenuDao.getConnection();
            String q;
            q="Select * from HistorialMenu where idHistorialMenu=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, idHistorialMenu);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                hm.setIdHistorialMenu(rs.getInt(1));
                hm.setIdMenu(rs.getInt(2));
                hm.setIdUsuario(rs.getInt(3));
                hm.setFechaCreacion(rs.getDate(4));
                hm.setCalificacion(rs.getInt(5));                
                hm.setComentarioHistorial(rs.getString(6));
            }
            con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return hm;
    }
     
       public static  HistorialMenu getHistorialMenuid (int idMenu, int idUsuario, java.sql.Date datesqlFechaCreacion) throws SQLException{
        
        HistorialMenu hm=new HistorialMenu();
        
        try{
            Connection con=HistorialMenuDao.getConnection();
            String q;
            q="Select * from HistorialMenu where idMenu=? and idUsuario=? and FechaCreacion=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, idMenu);
            ps.setInt(2, idUsuario);
            ps.setDate(3, datesqlFechaCreacion);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                hm.setIdHistorialMenu(rs.getInt(1));
                hm.setIdMenu(rs.getInt(2));
                hm.setIdUsuario(rs.getInt(3));
                hm.setFechaCreacion(rs.getDate(4));
                hm.setCalificacion(rs.getInt(5));
                hm.setComentarioHistorial(rs.getString(6));                

            }
            else{
                out.println("historialmenu  no encontrado");
            }

            con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return hm;
    }
       
       public static  HistorialMenu getHistorialMenuByidMenu (int idMenu) throws SQLException{
        
        HistorialMenu hm=new HistorialMenu();
        
        try{
            Connection con=HistorialMenuDao.getConnection();
            String q;
            q="Select * from HistorialMenu where idMenu=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, idMenu);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                hm.setIdHistorialMenu(rs.getInt(1));
                hm.setIdMenu(rs.getInt(2));
                hm.setIdUsuario(rs.getInt(3));
                hm.setFechaCreacion(rs.getDate(4));
                hm.setCalificacion(rs.getInt(5));
                hm.setComentarioHistorial(rs.getString(6));                

            }
            else{
                out.println("historialmenu  no encontrado");
            }

            con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return hm;
    }
           
    public static List<HistorialMenu> getAllHistorialMenuByIdUsuario(int idUsuario) throws SQLException{
        
        List<HistorialMenu> lista =new ArrayList<HistorialMenu>();
                
        try{
            Connection con=HistorialMenuDao.getConnection();
            String q;
            q="Select *from HistorialMenu where idUsuario = ? ";

            PreparedStatement ps =con.prepareStatement(q);
             ps.setInt(1, idUsuario);
             
            ResultSet rs=ps.executeQuery();

            while(rs.next()){
                    HistorialMenu hm=new HistorialMenu(); 
                    hm.setIdHistorialMenu(rs.getInt(1));
                    hm.setIdMenu(rs.getInt(2));
                    hm.setIdUsuario(rs.getInt(3));
                    hm.setFechaCreacion(rs.getDate(4));
                    hm.setCalificacion(rs.getInt(5));
                    hm.setComentarioHistorial(rs.getString(6));
                    
                   lista.add(hm);
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
