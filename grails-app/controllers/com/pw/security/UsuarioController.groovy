package com.pw.security



import static org.springframework.http.HttpStatus.*

import com.mysql.jdbc.Util;
import com.pw.security.Permiso;
import com.pw.security.Rol;
import com.pw.security.Sesion;
import com.pw.security.Usuario;
import utilitarios.Util;
import callcenter.*;

import grails.transaction.Transactional

@Transactional(readOnly = false)
class UsuarioController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


	static beforeInterceptor = {
		String accion = actionUri;
		if (!accion.equals("/usuario/login") && !accion.equals("/usuario/logout") && !accion.equals('/usuario/cargaConfiguracion')) {
			if (!session.user) {
				redirect(uri: "/usuario/login");
				return false;
			} else {
				boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
				if (!tienePermiso) {
					render "No tienes permiso para ingresar a este sitio-> " + accion;
				}
			}
		}
	}


	def login(){

		if(session.user){
			redirect(action:'dashboard')
		}

		if(params.usuario && params.password){
			def usuario = Usuario.findByUsuarioAndPasswordAndEstado(params.usuario.toString(), params.password.toString(), "ACTIVO");
			if(usuario){
				session.user = usuario
				def sesion = new Sesion()
				sesion.usuario = usuario
				sesion.idSesion = session.id
				sesion.fechaInicio = new Date()
				if(!sesion.save(flush: true))
					println sesion.errors
				redirect(action:'dashboard')
			}else{
				flash.errorMessage = "Error al iniciar sesion"
				usuario = new Usuario(params)
				[usuario: usuario]
			}

		}
	}

	def logout(){
		Sesion sesion = Sesion.findByIdSesion(session.id)
		sesion?.fechaFin = new Date()
		sesion.save(flush: true)
		session.invalidate()
		redirect(uri: "/usuario/login");
	}
	def dashboard(){

		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"))
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		Subestado subestados = Subestado.findByType("Exitoso")
		//Subestado subestadosCruzados = Subestado.findById(1,3)
		ArrayList<SubSubestado> subestadosCruzados = Subestado.executeQuery("from Subestado where id in (1,3)")
		//Subestado subestados = Subestado.findById(1)
		Subestado cruzadas = Subestado.findByType("Cruzada")
		// Subestado cruzadas = Subestado.findById(3)
		Rol rol = Rol.findByNombre("OPERADOR")
		def ventasPorUsuario = Clientes.executeQuery("select  nombreVendedor, count(*) from Clientes where subestadoGestion in (:subestados) and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by count(*) desc", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		def creditoPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where subestadoGestion in (:subestados) and subSubEstado = 'CREDITO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by count(*) desc", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		def cruzadaPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where subestadoGestion in (:cruzadas) and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by count(*) desc", [cruzadas: cruzadas, fechaInicio: fechaInicio, fechaFin: fechaFin])
		def exitosasPorUsuario = Clientes.executeQuery("select  nombreVendedor, count(*) from Clientes where subestadoGestion in (:subestadosCruzados) and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by count(*) desc", [subestadosCruzados: subestadosCruzados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		def ventasPorHoraVendedor = Clientes.executeQuery("select hour(fechaGestion), nombreVendedor, count(*) from Clientes where subestadoGestion in (1, 3) and fechaGestion between :fechaInicio and :fechaFin group by hour(fechaGestion), nombreVendedor", [ fechaInicio: fechaInicio, fechaFin: fechaFin])
		//	String[][] consolidado = consolidarExitAdic(ventasPorUsuario, cruzadaPorUsuario)
		//ArrayList consolidado = [ventasPorUsuario, cruzadaPorUsuario]
		def inicioSesion = Sesion.executeQuery("select usuario.nombre, time(fechaInicio), time(fechaFin) from Sesion where fechaInicio between :fechaInicio and :fechaFin and usuario.rol = :rol order by fechaInicio desc", [fechaInicio: fechaInicio, fechaFin: fechaFin, rol: rol])

		//Para la tabla de gestionados contactados y no contactados
		int totalGestionados = 0
		int totalContactados = 0
		int totalNoContactados = 0
		int totalExitosos = 0
		int totalCreditos = 0
		int totalCruzadas = 0
		def gestionadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
		def contactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
		def noContactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where intentos != 0 and estadoGestion = 'NO CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
		String[][] tablaResultAgente = new String[exitosasPorUsuario.size()][4]
		for(int x = 0; x < tablaResultAgente.size(); x++){
			tablaResultAgente[x][0] = exitosasPorUsuario[x][0];
			for(int z = 0; z < ventasPorUsuario.size(); z++){
				if(ventasPorUsuario[z][0] == exitosasPorUsuario[x][0]){
					tablaResultAgente[x][1] = ventasPorUsuario[z][1];
				}
			}
			for(int y = 0; y < cruzadaPorUsuario.size(); y++){
				if(cruzadaPorUsuario[y][0] == exitosasPorUsuario[x][0]){
					tablaResultAgente[x][2] = cruzadaPorUsuario[y][1];
					break;
				}
			}
		}
		//Lleno la matriz de resultados con los resultados de las onsultas anteriores
		String[][] tablaResult = new String[gestionadosAgente.size()][7]
		for(int i = 0; i < tablaResult.size(); i++){
			tablaResult[i][0] = gestionadosAgente[i][0];
			tablaResult[i][1] = gestionadosAgente[i][1];
			for(int j = 0; j < contactadosAgente.size(); j++){
				if(contactadosAgente[j][0] == gestionadosAgente[i][0]){
					tablaResult[i][2] = contactadosAgente[j][1];
					break;
				}
			}
			//LLeno la información de las ventas TDC
			for(int j = 0; j < ventasPorUsuario.size(); j++){
				if(ventasPorUsuario[j][0] == gestionadosAgente[i][0]){
					tablaResult[i][4] = ventasPorUsuario[j][1]
					break
				}
			}
			//LLeno la información de las ventas CREDITO
			for(int j = 0; j < creditoPorUsuario.size(); j++){
				if(creditoPorUsuario[j][0] == gestionadosAgente[i][0]){
					tablaResult[i][5] = creditoPorUsuario[j][1]
					break
				}
			}

			//LLeno la información de las ventas CRUZADAS
			for(int j = 0; j < cruzadaPorUsuario.size(); j++){
				if(cruzadaPorUsuario[j][0] == gestionadosAgente[i][0]){
					tablaResult[i][6] = cruzadaPorUsuario[j][1]
					break
				}
			}

			for(int k = 0; k < noContactadosAgente.size(); k++){
				if(noContactadosAgente[k][0] == gestionadosAgente[i][0]){
					tablaResult[i][3] = noContactadosAgente[k][1];
					break;
				}
			}
		}
		//Recorro la matriz de resultados para obtener los totales
		for(int i = 0; i < tablaResult.size(); i++){
			totalGestionados = totalGestionados + tablaResult[i][1].toInteger();
			if(tablaResult[i][2] != null)
				totalContactados = totalContactados + tablaResult[i][2].toInteger();
			if(tablaResult[i][3] != null)
				totalNoContactados = totalNoContactados + tablaResult[i][3].toInteger();
			if(tablaResult[i][4] != null)
				totalExitosos = totalExitosos + tablaResult[i][4].toInteger()
			if(tablaResult[i][5] != null)
				totalCreditos = totalCreditos + tablaResult[i][5].toInteger()
			if(tablaResult[i][6] != null)
				totalCruzadas = totalCruzadas + tablaResult[i][6].toInteger()
		}

		def gestionadosPlusWireless = Historial.executeQuery("select nombreVendedor, count(*) from Historial where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin and plataforma != 'PURE CLOUD' group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
		def contactadosPlusWireless = Historial.executeQuery("select nombreVendedor, count(*) from Historial where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin and plataforma != 'PURE CLOUD' group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
		def ventasPlusWireless = Historial.executeQuery("select  nombreVendedor, count(*) from Historial where subestadoGestion in (:subestados) and fechaGestion between :fechaInicio and :fechaFin and plataforma != 'PURE CLOUD' group by nombreVendedor order by count(*) desc", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		String[][] tablaResultPlusWireless = new String[gestionadosPlusWireless.size()][5]
		int totalGestionadosPlusWireless = 0;
		int totalContactadosPlusWireless = 0;
		int totalExitososPlusWireless = 0;
		//Lleno la matriz de resultados
		for(int k = 0; k < tablaResultPlusWireless.size(); k++){
			tablaResultPlusWireless[k][0] = gestionadosPlusWireless[k][0];
			tablaResultPlusWireless[k][1] = gestionadosPlusWireless[k][1];
			for(int l = 0; l < contactadosPlusWireless.size(); l++) {
				if (contactadosPlusWireless[l][0] == gestionadosPlusWireless[k][0]) {
					tablaResultPlusWireless[k][2] = contactadosPlusWireless[l][1];
					break;
				}
			}
			//LLeno la información de las ventas TDC
			for(int m = 0; m < ventasPlusWireless.size(); m++){
				if(ventasPlusWireless[m][0] == gestionadosPlusWireless[k][0]){
					tablaResultPlusWireless[k][3] = ventasPlusWireless[m][1]
					break
				}
			}
		}

		//Recorro la matriz de resultados para obtener los totales
		for(int i = 0; i < tablaResultPlusWireless.size(); i++){
			totalGestionadosPlusWireless = totalGestionadosPlusWireless + tablaResultPlusWireless[i][1].toInteger();
			if(tablaResultPlusWireless[i][2] != null)
				totalContactadosPlusWireless = totalContactadosPlusWireless + tablaResultPlusWireless[i][2].toInteger();
			if(tablaResultPlusWireless[i][3] != null)
				totalExitososPlusWireless = totalExitososPlusWireless + tablaResultPlusWireless[i][3].toInteger();
		}

		def gestionadosPureCloud = Historial.executeQuery("select nombreVendedor, count(*) from Historial where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin and plataforma = 'PURE CLOUD' group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
		def contactadosPureCloud = Historial.executeQuery("select nombreVendedor, count(*) from Historial where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin and plataforma = 'PURE CLOUD' group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
		def ventasPureCloud = Historial.executeQuery("select  nombreVendedor, count(*) from Historial where subestadoGestion in (:subestados) and fechaGestion between :fechaInicio and :fechaFin and plataforma = 'PURE CLOUD' group by nombreVendedor order by count(*) desc", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		String[][] tablaResultPureCloud = new String[gestionadosPureCloud.size()][5]
		int totalGestionadosPureCloud = 0;
		int totalContactadosPureCloud = 0;
		int totalExitososPureCloud = 0;


		//Lleno la matriz de resultados
		for(int k = 0; k < tablaResultPureCloud.size(); k++){
			tablaResultPureCloud[k][0] = gestionadosPureCloud[k][0];
			tablaResultPureCloud[k][1] = gestionadosPureCloud[k][1];
			for(int l = 0; l < contactadosPureCloud.size(); l++) {
				if (contactadosPureCloud[l][0] == gestionadosPureCloud[k][0]) {
					tablaResultPureCloud[k][2] = contactadosPureCloud[l][1];
					break;
				}
			}
			//LLeno la información de las ventas TDC
			for(int m = 0; m < ventasPureCloud.size(); m++){
				if(ventasPureCloud[m][0] == gestionadosPureCloud[k][0]){
					tablaResultPureCloud[k][3] = ventasPureCloud[m][1]
					break
				}
			}
		}

		//Recorro la matriz de resultados para obtener los totales
		for(int i = 0; i < tablaResultPureCloud.size(); i++){
			totalGestionadosPureCloud = totalGestionadosPureCloud + tablaResultPureCloud[i][1].toInteger();
			if(tablaResultPureCloud[i][2] != null)
				totalContactadosPureCloud = totalContactadosPureCloud + tablaResultPureCloud[i][2].toInteger();
			if(tablaResultPureCloud[i][3] != null)
				totalExitososPureCloud = totalExitososPureCloud + tablaResultPureCloud[i][3].toInteger();
		}


		//Para la tabla de gestionados, exitosos y contactados por base
		def contactadosPorBase = Clientes.executeQuery("select nombreBase, count(*) from Clientes where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreBase order by nombreBase", [fechaInicio: fechaInicio, fechaFin: fechaFin])
		def exitososPorBase = Clientes.executeQuery("select nombreBase, count(*) from Clientes where intentos != 0 and estadoGestion = 'CONTACTADO' and subestadoGestion in (:subestados) and fechaGestion between :fechaInicio and :fechaFin group by nombreBase order by nombreBase", [fechaInicio: fechaInicio, fechaFin: fechaFin, subestados: subestados])
		String[][] tablaResult1 = new String[contactadosPorBase.size()][4]
		//Lleno la matriz de resultados con los resultados de las consultas anteriores
		for(int i = 0; i < tablaResult1.size(); i++){
			tablaResult1[i][0] = contactadosPorBase[i][0]
			tablaResult1[i][1] = contactadosPorBase[i][1]
			for(int j = 0; j < exitososPorBase.size(); j++){
				if(exitososPorBase[j][0] == contactadosPorBase[i][0]){
					tablaResult1[i][2] = exitososPorBase[j][1]
					break
				}
			}
		}

		[inicioSesion: inicioSesion, tablaResult: tablaResult,tablaResult1: tablaResult1, totalGestionados: totalGestionados, totalContactados: totalContactados, totalNoContactados: totalNoContactados, totalExitosos: totalExitosos, totalCreditos: totalCreditos, totalCruzadas: totalCruzadas, ventasPorHoraVendedor:ventasPorHoraVendedor, ventasPorUsuario: ventasPorUsuario, tablaResultAgente: tablaResultAgente
		 ,tablaResultPlusWireless: tablaResultPlusWireless, totalGestionadosPlusWireless: totalGestionadosPlusWireless,
		  totalContactadosPlusWireless: totalContactadosPlusWireless,totalExitososPlusWireless: totalExitososPlusWireless, tablaResultPureCloud: tablaResultPureCloud,
		  totalGestionadosPureCloud: totalGestionadosPureCloud, totalContactadosPureCloud: totalContactadosPureCloud, totalExitososPureCloud: totalExitososPureCloud];

	}

	private String[][] consolidarExitAdic(ArrayList exitosos, ArrayList cruzadas){
		String[][] consolidado = new String[exitosos.size()][3]
		//String[][] consolidado = new String[cruzadas.size()][exitosos.size()]
		for(int i = 0; i < exitosos.size(); i++){
			boolean encontrado = false
			for(int j = 0; j < cruzadas.size(); j++){
				if( exitosos[i][0] == cruzadas[j][0]){
					consolidado[i][0] = exitosos[i][0]
					consolidado[i][1] = exitosos[i][1]
					consolidado[i][2] = cruzadas[j][1]
					encontrado = true
					break
				}
			}
			if(!encontrado){
				consolidado[i][0] = exitosos[i][0]
				consolidado[i][1] = exitosos[i][1]
				consolidado[i][2] = "0"
			}
		}
		return consolidado
	}

	def dashboardAgente(){
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"));
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"));
		Subestado subestado = Subestado.findByNombre("ACEPTA");
		Rol rol = Rol.findByNombre("OPERADOR");
		def ventasPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where subestadoGestion = :subestado and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by count(*) desc", [subestado: subestado, fechaInicio: fechaInicio, fechaFin: fechaFin]);
		def inicioSesion = Sesion.executeQuery("select usuario.nombre, time(min(dateCreated)) from Sesion where dateCreated between :fechaInicio and :fechaFin and usuario.rol = :rol group by usuario.nombre order by usuario.nombre", [fechaInicio: fechaInicio, fechaFin: fechaFin, rol: rol]);

		//Para la tabla de gestionados contactados y no contactados
		int totalGestionados = 0;
		int totalContactados = 0;
		int totalNoContactados = 0;
		def gestionadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin]);
		def contactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin]);
		def noContactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*) from Clientes where intentos != 0 and estadoGestion = 'NO CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin]);
		String[][] tablaResult = new String[gestionadosAgente.size()][4];
		//Lleno la matriz de resultados con los resultados de las onsultas anteriores
		for(int i = 0; i < tablaResult.size(); i++){
			tablaResult[i][0] = gestionadosAgente[i][0];
			tablaResult[i][1] = gestionadosAgente[i][1];
			for(int j = 0; j < contactadosAgente.size(); j++){
				if(contactadosAgente[j][0] == gestionadosAgente[i][0]){
					tablaResult[i][2] = contactadosAgente[j][1];
					break;
				}
			}
			for(int k = 0; k < noContactadosAgente.size(); k++){
				if(noContactadosAgente[k][0] == gestionadosAgente[i][0]){
					tablaResult[i][3] = noContactadosAgente[k][1];
					break;
				}
			}
		}
		//Recorro la matriz de resultados para obtener los totales
		for(int i = 0; i < tablaResult.size(); i++){
			totalGestionados = totalGestionados + tablaResult[i][1].toInteger();
			if(tablaResult[i][2] != null)
				totalContactados = totalContactados + tablaResult[i][2].toInteger();
			if(tablaResult[i][3] != null)
				totalNoContactados = totalNoContactados + tablaResult[i][3].toInteger();
		}

		[ventasPorUsuario: ventasPorUsuario, inicioSesion: inicioSesion, tablaResult: tablaResult, totalGestionados: totalGestionados, totalContactados: totalContactados, totalNoContactados: totalNoContactados];
	}

	def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
		Usuario usuario = Usuario.findById(session.user.id);
		if(Util.isAdmin(usuario.usuario))
			respond Usuario.list(), model:[usuarioInstanceCount: Usuario.count()]
		else
			respond Usuario.executeQuery("from Usuario where rol.nombre != 'ADMINISTRADOR'"), model:[usuarioInstanceCount: Usuario.count()]
	}

	def show(Usuario usuarioInstance) {
		respond usuarioInstance
	}

	def create() {
		respond new Usuario(params)
	}

	@Transactional
	def save(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}

		if (usuarioInstance.hasErrors()) {
			respond usuarioInstance.errors, view:'create'
			return
		}

		Usuario usuario = Usuario.findById(session.user.id)
		usuarioInstance.usuarioCreador = usuario.nombre
		usuarioInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
				redirect usuarioInstance
			}
			'*' { respond usuarioInstance, [status: CREATED] }
		}
	}

	def edit(Usuario usuarioInstance) {
		respond usuarioInstance
	}

	@Transactional
	def update(Usuario usuarioInstance) {
		if (usuarioInstance == null) {
			notFound()
			return
		}

		if (usuarioInstance.hasErrors()) {
			respond usuarioInstance.errors, view:'edit'
			return
		}

		usuarioInstance.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'Usuario.label', default: 'Usuario'), usuarioInstance.id])
				redirect usuarioInstance
			}
			'*'{ respond usuarioInstance, [status: OK] }
		}
	}

	@Transactional
	def delete(Usuario usuarioInstance) {

		if (usuarioInstance == null) {
			notFound()
			return
		}

		usuarioInstance.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'Usuario.label', default: 'Usuario'), usuarioInstance.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
