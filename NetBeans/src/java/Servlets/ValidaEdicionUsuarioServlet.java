
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


public class ValidaEdicionUsuarioServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String Nickname;//,Contrasena,Contrasena2,
            String nombreUsuario,FechaNacimiento,Ocupacion,ActividadS,PesoS,EstaturaS;
            int idPerfil;
            int idSexo,idActividad;
            float pesoUsuario,Estatura,CaloriasDiarias;
            
            Nickname=request.getParameter("Nickname");
            //Contrasena=request.getParameter("Contrasena");
            //Contrasena2=request.getParameter("Contrasena2");
            nombreUsuario=request.getParameter("Nombre");
            //SexoS=request.getParameter("SexoS");
            ActividadS=request.getParameter("ActividadS");
            PesoS=request.getParameter("Peso");
            EstaturaS=request.getParameter("Estatura");
            //FechaNacimiento=request.getParameter("FechaNacimiento");
            Ocupacion = request.getParameter("Ocupacion");

            Usuario u=new Usuario();
            
            u=UsuarioDao.getUsuarioByNickname(Nickname);

            if((u.getNickname())==null & u.getEstatus()==1){
              
                out.println("<html>");
                out.println("<head>");
                out.println("<script src='assets/js/sweetalert.min.js'></script>");
                out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                out.println("</head>");
                out.println("<body>");
                out.println("<script type='text/javascript'>");
                out.println("swal({title: 'Lo sentimos :(',text: 'Ocurrio error.',type: 'error'},");
                out.println("function () {window.location.href = 'index.html?#four';});");
                out.println("</script>"); 
                out.println("</body>");
                out.println("</html>");                    
            }
            else{
                
                int res=0;
                //nombreUsuario,FechaNacimiento,Ocupacion,FechaUltVez,FechaRegistro,SexoS,ActividadS,PesoS,EstaturaS;
                String resultadonombreUsuario, resultadoOcupacion,resultadoEstatura,resultadoPeso;

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
                pesoUsuario = 0;
                Estatura = 0;
                if (resultadoPeso!="OK")
                        res+=1;
                if (res==0){
                    pesoUsuario = Float.parseFloat(PesoS);
                    if (pesoUsuario< 50){
                        resultadoPeso = "El peso capturado no puede ser menor a 50 Kg.";
                        res +=1;
                    }
                     if (pesoUsuario> 150){
                        resultadoPeso = "El peso capturado no puede ser mayor a 150 Kg.";
                        res +=1;
                    }
                    Estatura = Float.parseFloat(EstaturaS);
                    if (Estatura< 120){
                        resultadoEstatura = "La estatura capturada no puede ser menor a 120 cm.";
                        res +=1;
                    }
                     if (Estatura> 200){
                        resultadoEstatura = "La estatura capturada no puede ser mayor a 200 cm.";
                        res +=1;
                    }
                }
                if(res==0){

//                    Sexo s = new Sexo();
//                    s = SexoDao.getSexoByName(SexoS);
//                    idSexo = s.getIdSexo();

                    Actividad a = new Actividad();
                    a = ActividadDao.getActividadByName(ActividadS);
                    idActividad = a.getIdActividad();

//                    Perfil p = new Perfil();
//                    p = PerfilDao.getPerfilByName("Usuario");
//                    idPerfil = p.getIdPerfil();

                     //FechaNacimiento                                                    
                    //out.println("id FechaUltVez: " +FechaNacimiento);
                    
                    //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    
                    java.sql.Date datesqlFechaNacimiento = u.getFechaNacimiento();

                    int Edad= calculaEdad(datesqlFechaNacimiento);
                    idSexo = u.getIdsexo();
                    
                    //CaloriasDiarias
                    CaloriasDiarias = CalculaHarrisBenedict(pesoUsuario, Estatura, idSexo, idActividad, Edad);
//                        out.println("id CaloriasDiarias: " +CaloriasDiarias);
                    //FechaRegistro                  
                    java.util.Date d = new java.util.Date();  
                    java.sql.Date date2 = new java.sql.Date(d.getTime());

                    //u = new Usuario();

                    //u.setNickname(Nickname);
                   // u.setContrasena(Contrasena);
                   // u.setIdperfil(idPerfil);                        
                    u.setNombreUsuario(nombreUsuario);                        
                    //u.setIdsexo(idSexo);    
                    //u.setFechaNacimiento(datesqlFechaNacimiento);                                              
                    u.setPesoUsuario(pesoUsuario);
                    u.setEstatura(Estatura);
                    u.setOcupacion(Ocupacion);
                    u.setIdActividad(idActividad);
                    //u.setFechaRegistro(date2);                                        
                    //u.setFechaUltVez(date2);                        
                    u.setCaloriasDiarias(CaloriasDiarias);

                    int estatus = UsuarioDao.Actualizar(u);
                    HttpSession sesion=request.getSession();
                    if(estatus>0){
/* 
                        getRequestDispatcher se encarga de redireccionar un evento establecido por
                        parte del back ened, de modo que el usuario nunca ve el proceso de 
                        dicha accion
                        */
                    out.println("<html>"
                        +"<head>"
                        +"<title>KeepHealthy | Edicion Usuario </title>"
                        +"<meta charset='utf-8' />"
                        +"<meta name='viewport' content='width=device-width, initial-scale=1' />"
                        +"<link rel='stylesheet' href='assets/css/main.css' />"
                        +"<link rel='stylesheet' href='assets/css/bootstrap.css'>"
                        +"</head><body class='landing'>"
                        +"<header id='navmain-header'>"
                        +"<div class='container'>"
                        +"<nav class='navbar navbar-default'>"
                        +"<div class='navbar-header'>"
                        +"<a href='#' class='js-navmain-nav-toggle navmain-nav-toggle' data-toggle='collapse' data-target='#navbar' aria-expanded='false' aria-controls='navbar'><i></i></a>"
                        +"<a class='navbar-brand'>KeepHealthy</a>"
                        +"</div><div id='navbar' class='navbar-collapse collapse'>"
                        +"</div></nav></div></header>"
                        +"<section id='InicioSesion' class='wrapper style2 special'>"
                        +"<div class='inner'>"
                        +"<header class='major narrow'>"
                        +"<h2>Usuario Actualizado "+Nickname+"</h2>"
                        +"</header>"
                        +"<form action='perfil.jsp' method='POST'>"
                        +"<div class='container 75%'>"
                        +"<div class='row uniform 50%'>"
                        +"<div class='6u 12u(xsmall)'>"
                        +"<input name='Nickname' value='"+Nickname+"' type='hidden' />"
                        +"</div></div></div>"
                        + "<ul class='actions'>"
                        +"<li><input type='submit' class='special' value='OK' /></li>"
                        +"</ul></form></div>"
                        +"</section>"
                        +"<footer id='footer'>"
                        +"<div class='inner'>"
                        +"<ul class='icons'>"
                        +"<li><a href='#' class='icon fa-github'>"
                        +"<span class='label'>Github</span>"
                        +"</a></li>"
                        +"</ul>"
                        +"<ul class='copyright'>"
                        +"<li>&copy; TYFONWARE 2018.</li>"
                        +"<li style='text-transform: lowercase'>Imagenes de <a href='http://unsplash.com'>Unsplash</a>.</li>"
                        +"</ul></div></footer>"
                        +"<script src='assets/js/jquery.min.js'></script>"
                        +"<script src='assets/js/skel.min.js'></script>"
                        +"<script src='assets/js/util.js'></script>"
                        +"<script src='assets/js/main.js'></script>"
                        +"<script src='assets/js/bootstrap.min.js'></script>"
                        +"</body>"
                        +"</html>");
                    }
                    else{
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<script src='assets/js/sweetalert.min.js'></script>");
                        out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<script type='text/javascript'>");
                        out.println("swal({title: 'Lo sentimos :(',text: 'Has excedido el tiempo limite de registro, intentalo de nuevo.',type: 'warning'},");
                        out.println("function () {window.location.href = 'index.html?#four';});");
                        out.println("</script>"); 
                        out.println("</body>");
                        out.println("</html>");    
                    }
                    out.close();                                                
                }
                else{  //SEGUNDOS ERRORES

                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script src='assets/js/sweetalert.min.js'></script>");
                    out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                    out.println("</head>");
                    out.println("<body>");                                                
                    if (resultadonombreUsuario!="OK"){
                        out.println("<script type='text/javascript'>");
                        out.println("swal({title:'Ocurrio un error',");
                        out.println("text: 'Error en el nombre de usuario: "+resultadonombreUsuario+"',type:'error'},");
                        out.println("function () {window.location.href = 'index.html?#four';});");
                        out.println("</script>"); 
                        out.println("</body>");
                        out.println("</html>");
                    }
                    else{
                        if (resultadoOcupacion!="OK"){
                            out.println("<script type='text/javascript'>");
                            out.println("swal({title:'Ocurrio un error',");                         
                            out.println("text:'Error en la ocupacion del usuario:"+resultadoOcupacion+"',type:'error'},");
                            out.println("function () {window.location.href = 'index.html?#four';});");
                            out.println("</script>"); 
                            out.println("</body>");
                            out.println("</html>");
                        }
                        else{
                            if (resultadoEstatura!="OK"){
                                out.println("<script type='text/javascript'>");
                                out.println("swal({title:'Ocurrio un error',");                         
                                out.println("text:'Error en la estatura del usuario: "+resultadoEstatura+"',type:'error'},");
                                out.println("function () {window.location.href = 'index.html?#four';});");
                                out.println("</script>"); 
                                out.println("</body>");
                                out.println("</html>");
                            }
                            else{
                                if (resultadoPeso!="OK"){
                                    out.println("<script type='text/javascript'>");
                                out.println("swal({title:'Ocurrio un error',");  
                                    out.println("text:'Error en el peso del usuario: "+resultadoPeso+"',type:'error'},");
                                    out.println("function () {window.location.href = 'index.html?#four';});");
                                    out.println("</script>"); 
                                    out.println("</body>");
                                    out.println("</html>"); 
                                }
                            }
                        }
                    }
                }
            }
                    
        } catch (SQLException ex) {
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
    public int calculaEdad(java.util.Date  fecha2){
      
   
        java.util.Date d = new java.util.Date();  
        
        SimpleDateFormat sdfy = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdfd = new SimpleDateFormat("dd");
        SimpleDateFormat sdfM = new SimpleDateFormat("MM");
        String ahora_anio = sdfy.format(d);
        String ahora_mes = sdfM.format(d);
        String ahora_dia = sdfd.format(d);
        
        String anio = sdfy.format(fecha2);
        String mes = sdfM.format(fecha2);
        String dia = sdfd.format(fecha2);
        int ianio = Integer.parseInt(anio);
        int imes = Integer.parseInt(mes);
        int idia = Integer.parseInt(dia);
        int iahora_anio = Integer.parseInt(ahora_anio);
        int iahora_mes = Integer.parseInt(ahora_mes);
        int iahora_dia = Integer.parseInt(ahora_dia);
        
        int edad = (iahora_anio + 1900) - ianio;    	
        if ( iahora_mes < imes )
            edad--;        
        if ((imes == iahora_mes) && (iahora_dia < idia))
            edad--;       
        if (edad > 1900)
            edad -= 1900;
        int  meses=0;
        
        if(iahora_mes>imes)
            meses=iahora_mes-imes;
        if(iahora_mes<imes)
            meses=12-(imes-iahora_mes);
        
        if(idia>iahora_dia)  //((iahora_mes==imes) && (idia>iahora_dia))
            meses=11;
 	int dias=0;
        if(iahora_dia>idia)
           dias=iahora_dia-idia;
        if(iahora_dia<idia){           
            //java.util.Date ultimoDiaMes=new java.util.Date(iahora_anio, iahora_mes, 0);
           // dias=ultimoDiaMes.getDate()-(idia-iahora_dia);
        }
    //document.registro.totaledad.value = "Tienes "+edad+" años, "+meses+" meses y "+dias+" días";
        return edad;
    }
           	
        
 
    public Float CalculaHarrisBenedict(float peso, float estatura, int sexo, int actividad, int Edad) throws ServletException{
        float Calorias=0;
        try{                        
            if (sexo==2){
                //es Mujer
                Calorias =((10 * peso) + (6.25f * estatura) -(5f * Edad) + 5f);
            }
            else{
                //es Hombre
                Calorias =((10 * peso) + (6.25f * estatura) -(5f * Edad) - 161f);
            }
            //1 poca
            //2 mediana
            //3 mucha
            if (actividad == 1){
                Calorias = Calorias * 1.2f;
            }
            else{
                if (actividad == 2){
                    Calorias = Calorias * 1.55f;
                }
                else{
                    Calorias = Calorias * 1.9f;
                }
            }
        }catch(Exception e){
            System.out.println("Sólo jugó contigo ¬¬");
        }
         return Calorias; 
    }
}
