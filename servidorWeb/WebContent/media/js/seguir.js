var botonSeguidores = $("#botonSeguir");
var heart = "/servidorWeb/media/imagenes/general/seguir.svg";
var broken_heart = "/servidorWeb/media/imagenes/general/dejar_de_seguir.svg";

const burstSeguir = new mojs.Burst({
    radius: { 33: 38 },
    count: 7,
    rotate: { 0: 90 },
    fill: { 'magenta' : 'cyan' },
    duration: 200,
});

const hoverUnfollow = function () {
    botonSeguidores.attr("src", broken_heart);
}

const unhoverUnfollow = function () {
    botonSeguidores.attr("src", heart);
}

$(document).ready(function () {
    botonSeguidores.click(function () {
        botonSeguidores.attr("src", heart);
        if(botonSeguidores.hasClass("imagen-unfollow")){
            burstSeguir.tune({
                left: botonSeguidores.offset().left + botonSeguidores.width() / 2 + 11,
                top: botonSeguidores.offset().top + botonSeguidores.height() / 2 + 14,
            });
            burstSeguir.play();
            botonSeguidores.removeClass();
            botonSeguidores.addClass("imagen-unfollow-anim pt-3");
            setTimeout(function () {
                $.ajax({
                    type: "GET",
                    url: "/servidorWeb/Follow",
                    data: {"nick": nickSeguir},
                    datatype: "xml",
                    success: function (data) {
                        $("#seguidores").prepend(`
                            <a class='sinHipervinculo' href='consulta-usuario?user=` + data.getElementsByTagName("nick")[0].textContent + `'>
                                <div class='row efectoZoom coloresOfertas centrar'>
                                    <div class='col-3 px-0'>
                                        <img src='Imagenes?id=` + data.getElementsByTagName("imagen")[0].textContent + `&tipo=usuarios' class='imagenOfertaIndex'></img>
                                    </div>
                                    <div class='col-9'>
                                        <div>
                                            <p class='tituloOferta' id='encabezadoOferta'>` + 
                                                data.getElementsByTagName("nick")[0].textContent + " / " + 
                                                data.getElementsByTagName("nombre")[0].textContent + " " + 
                                                data.getElementsByTagName("apellido")[0].textContent + 
                                            `</p>
                                        </div>
                                    </div>
                                </div>
                            </a> 
                        `);
                    },
                });
                $("#contadorSeguidores").text(Number($("#contadorSeguidores").text()) + 1);
                botonSeguidores.removeClass();
                botonSeguidores.hover(hoverUnfollow, unhoverUnfollow);
                botonSeguidores.addClass("imagen-follow pt-3");
            }, 200);
        } else {
            botonSeguidores.unbind('mouseenter mouseleave');
            botonSeguidores.removeClass();
            botonSeguidores.addClass("imagen-follow-anim pt-3");
            setTimeout(function () {
                $.ajax({
                    type: "GET",
                    url: "/servidorWeb/Unfollow",
                    data: {"nick": nickSeguir},
                    datatype: "xml",
                    success: function (data) {
                        if (data.getElementsByTagName("data")[0].textContent == "1") {
                            $("#seguidores a").first().remove();
                        }
                    },
                });
                $("#contadorSeguidores").text(Number($("#contadorSeguidores").text()) - 1);
                botonSeguidores.removeClass();
                botonSeguidores.addClass("imagen-unfollow pt-3");
            }, 500);
        }
    });
});

if(botonSeguidores.hasClass("imagen-follow")){
    botonSeguidores.hover(hoverUnfollow, unhoverUnfollow);
}
