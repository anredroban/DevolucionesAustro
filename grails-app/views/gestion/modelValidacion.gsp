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
<g:if test="${tipo == 'EXITOSO'}">
    <div class="col-md-8">
        <div class="alert alert-danger" role="alert">
            <label style="font-size: 30px"><strong><i class="fa fa-fw fa-exclamation-triangle"></i> ¡ERROR!</strong></label><br>
            <p>El registro ya se encuentra guardado con el estado: <strong>${estado}</strong> >> <strong>${subestado}.</strong></p>
            <p>No se puede continuar, por favor consulte con el Administrador.</p>
            <p><strong>ID REGISTRO: </strong> ${idCliente}</p>
        </div>
    </div>
</g:if>

<g:if test="${tipo == 'NO GESTIONABLES'}">
    <div class="col-md-6">
        <div class="alert alert-warning" role="alert">
            <label style="font-size: 30px"><strong><i class="fa fa-fw fa-exclamation-triangle"></i> ¡AVISO!</strong></label><br>
            <p>No se puede gestionar al registro.</p>
            <p><strong>MOTIVO: </strong> ${motivo}</p>
            <p>Por favor consulte con el Administrador o Supervisor.</p>
            <p><strong>ID REGISTRO: </strong> ${idCliente}</p>
        </div>
    </div>
</g:if>

</body>
</html>