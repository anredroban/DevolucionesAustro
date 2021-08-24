$(document).ready(function() {
    $("#btnEnviar").click(function (e) {
        if(!validateManagementData()){
            e.preventDefault()
            return false
        }
    });
});


function validateManagementData() {
    $isValid = true
    if($("#cedula").val() == ""){
        alert("Debe ingresar la cedula del cliente para calcular.");
        $isValid = false;
        return
    }
    if($("#fechas").val() == ""){
        alert("Debe ingresar el monto seleccionado po el cliente para calcular.");
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
