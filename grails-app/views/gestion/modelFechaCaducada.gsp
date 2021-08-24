<%--
  Created by IntelliJ IDEA.
  User: Desarrollo
  Date: 28/10/2020
  Time: 12:28
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <meta name="layout" content="main" />
    <title>Devoluciones</title>
    <asset:stylesheet src="bootstrap.min.css" />
</head>

<body>
<br>
<div class="col-md-6">
    <div class="alert alert-warning" role="alert">
        <label style="font-size: 30px"><strong><i class="fa fa-fw fa-exclamation-triangle"></i> ¡AVISO!</strong></label><br>
        <p>No se puede gestionar al registro.</p>
        <p><strong>MOTIVO: </strong> ${motivo}</p>
        <p>Por favor consulte con el Administrador o Supervisor.</p>
        <p><strong>ID REGISTRO: </strong> ${idCliente}</p>
    </div>
</div>
</body>
</html>