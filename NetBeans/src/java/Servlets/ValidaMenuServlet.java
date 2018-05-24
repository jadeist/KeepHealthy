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


public class ValidaMenuServlet extends HttpServlet {

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
                int CantidadAlimentosElegidos=0;
                //para verificar que los alimentos no sobre pasen las caloriasDiarias
                float CaloriasDiarias =u.getCaloriasDiarias();                
                
                float TotalCaloriasDiarias = 0;
                float TotalCaloriasEtapa=0;
                //para verificar que cada etapa no sobre pase las calorias segun su porcentaje
                float porcentajeCalorias;
                float totalporcentajeCalorias=0;
                String Error="Error: ";
                List<EtapaMenu> lEtapaMenu =  EtapaMenuDao.getAll();
                String sAlimento;
                int res=0;
                //recorro todas las etapas
                for(EtapaMenu lEtapaM:lEtapaMenu){
                    String NombreEtapa = lEtapaM.getDescripcionEtapa();
                    TotalCaloriasEtapa =0;
                    porcentajeCalorias = CaloriasDiarias * (lEtapaM.getPocentajeCalorias()/100);                    
                    //out.println("etapa " + NombreEtapa);
                    //out.println("porcentajeCalorias "+porcentajeCalorias);
                    //out.println("CaloriasDiarias "+CaloriasDiarias);
                    //out.println("TotalCaloriasEtapa "+TotalCaloriasEtapa);
                    
                    int idEtapaMenu = lEtapaM.getIdEtapamenu();
                    List<GrupoEtapaCatAlimento> listaGrupoEtapaCatAlimento = GrupoEtapaCatAlimentoDao.getAllGrupoEtapaCatAlimentobyidEtapaMenu(idEtapaMenu);
                    for(GrupoEtapaCatAlimento lGrupoEtapaCatAlimento:listaGrupoEtapaCatAlimento){  
                        int idCategoria = lGrupoEtapaCatAlimento.getIdCategoria();
                        List<Alimento> listaAlimento =  AlimentoDao.getAllAlimentosByCategoria(idCategoria);
                        for(Alimento lAlimento:listaAlimento){            
                            int idAlimento = lAlimento.getIdAlimento();
                            sAlimento = request.getParameter(String.valueOf(idEtapaMenu)+String.valueOf(idAlimento));
                            //out.println("idAlimento "+idAlimento);
                            //out.println("idcategoria "+idCategoria);
                            //out.println("sAlimento "+sAlimento);
                            if (sAlimento!=null){
                                //fue escogido
                                //cuantas calorias representa ese alimento
                                Alimento a = new Alimento();
                                a = AlimentoDao.getAlientoById(idAlimento);
                                float Calorias = a.getCalorias();
                                //out.println("Calorias "+Calorias);
                                TotalCaloriasEtapa = TotalCaloriasEtapa + Calorias;
                                TotalCaloriasDiarias = TotalCaloriasDiarias + Calorias;
                            }    
                        }                 
                        //temino de recorrer las categorias de esa etapa
                      
                    }
                    //out.println("TotalCaloriasEtapa "+TotalCaloriasEtapa);
                    if (TotalCaloriasEtapa>porcentajeCalorias){
                        //error se paso de calorias para la etapa 
                        Error = Error + " Se sobrepasaron las Calorias en la etapa " + NombreEtapa ; 
                       res = res + 1;
                    }    
                    else{
                        //revisaremos si son insufientes menos del 80% de lo que se requiere
                        Float porcTotalCaloriasEtapa = (CaloriasDiarias * 0.20f) * 0.8f;
                        //out.println("TotalCaloriasEtapa"+TotalCaloriasEtapa);
                        //out.println("porcTotalCalorias"+porcTotalCaloriasEtapa);
                        if (TotalCaloriasEtapa < porcTotalCaloriasEtapa){
                            Error = Error + " Las calorias son insuficientes en la etapa " + NombreEtapa ;                             
                            res = res + 1;
                        }
                    }
                }
                    //out.println("error"+Error);
                    if (res==0){
                        //verifico si el total de calorias sumadas
                        //contra el total de caloriasdiarias
                        Float MinimoCaloriasDiarias = CaloriasDiarias * 0.80f;
                        if (TotalCaloriasDiarias < MinimoCaloriasDiarias){
                            //envio error de calorias insuficientes
                           // out.println("error de calorias insuficientes");
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<script src='assets/js/sweetalert.min.js'></script>");
                            out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<script type='text/javascript'>");
                            out.println("swal({title: 'Hubo un error',text: ' calorias insuficientes',type: 'error'},");
                            out.println("function () {window.location.href = 'mimenu.jsp';});");
                            out.println("</script>"); 
                            out.println("</body>");
                            out.println("</html>");
                        }
                        else{
                            //calorias correctas
                            //crear menu y obtener idmenu y nomenu
                            Menu m = new Menu();
                            m = MenuDao.getLastnoMenu();
                            int noMenu = m.getNoMenu() + 1;
                            m.setCaloriasMenu(TotalCaloriasDiarias);
                            m.setNoMenu(noMenu);
                            //out.println("nomenu"+noMenu);
                            //out.println("TotalCaloriasDiarias");
                            estatus = MenuDao.Guardar(m);
                            if (estatus>0){
                                //guardo bien el menu
                                //crear menusuario con idmenu con fechaCreacion
                                m = MenuDao.getMenuBynoMenu(noMenu);
                                int idMenu = m.getIdMenu();
                                //FechaRegistro                  
                                java.util.Date d = new java.util.Date();  
                                java.sql.Date date2 = new java.sql.Date(d.getTime());
                                MenuUsuario mu = new MenuUsuario();
                                mu.setIdMenu(idMenu);
                                mu.setFechaCreacion(date2);
                                mu.setIdUsuario(idUsuario);
                                estatus = MenuUsuarioDao.Guardar(mu);
                                if (estatus>0){
                                    //se creo menuusuario
                                    //crear grupoEtapa con  nomenu para todas las etapas  
                                    List<EtapaMenu> lEtapaMenu2 =  EtapaMenuDao.getAll();
                                    //recorro todas las etapas
                                    res = 0;
                                    for(EtapaMenu lEtapaM2:lEtapaMenu2){                                        
                                        int idEtapaMenu2 = lEtapaM2.getIdEtapamenu();
                                        List<GrupoEtapaCatAlimento> listaGrupoEtapaCatAlimento2 = GrupoEtapaCatAlimentoDao.getAllGrupoEtapaCatAlimentobyidEtapaMenu(idEtapaMenu2);
                                        for(GrupoEtapaCatAlimento lGrupoEtapaCatAlimento2:listaGrupoEtapaCatAlimento2){  
                                            int idCategoria2 = lGrupoEtapaCatAlimento2.getIdCategoria();
                                            List<Alimento> listaAlimento2 =  AlimentoDao.getAllAlimentosByCategoria(idCategoria2);
                                            for(Alimento lAlimento2:listaAlimento2){            
                                                int idAlimento2 = lAlimento2.getIdAlimento();
                                                sAlimento = request.getParameter(String.valueOf(idEtapaMenu2)+String.valueOf(idAlimento2));
                                                if (sAlimento!=null){
                                                    //fue escogido
                                                    //se debe agregar a la tabla grupoEtapa
                                                    GrupoEtapa ge = new GrupoEtapa();
                                                    ge.setIdEtapaMenu(idEtapaMenu2);
                                                    ge.setIdAlimento(idAlimento2);
                                                    ge.setNoMenu(noMenu);
                                                    estatus = GrupoEtapaDao.Guardar(ge);
                                                    if (estatus>0){
                                                        //continuar con las demas etapas
                                                    }
                                                    else{
                                                        // ocurrio error en grabar grupoetapa
                                                        //out.println("error en grabar grupoetapa");
                                                        res = res +1 ;
                                                    }                                                    
                                                }
                                            } //for AlimentoCategoria
                                        } //for grupoetapa
                                    } //for etapamenu
                                    if (res==0){
                                        //eliminar de menuusariocrea
                                        estatus = MenuUsuarioCreaDao.Eliminar(idUsuario);
                                        if (estatus>0){
                                            //exito en toda la actualizacion
                                            //out.println("exito en toda la actualizacion");
                                            out.println("<html>"
                                                    +"<head>"
                                                    +"<title>KeepHealthy | Menu Creado </title>"
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
                                                    +"<section id='MenuCrea' class='wrapper style2 special'>"
                                                    +"<div class='inner'>"
                                                    +"<header class='major narrow'>"
                                                    +"<h2>Menu Creado con exito</h2>"
                                                    +"</header>"
                                                    +"<form action='mimenu.jsp' method='POST'>"
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
                                        else{
                                            //existio un error al eliminar menuusuariocrea
                                            //out.println("existio un error al eliminar menuusuario");
                                            out.println("<html>");
                                            out.println("<head>");
                                            out.println("<script src='assets/js/sweetalert.min.js'></script>");
                                            out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                                            out.println("</head>");
                                            out.println("<body>");
                                            out.println("<script type='text/javascript'>");
                                            out.println("swal({title: 'Hubo un error',text: 'no se elimino menuusario.',type: 'error'},");
                                            out.println("function () {window.location.href = 'mimenu.jsp';});");
                                            out.println("</script>"); 
                                            out.println("</body>");
                                            out.println("</html>");
                                        }
                                    }
                                    else{
                                        //ocurrio algun error al grabar grupoetapa
                                        //out.println("error en algun error al grabar grupoetapa");
                                            out.println("<html>");
                                            out.println("<head>");
                                            out.println("<script src='assets/js/sweetalert.min.js'></script>");
                                            out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                                            out.println("</head>");
                                            out.println("<body>");
                                            out.println("<script type='text/javascript'>");
                                            out.println("swal({title: 'Hubo un error',text: 'Error al grabar en grpoetapa.',type: 'error'},");
                                            out.println("function () {window.location.href = 'mimenu.jsp';});");
                                            out.println("</script>"); 
                                            out.println("</body>");
                                            out.println("</html>");
                                    }
                                    
                                }
                                else{
                                    //sucedio un error al guardar menu usuario
                                    //out.println("error al guardar menu usuario");
                                        out.println("<html>");
                                        out.println("<head>");
                                        out.println("<script src='assets/js/sweetalert.min.js'></script>");
                                        out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                                        out.println("</head>");
                                        out.println("<body>");
                                        out.println("<script type='text/javascript'>");
                                        out.println("swal({title: 'Hubo un error',text: 'no se guardo en menu usuario.',type: 'error'},");
                                        out.println("function () {window.location.href = 'mimenu.jsp';});");
                                        out.println("</script>"); 
                                        out.println("</body>");
                                        out.println("</html>");
                                }    
                            } 
                            else{
                              //sucedio un error al guardar menu
                                //out.println("error en grabar menu");
                                out.println("<html>");
                                out.println("<head>");
                                out.println("<script src='assets/js/sweetalert.min.js'></script>");
                                out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                                out.println("</head>");
                                out.println("<body>");
                                out.println("<script type='text/javascript'>");
                                out.println("swal({title: 'Hubo un error',text: 'no se guardo en menu .',type: 'error'},");
                                out.println("function () {window.location.href = 'mimenu.jsp';});");
                                out.println("</script>"); 
                                out.println("</body>");
                                out.println("</html>");
                            }
                        }                                                                                         
                    }
                    else{
                        //error en categorias
                        //mostrar los errores
                            out.println("<html>");
                            out.println("<head>");
                            out.println("<script src='assets/js/sweetalert.min.js'></script>");
                            out.println("<link href='assets/css/sweetalert.css' rel='stylesheet' type='text/css'/>");
                            out.println("</head>");
                            out.println("<body>");
                            out.println("<script type='text/javascript'>");
                            out.println("swal({title: 'Hubo un error',text: '"+ Error +"',type: 'error'},");
                            out.println("function () {window.location.href = 'mimenu.jsp';});");
                            out.println("</script>"); 
                            out.println("</body>");
                            out.println("</html>");
                    }
                 
               // }  //for EtapaMenu                     
       }
       else{
                //No existe el usuario
                 response.sendRedirect("index.html");
            } 
                    } catch (SQLException ex) {
            Logger.getLogger(ValidaMenuServlet.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }       
}
