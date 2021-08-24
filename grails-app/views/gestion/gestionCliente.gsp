<meta name="layout" content="main">
<%@ page contentType="text/html;charset=UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta charset="utf-8" />
<!--This is what you should include-->
<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">
<asset:stylesheet src="usogeneral/bootstrap-datepicker.min.css"></asset:stylesheet>

<div class="container-fluid">
	<title>Gestionar Cliente</title>

<asset:stylesheet src="usogeneral/datetimepicker.css" />
<asset:stylesheet src="gestion/gestionCliente.css" />

<%--
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css">

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/2.14.1/moment.min.js"></script>
<script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<script type='text/javascript'>
    $(function () {
        $('#datetimepicker3').datetimepicker({
            format: 'LT'
        });
    });
</script>

<script type="text/javascript">
    $(function () {
        $('#datetimepicker1').datetimepicker({
            format: 'YYYY/MM/DD hh:mm A'
        });
    });
</script>


<script type="text/javascript">
    $(function () {
        $('#datetimepicker2').datetimepicker({
            format: 'YYYY/MM/DD hh:mm A'
        });
    });
</script>--%>

<script>
    window.setInterval (BlinkIt, 500);
    var color = "red";
    function BlinkIt () {
        var blink = document.getElementById ("blink");
        color = (color == "#ffffff")? "red" : "#ffffff";
        blink.style.color = color;
        blink.style.fontSize='36px';}
</script>

<div class="col-lg-12 col-md-12 col-xs-12">
	<br><h1><span class="fa fa-phone"></span> Gestionar Cliente</h1>
</div>
<g:if test="${cliente.registroExitoso == 'SI'}">
	<div class="col-md-8"><div class="alert alert-danger" role="alert"><i class="fa fa-fw fa-exclamation-triangle" style="font-size: 30px"></i><span style="font-size: 26px; font-weight: bold">ATENCIÓN</span><span style="font-size: 24px"> CLIENTE CON ESTADO EXITOSO</span></div></div>
</g:if>

<g:form action="guardarCliente">


<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
	<div class="form-group col-lg-4 col-md-6 col-xs-12">
		<label>Nombre Adicional: </label>
		${cliente.nombre}
	</div>
	<div class="form-group col-lg-4 col-md-6 col-xs-12">
		<label>Identificación: </label>
		${cliente.identificacion}
	</div>
	<g:if test="${cliente.producto && cliente.producto.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Producto: </label>
			${cliente.producto}
		</div>
	</g:if>
	<g:if test="${cliente.guia && cliente.guia.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Guia: </label>
			${cliente.guia}
		</div>
	</g:if>
	<g:if test="${cliente.inventario && cliente.inventario.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Inventario: </label>
			${cliente.inventario}
		</div>
	</g:if>
	<g:if test="${cliente.codigoUnico && cliente.codigoUnico.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Código Único: </label>
			${cliente.codigoUnico}
		</div>
	</g:if>
	<g:if test="${cliente.motivoDevolucion && cliente.motivoDevolucion.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Motivo Devolución: </label>
			${cliente.motivoDevolucion}
		</div>
	</g:if>
	<g:if test="${cliente.direccion && cliente.direccion.trim() != ''}">
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
			<label>Direccion: </label>
			${cliente.direccion}
		</div>
	</g:if>
	<g:if test="${cliente.x15Producto && cliente.x15Producto.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Producto_15: </label>
			${cliente.x15Producto}
		</div>
	</g:if>
	<g:if test="${cliente.ciudad && cliente.ciudad.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Ciudad: </label>
			${cliente.ciudad}
		</div>
	</g:if>
	<g:if test="${cliente.dhnomp && cliente.dhnomp.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Nombre Titular: </label>
			${cliente.dhnomp}
		</div>
	</g:if>
	<g:if test="${cliente.cedPrincipal && cliente.cedPrincipal.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Cedula Principal: </label>
			${cliente.cedPrincipal}
		</div>
	</g:if>
	<g:if test="${cliente.edad && cliente.edad.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Edad: </label>
			${cliente.edad}
		</div>
	</g:if>
	<g:if test="${cliente.callCenter && cliente.callCenter.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Call Center: </label>
			${cliente.callCenter}
		</div>
	</g:if>
	<g:if test="${cliente.callCenter && cliente.callCenter.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Call Center: </label>
			${cliente.callCenter}
		</div>
	</g:if>
	<g:if test="${cliente.direccionDomilio1 && cliente.direccionDomilio1.trim() != ''}">
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
			<label>Direccion Domicilio 1: </label>
			${cliente.direccionDomilio1}
		</div>
	</g:if>
	<g:if test="${cliente.direccionDomilio2 && cliente.direccionDomilio2.trim() != ''}">
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
			<label>Direccion Domicilio 2: </label>
			${cliente.direccionDomilio2}
		</div>
	</g:if>
	<g:if test="${cliente.direccionDomilio3 && cliente.direccionDomilio3.trim() != ''}">
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
			<label>Direccion Domicilio 3: </label>
			${cliente.direccionDomilio3}
		</div>
	</g:if>
	<g:if test="${cliente.direccionTrabajo1 && cliente.direccionTrabajo1.trim() != ''}">
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
			<label>Direccion Trabajo 1: </label>
			${cliente.direccionTrabajo1}
		</div>
	</g:if>
	<g:if test="${cliente.direccionTrabajo2 && cliente.direccionTrabajo2.trim() != ''}">
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
			<label>Direccion Trabajo 2: </label>
			${cliente.direccionTrabajo2}
		</div>
	</g:if>
	<g:if test="${cliente.direccionTrabajo3 && cliente.direccionTrabajo3.trim() != ''}">
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
			<label>Direccion Trabajo 3: </label>
			${cliente.direccionTrabajo3}
		</div>
	</g:if>
	<g:if test="${cliente.numeroOperaciones && cliente.numeroOperaciones.trim() != '' && cliente.numeroOperaciones.trim() > '1'}">
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
			<label style="font-size: 28px; font-weight: bold; color: red">Número de Operaciones: </label>
			<label id="blink" style="font-size: 28px; font-weight: bold; color: red" >${cliente.numeroOperaciones}</label>
		</div>
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
		<p><strong style="font-size: 20px">Id´s pertenecientes al cliente:</strong></p>
		<p>
			<%--<blockquote>
		${utilitarios.Util.getNumOperaciones2(cliente.cedPrincipal, cliente.nombreBase)}
		</blockquote>--%>
		<g:each in="${utilitarios.Util.getNumeroOperacionesArray(cliente.cedPrincipal.toString(), cliente.nombreBase)}">
			<li style="list-style: none; font-size: 20px"><a href="${createLink(uri:'/gestion/gestionCliente/')}${it}" target="_blank">${it}</a></li>
		</g:each>
        </p>
       </div>
	</g:if>
</div>

	<%-- INCRUSTAR IFRAME DE SCRITPS EN LOS SISTEMAS 
	<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
		<iframe width="100%" height="500" src="https://docs.google.com/document/d/e/2PACX-1vQx2vUfxyKEAIj9AS083WNhJqVNYpobm4CjWUwd-WYhKy3ktWLJvYpmBf_NcaxmAerDMxEnIgS31j2x/pub?embedded=true"></iframe>
	</div>
	--%>
	

<div class="col-lg-12 col-md-12 col-xs-12">
	<h5> <b>Datos de Contácto</b></h5>
</div>

<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
	<g:if test="${cliente.telefono1}">
		<div id="number1" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-mobile-phone"></span> Teléfono 1: </label>
			${cliente.telefono1}
			<g:select class="form-control" id="estadoTel1" name="estadoTel1" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" optionKey="value" />

		</div>
	</g:if>
	<g:if test="${cliente.telefono2 && cliente.telefono2.trim() != ''}">
		<div id="number2" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-phone"></span> Teléfono 2: </label>
			${cliente.telefono2}
			<g:select  class="form-control" id="estadoTel2" name="estadoTel2" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
		</div>
	</g:if>
	<g:if test="${cliente.telefono3 && cliente.telefono3.trim() != ''}">
		<div id="number3" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-phone"></span> Teléfono 3: </label>
			${cliente.telefono3}
			<g:select  class="form-control" id="estadoTel3" name="estadoTel3" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
		</div>
	</g:if>
	<g:if test="${cliente.telefono4 && cliente.telefono4.trim() != ''}">
		<div id="number4" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-phone"></span> Teléfono 4: </label>
			${cliente.telefono4}
			<g:select  class="form-control" id="estadoTel4" name="estadoTel4" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
		</div>
	</g:if>
	<g:if test="${cliente.telefono5 && cliente.telefono5.trim() != ''}">
		<div id="number5" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-phone"></span> Teléfono 5: </label>
			${cliente.telefono5}
			<g:select  class="form-control" id="estadoTel5" name="estadoTel5" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
		</div>
	</g:if>
	<g:if test="${cliente.telefono6 && cliente.telefono6.trim() != ''}">
		<div id="number6" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-phone"></span> Teléfono 6: </label>
			${cliente.telefono6}
			<g:select  class="form-control" id="estadoTel6" name="estadoTel6" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
		</div>
	</g:if>
	<g:if test="${cliente.telefono7 && cliente.telefono7.trim() != ''}">
		<div id="number7" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-phone"></span> Teléfono 7: </label>
			${cliente.telefono7}
			<g:select  class="form-control" id="estadoTel7" name="estadoTel7" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
		</div>
	</g:if>
	<g:if test="${cliente.telefono8 && cliente.telefono8.trim() != ''}">
		<div id="number8" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-phone"></span> Teléfono 8: </label>
			${cliente.telefono8}
			<g:select  class="form-control" id="estadoTel8" name="estadoTel8" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
		</div>
	</g:if>
	<g:if test="${cliente.telefono9 && cliente.telefono9.trim() != ''}">
		<div id="number9" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-phone"></span> Teléfono 9: </label>
			${cliente.telefono9}
			<g:select  class="form-control" id="estadoTel9" name="estadoTel9" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
		</div>
	</g:if>
	<g:if test="${cliente.telefono10 && cliente.telefono10.trim() != ''}">
		<div id="number10" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-phone"></span> Teléfono 10: </label>
			${cliente.telefono10}
			<g:select  class="form-control" id="estadoTel10" name="estadoTel10" noSelection="${['': '-- Seleccione --']}" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" />
		</div>
	</g:if>
	<div class="col-lg-12 group-title">Datos Complementarios</div>
	<div class="col-lg-12 line"></div>
	<g:if test="${cliente.codigoAsignacion && cliente.codigoAsignacion.trim() != ''}">
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label><span class="fa fa-archive"></span> Easy Code: </label>
			${cliente.codigoAsignacion}
		</div>
	</g:if>
</div>

	<input type="hidden" name="idCliente" value="${cliente.id}" />
	<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">

		<div class="col-lg-4 col-md-6 col-xs-12 form-group">
			<label>Estado Gestion</label>
			<span class="required-indicator">*</span>
			<g:select class="form-control" name="estado" id="status"
					  from="${callcenter.Estado.list()}" optionKey="id"
					  optionValue="${{it.nombre	}}"
					  noSelection="${['': '-- Seleccione --']}" />
		</div>

		<div id="subStatusDiv" class="col-lg-4 col-md-6 col-xs-12 form-group">
			<label>Subestado Gestion</label>
			<span class="required-indicator">*</span>
			<g:select class="form-control" name="substatus" id="substatus" from="" noSelection="${['': '-- Seleccione --']}" />
		</div>

		<div id="subSubStatusDiv" class="form-group col-lg-4 col-md-6 col-xs-12">
			<label>Sub subestado</label>
			<span class="required-indicator">*</span>
			<g:select id="subSubStatus" class="form-control" name="subSubStatus" from="" optionKey="id"></g:select>
		</div>

		<div id="divMotivo" class="col-lg-12 col-md-12 col-xs-12 form-group">
			<label>Motivos No Acepta</label>
			<span class="required-indicator">*</span>
			<g:textArea class="form-control" name="motivoNoAceptaSeguro" value=""/>

		</div>

		<div id="recallDiv">
			<div class="col-lg-4 col-md-6 col-xs-12 form-group">
				<label>Agendamiento</label>
				<span class="required-indicator">*</span>
				<g:select class="form-control" name="agendamiento" id="agendamiento" from="${['AGENDAR PARA MI':'AGENDAR PARA MI', 'AGENDAR PARA CUALQUIERA':'AGENDAR PARA CUALQUIERA']}" optionKey="key"
						  optionValue="value"
						  noSelection="${['': '-- Seleccione --']}" />
			</div>
			<div class="col-lg-4 col-md-6 form-group">
				<label>Fecha Rellamada</label>
				<span class="required-indicator">*</span>
				<g:textField id="recall" name="recall" class="recall form-control"/>
			</div>
		</div>

	<div id="telefonoContactadoDiv" >
		<div class="col-lg-4 col-md-6 form-group">
			<label style="color: red">Teléfono Contactado</label>
			<span class="required-indicator">*</span>
			<g:textField maxlength="10" minlength="9" id="telefonoContactado" name="telefonoContactado" class="form-control"/>
		</div>
		<div class="form-group col-lg-4 col-md-6 col-xs-12">
			<label >Estado Teléfono Contactado</label>
			<span class="required-indicator">*</span>
			<g:select class="form-control" id="estadoTelefonoContactado" name="estadoTelefonoContactado" from="${['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" optionKey="value" />

		</div>
	</div>
	</div>



<%-- EMPIEZA GESTIÓN --%>
	<br>
		<div id="managementData" style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
			<div class="col-lg-12 col-md-12 col-xs-12">
				<div class="line"><h5>Recopilación de datos</h5></div>
				<div class="form-group col-lg-3 col-md-6 col-xs-12">
					<label>Enviar a: </label>
					<span class="required-indicator"> *</span>
<%-- 				<g:select class="form-control" id="lugarEnvio" name="lugarEnvio" from="${['AGENCIA','DIRECCION']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" />   --%>
					<g:select class="form-control" id="lugarEnvio" name="lugarEnvio" from="${['DIRECCION']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" />

				</div>
				<div id="divAgencia">
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Provincia Agencia</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="provinciaAgencia" name="provinciaAgencia" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="${callcenter.Provincia.list(sort: "nombre", order: "asc")}"  optionValue="${{it.nombre}}"/>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Agencia</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="ciudadAgencia" name="ciudadAgencia" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Motivo Devolución</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="motivoDevolucionesGestion" name="motivoDevolucionesGestion" from="${['NO DESEA RECIBIR TITULAR','DIRECCION INSUFICIENTE','NO COBERTURA/INACCESIBLE','DIRECCION INCORRECTA','CAMBIO DOMICILIO/TRABAJO','MAL SERVICIO DE COURIER','NO HAY QUIEN RECIBA','DESTINO INCORRECTO','ZONA PELIGROSA','CLIENTE VIAJE/VACACIONES','NO ENTREGA HABILITANTES','NO DESEA RECIBIR 3RA PERSONA','NO FIRMA DOCUMENTO HABILITANTE']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Teléfono de Contacto</label>
						<span class="required-indicator">*</span>
						<g:textField maxlength="10" minlength="9" id="telefonoContactoGestion" name="telefonoContactoGestion" class="form-control"/>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Celular de Contacto</label>
						<span class="required-indicator">*</span>
						<g:textField maxlength="10" minlength="10" id="celularContactoGestion" name="celularContactoGestion" class="form-control"/>
					</div>
					<%--
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Email</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" id="emailGestionAgencia" name="emailGestionAgencia"/>
					</div>
					--%>
				</div>

	            <div id="divDireccion">
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Respuesta Tipo: </label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="respuestaTipo" name="respuestaTipo" from="${['ENVIAR A NUEVA DIRECCION','ENVIAR SE COMPLEMENTA DIRECCION','ENVIAR DIRECCION CORRECTA']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Provincia Dirección</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="provinciaDirecion" name="provinciaDirecion" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="${callcenter.Provincia.list(sort: "nombre", order: "asc")}"  optionValue="${{it.nombre}}"/>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Ciudad Dirección</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="ciudadDirecion" name="ciudadDirecion" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Parroquia Domicilio</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="parroquiaDirecion" name="parroquiaDirecion" optionKey="id" noSelection="${['': '-- Seleccione --']}" from="" optionValue="${{it.nombre}}"/>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Teléfono de Contacto</label>
						<span class="required-indicator">*</span>
						<g:textField maxlength="10" minlength="9" id="telefonoContactoGestionDir" name="telefonoContactoGestionDir" class="form-control"/>
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Celular de Contacto</label>
						<span class="required-indicator">*</span>
						<g:textField maxlength="10" minlength="10" id="celularContactoGestionDir" name="celularContactoGestionDir" class="form-control"/>
					</div>
					<%--
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Email</label>
						<span class="required-indicator"> *</span>
						<g:textField class="form-control" id="emailGestionDireccion" name="emailGestionDireccion"/>
					</div>
					--%>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Persona Contacto: </label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="personaContacto" name="personaContacto" from="${['TITULAR','TERCERA PERSONA']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" />
					</div>

					<div id="divTerceraPersona">
						<div class="form-group col-lg-4 col-md-6 col-xs-12">
							<label>Nombre Tercera Persona</label>
							<span class="required-indicator"> *</span>
							<g:textField class="form-control" id="nombreTerceraPersona" name="nombreTerceraPersona"/>
						</div>
						<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Perentesco Tercera Persona</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" optionKey="key" optionValue="value" name="parentescoTerceraPersona" from="${['PADRES': 'PADRES', 'HIJO(A)': 'HIJO(A)', 'ABUELO(A)': 'ABUELO(A)', 'NUERA': 'NUERA', 'YERNO': 'YERNO', 'CUNADO(A)': 'CUNADO(A)',
																																 'NIETO(A)': 'NIETO(A)', 'TIO(A)' : 'TIO(A)', 'SOBRINO(A)' : 'SOBRINO(A)', 'PRIMO(A)' : 'PRIMO(A)', 'HERMANO(A)' : 'HERMANO(A)', 'COMPANERO(A) DE TRABAJO':'COMPANERO(A) DE TRABAJO'
						                                                                                                        , 'CONOCIDO(A)':'CONOCIDO(A)', 'SUEGRO(A)':'SUEGRO(A)']}" noSelection="${['': '-- Seleccione --']}" />
					     </div>
					</div>

					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Horario Entrega: </label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="horarioEntrega" name="horarioEntrega" from="${['MAÑANA','TARDE']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" />
					</div>
					<div class="form-group col-lg-3 col-md-6 col-xs-12">
						<label>Motivo Devolución</label>
						<span class="required-indicator"> *</span>
						<g:select class="form-control" id="motivoDevolucionesGestionDir" name="motivoDevolucionesGestionDir" from="${['NO DESEA RECIBIR TITULAR','DIRECCION INSUFICIENTE','NO COBERTURA/INACCESIBLE','DIRECCION INCORRECTA','CAMBIO DOMICILIO/TRABAJO','MAL SERVICIO DE COURIER','NO HAY QUIEN RECIBA','DESTINO INCORRECTO','ZONA PELIGROSA','CLIENTE VIAJE/VACACIONES','NO ENTREGA HABILITANTES','NO DESEA RECIBIR 3RA PERSONA','NO FIRMA DOCUMENTO HABILITANTE']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" />
					</div>
					<div class="form-group col-lg-4 col-md-6 col-xs-12">
						<label>Calle Principal</label>
						<span class="required-indicator"> *</span>
						<g:textField id="callePrincipalEntrega" name="callePrincipalEntrega" class="form-control" />
					</div>
					<div class="form-group col-lg-4 col-md-6 col-xs-12">
						<label>Nro Casa</label>
						<span class="required-indicator"> *</span>
						<g:textField id="nomenclaturaEntrega" name="nomenclaturaEntrega" class="form-control" />
					</div>
					<div class="form-group col-lg-4 col-md-6 col-xs-12">
						<label>Calle Secundaria</label>
						<span class="required-indicator"> *</span>
						<g:textField id="calleSecundariaEntrega" name="calleSecundariaEntrega" class="form-control" />
					</div>
					<div class="form-group col-lg-12 col-md-12 col-xs-12">
						<label>Referencia Domicilio</label>
						<span class="required-indicator"> *</span>
						<g:textArea class="form-control" id="referenciaEntrega"  name="referenciaEntrega"/>
					</div>
	            </div>

			</div>

	</div>
	<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
        <div class="form-group col-lg-3 col-md-6 col-xs-12">
            <label>Discrepancias: </label>
            <span class="required-indicator"> *</span>
		<g:select class="form-control" id="discrepancias" name="discrepancias" from="${['SI','NO']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" />
	</div>
	<div id="divDiscrepancias">
		<div class="form-group col-lg-3 col-md-6 col-xs-12">
			<label>Detalle Discrepancias: </label>
			<span class="required-indicator"> *</span>
			<g:select class="form-control" id="detalleDiscrepancias" name="detalleDiscrepancias" from="${['CC- NO INFORMA VALORES A PAGAR','CC- NO INFORMA DOCUMENTOS HABILIANTES','CC- CLIENTE NO ACEPTO EL PRODUCTO','CC- CLIENTE MOLESTO','DEL- COURIER NUNCA LO VISITO NI COORDINO ENTREGA','DEL- DEMORA EN LA ENTREGA','DEL- DOCUMENTOS HABILITANTES INCOMPLETOS','DEL- HORARIOS DE ENTREGA','DEL- MALTRATO AL CLIENTE','DEL- POR COBERTURA','OTROS..Cual ?']}" noSelection="${['': '-- Seleccione --']}" optionValue="value" />
		</div>
		<div id="divDetalleDiscrepancias">
			<div class="form-group col-lg-12 col-md-12 col-xs-12">
				<label>Detalle Otros</label>
				<g:textArea class="form-control" name="detalleOtros" id="detalleOtros"/>
			</div>
		</div>
	</div>
	</div>

<%-- FIN GESTION --%>

	<div style="border-radius: 30px" class="panel panel-default col-lg-12 col-md-12 col-xs-12">
		<div class="form-group col-lg-12 col-md-12 col-xs-12">
			<label>Observaciones</label>
			<g:textArea class="form-control" name="observaciones" value="${cliente?.observaciones}"/>
		</div>
	</div>




	<div class="col-lg-12 col-md-12 col-xs-12">
		<g:submitButton id="send" name="btnGuardarCliente" class="btn btn-primary" value="Guardar Gestión" />
	</div>
	<div class="line"></div>
</g:form>

<asset:javascript src="usogeneral/objetos.js" />
<asset:javascript src="gestion/gestionCliente1.js" />
<asset:javascript src="usogeneral/customdatetimepicker.js" />
<asset:javascript src="usogeneral/datetimepicker.js" />
<asset:javascript src="usogeneral/bootstrap-datepicker.min.js" />
<asset:javascript src="usogeneral/customdatepicker.js" />
<asset:javascript src="usogeneral/bootstrap-datepicker.es.min.js" />
<asset:javascript src="usogeneral/moment.min.js" />
<asset:javascript src="usogeneral/daterangepicker.js" />
<asset:stylesheet src="usogeneral/daterangepicker.css" />
<asset:stylesheet src="usogeneral/customdaterangepicker.css" />

<%--<asset:javascript src="gestion/gestionCliente1.js" />
<asset:javascript src="usogeneral/datetimepicker.js" />
<asset:javascript src="usogeneral/customdatetimepicker.js" />
<asset:javascript src="usogeneral/bootstrap-datepicker.min.js" />
<asset:javascript src="usogeneral/customdatepicker.js" />
<asset:javascript src="usogeneral/bootstrap-datepicker.es.min.js" />--%>
