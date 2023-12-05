package controllers;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.Port;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class PageCounterFilter implements Filter {
	publicador.WebServicesService service = new publicador.WebServicesService();
    publicador.WebServices port = service.getWebServicesPort();
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String oferta = request.getParameter("name");
        port.addVisita(oferta);
        chain.doFilter(request, response);
    }

    public void destroy() {
        // Puedes guardar o registrar los datos del contador al finalizar la aplicación
    }
   

}
