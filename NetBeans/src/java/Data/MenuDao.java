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



public class MenuDao {
   
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
    
    /*Primer metodo Guardar Empleado*/
    
    public static int Guardar(Menu m) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=MenuDao.getConnection();
        String q;
        q="insert into Menu (noMenu,caloriasMenu) values (?,?) ";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1,m.getNoMenu());        
        ps.setFloat(2,m.getCaloriasMenu());
              
        estatus=ps.executeUpdate();
        con.close();
              
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
    public static int Actualizar(Menu m) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=MenuDao.getConnection();
        String q;
        q="update Menu set " 
                +"noMenu=?," 
                +"caloriasMenu=?," 
                +"where idMenu=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        
        ps.setInt(1,m.getNoMenu());
        ps.setFloat(2,m.getCaloriasMenu());
        ps.setInt(3,m.getIdMenu());
        
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
        Connection con=MenuDao.getConnection();
        String q;
        q="delete from Menu where idMenu=? ";
        
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
     
     public static  Menu getMenuById (int id) throws SQLException{
        
        Menu m=new Menu();
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=MenuDao.getConnection();
        String q;
        q="Select * from Menu where idMenu=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1, id);
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next()){
            m.setIdMenu(rs.getInt(1));            
            m.setNoMenu(rs.getInt(2));
            m.setCaloriasMenu(rs.getFloat(3));
        }
        
        
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return m;
    }
     
    public static  Menu getMenuBynoMenu (int noMenu) throws SQLException{
        
        Menu m=new Menu();
        
        try{
            Connection con=MenuDao.getConnection();
            String q;
            q="Select * from Menu where noMenu=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, noMenu);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                m.setIdMenu(rs.getInt(1));            
                m.setNoMenu(rs.getInt(2));
                m.setCaloriasMenu(rs.getFloat(3));
            }
            else{
                m.setIdMenu(0);            
                m.setNoMenu(0);
                m.setCaloriasMenu(0f);
            }
            con.close();                
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return m;
    }
       
    public static  Menu getLastnoMenu () throws SQLException{
        
        Menu m=new Menu();                
        try{
            Connection con=MenuDao.getConnection();
            String q;
            q="Select max(noMenu) from Menu ";

            PreparedStatement ps =con.prepareStatement(q);            

            ResultSet rs=ps.executeQuery();

            if(rs.next()){
                m.setNoMenu(rs.getInt(1));                    
            }
            con.close();        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return m;
    }
    
    public static List<Menu> getAllMenus(int id ) throws SQLException{
        
        List<Menu> lista =new ArrayList<Menu>();
        
        
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=MenuDao.getConnection();
        String q;
        q="Select * from Menu where idMenu=?";
        
        PreparedStatement ps =con.prepareStatement(q);
        ps.setInt(1, id);
        
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
                Menu m=new Menu();
                m.setIdMenu(rs.getInt(1));                
                m.setNoMenu(rs.getInt(2));
                m.setCaloriasMenu(rs.getFloat(3));
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
    
    
    
}