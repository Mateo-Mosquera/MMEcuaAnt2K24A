package MMBusinessLogic;

import java.util.List;
import java.util.Random;

import MMDataAccess.MMDAO.MMHormigaDAO;
import MMDataAccess.MMDAO.MMProvinciaDAO;
import MMDataAccess.MMDTO.MMHormigaDTO;
import MMDataAccess.MMDTO.MMProvinciaDTO;

public class MMCrearHormigaBL {

    private MMHormigaDAO hormigaDAO;
    private MMGetHormigaBL getHormigaBL;
    private MMProvinciaDAO provinciaDAO;

    public MMCrearHormigaBL() {
        this.hormigaDAO = new MMHormigaDAO();
        this.getHormigaBL = new MMGetHormigaBL();
        this.provinciaDAO = new MMProvinciaDAO();
    }

    /**
     * The function creates a new ant object with default values and saves it to the
     * database after
     * retrieving a random province from the database.
     * 
     * @return The method `crearNuevaHormigaConValoresPorDefecto` is returning a
     *         boolean value. If the
     *         creation of a new `MMHormigaDTO` object with default values is
     *         successful and it is saved in the
     *         database using `hormigaDAO.mmCreate`, then the method will return
     *         `true`. If there is an
     *         exception during the process, it will catch the exception
     */
    public boolean crearNuevaHormigaConValoresPorDefecto() {
        try {
            // Obtener una provincia aleatoria de la base de datos
            List<MMProvinciaDTO> provincias = provinciaDAO.mmReadAll();
            if (provincias.isEmpty()) {
                throw new Exception("No hay provincias disponibles para asignar.");
            }
            MMProvinciaDTO provinciaAleatoria = obtenerProvinciaAleatoria(provincias);

            // Crear el objeto Hormiga DTO con valores por defecto
            MMHormigaDTO nuevaHormiga = new MMHormigaDTO();
            nuevaHormiga.setNombreVida("VIVA"); // Estado de vida
            nuevaHormiga.setIdSexo(10); // Sexo por defecto (ID 10)
            nuevaHormiga.setIdGenoAlimento(-1); // Valor no asignado para GenoAlimento
            nuevaHormiga.setIdIngestaNativa(-1); // Valor no asignado para IngestaNativa
            nuevaHormiga.setIdProvincia(provinciaAleatoria.getIdCatalogoGeo()); // Provincia aleatoria
            nuevaHormiga.setTipoHormiga("Larva"); // Tipo de hormiga
            nuevaHormiga.setPorcentajeEvolucion(0); // Inicia en 0%
            nuevaHormiga.setEstado("A"); // Asignar un valor por defecto para 'estado'

            // Guardar en la base de datos
            return hormigaDAO.mmCreate(nuevaHormiga);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * The function obtains a random province from a list of provinces.
     * 
     * @param provincias The parameter `provincias` is a List of `MMProvinciaDTO`
     *                   objects. The method
     *                   `obtenerProvinciaAleatoria` takes this List as input and
     *                   returns a randomly selected
     *                   `MMProvinciaDTO` object from the list.
     * @return A random MMProvinciaDTO object from the list of MMProvinciaDTO
     *         objects passed as a
     *         parameter.
     */
    private MMProvinciaDTO obtenerProvinciaAleatoria(List<MMProvinciaDTO> provincias) {
        Random random = new Random();
        int index = random.nextInt(provincias.size());
        return provincias.get(index);
    }

    /**
     * The function returns a list of names based on gender.
     * 
     * @return A List of String values representing the names of sexes.
     */
    public List<String> obtenerNombresSexo() {
        return getHormigaBL.obtenerNombresSexo();
    }

    /**
     * The function returns a list of province names obtained from a method in the
     * getHormigaBL class.
     * 
     * @return A List of Strings containing the names of provinces is being
     *         returned.
     */
    public List<String> obtenerNombresProvincia() {
        return getHormigaBL.obtenerNombresProvincia();
    }

    /**
     * The function returns a list of strings containing names of genes related to
     * food in ants.
     * 
     * @return A List of String objects containing the names of genes related to
     *         food in ants.
     */
    public List<String> obtenerNombresGenoAlimento() {
        return getHormigaBL.obtenerNombresGenoAlimento();
    }

    /**
     * The function returns a list of native ingestion names obtained from the
     * "HormigaBL" class.
     * 
     * @return A List of Strings containing the names of native ingest ants is being
     *         returned.
     */
    public List<String> obtenerNombresIngestaNativa() {
        return getHormigaBL.obtenerNombresIngestaNativa();
    }
}
