<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.alvarez.app.rutas.models.*" %>

<%
    Map<String, String> errores = (Map<String, String>)request.getAttribute("errores");
    Camion camion = (Camion) request.getAttribute("camiones");
    
    List<Chofer> choferes = (List<Chofer>) request.getAttribute("choferes");
    List<Camion> camiones = (List<Camion>) request.getAttribute("camiones");
    List<Integer> modelos = (List<Integer>) request.getAttribute("modelos");
    
    String tipo = camion.getTipoCamion() != null ? camion.getTipoCamion().getDescripcion() : "";
    String marcaCamion = camion.getMarca() != null ? camion.getMarca().getDescripcion() : "";
    Integer modeloCamion = camion.getModelo() != null ? camion.getModelo() : 0;
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta rutas</title>
    <!--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css" integrity="sha384-X38yfunGUhNzHpBaEBsWLO+A0HDYOQi8ufWDkZ0k9e0eXz/tH3II7uKZ9msv++Ls" crossorigin="anonymous">-->

<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
   <link href="//cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css" rel="stylesheet">

   <script src="https://code.jquery.com/jquery-2.2.4.min.js"
      integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
      crossorigin="anonymous"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <script src="//cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>

</head>
<body class="centrar">

   <nav class="navbar navbar-inverse">
       <div class="container-fluid">
           <!-- Brand and toggle get grouped for better mobile display-->
           <div class="navbar-header" id="div1">
               <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                   data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                   <span class="sr-only">Toggle navigation</span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
                   <span class="icon-bar"></span>
               </button>
               <a class="navbar-brand" href="#" id="enlace1">Rutas App</a>
           </div>


           <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
               <ul class="nav navbar-nav">
                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Choferes<span
                               class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a href="<%=request.getContextPath()%>/choferes/listar">Lista Choferes</a></li>
                           <li><a href="<%=request.getContextPath()%>/choferes/alta">Alta Chofer</a></li>

                       </ul>

                   </li>

                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Camiones<span
                               class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a href="<%=request.getContextPath()%>/camiones/listar">Lista Camiones</a></li>
                           <li><a href="<%=request.getContextPath()%>/camiones/alta">Alta Camion</a></li>

                       </ul>
                   </li>

                   <li class="dropdown">
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="false">Rutas<span
                               class="caret"></span></a>
                       <ul class="dropdown-menu">
                           <li><a href="<%=request.getContextPath()%>/rutas/alta">Alta Ruta</a></li>

                       </ul>
                   </li>


               </ul>
           </div><!-- /.navbar-collapse -->
       </div><!-- /.container-fluid -->
   </nav>

    <div class="container">
        <div class="row">
            <div class="col-12">
                <h2>Formulario alta rutas</h2>
            </div>
        </div>
        <br>

        <% if(errores != null && errores.size()>0){ %>
            <ul class="alert alert-danger mx-5 px-5">
                <% for(String error : errores.values()){%>
                    <li><%=error%></li>
                <%}%>
            </ul>
        <%}%>

DIRECCION_ORIGEN_ID	NUMBER
DIRECCION_DESTINO_ID	NUMBER
DISTANCIA	FLOAT
FECHA_SALIDA	DATE
FECHA_LLEGADA_ESTIMADA	DATE
FECHA_LLEGADA_REAL	DATE
A_TIEMPO	NUMBER

        <div class="row">
            <form action="<%=request.getContextPath()%>/choferes/alta" method="post">
                <div class="col-md-12">
                    <!--Camiones-->
                    <div class="form-group">
                        <label for="">Camiones</label>
                        <select type="text" name="camiones" id="camiones" class="form-control">
                            <option value=""> ---seleccionar--- </option>
                            <% for(Camion t : camiones){ %>
                                <% if(t.getDescripcion().equals(tipo)) { %>
                                    <option value="<%=t.getDescripcion()%>" selected><%=t.getDescripcion()%></option>
                                <% } else { %>
                                    <option value="<%=t.getDescripcion()%>"><%=t.getDescripcion()%></option>
                                <% } %>
                            <% } %>
                        </select>
                        <%
                            if(errores != null && errores.containsKey("tipoCamion")){
                                out.println("<span class='text-danger'>"+errores.get("tipoCamion")+"</span>");
                            }
                        %>
                    </div>
                

                    <div class="form-group">
                        <label for="">Choferes</label>
                        <input type="text" name="choferes" id="choferes" class="form-control" value="${param.choferes}">
                        <%
                            if(errores != null && errores.containsKey("choferes")){
                                out.println("<span class='text-danger'>"+errores.get("choferes")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Apellido materno</label>
                        <input type="text" name="apMaterno" id="apMaterno" class="form-control" value="${param.apMaterno}">
                        <%
                            if(errores != null && errores.containsKey("apMaterno")){
                                out.println("<span class='text-danger'>"+errores.get("apMaterno")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Licencia</label>
                        <input type="text" name="licencia" id="licencia" class="form-control" value="${param.licencia}">
                        <%
                            if(errores != null && errores.containsKey("licencia")){
                                out.println("<span class='text-danger'>"+errores.get("licencia")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Telefono</label>
                        <input type="text" name="telefono" id="telefono" class="form-control" value="${param.telefono}">
                        <%
                            if(errores != null && errores.containsKey("telefono")){
                                out.println("<span class='text-danger'>"+errores.get("telefono")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Fecha de nacimiento</label>
                        <input type="text" name="fechaNacimiento" id="fechaNacimiento" class="form-control" value="${param.fechaNacimiento}">
                        <%
                            if(errores != null && errores.containsKey("fechaNacimiento")){
                                out.println("<span class='text-danger'>"+errores.get("fechaNacimiento")+"</span>");
                            }
                        %>
                    </div>

                    <div class="form-group">
                        <label for="">Disponibilidad</label>
                        <input type="checkbox" name="disponibilidad" id="disponibilidad" class="form-check-input" value="${param.disponibilidad}" checked>
                    </div>

                    <div class="form-group">
                        <button type="submit" class="btn btn-success btn-xs">Guardar</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>