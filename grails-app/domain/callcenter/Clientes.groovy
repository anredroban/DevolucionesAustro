package callcenter
import java.util.Date;
import com.pw.security.*;

class Clientes {

	//Campos de la base
	String codigoCampania //SI
	String nombreCampania //SI
	String identificacion //SI
	String nombre //SI
	String edad

	String fechaGrabacion
	String status
	String producto
	String codProducto
	String tarjeta
	String secuencial
	String codigoUnico
	String fecha
	String motivoDevolucion
	String ciudad
	String x16
	String canal
	String codRet
	String bolNac
	String gb
	String caso
	String gestiones
	String tipo
	String dhnomp
	String dhCuenta
	String secuencialJ
	String cedPrincipal
	String direccion
	String direccionCuenta
	String x15Producto
	String regestion
	String dhsegm
	String callCenter
	String direccionDomilio1
	String direccionDomilio2
	String direccionDomilio3
	String direccionTrabajo1
	String direccionTrabajo2
	String direccionTrabajo3




	String numeroOperaciones
	/*----Encuesta----*/
	String lugarEnvio
	String provinciaAgencia
	String ciudadAgencia
	String motivoDevolucionesGestion
	String telefonoContactoGestion
	String respuestaTipo
	String provinciaDirecion
	String ciudadDirecion
	String parroquiaDirecion
	String celularContactoGestion
	String emailGestion
	String personaContacto
	String nombreTerceraPersona
	String parentescoTerceraPersona
	String horarioEntrega
	String callePrincipalEntrega
	String nomenclaturaEntrega
	String calleSecundariaEntrega
	String referenciaEntrega

	String discrepancias
	String detalleDiscrepancias
	String detalleOtros

	/*--Fin Encuesta----*/


	//Campos que SIEMPRE van en la gestión
	Date fechaGestion
	Date fechaMarcacion
	int intentos
	String estadoGestion
	Subestado subestadoGestion
	String subSubEstado
	Usuario usuario
	String nombreBase
	String nombreVendedor
	Date fechaRellamada
	String observaciones
	String motivoNoDesea
	String telefonoContactado
	String agendamientoAsesor
	String motivoNoAceptaSeguro
	boolean isActive
	String registroExitoso
	String telefono1
	String telefono2
	String telefono3
	String telefono4
	String telefono5
	String telefono6
	String telefono7
	String telefono8
	String telefono9
	String telefono10
	String codigoAsignacion
	String estadoTelefonoContactado


	/*Creacion de campos solicitados por el area de Reporting 2019-05-11*/
	String gama
	String regional
	String rangoEdad
	String rangoCupo
	String segmentacionAd1
	String segmentacionAd2
	String segmentacionAd3
	String segmentacionAd4
	String segmentacionAd5
	String easyCodeRegional
	String easyCodeEdad
	String easyCodeCupo
	String easyCodeEdadCupo
	String easyCodeGamaEdad
	String easyCodeAd1
	String easyCodeAd2
	String easyCodeAd3
	String easyCodeAd4
	String easyCodeAd5
	String prioridadCampania
	String fechaCaducidad
	String metaContactabilidad
	String metaEfectividadTelefonica
	String metaEfectividadReal
	String tipoGestion
	String plataforma
	String bloqueSegmentacion

	String guia
	String inventario

	static constraints = {
		//Campos de la base
		codigoCampania nullable: true
		nombreCampania nullable: true
		identificacion nullable: true
		nombre nullable: true
		edad nullable: true
		ciudad nullable: true
		fechaGrabacion nullable: true
		status nullable: true
		producto nullable: true
		codProducto nullable: true
		tarjeta nullable: true
		secuencial nullable: true
		codigoUnico nullable: true
		fecha nullable: true
		motivoDevolucion nullable: true
		ciudad nullable: true
		canal nullable: true
		caso nullable: true
		gestiones nullable: true
		tipo nullable: true
		dhnomp nullable: true
		dhCuenta nullable: true
		cedPrincipal nullable: true
		direccion nullable: true
		direccionCuenta nullable: true
		x15Producto nullable: true
		x16 nullable: true
		secuencialJ nullable: true
		regestion nullable: true
		callCenter nullable: true
		direccionDomilio1 nullable: true
		direccionDomilio2 nullable: true
		direccionDomilio3 nullable: true
		direccionTrabajo1 nullable: true
		direccionTrabajo2 nullable: true
		direccionTrabajo3 nullable: true
		dhsegm nullable: true
		lugarEnvio nullable: true
		provinciaAgencia nullable: true
		motivoDevolucionesGestion nullable: true
		telefonoContactoGestion nullable: true
		respuestaTipo nullable: true
		provinciaDirecion nullable: true
		ciudadDirecion nullable: true
		parroquiaDirecion nullable: true
		celularContactoGestion nullable: true
		emailGestion nullable: true
		personaContacto nullable: true
		nombreTerceraPersona nullable: true
		parentescoTerceraPersona nullable: true
		horarioEntrega nullable: true
		callePrincipalEntrega nullable: true
		nomenclaturaEntrega nullable: true
		calleSecundariaEntrega nullable: true
		referenciaEntrega nullable: true
		discrepancias nullable: true
		detalleDiscrepancias nullable: true
		detalleOtros nullable: true
		codRet nullable: true
		bolNac nullable: true
		gb nullable: true
		/*--Fin Encuesta----*/

		numeroOperaciones nullable: true
		//Campos que SIEMPRE van en la gestión
		fechaGestion nullable: true
		fechaMarcacion nullable: true
		intentos nullable: true
		estadoGestion nullable: true
		subestadoGestion nullable: true
		subSubEstado nullable: true
		usuario nullable: true
		nombreBase nullable: true
		nombreVendedor nullable: true
		fechaRellamada nullable: true
		observaciones nullable: true
		motivoNoDesea nullable: true
		isActive nullable: true
		telefonoContactado nullable: true
		motivoNoAceptaSeguro nullable: true
		agendamientoAsesor nullable: true
		ciudadAgencia nullable: true
		producto nullable: true
		registroExitoso nullable: true
		telefono1 nullable: true
		telefono2 nullable: true
		telefono3 nullable: true
		telefono4 nullable: true
		telefono5 nullable: true
		telefono6 nullable: true
		telefono7 nullable: true
		telefono8 nullable: true
		telefono9 nullable: true
		telefono10 nullable: true
		codigoAsignacion nullable: true
		estadoTelefonoContactado nullable: true

		/*Creacion de campos solicitados por el area de Reporting 2019-05-11*/
		gama nullable: true
		regional nullable: true
		rangoEdad nullable: true
		rangoCupo nullable: true
		segmentacionAd1 nullable: true
		segmentacionAd2 nullable: true
		segmentacionAd3 nullable: true
		segmentacionAd4 nullable: true
		segmentacionAd5 nullable: true
		easyCodeRegional nullable: true
		easyCodeEdad nullable: true
		easyCodeCupo nullable: true
		easyCodeEdadCupo nullable: true
		easyCodeGamaEdad nullable: true
		easyCodeAd1 nullable: true
		easyCodeAd2 nullable: true
		easyCodeAd3 nullable: true
		easyCodeAd4 nullable: true
		easyCodeAd5 nullable: true
		prioridadCampania nullable: true
		fechaCaducidad nullable: true
		metaContactabilidad nullable: true
		metaEfectividadTelefonica nullable: true
		metaEfectividadReal nullable: true
		tipoGestion nullable: true
		plataforma nullable: true
		bloqueSegmentacion nullable: true

		guia nullable: true
		inventario nullable: true
	}
	
	static mapping = {
		version false
		observaciones type: 'text'
	}

	static String[] getFields(){
		String[] fields = [
						   "fechaGrabacion",
						   "status",
						   "producto",
						   "codProducto",
						   "tarjeta",
						   "secuencial",
                           "codigoUnico",
						   "fecha",
						   "identificacion",
						   "nombre",
						   "telefono1",
						   "telefono2",
						   "telefono3",
						   "motivoDevolucion",
						   "ciudad",
				           "x16",
						   "canal",
						   "codRet",
						   "bolNac",
				           "gb",
						   "caso",
						   "gestiones",
						   "tipo",
						   "dhnomp",
						   "dhCuenta",
				           "secuencialJ",
						   "cedPrincipal",
						   "direccion",
						   "direccionCuenta",
						   "telefono4",
						   "telefono5",
						   "telefono6",
						   "x15Producto",
				           "regestion",
						   "dhsegm",
						   "callCenter",
						   "direccionDomilio1",
						   "direccionDomilio2",
						   "direccionDomilio3",
						   "direccionTrabajo1",
						   "direccionTrabajo2",
						   "direccionTrabajo3",
				           "numeroOperaciones",
						    "fechaCaducidad"
						   , "metaContactabilidad"
						   , "metaEfectividadReal"
						   , "tipoGestion"
						   , "plataforma"
						   ,"codigoCampania",
						   "nombreCampania",
						   "bloqueSegmentacion",
						   "guia",
						   "inventario"]
		return fields
	}
	static HashMap getTiposParientes(){
		return ['': '-- Seleccione --', '00':'Ninguno', '01': 'Padre', '02': 'Madre', '03': 'Hermano(a)', '04': 'Primo(a)',
				'05': 'Tío(a)', '06': 'Sobrino(a)', '07': 'Esposa(o)', '08': 'Cuñado', '09': 'Yerno (Nuera)', '10': 'Suegro(a)', '11': 'Hijo(a)',
				'12': 'Amigo(a)', '13': 'Abuelo(a)', '14': 'Novio (a)', '15': 'Nieto (a)', '16': 'Compañero de Trabajo', '17': 'Familiar',
				'18': 'Representante Legal', '19': 'Relación Comercial', '20': 'Relación Laboral', '21': 'Presidente', '22': 'Vice-presidente',
				'23': 'Funcionario', '24': 'Ejecutivo']
	}
}
