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


public class ValidaRestriccionesServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String Nickname;
            String Alimento;
            int estatus;
            Nickname=request.getParameter("Nickname");
            
            
            Usuario u=new Usuario();
            u=UsuarioDao.getUsuarioByNickname(Nickname);
            if((u.getNickname())!=null & u.getEstatus()==1){                    
                //usuario existente 
                int idUsuario = u.getIdUsuario();
                //verificar que no haya seleccionado todos los alimentos para restringir
                Alimento a = new Alimento();
                int TotalAlimentos = 0;
                int TotalAlimentosElegidos = 0;
                List<Alimento> lAlimento =  AlimentoDao.getAllAlimentos();
                TotalAlimentos = lAlimento.size();
                String idAlimentos = "";
                for(Alimento lalimento:lAlimento){
                    idAlimentos = String.valueOf(lalimento.getIdAlimento());
                    Alimento = request.getParameter(idAlimentos);
                    if (Alimento!=null){                        
                        //lo eligio y cuento 
                        TotalAlimentosElegidos = TotalAlimentosElegidos +  1;                       
                    }
                    
                } 
                
                if (TotalAlimentosElegidos == TotalAlimentos){
                    //eligio todos los alimentos
                    //no dejo pasar
                    out.println("elegidos todos");
                }
                else{
                    //procedemos a restringir todos los alimentos elegidos
                    
                    //eliminaremos los alimentos restringidos actuales para este usuario
                    int RegisrosBorrados = 0;
                    int TotalaBorrar =0;
                    List<AlimentoRestriccion> lAlimentoRestriccion =  AlimentoRestriccionDao.getAllAlimentoRestriccionByIdUsuario(idUsuario);                                
                    TotalaBorrar = lAlimentoRestriccion.size();
                    
                
                    for(AlimentoRestriccion lalimentorestriccion:lAlimentoRestriccion){ 
                    
                        estatus = AlimentoRestriccionDao.Eliminar(lalimentorestriccion.getIdAlimentoRestriccion());
                        HttpSession sesion=request.getSession();
                        if(estatus>0){ 
                          RegisrosBorrados = RegisrosBorrados + 1;
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
                    //verifico que los que debia borrar se borraron
                    
                    if (TotalaBorrar==RegisrosBorrados){
                    
                        //se eliminan todos los anteriores
                        //vuelvo a recorrer todos los alimentos para verificar los que
                        //se hayan seleccionado para darlos de alta para este usuario
                        int TotalAlimentosElegidosActualizados= 0;
                        
                        for(Alimento lalimento:lAlimento){
                            
                            idAlimentos = String.valueOf(lalimento.getIdAlimento());
                            
                            Alimento = request.getParameter(idAlimentos);
                            if (Alimento!=null){                 
                              
                                //lo eligio
                                //hay que agregarlo en su lista de alimentos restringidos
                                
                                AlimentoRestriccion ar = new AlimentoRestriccion();
                                // le asigno el alimento en la lista y el usuario para dar de alta
                                ar.setIdAlimento(lalimento.getIdAlimento());                                
                                ar.setIdUsuario(idUsuario);

                                estatus = AlimentoRestriccionDao.Guardar(ar);
                                HttpSession sesion=request.getSession();
                                if(estatus>0){ 
                                    //da de alta la restriccion
                                   TotalAlimentosElegidosActualizados = TotalAlimentosElegidosActualizados + 1;                                   
                                }
                                else{
                                    //por algun error no se logro dar de alta 
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

                        } 
                        //verificar que todos los seleccionados se hayan dado de alta
                        // TotalAlimentosElegidos contiene el total de elegidos
                        // TotalAlimentosElegidosActualizados contiene el total de actualizados
                        if (TotalAlimentosElegidos==TotalAlimentosElegidosActualizados){
                            //si coinciden el total de elegidos contra los actualizados
                            //mensaje de actualizados todos
                            out.println("<html><head>" 
                                    +"<title>KeepHealthy | Alimentos Restringidos actualizados </title>" 
                                    +"<meta charset='utf-8'/>" 
                                    +"<meta name='viewport' content='width=device-width, initial-scale=1' />" 
                                    +"<link rel='stylesheet' href='assets/css/main.css\' />" 
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
                                    +"</div></nav></div></header>"
                                    +"<section id='InicioSesion' class='wrapper style2 special'>" 
                                    +"<div class='inner'>" 
                                    +"<header class='major narrow'>" 
                                    +"<h2>Menu Actualizado con exito</h2>" 
                                    +"</header>" 
                                    +"<form action='mimenu.jsp' method='POST'>" 
                                    +"<div class='container 75%'>" 
                                    +"<div class='row uniform 50%'>" 
                                    +"<div class='6u 12u(xsmall)'>" 
                                    +"<input name='Nickname' value='"+Nickname+"' type='hidden' />" 
                                    +"</div></div></div>" 
                                    +"<ul class='actions'>" 
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
                                    +"/body>" 
                                    +"</html>");                                    
                        }
                        else{
                            //no coinciden el total de elegidos contra los actualizados
                            //mensaje de ocurrio un error, verifique
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<script src='assets/js/sweetalert.min.js'></script>");
                            out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<script type='text/javascript'>");
                            out.println("swal({title: 'Lo sentimos :(',text: 'No se han registrado por completo los alimentos, reintente.',type: 'warning'},");
                            out.println("function () {window.location.href = 'inicio.jsp';});");
                            out.println("</script>"); 
                            out.println("</body>");
                            out.println("</html>");
                        }
                    }
                    else{
                        //no se borraron todos por algun error
                    }
 
                }
            }             
            else{
                //No existe el usuario
                 response.sendRedirect("index.html");
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
}
