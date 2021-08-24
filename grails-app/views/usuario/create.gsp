<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<asset:javascript src="usogeneral/validateIdentification.js" />
	<g:set var="entityName" value="${message(code: 'usuario.label', default: 'Usuario')}" />
	<title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-usuario" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav sombra" role="navigation">
	<ul>
		<li><g:link class="list btn btn-primary" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
	</ul>
</div>
<div id="create-usuario" class="content scaffold-create" role="main">
<%--			<h1><g:message code="default.create.label" args="[entityName]" /></h1>--%>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${usuarioInstance}">
		<ul class="errors" role="alert">
			<g:eachError bean="${usuarioInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors><br>
	<g:form url="[resource:usuarioInstance, action:'save']" >
		<div class="row">
			<div class="col-lg-12 col-md-6">
				<div class="panel panel-border-blue-u">
					<div class="panel-heading panel-body-blue-u">
						<h3 class="panel-title" style="color:white"><i class="fa fa-user fa-fw"></i> Crear Usuario</h3>
					</div>
					<div class="panel-body">
						<fieldset class="form">
							<g:render template="form"/>
						</fieldset>
					</div>
				</div>
			</div>
			<div class="col-lg-2 col-md-2 col-xs-2">
				<g:submitButton name="create" class="form-control btn btn-success" value="Crear Usuario" />
				<%-- value="${message(code: 'default.button.create.label', default: 'Create')}"   --%>
			</div>
		</div>
		<!-- /.row -->
	</g:form>
</div>
</body>
</html>
