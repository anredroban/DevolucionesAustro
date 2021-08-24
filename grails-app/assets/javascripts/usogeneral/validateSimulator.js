$(document).ready(function() {
    $("#btnEnviar").click(function (e) {
        if(!validateManagementData()){
            e.preventDefault()
            return false
        }
    });

    $('#cedula').on('keypress', function(e) {
        if(checkIfNumberNoSpace(e.which, e)==0){
            return false;
        }else{
            return;
        }
    });
    $('#fechas').on('keypress', function(e) {
        if(checkIfNumberNoSpace(e.which, e)==0){
            return false;
        }else{
            return;
        }
    });

});


function validateManagementData() {
    $isValid = true
    if($("#cedula").val() == ""){
        alert("Debe ingresar la cedula del cliente para calcular.");
        $isValid = false;
        return
    }/*else{
        if($("#cedula").length > 10 && $("#cedula").length < 14){
            alert("La cédula debe contener 10 o 14 caracteres.");
            $isValid = false;
            return
        }
    }*/
    if($("#fechas").val() == ""){
        alert("Debe ingresar el monto seleccionado por el cliente para calcular.");
        $isValid = false;
        return
    }
    if($("#sobregiro").val() == ""){
        alert("Debe ingresar el plazo seleccionado po el cliente para calcular.");
        $isValid = false;
        return
    }
    return $isValid;
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
