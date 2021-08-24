import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_devolucionesAustro_gestiongestionCliente_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/gestion/gestionCliente.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
invokeTag('captureMeta','sitemesh',1,['gsp_sm_xmlClosingForEmptyTag':(""),'name':("layout"),'content':("main")],-1)
printHtmlPart(0)
printHtmlPart(0)
invokeTag('captureMeta','sitemesh',3,['gsp_sm_xmlClosingForEmptyTag':("/"),'http-equiv':("Content-Type"),'content':("text/html; charset=utf-8")],-1)
printHtmlPart(0)
invokeTag('captureMeta','sitemesh',4,['gsp_sm_xmlClosingForEmptyTag':("/"),'charset':("utf-8")],-1)
printHtmlPart(1)
invokeTag('captureMeta','sitemesh',6,['gsp_sm_xmlClosingForEmptyTag':(""),'http-equiv':("content-type"),'content':("application/vnd.ms-excel; charset=UTF-8")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',7,['src':("usogeneral/bootstrap-datepicker.min.css")],-1)
printHtmlPart(2)
createTagBody(1, {->
createClosureForHtmlPart(3, 2)
invokeTag('captureTitle','sitemesh',10,[:],2)
})
invokeTag('wrapTitleTag','sitemesh',10,[:],1)
printHtmlPart(4)
invokeTag('stylesheet','asset',12,['src':("usogeneral/datetimepicker.css")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',13,['src':("gestion/gestionCliente.css")],-1)
printHtmlPart(5)
if(true && (cliente.registroExitoso == 'SI')) {
printHtmlPart(6)
}
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(7)
expressionOut.print(cliente.nombre)
printHtmlPart(8)
expressionOut.print(cliente.identificacion)
printHtmlPart(9)
if(true && (cliente.producto && cliente.producto.trim() != '')) {
printHtmlPart(10)
expressionOut.print(cliente.producto)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.guia && cliente.guia.trim() != '')) {
printHtmlPart(13)
expressionOut.print(cliente.guia)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.inventario && cliente.inventario.trim() != '')) {
printHtmlPart(14)
expressionOut.print(cliente.inventario)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.codigoUnico && cliente.codigoUnico.trim() != '')) {
printHtmlPart(15)
expressionOut.print(cliente.codigoUnico)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.motivoDevolucion && cliente.motivoDevolucion.trim() != '')) {
printHtmlPart(16)
expressionOut.print(cliente.motivoDevolucion)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.direccion && cliente.direccion.trim() != '')) {
printHtmlPart(17)
expressionOut.print(cliente.direccion)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.x15Producto && cliente.x15Producto.trim() != '')) {
printHtmlPart(18)
expressionOut.print(cliente.x15Producto)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.ciudad && cliente.ciudad.trim() != '')) {
printHtmlPart(19)
expressionOut.print(cliente.ciudad)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.dhnomp && cliente.dhnomp.trim() != '')) {
printHtmlPart(20)
expressionOut.print(cliente.dhnomp)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.cedPrincipal && cliente.cedPrincipal.trim() != '')) {
printHtmlPart(21)
expressionOut.print(cliente.cedPrincipal)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.edad && cliente.edad.trim() != '')) {
printHtmlPart(22)
expressionOut.print(cliente.edad)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.callCenter && cliente.callCenter.trim() != '')) {
printHtmlPart(23)
expressionOut.print(cliente.callCenter)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.callCenter && cliente.callCenter.trim() != '')) {
printHtmlPart(23)
expressionOut.print(cliente.callCenter)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.direccionDomilio1 && cliente.direccionDomilio1.trim() != '')) {
printHtmlPart(24)
expressionOut.print(cliente.direccionDomilio1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.direccionDomilio2 && cliente.direccionDomilio2.trim() != '')) {
printHtmlPart(25)
expressionOut.print(cliente.direccionDomilio2)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.direccionDomilio3 && cliente.direccionDomilio3.trim() != '')) {
printHtmlPart(26)
expressionOut.print(cliente.direccionDomilio3)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.direccionTrabajo1 && cliente.direccionTrabajo1.trim() != '')) {
printHtmlPart(27)
expressionOut.print(cliente.direccionTrabajo1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.direccionTrabajo2 && cliente.direccionTrabajo2.trim() != '')) {
printHtmlPart(28)
expressionOut.print(cliente.direccionTrabajo2)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.direccionTrabajo3 && cliente.direccionTrabajo3.trim() != '')) {
printHtmlPart(29)
expressionOut.print(cliente.direccionTrabajo3)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.numeroOperaciones && cliente.numeroOperaciones.trim() != '' && cliente.numeroOperaciones.trim() > '1')) {
printHtmlPart(30)
expressionOut.print(cliente.numeroOperaciones)
printHtmlPart(31)
for( _it311572971 in (utilitarios.Util.getNumeroOperacionesArray(cliente.cedPrincipal.toString(), cliente.nombreBase)) ) {
changeItVariable(_it311572971)
printHtmlPart(32)
expressionOut.print(createLink(uri:'/gestion/gestionCliente/'))
expressionOut.print(it)
printHtmlPart(33)
expressionOut.print(it)
printHtmlPart(34)
}
printHtmlPart(35)
}
printHtmlPart(36)
if(true && (cliente.telefono1)) {
printHtmlPart(37)
expressionOut.print(cliente.telefono1)
printHtmlPart(38)
invokeTag('select','g',196,['class':("form-control"),'id':("estadoTel1"),'name':("estadoTel1"),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value"),'optionKey':("value")],-1)
printHtmlPart(39)
}
printHtmlPart(12)
if(true && (cliente.telefono2 && cliente.telefono2.trim() != '')) {
printHtmlPart(40)
expressionOut.print(cliente.telefono2)
printHtmlPart(38)
invokeTag('select','g',211,['class':("form-control"),'id':("estadoTel2"),'name':("estadoTel2"),'noSelection':(['': '-- Seleccione --']),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO'])],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.telefono3 && cliente.telefono3.trim() != '')) {
printHtmlPart(41)
expressionOut.print(cliente.telefono3)
printHtmlPart(38)
invokeTag('select','g',225,['class':("form-control"),'id':("estadoTel3"),'name':("estadoTel3"),'noSelection':(['': '-- Seleccione --']),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO'])],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.telefono4 && cliente.telefono4.trim() != '')) {
printHtmlPart(42)
expressionOut.print(cliente.telefono4)
printHtmlPart(38)
invokeTag('select','g',232,['class':("form-control"),'id':("estadoTel4"),'name':("estadoTel4"),'noSelection':(['': '-- Seleccione --']),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO'])],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.telefono5 && cliente.telefono5.trim() != '')) {
printHtmlPart(43)
expressionOut.print(cliente.telefono5)
printHtmlPart(38)
invokeTag('select','g',239,['class':("form-control"),'id':("estadoTel5"),'name':("estadoTel5"),'noSelection':(['': '-- Seleccione --']),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO'])],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.telefono6 && cliente.telefono6.trim() != '')) {
printHtmlPart(44)
expressionOut.print(cliente.telefono6)
printHtmlPart(38)
invokeTag('select','g',246,['class':("form-control"),'id':("estadoTel6"),'name':("estadoTel6"),'noSelection':(['': '-- Seleccione --']),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO'])],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.telefono7 && cliente.telefono7.trim() != '')) {
printHtmlPart(45)
expressionOut.print(cliente.telefono7)
printHtmlPart(38)
invokeTag('select','g',253,['class':("form-control"),'id':("estadoTel7"),'name':("estadoTel7"),'noSelection':(['': '-- Seleccione --']),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO'])],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.telefono8 && cliente.telefono8.trim() != '')) {
printHtmlPart(46)
expressionOut.print(cliente.telefono8)
printHtmlPart(38)
invokeTag('select','g',260,['class':("form-control"),'id':("estadoTel8"),'name':("estadoTel8"),'noSelection':(['': '-- Seleccione --']),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO'])],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.telefono9 && cliente.telefono9.trim() != '')) {
printHtmlPart(47)
expressionOut.print(cliente.telefono9)
printHtmlPart(38)
invokeTag('select','g',267,['class':("form-control"),'id':("estadoTel9"),'name':("estadoTel9"),'noSelection':(['': '-- Seleccione --']),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO'])],-1)
printHtmlPart(11)
}
printHtmlPart(12)
if(true && (cliente.telefono10 && cliente.telefono10.trim() != '')) {
printHtmlPart(48)
expressionOut.print(cliente.telefono10)
printHtmlPart(38)
invokeTag('select','g',274,['class':("form-control"),'id':("estadoTel10"),'name':("estadoTel10"),'noSelection':(['': '-- Seleccione --']),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO'])],-1)
printHtmlPart(11)
}
printHtmlPart(49)
if(true && (cliente.codigoAsignacion && cliente.codigoAsignacion.trim() != '')) {
printHtmlPart(50)
expressionOut.print(cliente.codigoAsignacion)
printHtmlPart(11)
}
printHtmlPart(51)
expressionOut.print(cliente.id)
printHtmlPart(52)
invokeTag('select','g',283,['class':("form-control"),'name':("estado"),'id':("status"),'from':(callcenter.Estado.list()),'optionKey':("id"),'optionValue':({it.nombre	}),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(53)
invokeTag('select','g',286,['class':("form-control"),'name':("substatus"),'id':("substatus"),'from':(""),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(54)
invokeTag('select','g',290,['id':("subSubStatus"),'class':("form-control"),'name':("subSubStatus"),'from':(""),'optionKey':("id")],-1)
printHtmlPart(55)
invokeTag('textArea','g',290,['class':("form-control"),'name':("motivoNoAceptaSeguro"),'value':("")],-1)
printHtmlPart(56)
invokeTag('select','g',301,['class':("form-control"),'name':("agendamiento"),'id':("agendamiento"),'from':(['AGENDAR PARA MI':'AGENDAR PARA MI', 'AGENDAR PARA CUALQUIERA':'AGENDAR PARA CUALQUIERA']),'optionKey':("key"),'optionValue':("value"),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(57)
invokeTag('textField','g',306,['id':("recall"),'name':("recall"),'class':("recall form-control")],-1)
printHtmlPart(58)
invokeTag('textField','g',315,['maxlength':("10"),'minlength':("9"),'id':("telefonoContactado"),'name':("telefonoContactado"),'class':("form-control")],-1)
printHtmlPart(59)
invokeTag('select','g',328,['class':("form-control"),'id':("estadoTelefonoContactado"),'name':("estadoTelefonoContactado"),'from':(['C CLIENTE DE VIAJE','C CLIENTE FALLECIDO','C CLIENTE VIVE FUERA DEL PAIS','C CONTESTA TERCERO','C TELEFONO DE REFERENCIA','C CLIENTE','N CLIENTE SIN TELEFONO','N GRABADORA','N NO CONTESTA','N TELEFONO AVERIADO','N TELEFONO EQUIVOCADO_NO ASIGNADO','N TONO OCUPADO','N NO MARCADO']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value"),'optionKey':("value")],-1)
printHtmlPart(60)
invokeTag('select','g',340,['class':("form-control"),'id':("lugarEnvio"),'name':("lugarEnvio"),'from':(['DIRECCION']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value")],-1)
printHtmlPart(61)
invokeTag('select','g',352,['class':("form-control"),'id':("provinciaAgencia"),'name':("provinciaAgencia"),'optionKey':("id"),'noSelection':(['': '-- Seleccione --']),'from':(callcenter.Provincia.list(sort: "nombre", order: "asc")),'optionValue':({it.nombre})],-1)
printHtmlPart(62)
invokeTag('select','g',358,['class':("form-control"),'id':("ciudadAgencia"),'name':("ciudadAgencia"),'optionKey':("id"),'noSelection':(['': '-- Seleccione --']),'from':(""),'optionValue':({it.nombre})],-1)
printHtmlPart(63)
invokeTag('select','g',369,['class':("form-control"),'id':("motivoDevolucionesGestion"),'name':("motivoDevolucionesGestion"),'from':(['NO DESEA RECIBIR TITULAR','DIRECCION INSUFICIENTE','NO COBERTURA/INACCESIBLE','DIRECCION INCORRECTA','CAMBIO DOMICILIO/TRABAJO','MAL SERVICIO DE COURIER','NO HAY QUIEN RECIBA','DESTINO INCORRECTO','ZONA PELIGROSA','CLIENTE VIAJE/VACACIONES','NO ENTREGA HABILITANTES','NO DESEA RECIBIR 3RA PERSONA','NO FIRMA DOCUMENTO HABILITANTE']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value")],-1)
printHtmlPart(64)
invokeTag('textField','g',374,['maxlength':("10"),'minlength':("9"),'id':("telefonoContactoGestion"),'name':("telefonoContactoGestion"),'class':("form-control")],-1)
printHtmlPart(65)
invokeTag('textField','g',379,['maxlength':("10"),'minlength':("10"),'id':("celularContactoGestion"),'name':("celularContactoGestion"),'class':("form-control")],-1)
printHtmlPart(66)
invokeTag('select','g',385,['class':("form-control"),'id':("respuestaTipo"),'name':("respuestaTipo"),'from':(['ENVIAR A NUEVA DIRECCION','ENVIAR SE COMPLEMENTA DIRECCION','ENVIAR DIRECCION CORRECTA']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value")],-1)
printHtmlPart(67)
invokeTag('select','g',391,['class':("form-control"),'id':("provinciaDirecion"),'name':("provinciaDirecion"),'optionKey':("id"),'noSelection':(['': '-- Seleccione --']),'from':(callcenter.Provincia.list(sort: "nombre", order: "asc")),'optionValue':({it.nombre})],-1)
printHtmlPart(68)
invokeTag('select','g',392,['class':("form-control"),'id':("ciudadDirecion"),'name':("ciudadDirecion"),'optionKey':("id"),'noSelection':(['': '-- Seleccione --']),'from':(""),'optionValue':({it.nombre})],-1)
printHtmlPart(69)
invokeTag('select','g',397,['class':("form-control"),'id':("parroquiaDirecion"),'name':("parroquiaDirecion"),'optionKey':("id"),'noSelection':(['': '-- Seleccione --']),'from':(""),'optionValue':({it.nombre})],-1)
printHtmlPart(64)
invokeTag('textField','g',402,['maxlength':("10"),'minlength':("9"),'id':("telefonoContactoGestionDir"),'name':("telefonoContactoGestionDir"),'class':("form-control")],-1)
printHtmlPart(65)
invokeTag('textField','g',408,['maxlength':("10"),'minlength':("10"),'id':("celularContactoGestionDir"),'name':("celularContactoGestionDir"),'class':("form-control")],-1)
printHtmlPart(70)
invokeTag('select','g',417,['class':("form-control"),'id':("personaContacto"),'name':("personaContacto"),'from':(['TITULAR','TERCERA PERSONA']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value")],-1)
printHtmlPart(71)
invokeTag('textField','g',421,['class':("form-control"),'id':("nombreTerceraPersona"),'name':("nombreTerceraPersona")],-1)
printHtmlPart(72)
invokeTag('select','g',432,['class':("form-control"),'optionKey':("key"),'optionValue':("value"),'name':("parentescoTerceraPersona"),'from':(['PADRES': 'PADRES', 'HIJO(A)': 'HIJO(A)', 'ABUELO(A)': 'ABUELO(A)', 'NUERA': 'NUERA', 'YERNO': 'YERNO', 'CUNADO(A)': 'CUNADO(A)',
																																 'NIETO(A)': 'NIETO(A)', 'TIO(A)' : 'TIO(A)', 'SOBRINO(A)' : 'SOBRINO(A)', 'PRIMO(A)' : 'PRIMO(A)', 'HERMANO(A)' : 'HERMANO(A)', 'COMPANERO(A) DE TRABAJO':'COMPANERO(A) DE TRABAJO'
						                                                                                                        , 'CONOCIDO(A)':'CONOCIDO(A)', 'SUEGRO(A)':'SUEGRO(A)']),'noSelection':(['': '-- Seleccione --'])],-1)
printHtmlPart(73)
invokeTag('select','g',437,['class':("form-control"),'id':("horarioEntrega"),'name':("horarioEntrega"),'from':(['MAÑANA','TARDE']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value")],-1)
printHtmlPart(63)
invokeTag('select','g',451,['class':("form-control"),'id':("motivoDevolucionesGestionDir"),'name':("motivoDevolucionesGestionDir"),'from':(['NO DESEA RECIBIR TITULAR','DIRECCION INSUFICIENTE','NO COBERTURA/INACCESIBLE','DIRECCION INCORRECTA','CAMBIO DOMICILIO/TRABAJO','MAL SERVICIO DE COURIER','NO HAY QUIEN RECIBA','DESTINO INCORRECTO','ZONA PELIGROSA','CLIENTE VIAJE/VACACIONES','NO ENTREGA HABILITANTES','NO DESEA RECIBIR 3RA PERSONA','NO FIRMA DOCUMENTO HABILITANTE']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value")],-1)
printHtmlPart(74)
invokeTag('textField','g',454,['id':("callePrincipalEntrega"),'name':("callePrincipalEntrega"),'class':("form-control")],-1)
printHtmlPart(75)
invokeTag('textField','g',460,['id':("nomenclaturaEntrega"),'name':("nomenclaturaEntrega"),'class':("form-control")],-1)
printHtmlPart(76)
invokeTag('textField','g',465,['id':("calleSecundariaEntrega"),'name':("calleSecundariaEntrega"),'class':("form-control")],-1)
printHtmlPart(77)
invokeTag('textArea','g',466,['class':("form-control"),'id':("referenciaEntrega"),'name':("referenciaEntrega")],-1)
printHtmlPart(78)
invokeTag('select','g',472,['class':("form-control"),'id':("discrepancias"),'name':("discrepancias"),'from':(['SI','NO']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value")],-1)
printHtmlPart(79)
invokeTag('select','g',480,['class':("form-control"),'id':("detalleDiscrepancias"),'name':("detalleDiscrepancias"),'from':(['CC- NO INFORMA VALORES A PAGAR','CC- NO INFORMA DOCUMENTOS HABILIANTES','CC- CLIENTE NO ACEPTO EL PRODUCTO','CC- CLIENTE MOLESTO','DEL- COURIER NUNCA LO VISITO NI COORDINO ENTREGA','DEL- DEMORA EN LA ENTREGA','DEL- DOCUMENTOS HABILITANTES INCOMPLETOS','DEL- HORARIOS DE ENTREGA','DEL- MALTRATO AL CLIENTE','DEL- POR COBERTURA','OTROS..Cual ?']),'noSelection':(['': '-- Seleccione --']),'optionValue':("value")],-1)
printHtmlPart(80)
invokeTag('textArea','g',480,['class':("form-control"),'name':("detalleOtros"),'id':("detalleOtros")],-1)
printHtmlPart(81)
invokeTag('textArea','g',485,['class':("form-control"),'name':("observaciones"),'value':(cliente?.observaciones)],-1)
printHtmlPart(82)
invokeTag('submitButton','g',490,['id':("send"),'name':("btnGuardarCliente"),'class':("btn btn-primary"),'value':("Guardar Gestión")],-1)
printHtmlPart(83)
})
invokeTag('form','g',490,['action':("guardarCliente")],1)
printHtmlPart(4)
invokeTag('javascript','asset',491,['src':("usogeneral/objetos.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',492,['src':("gestion/gestionCliente1.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',494,['src':("usogeneral/customdatetimepicker.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',495,['src':("usogeneral/datetimepicker.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',495,['src':("usogeneral/bootstrap-datepicker.min.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',497,['src':("usogeneral/customdatepicker.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',499,['src':("usogeneral/bootstrap-datepicker.es.min.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',500,['src':("usogeneral/moment.min.js")],-1)
printHtmlPart(0)
invokeTag('javascript','asset',500,['src':("usogeneral/daterangepicker.js")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',502,['src':("usogeneral/daterangepicker.css")],-1)
printHtmlPart(0)
invokeTag('stylesheet','asset',507,['src':("usogeneral/customdaterangepicker.css")],-1)
printHtmlPart(84)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1629731730569L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
