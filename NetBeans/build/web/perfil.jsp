<%-- 
    Document   : perfil.jsp
    Created on : 7/05/2018, 06:21:35 PM
    Author     : Diego
--%>

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

	<body class="landing">
		<header id="navmain-header">
			<div class="container">
			    <nav class="navbar navbar-default">
		        	<div class="navbar-header">
						<a href="#" class="js-navmain-nav-toggle navmain-nav-toggle" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"><i></i></a>
		         		<a class="navbar-brand" href="index.html">KeepHealthy</a>
		        	</div>
		        	<div id="navbar" class="navbar-collapse collapse">
		          		<ul class="nav navbar-nav navbar-right">
		            		<li><a href="inicio.jsp"><span>Inicio</span></a></li>
		            		<li><a href="mimenu.jsp"><span>Mi Menu</span></a></li>
		            		<li class="active"><a href="perfil.jsp"><span>Perfil</span></a></li>
                    		<li><a href="index.html"><span>Cerrar Sesion <a class="fa-power-of"></a></span></a></li>
		          		</ul>
		        	</div>
			    </nav>
		  	</div>
		</header>

		<section class="wrapper style3"></section>

		<section>
			<div class="row" style="margin: 1em; padding: 1em;">
				<div class="6u$" style="margin:0;padding:0; float:right; width:45%;">
					<div class="6u$">
						<span class="image fit"><img src="images/profilepic.png"/></span>
							<a href="#" class="button">Editar</a>
							<hr />
							<a href="#" class="button alt">Preferencias</a>
					</div>
				</div>
			<div class="6u$" style="display: inline;">
				<div class="wrapper style4" style="padding: 25px; background-color: #69b294; height: 28em">
					<h3>Usuario de Prueba</h3>
					<div class="row 50% uniform">
						<div class="4u$">
							<p style="font-size: 20;"> Menus: 3</p>
						</div>
						<div class="4u$">
							<p style="font-size: 20;"> Edad: 17</p>
						</div>
					</div>
					<p style="font-size: 20;">Peso perdido: 5kg</p>
				</div>
			</div>						
			</div>				
		</section>
			
		<footer id="footer">
			<div class="inner">
				<ul class="icons">
					<li><a href="#" class="icon fa-github">
						<span class="label">Github</span>
						</a></li>
				</ul>
				<ul class="copyright">
					<li>&copy; TYFONWARE 2018.</li>
				</ul>
			</div>
		</footer>

		<script src="assets/js/jquery.min.js"></script>
		<script src="assets/js/skel.min.js"></script>
		<script src="assets/js/util.js"></script>
		<script src="assets/js/main.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		
	</body>
</html>
