
<%@ page import="com.pw.security.Usuario" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
<a href="#show-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav sombra" role="navigation">
	<ul>
		<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
		<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
	</ul>
</div><br>
<div id="show-usuario" class="content scaffold-show" role="main">
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<ol class="property-list usuario">
		<div class="row">
			<div class="col-lg-12 col-md-6">
				<div class="panel panel-border-blue-u">
					<div class="panel-heading panel-body-blue-u">
						<h3 class="panel-title" style="color:white"><i class="fa fa-user fa-fw"></i> <g:fieldValue bean="${usuarioInstance}" field="nombre"/></h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive">

							<table class="table table-bordered table-hover table-striped">
								<tbody>
								<tr>
									<td class="success"><strong>Cédula</strong></td>
									<td class="success"><span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${usuarioInstance}" field="cedula"/></span></td>
								</tr>
								<tr>
									<td class="active"><strong>Nombre</strong></td>
									<td class="active"><span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${usuarioInstance}" field="nombre"/></span></td>
								</tr>
								<tr>
									<td class="success"><strong>Usuario</strong></td>
									<td class="success"><span class="property-value" aria-labelledby="usuario"><g:fieldValue bean="${usuarioInstance}" field="usuario"/></span></td>
								</tr>
								<tr>
									<td class="active"><strong>Password</strong></td>
									<td class="active"><span class="property-value" aria-labelledby="password-label">********</span></td>
								</tr>
								<tr>
									<td class="success"><strong>Rol</strong></td>
									<td class="success"><span class="property-value" aria-labelledby="rol-label"><g:link controller="rol" action="show" id="${usuarioInstance?.rol?.id}">${usuarioInstance?.rol.nombre?.encodeAsHTML()}</g:link></span></td>
								</tr>
								<tr>
									<td class="active"><strong>Estado</strong></td>
									<td class="active"><span class="property-value" aria-labelledby="estado-label"><g:fieldValue bean="${usuarioInstance}" field="estado"/></span></td>
								</tr>
								<tr>
									<td class="success"><strong>Fecha Creación</strong></td>
									<td class="success"><span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${usuarioInstance?.dateCreated}" format="yyyy-MM-dd HH:mm:ss" /></span></td>
								</tr>
								<tr>
									<td class="active"><strong>Fecha Actualización</strong></td>
									<td class="active"><span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${usuarioInstance?.lastUpdated}" format="yyyy-MM-dd HH:mm:ss" /></span></td>
								</tr>
								</tbody>
							</table>
						</div>
						<%--								table responsive--%>

					</div>
				</div>
			</div>
			<g:form url="[resource:usuarioInstance, action:'delete']" method="DELETE">
			<%--<fieldset class="buttons">
                <g:link class="edit" action="edit" resource="${usuarioInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
            </fieldset>--%>
				<div class="col-lg-2 col-md-2 col-xs-2">
					<g:actionSubmit class="form-control btn btn-success" action="edit" value="Editar" />
					<%-- value="${message(code: 'default.button.create.label', default: 'Create')}"   --%>
				</div>
			</g:form>
		</div>
		<!-- /.row -->
	</ol>

</div>
</body>
</html>
