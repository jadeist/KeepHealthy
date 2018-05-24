package Data;


import Data.*;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego
 */
public class MenuUsuarioCreaDao {
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
    
    public static int Guardar(MenuUsuarioCrea m) throws SQLException{
        
        int estatus=0;
       
        try{
        Connection con=MenuUsuarioCreaDao.getConnection();
            String q;
            q="insert into MenuUsuarioCrea(idUsuario,CaloriasDiarias,calorias1,calorias2,calorias3,"
              +"calorias4,calorias5,"
              +"idAlimento11,idAlimento12,idAlimento13,idAlimento14,idAlimento15,"
              +"idAlimento16,idAlimento17,idAlimento18,idAlimento19,"
              +"idAlimento21,idAlimento22,idAlimento23,idAlimento24,idAlimento25,"
              +"idAlimento26,idAlimento27,idAlimento28,idAlimento29,"
              +"idAlimento31,idAlimento32,idAlimento33,idAlimento34,idAlimento35,"
              +"idAlimento36,idAlimento37,idAlimento38,idAlimento39,"
              +"idAlimento41,idAlimento42,idAlimento43,idAlimento44,idAlimento45,"
              +"idAlimento46,idAlimento47,idAlimento48,idAlimento49,"
              +"idAlimento51,idAlimento52,idAlimento53,idAlimento54,idAlimento55,"
              +"idAlimento56,idAlimento57,idAlimento58,idAlimento59,"
              +") "
              +"values (?,?,?,?,?,?,?,?,?,?,"
              +"?,?,?,?,?,?,?,?,?,?"
              +"?,?,?,?,?,?,?,?,?,?"
              +"?,?,?,?,?,?,?,?,?,?"
              +"?,?,?,?,?,?,?,?,?,?"
              +"?,?,?,?,?,?,?"
              +")";

            PreparedStatement ps =con.prepareStatement(q);
            
            ps.setInt(1,m.getIdUsuario());
            ps.setFloat(2,m.getCaloriasDiarias());
            ps.setFloat(3,m.getCalorias1());
            ps.setFloat(4,m.getCalorias2());
            ps.setFloat(5,m.getCalorias3());
            ps.setFloat(6,m.getCalorias4());
            ps.setFloat(7,m.getCalorias5());
          
            ps.setInt(8,m.getIdAlimento11());
            ps.setInt(9,m.getIdAlimento12());
            ps.setInt(10,m.getIdAlimento13());
            ps.setInt(11,m.getIdAlimento14());
            ps.setInt(12,m.getIdAlimento15());
            ps.setInt(13,m.getIdAlimento16());
            ps.setInt(14,m.getIdAlimento17());
            ps.setInt(15,m.getIdAlimento18());
            ps.setInt(16,m.getIdAlimento19());
            
            ps.setInt(17,m.getIdAlimento21());
            ps.setInt(18,m.getIdAlimento22());
            ps.setInt(19,m.getIdAlimento23());
            ps.setInt(20,m.getIdAlimento24());
            ps.setInt(21,m.getIdAlimento25());
            ps.setInt(22,m.getIdAlimento26());
            ps.setInt(23,m.getIdAlimento27());
            ps.setInt(24,m.getIdAlimento28());
            ps.setInt(25,m.getIdAlimento29());

            ps.setInt(26,m.getIdAlimento31());
            ps.setInt(27,m.getIdAlimento32());
            ps.setInt(28,m.getIdAlimento33());
            ps.setInt(29,m.getIdAlimento34());
            ps.setInt(30,m.getIdAlimento35());
            ps.setInt(31,m.getIdAlimento36());
            ps.setInt(32,m.getIdAlimento37());
            ps.setInt(33,m.getIdAlimento38());
            ps.setInt(34,m.getIdAlimento39());
           
            ps.setInt(35,m.getIdAlimento41());
            ps.setInt(36,m.getIdAlimento42());
            ps.setInt(37,m.getIdAlimento43());
            ps.setInt(38,m.getIdAlimento44());
            ps.setInt(39,m.getIdAlimento45());
            ps.setInt(40,m.getIdAlimento46());
            ps.setInt(41,m.getIdAlimento47());
            ps.setInt(42,m.getIdAlimento48());
            ps.setInt(43,m.getIdAlimento49());
            
            ps.setInt(44,m.getIdAlimento51());
            ps.setInt(45,m.getIdAlimento52());
            ps.setInt(46,m.getIdAlimento53());
            ps.setInt(47,m.getIdAlimento54());
            ps.setInt(48,m.getIdAlimento55());
            ps.setInt(49,m.getIdAlimento56());
            ps.setInt(50,m.getIdAlimento57());
            ps.setInt(51,m.getIdAlimento58());
            ps.setInt(52,m.getIdAlimento59());
            
            estatus=ps.executeUpdate();
            con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
    public static int Actualizar(MenuUsuarioCrea m) throws SQLException{
        
        int estatus=0;

        try{
            Connection con=MenuUsuarioCreaDao.getConnection();
            String q;
            q="update MenuUsuarioCrea set "                  
                    +"idUsuario=?, "
                    +"CaloriasDiarias=?, "
                    +"calorias1=?, "
                    +"calorias2=?, "
                    +"calorias3=?, "
                    +"calorias4=?, "
                    +"calorias5=?, "
                    +"idAlimento11=?, "
                    +"idAlimento12=?, "
                    +"idAlimento13=?, "
                    +"idAlimento14=?, "
                    +"idAlimento15=?, "
                    +"idAlimento16=?, "
                    +"idAlimento17=?, "
                    +"idAlimento18=?, "
                    +"idAlimento19=?, "
                    +"idAlimento21=?, "
                    +"idAlimento22=?, "
                    +"idAlimento23=?, "
                    +"idAlimento24=?, "
                    +"idAlimento25=?, "
                    +"idAlimento26=?, "
                    +"idAlimento27=?, "
                    +"idAlimento28=?, "
                    +"idAlimento29=?, "
                    +"idAlimento31=?, "
                    +"idAlimento32=?, "
                    +"idAlimento33=?, "
                    +"idAlimento34=?, "
                    +"idAlimento35=?, "
                    +"idAlimento36=?, "
                    +"idAlimento37=?, "
                    +"idAlimento38=?, "
                    +"idAlimento39=?, "
                    +"idAlimento41=?, "
                    +"idAlimento42=?, "
                    +"idAlimento43=?, "
                    +"idAlimento44=?, "
                    +"idAlimento45=?, "
                    +"idAlimento46=?, "
                    +"idAlimento47=?, "
                    +"idAlimento48=?, "
                    +"idAlimento49=?, "
                    +"idAlimento51=?, "
                    +"idAlimento52=?, "
                    +"idAlimento53=?, "
                    +"idAlimento54=?, "
                    +"idAlimento55=?, "
                    +"idAlimento56=?, "
                    +"idAlimento57=?, "
                    +"idAlimento58=?, "
                    +"idAlimento59=? "                    
                  + "where idMenuUsuarioCrea=?";
            
            PreparedStatement ps =con.prepareStatement(q);            
            
            ps.setInt(1,m.getIdUsuario());
            ps.setFloat(2,m.getCaloriasDiarias());
            ps.setFloat(3,m.getCalorias1());
            ps.setFloat(4,m.getCalorias2());
            ps.setFloat(5,m.getCalorias3());
            ps.setFloat(6,m.getCalorias4());
            ps.setFloat(7,m.getCalorias5());
          
            ps.setInt(8,m.getIdAlimento11());
            ps.setInt(9,m.getIdAlimento12());
            ps.setInt(10,m.getIdAlimento13());
            ps.setInt(11,m.getIdAlimento14());
            ps.setInt(12,m.getIdAlimento15());
            ps.setInt(13,m.getIdAlimento16());
            ps.setInt(14,m.getIdAlimento17());
            ps.setInt(15,m.getIdAlimento18());
            ps.setInt(16,m.getIdAlimento19());
            
            ps.setInt(17,m.getIdAlimento21());
            ps.setInt(18,m.getIdAlimento22());
            ps.setInt(19,m.getIdAlimento23());
            ps.setInt(20,m.getIdAlimento24());
            ps.setInt(21,m.getIdAlimento25());
            ps.setInt(22,m.getIdAlimento26());
            ps.setInt(23,m.getIdAlimento27());
            ps.setInt(24,m.getIdAlimento28());
            ps.setInt(25,m.getIdAlimento29());

            ps.setInt(26,m.getIdAlimento31());
            ps.setInt(27,m.getIdAlimento32());
            ps.setInt(28,m.getIdAlimento33());
            ps.setInt(29,m.getIdAlimento34());
            ps.setInt(30,m.getIdAlimento35());
            ps.setInt(31,m.getIdAlimento36());
            ps.setInt(32,m.getIdAlimento37());
            ps.setInt(33,m.getIdAlimento38());
            ps.setInt(34,m.getIdAlimento39());
           
            ps.setInt(35,m.getIdAlimento41());
            ps.setInt(36,m.getIdAlimento42());
            ps.setInt(37,m.getIdAlimento43());
            ps.setInt(38,m.getIdAlimento44());
            ps.setInt(39,m.getIdAlimento45());
            ps.setInt(40,m.getIdAlimento46());
            ps.setInt(41,m.getIdAlimento47());
            ps.setInt(42,m.getIdAlimento48());
            ps.setInt(43,m.getIdAlimento49());
            
            ps.setInt(44,m.getIdAlimento51());
            ps.setInt(45,m.getIdAlimento52());
            ps.setInt(46,m.getIdAlimento53());
            ps.setInt(47,m.getIdAlimento54());
            ps.setInt(48,m.getIdAlimento55());
            ps.setInt(49,m.getIdAlimento56());
            ps.setInt(50,m.getIdAlimento57());
            ps.setInt(51,m.getIdAlimento58());
            ps.setInt(52,m.getIdAlimento59());
            ps.setInt(53,m.getIdMenuUsuarioCrea());

            estatus=ps.executeUpdate();
            con.close();

        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
    
     public static int Eliminar(int idUsuario) throws SQLException{
        
        int estatus=0;
        
        /*Es necesrio contar con un estado
        de la tabla de la bd para saber si el empleado
        que voy a registrar es nuevo o no */
        try{
        Connection con=MenuUsuarioCreaDao.getConnection();
        String q;
        q="delete from MenuUsuarioCrea where idUsuario=? ";
        
        PreparedStatement ps =con.prepareStatement(q);
        
        ps.setInt(1,idUsuario);
        
        estatus=ps.executeUpdate();
        con.close();
        
        
        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return estatus;
    }
     
     public static  MenuUsuarioCrea getMenuUsuarioCreaByIdUsuario (int idUsuario) throws SQLException{
        
        MenuUsuarioCrea m=new MenuUsuarioCrea();      
        try{
            Connection con=MenuUsuarioCreaDao.getConnection();
            String q;
            q="Select idMenuUsuarioCrea, idUsuario, CaloriasDiarias, calorias1,calorias2,calorias3,"
                  +"calorias4,calorias5,"
                  +"idAlimento11,idAlimento12,idAlimento13,idAlimento14,idAlimento15,"
                  +"idAlimento16,idAlimento17,idAlimento18,idAlimento19,"
                  +"idAlimento21,idAlimento22,idAlimento23,idAlimento24,idAlimento25,"
                  +"idAlimento26,idAlimento27,idAlimento28,idAlimento29,"
                  +"idAlimento31,idAlimento32,idAlimento33,idAlimento34,idAlimento35,"
                  +"idAlimento36,idAlimento37,idAlimento38,idAlimento39,"
                  +"idAlimento41,idAlimento42,idAlimento43,idAlimento44,idAlimento45,"
                  +"idAlimento46,idAlimento47,idAlimento48,idAlimento49,"
                  +"idAlimento51,idAlimento52,idAlimento53,idAlimento54,idAlimento55,"
                  +"idAlimento56,idAlimento57,idAlimento58,idAlimento59 "
                  +"from MenuUsuarioCrea where idUsuario=?";

            PreparedStatement ps =con.prepareStatement(q);

            ps.setInt(1, idUsuario);

            ResultSet rs=ps.executeQuery();

            if(rs.next()){            
                m.setIdMenuUsuarioCrea(rs.getInt(1));
                m.setIdUsuario(rs.getInt(2));
                m.setCaloriasDiarias(rs.getFloat(3));
                m.setCalorias1(rs.getFloat(4));
                m.setCalorias2(rs.getFloat(5));
                m.setCalorias3(rs.getFloat(6));
                m.setCalorias4(rs.getFloat(7));
                m.setCalorias5(rs.getFloat(8));
                
                m.setIdAlimento11(rs.getInt(9));
                m.setIdAlimento12(rs.getInt(10));
                m.setIdAlimento13(rs.getInt(11));
                m.setIdAlimento14(rs.getInt(12));
                m.setIdAlimento15(rs.getInt(13));
                m.setIdAlimento16(rs.getInt(14));
                m.setIdAlimento17(rs.getInt(15));
                m.setIdAlimento18(rs.getInt(16));
                m.setIdAlimento19(rs.getInt(17));
                
                m.setIdAlimento21(rs.getInt(18));
                m.setIdAlimento22(rs.getInt(19));
                m.setIdAlimento23(rs.getInt(20));
                m.setIdAlimento24(rs.getInt(21));
                m.setIdAlimento25(rs.getInt(22));
                m.setIdAlimento26(rs.getInt(23));
                m.setIdAlimento27(rs.getInt(24));
                m.setIdAlimento28(rs.getInt(25));
                m.setIdAlimento29(rs.getInt(26));
                
                m.setIdAlimento31(rs.getInt(27));
                m.setIdAlimento32(rs.getInt(28));
                m.setIdAlimento33(rs.getInt(29));
                m.setIdAlimento34(rs.getInt(30));
                m.setIdAlimento35(rs.getInt(31));
                m.setIdAlimento36(rs.getInt(32));
                m.setIdAlimento37(rs.getInt(33));
                m.setIdAlimento38(rs.getInt(34));
                m.setIdAlimento39(rs.getInt(35));
                
                m.setIdAlimento41(rs.getInt(36));
                m.setIdAlimento42(rs.getInt(37));
                m.setIdAlimento43(rs.getInt(38));
                m.setIdAlimento44(rs.getInt(39));
                m.setIdAlimento45(rs.getInt(40));
                m.setIdAlimento46(rs.getInt(41));
                m.setIdAlimento47(rs.getInt(42));
                m.setIdAlimento48(rs.getInt(43));
                m.setIdAlimento49(rs.getInt(44));
                
                m.setIdAlimento51(rs.getInt(45));
                m.setIdAlimento52(rs.getInt(46));
                m.setIdAlimento53(rs.getInt(47));
                m.setIdAlimento54(rs.getInt(48));
                m.setIdAlimento55(rs.getInt(49));
                m.setIdAlimento56(rs.getInt(50));
                m.setIdAlimento57(rs.getInt(51));
                m.setIdAlimento58(rs.getInt(52));
                m.setIdAlimento59(rs.getInt(53));
            } 
            else{
                m.setIdMenuUsuarioCrea(0);
                m.setIdUsuario(0);            
            }

            con.close();

        }catch(Exception d){
            System.out.println("Error");
            System.out.println(d.getMessage());
            System.out.println(d.getStackTrace());
        }
        return m;
    }
     
       
        
       
}
