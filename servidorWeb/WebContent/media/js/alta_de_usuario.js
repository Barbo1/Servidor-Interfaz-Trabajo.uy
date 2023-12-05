function createPreview(file){
    var imgCod = URL.createObjectURL(file);
    $("#prevImg").attr('src',imgCod);
}

$(document).ready(function(){
    $("#camposPostulante").hide();
    $("#camposEmpresa").hide();
    $("#btnCrear").hide();

    $("#warningNickname").hide();
    $("#warningNombre").hide();
    $("#warningApellido").hide();
    $("#warningEmail").hide();
    $("#warningPassword").hide();
    $("#warningCheckpassword").hide();
    $("#warningDescripcion").hide();
    $("#warningLink").hide();
    $("#warningNacimiento").hide();
    $("#warningNacionalidad").hide();
    $("#warningNickExistente").hide();
    $("#warningEmailExistente").hide();
    var esEmpresa = true;  

    $("#formulario").on('submit', function(evt){
        evt.preventDefault();  
    });
})

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

function validarFormulario() {
    var correcto = true;
    var nombre = $("#nombre").val(); var nickname = $("#nickname").val(); var apellido = $("#apellido").val();var email = $("#email").val(); var password = $("#password").val();var checkpassword = $("#checkpassword").val();var link = $("#link").val();var descripcion = $("#descripcion").val();var nacionalidad = $("#nacionalidad").val();var nacimiento = $("#nacimiento").val();

    let regexEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    let regexUrl = /(https:\/\/www\.|http:\/\/www\.|https:\/\/|http:\/\/)?[a-zA-Z]{2,}(\.[a-zA-Z]{2,})(\.[a-zA-Z]{2,})?\/[a-zA-Z0-9]{2,}|((https:\/\/www\.|http:\/\/www\.|https:\/\/|http:\/\/)?[a-zA-Z]{2,}(\.[a-zA-Z]{2,})(\.[a-zA-Z]{2,})?)|(https:\/\/www\.|http:\/\/www\.|https:\/\/|http:\/\/)?[a-zA-Z0-9]{2,}\.[a-zA-Z0-9]{2,}\.[a-zA-Z0-9]{2,}(\.[a-zA-Z0-9]{2,})?/g;

    if (nombre.trim().replace(/\s+/g, '').length == 0){
        correcto = false;
        $("#warningNombre").show();
    } else
        $("#warningNombre").hide();

    if (nickname.trim().replace(/\s+/g, '').length == 0){
        correcto = false;
        $("#warningNickname").show()
    } else
        $("#warningNickname").hide();

    if (apellido.trim().replace(/\s+/g, '').length == 0){
        correcto = false;
        $("#warningApellido").show()
    } else
        $("#warningApellido").hide();

    if (!regexEmail.test(email)){
        correcto = false;
        $("#warningEmail").show();
    } else
        $("#warningEmail").hide();

    if (password.trim().replace(/\s+/g, '').length == 0){
        correcto = false;
        $("#warningPassword").show()
    } else
        $("#warningPassword").hide();

    if (password != checkpassword){
        correcto = false;
        $("#warningCheckpassword").show()
    } else
        $("#warningCheckpassword").hide();

    if(esEmpresa){
        if (descripcion.trim().replace(/\s+/g, '').length == 0){
            correcto = false;
            $("#warningDescripcion").show()
        } else
            $("#warningDescripcion").hide();

        if (link.trim().replace(/\s+/g, '').length != 0){
            if (!regexUrl.test(link)){
                correcto = false;
                $("#warningLink").show()
            } else
                $("#warningLink").hide();
        }
    } else{
        if (nacimiento == ''){
            correcto = false;
            $("#warningNacimiento").show()
        } else
            $("#warningNacimiento").hide();

        if (nacionalidad.trim().replace(/\s+/g, '').length == 0){
            correcto = false;
            $("#warningNacionalidad").show()
        } else
            $("#warningNacionalidad").hide();
    }

    if(correcto){
        $("#formulario")[0].submit();
    }
}

$("#email").on("input", function() {
    $.ajax({
        type: "GET",
        url: "/servidorWeb/existeEmail",
        data: {"arg0": $("#email").val()},
        datatype: "xml",
        success: function (data) { 
            if (data.getElementsByTagName("data")[0].textContent == "1") {
                $("#warningEmailExistente").show();
            } else {
                $("#warningEmailExistente").hide();
            }
        }, 
    });
});

$("#btnPostulante").click(function(){
    $("#camposPostulante").show();
    $("#camposEmpresa").hide();
    $("#btnCrear").show();
    esEmpresa = false;
})

$("#btnEmpresa").click(function(){
    $("#camposPostulante").hide();
    $("#camposEmpresa").show();
    $("#btnCrear").show();
    esEmpresa = true;
})

$("#imagen").on("change",function(){
    var files = this.files;
    var element;
    var supportedImages = ["image/jpeg", "image/png","image/gif"];
    if(supportedImages.indexOf(files[0].type) != -1)
        createPreview(files[0]);
    else 
        showMessage("Archivo no valido");
});

$("#nickname").on("input", function() {
    $.ajax({
        type: "GET",
        url: "/servidorWeb/existeNickname",
        data: {"arg0": $("#nickname").val()},
        datatype: "xml",
        success: function (data) { 
            if (data.getElementsByTagName("data")[0].textContent == "1") {
                $("#warningNickExistente").show();
            } else {
                $("#warningNickExistente").hide();
            }
        }, 
    });
});

