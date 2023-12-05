$(document).ready(function () {
    $("#warningCero").hide();

    var selects = $("select[id^='orden_']");
    var opcionesElegidas = {};

    selects.change(function () {
        var valor = $(this).val();
        var selectId = $(this).attr('id');

        selects.find("option").prop("disabled", false);

        if (valor != 0) {
            selects.not(this).find("option[value='" + valor + "']").prop("disabled", true);

            opcionesElegidas[selectId] = valor;

            $.each(opcionesElegidas, function (id, val) {
                if (id !== selectId) {
                    selects.not("#" + id).find("option[value='" + val + "']").prop("disabled", true);
                }
            });
        } else {
            delete opcionesElegidas[selectId];

            $.each(opcionesElegidas, function (id, val) {
                selects.not("#" + id).find("option[value='" + val + "']").prop("disabled", true);
            });
        }
    });
});

$("#formulario").on('submit', function(evt){
    	evt.preventDefault();  
	});  

    function validarFormulario() {
        var selects = $("select[id^='orden_']");
        var correcto = true;

    	selects.each(function () {
        	var valor = $(this).val();
        	if (valor == 0) {
            	correcto = false;
        	}
    	});
  
        if(correcto){
			$("#warningCero").hide();
			$("#formulario")[0].submit();
		}else{
			$("#warningCero").show();
		}
    }  

    
