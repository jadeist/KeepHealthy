package Servlets;

import Data.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CierraRegimenServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String Nickname,idUsuarios, idMenus, noMenus, FechaCreacions,Notas;
            String sBueno,sRegular,sMalo,sPeso;
                        
            java.sql.Date FechaCreacion;
                                    
            Nickname=request.getParameter("Nickname");
           
            Usuario u=new Usuario();
            
            u=UsuarioDao.getUsuarioByNickname(Nickname);
            if(u.getNickname()!= null & u.getEstatus()==1){
                
                //existe el usuario
               Float Estatura = u.getEstatura();
               int Actividad = u.getIdActividad();
               Float peso= u.getPesoUsuario();
               int sexo= u.getIdsexo();               
 
               java.util.Date FechaNacimiento = u.getFechaNacimiento();
               java.sql.Date FechaNac = new java.sql.Date(FechaNacimiento.getTime());
                    
               int Edad= calculaEdad(FechaNac);
               
               
               
               idUsuarios = request.getParameter("idUsuario");
               idMenus=request.getParameter("idMenu");
               noMenus=request.getParameter("noMenu");
               FechaCreacions=request.getParameter("FechaCreacion");
               sBueno=request.getParameter("Bueno");
               sRegular=request.getParameter("Regular");
               sMalo=request.getParameter("Malo");
               sPeso=request.getParameter("Peso");
               Notas=request.getParameter("Notas");

               SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

               java.util.Date dateFechaCreacion = formatter.parse(FechaCreacions);         
               java.util.Date d = new java.util.Date();           
               int dias=(int) ((d.getTime()-dateFechaCreacion.getTime())/86400000);
          
              if (sBueno==null & sRegular==null & sMalo==null){
                out.println("<html>");
                out.println("<head>");
                out.println("<script src='assets/js/sweetalert.min.js'></script>");
                out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                out.println("</head>");
                out.println("<body>");
                out.println("<script type='text/javascript'>");
                out.println("swal({title: 'Lo sentimos :(',text: 'No has seleccionado que te ha parecido el regimen .',type: 'error'},");
                out.println("function () {window.location.href = 'inicio.jsp';});");
                out.println("</script>"); 
                out.println("</body>");
                out.println("</html>");       

              }
              else{
                  //escogio la calificacion          
                  if (dias<7){
                      //no lleva 7 dias con el regimen
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<script src='assets/js/sweetalert.min.js'></script>");
                    out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script type='text/javascript'>");
                    out.println("swal({title: 'Lo sentimos :(',text: 'No has completado los 7 dias del Regimen actual .',type: 'error'},");
                    out.println("function () {window.location.href = 'inicio.jsp';});");
                    out.println("</script>"); 
                    out.println("</body>");
                    out.println("</html>");          
                  }
                  else{
                      //ya cumplio mas de 7 dias con el regimen
                       String resultadopeso, resultadonotas;
                       int res;

                        res = 0;

                        resultadopeso = validanumerodecimal(sPeso,6,1);
                        if (resultadopeso!="OK"){
                                res+=1;
                        }
                        else{
                            Float pesoUsuario = Float.parseFloat(sPeso);
                            if (pesoUsuario< 50){
                                resultadopeso = "El peso capturado no puede ser menor a 50 Kg.";
                                res +=1;
                            }
                            if (pesoUsuario> 150){
                                resultadopeso = "El peso capturado no puede ser mayor a 150 Kg.";
                                res +=1;
                            }   
                        }
                            
                        resultadonotas = validanombre(Notas,150,1);
                        if (resultadonotas!="OK")
                                res+=1; 
                        if(res==0){

                           int idMenu = Integer.parseInt(idMenus);
                           int idUsuario = Integer.parseInt(idUsuarios);
                           int noMenu = Integer.parseInt(noMenus);

                           java.sql.Date datesqlFechaCreacion = new java.sql.Date(dateFechaCreacion.getTime());
                           int califica;

                           if (sBueno!=null)
                               califica=3;
                           else{
                               if(sRegular!=null)
                                  califica=2;
                               else
                                  califica=1; 
                           }

                           HistorialMenu hm = new HistorialMenu();

                           hm.setIdMenu(idMenu);
                           hm.setIdUsuario(idUsuario);
                           hm.setFechaCreacion(datesqlFechaCreacion);
                           hm.setCalificacion(califica);
                           hm.setComentarioHistorial(Notas);


                           int estatus  = HistorialMenuDao.Guardar(hm);

                            HttpSession sesion=request.getSession();
                            if(estatus>0){ 
                                float Peso = Float.parseFloat(sPeso);

                                hm = new HistorialMenu();
                                hm = HistorialMenuDao.getHistorialMenuid(idMenu, idUsuario,datesqlFechaCreacion);
                                int idHistorialMenu = hm.getIdHistorialMenu();
                                java.sql.Date datesqlFechaHistorial = new java.sql.Date(d.getTime());
                                HistorialPeso hp = new HistorialPeso();

                                hp.setFechaHistorial(datesqlFechaHistorial);
                                hp.setIdUsuario(idUsuario);
                                hp.setPesoHistorial(Peso);
                                hp.setIdHistorialMenu(idHistorialMenu);


                                estatus  = HistorialPesoDao.Guardar(hp);

                                sesion=request.getSession();
                                if(estatus>0){ 
                                    //actualizar caloriasDiarias en el usuario por cambio de peso

                                    Float CaloriasDiarias = CalculaHarrisBenedict(Peso, Estatura, sexo, Actividad, Edad);
                                    u.setCaloriasDiarias(CaloriasDiarias);
                                    estatus = UsuarioDao.Actualizar(u);
                                    if (estatus>0){
                                        //borrar de MenuUsuario
                                        MenuUsuario mu = new MenuUsuario();
                                        estatus  = MenuUsuarioDao.Eliminar(idUsuario);
                                        sesion=request.getSession();
                                        if(estatus>0){ 
                                           //todo se dio de alta correctamente y se elimino
                                           out.println("<html>"
                                            +"<head>"
                                            +"<title>KeepHealthy | Menu actualizado </title>"
                                            +"<meta charset='utf-8' />"
                                            +"<meta name='viewport' content='width=device-width, initial-scale=1' />"
                                            +"<link rel='stylesheet' href='assets/css/main.css' />"
                                            +"<link rel='stylesheet' href='assets/css/bootstrap.css'>"
                                            +"</head>"
                                            +"<body class='landing'>"
                                            +"<header id='navmain-header'>"
                                            +"<div class='container'>"
                                            +"<nav class='navbar navbar-default'>"
                                            +"<div class='navbar-header'>"
                                            +"<a href='#' class='js-navmain-nav-toggle navmain-nav-toggle' data-toggle='collapse' data-target='#navbar' aria-expanded='false' aria-controls='navbar'><i></i></a>"
                                            +"<a class='navbar-brand'>KeepHealthy</a>"
                                            +"</div><div id='navbar' class='navbar-collapse collapse'>"
                                            +"</div></nav></div>"
                                            +"</header>"
                                            +"<section id='InicioSesion' class='wrapper style2 special'>"
                                            +"<div class='inner'>"
                                            +"<header class='major narrow'>"
                                            +"<h2>Menu Actualizado con exito</h2>"
                                            +"</header>"
                                            +"<form action='inicio.jsp' method='POST'>"
                                            +"<div class='container 75%'>"
                                            +"<div class='row uniform 50%'>"
                                            +"<div class='6u 12u(xsmall)'>"
                                            +"<input name='Nickname' value='"+Nickname+"' type='hidden' />"
                                            +"</div></div></div>"
                                            +"<ul class='actions'>                                                    "
                                            +"<li><input type='submit' class='special' value='OK' /></li>"
                                            +"</ul></form>"
                                            +"</div>"
                                            +"</section>"
                                            +"<footer id='footer'>"
                                            +"<div class='inner'>"
                                            +"<ul class='icons'>"
                                            +"<li><a href='#' class='icon fa-github'>"
                                            +"<span class='label'>Github</span>"
                                            +"</a></li></ul>"
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
                                            out.println("function () {window.location.href = 'inicio.jsp';});");
                                            out.println("</script>"); 
                                            out.println("</body>");
                                            out.println("</html>");   
                                        }
                                    }
                                    else{
                                        //existio un error al actualizar el usuario
                                        out.println("<html>");
                                        out.println("<head>");
                                        out.println("<script src='assets/js/sweetalert.min.js'></script>");
                                        out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                                        out.println("</head>");
                                        out.println("<body>");
                                        out.println("<script type='text/javascript'>");
                                        out.println("swal({title: 'Error :(',text: 'al actualizar el usuario.',type: 'warning'},");
                                        out.println("function () {window.location.href = 'mimenu.jsp';});");
                                        out.println("</script>"); 
                                        out.println("</body>");
                                        out.println("</html>");   
                                    } //borrar de MenuUsuario
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
                                    out.println("function () {window.location.href = 'inicio.jsp';});");
                                    out.println("</script>"); 
                                    out.println("</body>");
                                    out.println("</html>");                                  
                                }                            
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
                                out.println("function () {window.location.href = 'inicio.jsp';});");
                                out.println("</script>"); 
                                out.println("</body>");
                                out.println("</html>");  
                            }                                           
                        }
                        else{
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<script src='assets/js/sweetalert.min.js'></script>");
                            out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<script type='text/javascript'>");
                            out.println("swal({title:'Ocurrio un error',");

                            if (resultadopeso!="OK"){
                                out.println("text: 'Error en el peso : "+resultadopeso+"',type:'error'},");
                                out.println("function () {window.location.href = 'index.html?#four';});");
                                out.println("</script>"); 
                                out.println("</body>");
                                out.println("</html>");
                            }

                            if (resultadonotas!="OK"){
                                out.println("text:'Error en el campo notas:"+resultadonotas+"',type:'error'},");
                                out.println("function () {window.location.href = 'index.html?#four';});");
                                out.println("</script>"); 
                                out.println("</body>");
                                out.println("</html>");
                            }                                                              
                        }
                  }              
              }
          
          
          
          
            }
            else{
                //no existe el usuario
            }
          
              
        }catch (ParseException ex) {
            Logger.getLogger(CierraRegimenServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CierraRegimenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
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
    public String validanombre(String Cadena, int maximo, int obligatorio) throws ServletException{
        String Error="";        
        String checkOK="abcdefghijklmnñopqrstuvwxyzáéíóúABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ1234567890 .,";        
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
                            Error = "Solo se permiten espacio, punto, coma, letras y numeros en este campo ";
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
}