
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


public class RestriccionesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String Nickname;
           
            Nickname=request.getParameter("Nickname");
            
            Usuario u=new Usuario();

            u=UsuarioDao.getUsuarioByNickname(Nickname);

            if((u.getNickname())!=null & u.getEstatus()==1){
                int idUsuario = u.getIdUsuario();
                out.println("<html>" 
                            +"<head>"
                            +"<title>KeepHealthy | Restricciones </title>"
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
                            +"<h2>Restricciones para: "+Nickname+"</h2>"
                            +"</header>"
                            +"<form action='ValidaRestriccionesServlet' method='POST'>"
                            +"<table border='1'>");
                List<CatAlimento> listaCatAlimento =  CatAlimentoDao.getAllCategorias();

                    out.println("<style>"
                    +"/* The container */"
                    +".container {display: block; position: relative; padding-left: 35px; margin-bottom: 12px;"
                    +"            cursor: pointer;font-size: 22px; -webkit-user-select: none; -moz-user-select: none; -ms-user-select: none;"
                    +"            user-select: none;}"
                    +"/* Hide the browser's default checkbox */"
                    +".container input {position: absolute; opacity: 0; cursor: pointer;}"
                    +"/* Create a custom checkbox */"
                    +".checkmark {position: absolute; top: 0; left: 0; height: 25px; width: 25px; background-color: #eee;}"
                    +"/* On mouse-over, add a grey background color */"
                    +".container:hover input ~ .checkmark { background-color: #ccc;}"
                    +"/* When the checkbox is checked, add a blue background */"
                    +".container input:checked ~ .checkmark { background-color: #2196F3;}"                    
                    +"/* Create the checkmark/indicator (hidden when not checked) */"
                    +".checkmark:after { content: ''; position: absolute; display: none;}"
                    +"/* Show the checkmark when checked */"
                    +".container input:checked ~ .checkmark:after { display: block;}"
                    +"/* Style the checkmark/indicator */"
                    +".container .checkmark:after { left: 9px; top: 5px; width: 5px; height: 10px; border: solid white;"
                    +"                              border-width: 0 3px 3px 0; -webkit-transform: rotate(45deg);"
                    +"                              -ms-transform: rotate(45deg); transform: rotate(45deg);}"
                    +"</style>"
                    +"<body>");
                                                
                    out.println("<input type='hidden' name ='Nickname' value="+Nickname+" >");                                                
                    //checked='checked'
                    //los alimentos que ya estan checkeados se colocan asi
                    for(CatAlimento lCatAlimento:listaCatAlimento){
                        out.println("<h1>Alimentos Categoria: "+lCatAlimento.getDescripcionCategoria()+"</h1>");
                        int idCategoria = lCatAlimento.getIdCategoria();
                        List<Alimento> listaAlimento =  AlimentoDao.getAllAlimentosByCategoria(idCategoria);
                        for(Alimento lAlimento:listaAlimento){  
                            int idAlimento = lAlimento.getIdAlimento();
                            AlimentoRestriccion ar = new AlimentoRestriccion();
                            ar = AlimentoRestriccionDao.getAlimentoRestriccionByIdAlimentoUsuario(idAlimento,idUsuario);
                            if (ar.getIdAlimento()!=0){
                                out.println(" "+
                                "<label class='container'>"+ lAlimento.getNombreAlimento() +
                                "  <input type='checkbox' checked='checked' name ='"+ lAlimento.getIdAlimento()+"'>" +
                                "  <span class='checkmark'></span>" +
                                "</label>" );
                            }
                            else{
                                out.println(" "+
                                "<label class='container'>"+ lAlimento.getNombreAlimento() +
                                "  <input type='checkbox' name ='"+ lAlimento.getIdAlimento()+"'>" +
                                "  <span class='checkmark'></span>" +
                                "</label>" );
                            }

                        }                                                    
                    }
                    out.println("</body>"); 
                    out.println("<tr><td></td></tr>"
                                + "</table>"
                                + "<div class='container 75%'>"
                                +"<div class='row uniform 50%'>"
                                +"<div class='6u 12u(xsmall)'>"
                                +"<input name='Nickname' value='"+Nickname+"' type='hidden' />"
                                +"</div></div></div>"
                                +"<ul class='actions'>                                                    "
                                +"<li><input type='submit' class='special' value='OK' /></li>"
                                +"</ul></form></div>"
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
