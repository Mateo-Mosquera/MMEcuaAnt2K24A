package MMBusinessLogic;

import java.util.List;
import java.util.stream.Collectors;

import MMDataAccess.MMDAO.*;
import MMDataAccess.MMDTO.*;

public class MMGetHormigaBL {
    private MMGenoAlimentoDAO genoAlimentoDAO;
    private MMIngestaNativaDAO ingestaNativaDAO;
    private MMSexoDAO sexoDAO;
    private MMProvinciaDAO provinciaDAO;

    public MMGetHormigaBL() {
        this.genoAlimentoDAO = new MMGenoAlimentoDAO();
        this.ingestaNativaDAO = new MMIngestaNativaDAO();
        this.sexoDAO = new MMSexoDAO();
        this.provinciaDAO = new MMProvinciaDAO();
    }

    // Métodos existentes
    /**
     * Obtiene el nombre de un GenoAlimento basado en su ID.
     * 
     * @param idGenoAlimento el ID del GenoAlimento cuyo nombre se quiere obtener
     * @return el nombre del GenoAlimento en mayúsculas, o "Desconocido" si no se
     *         encuentra
     */
    public String obtenerNombreGenoAlimento(int idGenoAlimento) {
        try {
            // Intenta leer un GenoAlimento desde la base de datos usando su ID
            MMGenoAlimentoDTO genoAlimento = genoAlimentoDAO.mmReadBy(idGenoAlimento);
            // Si se encuentra el GenoAlimento, se retorna su nombre en mayúsculas
            return genoAlimento != null ? genoAlimento.getNombre().toUpperCase() : "Desconocido";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al obtener nombre";
        }
    }

    /**
     * Obtiene el nombre de una Ingesta Nativa basado en su ID.
     * 
     * @param idIngestaNativa el ID de la Ingesta Nativa cuyo nombre se quiere
     *                        obtener
     * @return el nombre de la Ingesta Nativa en mayúsculas, o "N/A" si no se
     *         encuentra
     */
    public String obtenerNombreIngestaNativa(int idIngestaNativa) throws Exception {
        MMIngestaNativaDTO ingestaNativa = ingestaNativaDAO.mmReadBy(idIngestaNativa);
        return ingestaNativa != null ? ingestaNativa.getNombre().toUpperCase() : "N/A";
    }

    /**
     * Obtiene el nombre de un Sexo basado en su ID.
     * 
     * @param idSexo el ID del Sexo cuyo nombre se quiere obtener
     * @return el nombre del Sexo en mayúsculas, o "N/A" si no se encuentra
     */
    public String obtenerNombreSexo(int idSexo) throws Exception {
        MMSexoDTO sexo = sexoDAO.mmReadBy(idSexo);
        return sexo != null ? sexo.getNombre().toUpperCase() : "N/A";
    }

    /**
     * Obtiene el nombre de una Provincia basado en su ID.
     * 
     * @param idProvincia el ID de la Provincia cuyo nombre se quiere obtener
     * @return el nombre de la Provincia en mayúsculas, o "N/A" si no se encuentra
     */
    public String obtenerNombreProvincia(int idProvincia) throws Exception {
        MMProvinciaDTO provincia = provinciaDAO.mmReadBy(idProvincia);
        return provincia != null ? provincia.getNombre().toUpperCase() : "N/A";
    }

    // Nuevos métodos para obtener listas de nombres
    /**
     * Obtiene una lista de nombres de todos los Sexos en la base de datos.
     * 
     * @return una lista de nombres de Sexos, o un mensaje de error si ocurre un
     *         problema
     */
    public List<String> obtenerNombresSexo() {
        try {
            // Lee todos los Sexos y mapea sus nombres a una lista
            return sexoDAO.mmReadAll().stream()
                    .map(MMSexoDTO::getNombre)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }

    /**
     * Obtiene una lista de nombres de todas las Provincias en la base de datos.
     * 
     * @return una lista de nombres de Provincias, o un mensaje de error si ocurre
     *         un problema
     */
    public List<String> obtenerNombresProvincia() {
        try {
            return provinciaDAO.mmReadAll().stream()
                    .map(MMProvinciaDTO::getNombre)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }

    /**
     * Obtiene una lista de nombres de todos los GenoAlimentos en la base de datos.
     * 
     * @return una lista de nombres de GenoAlimentos, o un mensaje de error si
     *         ocurre un problema
     */
    public List<String> obtenerNombresGenoAlimento() {
        try {
            return genoAlimentoDAO.mmReadAll().stream()
                    .map(MMGenoAlimentoDTO::getNombre)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }

    /**
     * Obtiene una lista de nombres de todas las Ingestas Nativas en la base de
     * datos.
     * 
     * @return una lista de nombres de Ingestas Nativas, o un mensaje de error si
     *         ocurre un problema
     */
    public List<String> obtenerNombresIngestaNativa() {
        try {
            return ingestaNativaDAO.mmReadAll().stream()
                    .map(MMIngestaNativaDTO::getNombre)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }

    // Métodos para obtener IDs por nombre
    /**
     * Obtiene el ID de un Sexo basado en su nombre.
     * 
     * @param nombreSexo el nombre del Sexo cuyo ID se quiere obtener
     * @return el ID del Sexo si se encuentra, de lo contrario, -1
     */
    public int obtenerIdSexo(String nombreSexo) {
        try {
            List<MMSexoDTO> sexos = sexoDAO.mmReadAll();
            for (MMSexoDTO sexo : sexos) {
                // Compara el nombre en mayúsculas (para evitar problemas de
                // mayúsculas/minúsculas)
                if (sexo.getNombre().equalsIgnoreCase(nombreSexo)) {
                    return sexo.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Valor de retorno en caso de que no se encuentre el Sexo
    }

    /**
     * Obtiene el ID de una Provincia basado en su nombre.
     * 
     * @param nombreProvincia el nombre de la Provincia cuyo ID se quiere obtener
     * @return el ID de la Provincia si se encuentra, de lo contrario, -1
     */
    public int obtenerIdProvincia(String nombreProvincia) {
        try {
            List<MMProvinciaDTO> provincias = provinciaDAO.mmReadAll();
            for (MMProvinciaDTO provincia : provincias) {
                if (provincia.getNombre().equalsIgnoreCase(nombreProvincia)) {
                    return provincia.getIdCatalogoGeo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Obtiene el ID de un GenoAlimento basado en su nombre.
     * 
     * @param nombreGenoAlimento el nombre del GenoAlimento cuyo ID se quiere
     *                           obtener
     * @return el ID del GenoAlimento si se encuentra, de lo contrario, -1
     */
    public int obtenerIdGenoAlimento(String nombreGenoAlimento) {
        try {
            List<MMGenoAlimentoDTO> genoAlimentos = genoAlimentoDAO.mmReadAll();
            for (MMGenoAlimentoDTO genoAlimento : genoAlimentos) {
                if (genoAlimento.getNombre().equalsIgnoreCase(nombreGenoAlimento)) {
                    return genoAlimento.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Obtiene el ID de una Ingesta Nativa basado en su nombre.
     * 
     * @param nombreIngestaNativa el nombre de la Ingesta Nativa cuyo ID se quiere
     *                            obtener
     * @return el ID de la Ingesta Nativa si se encuentra, de lo contrario, -1
     */
    public int obtenerIdIngestaNativa(String nombreIngestaNativa) {
        try {
            List<MMIngestaNativaDTO> ingestaNativas = ingestaNativaDAO.mmReadAll();
            for (MMIngestaNativaDTO ingestaNativa : ingestaNativas) {
                if (ingestaNativa.getNombre().equalsIgnoreCase(nombreIngestaNativa)) {
                    return ingestaNativa.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
