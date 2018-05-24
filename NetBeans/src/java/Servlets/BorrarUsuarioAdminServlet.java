
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


public class BorrarUsuarioAdminServlet extends HttpServlet {

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
                //es administrador
                Usuario ue = new Usuario();
                
                ue = UsuarioDao.getUsuarioByNickname(UsuarioElegido);
                if (ue.getNickname()!=null){
                    //existe el usuario
                    int idUsuario = ue.getIdUsuario();
                    int Estatus = ue.getEstatus();
                    String Nickname2 = ue.getNickname();
                    ue.setEstatus(0);
                    int estatus = UsuarioDao.Actualizar(ue);
                    if (estatus>0){
                        //logra reactivarlo
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
                            +"<h2>Usuario Actualizado "+Nickname2+"</h2>"
                            +"</header>"
                            +"<form action='inicioAdmin.jsp' method='POST'>"
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
                            out.println("swal({title: 'Lo sentimos :(',text: 'No se logro eliminar usuario. intentalo de nuevo.',type: 'warning'},");
                            out.println("function () {window.location.href = 'index.html?#four';});");
                            out.println("</script>"); 
                            out.println("</body>");
                            out.println("</html>");    
                    }                                        
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
