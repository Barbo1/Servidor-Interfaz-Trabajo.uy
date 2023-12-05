var boton = $("#botonFavorito");

const burstFavorito = new mojs.Burst({
    radius: { 35: 40 },
    count: 7,
    children: {
        shape: 'line',
        radius: 7,
        radiusY: 0,
        scale: 1,
        strokeDasharray: '100%',
        strokeDashoffset: { '-100%' : '100%' },
        stroke: '#0A66C2' ,
        easing: 'linear.none',
        duration: 200
    }
});

const funcionFavorito = function (operation) {
    $.ajax({
        type: "GET",
        url: "/servidorWeb/" + operation,
        data: {"nombreOferta": $("#nombreOferta").text()},
        datatype: "xml",
    });
}

$(document).ready(function () {
    boton.click(function() {
        if(boton.hasClass("NoFavorito")){
            funcionFavorito("Favorito");
            $("#reputacion").text(Number($("#reputacion").text()) + 1);
            burstFavorito.tune({
                left: boton.offset().left + boton.width() / 2,
                top: boton.offset().top + boton.height() / 2,
            });
            burstFavorito.play();
            boton.removeClass();
            boton.addClass("FavoritoAnim");
            setTimeout(function () {
                boton.removeClass();
                boton.addClass("Favorito");
            }, 200);
        } else {
            funcionFavorito("noFavorito");
            $("#reputacion").text(Number($("#reputacion").text()) - 1);
            boton.removeClass();
            boton.addClass("NoFavoritoAnim");
            setTimeout(function () {
                boton.removeClass();
                boton.addClass("NoFavorito"); 
            }, 500);
        }
    })
});

