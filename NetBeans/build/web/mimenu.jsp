<%-- 
    Document   : mimenu
    Created on : 7/05/2018, 06:20:38 PM
    Author     : Diego
--%>
<%@page import="java.util.List"%>
<%@page import="Data.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>

	<head>
		<title>KeepHealthy | Mi Menu </title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
	    <link rel="stylesheet" href="assets/css/bootstrap.css">
	</head>

	<body class="landing">
           
		

		<section class="wrapper style4"></section>
                <% try{                          
                        String Nickname;
                        Nickname=request.getParameter("Nickname");
                       
                    
                        out.println("<header id='navmain-header'>"
                                +"<div class='container'>"
                                +"<nav class='navbar navbar-default'>"
		        	+"<div class='navbar-header'>"
				+"<a href='#' class='js-navmain-nav-toggle navmain-nav-toggle' data-toggle='collapse' data-target='#navbar' aria-expanded='false' aria-controls='navbar'><i></i></a>"
		         	+"<a class='navbar-brand' href='index.html'>KeepHealthy</a>"
		        	+"</div>"
		        	+"<div id='navbar' class='navbar-collapse collapse'>"
		          	+"<ul class='nav navbar-nav navbar-right'>"
                                +"	<li><a href='inicio.jsp?Nickname="+Nickname+"'><span>Inicio</span></a></li>"
		            	+"	<li class='active'><a href='mimenu.jsp?Nickname="+Nickname+"'><span>Mi Menu</span></a></li>"
		            	+"	<li><a href='perfil.jsp?Nickname="+Nickname+"'><span>Perfil</span></a></li>"
		            	+"	<li><a href='index.html'><span>Cerrar Sesion</span></a></li>"
		            	//+"<li><a href='inicio.jsp?Nickname="+Nickname+"><span>Inicio</span></a></li>"
		            	//+"<li class='active'><a href='mimenu.jsp?Nickname="+Nickname+"><span>Mi Menu</span></a></li>"
		            	//+"<li><a href='perfil.jsp'><span>Perfil</span></a></li>"
                    		//+"<li><a href='index.html' ><span>Cerrar Sesion</span></a></li>"
		          	+"</ul>"
		        	+"</div>"
                                +"</nav>"
                                +"</div>"
                                +"</header>");
                        
                        Usuario u=new Usuario();
                        u=UsuarioDao.getUsuarioByNickname(Nickname);
                        if(u.getNickname()!= null){
                          //buscar si el usuairo tiene menu asignado en tabla MenuUsuario
                          out.println(" <section>"
                                        +"<div class='row' style='margin: 1em; padding: 2em;'>"
                                        +"<div class='6u 12u$(xsmall)'>"
					+"<div class='content'>");
                          
                          
                            MenuUsuario mu= new MenuUsuario();
                            int idUsuario = u.getIdUsuario();
                            mu=MenuUsuarioDao.getMenuUsuarioByIdUsuario(idUsuario);
                            if(mu.getIdMenu()== 0){
                            //no tiene menu asignado para la semana
                                out.println("<h3>Menu Actual (No tiene asignado ningun menu actualmente)</h3>"
                                +"<div class='table-wrapper'>"
                                +"<table class='alt'>"
				+"<thead>"
				+"<tr>"
				+"<th>Comida</th>"
				+"<th>Descripcion</th>"
				+"<th>Hora</th>"
				+"</tr>"
				+"</thead><tbody>");                                
                                out.println("</tbody>"
                                    +"</table>"
                                    +"</div>"
                                    +"</div>"
                                    +"</div>");
                       
                            }                            
                            else{
                                //si tiene menu de esta semana
                                out.println("<h3>Menu Actual </h3>"
                                +"<div class='table-wrapper'>"
                                +"<table class='alt'>"
				+"<thead>"
				+"<tr>"
				+"<th>Comida</th>"
				+"<th>Descripcion</th>"
				+"<th>Hora</th>"
				+"</tr>"
				+"</thead><tbody>");
                             
                                java.sql.Date FechaCreacion=mu.getFechaCreacion();
                                //encuentra menu asignado y se muestra                                                                                                
                                int idMenu = mu.getIdMenu();
                                Menu m = new Menu();
                                m = MenuDao.getMenuById(idMenu);
                                int noMenu = m.getNoMenu();
                          
                                //obtengo todas las etapas del menu
                                List<EtapaMenu> listaEtapaMenu =  EtapaMenuDao.getAll();                          
                                for(EtapaMenu letapamenu:listaEtapaMenu){
                                    //por cada etapa muestro los que tenga en grupoEtapa
                                    int idEtapaMenu = letapamenu.getIdEtapamenu();
                                    out.println("<tr>"
                                           +"<td>"+letapamenu.getDescripcionEtapa()+"</td>"
                                           +"<td>   </td>"
                                           +"<td>"+letapamenu.getHoraEtapa()+"</td>"
                                           + "</tr>" );
                                    List<GrupoEtapa> listaGrupoEtapa =  GrupoEtapaDao.getAllGrupoEtapabyNoMenu(noMenu,idEtapaMenu);                                                                                   
                                    out.println("<tr><td>   </td>"
                                                +"<td>"
                                                +"<ul>");
                                    for(GrupoEtapa lgrupoetapa:listaGrupoEtapa){     
                                        Alimento a = new Alimento();
                                        a = AlimentoDao.getAlientoById(lgrupoetapa.getIdAlimento());                                   
                                        out.println("<li><b>"+a.getNombreAlimento()+":</b>"+a.getPorcion()+".</li>");                                   
                                    }                              
                                    out.println("</ul>"
                                           +"</td>"
                                           +"<td>  </td>"
                                           +"</tr>");                                   
                                }    
                                out.println("</tbody>"
                                    +"</table>"
                                    +"</div>"
                                    +"</div>"
                                    +"</div>");			
                            }
                            //aqui se deben desplegar los alimentos restriccion por este usuario
                            
                            List<AlimentoRestriccion> listaAlimentoRestriccion =  AlimentoRestriccionDao.getAllAlimentoRestriccionByIdUsuario(idUsuario);
                            
                            out.println("<div class='6u$ 12u$(xsmall)'>"
                                        +"<div style='margin: 1em; padding: 2em;'>"
                                        + "<form action='RestriccionesServlet' method='POST'>"
                                        + "<input type='hidden' value='"+Nickname+"' name='Nickname'>"
                                        + "<input type='submit'  class='special' value='Editar Alimentos Restringidos'>"
                                        + "</form>"
                                        +"<hr />"
                                        +"<div class='wrapper style3' style='margin: 1em; padding: 1em;'>"
                                        +"<h4> Alimentos Excluidos:</h4>"
                                        +"<hr/>"
                                        +"<ul class='alt'>");
                            for(AlimentoRestriccion lalimentorestriccion:listaAlimentoRestriccion){                                                            
                                int idAlimento = lalimentorestriccion.getIdAlimento();                                
                                Alimento a = new Alimento();
                                a = AlimentoDao.getAlientoById(idAlimento);                                
                                out.println(" "
                                        +"<li><b>" + a.getNombreAlimento()+"</b></li>");                                                                               
                            }
                            out.println("</ul>"
                                        +"</div>"
                                        +"</div>"
                                        +"</div></section>	"); 
                                                        
                            //desplegar los menus historicos de este usuario.
                            out.println("<section>"
                                        +"<div class='wrapper style4' style='overflow-y:scroll;, margin: 1em; padding: 2em;'>"
                                        +"<h3>Historial de Menus (Regimenes)</h3>"
                                        +"<div class='list-group'>"
                                        +"<a class='list-group-item' style='background-color: #69b294;'>");
                                                                                       
                            List<HistorialMenu> listaHistorialMenu =  HistorialMenuDao.getAllHistorialMenuByIdUsuario(idUsuario);                                
                            for(HistorialMenu lhistorialmenu:listaHistorialMenu){

                                int HidMenu= lhistorialmenu.getIdMenu();
                                Menu hm = new Menu();
                                hm = MenuDao.getMenuById(HidMenu);
                                int HnoMenu = hm.getNoMenu();

                                java.sql.Date HFechaCreacion=lhistorialmenu.getFechaCreacion(); 
                                out.println("<h4 class='list-group-item-heading'>Menu Semana: " + HFechaCreacion +"</h4>"
                                            +"<p class='list-group-item-text'>"
                                            +"<div class='table-wrapper'>"
                                            +"<table class='alt'>"
                                            +"<thead>"
                                            +"<tr>"
                                            +"<th>Comida</th>"
                                            +"<th>Descripcion</th>"
                                            +"<th>Hora</th>"
                                            +"</tr>"
                                            +"</thead>"
                                            +"<tbody>");

                                List<EtapaMenu> HlistaEtapaMenu =  EtapaMenuDao.getAll();                          
                                for(EtapaMenu letapamenu:HlistaEtapaMenu){
                                    //por cada etapa muestro los que tenga en grupoEtapa
                                    int idEtapaMenu = letapamenu.getIdEtapamenu();
                                    out.println("<tr>"
                                       +"<td>"+letapamenu.getDescripcionEtapa()+"</td>"
                                       +"<td>   </td>"
                                       +"<td>"+letapamenu.getHoraEtapa()+"</td>"
                                       + "</tr>" );
                                    List<GrupoEtapa> listaGrupoEtapa =  GrupoEtapaDao.getAllGrupoEtapabyNoMenu(HnoMenu,idEtapaMenu);
                                    out.println("<tr><td>   </td>"
                                            +"<td>"
                                            +"<ul>");
                                    for(GrupoEtapa lgrupoetapa:listaGrupoEtapa){     
                                        Alimento a = new Alimento();
                                        a = AlimentoDao.getAlientoById(lgrupoetapa.getIdAlimento());                                   
                                        out.println("<li><b>"+a.getNombreAlimento()+":</b>"+a.getPorcion()+".</li>");                                   
                                    }                              
                                    out.println("</ul>"
                                       +"</td>"
                                       +"<td>  </td>"
                                       +"</tr>");                                   
                                }    
                                out.println("</tbody>"
                                +"</table>"
                                +"</div>"
                                +"</div>");			                                                                                                            
                            } //fin for HistorialMenu
                            out.println("</p>"
                                    +"</a>"
                                    /*+"<a href='#' class='list-group-item'>"
                                    +"<h4 class='list-group-item-heading'>Menu Semana 2</h4>"
                                    +"<p class='list-group-item-text'>Contenido</p>"
                                    +"</a>"
                                    +"<a href='#' class='list-group-item'>"
                                    +"<h4 class='list-group-item-heading'>Menu Semana 3</h4>"
                                    +"<p class='list-group-item-text'>Contenido</p>"
                                    +"</a>"*/
                                    +"</div>"
                                    +"</div>"
                                    +"</div>"
                                    +"</section>");
                        }
                        else{
                            response.sendRedirect("index.html");
                        }
                 }catch(Exception e){
                     response.sendRedirect("index.html");
                }
          
                %>		
		<footer id="footer">
			<div class="inner">
				<ul class="icons">
					<li><a href="#" class="icon fa-github"><span class="label">Github</span></a></li>
				</ul>
				<ul class="copyright">
					<li>&copy; TYFONWARE 2018.</li>
				</ul>
			</div>
		</footer>

		<script src="assets/js/main.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/skel.min.js"></script>
		<script src="assets/js/util.js"></script>
			
	</body>
</html>
