$(document).ready(function(){
	$("#formularioPopup").hide();
});

$("#mostrarPopup").click(function() {
    $("#video").val("");
    $("#motivacion").val("");
    $("#cv_reducido").val("");
    $("#warningCV").hide();
    $("#warningMot").hide();
    $("#warningVid").hide();

    $("#formularioPopup").fadeIn();
});

$("#formulario").on('submit', function(evt){
    evt.preventDefault();  
});

$("#botonPost").click(function() {
    $("#video").val("");
    $("#motivacion").val("");
    $("#cv_reducido").val("");
    $("#warningCV").hide();
    $("#warningVid").hide();

    $("#warningMot").hide();
});

$("#cancelar").click(function() {
    $("#formularioPopup").fadeOut();
    $("#formularioPopup").modal("hide");
});

function validarFormulario(){
    var cvValue = $("#cv_reducido").val();
    var motValue = $("#motivacion").val();
    var vidValue = $("#video").val();
    var correcto = true;
    
    
   

    if (cvValue.trim().replace(/\s+/g, '').length == 0){
        correcto = false;
        $("#warningCV").show();
    } else {
        $("#warningCV").hide();
    } 

    if (motValue.trim().replace(/\s+/g, '').length == 0) {
        correcto = false;
        $("#warningMot").show();
    } else {
        $("#warningMot").hide();
    }
    
      if (vidValue != ""){
		  if(!vidValue.startsWith("https://www.youtube.com/")){
			  correcto = false;
       		  $("#warningVid").show();
		  }
    	}
    
    if (correcto){
        $("#formulario")[0].submit();
        $("#formularioPopup").modal("hide");
        $("#formularioPopup").hide();
    }
}

