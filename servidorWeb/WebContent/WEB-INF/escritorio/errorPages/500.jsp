<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="media/styles/main.css">
    <meta http-equiv="Content-type" content="text/html; charset=utf-8" />
    <meta http-equiv="Content-language" content="es" />
    <link href="media/images/favicon.ico" rel="icon" type="image/x-icon" />
    <link href="media/images/favicon.ico" rel="shortcut icon" />
    <title>Error interno en el Servidor :: gamebook</title>
    
    <script type="text/javascript">
        // cambia alto del elemento
        function doTopChangeMem(elem,startTop,endTop,steps,intervals,powr, endfunc) { 
            if (elem.widthChangeMemInt)
                window.clearInterval(elem.widthChangeMemInt);
            var actStep = 0;
            elem.widthChangeMemInt = window.setInterval(
                function() { 
                    elem.currentWidth = easeInOut(startTop,endTop,steps,actStep,powr);
                    elem.style.top = elem.currentWidth + "px"; 
                    actStep++;
                    if (actStep > steps) { 
                        window.clearInterval(elem.widthChangeMemInt);
                        if(endfunc)
                            endfunc();
                    }
                } 
                ,intervals);
        }
        
        // para animar
        function easeInOut(minValue,maxValue,totalSteps,actualStep,powr) { 
            var delta = maxValue - minValue; 
            var stepp = minValue+(Math.pow(((1 / totalSteps) * actualStep), powr) * delta); 
            return Math.ceil(stepp) 
        } 
        
        // anima para arriba y luego hacia abajo al div de dead_mario
        window.onload = function () {
            var mario = document.getElementById("dead_mario");
            // startTop se toma del CSS
            var startTop = parseInt(document.defaultView.getComputedStyle(mario, null).getPropertyValue("top"));
            var top1 = startTop - 100;
            // top2 = largo del body
            var top2 = Math.max(
                Math.max(document.body.scrollHeight, document.documentElement.scrollHeight),
                Math.max(document.body.offsetHeight, document.documentElement.offsetHeight),
                Math.max(document.body.clientHeight, document.documentElement.clientHeight)
            );
           
            // anima hacia arriba
            doTopChangeMem(mario, startTop, top1, 30, 20, 0.5, function() {
                
                // luego hacia abajo
                doTopChangeMem(mario, top1, top2, 150, 20, 2, function() {
                    
                    // luego borra al elemento
                    mario.parentNode.removeChild(mario);
                })
            });
        }
    </script>
</head>
<body id="error_page">
    <div id="body-container">
        <div class="gameover">500: GAME OVER</div>

        <p>
        Ocurrió un error al procesar la página. Puedes intentar recargar o 
        <a href="/">volver</a> al inicio.
        </p>
    
        <div id="dead_mario"></div>
        <div class="main">
            <pre><%= exception.getClass().getName() %>: <%= exception.getMessage() %>
<% for(StackTraceElement ste: exception.getStackTrace()){ %><%= ste.toString() %>
<% } %></pre>
        </div>
    </div>
</body>
</html>
