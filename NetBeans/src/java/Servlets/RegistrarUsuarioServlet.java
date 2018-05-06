
package Servlets;

import Data.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegistrarUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String Nickname,Contrasena,Contrasena2,nombreUsuario,FechaNacimiento,Ocupacion,FechaUltVez,FechaRegistro,SexoS,ActividadS,PesoS,EstaturaS;
            int idPerfil;
            int idSexo,idActividad;
            float pesoUsuario,Estatura,CaloriasDiarias;
            
            Nickname=request.getParameter("Nickname");
            Contrasena=request.getParameter("Contrasena");
            Contrasena2=request.getParameter("Contrasena2");
            nombreUsuario=request.getParameter("nombreUsuario");
            SexoS=request.getParameter("SexoS");
            ActividadS=request.getParameter("ActividadS");
            PesoS=request.getParameter("Peso");
            EstaturaS=request.getParameter("Estatura");
            FechaNacimiento=request.getParameter("FechaNacimiento");

            String resultadonombre, resultadopassword;
            int res;
          
            res = 0;
            
            resultadonombre = validanombre(Nickname,15,1);
            if (resultadonombre!="OK")
                    res+=1;
            resultadopassword = validapass(Contrasena,Contrasena2,15,1);
            if (resultadopassword!="OK")
                    res+=1;
            if(res==0){
                Usuario u=new Usuario();
            
                u=UsuarioDao.getUsuarioByNickname(Nickname);

                if((u.getNickname())!=null){
                        out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head"
                            + " <meta charset='UTF-8'>");
                    out.println("<title>KeepHealthy</title>"
                             + "<meta name='viewport' content='width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1.0, minimm-scale=1.0'>"
                             + "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<table bgcolor='CC6666' align='center'>"
                            + "<tr>"
                            + "<td>"
                            + "<h1>Errores encontrados</h1>");                      
                   
                     out.println("Error : " + "Usuario Existente"+"<br>");
                              
                    out.println("<form action='Registro.html'>"
                            + "<input type='submit' value='Inicio'>"
                            + "</form>"
                            + "</td>"
                            + "</tr>"
                            + "</table>"
                            +"<script src='js/jquery.js'></script>"
                            +"<script src='js/bootstrap.min.js'></script>");
                    out.println("</body>");
                    out.println("</html>");
                }
                else{
                    //nombreUsuario,FechaNacimiento,Ocupacion,FechaUltVez,FechaRegistro,SexoS,ActividadS,PesoS,EstaturaS;
                    String resultadonombreUsuario, resultadoOcupacion,resultadoEstatura,resultadoPeso,
                            resultadoFechaNacimiento;
                    resultadonombreUsuario = validanombreUsuario(nombreUsuario,50,1);                    
                    if (resultadonombreUsuario!="OK")
                            res+=1;
                    resultadoOcupacion = validanombreUsuario(Ocupacion,20,1);
                    if (resultadoOcupacion!="OK")
                            res+=1;
                    resultadoEstatura = validanumerodecimal(EstaturaS,6,1);                    
                    if (resultadoEstatura!="OK")
                            res+=1;
                    resultadoPeso = validanumerodecimal(PesoS,6,1);
                    if (resultadoPeso!="OK")
                            res+=1;
                    
                    if(res==0){
                        Sexo s = new Sexo();
                        s = SexoDao.getSexoByName(SexoS);
                        idSexo = s.getIdSexo();
                        Actividad a = new Actividad();
                        a = ActividadDao.getActividadByName(ActividadS);
                        idActividad = a.getIdActividad();
                        Perfil p = new Perfil();
                        p = PerfilDao.getPerfilByName("Usuario");
                        idPerfil = p.getIdPerfil();
                        pesoUsuario = Float.parseFloat(PesoS);
                        Estatura = Float.parseFloat(EstaturaS);
                        int Edad = 17;
                        //CaloriasDiarias
                        CaloriasDiarias = CalculaHarrisBenedict(pesoUsuario, Estatura, idSexo, idActividad, Edad);
                        //FechaRegistro
                         Calendar fecha = new GregorianCalendar();
                         int año = fecha.get(Calendar.YEAR);
                         int mes = fecha.get(Calendar.MONTH);
                         int dia = fecha.get(Calendar.DAY_OF_MONTH);
                         String años,mess,dias;
                         años = String.valueOf(año);
                         mess = String.valueOf(mes);
                         dias = String.valueOf(dia);
                         if(mess.length()<2){
                             mess = "0" + mess;
                         }
                         if(dias.length()<2){
                             dias = "0" + dias;
                         }
                         FechaRegistro = años + mess + dias; 
                         
                        //FechaNacimiento
                         
                        //FEchaUltVez
                         FechaUltVez = "";
                         
                         u = new Usuario();
                         
                         u.setFechaUltVez(FechaUltVez);
                         u.setIdsexo(idSexo);
                         u.setNickname(Nickname);
                         u.setNombreUsuario(nombreUsuario);
                         u.setOcupacion(Ocupacion);
                         u.setPesoUsuario(pesoUsuario);
                         u.setIdActividad(idActividad);
                         u.setIdperfil(idPerfil);
                         u.setFechaRegistro(FechaRegistro);
                         u.setFechaNacimiento(FechaNacimiento);
                         u.setEstatura(Estatura);
                         u.setContrasena(Contrasena);
                         u.setCaloriasDiarias(CaloriasDiarias);
                         int estatus = UsuarioDao.Guardar(u);
                    }
                 
                        int estatus = UsuarioDao.Guardar(u);
                        HttpSession sesion=request.getSession();
                        if(estatus>0){
                           
                            /* 
                            getRequestDispatcher se encarga de redireccionar un evento establecido por
                            parte del back ened, de modo que el usuario nunca ve el proceso de 
                            dicha accion
                            */
                            u=UsuarioDao.getUsuarioByNombre(usu);
                            if(u.getUsuario()!=null){
                               
                               e.setId_usuario(u.getId_usuario());
                               
                               estatus = EstadoDao.Actualizar(e);  
                               sesion=request.getSession();
                               
                               if (estatus>0){
                                    out.println("<!DOCTYPE html>");
                                    out.println("<html>");
                                    out.println("<head"
                                            + " <meta charset='UTF-8'>");
                                    out.println("<title>Insertar Alumno</title>"
                                             + "<meta name='viewport' content='width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1.0, minimm-scale=1.0'>"
                                             + "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>");            
                                    out.println("</head>");
                                    out.println("<body>");
                                    out.println("<table bgcolor='CC6666' align='center'>"
                                            + "<tr>"
                                            + "<td>"
                                            + "<h1>Secretario reigistrado con exito</h1>");                      

                                     out.println("Se le asigno la ciudad de: "+e.getNombreestado()+"<br>");

                                    out.println("<form action='Registro.html'>"
                                            + "<input type='submit' value='Inicio'>"
                                            + "</form>"
                                            + "</td>"
                                            + "</tr>"
                                            + "</table>"
                                            +"<script src='js/jquery.js'></script>"
                                            +"<script src='js/bootstrap.min.js'></script>");
                                    out.println("</body>");
                                    out.println("</html>");
                               }

                            }
                            else{                               
                                out.println("error actualiza");
                            }
                            
                            
                        }
                        else{                            
                            out.println("No se guardo nada");
                        }
                        out.close();                                                
                    }
                    else{
                            
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head"
                            + " <meta charset='UTF-8'>");
                        out.println("<title>Insertar Alumno</title>"
                             + "<meta name='viewport' content='width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1.0, minimm-scale=1.0'>"
                             + "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<table bgcolor='CC6666' align='center'>"
                            + "<tr>"
                            + "<td>"
                            + "<h1>Errores encontrados</h1>");                      
                   
                        out.println("Error : " + "No hay Ciudades disponibles, debe eliminar un Secretario"+"<br>");
                              
                        out.println("<form action='Registro.html'>"
                            + "<input type='submit' value='Inicio'>"
                            + "</form>"
                            + "</td>"
                            + "</tr>"
                            + "</table>"
                            +"<script src='js/jquery.js'></script>"
                            +"<script src='js/bootstrap.min.js'></script>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    
                }
                    
            }else{
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head"
                            + " <meta charset='UTF-8'>");
                    out.println("<title>Insertar Alumno</title>"
                             + "<meta name='viewport' content='width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1.0, minimm-scale=1.0'>"
                             + "<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\"/>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<table bgcolor='CC6666' align='center'>"
                            + "<tr>"
                            + "<td>"
                            + "<h1>Errores encontrados</h1>");                      
                    if (resultadonombre!="OK"){
                        out.println("Error en campo Nombre: " + resultadonombre+"<br>");
                    }                  
                    if (resultadopassword!="OK"){
                        out.println("Error en campo Password: " + resultadopassword+"<br>");
                    }                 
                    out.println("<form action='Registro.html'>"
                            + "<input type='submit' value='Inicio'>"
                            + "</form>"
                            + "</td>"
                            + "</tr>"
                            + "</table>"
                            +"<script src='js/jquery.js'></script>"
                            +"<script src='js/bootstrap.min.js'></script>");
                    out.println("</body>");
                    out.println("</html>");
                
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(GuardarSecretario.class.getName()).log(Level.SEVERE, null, ex);
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
                            Error = "Solo se permiten letras en este campo";
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
     public String validapass(String Cadena,String Cadena2 ,int maximo, int obligatorio) throws ServletException{
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
                        }else if(Cadena.equals(Cadena2)){
                             Error ="OK"; 
                        }
                        else{
                            Error ="Las contrasñas no coinciden";                    
                        }                                         
                    }    
                }                                                                                          
            }
            
        }catch(Exception e){
            System.out.println("Sólo jugó contigo ¬¬");
        }
         return Error; 
    }
      public String validanumero(String Cadena, int maximo, int obligatorio) throws ServletException{
        String Error="";        
        String checkOK="1234567890";        
        boolean allvalido = true;
        int k;
        int contapuntos=0,pospunto=0;
        char primercaracter=' ',ultimocaracter=' ';
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
                            if(i==0){
                                primercaracter=ch;  
                             }
                            if(i==(Cadena.length()-1)){
                               ultimocaracter=ch; 
                            }
                            if(ch=='.'){
                                  contapuntos++;
                                  pospunto=i;
                              }
                        }
                        if(primercaracter=='.'){
                            Error = "No empieces los numeros decimales con un punto";
                        }else if((pospunto!=1 & primercaracter=='0'& contapuntos>0 )| (primercaracter=='0' & contapuntos==0)){
                            Error = "No empieces los numeros con un 0";
                        }else if (allvalido == false){
                            Error = "Solo se permiten dígitos en este campo ";
                        }else if(contapuntos>1){
                            Error = "Solo se permite un punto decimal";    
                        }else if((contapuntos>0 & Cadena.length()==2)|(ultimocaracter=='.')){
                            Error = "Cuando utilizes punto decimal escribe enteros y decimales";
                        }else if(Double.parseDouble(Cadena)==0){
                             Error = "Ingresa una cantidad mayor a 0";
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
    
    public String validanumerodecimal(String Cadena, int maximo, int obligatorio) throws ServletException{
        String Error="";        
        String checkOK="1234567890.";        
        boolean allvalido = true;
        int k;
        int contapuntos=0,pospunto=0;
        char primercaracter=' ',ultimocaracter=' ';
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
                            if(i==0){
                                primercaracter=ch;  
                             }
                            if(i==(Cadena.length()-1)){
                               ultimocaracter=ch; 
                            }
                            if(ch=='.'){
                                  contapuntos++;
                                  pospunto=i;
                              }
                        }
                        if(primercaracter=='.'){
                            Error = "No empieces los numeros decimales con un punto";
                        }else if((pospunto!=1 & primercaracter=='0'& contapuntos>0 )| (primercaracter=='0' & contapuntos==0)){
                            Error = "No empieces los numeros con un 0";
                        }else if (allvalido == false){
                            Error = "Solo se permiten dígitos en este campo ";
                        }else if(contapuntos>1){
                            Error = "Solo se permite un punto decimal";    
                        }else if((contapuntos>0 & Cadena.length()==2)|(ultimocaracter=='.')){
                            Error = "Cuando utilizes punto decimal escribe enteros y decimales";
                        }else if(Double.parseDouble(Cadena)==0){
                             Error = "Ingresa una cantidad mayor a 0";
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
    public Float CalculaHarrisBenedict(float peso, float estatura, int sexo, int actividad, int Edad) throws ServletException{
        float Calorias=0;
        try{                        
        
            Calorias = 100;
        }catch(Exception e){
            System.out.println("Sólo jugó contigo ¬¬");
        }
         return Calorias; 
    }
}