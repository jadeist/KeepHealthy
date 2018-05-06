package Data;

import Data.Usuario;
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



public class UsuarioDao {
   
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
    
    public static int Guardar(Usuario u) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=UsuarioDao.getConnection();
        String q;
        q="insert into Usuario(Clave, Password , idperfil, NombreUsuario, idsexo , FechaNacimiento, Peso, Estatura, Ocupacion, IdActividad, FechaRegistro, FechaUltVez , CaloriasDiarias ) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setString(1,u.getClave());
        ps.setString(2,u.getPassword());
        ps.setInt(3,u.getIdperfil());
        ps.setString(4,u.getNombreUsuario());
        ps.setInt(5,u.getIdsexo());
        ps.setString(6,u.getFechaNacimiento());
        ps.setFloat(7,u.getPeso());
        ps.setFloat(8,u.getEstatura());
        ps.setString(9,u.getOcupacion());
        ps.setInt(10,u.getIdActividad());
        ps.setString(11,u.getFechaRegistro());
        ps.setString(12,u.getFechaUltVez());
        ps.setFloat(13,u.getCaloriasDiarias());
        
        estatus=ps.executeUpdate();
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
    public static int Actualizar(Usuario u) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=UsuarioDao.getConnection();
        String q;
      q="update Usuario set Clave=?,"
              + "Password=?,"
              + "idperfil=?,"
              + "NombreUsuario=?,"
              + "idsexo=?,"
              + "FechaNacimiento=?,"
              + "Peso=?,"
              + "Estatura=?,"
              + "Ocupacion=?,"
              + "IdActividad=?,"
              + "FechaRegistro=?,"
              + "FechaUltVez=?,"
              + "CaloriasDiarias=?,"
              + "where id_Usuario=?";
        PreparedStatement ps =con.prepareStatement(q);

        ps.setString(1,u.getClave());
        ps.setString(2,u.getPassword());
        ps.setInt(3,u.getIdperfil());
        ps.setString(4,u.getNombreUsuario());
        ps.setInt(5,u.getIdsexo());
        ps.setString(6,u.getFechaNacimiento());
        ps.setFloat(7,u.getPeso());
        ps.setFloat(8,u.getEstatura());
        ps.setString(9,u.getOcupacion());
        ps.setInt(10,u.getIdActividad());
        ps.setString(11,u.getFechaRegistro());
        ps.setString(12,u.getFechaUltVez());
        ps.setFloat(13,u.getCaloriasDiarias());
        ps.setInt(14,u.getId_Usuario());
        
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
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=UsuarioDao.getConnection();
        String q;
        q="delete from Usuario where id_Usuario=? ";
        
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
     
     public static  Usuario getUsuarioById (int id) throws SQLException{
        
        Usuario u=new Usuario();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=UsuarioDao.getConnection();
        String q;
        q="Select * from Usuario where id_Usuario=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1, id);
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){
            u.setId_Usuario(rs.getInt(1));
            u.setClave(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setIdperfil(rs.getInt(4));
            u.setNombreUsuario(rs.getString(5));
            u.setIdsexo(rs.getInt(6));
            u.setFechaNacimiento(rs.getString(7));
            u.setPeso(rs.getFloat(8));
            u.setEstatura(rs.getFloat(9));
            u.setOcupacion(rs.getString(10));
            u.setIdActividad(rs.getInt(11));
            u.setFechaRegistro(rs.getString(12));
            u.setFechaUltVez(rs.getString(13));
            u.setCaloriasDiarias(rs.getFloat(14));
            
        }
        
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return u;
    }
     
       public static  Usuario getUsuarioByClave (String clave) throws SQLException{
        
        Usuario u=new Usuario();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=UsuarioDao.getConnection();
        String q;
        q="Select * from Usuario where Clave=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setString(1, clave);
        
        ResultSet rs=ps.executeQuery();
       
        if(rs.next()){
           
            u.setId_Usuario(rs.getInt(1));
            u.setClave(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setIdperfil(rs.getInt(4));
            u.setNombreUsuario(rs.getString(5));
            u.setIdsexo(rs.getInt(6));
            u.setFechaNacimiento(rs.getString(7));
            u.setPeso(rs.getFloat(8));
            u.setEstatura(rs.getFloat(9));
            u.setOcupacion(rs.getString(10));
            u.setIdActividad(rs.getInt(11));
            u.setFechaRegistro(rs.getString(12));
            u.setFechaUltVez(rs.getString(13));
            u.setCaloriasDiarias(rs.getFloat(14));
            
        }
        else{
            out.println("usuario no encontrado");
        }
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return u;
    }
     
    public static List<Usuario> getAllUsuarios() throws SQLException{
        
        List<Usuario> lista =new ArrayList<Usuario>();
        
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=UsuarioDao.getConnection();
        String q;
        q="Select *from Usuario ";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
                Usuario u=new Usuario(); 
                u.setId_Usuario(rs.getInt(1));
                u.setClave(rs.getString(2));
                u.setPassword(rs.getString(3));
                u.setIdperfil(rs.getInt(4));
                u.setNombreUsuario(rs.getString(5));
                u.setIdsexo(rs.getInt(6));
                u.setFechaNacimiento(rs.getString(7));
                u.setPeso(rs.getFloat(8));
                u.setEstatura(rs.getFloat(9));
                u.setOcupacion(rs.getString(10));
                u.setIdActividad(rs.getInt(11));
                u.setFechaRegistro(rs.getString(12));
                u.setFechaUltVez(rs.getString(13));
                u.setCaloriasDiarias(rs.getFloat(14));
               lista.add(u);
        }
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        
        return lista;
    }
    
   public static  Usuario VerificarUsuario (String clave, String pass) throws SQLException{
        
        Usuario u=new Usuario();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=UsuarioDao.getConnection();
        String q;
        q="Select id_Usuario,Clave, Password , idperfil, NombreUsuario, idsexo , FechaNacimiento, Peso, Estatura, Ocupacion, IdActividad, FechaRegistro, FechaUltVez , CaloriasDiarias from Usuario whre Clave=? and Password=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setString(1,clave);
        ps.setString(2,pass);
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){            
            u.setId_Usuario(rs.getInt(1));
            u.setClave(rs.getString(2));
            u.setPassword(rs.getString(3));
            u.setIdperfil(rs.getInt(4));
            u.setNombreUsuario(rs.getString(5));
            u.setIdsexo(rs.getInt(6));
            u.setFechaNacimiento(rs.getString(7));
            u.setPeso(rs.getFloat(8));
            u.setEstatura(rs.getFloat(9));
            u.setOcupacion(rs.getString(10));
            u.setIdActividad(rs.getInt(11));
            u.setFechaRegistro(rs.getString(12));
            u.setFechaUltVez(rs.getString(13));
            u.setCaloriasDiarias(rs.getFloat(14));
        }else{
            u.setId_Usuario(0);
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
