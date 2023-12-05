const code1 = `
            <label class="labels">Nickname</label><br>
            <input class="campo" type="text" id="nickname" name="nickname">

            <label class="labels">Contraseña</label>
            <input class="campo" type="password" id="password" name="password">

            <div class="row pt-4 px-4 d-flex justify-content-center">
                <button type="submit" class="btn botonseleccionar btn-lg">
                    Iniciar sesión
                </button>
            </div>
    `;

const code2 = `
            <div class="row">
                <div class="col">
                    <label class="labels">Nickname</label><br>
                    <input class="campo" type="text" id="nickname" name= "nickname">

                    <label class="labels">Contraseña</label>
                    <input class="campo" type="password" id="password" name="password">
                </div>
                <div class="col my-auto">
                    <div class="row mx-4">
                        <button type="submit" class="btn botonseleccionar btn-lg">
                            Iniciar sesión
                        </button>
                    </div>
                </div>
            </div>
    `;

const mediaQ = window.matchMedia('screen and (orientation:portrait)');

mediaQ.onchange = (e) => {
    if (e.matches) {
        $("#content").html(code1);
        $("#content-img").attr("class", "row my-auto");
    } else {
        $("#content").html(code2);
        $("#content-img").attr("class", "row my-3");
    }
}

if (mediaQ.matches) {
    $("#content").html(code1);
    $("#content-img").attr("class", "row my-auto");
} else {
    $("#content").html(code2);
    $("#content-img").attr("class", "row my-3");
}
