$(document).ready(function(){
    $('ul.tabs li a:first').addClass('active');
    $('.secciones article').hide();
    $('.secciones article:first').show();
    $("#warningLink").hide();
    $("#warningNombre").hide();
    $("#warningApellido").hide();
    $("#warningDescripcion").hide();
    $("#warningNombrePostulante").hide();
    $("#warningApellidoPostulante").hide();
    $("#warningNacionalidadPostulante").hide();
    $("#warningFechaPostulante").hide();
    $("#warningPassword").hide();
    $("#warningCheckpassword").hide();

    $('ul.tabs li a').click(function(){
        $('ul.tabs li a').removeClass('active');
        $(this).addClass('active'); 
        $('.secciones article').hide();

        var activeTab = $(this).attr('href');
        $(activeTab).show();
        return false;
    });

    $("#formulario1").on('submit', function(evt){
        evt.preventDefault();  
    });

    $("#formulario2").on('submit', function(evt){
        evt.preventDefault();  
    });
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

function validarEmpresa(){
    correcto =true;
    var nombre = $("#nombreEmpresa").val(); var apellido = $("#apellidoEmpresa").val(); var link = $("#linkEmpresa").val(); var descripcion = $("#descripcionEmpresa").val(); var password = $("#password").val(); var checkpassword = $("#checkpassword").val();
    let regexUrl = /(https:\/\/www\.|http:\/\/www\.|https:\/\/|http:\/\/)?[a-zA-Z]{2,}(\.[a-zA-Z]{2,})(\.[a-zA-Z]{2,})?\/[a-zA-Z0-9]{2,}|((https:\/\/www\.|http:\/\/www\.|https:\/\/|http:\/\/)?[a-zA-Z]{2,}(\.[a-zA-Z]{2,})(\.[a-zA-Z]{2,})?)|(https:\/\/www\.|http:\/\/www\.|https:\/\/|http:\/\/)?[a-zA-Z0-9]{2,}\.[a-zA-Z0-9]{2,}\.[a-zA-Z0-9]{2,}(\.[a-zA-Z0-9]{2,})?/g;
    if (nombre.trim().replace(/\s+/g, '').length == 0){
        $("#warningNombre").show();
        correcto=false;
    } else
        $("#warningNombre").hide();
    if (apellido.trim().replace(/\s+/g, '').length == 0){
        correcto=false;
        $("#warningApellido").show()
    } else
        $("#warningApellido").hide();
    if (descripcion.trim().replace(/\s+/g, '').length == 0){
        correcto=false;
        $("#warningDescripcion").show()
    } else
        $("#warningDescripcion").hide();
    if (link.trim().replace(/\s+/g, '').length != 0){
        if (!regexUrl.test(link)){
            correcto=false;
            $("#warningLink").show()
        }
        else
            $("#warningLink").hide();
    } else $("#warningLink").hide();
    if (password.trim().replace(/\s+/g, '') != checkpassword.trim().replace(/\s+/g, '')){

		correcto = false;
		$("#warningCheckpassword").show();
	}else
		$("#warningCheckpassword").hide();
	if(password != "")	
		if (password.trim().replace(/\s+/g, '') == ""){
			correcto = false;
			$("#warningPassword").show();
		} else 	$("#warningPassword").hide();
    if(correcto)
        $("#formulario1")[0].submit();
}

function validarPostulante(){
    correcto=true;
    var nombre = $("#nombrePostulante").val(); var apellido = $("#apellidoPostulante").val(); var nacionalidad = $("#nacionalidadPostulante").val(); var fecha = $("#fechaPostulante").val(); var password = $("#password").val(); var checkpassword = $("#checkpassword").val();
    if (nombre.trim().replace(/\s+/g, '').length == 0){
        correcto=false;
        $("#warningNombrePostulante").show();
    } else
        $("#warningNombrePostulante").hide();
    if (apellido.trim().replace(/\s+/g, '').length == 0){
        correcto=false;
        $("#warningApellidoPostulante").show()
    } else
        $("#warningApellidoPostulante").hide();
    if (nacionalidad.trim().replace(/\s+/g, '').length == 0){
        correcto=false;
        $("#warningNacionalidadPostulante").show()
    } else
        $("#warningNacionalidadPostulante").hide();
    if (fecha == ''){
        correcto=false;
        $("#warningFechaPostulante").show()
    }
    else
        $("#warningFechaPostulante").hide();
    if (password.trim().replace(/\s+/g, '') != checkpassword.trim().replace(/\s+/g, '')){
		correcto = false;
		$("#warningCheckpassword").show();
	}else
		$("#warningCheckpassword").hide();
	if (password != "")
		if (password.trim().replace(/\s+/g, '') == ""){
			correcto = false;
			$("#warningPassword").show();
		} else 	$("#warningPassword").hide();
    if(correcto)
        $("#formulario2")[0].submit();
}


