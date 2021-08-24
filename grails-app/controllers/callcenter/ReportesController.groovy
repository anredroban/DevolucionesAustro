package callcenter

//import javafx.scene.control.Cell
import jxl.Workbook
import jxl.WorkbookSettings
import jxl.format.Alignment
import jxl.format.Border
import jxl.format.BorderLineStyle
import jxl.format.Colour
import jxl.format.VerticalAlignment
import jxl.write.Label
import jxl.write.WritableFont
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import telephony.BreakTime
import utilitarios.ExcelUtils


import com.pw.security.*
import groovy.sql.Sql
import org.hibernate.criterion.CriteriaSpecification
import utilitarios.Util

import java.text.DecimalFormat
import java.text.SimpleDateFormat

//import pl.touk.excel.export.WebXlsxExporter

class ReportesController {

	static beforeInterceptor = {
		String accion = actionUri;
		if(!accion.equals("/usuario/login") && !accion.equals("/usuario/logout")){
			if(!session.user){
				redirect(uri: "/usuario/login");
				return false;
			}else{
				boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
				if(!tienePermiso){
					render "No tienes permiso para ingresar a este sitio-> "+accion;
				}
			}
		}
	}


/**
 * @author Andres Redroban
 * Genera trama de creditos aceptados por los clientes de BP
 * @param
 * @return
 */
	def bitacoraDevoluciones(){
		Date fechaActual = new Date()
		SimpleDateFormat objSDF = new SimpleDateFormat('yyyy-MM-dd')
		String fecha = objSDF.format(fechaActual)
		if(params.nombreBase){

			//Obtenemos los datos
//			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			//ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1,3)")
			//ArrayList<SubSubestado> crazadas = Subestado.executeQuery("from Subestado where id in (3)")
			 def nombresBase = params.list("nombreBase")
			def condiciones = [nombresBase: nombresBase]
			//def condicionesCruzadas = [fechaInicio: fechas[0], fechaFin: fechas[1], subestados: subestados]
			String sqlPrincipales = "from Clientes where nombreBase in (:nombresBase) and fechaGestion is not null"
			def principalesList = Clientes.executeQuery(sqlPrincipales, condiciones)
			//String sqlCruzadas = "from Clientes where subestadoGestion in (:subestados) and fechaGestion between :fechaInicio and :fechaFin and cast(montoAceptadoGestion as integer) > 10000"
			//def cruzadasList = Clientes.executeQuery(sqlCruzadas, condicionesCruzadas)
			DecimalFormat df = new DecimalFormat("#.00")

			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("RESULTADOS GESTION", 0)
			//workbook.createSheet("RESUMEN", 1)
			workbook.createSheet("SOLICITADO INTERDIN", 1)
			WritableFont cellFont = new WritableFont(WritableFont.TAHOMA, 10, WritableFont.BOLD)
			WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
			WritableSheet sheetPrincipales = workbook.getSheet(0)
			WritableSheet sheetInterdin = workbook.getSheet(1)
			String[] headersPrincipales = ["FECHA GRABACION", "STATUS (P)", "PRODUCTO (D)", "COD_PRO", "TARJETA (F)", "SECUENCIAL (J)", "CODIGO UNICO",
										   "FECHA", "12_CEDULA", "NOMBRE (G)", "4_TELEFONO 1", "5_TELEFONO 2", "6_TELEFONO 3", "MOTIVO DE DEV. (S)", "CIUDAD (N)","16", "17_CANAL 1",
					                       "24_CODRET", "25_BOLNAC", "GB", "CASO", "GESTIONES", "TIPO", "9_DHNOMP", "13_DHCUENTA", "SECUENCIAL (J2)", "CEDPRI","3_DIRECCION",
										   "20_DIRECCION CTA", "21_TELEFONO 1 CTA", "22_TELEFONO 2 CTA", "23_TELEFONO 3 CTA","15_PRODUCTO", "REGESTION", "10_DHSEGM", "ENVIAR A:", "PROVINCIA", "CIUDAD",
										   "RESPUESTA TIPO","PERSONA CONTACTO", "NOMBRE 3RA PERSONA",  "PARENTEZCO 3RA PERSONA", "HORARIO ENTREGA", "DIRECCION DE ENTREGA", "NOMBRE AGENCIA (ENTREGA)",
										   "TELEFONO DE CONTACTO", "CELULAR DE CONTACTO", "EMAIL", "DISCREPANCIAS", "DETALLE DISCREPENCIAS", "DETALLE OTROS", "MOTIVO DE DEV (S)",
										   "ESTADO","SUB ESTADO", "SUB SUB ESTADO", "OBSERVACIONES", "NOMBRE BASE", "ASESOR", "INTENTOS", "FECHA DE GESTION", "MES BASE", "SEMANA",
										   "% CONTACTABILIDAD", "% EFECTIVIDAD", "% CU4", "LIMITE CONTACTABILIDAD", "LIMITE EFECTIVIDAD", "LIMITE ENVIO AGENCIA", "% ENVIO AGENCIA", "REGISTROS POR BASE",
										   "REGISTROS ENVIO AGENCIA", "CLIENTES", "TOTAL MES", "CALL CENTER", "ID SISTEMA","GUIA","INVENTARIO"]
			String[] headersInterdin = ["STATUS (P)", "PRODUCTO (D)", "COD_PRO", "TARJETA (F)", "SECUENCIAL (J)", "CODIGO UNICO",
										"FECHA", "12_CEDULA", "NOMBRE (G)", "4_TELEFONO 1", "5_TELEFONO 2", "6_TELEFONO 3", "MOTIVO DE DEV. (S)", "CIUDAD (N)","16", "17_CANAL 1",
										"24_CODRET", "25_BOLNAC", "GB", "CASO", "GESTIONES", "TIPO", "9_DHNOMP", "13_DHCUENTA", "SECUENCIAL (J2)", "CEDPRI", "PEPS", "ANVITN (4)",
										"MEVITN (5)","3_DIRECCION", "20_DIRECCION CTA", "21_TELEFONO 1 CTA", "22_TELEFONO 2 CTA", "23_TELEFONO 3 CTA","15_PRODUCTO", "REGESTION",
										"10_DHSEGM", "CARGO", "Direct", "Direct", "Direct", "CIUDAD TEXTO", "TELEFONO CONTACTO", "GESTION", "OBSERVACION", "CELULAR", "MAIL", "PERSONA CONTACTO",
										"HORARIO ENTREGA", "TELEFONO", "TELEFONO 2", "CENTRO DE COSTOS", "NOMBRE DE LA AGENCIA", "DISCREPANCIAS", "OBSERVACIONES", "OTROS CUAL?"]
			ExcelUtils.addCells(headersPrincipales, sheetPrincipales, 0, Colour.AQUA, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
			ExcelUtils.addCells(headersInterdin, sheetInterdin, 0, Colour.GOLD, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
			int contactadosBase = Clientes.executeQuery("from Clientes where estadoGestion = 'CONTACTADO' and nombreBase in (:nombresBase) ", [nombresBase: nombresBase]).size()
			int CU1Base = Clientes.executeQuery("from Clientes where subestadoGestion = 1 and nombreBase in (:nombresBase) ", [nombresBase: nombresBase]).size()
			int CU4Base = Clientes.executeQuery("from Clientes where subestadoGestion = 4 and nombreBase in (:nombresBase) ", [nombresBase: nombresBase]).size()
			int totalBase = Clientes.executeQuery("from Clientes where nombreBase in (:nombresBase) ", [nombresBase: nombresBase]).size()
			int CU1BaseAgencia = Clientes.executeQuery("from Clientes where subestadoGestion = 1 and lugarEnvio = 'AGENCIA' and nombreBase in (:nombresBase) ", [nombresBase: nombresBase]).size()
			String pctContactabilidad = df.format(Double.parseDouble(contactadosBase.toString()) / Double.parseDouble(totalBase.toString()) * 100)
			String pctEfectividad = df.format(Double.parseDouble(CU1Base.toString()) / Double.parseDouble(totalBase.toString()) * 100)
			String pctCU4= df.format(Double.parseDouble(CU4Base.toString()) / Double.parseDouble(totalBase.toString()) * 100)
			String pctAgencia= df.format(Double.parseDouble(CU1BaseAgencia.toString()) / Double.parseDouble(totalBase.toString()) * 100)
			def dbNombreSubestado = MotivosnoAceptaSeguro.executeQuery("from MotivosnoAceptaSeguro")
			for (int i = 0; i < principalesList.size(); i++){
				String direccionConcat = ""
				String nombreAgencia = ""
				String nombreSubSubestado = ""
				String emailGestion = ""
				String[] campos = new String[headersPrincipales.length]
				Clientes princ = principalesList.get(i)
				campos[0] = princ.fechaGrabacion
				campos[1] = princ.status
				campos[2] = princ.producto
				campos[3] = princ.codProducto
				campos[4] = princ.tarjeta
				campos[5] = princ.secuencial
				campos[6] = princ.codigoUnico
				campos[7] = princ.fecha
				campos[8] = princ.identificacion
				campos[9] = princ.nombre
				campos[10] = princ.telefono1
				campos[11] = princ.telefono2
				campos[12] = princ.telefono3
				campos[13] = princ.motivoDevolucion
				campos[14] = princ.ciudad
				campos[15] = princ.x16
				campos[16] = princ.canal
				campos[17] = princ.codRet
				campos[18] = princ.bolNac
				campos[19] = princ.gb
				campos[20] = princ.caso
				campos[21] = princ.gestiones
				campos[22] = princ.tipo
				campos[23] = princ.dhnomp
				campos[24] = princ.dhCuenta
				campos[25] = princ.secuencialJ
				campos[26] = princ.cedPrincipal
				campos[27] = princ.direccion
				campos[28] = princ.direccionCuenta
				campos[29] = princ.telefono4
				campos[30] = princ.telefono5
				campos[31] = princ.telefono6
				campos[32] = princ.x15Producto
				campos[33] = princ.regestion
				campos[34] = princ.dhsegm
				campos[35] = princ.lugarEnvio
				if (princ.lugarEnvio.toString().equalsIgnoreCase("AGENCIA")){
					campos[36] = princ.provinciaAgencia
					campos[37] = Agencia.findByNombre(princ.ciudadAgencia).direccionAgencia  //princ.ciudadAgencia
					nombreAgencia = princ.ciudadAgencia
				}else{
					campos[36] = princ.provinciaDirecion
					campos[37] = princ.ciudadDirecion
					nombreAgencia = ""
				}
				if (princ.lugarEnvio.toString().equalsIgnoreCase("DIRECCION")){
					campos[38] = princ.respuestaTipo
					direccionConcat = princ.provinciaDirecion + " " + princ.ciudadDirecion + " " + princ.parroquiaDirecion + " " + princ.callePrincipalEntrega + " " + princ.nomenclaturaEntrega + " " + princ.calleSecundariaEntrega + " " + princ.referenciaEntrega

				}else{
					// campos[38] = MotivosnoAceptaSeguro.findByNombreSubSubEstado(princ.subSubEstado).nombre //princ.subSubEstado
					direccionConcat = ""

				}
				if (princ.lugarEnvio.toString().equalsIgnoreCase("AGENCIA")){
					campos[38] = "SOCIO SOLICITA ENVIO AGENCIA"
				}
				for (int r = 0; r < dbNombreSubestado.size(); r++){
					MotivosnoAceptaSeguro mot = dbNombreSubestado.get(r)
					if(princ.subSubEstado == mot.nombreSubSubEstado){
						campos[38] = mot.nombre //princ.subSubEstado
					}
				}
				campos[39] = princ.personaContacto
				campos[40] = princ.nombreTerceraPersona
				campos[41] = princ.parentescoTerceraPersona
				campos[42] = princ.horarioEntrega
				campos[43] = direccionConcat
				campos[44] = nombreAgencia
				campos[45] = princ.telefonoContactoGestion
				campos[46] = princ.celularContactoGestion
				// emailGestion
				if (princ.emailGestion.toString().equalsIgnoreCase("notiene@notiene.com")){
					campos[47] =""
				}else {
					campos[47] = princ.emailGestion
				}
				campos[48] = princ.discrepancias
				campos[49] = princ.detalleDiscrepancias
				campos[50] = princ.detalleOtros
				campos[51] = princ.motivoDevolucionesGestion
				campos[52] = princ.estadoGestion
				campos[53] = princ.subestadoGestion.nombre
				campos[54] = princ.subSubEstado
				campos[55] = princ.observaciones
				campos[56] = princ.nombreBase
				campos[57] = princ.nombreVendedor
				campos[58] = princ.intentos
				campos[59] = princ.fechaGestion.toString()
				campos[60] = ""
				campos[61] = "SEMANA 1"
				campos[62] = pctContactabilidad.replace(".",",")
				campos[63] = pctEfectividad.replace(".",",")
				campos[64] = pctCU4.replace(".",",")
				campos[65] = "75%"
				campos[66] = "52,5%"
				campos[67] = "0,08%"
				campos[68] = pctAgencia.replace(".",",")
				campos[69] = totalBase.toString()
				campos[70] = CU1BaseAgencia.toString()
				campos[71] = ""
				campos[72] = ""
				campos[73] = ""
				campos[74] = princ.id
				campos[75] = princ.guia
				campos[76] = princ.inventario

				ExcelUtils.addCells(campos, sheetPrincipales, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}
			for (int j = 0; j < principalesList.size(); j++){
				String direccionConcat2 = ""
				String ciudadGestion = ""
				String ciudadAgencia = ""
				String campoGestion = ""
				String nombrePersona = ""
				String[] campos2 = new String[headersInterdin.length]
				Clientes princ2 = principalesList.get(j)
				campos2[0] = princ2.status
				campos2[1] = princ2.producto
				campos2[2] = princ2.codProducto
				campos2[3] = princ2.tarjeta
				campos2[4] = princ2.secuencial
				campos2[5] = princ2.codigoUnico
				campos2[6] = princ2.fecha
				campos2[7] = princ2.identificacion
				campos2[8] = princ2.nombre
				campos2[9] = princ2.telefono1
				campos2[10] = princ2.telefono2
				campos2[11] = princ2.telefono3
				campos2[12] = princ2.motivoDevolucion
				campos2[13] = princ2.ciudad
				campos2[14] = princ2.x16
				campos2[15] = princ2.canal
				campos2[16] = princ2.codRet
				campos2[17] = princ2.bolNac
				campos2[18] = princ2.gb
				campos2[19] = princ2.caso
				campos2[20] = princ2.gestiones
				campos2[21] = princ2.tipo
				campos2[22] = princ2.dhnomp
				campos2[23] = princ2.dhCuenta
				campos2[23] = princ2.dhCuenta
				campos2[24] = princ2.secuencialJ
				campos2[25] = princ2.cedPrincipal
				campos2[26] = ""
				campos2[27] = ""
				campos2[28] = ""
				campos2[29] = princ2.direccion
				campos2[30] = princ2.direccionCuenta
				campos2[31] = princ2.telefono4
				campos2[32] = princ2.telefono5
				campos2[33] = princ2.telefono6
				campos2[34] = princ2.producto
				campos2[35] = princ2.regestion
				campos2[36] = princ2.dhsegm
				if (princ2.lugarEnvio.toString().equalsIgnoreCase("DIRECCION")){
					direccionConcat2 = princ2.callePrincipalEntrega + " " + princ2.nomenclaturaEntrega + " " + princ2.calleSecundariaEntrega + " " + princ2.referenciaEntrega
					ciudadGestion = princ2.ciudadDirecion
					campoGestion = princ2.respuestaTipo

				}else{
					direccionConcat2 = ""
					ciudadGestion = princ2.ciudadAgencia
					campoGestion = princ2.subSubEstado
				}
				campos2[37] = ""
				campos2[38] = direccionConcat2
				campos2[39] = ""
				campos2[40] = ""
				campos2[41] = ciudadGestion
				campos2[42] = princ2.telefonoContactoGestion
				if (princ2.lugarEnvio.toString().equalsIgnoreCase("DIRECCION")){
					campos2[43] = princ2.respuestaTipo

				}
				if (princ2.lugarEnvio.toString().equalsIgnoreCase("AGENCIA")){
					campos2[43] = "SOCIO SOLICITA ENVIO AGENCIA"
				}
				for (int r = 0; r < dbNombreSubestado.size(); r++){
					MotivosnoAceptaSeguro mot = dbNombreSubestado.get(r)
					if(princ2.subSubEstado == mot.nombreSubSubEstado){
						campos2[43] = mot.nombre //princ.subSubEstado
					}
				}
				//campos2[43] = campoGestion
				if (princ2.subSubEstado.toString().equalsIgnoreCase("ERROR EN EL PLASTICO") || princ2.subSubEstado.toString().equalsIgnoreCase("INFORMACION ERRADA EN HABILITANTES")){
					campos2[44] = princ2.observaciones
				}else{
					campos2[44] = ""
				}
				campos2[45] = princ2.celularContactoGestion
				//campos2[46] = princ2.emailGestion
				if (princ2.emailGestion.toString().equalsIgnoreCase("notiene@notiene.com")){
					campos2[46] =""
				}else {
					campos2[46] = princ2.emailGestion
				}
				if (princ2.personaContacto.toString().equalsIgnoreCase("TERCERA PERSONA")){
					nombrePersona = princ2.nombreTerceraPersona + " " + princ2.parentescoTerceraPersona
				}else{
					nombrePersona = ""
				}
				campos2[47] = nombrePersona
				campos2[48] = princ2.horarioEntrega
				campos2[49] = princ2.telefonoContactoGestion
				campos2[50] = princ2.telefonoContactoGestion
				campos2[51] = ""
				if (princ2.lugarEnvio.toString().equalsIgnoreCase("AGENCIA")){
					ciudadAgencia = princ2.ciudadAgencia
				}else{
					ciudadAgencia = ""
				}
				campos2[52] = ciudadAgencia
				campos2[53] = princ2.discrepancias
				campos2[54] = princ2.detalleDiscrepancias
				campos2[55] = princ2.detalleOtros
				ExcelUtils.addCells(campos2, sheetInterdin, j+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}
			//println(contactadosBase  + " " + totalBase  + " " + pctContactabilidad)
			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=REPORTE_DEVOLUCIONES_"+fecha+"_PW.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return
		}
	}
/**
 * @author Andres Redroban
 * @description Funcion que transforma una fecha en formato EEEE dd MMM YYYY. Ejemplo Miercoles 17 de 04 del 2019
 * @param fecha
 * @return formato
 */
	def convertirFecha(String fecha){
		//String formato = new SimpleDateFormat("EEEE dd 'de' MMMM 'del' YYYY", new Locale("ES")).format(fecha)
		String formato = ""
		String[] arrayFechas = fecha.trim().split('-')
		String dia = arrayFechas[2]
		String mes = arrayFechas[1]
		String anio = arrayFechas[0]
		formato = dia + "/" + mes + "/" + anio
		return formato
	}


	def baseGestionada(){
		if(params.fechas){
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			//def nombresBase = params.list("nombreBase")
			def subestados = params.list("subestados")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1]]
			String sql = "from Clientes where fechaGestion between :fechaInicio and :fechaFin "
			def base = Clientes.executeQuery(sql, condiciones)

			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("baseGestionada", 0)
			WritableSheet sheet = workbook.getSheet(0)
			String[] headers = [
					"CEDULA",
					"NOMBRES",
					"ESTADO",
					"SUBESTADO",
					"SUBSUBESTADO",
					"FECHA GESTION",
					"NOMBRE BASE",
					"NOMBRE VENDEDOR",
					"INTENTOS",
					"OBSERVACIONES",
					"TELEFONO CONTACTADO"
			]
			ExcelUtils.addCells(headers, sheet, 0, Colour.GRAY_25, Alignment.LEFT, VerticalAlignment.CENTRE, null, Border.ALL, BorderLineStyle.HAIR)

			for(int i = 0; i < base.size(); i++){
				String[] campos = new String[headers.length]
				Clientes c = base.get(i)

				campos[0] = c.identificacion
				campos[1] = c.nombre
				campos[2] = c.estadoGestion
				campos[3] = c.subestadoGestion.nombre
				campos[4] = c.subSubEstado
				campos[5] = c.fechaGestion.toString()
				campos[6] = c.nombreBase
				campos[7] = c.nombreVendedor
				campos[8] = c.intentos
				campos[9] = c.observaciones
				campos[10] = c.telefonoContactado
				ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, null, null, null)
			}
			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=baseGestionadaDiferido.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return

		}
	}
	def tiemposBreak() {
		if (params.fechas) {
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1)")
			def nombresBase = params.list("nombreBase")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1]]
			String sqlPrincipales = "from BreakTime where dateBreak between :fechaInicio and :fechaFin"
			def principalesList = BreakTime.executeQuery(sqlPrincipales, condiciones)
			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("UTF-8")
			WritableWorkbook workbook = Workbook.createWorkbook(file)
			workbook.createSheet("Clientes Efectivos", 0)
			WritableFont cellFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD)
			cellFont.setColour(Colour.WHITE);
			WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
			WritableSheet sheetPrincipales = workbook.getSheet(0)
			String[] headersPrincipales = ["FECHA/HORA", "TIEMPO", "OPCION", "NOMBRE USUARIO"]
			ExcelUtils.addCells(headersPrincipales, sheetPrincipales, 0, Colour.GREEN, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.THIN)
			for (int i = 0; i < principalesList.size(); i++) {
				String[] campos = new String[headersPrincipales.length]
				BreakTime cli = principalesList.get(i)
				campos[0] = cli.dateBreak.toString()
				campos[1] = cli.timeBreak.toString()
				campos[2] = cli.typeBreak
				campos[3] = cli.user.nombre
				ExcelUtils.addCells(campos, sheetPrincipales, i + 1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}
			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=tiemposBreakAsesores.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return
		}
	}

	def loginAgentes(){
		boolean visibilizar = false
		if(params.fechas) {
			visibilizar = true
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			def nombresBase = params.list("nombreBase")
			Date fechaInicio = fechas[0]
			Date fechaFin = fechas[1]
			println(fechaInicio)
			def consulta = Clientes.executeQuery("select substring(fechaGestion,1,10), nombreVendedor, substring(min(fechaGestion),11,12), substring(max(fechaGestion),11,12) from Clientes where fechaGestion between :fechaInicio and :fechaFin group by substring(fechaGestion,1,10), nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			String[][] tablaResult = new String[consulta.size()][5]
			//Lleno la matriz de resultados con los resultados de las onsultas anteriores
			for(int i = 0; i < tablaResult.size(); i++) {
				tablaResult[i][0] = consulta[i][0]
				tablaResult[i][1] = consulta[i][1]
				tablaResult[i][2] = consulta[i][2]
				tablaResult[i][3] = consulta[i][3]
			}
			[visibilizar: visibilizar, tablaResult: tablaResult]
		}
	}

}
