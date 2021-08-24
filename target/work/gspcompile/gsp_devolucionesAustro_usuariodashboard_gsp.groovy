import callcenter.Clientes
import com.pw.security.*
import utilitarios.Util
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_devolucionesAustro_usuariodashboard_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/usuario/dashboard.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
printHtmlPart(1)
printHtmlPart(2)
createTagBody(1, {->
printHtmlPart(3)
invokeTag('captureMeta','sitemesh',8,['gsp_sm_xmlClosingForEmptyTag':("/"),'name':("layout"),'content':("main")],-1)
printHtmlPart(3)
createTagBody(2, {->
createClosureForHtmlPart(4, 3)
invokeTag('captureTitle','sitemesh',9,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',9,[:],2)
printHtmlPart(3)
invokeTag('javascript','asset',10,['src':("usogeneral/moment.min.js")],-1)
printHtmlPart(3)
invokeTag('javascript','asset',11,['src':("usuario/dashboard.js")],-1)
printHtmlPart(5)
})
invokeTag('captureHead','sitemesh',13,[:],1)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(6)
expressionOut.print(createLink(uri:'/usuario/'))
printHtmlPart(7)
expressionOut.print(Util.getOperadoresLogueados())
printHtmlPart(8)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(9)
expressionOut.print(Util.getContactados())
printHtmlPart(10)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(11)
expressionOut.print(Util.getCantidadVentas())
printHtmlPart(12)
expressionOut.print(createLink(uri: '#'))
printHtmlPart(13)
expressionOut.print(Util.getAExitosasMes())
printHtmlPart(14)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(15)
expressionOut.print(Util.getContactadosMes())
printHtmlPart(16)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(17)
expressionOut.print(Util.getContactabilidadMensual())
printHtmlPart(18)
expressionOut.print(createLink(uri:'#'))
printHtmlPart(17)
expressionOut.print(Util.getContactabilidadDiaria())
printHtmlPart(19)
for( _it1472716557 in (inicioSesion) ) {
changeItVariable(_it1472716557)
printHtmlPart(20)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1])
printHtmlPart(21)
expressionOut.print(it[2]?:'Sin cerrar')
printHtmlPart(22)
}
printHtmlPart(23)
for( _it50658177 in (ventasPorUsuario) ) {
changeItVariable(_it50658177)
printHtmlPart(24)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1]?:0)
printHtmlPart(22)
}
printHtmlPart(25)
for( _it1008456627 in (tablaResult) ) {
changeItVariable(_it1008456627)
printHtmlPart(20)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1])
printHtmlPart(21)
expressionOut.print(it[2]?:0)
printHtmlPart(21)
expressionOut.print(it[3]?:0)
printHtmlPart(22)
}
printHtmlPart(26)
expressionOut.print(totalGestionados)
printHtmlPart(27)
expressionOut.print(totalContactados)
printHtmlPart(27)
expressionOut.print(totalNoContactados)
printHtmlPart(28)
for( _it1189587865 in (tablaResult1) ) {
changeItVariable(_it1189587865)
printHtmlPart(20)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1])
printHtmlPart(21)
expressionOut.print(it[2]?:0)
printHtmlPart(22)
}
printHtmlPart(29)
for( _it330493485 in (tablaResultPlusWireless) ) {
changeItVariable(_it330493485)
printHtmlPart(20)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1]?:0)
printHtmlPart(21)
expressionOut.print(it[2]?:0)
printHtmlPart(21)
expressionOut.print(it[3]?:0)
printHtmlPart(22)
}
printHtmlPart(26)
expressionOut.print(totalGestionadosPlusWireless)
printHtmlPart(27)
expressionOut.print(totalContactadosPlusWireless)
printHtmlPart(27)
expressionOut.print(totalExitososPlusWireless)
printHtmlPart(30)
for( _it1211291253 in (tablaResultPureCloud) ) {
changeItVariable(_it1211291253)
printHtmlPart(20)
expressionOut.print(it[0])
printHtmlPart(21)
expressionOut.print(it[1]?:0)
printHtmlPart(21)
expressionOut.print(it[2]?:0)
printHtmlPart(21)
expressionOut.print(it[3]?:0)
printHtmlPart(22)
}
printHtmlPart(26)
expressionOut.print(totalGestionadosPureCloud)
printHtmlPart(27)
expressionOut.print(totalContactadosPureCloud)
printHtmlPart(27)
expressionOut.print(totalExitososPureCloud)
printHtmlPart(31)
})
invokeTag('captureBody','sitemesh',432,[:],1)
printHtmlPart(32)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1621533075068L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
