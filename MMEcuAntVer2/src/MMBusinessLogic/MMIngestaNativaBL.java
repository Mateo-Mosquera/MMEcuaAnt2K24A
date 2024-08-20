package MMBusinessLogic;

import java.util.List;

import MMDataAccess.MMDAO.MMIngestaNativaDAO;
import MMDataAccess.MMDTO.MMIngestaNativaDTO;

public class MMIngestaNativaBL {
    private MMIngestaNativaDAO ingestaNativaDAO;

    // Constructor que inicializa el DAO para acceder a los datos de ingesta nativa.
    public MMIngestaNativaBL() {
        this.ingestaNativaDAO = new MMIngestaNativaDAO();
    }

    /**
     * Obtiene el ID de la ingesta nativa basada en su nombre.
     * 
     * @param nombreIngestaNativa El nombre de la ingesta nativa que se quiere
     *                            buscar.
     * @return El ID de la ingesta nativa si se encuentra, -1 en caso contrario.
     */
    public int obtenerIdIngestaNativa(String nombreIngestaNativa) {
        try {
            // Se obtienen todas las ingestas nativas de la base de datos
            List<MMIngestaNativaDTO> ingestaNativas = ingestaNativaDAO.mmReadAll();

            // Se busca la ingesta nativa que coincide con el nombre proporcionado
            for (MMIngestaNativaDTO ingestaNativa : ingestaNativas) {
                if (ingestaNativa.getNombre().equals(nombreIngestaNativa)) {
                    return ingestaNativa.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Retorna -1 si no se encontró la ingesta nativa
        return -1;
    }

    /**
     * Obtiene una lista de nombres de todas las ingestas nativas.
     * 
     * @return Una lista de nombres de ingestas nativas, o un mensaje de error si
     *         ocurre una excepción.
     */
    public List<String> obtenerNombresIngestaNativa() {
        try {
            // Obtiene todos los nombres de las ingestas nativas desde la base de datos
            return ingestaNativaDAO.mmReadAll().stream()
                    .map(MMIngestaNativaDTO::getNombre)
                    .toList();
        } catch (Exception e) {
            e.printStackTrace();

            // Retorna un mensaje de error si ocurre una excepción
            return List.of("Error al cargar");
        }
    }
}
