
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


public class AgregarAlimentoAdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String Nickname;
           
            Nickname=request.getParameter("Nickname");
            String UsuarioElegido=request.getParameter("UsuarioElegido");
            
            Usuario uadmin=new Usuario();

            uadmin=UsuarioDao.getUsuarioByNickname(Nickname);

            if((uadmin.getNickname())!=null & uadmin.getEstatus()==1 & uadmin.getIdperfil()==1){
                
                Usuario ue = new Usuario();
                
                ue = UsuarioDao.getUsuarioByNickname(UsuarioElegido);
                if (ue.getNickname()!=null){
                    //existe el usuario
                    int idUsuario = ue.getIdUsuario();
                    String Nombre = ue.getNombreUsuario();
                    String Nickname2 = ue.getNickname();
                    out.println("<html>" 
                                +"<head>"
                                +"<title>KeepHealthy | Perfil </title>"
                                +"<meta charset='utf-8' />"
                                +"<meta name='viewport' content='width=device-width, initial-scale=1' />"
                                +"<link rel='stylesheet' href='assets/css/main.css' />"
                                +"<link rel='stylesheet' href='assets/css/bootstrap-theme.css'>"
                                +"</head><body class='landing'>"
                                +"<header id='navmain-header'>"
                                +"<div class='container'>"
                                +"<nav class='navbar navbar-default'>"
                                +"<div class='navbar-header'>"
                                +"<a href='#' class='js-navmain-nav-toggle navmain-nav-toggle' data-toggle='collapse' data-target='#navbar' aria-expanded='false' aria-controls='navbar'><i></i></a>"
                                +"<a class='navbar-brand'>KeepHealthy</a>"
                                +"</div>"                            
                                +"<div id='navbar' class='navbar-collapse collapse'>"
                                +"</div></nav></div></header>"                         
                                +"<section id='InicioSesion' class='wrapper style2 special'>"
                                +"<div class='inner'>"
                                +"<header class='major narrow'>"
                                +"<h2>Edicion de Usuario: "+Nickname2+"</h2>"
                                +"</header>");                    
                    Float Estatura = ue.getEstatura();
                    Float Peso = ue.getPesoUsuario();
                    String Ocupacion = ue.getOcupacion();
                    int idActividad = ue.getIdActividad();
                    int idSexo = ue.getIdsexo();
                    String Contrasena= ue.getContrasena();
                    Actividad a = new Actividad();
                    a=ActividadDao.getActividadById(idActividad);
                    String Actividads = a.getDescripcionActividad();

                    Sexo s = new Sexo();
                    s = SexoDao.getSexoById(idSexo);
                    String Sexos = s.getDescripcionSexo();

                    Float PesoAcual;
                    java.sql.Date FechaRegistro=ue.getFechaRegistro(),FechaUltVez=ue.getFechaUltVez(),FechaNacimiento=ue.getFechaNacimiento();
                    Float CaloriasDiarias=ue.getCaloriasDiarias(); 

                    out.println("<form action='ValidaEdicionUsuarioAdminServlet' method='POST'>"
                            +"<meta charset='UTF-8'>"
                            + "<table border='1'>"
                            + "<input name='Nickname' value='"+Nickname+"'  type='hidden' >"
                            + "<input name='idUsuario2' value='"+idUsuario+"'  type='hidden' >"
                            + "<tr><td>Nickname :<input name='Nickname2' value='"+Nickname2+"' type='text' ></td></tr>"
                            + "<tr><td>Nombre :<input name='Nombre' value='"+Nombre+"' type='text'></td></tr>"
                            + "<tr><td>Peso :<input name='Peso' value='"+Peso+"'  type='text'></td></tr>"
                            + "<tr><td>Estatura :<input name='Estatura' value='"+Estatura+"' type='text'></td></tr>"
                            + "<tr><td>Ocupacion :<input name='Ocupacion' value='"+Ocupacion+"' type='text'></td></tr>"
                            + "<tr><td>Contraseña :<input name='Contrasena' value='"+Contrasena+"' type='text'></td></tr>"
                            + "</table>"
                            +"<div class='container 75%'>"
                            +"<div class='row uniform 50%'>"
                            +"<div class='6u 12u(xsmall)'>");

                    out.println("Actividad");
                    if (Actividads.equals("Poca")){
                        out.println(" "
                            +"       <select style='color: gray;' name='ActividadS'>"   
                            +"          <option value='Poca' selected >Poca </option>"
                            +"           <option value='Media'>Media</option>"
                            +"          <option value='Mucha' >Mucha</option>"
                            +"</select>");
                    }
                    else{
                        if (Actividads== "Media"){
                        out.println(" "
                            +"       <select style='color: gray;' name='ActividadS'>"   
                            +"          <option value='Poca'  >Poca</option>"
                            +"           <option value='Media' selected >Media</option>"
                            +"          <option value='Mucha' >Mucha</option>"
                            +"</select>");
                        }
                        else{
                            out.println(" "
                            +"       <select style='color: gray;' name='ActividadS'>"   
                            +"          <option value='Poca'  >Poca </option>"
                            +"           <option value='Media'>Media</option>"
                            +"          <option value='Mucha' selected>Mucha</option>"
                            +"</select>");
                        }
                    }

                    out.println("Sexo");    
                    if (Sexos.equals("Masculino")){
                        out.println(" "
                            +"       <select style='color: gray;' name='SexoS'>"   
                            +"          <option value='Masculino' selected >Masculino</option>"
                            +"           <option value='Femenino'>Femenino</option>"                        
                            +"</select>");
                    }
                    else{
                        out.println(" "
                            +"       <select style='color: gray;' name='SexoS'>"   
                            +"          <option value='Masculino' >Masculino</option>"
                            +"           <option value='Femenino' selected>Femenino</option>"                        
                            +"</select>");                    
                    }

                    out.println(" "                                              
                            +"<div class='6u 12u(xsmall)'>"
                            +"    Fecha de Nacimiento:"
                            +"        <input name='FechaNacimiento' type='date' value='"+ FechaNacimiento +"' min='1990-01-01' max='2010-01-01' style='color: black;'>"
                            +"</div>"
                            +"</div>"
                            +"</div>"
                            +"<ul class='actions'>"
                            +"<li><input type='submit' class='special' value='OK' /></li>"
                            +"</ul>	</form>");
                    out.println("<div class='container 75%'>"
                                +"<div class='row uniform 50%'>"
                                +"<div class='6u 12u(xsmall)'>"
                                //+"<input name='Nickname' value='"+Nickname+"' type='hidden' />"
                                +"</div></div></div>"
                                +"<ul class='actions'>                                                    "
                                //+"<li><input type='submit' class='special' value='OK' /></li>"
                                +"</ul></div>"
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
                    //no existe el usuario
                }
            }
            else{
                response.sendRedirect("index.html");

             }
        } catch (Exception e) {
            out.println("Error");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
