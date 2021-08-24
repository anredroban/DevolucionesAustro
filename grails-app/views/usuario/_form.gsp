<%@ page import="com.pw.security.Usuario" %>
<%@page import="utilitarios.Util"%>


<div class="form-group col-lg-3 col-md-6 col-xs-12 ${hasErrors(bean: usuarioInstance, field: 'cedula', 'error')} required">
	<label for="nombre">
		<g:message code="usuario.nombre.label" default="Cédula" />
		<span class="required-indicator">*&nbsp;</span>
	</label>
	<g:textField maxlength="10"  minlength="10" class="form-control" name="cedula" required="" value="${usuarioInstance?.cedula}"/>

</div>
<br><br><br><br>
<div class="form-group col-lg-3 col-md-6 col-xs-12 ${hasErrors(bean: usuarioInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="usuario.nombre.label" default="Nombre" />
		<span class="required-indicator">*&nbsp;</span>
	</label>
	<g:textField class="form-control" name="nombre" required="" value="${usuarioInstance?.nombre}"/>

</div>
<br><br><br><br>


<div class="form-group col-lg-3 col-md-6 col-xs-12 ${hasErrors(bean: usuarioInstance, field: 'usuario', 'error')} required">
	<label for="usuario">
		<g:message code="usuario.usuario.label" default="Usuario" />
		<span class="required-indicator">*&nbsp;</span>
	</label>
	<g:textField class="form-control" name="usuario" required="" value="${usuarioInstance?.usuario}"/>

</div>

<br><br><br><br>



<div class="form-group col-lg-3 col-md-6 col-xs-12 ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="usuario.password.label" default="Password" />
		<span class="required-indicator">*&nbsp;</span>
	</label>
	<g:textField class="form-control" name="password" required="" value="${usuarioInstance?.password}"/>

</div>

<br><br><br><br>

<div class="form-group col-lg-3 col-md-6 col-xs-12 ${hasErrors(bean: usuarioInstance, field: 'extent', 'error')} required">
	<label for="extent">
		<g:message code="usuario.extent.label" default="Extension" />
		<span class="required-indicator">*&nbsp;</span>
	</label>
	<g:textField class="form-control" name="extent" value="${usuarioInstance?.extent}"/>
</div>


<div class="form-group col-lg-3 col-md-6 col-xs-12 ${hasErrors(bean: usuarioInstance, field: 'rol', 'error')} required">
	<label for="rol">
		<g:message code="usuario.rol.label" default="Rol" />
		<span class="required-indicator">*&nbsp;</span>
	</label>
	<g:if test="${Util.isAdmin(session.user.usuario)}">
		<g:select  id="rol" name="rol.id" from="${com.pw.security.Rol.list()}" optionKey="id" required="" value="${usuarioInstance?.rol?.id}" class="form-control" optionValue="${{it.nombre}}"/>
	</g:if>
	<g:else>
		<g:select id="rol" name="rol.id" from="${utilitarios.Util.getRoles()}" optionKey="id" required="" value="${usuarioInstance?.rol?.id}" class="form-control" optionValue="${{it.nombre}}"/>
	</g:else>

</div>

<div class="form-group col-lg-3 col-md-6 col-xs-12 ${hasErrors(bean: usuarioInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="usuario.estado.label" default="Estado" />
		<span class="required-indicator">*&nbsp;</span>
	</label>
	<g:select class="form-control" name="estado" from="${usuarioInstance.constraints.estado.inList}" required="" value="${usuarioInstance?.estado}" valueMessagePrefix="usuario.estado"/>

</div>