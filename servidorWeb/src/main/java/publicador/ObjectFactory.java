
package publicador;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicador package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _IOException_QNAME = new QName("http://publicador/", "IOException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicador
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtOferta }
     * 
     * @return
     *     the new instance of {@link DtOferta }
     */
    public DtOferta createDtOferta() {
        return new DtOferta();
    }

    /**
     * Create an instance of {@link DtOferta.DataPostulantesFavoritos }
     * 
     * @return
     *     the new instance of {@link DtOferta.DataPostulantesFavoritos }
     */
    public DtOferta.DataPostulantesFavoritos createDtOfertaDataPostulantesFavoritos() {
        return new DtOferta.DataPostulantesFavoritos();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     * @return
     *     the new instance of {@link IOException }
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtPostulante }
     * 
     * @return
     *     the new instance of {@link DtPostulante }
     */
    public DtPostulante createDtPostulante() {
        return new DtPostulante();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtPostulanteCompleto }
     * 
     * @return
     *     the new instance of {@link DtPostulanteCompleto }
     */
    public DtPostulanteCompleto createDtPostulanteCompleto() {
        return new DtPostulanteCompleto();
    }

    /**
     * Create an instance of {@link DtPaqueteCompleto }
     * 
     * @return
     *     the new instance of {@link DtPaqueteCompleto }
     */
    public DtPaqueteCompleto createDtPaqueteCompleto() {
        return new DtPaqueteCompleto();
    }

    /**
     * Create an instance of {@link DtCantidad }
     * 
     * @return
     *     the new instance of {@link DtCantidad }
     */
    public DtCantidad createDtCantidad() {
        return new DtCantidad();
    }

    /**
     * Create an instance of {@link DtOfertaCompleto }
     * 
     * @return
     *     the new instance of {@link DtOfertaCompleto }
     */
    public DtOfertaCompleto createDtOfertaCompleto() {
        return new DtOfertaCompleto();
    }

    /**
     * Create an instance of {@link DtPostulacionCompleto }
     * 
     * @return
     *     the new instance of {@link DtPostulacionCompleto }
     */
    public DtPostulacionCompleto createDtPostulacionCompleto() {
        return new DtPostulacionCompleto();
    }

    /**
     * Create an instance of {@link DtPostulacion }
     * 
     * @return
     *     the new instance of {@link DtPostulacion }
     */
    public DtPostulacion createDtPostulacion() {
        return new DtPostulacion();
    }

    /**
     * Create an instance of {@link DtTipoDeOferta }
     * 
     * @return
     *     the new instance of {@link DtTipoDeOferta }
     */
    public DtTipoDeOferta createDtTipoDeOferta() {
        return new DtTipoDeOferta();
    }

    /**
     * Create an instance of {@link DtEmpresaCompleto }
     * 
     * @return
     *     the new instance of {@link DtEmpresaCompleto }
     */
    public DtEmpresaCompleto createDtEmpresaCompleto() {
        return new DtEmpresaCompleto();
    }

    /**
     * Create an instance of {@link DtEmpresa }
     * 
     * @return
     *     the new instance of {@link DtEmpresa }
     */
    public DtEmpresa createDtEmpresa() {
        return new DtEmpresa();
    }

    /**
     * Create an instance of {@link DtOfertaArray }
     * 
     * @return
     *     the new instance of {@link DtOfertaArray }
     */
    public DtOfertaArray createDtOfertaArray() {
        return new DtOfertaArray();
    }

    /**
     * Create an instance of {@link DtUsuarioArray }
     * 
     * @return
     *     the new instance of {@link DtUsuarioArray }
     */
    public DtUsuarioArray createDtUsuarioArray() {
        return new DtUsuarioArray();
    }

    /**
     * Create an instance of {@link DtPaqueteArray }
     * 
     * @return
     *     the new instance of {@link DtPaqueteArray }
     */
    public DtPaqueteArray createDtPaqueteArray() {
        return new DtPaqueteArray();
    }

    /**
     * Create an instance of {@link DtOferta.DataPostulantesFavoritos.Entry }
     * 
     * @return
     *     the new instance of {@link DtOferta.DataPostulantesFavoritos.Entry }
     */
    public DtOferta.DataPostulantesFavoritos.Entry createDtOfertaDataPostulantesFavoritosEntry() {
        return new DtOferta.DataPostulantesFavoritos.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicador/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<>(_IOException_QNAME, IOException.class, null, value);
    }

}
