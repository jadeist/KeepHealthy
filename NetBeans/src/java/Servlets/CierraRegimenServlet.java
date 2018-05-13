package Servlets;

import Data.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CierraRegimenServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String Nickname,idUsuarios, idMenus, noMenus, FechaCreacions,Notas;
            String sBueno,sRegular,sMalo,sPeso;
            
            
            java.sql.Date FechaCreacion;
            
            
            
           Nickname=request.getParameter("Nickname");
           
           idUsuarios = request.getParameter("idUsuario");
           idMenus=request.getParameter("idMenu");
           noMenus=request.getParameter("noMenu");
           FechaCreacions=request.getParameter("FechaCreacion");
           sBueno=request.getParameter("Bueno");
           sRegular=request.getParameter("Regular");
           sMalo=request.getParameter("Malo");
           sPeso=request.getParameter("Peso");
           Notas=request.getParameter("Notas");
         
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
           
          java.util.Date dateFechaCreacion = formatter.parse(FechaCreacions);         
          java.util.Date d = new java.util.Date();           
	  int dias=(int) ((d.getTime()-dateFechaCreacion.getTime())/86400000);
          
          if (sBueno==null & sRegular==null & sMalo==null){
            out.println("<html>");
            out.println("<head>");
            out.println("<script src=\"assets/js/sweetalert.min.js\"></script>");
            out.println("<link href=\"assets/css/sweetalert.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("</head>");
            out.println("<body>");
            out.println("<script type=\"text/javascript\">");
            out.println("swal({title: \"Lo sentimos :(\",text: \"No has seleccionado que te ha parecido el regimen .\",type: \"error\"},");
            out.println("function () {window.location.href = 'inicio.jsp';});");
            out.println("</script>"); 
            out.println("</body>");
            out.println("</html>");       
            
          }
          else{
              //escogio la calificacion          
              if (dias<7){
                  //no lleva 7 dias con el regimen
                out.println("<html>");
                out.println("<head>");
                out.println("<script src=\"assets/js/sweetalert.min.js\"></script>");
                out.println("<link href=\"assets/css/sweetalert.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                out.println("</head>");
                out.println("<body>");
                out.println("<script type=\"text/javascript\">");
                out.println("swal({title: \"Lo sentimos :(\",text: \"No has completado los 7 dias del Regimen actual .\",type: \"error\"},");
                out.println("function () {window.location.href = 'inicio.jsp';});");
                out.println("</script>"); 
                out.println("</body>");
                out.println("</html>");          
              }
              else{
                  //ya cumplio mas de 7 dias con el regimen
                   String resultadopeso, resultadonotas;
                   int res;
          
                    res = 0;

                    resultadopeso = validanumerodecimal(sPeso,6,1);
                    if (resultadopeso!="OK")
                            res+=1;
                    resultadonotas = validanombre(Notas,150,1);
                    if (resultadonotas!="OK")
                            res+=1; 
                    if(res==0){
                        
                       int idMenu = Integer.parseInt(idMenus);
                       int idUsuario = Integer.parseInt(idUsuarios);
                       int noMenu = Integer.parseInt(noMenus);
                       java.sql.Date datesqlFechaCreacion = new java.sql.Date(dateFechaCreacion.getTime());
                       int califica;
                       
                       if (sBueno!=null)
                           califica=3;
                       else{
                           if(sRegular!=null)
                              califica=2;
                           else
                              califica=1; 
                       }
                           
                       HistorialMenu hm = new HistorialMenu();
                       
                       hm.setIdMenu(idMenu);
                       hm.setIdUsuario(idUsuario);
                       hm.setFechaCreacion(datesqlFechaCreacion);
                       hm.setCalificacion(califica);
                       hm.setComentarioHistorial(Notas);
                       
                       
                       int estatus  = HistorialMenuDao.Guardar(hm);
                        
                        HttpSession sesion=request.getSession();
                        if(estatus>0){ 
                            float Peso = Float.parseFloat(sPeso);
                            
                            hm = new HistorialMenu();
                            hm = HistorialMenuDao.getHistorialMenuid(idMenu, idUsuario,datesqlFechaCreacion);
                            int idHistorialMenu = hm.getIdHistorialMenu();
                            java.sql.Date datesqlFechaHistorial = new java.sql.Date(d.getTime());
                            HistorialPeso hp = new HistorialPeso();
                            
                            hp.setFechaHistorial(datesqlFechaHistorial);
                            hp.setIdUsuario(idUsuario);
                            hp.setPesoHistorial(Peso);
                            hp.setIdHistorialMenu(idHistorialMenu);
                            
                            
                            estatus  = HistorialPesoDao.Guardar(hp);
                        
                            sesion=request.getSession();
                            if(estatus>0){ 
                                //borrar de MenuUsuario
                                MenuUsuario mu = new MenuUsuario();
                                estatus  = MenuUsuarioDao.Eliminar(idUsuario);
                                sesion=request.getSession();
                                if(estatus>0){ 
                                   //todo se dio de alta correctamente y se elimino
                                   out.println("<html>\n" +
                                    "	<head>\n" +
                                    "		<title>KeepHealthy | Menu actualizado </title>\n" +
                                    "		<meta charset=\"utf-8\" />\n" +
                                    "		<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                                    "		<link rel=\"stylesheet\" href=\"assets/css/main.css\" />\n" +
                                    "	    <link rel=\"stylesheet\" href=\"assets/css/bootstrap.css\">\n" +
                                    "	</head>\n" +
                                    "\n" +
                                    "	<body class=\"landing\">\n" +
                                    "		<header id=\"navmain-header\">\n" +
                                    "			<div class=\"container\">\n" +
                                    "			    <nav class=\"navbar navbar-default\">\n" +
                                    "		        	<div class=\"navbar-header\">\n" +
                                    "						<a href=\"#\" class=\"js-navmain-nav-toggle navmain-nav-toggle\" data-toggle=\"collapse\" data-target=\"#navbar\" aria-expanded=\"false\" aria-controls=\"navbar\"><i></i></a>\n" +
                                    "		         		<a class=\"navbar-brand\">KeepHealthy</a>\n" +
                                    "		    		</div>\n" +
                                    "                                \n" +
                                    "		    		<div id=\"navbar\" class=\"navbar-collapse collapse\">\n" +
                                    "                                 \n" +
                                    "                                \n" +
                                    "		    		</div>\n" +
                                    "                                \n" +
                                    "				</nav>\n" +
                                    "		  	</div>\n" +
                                    "		</header>						\n" +
                                    "			<section id=\"InicioSesion\" class=\"wrapper style2 special\">\n" +
                                    "				<div class=\"inner\">\n" +
                                    "					<header class=\"major narrow\">\n" +
                                    "						<h2>Menu Actualizado con exito</h2>\n" +
                                    "					</header>\n" +
                                    "					<form action='inicio.jsp' method=\"POST\">\n" +
                                    "						<div class=\"container 75%\">\n" +
                                    "							<div class=\"row uniform 50%\">\n" +
                                    "								<div class=\"6u 12u(xsmall)\">\n" +
                                    "									<input name=\"Nickname\" value='"+Nickname+"' type='hidden' />\n" +
                                    "								</div>\n" +
                                    "							</div>\n" +
                                    "						</div>\n" +
                                    "						<ul class=\"actions\">                                                    \n" +
                                    "							<li><input type=\"submit\" class=\"special\" value=\"OK\" /></li>\n" +
                                    "                                                </ul>\n" +
                                    "					</form>\n" +
                                    "				</div>\n" +
                                    "			</section>\n" +
                                    "			<footer id=\"footer\">\n" +
                                    "				<div class=\"inner\">\n" +
                                    "					<ul class=\"icons\">\n" +
                                    "						<li><a href=\"#\" class=\"icon fa-github\">\n" +
                                    "							<span class=\"label\">Github</span>\n" +
                                    "						</a></li>\n" +
                                    "					</ul>\n" +
                                    "					<ul class=\"copyright\">\n" +
                                    "						<li>&copy; TYFONWARE 2018.</li>\n" +
                                    "						<li style=\"text-transform: lowercase\">Imagenes de <a href=\"http://unsplash.com\">Unsplash</a>.</li>\n" +
                                    "					</ul>\n" +
                                    "				</div>\n" +
                                    "			</footer>\n" +
                                    "\n" +
                                    "			<script src=\"assets/js/jquery.min.js\"></script>\n" +
                                    "			<script src=\"assets/js/skel.min.js\"></script>\n" +
                                    "			<script src=\"assets/js/util.js\"></script>\n" +
                                    "			<script src=\"assets/js/main.js\"></script>\n" +
                                    "			<script src=\"assets/js/bootstrap.min.js\"></script>\n" +
                                    "	</body>\n" +
                                    "</html>");
                                    
                                
                                }
                                else{
                                    out.println("<html>");
                                    out.println("<head>");
                                    out.println("<script src=\"assets/js/sweetalert.min.js\"></script>");
                                    out.println("<link href=\"assets/css/sweetalert.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                                    out.println("</head>");
                                    out.println("<body>");
                                    out.println("<script type=\"text/javascript\">");
                                    out.println("swal({title: \"Lo sentimos :(\",text: \"Has excedido el tiempo limite de registro, intentalo de nuevo.\",type: \"warning\"},");
                                    out.println("function () {window.location.href = 'inicio.jsp';});");
                                    out.println("</script>"); 
                                    out.println("</body>");
                                    out.println("</html>");   
                                }
                                
                            }
                            else{
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<script src=\"assets/js/sweetalert.min.js\"></script>");
                                out.println("<link href=\"assets/css/sweetalert.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<script type=\"text/javascript\">");
                                out.println("swal({title: \"Lo sentimos :(\",text: \"Has excedido el tiempo limite de registro, intentalo de nuevo.\",type: \"warning\"},");
                                out.println("function () {window.location.href = 'inicio.jsp';});");
                                out.println("</script>"); 
                                out.println("</body>");
                                out.println("</html>");                                  
                            }                            
                        }
                        else{
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<script src=\"assets/js/sweetalert.min.js\"></script>");
                            out.println("<link href=\"assets/css/sweetalert.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<script type=\"text/javascript\">");
                            out.println("swal({title: \"Lo sentimos :(\",text: \"Has excedido el tiempo limite de registro, intentalo de nuevo.\",type: \"warning\"},");
                            out.println("function () {window.location.href = 'inicio.jsp';});");
                            out.println("</script>"); 
                            out.println("</body>");
                            out.println("</html>");  
                        }                                           
                    }
                    else{
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<script src=\"assets/js/sweetalert.min.js\"></script>");
                        out.println("<link href=\"assets/css/sweetalert.css\" rel=\"stylesheet\" type=\"text/css\"/>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<script type=\"text/javascript\">");
                        out.println("swal({title:\"Ocurrio un error\",");
                        
                        if (resultadopeso!="OK"){
                            out.println("text: \"Error en el peso : "+resultadopeso+"\",type:\"error\"},");
                            out.println("function () {window.location.href = 'index.html?#four';});");
                            out.println("</script>"); 
                            out.println("</body>");
                            out.println("</html>");
                        }
                    
                        if (resultadonotas!="OK"){
                            out.println("text:\"Error en el campo notas:"+resultadonotas+"\",type:\"error\"},");
                            out.println("function () {window.location.href = 'index.html?#four';});");
                            out.println("</script>"); 
                            out.println("</body>");
                            out.println("</html>");
                        }                                                              
                    }
              }              
          }
              
        }catch (ParseException ex) {
            Logger.getLogger(CierraRegimenServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CierraRegimenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
 public String validanumerodecimal(String Cadena, int maximo, int obligatorio) throws ServletException{
        String Error="";        
        String checkOK="1234567890.";        
        boolean allvalido = true;
        int k;
        int contapuntos=0,pospunto=0;
        char primercaracter=' ',ultimocaracter=' ';
        try{                        
             if (Cadena.length()==0 && obligatorio==1){
                 Error="El campo es obligatorio ";
             }    
             else{  
                if (Cadena.length()==0 && obligatorio==0){
                    Error ="OK";
                } 
                else{
                    if (Cadena.length()>maximo ){
                        Error="La longitud del campo no debe ser mayor a " + maximo;
                    }
                    else{                    
                        for(int i=0;i<Cadena.length();i++){
                            char ch= Cadena.charAt(i);
                            for(k=0;k<checkOK.length();k++){
                              if (ch == checkOK.charAt(k))
                                 break;
                              
                            }
                            if (k==checkOK.length()){
                                  allvalido=false;
                            }
                            if(i==0){
                                primercaracter=ch;  
                             }
                            if(i==(Cadena.length()-1)){
                               ultimocaracter=ch; 
                            }
                            if(ch=='.'){
                                  contapuntos++;
                                  pospunto=i;
                              }
                        }
                        if(primercaracter=='.'){
                            Error = "No empieces los numeros decimales con un punto";
                        }else if((pospunto!=1 & primercaracter=='0'& contapuntos>0 )| (primercaracter=='0' & contapuntos==0)){
                            Error = "No empieces los numeros con un 0";
                        }else if (allvalido == false){
                            Error = "Solo se permiten dígitos en este campo ";
                        }else if(contapuntos>1){
                            Error = "Solo se permite un punto decimal";    
                        }else if((contapuntos>0 & Cadena.length()==2)|(ultimocaracter=='.')){
                            Error = "Cuando utilizes punto decimal escribe enteros y decimales";
                        }else if(Double.parseDouble(Cadena)==0){
                             Error = "Ingresa una cantidad mayor a 0";
                        }
                        else{
                            Error ="OK";                    
                        }                                         
                    }    
                }                                                                                          
            }
            
        }catch(Exception e){
            System.out.println("Sólo jugó contigo ¬¬");
        }
         return Error; 
    }
    public String validanombre(String Cadena, int maximo, int obligatorio) throws ServletException{
        String Error="";        
        String checkOK="abcdefghijklmnñopqrstuvwxyzáéíóúABCDEFGHIJKLMNÑOPQRSTUVWXYZÁÉÍÓÚ1234567890 .,";        
        boolean allvalido = true;
        int k;
        try{                        
             if (Cadena.length()==0 && obligatorio==1){
                 Error="El campo es obligatorio ";
             }    
             else{  
                if (Cadena.length()==0 && obligatorio==0){
                    Error ="OK";
                } 
                else{
                    if (Cadena.length()>maximo ){
                        Error="La longitud del campo no debe ser mayor a " + maximo;
                    }
                    else{                    
                        for(int i=0;i<Cadena.length();i++){
                            char ch= Cadena.charAt(i);
                            for(k=0;k<checkOK.length();k++){
                              if (ch == checkOK.charAt(k))
                                 break;
                            }
                            if (k==checkOK.length()){
                                  allvalido=false;
                            }                          
                        }                    
                        if (allvalido == false){
                            Error = "Solo se permiten espacio, punto, coma, letras y numeros en este campo ";
                        }
                        else{
                            Error ="OK";                    
                        }                                         
                    }    
                }                                                                                          
            }
            
        }catch(Exception e){
            System.out.println("Sólo jugó contigo ¬¬");
        }
         return Error; 
    }

}