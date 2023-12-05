var eleccion = 0; // Si eleccion = 1 toco btnPagoGeneral, si eleccion = 2 toco btnPagoPaquete

$(document).ready(function(){ // Esto pasa cuando se carga la pagina
    $("#confirmar").hide();
    $("#camposPagoGeneral").hide();
    $("#camposPagoPaquete").hide();
    $("#comproConGeneral").hide();
    $("#comproConPaquete").hide();
    $("#seleccionPaquete").hide();

    $("#warningTipoDeOferta").hide();
    $("#warningNombre").hide();
    $("#warningDescripcion").hide();
    $("#warningHorario").hide();
    $("#warningRemuneracion").hide();
    $("#warningDepartamento").hide();
    $("#warningCiudad").hide();
    $("#warningSeleccionPaquete").hide();
});

$("#btnPagoGeneral").click(function(){
    $("#camposPagoGeneral").show();
    $("#camposPagoPaquete").hide();
    $("#confirmar").show();
    $("#seleccionPaquete").hide();
    $("#warningSeleccionPaquete").hide();
    eleccion = 1;
});

$("#btnPagoPaquete").click(function () {
    tdo = $("#tiposdeO").val();
    if(tdo == "0")
        $("#warningTipoDeOferta").show();
    else{
        $("#warningTipoDeOferta").hide();
        $("#camposPagoPaquete").show();
        $("#camposPagoGeneral").hide();
        $("#seleccionPaquete").show();
        $("#confirmar").show();
        }
    eleccion = 2;
});

 /*Esta funcion es para que no mande los datos del formulario */
$("#formulario").on('submit', function (evt) {
    evt.preventDefault();
});

const fileInput = document.getElementById('fileInput');
    const uploadedImage = document.getElementById('uploadedImage');

    fileInput.addEventListener('change', function() {
        const file = fileInput.files[0];
        if (file) {
            const reader = new FileReader();

            reader.onload = function(e) {
                uploadedImage.src = e.target.result;
            };

            reader.readAsDataURL(file);
        } else {
            uploadedImage.src = '';
        }
    });

$("#confirmar").click(function () {
    const tiposdeO = $("#tiposdeO").val();
    const nombreO = $("#nombreO").val();
    const descripcion = $("#descripcion").val();
    const horaCom = $("#horaCom").val();
    const horaFin = $("#horaFin").val();
    const remuneracion = $("#remuneracion").val();
    const departamento = $("#departamento").val();
    const ciudad = $("#ciudad").val();
    
    /* Chequeo que no hayan campos vacios  */
    var correcto = true;
    
    // Tipo de oferta
    if(tiposdeO == 0){
        correcto = false;
        $("#warningTipoDeOferta").show();
    }
    else{
        $("#warningTipoDeOferta").hide();
    }
    
    // Nombre
    if (nombreO.trim().replace(/\s+/g, '').length == 0) { // Esto saca los espacos vacios del campo
        correcto = false;
        $("#warningNombre").show();
    }
    else 
        $("#warningNombre").hide();
    
    // Descripcion
    if (descripcion.trim().replace(/\s+/g, '').length == 0) {
        correcto = false;
        $("#warningDescripcion").show();
    }
    else 
        $("#warningDescripcion").hide();
    
    // Horario
    if (horaCom.length == 0 || horaFin.length == 0 ) {
        correcto = false;
        $("#warningHorario").show();
    }
    else 
        $("#warningHorario").hide();
    
    // Remuneracion
    if (remuneracion.trim().replace(/\s+/g, '').length == 0 || remuneracion.trim().replace(/\s+/g, '') <= 0) {
        correcto = false;
        $("#warningRemuneracion").show();
    }
    else 
        $("#warningRemuneracion").hide();
    
    // Departamento
    if (departamento.trim().replace(/\s+/g, '').length == 0) {
        correcto = false;
        $("#warningDepartamento").show();
    }
    else 
        $("#warningDepartamento").hide();
    
    // Ciudad
    if (ciudad.trim().replace(/\s+/g, '').length == 0) {
        correcto = false;
        $("#warningCiudad").show();
    }
    else 
        $("#warningCiudad").hide();

    // Seleccion paquete cuando se estan mostrando los campos de compra de paquete
    if ($("#camposPagoPaquete").is(":visible"))
        if ($("#seleccionPaquete").val() == 0) {
            correcto = false;
            $("#warningSeleccionPaquete").show();
        }
        else {
            $("#warningSeleccionPaquete").hide();
        }

    // Mensajes al darle a confirmar cuando estan todos los campos completos    
    if(correcto){
        if (eleccion == 1) {
            $("#formulario")[0].submit();
        }
        else if (eleccion == 2) {  
            $("#formulario")[0].submit();
        } 
    }
});
