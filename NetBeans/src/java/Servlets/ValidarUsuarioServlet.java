package Servlets;

import Data.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ValidarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String Nickname,Contrasena;
            
            Nickname=request.getParameter("Nickname");
            Contrasena=request.getParameter("Contrasena");            

            String resultadonombre, resultadopassword;
            int res;
          
            res = 0;
            
            resultadonombre = validanombre(Nickname,15,1);
            if (resultadonombre!="OK")
                    res+=1;
            resultadopassword = validapass(Contrasena,15,1);
            if (resultadopassword!="OK")
                    res+=1;
            if(res==0){
                Usuario u=new Usuario();
            
                u=UsuarioDao.VerificarUsuario(Nickname,Contrasena);

                if((u.getNickname())!=null){                    
                    //usuario existente 
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script src=\"assets/js/sweetalert.min.js\"></script>");
                    out.println("<link href=\"assets/css/sweetalert.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type=\"text/javascript\">");
                    out.println("swal({title: \"Bienvenido!\",text: \"\",type: \"success\"},");
                    out.println("function () {window.location.href = 'inicio.html';});");
                    out.println("</script>"); 
                    out.println("</body>");
                    out.println("</html>");
                                                      
                }
                else{
                    //usuario inexistente
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script src=\"assets/js/sweetalert.min.js\"></script>");
                    out.println("<link href=\"assets/css/sweetalert.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type=\"text/javascript\">");
                    out.println("swal({title: \"Hubo un error\",text: \"Usuario o contraseña incorrecta.\",type: \"error\"},");
                    out.println("function () {window.location.href = 'InicioSesion.html';});");
                    out.println("</script>"); 
                    out.println("</body>");
                    out.println("</html>");
                }
                out.close();
            }
            else{
                out.println("<html>");
                out.println("<head>");
                out.println("<script src=\"assets/js/sweetalert.min.js\"></script>");
                out.println("<link href=\"assets/css/sweetalert.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                out.println("</head>");
                out.println("<body>");
                out.println("<script type=\"text/javascript\">");
                out.println("swal({title: \"Ocurrio un error\",");
                    
                if (resultadonombre!="OK"){
                    out.println("text: \"Error en el Nickname del usuario: "+resultadonombre+" \",type: \"error\"},");
                    out.println("function () {window.location.href = 'InicioSesion.html';});");
                    out.println("</script>"); 
                    out.println("</body>");
                    out.println("</html>");
                }
                    
                if (resultadopassword!="OK"){
                    out.println("text: \"Error en el campo de contraseña: "+resultadopassword+" \",type: \"error\"},");
                    out.println("function () {window.location.href = 'InicioSesion.html';});");
                    out.println("</script>"); 
                    out.println("</body>");
                    out.println("</html>");
                }
            }             
        } 
        catch (SQLException ex) {
            Logger.getLogger(RegistrarUsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    public String validanombre(String Cadena, int maximo, int obligatorio) throws ServletException{
        String Error="";        
        String checkOK="abcdefghijklmnñopqrstuvwxyzáéíóúABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ1234567890";        
        boolean allvalido = true;
        int k;
        try{                        
             if (Cadena.length()==0 && obligatorio==1){
                 Error="El campo es obligatorio ";
             }    
             else{  
                if (Cadena.length()==0 && obligatorio==0){
                    Error ="OK";
                } 
                else{
                    if (Cadena.length()>maximo ){
                        Error="La longitud del campo no debe ser mayor a " + maximo;
                    }
                    else{                    
                        for(int i=0;i<Cadena.length();i++){
                            char ch= Cadena.charAt(i);
                            for(k=0;k<checkOK.length();k++){
                              if (ch == checkOK.charAt(k))
                                 break;
                            }
                            if (k==checkOK.length()){
                                  allvalido=false;
                            }                          
                        }                    
                        if (allvalido == false){
                            Error = "Solo se permiten letras y numeros en este campo sin espacios";
                        }
                        else{
                            Error ="OK";                    
                        }                                         
                    }    
                }                                                                                          
            }
            
        }catch(Exception e){
            System.out.println("Sólo jugó contigo ¬¬");
        }
         return Error; 
    }
 public String validanombreUsuario(String Cadena, int maximo, int obligatorio) throws ServletException{
        String Error="";        
        String checkOK="abcdefghijklmnñopqrstuvwxyzáéíóúABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ ";        
        boolean allvalido = true;
        int k;
        try{                        
             if (Cadena.length()==0 && obligatorio==1){
                 Error="El campo es obligatorio ";
             }    
             else{  
                if (Cadena.length()==0 && obligatorio==0){
                    Error ="OK";
                } 
                else{
                    if (Cadena.length()>maximo ){
                        Error="La longitud del campo no debe ser mayor a " + maximo;
                    }
                    else{                    
                        for(int i=0;i<Cadena.length();i++){
                            char ch= Cadena.charAt(i);
                            for(k=0;k<checkOK.length();k++){
                              if (ch == checkOK.charAt(k))
                                 break;
                            }
                            if (k==checkOK.length()){
                                  allvalido=false;
                            }                          
                        }                    
                        if (allvalido == false){
                            Error = "Solo se permiten letras y espacio en este campo";
                        }
                        else{
                            Error ="OK";                    
                        }                                         
                    }    
                }                                                                                          
            }
            
        }catch(Exception e){
            System.out.println("Sólo jugó contigo ¬¬");
        }
         return Error; 
    }
     public String validapass(String Cadena,int maximo, int obligatorio) throws ServletException{
        String Error="";        
        String checkOK="abcdefghijklmnñopqrstuvwxyzáéíóúABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ1234567890";        
        boolean allvalido = true;
        int k;
        try{                        
             if (Cadena.length()==0 && obligatorio==1){
                 Error="El campo es obligatorio ";
             }    
             else{  
                if (Cadena.length()==0 && obligatorio==0){
                    Error ="OK";
                } 
                else{
                    if (Cadena.length()>maximo ){
                        Error="La longitud del campo no debe ser mayor a " + maximo;
                    }
                    else{                    
                        for(int i=0;i<Cadena.length();i++){
                            char ch= Cadena.charAt(i);
                            for(k=0;k<checkOK.length();k++){
                              if (ch == checkOK.charAt(k))
                                 break;
                            }
                            if (k==checkOK.length()){
                                  allvalido=false;
                            }                          
                        }                    
                        if (allvalido == false){
                            Error = "Solo se permiten números y letras en este campo";
                        }else if(Cadena.length()<5){
                            Error="La longitude del password debe de ser mayor a 5";
                        }    
                        else{
                            Error ="OK";                    
                        }                                         
                    }    
                }                                                                                          
            }
            
        }catch(Exception e){
            System.out.println("Sólo jugó contigo ¬¬");
        }
         return Error; 
    }     
}
