<%-- 
    Document   : perfil.jsp
    Created on : 7/05/2018, 06:21:35 PM
    Author     : Diego
--%>
<%@page import="Data.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>KeepHealthy | Mi Perfil </title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
	    <link rel="stylesheet" href="assets/css/bootstrap.css">
	    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
	</head>
        <% try{                          
                String Nickname;
                Nickname=request.getParameter("Nickname");                   
                Usuario u=new Usuario();
                u=UsuarioDao.getUsuarioByNickname(Nickname);
                if(u.getNickname()!= null & u.getEstatus()==1 & u.getIdperfil()==1){
                    
                    String Nombre = u.getNombreUsuario();
                   /* Float Estatura = u.getEstatura();
                    Float Peso = u.getPesoUsuario();
                    String Ocupacion = u.getOcupacion();
                    
                    Float PesoAcual;
                    java.sql.Date FechaRegistro=u.getFechaRegistro(),FechaUltVez=u.getFechaUltVez(),FechaNacimiento=u.getFechaNacimiento();
                    Float CaloriasDiarias=u.getCaloriasDiarias();*/
                    out.println("<body class='landing'>"
                        +"<header id='navmain-header'>"
			+"<div class='container'>"
			+"<nav class='navbar navbar-default'>"
		        +"<div class='navbar-header'>"
			+"<a href='#' class='js-navmain-nav-toggle navmain-nav-toggle' data-toggle='collapse' data-target='#navbar' aria-expanded='false' aria-controls='navbar'><i></i></a>"
		        +"<a class='navbar-brand' href='index.html'>KeepHealthy</a>"
		        +"</div>"
		        +"<div id='navbar' class='navbar-collapse collapse'>"
		        +"<ul class='nav navbar-nav navbar-right'>}"
                        +"<li><a href='inicioAdmin.jsp?Nickname="+Nickname+"'><span>Inicio</span></a></li>"
		        +"	<li><a href='CatalogoAlimento.jsp?Nickname="+Nickname+"'><span>Catalogo Alimentos</span></a></li>"
		        +"	<li><a href='perfil.jsp?Nickname="+Nickname+"'><span>Catalogo Menu</span></a></li>");
		            	
                        out.print("	<li><a href='index.html'><span>Cerrar Sesion</span></a></li>"
		        +"</ul>	</div> </nav></div></header>"
                        +"<section class='wrapper style3'></section>");
                    
                     out.println("<section>"
                            +"<div class='row' style='margin: 1em; padding: 1em;'>");
                             out.println("<table border='1'>"
                                     + "<tr>"
                                     + "<td></td>"
                                     + "<th>Nombre de Usuario</th>"
                                     + "<th>Nickname</th>"
                                     + "<td></td>"
                                     + "</tr>");
                             List<Usuario> listaUsuario =  UsuarioDao.getAllUsuarios();
                             for(Usuario lUsuario:listaUsuario){
                                 out.println("<tr>"
                                 + "<td></td>"
                                 + "<td>"+lUsuario.getNombreUsuario()+"</td>"
                                 + "<td>"+lUsuario.getNickname()+"</td>"
                                 + "<form action='EditarUsuarioAdminServlet' method='POST'>"
                                 + "<td>"
                                 + "<input type='hidden' value='"+lUsuario.getNickname()+"' name='UsuarioElegido'>"
                                 + "<input type='hidden' value='"+u.getNickname()+"' name='Nickname'>"
                                 + "<input type='submit' value='editar'>"
                                 + "</td>"
                                 + "</form>");
                                 if (lUsuario.getEstatus()==1){
                                     //se despliega boton de borrar
                                     out.println(" "
                                         + "<form action='BorrarUsuarioAdminServlet' method='POST'>"
                                         + "<td>"
                                         + "<input type='hidden' value='"+lUsuario.getNickname()+"' name='UsuarioElegido'>"
                                         + "<input type='hidden' value='"+u.getNickname()+"' name='Nickname'>"
                                         + "<input type='submit' value='borrar'>"
                                         + "</td>"
                                         + "</form>"  
                                         + "</tr>"
                                         );
                                 }
                                 else{
                                     //se despliega boton de reactivar
                                      out.println(" "
                                         + "<form action='ReactivarUsuarioAdminServlet' method='POST'>"
                                         + "<td>"
                                         + "<input type='hidden' value='"+lUsuario.getNickname()+"' name='UsuarioElegido'>"
                                         + "<input type='hidden' value='"+u.getNickname()+"' name='Nickname'>"
                                         + "<input type='submit' value='reactivar'>"
                                         + "</td>"
                                         + "</form>"  
                                         + "</tr>"
                                         );
                                 }                                 
                         
                             }
                             out.println("</table>"
                             + "</div></div></section>");
			
                    out.println("<footer id='footer'>"
                            +"<div class='inner'>"
                            +"<ul class='icons'>"
                            +"<li><a href='#' class='icon fa-github'>"
                            +"<span class='label'>Github</span>"
                            +"</a></li></ul>"
                            +"<ul class='copyright'>"
                            +"<li>&copy; TYFONWARE 2018.</li>"
                            +"</ul></div></footer>"
                            +"<script src='assets/js/jquery.min.js'></script>"
                            +"<script src='assets/js/skel.min.js'></script>"
                            +"<script src='assets/js/util.js'></script>"
                            +"<script src='assets/js/main.js'></script>"
                            +"<script src='assets/js/bootstrap.min.js'></script>"
                            +"</body></html>");
                }
                else{
                    response.sendRedirect("index.html");
                }
                }catch(Exception e){
                     response.sendRedirect("index.html");
                }
        %>

	
		
