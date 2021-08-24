$(document).ready(function(){
    $telefonoContactadoDiv = $("#telefonoContactadoDiv");
    $telefonoContactado = $("#telefonoContactado");
    $numeroCasaDomicilio = $("#numeroCasaDomicilio");
    $telefonoContacto = $("#telefonoContacto");
    $status = $("#status")
    $subSubStatus = $("#subSubStatus");
    $divMotivo = $("#divMotivo");
    init();



    $status.change(function(){
        esconderCamposEstados();
       // $telefonoContactadoDiv.hide();
        $telefonoContactado.val("");
        if($status.val() == ""){
            emptySelect('substatus');
        }else{
            $statusId = this.value;
            if($statusId == 1){
            //    $telefonoContactadoDiv.show();
            }
            $.get(baseUrl + "/FuncionesAjax/getSubStatusByStatus", {
                id: $statusId
            }, function (data) {
                fillSelect('substatus', data);
            });
        }
    });



    //Cuando cambia el SUBESTADO
    $("#substatus").change(function () {
        esconderCamposEstados();
        // $("#btnAdicional").attr("disabled", true);
        if($("#substatus").val() == ""){
            emptySelect("subSubStatus");
        }else{
            $subStatusId = this.value;
            $.get(baseUrl + "/FuncionesAjax/getSubSubStatusBySubStatus", {
                id: $subStatusId
            }, function (data) {
                if(fillSelect('subSubStatus', data) > 0){
                    $("#subSubStatusDiv").show();
                    //  $telefonoContactadoDiv.show();
                }else{
                    $("#subSubStatusDiv").hide();
                    //$("#send").show()
                }
                if(data[2] == 'Rellamada'){
                    $("#recallDiv").show();
                }else{
                    $("#recallDiv").hide();
                }
            });
        }
    });


    //LOGICA PARA CREDITO Y TDC
    $("#substatus").change(function () {
        if ($("#substatus").val() == 3){
            $("#managementData").show();
            $("#tipoAsistenciaDiv").show();
        }

        if ($("#substatus").val() == 2){
            $("#divPDP").show();
            $("#managementData").hide();
            $("#tipoAsistenciaDiv").hide();
        }

        if ($("#substatus").val() == 1){
            $("#divPDP").hide();
            $("#managementData").show();
            $("#tipoAsistenciaDiv").hide();
        }
    });


   /* $("#subSubStatus").change(function () {
        if ($("#subSubStatus").val() == 5){
            $("#managementData").show();
        }else{
            $("#managementData").hide();
        }
        if ($("#subSubStatus").val() == 4){
            $("#tipoAsistenciaDiv").show();
        }
        else{
            $("#tipoAsistenciaDiv").hide();
        }
        if ($("#subSubStatus").val() == 51){
            $("#managementData").show();
            $("#tipoAsistenciaDiv").show();
        }

        //LOGICA PARA DIFERIDO CU1

        if ($("#subSubStatus").val() == 50){
            $("#managementData").show();
        }


    });*/

    //Cuando cambia el check PROTECCION FRAUDES
    $("#fraudes").change(function () {
        $("#cobroProteccionFraudes").val($("#cobroProteccionFraudes option:first").val());
        if($(this).prop('checked')){
            $("#cobroDiv").show();
        }else
            $("#cobroDiv").hide();
    });


    //Cuando se escribe la cédula
    $('#cedula').keyup(function(){
        if($(this).val().trim().length == 10){
            var query = $(this).val();
            if(query != ''){
                load_data(query);

            }else{
                load_data();
            }
        }
    });


    //Cuando se GUARDA EL ADICIONAL
    $("#btnGuardarAdicional").click(function (e) {
        if(!validateAdicionalData()){
            e.preventDefault();
            return false;
        }
        $idCliente = $("#idCliente").val().trim();
        $cedula = $("#cedula").val().trim();
        $primerNombre = $("#primerNombre").val().trim();
        $segundoNombre = $("#segundoNombre").val().trim();
        $primerApellido = $("#primerApellido").val().trim();
        $segundoApellido = $("#segundoApellido").val().trim();
        $nombreTarjeta = $('input:radio[name=nombreTarjeta]:checked').val();
        $cupoOtorgado = $("#cupoOtorgado").val().trim();
        $sexo = $("#sexo").val().trim();
        $parentesco = $("#parentesco").val().trim();
        $fechaNacimiento = $("#fechaNacimiento").val().trim();
        $estadoCivil = $("#estadoCivilAdicional").val().trim();
        $nacionalidad = $("#nacionalidadAdicional").val().trim();
        $.get(baseUrl + "/FuncionesAjax/addAdicionalToList",
            {idCliente: $idCliente, cedula: $cedula, primerNombre: $primerNombre, segundoNombre: $segundoNombre, primerApellido: $primerApellido, segundoApellido: $segundoApellido,
                nombreTarjeta: $nombreTarjeta, cupoOtorgado: $cupoOtorgado, sexo: $sexo, parentesco: $parentesco,
                fechaNacimiento: $fechaNacimiento, estadoCivil: $estadoCivil, nacionalidad: $nacionalidad},
            function(data){
                cleanAdicionalData();
                if(data == 'true'){
                    alert("Adicional agregado");
                    //$("#send").show();
                }
            });
    });


    //Cuando sale de foco el SEGUNDO NOMBRE
    $("#segundoNombre").focusout(function () {
        if($("#segundoNombre").val().trim() != ''){
            $("#radioSegundoNombre").prop('disabled', false);
        }else {
            $("#radioPrimerNombre").prop('checked', true);
            $("#radioSegundoNombre").prop('disabled', true);
        }
    });

    //Cuando se cambia la PROVINCIA DE DOMICILIO
    $("#provinciaDomicilioGestion").change(function (data) {
        emptySelect("ciudadDomicilioGestion");
        emptySelect("cantonDomicilio");
        emptySelect("parroquiaDomicilio");
        if($("#provinciaDomicilioGestion").val() != ""){
            $idProvincia = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $idProvincia}, function (data) {
                fillSelect("ciudadDomicilioGestion", data)
            });
            $.get(baseUrl + "/FuncionesAjax/getCantones", {id: $idProvincia}, function (data) {
                fillSelect("cantonDomicilio", data)
            })
        }
    });

    //Cuando se cambia la PROVINCIA DE NACIMIENTO
    $("#provinciaNacimiento").change(function (data) {
        emptySelect("ciudadNacimiento");
        if($("#provinciaNacimiento").val() != ""){
            $idProvincia = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $idProvincia}, function (data) {
                fillSelect("ciudadNacimiento", data)
            });
        }
    });

    //Cuando se cambia la PROVINCIA DE ENTREGA
    $("#provinciaEntrega").change(function (data) {
        emptySelect("ciudadEntrega");
        //emptySelect("cantonDomicilio");
        //emptySelect("parroquiaDomicilio");
        if($("#provinciaEntrega").val() != ""){
            $idProvincia = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudadesEntrega", {id: $idProvincia}, function (data) {
                fillSelect("ciudadEntrega", data)
            });
        }
    });
    //Cuando se cambia la CIUDAD DE ENTREGA
    $("#ciudadEntrega").change(function (data) {
        emptySelect("oficinaTarjeta");
        if($("#ciudadEntrega").val() != ""){
            $idCiudad = this.value;
            $.get(baseUrl + "/FuncionesAjax/getParroquiasAgencias", {id: $idCiudad}, function (data) {
                fillSelect("oficinaTarjeta", data)
            });
        }
    });
    //Cuando se cambia la PARROQUIA DE ENTREGA
    $("#parroquiaEntrega").change(function (data) {
        emptySelect("oficinaTarjeta");
        if($("#parroquiaEntrega").val() != ""){
            $idParroquia = this.value;
            $.get(baseUrl + "/FuncionesAjax/getAgencias", {id: $idParroquia}, function (data) {
                fillSelect("oficinaTarjeta", data)
            });
        }
    });

    //Cuando se cambia el CANTON DE DOMICILIO
    $("#ciudadDirecion").change(function (data) {
        emptySelect("parroquiaDirecion");
        if($("#ciudadDirecion").val() != ""){
            $idCanton = this.value;
            $.get(baseUrl + "/FuncionesAjax/getParroquiasAgencias", {id: $idCanton}, function (data) {
                fillSelect("parroquiaDirecion", data)
            });
        }
    });

    //Cuando se cambia la PROVINCIA DE TRABAJO
    $("#provinciaDirecion").change(function (data) {
        emptySelect("ciudadDirecion");
        if($("#provinciaDirecion").val() != ""){
            $idProvincia = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudadesEntrega", {id: $idProvincia}, function (data) {
                fillSelect("ciudadDirecion", data)
            });
        }
    });

    //Cuando se cambia la PROVINCIA DE REFERENCIAS
    $("#provinciaAgencia").change(function (data) {
        emptySelect("ciudadAgencia");
        //emptySelect("cantonDomicilio");
        //emptySelect("parroquiaDomicilio");
        if($("#provinciaAgencia").val() != ""){
            $idProvincia = this.value;
            $.get(baseUrl + "/FuncionesAjax/getCiudades", {id: $idProvincia}, function (data) {
                fillSelect("ciudadAgencia", data)
            });
           /* $.get(baseUrl + "/FuncionesAjax/getCantones", {id: $idProvincia}, function (data) {
                fillSelect("ciudad", data)
            })*/
        }
    });

    $("#lugarEnvio").change(function () {
        $("#divDireccion").hide();
        $("#divAgencia").hide();
        $("#divTerceraPersona").hide();
        $("#telefonoContactoGestion").val("");
        $("#provinciaAgencia").val($("#provinciaAgencia option:first").val());
        $("#ciudadAgencia").val($("#ciudadAgencia option:first").val());
        $("#motivoDevolucionesGestion").val($("#motivoDevolucionesGestion option:first").val());
        $("#celularContactoGestion").val("");
        $("#telefonoContactoGestionDir").val("");
        $("#emailGestion").val("");
        $("#nombreTerceraPersona").val("");
        $("#callePrincipalEntrega").val("");
        $("#nomenclaturaEntrega").val("");
        $("#calleSecundariaEntrega").val("");
        $("#referenciaEntrega").val("");
        $("#respuestaTipo").val($("#respuestaTipo option:first").val());
        $("#provinciaDirecion").val($("#provinciaDirecion option:first").val());
        $("#ciudadDirecion").val($("#ciudadDirecion option:first").val());
        $("#personaContacto").val($("#personaContacto option:first").val());
        $("#parentescoTerceraPersona").val($("#parentescoTerceraPersona option:first").val());
        $("#horarioEntrega").val($("#horarioEntrega option:first").val());
        if($(this).val() == 'AGENCIA'){
            $("#divAgencia").show();
        }
        if($(this).val() == 'DIRECCION'){
            $("#divDireccion").show();
        }

    });

    $("#personaContacto").change(function () {
        $("#divTerceraPersona").hide();
        if($(this).val() == 'TERCERA PERSONA'){
            $("#divTerceraPersona").show();
        }else {
            $("#divTerceraPersona").hide();
        }

    });

    //Cuando cambia el discrepancias
    $("#discrepancias").change(function () {
        $("#divDiscrepancias").hide();
        $("#detalleDiscrepancias").val($("#detalleDiscrepancias option:first").val());
        $("#detalleOtros").val("");
        $("#divDetalleDiscrepancias").hide();
        if($(this).val() == 'SI'){
            $("#divDiscrepancias").show();
        }else {
            $("#divDiscrepancias").hide();
        }

    });

    $("#detalleDiscrepancias").change(function () {
        $("#divDetalleDiscrepancias").hide();
        $("#detalleOtros").val("");
        if($(this).val() == 'OTROS..Cual ?'){
            $("#divDetalleDiscrepancias").show();
        }else{
            $("#divDetalleDiscrepancias").hide();
        }
    });


    //Cuando se quiere GUARDAR LA GESTION
    $("#send").click(function (e) {
        if(!validateManagementData()){
            e.preventDefault()
            return false
        }else{
            $("#send").hide();
        }
    });

});


//Validación de los DATOS DE GESTION
function validateManagementData() {
    $isValid = true

    if ($("#number1").is(":visible")) {
        if ($("#estadoTel1").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 1");
            $isValid = false;
            return
        }
    }
    if ($("#number2").is(":visible")) {
        if ($("#estadoTel2").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 2");
            $isValid = false;
            return
        }
    }
    if ($("#number3").is(":visible")) {
        if ($("#estadoTel3").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 3");
            $isValid = false;
            return
        }
    }
    if ($("#number4").is(":visible")) {
        if ($("#estadoTel4").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 4");
            $isValid = false;
            return
        }
    }
    if ($("#number5").is(":visible")) {
        if ($("#estadoTel5").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 5");
            $isValid = false;
            return
        }
    }
    if ($("#number6").is(":visible")) {
        if ($("#estadoTel6").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 6");
            $isValid = false;
            return
        }
    }
    if ($("#number7").is(":visible")) {
        if ($("#estadoTel7").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 7");
            $isValid = false;
            return
        }
    }
    if ($("#number8").is(":visible")) {
        if ($("#estadoTel8").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 8");
            $isValid = false;
            return
        }
    }
    if ($("#number9").is(":visible")) {
        if ($("#estadoTel9").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 9");
            $isValid = false;
            return
        }
    }
    if ($("#number10").is(":visible")) {
        if ($("#estadoTel10").val() == "") {
            alert("Debe seleccionar un estado para el numero de Telefono 10");
            $isValid = false;
            return
        }
    }

    if ($("#status").val() == "") {
        alert("Debe escoger un estado");
        $isValid = false;
        return
    } else {
        if ($("#substatus").val() == "") {
            alert("Debe escoger un subestado");
            $isValid = false
            return
        } else {
            if ($("#recall").is(":visible")) {
                if ($("#agendamiento").val() == "") {
                    alert("Campo agendamiento vacio")
                    $isValid = false;
                    return
                }
                if ($("#recall").val() == "") {
                    alert("Debe ingresar una fecha para la rellamada")
                    $isValid = false;
                    return
                } else {
                    if (calcularDias($("#recall").val()) > 3) {
                        alert("La fecha de rellamada no puede sobrepasar los tres días")
                        $isValid = false;
                        return
                    }
                }
            }
            if ($("#subSubStatus").is(":visible")) {
                if ($("#subSubStatus").val() == "") {
                    alert("Debe escoger un sub subestado")
                    $isValid = false;
                    return
                }
            }
           /* if ($divMotivo.is(":visible")) {
                if ($("#motivoNoAceptaSeguro").val() == "") {
                    alert("Debe escoger el motivo no desea seguro")
                    $isValid = false;
                    return
                }
            }*/
            if ($telefonoContactadoDiv.is(":visible")) {
                if ($telefonoContactado.val() === "") {
                    alert("Ingrese el teléfono al cual pudo contactar al cliente");
                    $isValid = false;
                    return
                } else {
                    if ($telefonoContactado.val().substring(0, 1) != 0) {
                        alert("El teléfono contactado es incorrecto");
                        $isValid = false;
                        return
                    } else {
                        if (!validarSiNumero($telefonoContactado.val())) {
                            alert("El teléfono contactado no es un número válido");
                            $isValid = false;
                            return
                        }else{
                            if ($telefonoContactado.val().length ==  9 && $telefonoContactado.val().trim().substring(0, 2) == "09" ) {
                                alert("Esta ingresando un telefono convencional incorrecto. Verifique!");
                                $isValid = false;
                                return
                            }else {
                                if ($telefonoContactado.val().length ==  10 && $telefonoContactado.val().trim().substring(0, 2) != "09" ) {
                                    alert("Esta ingresando un telefono celular incorrecto. Verifique!");
                                    $isValid = false;
                                    return
                                }
                            }
                        }
                    }
                }
                if ($("#estadoTelefonoContactado").val() === "") {
                    alert("Ingrese el estado del teléfono contactado");
                    $isValid = false;
                    return
                }

            }
        }
    }

    if ($("#managementData").is(":visible")) {

        if ($("#lugarEnvio").val() == "") {
            alert("Campo lugar envío vacio");
            $isValid = false;
            return;
        }

        if ($("#divAgencia").is(":visible")) {
            if ($("#provinciaAgencia").val() == "") {
                alert("Seleccione la provincia de la agencia");
                $isValid = false;
                return;
            }
            if ($("#ciudadAgencia").val() == "") {
                alert("Seleccione la ciudad de la agencia");
                $isValid = false;
                return;
            }
            if ($("#motivoDevolucionesGestion").val() == "") {
                alert("Seleccione el motivo de devolución");
                $isValid = false;
                return;
            }
            if ($("#telefonoContactoGestion").val() === "") {
                alert("Ingrese el teléfono de contacto");
                $isValid = false;
                return
            } else {
                if ($("#telefonoContactoGestion").val().substring(0, 1) != 0) {
                    alert("El teléfono de contacto es incorrecto");
                    $isValid = false;
                    return
                } else {
                    if (!validarSiNumero($("#telefonoContactoGestion").val())) {
                        alert("El teléfono de contacto no es un número válido");
                        $isValid = false;
                        return
                    }else{
                        if ($("#telefonoContactoGestion").val().length ==  9 && $("#telefonoContactoGestion").val().trim().substring(0, 2) == "09" ) {
                            alert("Esta ingresando un telefono convencional incorrecto. Verifique!");
                            $isValid = false;
                            return
                        }else {
                            if ($("#telefonoContactoGestion").val().length ==  10 && $("#telefonoContactoGestion").val().trim().substring(0, 2) != "09" ) {
                                alert("Esta ingresando un telefono celular incorrecto. Verifique!");
                                $isValid = false;
                                return
                            }
                        }
                    }
                }
            }

            if ($("#celularContactoGestion").val() == "") {
                alert("Campo celular de contacto vacío");
                $isValid = false;
                return
            }else{
                if ($("#celularContactoGestion").val().substring(0, 1) != 0) {
                    alert("El celular de contacto es incorrecto");
                    $isValid = false;
                    return
                } else {
                    if (!validarSiNumero($("#celularContactoGestion").val())) {
                        alert("El celular de contacto no es un número válido");
                        $isValid = false;
                        return
                    }else{
                        if ($("#celularContactoGestion").val().length ==  10 && $("#celularContactoGestion").val().trim().substring(0, 2) != "09" ) {
                            alert("Esta ingresando un celular incorrecto. Verifique!");
                            $isValid = false;
                            return
                        }
                    }
                }
            }
            if ($("#emailGestionAgencia").val() != "" && !validarEmail($("#emailGestionAgencia").val())) {
                alert("El Email ingresado es incorrecto.");
                $isValid = false;
                return;
            }

        }

        if ($("#divDireccion").is(":visible")) {
            if ($("#respuestaTipo").val() == "") {
                alert("Campo respuesta tipo vacio");
                $isValid = false;
                return;
            }
            if ($("#provinciaDirecion").val() == "") {
                alert("Seleccione la provincia del domicilio");
                $isValid = false;
                return;
            }
            if ($("#ciudadDirecion").val() == "") {
                alert("Seleccione la ciudad del domicilio");
                $isValid = false;
                return;
            }
            if ($("#telefonoContactoGestionDir").val() === "") {
                alert("Ingrese el teléfono de contacto");
                $isValid = false;
                return
            } else {
                if ($("#telefonoContactoGestionDir").val().substring(0, 1) != 0) {
                    alert("El teléfono de contacto es incorrecto");
                    $isValid = false;
                    return
                } else {
                    if (!validarSiNumero($("#telefonoContactoGestionDir").val())) {
                        alert("El teléfono de contacto no es un número válido");
                        $isValid = false;
                        return
                    }
                }
            }

            if ($("#celularContactoGestionDir").val() == "") {
                alert("Campo celular de contacto vacío");
                $isValid = false;
                return
            }else{
                if ($("#celularContactoGestionDir").val().substring(0, 1) != 0) {
                    alert("El celular de contacto es incorrecto");
                    $isValid = false;
                    return
                } else {
                    if (!validarSiNumero($("#celularContactoGestionDir").val())) {
                        alert("El celular de contacto no es un número válido");
                        $isValid = false;
                        return
                    }else{
                        if ($("#celularContactoGestionDir").val().length ==  10 && $("#celularContactoGestionDir").val().trim().substring(0, 2) != "09" ) {
                            alert("Esta ingresando un celular incorrecto. Verifique!");
                            $isValid = false;
                            return
                        }
                    }
                }
            }

                /*
                if ($("#emailGestionDireccion").val() != "" && !validarEmail($("#emailGestionDireccion").val())) {
                    alert("El Email ingresado es incorrecto.");
                    $isValid = false;
                    return;
                }
                */

            if ($("#personaContacto").val() == "") {
                alert("Campo Persona contacto vacío");
                $isValid = false;
                return;
            }
            if ($("#divTerceraPersona").is(":visible")) {
                if ($("#nombreTerceraPersona").val() == "") {
                    alert("Campo nombre tercera persons vacío");
                    $isValid = false;
                    return;
                }
                if ($("#parentescoTerceraPersona").val() == "") {
                    alert("Campo parentesco tercera persona vacío");
                    $isValid = false;
                    return;
                }
            }
            if ($("#horarioEntrega").val() == "") {
                alert("Campo horario de entrega vacío");
                $isValid = false;
                return;
            }

            if ($("#callePrincipalEntrega").val() == "") {
                alert("Campo Calle principal vacío");
                $isValid = false;
                return;
            }
            if ($("#nomenclaturaEntrega").val() == "") {
                alert("Campo Num. Casa vacío");
                $isValid = false;
                return;
            }
            if ($("#calleSecundariaEntrega").val() == "") {
                alert("Campo calle secundaria vacío");
                $isValid = false;
                return;
            }
            if ($("#referenciaEntrega").val() == "") {
                alert("Campo referencia vacío");
                $isValid = false;
                return;
            }


        }
    }

            if ($("#discrepancias").val() == "") {
                alert("Campo discrepancias vacìo");
                $isValid = false;
                return;
            }
            if ($("#divDiscrepancias").is(":visible")) {
                if ($("#detalleDiscrepancias").val() == "") {
                    alert("Campo detalle discrepancias vacío");
                    $isValid = false;
                    return;
                }
                if ($("#divDetalleDiscrepancias").is(":visible")) {
                    if ($("#detalleOtros").val() == "") {
                        alert("Campo detalle otros vacío");
                        $isValid = false;
                        return;
                    }

                }

            }





    return $isValid;
}


//Para cuando se INICIA LA GESTION
function init() {
    esconderCamposEstados();
    //$("#telefonoContactadoDiv").hide();
    $("#status").val($("#status option:first").val());
    $("#estadoTelefonoContactado").val($("#status option:first").val());
    $("#sexo").val($("#sexo option:first").val());
    $("#estadoCivil").val($("#estadoCivil option:first").val());
    $("#parentesco").val($("#parentesco option:first").val());
    $("#provinciaDomicilioGestion").val($("#estadoCivil option:first").val());
    $("#provinciaTrabajoGestion").val($("#estadoCivil option:first").val());
    $("#radioPrimerNombre").prop('checked', true);
    $("#radioSegundoNombre").prop('disabled', true);
    $("#tipoCliente").val($("#tipoCliente option:first").val());
    $("#dataIndependiente").hide();
    $("#dataDependiente").hide();
    $("#dataJubilado").hide();
    $("#divDiferenteValor").hide();
    $("#divEscenarioIdeal").hide();
    $("#divEscenario12").hide();
    $("#divEscenario24").hide();
    $("#divEscenario36").hide();
    $("#divCuentaNueva").hide();
    $("#btnAdicional").attr("disabled", true);
}

//This function empties a select component
function emptySelect(idSelect) {

    var select = document.getElementById(idSelect);
    var option = document.createElement('option');
    var NumberItems = select.length;

    while (NumberItems > 0) {
        NumberItems--;
        select.remove(NumberItems);
    }

    option.text = '-- Seleccione --';
    option.value = ''
    select.add(option, null);
}

//This function fills a select component
function fillSelect(idSelect, data) {

    var select = document.getElementById(idSelect);
    var numberSubstatus = data[0].length;

    emptySelect(idSelect)

    if (numberSubstatus > 0) {
        for (i = 0; i < numberSubstatus; i++) {
            var option = document.createElement('option');
            option.text = data[1][i];
            option.value = data[0][i];
            select.add(option, null);
        }
    }

    return numberSubstatus;
}


/**
 *@description Funcion que evita que puedan ingresar numeros en campos
 * @author Andres Redroban
 * */

function checkIfNumberNoSpace(codeKey,e){
    if (codeKey == 32)
        return 0;
    // Asignando numero y no espacios
    if ($.inArray(codeKey, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
        // Allow: Ctrl+A
        (codeKey == 97 && e.ctrlKey === true) ||
        // Allow: Ctrl+C
        (codeKey == 99 && e.ctrlKey === true) ||
        // Allow: Ctrl+X
        (codeKey == 120 && e.ctrlKey === true) ||
        // Allow: home, end, left, right, tab
        (codeKey == 0)) {
        // let it happen, don't do anything
        return 1;
    }
    if ((codeKey < 48 || codeKey > 57)) {
        return 0;
    }
}
function soloLetras(e){
    key = e.keyCode || e.which;
    tecla = String.fromCharCode(key).toLowerCase();
    letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
    especiales = "8-37-39-46";

    tecla_especial = false
    for(var i in especiales){
        if(key == especiales[i]){
            tecla_especial = true;
            break;
        }
    }

    if(letras.indexOf(tecla)==-1 && !tecla_especial){
        return false;
    }
}


/**
 * Valida si el valor ingresado es numérico
 * @param numero
 */
function validarSiNumero(numero){
    $esNumero = true;
    if (!/^([0-9])*$/.test(numero)){
        $esNumero = false;
    }
    return $esNumero;
}
/**
 * Valida si el correo ingresado es correcto
 * @param email
 * @author Andres Redroban
 */
function validarEmail(email)
{
    var regex = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email) ? true : false;
}

function validarSiSoloLetras(entrada){
    $esSoloTexto = true;
    $filtro = /^([A-Za-z ])*$/;
    if(!$filtro.test(entrada)){
        $esSoloTexto = false;
    }
    return $esSoloTexto;
}

/**
 * Valida si el numero ingresado contiene espacios en blanco
 * @param validar
 * @author Andres Redroban
 */
function validarVacio(validar) {
    $esVacio = true;
    for ( i = 0; i < validar.length; i++ ) {
        if ( validar.charAt(i) == " " ) {
            $esVacio = false
        }
    }
    return $esVacio
}

/**
 * Función bajada de internet https://es.stackoverflow.com/questions/31373/obtener-la-edad-a-partir-de-la-fecha-de-nacimiento-con-javascript-y-php
 * @param fecha
 * @returns {number}
 */
function calcularEdad(fecha) {
    var hoy = new Date();
    var cumpleanos = new Date(fecha);
    var edad = hoy.getFullYear() - cumpleanos.getFullYear();
    var m = hoy.getMonth() - cumpleanos.getMonth();

    if (m < 0 || (m === 0 && hoy.getDate() < cumpleanos.getDate())) {
        edad--;
    }

    return edad;
}
/**
 * Función tomada como ejemplo de internet https://www.lawebdelprogramador.com/foros/JavaScript/1594805-Calcular-la-cantidad-de-dias-entre-dos-fechas-Javascript-y-HTML.html
 * @param fecha
 * @returns {contdias}
 * @author Andres Redrobán
 * @description La siguiente función calcula el numero de dias tomando como referencia el facha actual y la fecha ingresada desde el sistema.
 */
function calcularDias(fecha){
    var fechaini = new Date();
    var fechafin = new Date(fecha);
    var diasdif= fechafin.getTime()-fechaini.getTime();
    var contdias = Math.round(diasdif/(1000*60*60*24));
    return contdias;
}

/**
 * Funcion para validar una fecha en formato YYYY-MM-DD
 * @param fecha
 * @return {boolean}
 * @author Andres Redrobán
 */

function validarFecha(fecha){
    var fechaArr = fecha.split('/');
    var anio = fechaArr[0];
    var mes = fechaArr[1];
    var dia = fechaArr[2];

    var plantilla = new Date(anio, mes - 1, dia);//mes empieza de cero Enero = 0

    if(!plantilla || plantilla.getFullYear() == anio && plantilla.getMonth() == mes -1 && plantilla.getDate() == dia){
        return true;
    }else{
        return false;
    }
}

//Para limpiar luego de agregar un adicional
function cleanAdicionalData() {
    $("#cedula").val("");
    $("#primerNombre").val("");
    $("#segundoNombre").val("");
    $("#primerApellido").val("");
    $("#segundoApellido").val("");
    $("#radioPrimerNombre").prop('checked', true);
    $("#radioSegundoNombre").prop('disabled', true);
    $("#cupoOtorgado").val("");
    $("#sexo").val($("#sexo option:first").val());
    $("#parentesco").val($("#parentesco option:first").val());
    $("#fechaNacimiento").val("");
    $("#observaciones").val("");
    $("#estadoCivilAdicional").val($("#estadoCivilAdicional option:first").val());
    $("#nacionalidadAdicional").val("");
}

function load_data(query){
    $.post(baseUrl + "/FuncionesAjax/searchUser", {
        query:query
    }).done(function (data) {

        //$('#result').html(data);
        if(data == 'null'){

        }else{
            alert('ya tienes este usuario en la base de datos con la misma cedula');
        }
    });
}

function esconderCamposEstados(){
    $("#motivoNoDesea").val($("#motivoNoDesea option:first").val());
    $("#cobroProteccionFraudes").val($("#cobroProteccionFraudes option:first").val());
    $("#recallDiv").hide();
    $("#subSubStatusDiv").hide();
    $("#managementData").hide();
    //$("#send").hide();
    $("#motNoDeseaDiv").hide();
    $("#tipoAsistenciaDiv").hide();
    $("#exequial").prop('checked', false);
    $("#fraudes").prop('checked', false);
    $("#cobroDiv").hide();
    $("#agenciaGroup").hide();
    $("#divDiscrepancias").hide();
    $("#divDetalleDiscrepancias").hide();
    $("#divAgencia").hide();
    $("#divDireccion").hide();
    $("#divTerceraPersona").hide();
    $("#lugarEnvio").val($("#lugarEnvio option:first").val());
    $("#discrepancias").val($("#discrepancias option:first").val());
    $("#detalleDiscrepancias").val($("#detalleDiscrepancias option:first").val());
    $("#detalleOtros").val("");
    $divMotivo.hide();
}

function limpiarTipoCliente(){
    $("#nombreNegocio").val("");
    $("#fechaInicioNegocio").val("");
    $("#actividadEconomica").val("");
    $("#ventasHonorariosMensuales").val("");
    $("#costoVentas").val("");
    $("#gastosOperativos").val("");
    $("#situacionLaboral").val($("#situacionLaboral option:first").val());
    $("#nombreEmpresaGestion").val("");
    $("#contrato").val($("#contrato option:first").val());
    $("#cargo").val("");
    $("#fechaInicioTrabajoActual").val("");
    $("#sueldo").val("");
    $("#gastosFamilia").val("");
}