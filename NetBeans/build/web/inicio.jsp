<%-- 
    Document   : inicio
    Created on : 7/05/2018, 06:01:13 PM
    Author     : Diego
--%>

<%@page import="Data.*"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>KeepHealthy | Inicio </title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="assets/css/main.css" />
	    <link rel="stylesheet" href="assets/css/bootstrap.css">
	</head>

	<body class="landing">
            <% try{
                    String Nickname;
                    Nickname=request.getParameter("Nickname");                   
                    Usuario u=new Usuario();
                    u=UsuarioDao.getUsuarioByNickname(Nickname);
                  if(u.getNickname()!= null){
                      //buscar si el usuairo tiene menu asignado en tabla MenuUsuario
                        out.println("<header id='navmain-header'>"
                                +"<div class='container'>"
				+"<nav class='navbar navbar-default'>"
		        	+"<div class='navbar-header'>"
						+"<a href='#' class='js-navmain-nav-toggle navmain-nav-toggle' data-toggle='collapse' data-target='#navbar' aria-expanded='false' aria-controls='navbar'><i></i></a>"
		         		+"<a class='navbar-brand'>KeepHealthy</a>"
		        	+"</div>"
		        	+"<div id='navbar' class='navbar-collapse collapse'>"
		          	+"	<ul class='nav navbar-nav navbar-right'>"
		            	+"	<li class='active'><a href='inicio.jsp?Nickname="+Nickname+"'><span>Inicio</span></a></li>"
		            	+"	<li><a href='mimenu.jsp?Nickname="+Nickname+"'><span>Mi Menu</span></a></li>"
		            	+"	<li><a href='perfil.jsp?Nickname="+Nickname+"'><span>Perfil</span></a></li>"
		            	+"	<li><a href='index.html'><span>Cerrar Sesion</span></a></li>"
		        	+"	</ul>"
		        	+"</div>"
                                +"</nav>"
                                +"</div>"
                                +"</header>");
                      
                      MenuUsuario mu= new MenuUsuario();
                      int idUsuario = u.getIdUsuario();
                      mu=MenuUsuarioDao.getMenuUsuarioByIdUsuario(idUsuario);
                      if(mu.getIdMenu()== 0){
                          //no tiene menu asignado para la semana
                          out.println("<section class='wrapper style2'>"
                                +"<br>	"
                                +"<div class='inner'>"
				+"<div class='content'>"
				+"<h3>Recuerda seleccionar tu menu para continuar con tu buena alimentacion </h3>"
				+"<p>Ve a Mi Menu</p>"
				+"</div>"
                                +"</div>"
                                +"</section>");
                          
                          out.println("<section class='wrapper special' style='margin: 1em; padding: 1em;'>"
                                    +"	<div class='row'>"
                                    +"		<div class='6u 12u$(xsmall)' style='padding:2em'>"
                                    +"			<h4>Progreso semanal</h4>"
                                    +"			<p>¡Mira lo mucho que has logrado!</p>"
                                    +"			<canvas id='myChart' class='chart'></canvas>				"
                                    +"		</div>"
                                    +"		<div class='6u$ 12u$(xsmall)' style='padding:2em'>"
                                    +"			<div class='wrapper style1'>"
                                    +"				<h4>Registro de Resultados</h4>"
                                    +"				<p>Escoge un menu para activar esta sección e ingreses aqui tus datos para poder adaptarnos a tus necesidades</p>"
                                    +"				<form method='post' action='#'>"
                                    +"					<div class='row uniform 50%' style='padding-right:1.7em, padding-left:1.7em'>"                            
                                    +"						<br>"
                                    +"					</div>"
                                    +"					<br>"                                    
                                    //+"				</div>"
                                    +"				</form>"
                                    +"			</div>"
                                    +"		</div>"
                                    +"	</div>		"	
                                    +"</section>"
                                    +"<footer id='footer'>"
                                    +"	<div class='inner'>"
                                    +"		<ul class='icons'>"
                                    +"			<li><a href='#' class='icon fa-github'><span class='label'>Github</span></a></li>"
                                    +"		</ul>"
                                    +"		<ul class='copyright'>"
                                    +"				<li>&copy; TYFONWARE 2018.</li>"
                                    +"		</ul>"
                                    +"	</div>"
                                    +"</footer>"
                                    +" "
                                    //+"<!-- Script de la Grafica-->"
                                    +"	<script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js'></script>"
                                    +"	<script>"
                                    +"	var ctx = document.getElementById('myChart').getContext('2d');"
                                    +"	var chart = new Chart(ctx, {"
                                    //+"	// The type of chart we want to create"
                                    +"	type: 'line',"
                                    +" "
                                    //+"	// The data for our dataset"
                                    +"	data: {"
                                    +"labels: ['Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo'],"
                                    +"datasets: [{"
                                    +"	label: 'Progreso Semanal',"
                                    +"	backgroundColor: 'rgb(255, 99, 132)',"
                                    +"	borderColor: 'rgb(255, 99, 132)',"
                                    +"	data: [0, 10, 5, 2, 20, 30, 45],"
                                    +"	}]"
                                    +"	},"
                                    +"  "
                                    //+"	// Configuration options go here"
                                    +"	options: {}"
                                    +"	});"
                                    +"	</script>"
                                    +" "
                                    +"<script src='assets/js/jquery.min.js'></script>"
                                    +"<script src='assets/js/skel.min.js'></script>"
                                    +"<script src='assets/js/util.js'></script>"
                                    +"<script src='assets/js/main.js'></script>"
                                    +"<script src='assets/js/bootstrap.min.js'></script>"		
                                    +"</body>"
                                    +"</html>'");
                      }
                      else{
                          java.sql.Date FechaCreacion=mu.getFechaCreacion();
                          //encuentra menu asignado y se muestra 
                          out.println("<section class='wrapper style2'>"
                                +"<br>	"
                                +"<div class='inner'>"
				+"<div class='content'>"
				+"<h3>Mi menu de hoy</h3>"
				+"<p>Recuerda seguir tu menu diario para obtener mejores resultados</p>"
				+"</div>"
                                +"</div>"
                                +"</section>");
                          out.println("<section class='wrapper style1' style='margin: 1em; padding: 1em;'>"
                                +"	<div class='content'>"
                                +"		<div class='table-wrapper'>"
                                +"			<table class='alt'>"
                                +"				<thead>"
                                +"					<tr>"
                                +"						<th>Comida</th>"
                                +"						<th>Descripcion</th>"
                                +"						<th>Hora</th>"
                                +"					</tr>"
                                +"				</thead>"
                                +"				<tbody>");
                                
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
                                    +"</section>");
                          
                          
                           out.println("<section class='wrapper special' style='margin: 1em; padding: 1em;'>"
                                    +"<div class='row'>"
                                    +"  <div class='6u 12u$(xsmall)' style='padding:2em'>"
                                    +"	   <h4>Progreso semanal</h4>"
                                    +"	   <p>¡Mira lo mucho que has logrado!</p>"
                                    +"	   <canvas id='myChart' class='chart'></canvas>				"
                                    +"	</div>"
                                    +"	<div class='6u$ 12u$(xsmall)' style='padding:2em'>"
                                    +"	    <div class='wrapper style1'>"
                                    +"		<h4>Registro de Resultados</h4>"
                                    +"		<p>Al terminar es necesario que ingreses aqui tus datos para poder adaptarnos a tus necesidades</p>"
                                    +"		<form action='CierraRegimenServlet' method='POST' >"                                                  
                                                      + "<input type='hidden' value='"+Nickname +"' name='Nickname'>" 
                                                      + "<input type='hidden' value='"+idUsuario +"' name='idUsuario'>" 
                                                      + "<input type='hidden' value='"+idMenu +"' name='idMenu'>"
                                                      + "<input type='hidden' value='"+noMenu +"' name='noMenu'>"
                                                      + "<input type='hidden' value='"+FechaCreacion +"' name='FechaCreacion'>"
                                    +"              <div class='row uniform 50%' style='padding-right:1.7em, padding-left:1.7em'>"
                                    +"			<div class='10u$ 12u$'>"
                                    +"                  	<input type='text' name='Peso' placeholder='Peso perdido (ej. 5kg)'/>"
                                    +"			</div>"
                                    +"			<br>"
                                    +"			<div style='display: run-in'>"
                                    +"                      <p> ¿Que te ha parecido el regimen semanal?</p>"
                                    +"                      <div class='4u 10u(xsmall)'>"
                                    +"				<input type='radio' value='Bueno' name='Bueno' checked>"
                                    +"				<label for='Bueno'>Bueno</label>"
                                    +"                      </div>"
                                    +"                      <br>"
                                    +"                      <div class='4u 10u(xsmall)'>"
                                    +"				<input type='radio' value = 'Regular' name='Regular' >"
                                    +"				<label for='Regular'>Regular</label>	"
                                    +"                      </div>	"
                                    +"                      <br>					"
                                    +"                      <div class='4u 10u(xsmall)'>"
                                    +"				<input type='radio' value ='Malo' name='Malo'>"
                                    +"                          <label for='Malo'>Malo</label>"
                                    +"                      </div>"
                                    +"			</div>"
                                    +"			<br>"
                                    +"			<div class='10u$'>"
                                    +"                      <textarea name ='Notas' placeholder='Notas: (ej. Descanse mas, menos inflamacion)' rows='4'></textarea>"
                                    +"			</div>"
                                    +"			<div class='10u$'>"
                                    +"				<ul>"
                                    +"				<li><input type='submit' value='Enviar' class='special' /></li>"
                                    +"				</ul>"
                                    +"			</div>"
                                    +"		   </div>"
                                    +"		</form>"
                                    +"      </div>"
                                    +"	</div>"
                                    +"</div>		"	
                                    +"</section>"
                +"<footer id='footer'>"
		+"	<div class='inner'>"
		+"		<ul class='icons'>"
		+"			<li><a href='#' class='icon fa-github'><span class='label'>Github</span></a></li>"
		+"		</ul>"
		+"		<ul class='copyright'>"
		+"				<li>&copy; TYFONWARE 2018.</li>"
		+"		</ul>"
		+"	</div>"
		+"</footer>"
                +" "
		//+"<!-- Script de la Grafica-->"
		+"	<script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js'></script>"
		+"	<script>"
    		+"	var ctx = document.getElementById('myChart').getContext('2d');"
    		+"	var chart = new Chart(ctx, {"
        	//+"	// The type of chart we want to create"
        	+"	type: 'line',"
                +" "
        	//+"	// The data for our dataset"
        	+"	data: {"
            	+"labels: ['Lunes', 'Martes', 'Miercoles', 'Jueves', 'Viernes', 'Sabado', 'Domingo'],"
            	+"datasets: [{"
                +"	label: 'Progreso Semanal',"
                +"	backgroundColor: 'rgb(255, 99, 132)',"
                +"	borderColor: 'rgb(255, 99, 132)',"
                +"	data: [0, 10, 5, 2, 20, 30, 45],"
            	+"	}]"
        	+"	},"
                //+"  "
        	//+"	// Configuration options go here"
        	+"	options: {}"
    		+"	});"
		+"	</script>"
                +" "
		+"<script src='assets/js/jquery.min.js'></script>"
		+"<script src='assets/js/skel.min.js'></script>"
		+"<script src='assets/js/util.js'></script>"
		+"<script src='assets/js/main.js'></script>"
		+"<script src='assets/js/bootstrap.min.js'></script>"		
                +"</body>"
                +"</html>'");
                      }
                  }
                  else{
                      response.sendRedirect("index.html");
                  }
                }catch(Exception e){
                     response.sendRedirect("index.html");
                }
          
            %>