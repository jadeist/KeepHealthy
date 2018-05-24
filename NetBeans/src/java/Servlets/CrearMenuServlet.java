
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


public class CrearMenuServlet extends HttpServlet {

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
                //usuario existente                               
                //revisar si el usuario no tiene menu asignado
              
                int idUsuario = u.getIdUsuario();
                
                MenuUsuario mu = new MenuUsuario();
                mu = MenuUsuarioDao.getMenuUsuarioByIdUsuario(idUsuario);
                
                if (mu.getIdMenu()==0){
                    //no cuenta con menu asignado.
                    //muestro la pantalla
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
                            +"<h2>Creando el Menu para: "+Nickname+"</h2>"
                            +"</header>"
                            +"<form action='ValidaMenuServlet' method='POST'>"
                            +"<table border='1'>");
                
                    MenuUsuarioCrea muc = new MenuUsuarioCrea();
                    muc = MenuUsuarioCreaDao.getMenuUsuarioCreaByIdUsuario(idUsuario);
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
                    if (muc.getIdUsuario()==0){
                        //no existe menu nuevo se muestran solo los check                                                                                                                                    
                        //solo muestro los alimentos posibles sin chequear
                        //mostrare por etapa menu
                        List<EtapaMenu> listaEtapaMenu =  EtapaMenuDao.getAll();
                        for(EtapaMenu lEtapaMenu:listaEtapaMenu){                        
                            out.println("<h1>Alimentos para : "+lEtapaMenu.getDescripcionEtapa()+"</h1>");                                                
                            int idEtapaMenu = lEtapaMenu.getIdEtapamenu();                       
                            List<GrupoEtapaCatAlimento> listaGrupoEtapaCatAlimento = GrupoEtapaCatAlimentoDao.getAllGrupoEtapaCatAlimentobyidEtapaMenu(idEtapaMenu);
                            for(GrupoEtapaCatAlimento lGrupoEtapaCatAlimento:listaGrupoEtapaCatAlimento){  
                                int idCategoria = lGrupoEtapaCatAlimento.getIdCategoria();                                
                                CatAlimento ca = new CatAlimento();
                                ca = CatAlimentoDao.getCatAlimentoByIdCategoria(idCategoria);                               
                                out.println("<a1>" +ca.getDescripcionCategoria()+"</a1>");
                                List<Alimento> listaAlimento =  AlimentoDao.getAllAlimentosByCategoria(idCategoria);
                                for(Alimento lAlimento:listaAlimento){                                       
                                    int idAlimento = lAlimento.getIdAlimento();    
                                    String nombre = lAlimento.getNombreAlimento();
                                    String porcion = lAlimento.getPorcion();
                                    
                                    out.println(" "+
                                        "<label class='container'>"+ nombre + " " + porcion +
                                        "  <input type='checkbox' name ='"+ String.valueOf(idCategoria)+String.valueOf(idAlimento)+"'>" +
                                        "  <span class='checkmark'></span>" +
                                        "</label>" );                                
                                } //alimentos
                            }  //etapamenu categorias                                                                                                  
                        } //etapamenu
                    }
                    else{
                            //hay datos en la tabla para desplegar.                                                                
                            //solo muestro los alimentos posibles sin chequear
                            //mostrare por etapa menu
                            int Alimento11= muc.getIdAlimento11();
                            int Alimento12= muc.getIdAlimento12();
                            int Alimento13= muc.getIdAlimento13();
                            int Alimento14= muc.getIdAlimento14();
                            int Alimento15= muc.getIdAlimento15();
                            int Alimento16= muc.getIdAlimento16();
                            int Alimento17= muc.getIdAlimento17();
                            int Alimento18= muc.getIdAlimento18();
                            int Alimento19= muc.getIdAlimento19();
                            
                            int Alimento21= muc.getIdAlimento21();
                            int Alimento22= muc.getIdAlimento22();
                            int Alimento23= muc.getIdAlimento23();
                            int Alimento24= muc.getIdAlimento24();
                            int Alimento25= muc.getIdAlimento25();
                            int Alimento26= muc.getIdAlimento26();
                            int Alimento27= muc.getIdAlimento27();
                            int Alimento28= muc.getIdAlimento28();
                            int Alimento29= muc.getIdAlimento29();
                            
                            int Alimento31= muc.getIdAlimento31();
                            int Alimento32= muc.getIdAlimento32();
                            int Alimento33= muc.getIdAlimento33();
                            int Alimento34= muc.getIdAlimento34();
                            int Alimento35= muc.getIdAlimento35();
                            int Alimento36= muc.getIdAlimento36();
                            int Alimento37= muc.getIdAlimento37();
                            int Alimento38= muc.getIdAlimento38();
                            int Alimento39= muc.getIdAlimento39();
                            
                            int Alimento41= muc.getIdAlimento41();
                            int Alimento42= muc.getIdAlimento42();
                            int Alimento43= muc.getIdAlimento43();
                            int Alimento44= muc.getIdAlimento44();
                            int Alimento45= muc.getIdAlimento45();
                            int Alimento46= muc.getIdAlimento46();
                            int Alimento47= muc.getIdAlimento47();
                            int Alimento48= muc.getIdAlimento48();
                            int Alimento49= muc.getIdAlimento49();
                            
                            int Alimento51= muc.getIdAlimento51();
                            int Alimento52= muc.getIdAlimento52();
                            int Alimento53= muc.getIdAlimento53();
                            int Alimento54= muc.getIdAlimento54();
                            int Alimento55= muc.getIdAlimento55();
                            int Alimento56= muc.getIdAlimento56();
                            int Alimento57= muc.getIdAlimento57();
                            int Alimento58= muc.getIdAlimento58();
                            int Alimento59= muc.getIdAlimento59();
                            
                            List<EtapaMenu> listaEtapaMenu =  EtapaMenuDao.getAll();
                            for(EtapaMenu lEtapaMenu:listaEtapaMenu){                        
                                out.println("<h1>Alimentos para : "+lEtapaMenu.getDescripcionEtapa()+"</h1>");                                                
                                int idEtapaMenu = lEtapaMenu.getIdEtapamenu();                       
                                List<GrupoEtapaCatAlimento> listaGrupoEtapaCatAlimento = GrupoEtapaCatAlimentoDao.getAllGrupoEtapaCatAlimentobyidEtapaMenu(idEtapaMenu);
                                for(GrupoEtapaCatAlimento lGrupoEtapaCatAlimento:listaGrupoEtapaCatAlimento){  
                                    int idCategoria = lGrupoEtapaCatAlimento.getIdCategoria();
                                    CatAlimento ca = new CatAlimento();
                                    ca = CatAlimentoDao.getCatAlimentoByIdCategoria(idCategoria);                               
                                    out.println("<a1>" +ca.getDescripcionCategoria()+"</a1>");
                                    List<Alimento> listaAlimento =  AlimentoDao.getAllAlimentosByCategoria(idCategoria);
                                    for(Alimento lAlimento:listaAlimento){                                  
                                        int idAlimento = lAlimento.getIdAlimento(); 
                                        String nombre = lAlimento.getNombreAlimento();
                                        String porcion = lAlimento.getPorcion();                                        
                                        //buscar si el alimento esta restringido para esta usuario
                                        AlimentoRestriccion ar = new AlimentoRestriccion();
                                        ar = AlimentoRestriccionDao.getAlimentoRestriccionByIdAlimentoUsuario(idAlimento,idUsuario);
                                        if (ar.getIdAlimento()==0){
                                            //no es un alimento restingido
                                            //buscar si ya lo habia elegido 
                                          switch ( idEtapaMenu ) {
                                          case 1:                                                                                                  
                                                if ((idAlimento==Alimento11) | (idAlimento==Alimento12) |
                                                    (idAlimento==Alimento13) | (idAlimento==Alimento14) |
                                                    (idAlimento==Alimento15) | (idAlimento==Alimento16) |
                                                    (idAlimento==Alimento17) | (idAlimento==Alimento18) |
                                                    (idAlimento==Alimento19)){                                                    
                                                    out.println(" "+
                                                    "<label class='container'>"+  nombre + " " + porcion +
                                                    "  <input type='checkbox' checked='checked' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                    "  <span class='checkmark'></span>" +
                                                    "</label>" );
                                                }
                                                else{
                                                    out.println(" "+
                                                        "<label class='container'>"+ nombre + " " + porcion + 
                                                        "  <input type='checkbox' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                        "  <span class='checkmark'></span>" +
                                                        "</label>" );
                                                }
                                               break;                                          
                                          case 2:                                                                                             
                                                if ((idAlimento==Alimento21) | (idAlimento==Alimento22) |
                                                    (idAlimento==Alimento23) | (idAlimento==Alimento24) |
                                                    (idAlimento==Alimento25) | (idAlimento==Alimento26) |
                                                    (idAlimento==Alimento27) | (idAlimento==Alimento28) |
                                                    (idAlimento==Alimento29)){
                                                    out.println(" "+
                                                    "<label class='container'>"+ nombre + " " + porcion + 
                                                    "  <input type='checkbox' checked='checked' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                    "  <span class='checkmark'></span>" +
                                                    "</label>" );
                                                }
                                                else{
                                                    out.println(" "+
                                                        "<label class='container'>"+ nombre + " " + porcion + 
                                                        "  <input type='checkbox' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                        "  <span class='checkmark'></span>" +
                                                        "</label>" );
                                                }                                                 
                                           break;
                                          case 3:
                                                if ((idAlimento==Alimento31) | (idAlimento==Alimento32) |
                                                    (idAlimento==Alimento33) | (idAlimento==Alimento34) |
                                                    (idAlimento==Alimento35) | (idAlimento==Alimento36) |
                                                    (idAlimento==Alimento37) | (idAlimento==Alimento38) |
                                                    (idAlimento==Alimento39)){
                                                    out.println(" "+
                                                    "<label class='container'>"+ nombre + " " + porcion + 
                                                    "  <input type='checkbox' checked='checked' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                    "  <span class='checkmark'></span>" +
                                                    "</label>" );
                                                }
                                                else{
                                                    out.println(" "+
                                                        "<label class='container'>"+ nombre + " " + porcion + 
                                                        "  <input type='checkbox' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                        "  <span class='checkmark'></span>" +
                                                        "</label>" );
                                                }   
                                               break;
                                          case 4:
                                               if ((idAlimento==Alimento41) | (idAlimento==Alimento42) |
                                                    (idAlimento==Alimento43) | (idAlimento==Alimento44) |
                                                    (idAlimento==Alimento45) | (idAlimento==Alimento46) |
                                                    (idAlimento==Alimento47) | (idAlimento==Alimento48) |
                                                    (idAlimento==Alimento49)){
                                                    out.println(" "+
                                                    "<label class='container'>"+ nombre + " " + porcion + 
                                                    "  <input type='checkbox' checked='checked' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                    "  <span class='checkmark'></span>" +
                                                    "</label>" );
                                                }
                                                else{
                                                    out.println(" "+
                                                        "<label class='container'>"+ nombre + " " + porcion + 
                                                        "  <input type='checkbox' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                        "  <span class='checkmark'></span>" +
                                                        "</label>" );
                                                }
                                               break;
                                          case 5:
                                               if ((idAlimento==Alimento51) | (idAlimento==Alimento52) |
                                                    (idAlimento==Alimento53) | (idAlimento==Alimento54) |
                                                    (idAlimento==Alimento55) | (idAlimento==Alimento56) |
                                                    (idAlimento==Alimento57) | (idAlimento==Alimento58) |
                                                    (idAlimento==Alimento59)){
                                                    out.println(" "+
                                                    "<label class='container'>"+ nombre + " " + porcion + 
                                                    "  <input type='checkbox' checked='checked' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                    "  <span class='checkmark'></span>" +
                                                    "</label>" );
                                                }
                                                else{
                                                    out.println(" "+
                                                        "<label class='container'>"+ nombre + " " + porcion + 
                                                        "  <input type='checkbox' name ='"+ String.valueOf(idEtapaMenu)+String.valueOf(idAlimento)+"'>" +
                                                        "  <span class='checkmark'></span>" +
                                                        "</label>" );
                                                }
                                               break;
                                          default:                                               
                                               break;
                                          }
                                        }                                                                                                                                                                     
                                    } //alimentos
                                }  //etapamenu categorias                                                                                                  
                            } //etapamenu
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
                  //error este usuario ya cuenta con un menu asignado
                  //arreglar  
                    out.println("<html>"
                        +"head>"
                        +"<title>KeepHealthy | Menu asignado </title>"
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
