package callcenter
import com.pw.security.*
import grails.converters.JSON
import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.WorkbookSettings
import liquibase.util.file.FilenameUtils
import utilitarios.Util

import java.text.DateFormat
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat

class GestionController {



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
	 * @author Giovanny Granda
	 * Muestra en pantalla los clientes asignados
	 * @return
	 */
	def index() {
		Usuario usuario = Usuario.findById(session.user.id);
		def plataforma = 'PURE CLOUD'
//		def clientesGestionar = Clientes.executeQuery("from Clientes c where subestadoGestion.rellamar = 'SI' and usuario = :usuario order by c.intentos", [usuario: usuario])

		def clients = Clientes.withCriteria {
			eq('usuario',usuario)
			eq('isActive', true)
			notEqual('plataforma', plataforma)
			subestadoGestion {
				or{
					eq('type', Subestado.constraints.type.inList[0].toString())
					eq('type', Subestado.constraints.type.inList[1].toString())
				}
			}
			order("intentos")
		}
		def clientsNoManagement = Clientes.withCriteria {
			eq('usuario',usuario)
			eq('isActive', true)
			isNull('subestadoGestion')
		}

		clients.each {client ->
			clientsNoManagement.add(client)
		}
//		print clientsNoManagement.size()

//		def clientesGestionar = Clientes.withCriteria {
//			eq('usuario',usuario)
//			(
//					{
//						isNull('subestadoGestion')
//					}
//			)
//					{
//						or{
//							subestadoGestion{
//								eq('type',Subestado.constraints.type.inList[0].toString())
//							}
//							subestadoGestion{
//								eq('type',Subestado.constraints.type.inList[1].toString())
//							}
//						}
//					}
//			order('intentos','asc')
//		}
		[clientesGestionar: clientsNoManagement]
	}

	/**
	 * @author Giovanny Granda
	 * Muestra la pantalla de gestion donde se hara rectificación de datos
	 * @param id
	 * @return
	 */
	def gestionCliente(long id){
		long idCliente = id
		Clientes cliente = Clientes.findById(idCliente)
		session.user
		Calendar datos = Calendar.getInstance()
		int dia = datos.get(Calendar.DATE)
		int mes = datos.get(Calendar.MONTH)
		int anio = datos.get(Calendar.YEAR)
		String fechaActual = anio + '-' + (mes+1) + '-' + dia
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd")
		Date fecha1 = sfd.parse(fechaActual.toString())
		Date fecha2 = sfd.parse(cliente.fechaCaducidad)
		String salida = fecha1.compareTo(fecha2)
		String motivo = ''
		boolean isActive = cliente.isActive
		boolean validacion = false
		if(isActive == false){
			motivo = 'REGISTRO INACTIVO'
			validacion = true
		}
		if(salida == "1"){
			motivo = 'BASE CADUCADA'
			validacion = true
		}
		if(salida == "1" && isActive == false){
			motivo = 'BASE CADUCADA Y REGISTRO INACTIVO'
			validacion = true
		}
		if(validacion){
			render(view: "modelValidacion",  model: [idCliente: cliente.id, motivo: motivo, tipo: 'NO GESTIONABLES'])
		}
		[cliente: cliente,usuario: session.user]
	}


	/**
	 * @author Giovanny Granda
	 * Guarda la gestion del call center
	 * @param id
	 * @return
	 */
	def guardarCliente(){
		Usuario usuario = Usuario.findById(session.user.id)
		Date fechaActual = new Date()
		long idCliente = params.idCliente.toLong()
		long idEstadoGestion = params.estado.toLong()
		long idSubestadoGestion = params.substatus.toLong()



		Estado estadoGestion = Estado.findById(idEstadoGestion)
		//String estadoGestion = Estado.findById(params.status.toString().toLong())
		Subestado objSubestadoGestion = Subestado.findById(idSubestadoGestion)
		//Busco el cliente por su id
		Clientes cliente = Clientes.findById(idCliente)
		int intentos = cliente.intentos?: 0

		if(cliente.registroExitoso != "SI"){
		//if(objSubestadoGestion.enableManagement && params.subSubStatus != "4" && params.subSubStatus != "10"){
		if(objSubestadoGestion.nombre.equalsIgnoreCase("CU1 ACEPTA PRODUCTO CAMPAÑA") || objSubestadoGestion.nombre.equalsIgnoreCase("CU3 ACEPTA PRODUCTO Y VENTA CRUZADA")){
			//cliente.lugarEnvio = Util.muestraCedulaOriginal(cliente.identificacion)
			//cliente.nombre1 = formatearTexto(params.nombre1.toString()).toUpperCase()
			cliente.lugarEnvio = params.lugarEnvio
			if(params.lugarEnvio == "AGENCIA"){
				cliente.provinciaAgencia = Provincia.findById(params.provinciaAgencia.toLong()).nombre
				cliente.ciudadAgencia = Agencia.findById(params.ciudadAgencia.toLong()).nombre
				cliente.motivoDevolucionesGestion = params.motivoDevolucionesGestion
				cliente.telefonoContactoGestion = params.telefonoContactoGestion
				cliente.celularContactoGestion = params.celularContactoGestion
				cliente.emailGestion = params.emailGestionAgencia
			}

			if (params.lugarEnvio == "DIRECCION"){
				cliente.respuestaTipo = params.respuestaTipo
				cliente.motivoDevolucionesGestion = params.motivoDevolucionesGestionDir
				cliente.provinciaDirecion = Provincia.findById(params.provinciaDirecion.toLong()).nombre
				cliente.ciudadDirecion = Ciudad.findById(params.ciudadDirecion.toLong()).nombre
				cliente.parroquiaDirecion = ParroquiaAgencia.findById(params.parroquiaDirecion.toLong()).nombre
				cliente.telefonoContactoGestion = params.telefonoContactoGestionDir
				cliente.celularContactoGestion = params.celularContactoGestionDir
				//cliente.emailGestion = params.emailGestionemailGestionDireccion
				cliente.personaContacto = params.personaContacto
				if (params.personaContacto == "TERCERA PERSONA"){
					cliente.nombreTerceraPersona = params.nombreTerceraPersona
					cliente.parentescoTerceraPersona = params.parentescoTerceraPersona
				}
				cliente.horarioEntrega = params.horarioEntrega
				cliente.callePrincipalEntrega = params.callePrincipalEntrega
				cliente.nomenclaturaEntrega = params.nomenclaturaEntrega
				cliente.calleSecundariaEntrega = params.calleSecundariaEntrega
				cliente.referenciaEntrega = params.referenciaEntrega
			}
			//cliente.registroExitoso = 'SI'

		}

			if(objSubestadoGestion.alias.equalsIgnoreCase("NO EDITABLE")){
				cliente.registroExitoso = 'SI'
			}

		if(objSubestadoGestion.type.toString().equalsIgnoreCase("Rellamada")){
			cliente.fechaRellamada = new Date().parse('yyyy-MM-dd HH:mm:ss', params.recall.toString().replace('/','-') + ':00')
			//cliente.fechaRellamada =  new Date().parse('yyyy-MM-dd HH:mm:ss', formatearRellamada(params.recall.toString()))
			cliente.agendamientoAsesor = params.agendamiento
		}

		if(objSubestadoGestion.nombre.toString().equalsIgnoreCase("CU5 NO DESEA EL PRODUCTO")){
			cliente.motivoNoAceptaSeguro = params.motivoNoAceptaSeguro
		}else{
			cliente.motivoNoAceptaSeguro = ""
		}

		cliente.estadoGestion = estadoGestion.nombre
		/*if(estadoGestion.nombre == "CONTACTADO"){
			cliente.telefonoContactado = params.telefonoContactado
		}
		if(estadoGestion.nombre == "NO CONTACTADO"){
			cliente.telefonoContactado = ""
		}*/
		cliente.subestadoGestion = objSubestadoGestion
		if (params.subSubStatus != ""){
			String nombreSubSubEstado = SubSubestado.findById(params.subSubStatus.toString().toLong()).name
			cliente.subSubEstado = nombreSubSubEstado

		}
		else
			cliente.subSubEstado = ""
		cliente.intentos = intentos+1
		cliente.fechaGestion = fechaActual
		cliente.fechaMarcacion = fechaActual
		cliente.usuario = usuario
		cliente.nombreVendedor = usuario.nombre
		cliente.telefonoContactado = params.telefonoContactado
		cliente.estadoTelefonoContactado = params.estadoTelefonoContactado
		cliente.observaciones = params.observaciones
		cliente.motivoNoDesea = params.motivoNoDesea
		cliente.discrepancias = params.discrepancias
		cliente.detalleDiscrepancias = params.detalleDiscrepancias
		cliente.detalleOtros = params.detalleOtros


		cliente.save(flush: true)

		//Se guarda informacion en el historial
		Historial historial = new Historial()
		historial.cliente = Clientes.findById(cliente.id.toLong())
		historial.estadoGestion = cliente.estadoGestion
		historial.subestadoGestion = cliente.subestadoGestion
		historial.subSubEstado = cliente.subSubEstado
		historial.fechaGestion = fechaActual
		historial.intentos = cliente.intentos
		historial.nombreVendedor = cliente.nombreVendedor
		historial.observacionesGestion = cliente.observaciones
		historial.usuario = cliente.usuario
		historial.plataforma = cliente.plataforma
		historial.detalleTelefono1 = cliente.telefono1 + " " + params.estadoTel1
		historial.detalleTelefono2 = cliente.telefono2 + " " + params.estadoTel2
		historial.detalleTelefono3 = cliente.telefono3 + " " + params.estadoTel3
		historial.detalleTelefono4 = cliente.telefono4 + " " + params.estadoTel4
		historial.detalleTelefono5 = cliente.telefono5 + " " + params.estadoTel5
		historial.detalleTelefono6 = cliente.telefono6 + " " + params.estadoTel6
		historial.detalleTelefono7 = cliente.telefono7 + " " + params.estadoTel7
		historial.detalleTelefono8 = cliente.telefono8 + " " + params.estadoTel8
		historial.detalleTelefono9 = cliente.telefono9 + " " + params.estadoTel9
		historial.detalleTelefono10 = cliente.telefono10 + " " + params.estadoTel10
		historial.telefonoContactado = params.telefonoContactado
		historial.estadoTelefonoContactado = cliente.estadoTelefonoContactado
			
		historial.save(flush: true)
		session.setAttribute("lastManageId","")
		redirect(uri: "/gestion/index")
		}else{
			render(view: "modelValidacion",  model: [estado:cliente.estadoGestion, subestado: cliente.subestadoGestion.nombre, idCliente: cliente.id,  tipo: 'EXITOSO'])
		}

	}

	private String formatearTexto(String entrada){
		return entrada.toUpperCase().replace('Ñ', 'N').replace('-', ' ').replace('Á', 'A').replace('É', 'E').replace('Í', 'I').replace('Ó', 'O').replace('Ú', 'U').replace('.', ' ')
	}

	/**
	 * @author Andres Redroban
	 * Convierte de formato hh:MM AM/PM a formato 24H
	 * @return resultado
	 */
	private String formatearHora(String entrada){
		DateFormat formatoInicial = new SimpleDateFormat("hh:mm a") //11:00 pm
		Date hora = null
		try {
			hora = formatoInicial.parse(entrada)
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace()
		}
		DateFormat formatoFinal = new SimpleDateFormat("HH:mm")
		String resultado = formatoFinal.format(hora) // "23:00"

		return resultado
	}

	/**
	 * @author Andres Redroban
	 * Convierte el formato de campo Fecha Rellamada
	 * @return resultado
	 */
	private String formatearRellamada(String variable){
		String[] arrayFechas = variable.trim().split(' ')
		String fecha = arrayFechas[0].replace('/', '-')
		String hora =  arrayFechas[1] + ' ' + arrayFechas[2]
		SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
		SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
		Date date = parseFormat.parse(hora);
		String  horaFinal = displayFormat.format(date)+':00'
		String resultado = fecha + ' ' + horaFinal
		return resultado
	}

	def retirarBase(){
		boolean updateRealizado = false
		int resultado = 0
		if(params.usuario != null && params.tipo != null && params.nombrebase != null){

			String desde = params.desde
			String hasta = params.hasta

			updateRealizado = true
			Usuario usuarioAdministrador = Usuario.findById(1)

			def subestados
			if(params.tipo != "RELLAMADAS"){
				subestados = Subestado.executeQuery("from Subestado where type = 'Regestionable'")
			}else {
				subestados = Subestado.executeQuery("from Subestado where type = 'Rellamada'")
			}

			String sql = "update Clientes set usuario = :usuario where (subestadoGestion in (:subestados) or subestadoGestion is null) and usuario != :usuario and plataforma != 'PURE CLOUD' "


			def condiciones = [usuario: usuarioAdministrador, subestados: subestados]
			String condicionUsuario = ""
			String condicionTipo = ""
			String condicionNombreBase = ""
			String condicionDesde = ""
			String condicionHasta = ""

			if(params.desde != ""){
				condicionDesde = " and cast(codigoAsignacion as integer) >= :desde"
				condiciones.put("desde", desde.toString().toInteger())
			}

			if(params.hasta != ""){
				condicionHasta = " and cast(codigoAsignacion as integer) <= :hasta"
				condiciones.put("hasta", hasta.toString().toInteger())
			}

			if(params.usuario != ""){
				Usuario usuarioVendedor = Usuario.findById(params.usuario)
				condicionUsuario = " and usuario = :vendedor"
				condiciones.put("vendedor", usuarioVendedor)
			}

			if(params.tipo != ""){
				if(params.tipo == "REGESTIONABLE"){
					condicionTipo = " and intentos > 0"
				}
				if(params.tipo == "RELLAMADAS"){
					condicionTipo = " and intentos > 0 and agendamientoAsesor = 'AGENDAR PARA CUALQUIERA'"
				}
				if(params.tipo == "SIN GESTION"){
					condicionTipo = " and intentos = 0"
				}
			}

			if(params.nombrebase != ""){
				condicionNombreBase = " and nombreBase = :nombreBase"
				condiciones.put("nombreBase", params.nombrebase)
			}

			resultado = Clientes.executeUpdate(sql+condicionUsuario+condicionTipo+condicionNombreBase+condicionDesde+condicionHasta, condiciones)

		}
		[updateRealizado: updateRealizado, resultado: resultado]
	}

	def cargarBase(){

	}

	def saveFile(){
		String[] formFields = Clientes.getFields()
		def file = request.getFile('file')
		Cell[] cells
		String[] headers
		if(file.empty){
			flash.message = "Por favor selecciona un archivo"
		}else{
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder) //app directory
			File fileDest = new File(webrootDir,file.getOriginalFilename())
			if(fileDest.mkdirs()){
				println "directory created"
			}else{
				println "directory not created or already exists"
			}
			file.transferTo(fileDest)

			//Reading Excel
			String ext = FilenameUtils.getExtension(fileDest.path)
			if(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx")){
				try {
					WorkbookSettings ws = new WorkbookSettings()
					ws.setEncoding("Cp1252")
					Workbook workbook = Workbook.getWorkbook(fileDest, ws)
					Sheet sheet = workbook.getSheet(0)
					cells = sheet.getRow(0)
					workbook.close()
				}catch (IOException ex){
					flash.error = "Problemas al cargar el archivo"
					render(view: "index")
				}
				headers = new String[cells.length]
				for(int i = 0; i < cells.length; i++){
					headers[i] = cells[i].getContents()
				}
				render(view: "sortExcel", model: [headers: headers, formFields:formFields, filePath:fileDest.path])
			}else{
				flash.error = "El archivo debe ser una hoja de cálculo de Excel"
				render(view: "index")
			}
		}
	}

	/**
	 * Status
	 * @return
	 */
	def getSubStatusByStatus(){
		if(params.id) {
			def status = Estado.findById(params.id)
			def subStatus = Subestado.findAllByEstado(status)
			def array = [subStatus.id, subStatus.nombre, subStatus.type, subStatus.enableManagement]
			render array as JSON
		}
	}

	/**
	 *
	 */


	/**
	 * make by someone
	 * @param value
	 * @return
	 */
	private removeSpecialCharacters(value){
		if(value != null){
			def newValue = value.replace("-"," ").replace("!","").replace("@","").replace("#","").replace("\$","")
					.replace("&","").replace("/","").replace("(","").replace(")","").replace("=","")
					.replace("?","").replace("¿","").replace("ç","").replace("{","").replace("}","")
					.replace("\\","").replace("á","a").replace("é","e").replace("í","i").replace("ó","o")
					.replace("ú","u").replace("\"","").replace("Á","A").replace("É","E").replace("Í","I")
					.replace("Ó","O").replace("Ú","U").replace("\'","").replace("  "," ").replace("  "," ")
					.replace("  "," ").replace("%","").replace(".","").replace(",","").replace("º","")
					.replace("ª","").replace("|","").replace("\$","").replace("¬","").replace("%","")
					.replace("*","").replace("+","").replace("_","")
			return newValue
		}
	}

	def simulador1(){
		boolean visibilizar = false

		if(params.fechas){
			visibilizar = true
			String dato = params.fechas
			double dato2 = Double.parseDouble(dato)
			double dato3 = 0
			DecimalFormat df = new DecimalFormat("#.00")
			String montoIngresado = df.format(dato2)
			def valoresFactores = Factores.executeQuery("select plazo, factorPersonas, monto, comision, montoTarifa from Factores where isEnable = true")
			String[][] tablaResult = new String[valoresFactores.size()][8]
			for(int i = 0; i < tablaResult.size(); i++){
				tablaResult[i][0] = valoresFactores[i][0]
				//println(dato)
				tablaResult[i][1] = valoresFactores[i][1]
				/*
				print(dato2 + ' ' + dato3)*/
				tablaResult[i][2] = df.format(dato2 * Double.parseDouble(valoresFactores[i][2]))
				//dato3 = Double.parseDouble(tablaResult[i][2].replace(",","."))
				//print(dato3)
				tablaResult[i][3] = df.format(dato2 + Double.parseDouble(tablaResult[i][2].replace(",",".")))
				tablaResult[i][4] = df.format(Double.parseDouble(tablaResult[i][3].replace(",",".")) / tablaResult[i][0].toInteger())
				tablaResult[i][5] = valoresFactores[i][3]
				tablaResult[i][6] = valoresFactores[i][4]

				/*println tablaResult[i][0]
				println tablaResult[i][1]
				println tablaResult[i][2]
				println tablaResult[i][3]*/
			}
			//println tablaResult
			[visibilizar: visibilizar, tablaResult: tablaResult, montoIngresado: montoIngresado]
		}
	}

}
