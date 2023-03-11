<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="com.alvarez.app.rutas.models.*" %>

<%
    //Recuperamos la lista de choferes que seteamos en el request desde el servelet
    List<Chofer> choferes = (List<Chofer>) request.getAttribute("choferes");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista choferes</title>
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
           <!-- Brand and toggle get grouped for better mobile display -->
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
            <div class="col-6">
                <h2>Listado de choferes</h2>
            </div>

            <div class="col-6">
                <a href="<%=request.getContextPath()%>/cchoferes/alta"></a>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-12">
                <div class="table-responsive">
                    <table class="table table-bordered table-stripped" id="tablaChoferes" width="100%" cellspacing="0">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Ap. Paterno</th>
                                <th>Ap. Materno</th>
                                <th>Licencia</th>
                                <th>Telefono</th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for(Chofer c : choferes) { %>
                                <tr>
                                    <td> <%=c.getId()%> </td>
                                    <td> <%=c.getNombre()%> </td>
                                    <td> <%=c.getApPaterno()%> </td>
                                    <td> <%=c.getApMaterno()%> </td>
                                    <td> <%=c.getLicencia()%> </td>
                                    <td> <%=c.getTelefono()%> </td>
                                    <td class="position-relative">
                                        <a class="btn btn-primary btn-xs start-50" href="<%=request.getContextPath()%>/choferes/detalle?id=<%=c.getId()%>">Detalle</a>
                                    </td>
                                    <td class="center">
                                        <a class="btn btn-xs btn-warning" href="<%=request.getContextPath()%>/choferes/editar?id=<%=c.getId()%>">Editar</a>
                                    </td>
                                    <td class="center">
                                        <a class="btn btn-danger btn-xs" onclick="return confirm('esta seguro que desea eliminar?');" href="<%=request.getContextPath()%>/choferes/eliminar?id=<%=c.getId()%>">Eliminar</a>
                                    </td>
                                </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>