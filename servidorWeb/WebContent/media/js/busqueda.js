function autoCompletadoBusqueda() {
    $.ajax({
        url: "/servidorWeb/empresasBusqueda",
        data: {"arg0": $("#busqueda").val()},
        datatype: "xml",
        success: function (data) {
            $("#opcionesAutoCompletado li").remove();
            cont = "";
            for (let elem of data.getElementsByTagName("element")) {
                cont += "<li><a class='dropdown-item nombreAutocompletado'>" + elem.textContent + "</a></li>";
            }
            $("#opcionesAutoCompletado").html($("#opcionesAutoCompletado").html() + cont);
            $(".nombreAutocompletado").on("click", function () {
                $("#busqueda").val($(this).text());
            });
        }
    });
};

$(document).ready( function () {

    /* Muestra y oculta el dropdown del boton de filtros. */
    $("#botonFiltros").on("click", function () {
        if ($("#dropdownFiltros").hasClass("show")) {
            $("#dropdownFiltros").removeClass("show");
        } else {
            $("#dropdownFiltros").addClass("show");
        }
    });

    /* Esconde el ordenamiento en caso que la busqueda sea por oferta. */
    $("input[name='buscaRadio']").on("change", function () {
        switch($(this).val()) {
            case "1":
                $("#ordenamiento").hide();
                $("#busqueda").attr("data-bs-toggle", "dropdown");
                $("#busqueda").attr("aria-expanded", "false");
                
                /* Consula ajax para el autocompletado. */
                $("#busqueda").on("input", autoCompletadoBusqueda);
                autoCompletadoBusqueda();

                break;
            case "0":
                $("#ordenamiento").show();
                $("#busqueda").removeAttr("data-bs-toggle");
                $("#busqueda").removeAttr("aria-expanded");

                $("#busqueda").off("input focus");
                $("#opcionesAutoCompletado li").remove();

                break;
            default: 
                alert ("Ha ocurrido un error el filtro de busqueda");
                break;
        }
    });
});

function redireccion() {
    parametros = "";
    if ($("input[name='buscaRadio']:checked").val() == "1") {
        parametros = "empresa=";
    } else {
        parametros = "buscar=0&orden=" + ($("input[name='ordenRadio']:checked").val() == "1" ? "1" : "0") + "&busqueda=";
    }
    window.location.href = "/servidorWeb/home?" + parametros + $("#busqueda").val();
}

