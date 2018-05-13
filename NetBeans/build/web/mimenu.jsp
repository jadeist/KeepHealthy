<%-- 
    Document   : mimenu
    Created on : 7/05/2018, 06:20:38 PM
    Author     : Diego
--%>

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
                 <% String Nickname,Contrasena;
                Nickname=request.getParameter("Nickname");
            //Contrasena=request.getParameter("Contrasena"); 
             out.println("Bie"+Nickname);    
            %>
		<header id="navmain-header">
			<div class="container">
			    <nav class="navbar navbar-default">
		        	<div class="navbar-header">
						<a href="#" class="js-navmain-nav-toggle navmain-nav-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"><i></i></a>
		         		<a class="navbar-brand" href="index.html">KeepHealthy</a>
		        	</div>
		        	<div id="navbar" class="navbar-collapse collapse">
		          		<ul class="nav navbar-nav navbar-right">
		            		<li><a href='inicio.jsp?Nickname="<%out.println(""+Nickname);%>"><span>Inicio</span></a></li>
		            		<li class="active"><a href='mimenu.jsp?Nickname="+Nickname+"'><span>Mi Menu</span></a></li>
		            		<li><a href="perfil.jsp"><span>Perfil</span></a></li>
                    		<li><a href="index.html" ><span>Cerrar Sesion</span></a></li>
		          		</ul>
		        	</div>
			    </nav>
		  	</div>
		</header>

		<section class="wrapper style4"></section>

		<section>
			<div class="row" style="margin: 1em; padding: 2em;">
				<div class="6u 12u$(xsmall)">
					<div class="content">
						<h3>Menu Actual</h3>
						<div class="table-wrapper">
						<table class="alt">
							<thead>
								<tr>
									<th>Comida</th>
									<th>Descripcion</th>
									<th>Hora</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Desayuno</td>
									<td>
										<ul>
											<li><b>Sandwich:</b> Dos panes integrales, mayonesa baja en grasa, jamon de pierna de pavo y una rebanada de queso panela.</li>
											<li><b>Fruta:</b> Puede ser una naranja, una manzana o una pera. </li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>9:00 AM</td>
								</tr>
								<tr>
									<td>Almuerzo</td>
									<td>
										<ul>
											<li><b>Galletas:</b> Tres galletas saladas acompañadas de una cucharada de queso crema.</li>
											<li><b>Lacteo:</b> Puede ser un vaso de leche o un vaso de yogurth</li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>12 AM</td>
								</tr>
								<tr>
									<td>Comida</td>
									<td>
										<ul>
											<li><b>Sopa:</b> Caldo de pollo o consome, con o sin verduras.</li>
											<li><b>Plato fuerte:</b> Una rebanada de lasagna vegetariana.</li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>2:30 PM</td>
								</tr>
								<tr>
									<td>Merienda</td>
									<td>
										<ul>
											<li><b>Galletas:</b> Tres galletas saladas acompañadas de una cucharada de queso crema.</li>
											<li><b>Lacteo:</b> Puede ser un vaso de leche o un vaso de yogurth</li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>18:00 PM</td>
								</tr>
								<tr>
									<td>Cena</td>
									<td>
										<ul>
											<li><b>Fruta:</b> Puede ser una naranja, una manzana o una pera. </li>
											<li><b>Lacteo:</b> Puede ser un vaso de leche o un vaso de yogurth</li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>21:30 PM</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="6u$ 12u$(xsmall)">
				<div style="margin: 1em; padding: 2em;">
					<a href="#" class="button special big">Editar</a>
					<hr />
					<div class="wrapper style3" style="margin: 1em; padding: 1em;">
						<h4> Alimentos Excluidos:</h4>
						<hr />
						<ul class="alt">
							<li><b>Nuez</b></li>
							<li><b>Carne de cerdo</b></li>
							<li><b>Queso americano</b></li>
						</ul>
					</div>
				</div>
			</div>									
		</section>

		<section>
			<div class="wrapper style4" style="overflow-y:scroll;, margin: 1em; padding: 2em;">
				<h3>Historial de Menus (Regimenes)</h3>
  				<div class="list-group" >
  					<a class="list-group-item" style="background-color: #69b294;">
    				<h4 class="list-group-item-heading">Menu Semana 1</h4>
    				<p class="list-group-item-text">
    				<div class="table-wrapper">
						<table class="alt">
							<thead>
								<tr>
									<th>Comida</th>
									<th>Descripcion</th>
									<th>Hora</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>Desayuno</td>
									<td>
										<ul>
											<li><b>Sandwich:</b> Dos panes integrales, mayonesa baja en grasa, jamon de pierna de pavo y una rebanada de queso panela.</li>
											<li><b>Fruta:</b> Puede ser una naranja, una manzana o una pera. </li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>9:00 AM</td>
								</tr>
								<tr>
									<td>Almuerzo</td>
									<td>
										<ul>
											<li><b>Galletas:</b> Tres galletas saladas acompañadas de una cucharada de queso crema.</li>
											<li><b>Lacteo:</b> Puede ser un vaso de leche o un vaso de yogurth</li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>12 AM</td>
								</tr>
								<tr>
									<td>Comida</td>
									<td>
										<ul>
											<li><b>Sopa:</b> Caldo de pollo o consome, con o sin verduras.</li>
											<li><b>Plato fuerte:</b> Una rebanada de lasagna vegetariana.</li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>2:30 PM</td>
								</tr>
								<tr>
									<td>Merienda</td>
									<td>
										<ul>
											<li><b>Galletas:</b> Tres galletas saladas acompañadas de una cucharada de queso crema.</li>
											<li><b>Lacteo:</b> Puede ser un vaso de leche o un vaso de yogurth</li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>18:00 PM</td>
								</tr>
								<tr>
									<td>Cena</td>
									<td>
										<ul>
											<li><b>Fruta:</b> Puede ser una naranja, una manzana o una pera. </li>
											<li><b>Lacteo:</b> Puede ser un vaso de leche o un vaso de yogurth</li>
											<li><b>Agua:</b> Un vaso de agua o té sin azucar.</li>
										</ul>
									</td>
									<td>21:30 PM</td>
								</tr>
								</tbody>
							</table>
						</div>
					</p>
  					</a>
  					<a href="#" class="list-group-item">
    					<h4 class="list-group-item-heading">Menu Semana 2</h4>
    					<p class="list-group-item-text">Contenido</p>
  					</a>
  					<a href="#" class="list-group-item">
    					<h4 class="list-group-item-heading">Menu Semana 3</h4>
   						<p class="list-group-item-text">Contenido</p>
  					</a>
					</div>
				</div>
			</div>
		</section>

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
