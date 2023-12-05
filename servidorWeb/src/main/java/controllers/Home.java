package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpSession;
import publicador.DtEmpresa;
import publicador.DtOferta;
import publicador.DtUsuario;
import publicador.EstadoOferta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.function.BiPredicate;

/**
 * Servlet implementation class Home
 */
@WebServlet  (urlPatterns = {"/home"})
public class Home extends jakarta.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
	publicador.WebServicesService service = new publicador.WebServicesService();
    publicador.WebServices port = service.getWebServicesPort();

    public Home() {
    	super();
    }
    
    public static void initSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("estado_sesion") == null) {
			session.setAttribute("estado_sesion","VISITANTE");
		}
	}

    private LocalDate to_local (String fecha) {
        String[] res = fecha.split("-");
        return LocalDate.of(Integer.parseInt(res[2]), Integer.parseInt(res[1]), Integer.parseInt(res[0]));
    }

    private <T> T[] heapsort(T[] data, int n, BiPredicate<T, T> predicate){
        if (n > 1) {
            n++;
            @SuppressWarnings("unchecked")
            T[] aux = (T[])new Object[n];
            T res;
            int padre, hijo, i;
            for(i = 1; i < n; i++){
                aux[i] = data[i-1];
                hijo = i;
                while(hijo > 1 && predicate.test(aux[hijo], aux[(padre = hijo>>1)])){
                    res = aux[padre];
                    aux[padre] = aux[hijo];
                    aux[hijo] = res;
                    hijo = padre;
                }
            }
            for(i = n; i > 1; i--){
                data[n-i] = aux[1];
                hijo = 2;
                padre = 1;
                
                while((hijo < i && predicate.test(aux[hijo], aux[i-1])) || (hijo+1 < i && predicate.test(aux[hijo+1], aux[i-1]))){
                    if(hijo+1 < i && predicate.test(aux[hijo+1], aux[hijo]))
                        hijo++;
                    aux[padre] = aux[hijo];
                    padre = hijo;
                    hijo = hijo<<1;
                }
                aux[padre] = aux[i-1];
            }
        }
        return data;
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse respone) throws ServletException, IOException {
        initSession(request);
		HttpSession session = request.getSession();
        String pagina;

        /* variables */
        String key = request.getParameter("key");
        String empresa = request.getParameter("empresa");
        String buscar = request.getParameter("buscar");
        buscar = (buscar != null ? buscar : "1");

        String orden = request.getParameter("orden");
        orden = (orden != null ? orden : "0");

        final String busqueda = request.getParameter("busqueda") != null ? request.getParameter("busqueda") : "";
        request.setAttribute("busqueda", busqueda);

        String userAgent = request.getHeader("User-Agent");
        boolean movil = userAgent != null && userAgent.toLowerCase().contains("mobile");
        
        List<DtOferta> lista_ofertas = new ArrayList<DtOferta>();
        List<DtEmpresa> lista_empresas = null;

        /* casos posibles: 
         * buscar == "1" && key == null && empresa == null
         * buscar == "1" && key == null && empresa != null
         * buscar == "1" && key != null && empresa == null
         * buscar == "0" && orden == "0" && !movil
         * buscar == "0" && orden == "1" && !movil
         * */
        if (movil && buscar.equals("1")) {
            lista_empresas = new ArrayList<DtEmpresa>();
            List<DtUsuario> res = port.getDtUsuarios().getItem();
            DtEmpresa empres;
            String res_1;
            for (DtUsuario emp : res) {
                res_1 = emp.getNickname();
                if (port.esEmpresa(res_1)) {
                    lista_empresas.add(port.mostrarEmpresa(res_1).getEmpresa());
                }
            }
            request.getSession().setAttribute("empr", lista_empresas);
        }

        if (buscar.equals("1") && key == null && empresa == null) {
            lista_ofertas = port.listarOfertas().getItem();
            lista_empresas = null;
        } else if (buscar.equals("1") && key == null && empresa != null) {
            lista_ofertas = port.listarOfertasPorEmpresa(empresa, publicador.EstadoOferta.CONFIRMADA).getItem();
            request.setAttribute("empresa", empresa);
            lista_empresas = null;
        } else if (buscar.equals("1") && key != null && empresa == null) {
            lista_ofertas = port.listarOfertasPorKeyword(key).getItem();
            request.setAttribute("key", key);
            lista_empresas = null;
        } else if (buscar.equals("0") && orden.equals("0") && !movil) {
            lista_empresas = new ArrayList<DtEmpresa>();

            /* Hago un map con las empresas que cumplen la condicion de busqueda. */
            List<DtUsuario> res = port.getDtUsuarios().getItem();
            Map<String, DtEmpresa> res_empresas = new HashMap<String, DtEmpresa>();
            DtEmpresa empres;
            String res_1;
            for (DtUsuario emp : res) {
                res_1 = emp.getNickname();
                if (port.esEmpresa(res_1)) {
                    empres = port.mostrarEmpresa(res_1).getEmpresa();
                    if (res_1.contains(busqueda) || empres.getDescripcion().contains(busqueda)) {
                        res_empresas.put(res_1, empres);
                    }
                }
            }

            List<DtOferta> ofertas_para_empresas = port.listarOfertasSinOrdenar().getItem();
            ofertas_para_empresas.removeIf(oferta -> !(oferta.getNombre().contains(busqueda) || oferta.getDescripcion().contains(busqueda)));

            /* Ordenar ofertas_para_empresas por fecha de alta ascendente. */
            ofertas_para_empresas = Arrays.asList(
                heapsort(
                    ofertas_para_empresas.toArray(new DtOferta[0]), 
                    ofertas_para_empresas.size(), 
                    (a, b) -> to_local(a.getFechaAlta()).compareTo(to_local(b.getFechaAlta())) < 0 
                )
            );

            /* Ordenar lista_empresas segun ofertas_para_empresas. */
            String nickname; 
            for (DtOferta oferta : ofertas_para_empresas) {
                nickname = oferta.getEmpresa();
                empres = res_empresas.get(nickname);
                if (empres != null) {
                    res_empresas.remove(nickname);
                    lista_empresas.add(empres);
                }
            }
            for (Map.Entry<String, DtEmpresa> empre : res_empresas.entrySet()) {
                lista_empresas.add(empre.getValue());
            }

            /* Ordenar ofertas_para_empresas por exposicion preservando el orden por fecha. */
            List<DtOferta> lis2 = new ArrayList<>();
            List<DtOferta> lis3 = new ArrayList<>();
            List<DtOferta> lis4 = new ArrayList<>();
            List<DtOferta> lis5 = new ArrayList<>();
            LocalDate fechaActual = LocalDate.now();
            for (DtOferta oferta: ofertas_para_empresas) {
                switch (port.mostrarTipoOferta(oferta.getTipoDeOferta()).getExposicion()) {
                    case 1:
                        lista_ofertas.add(oferta);
                        break;
                    case 2:
                        lis2.add(oferta);
                        break;
                    case 3:
                        lis3.add(oferta);
                        break;
                    case 4:
                        lis4.add(oferta);
                        break;
                    case 5:
                        lis5.add(oferta);
                        break;
                }
            }
            lista_ofertas.addAll(lis2);
            lista_ofertas.addAll(lis3);
            lista_ofertas.addAll(lis4);
            lista_ofertas.addAll(lis5);

            pagina = request.getParameter("paginaEmpresas");
            try {
                request.setAttribute("paginaEmpresas", pagina != null ? Integer.valueOf(pagina) : 1);
            } catch (Exception e) {
                request.setAttribute("paginaEmpresas", 1);
            }
            request.setAttribute("orden", orden);

        } else if (buscar.equals("0") && orden.equals("1") && !movil) {

            /* obtengo y listo las ofertas. */
            lista_ofertas = port.listarOfertasSinOrdenar().getItem();
            lista_ofertas.removeIf(oferta -> !(oferta.getNombre().contains(busqueda) || oferta.getDescripcion().contains(busqueda)));
            lista_ofertas = Arrays.asList(
                heapsort(
                    lista_ofertas.toArray(new DtOferta[0]),
                    lista_ofertas.size(),
                    (a, b) -> a.getNombre().compareTo(b.getNombre()) < 0
                )
            );
            
            /* Se filtran las empresas. */
            lista_empresas = new ArrayList<DtEmpresa>();
            List<DtUsuario> res = port.getDtUsuarios().getItem();
            DtEmpresa empres;
            String res_1;
            for (DtUsuario emp : res) {
                res_1 = emp.getNickname();
                if (port.esEmpresa(res_1)) {
                    empres = port.mostrarEmpresa(res_1).getEmpresa();
                    if (res_1.contains(busqueda) || empres.getDescripcion().contains(busqueda)) {
                        lista_empresas.add(empres);
                    }
                }
            }

            /* Se ordenan. */
            lista_empresas = Arrays.asList(
                heapsort(
                    lista_empresas.toArray(new DtEmpresa[0]),
                    lista_empresas.size(),
                    (a, b) -> a.getNickname().compareTo(b.getNickname()) < 0
                )
            );

            pagina = request.getParameter("paginaEmpresas");
            try {
                request.setAttribute("paginaEmpresas", pagina != null ? Integer.valueOf(pagina) : 1);
            } catch (Exception e) {
                request.setAttribute("paginaEmpresas", 1);
            }

            request.setAttribute("orden", orden);
        }

        /* casos posibles (las variables que no aparezcan se asumen null):
         *    (muestra todos)    buscar == null
         *    (muestra todos)    buscar == "1" && key == null && empresa == null
         *   (consulta oferta)   buscar == "1" && key != null
         *   (consulta oferta)   buscar == "1" && empresa != null
         *  (barra de busqueda)  buscar == "0" && orden == "0"
         *  (barra de busqueda)  buscar == "0" && orden == "1"
         * */
        
        request.setAttribute("ofertas", lista_ofertas);
        request.setAttribute("empresas", lista_empresas);
        
        pagina = request.getParameter("paginaOfertas");
        try {
        	request.setAttribute("paginaOfertas", pagina != null ? Integer.valueOf(pagina) : 1);
        } catch (Exception e) {
        	request.setAttribute("paginaOfertas", 1);
        }

        /* ver para que sirven la keywords */
        session.setAttribute("keywords", port.listarKeywords().getItem());

        /* tengo que controlar el caso movil arriba, lo voy a hacer cuando funcionen las consultas de escritorio. */
        if (movil) {
    		if (!session.getAttribute("estado_sesion").equals("POSTULANTE")) {
    			request.getRequestDispatcher("/WEB-INF/movil/InicioSesion/inicio_sesion.jsp").forward(request, respone);
			} else {
    			request.getRequestDispatcher("/WEB-INF/movil/home/index.jsp").forward(request, respone);
            }
        } else {
        	request.getRequestDispatcher("/WEB-INF/escritorio/home/index.jsp").forward(request, respone);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
