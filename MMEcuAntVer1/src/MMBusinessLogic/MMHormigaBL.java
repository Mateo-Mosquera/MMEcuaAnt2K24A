package MMBusinessLogic;

import java.util.List;

import MMDataAccess.MMDAO.MMHormigaDAO;
import MMDataAccess.MMDTO.MMHormigaDTO;

public class MMHormigaBL {
    private MMHormigaDAO hormigaDAO;

    public MMHormigaBL() {
        this.hormigaDAO = new MMHormigaDAO();
    }

    /**
     * Guarda una hormiga en la base de datos.
     * 
     * @param hormiga La hormiga que se va a guardar.
     * @return true si la hormiga se guarda correctamente, false en caso contrario.
     * @throws Exception si ocurre un error durante el proceso de guardado.
     */
    public boolean guardarHormiga(MMHormigaDTO hormiga) throws Exception {
        return hormigaDAO.mmUpdate(hormiga);
    }

    /**
     * Elimina una hormiga de la base de datos.
     * 
     * @param idHormiga El ID de la hormiga que se va a eliminar.
     * @return true si la hormiga se elimina correctamente, false en caso contrario.
     * @throws Exception si ocurre un error durante el proceso de eliminación.
     */
    public boolean eliminarHormiga(int idHormiga) throws Exception {
        return hormigaDAO.mmDelete(idHormiga);
    }

    /**
     * Obtiene una hormiga por su ID.
     * 
     * @param idHormiga El ID de la hormiga que se quiere obtener.
     * @return El objeto mmHormigaDTO correspondiente al ID proporcionado.
     * @throws Exception si ocurre un error durante el proceso de obtención.
     */
    public MMHormigaDTO obtenerHormigaPorId(int idHormiga) throws Exception {
        return hormigaDAO.mmReadBy(idHormiga);
    }

    /**
     * Obtiene una lista de todas las hormigas de la base de datos.
     * 
     * @return Una lista de objetos mmHormigaDTO que representa todas las hormigas.
     * @throws Exception si ocurre un error durante el proceso de obtención.
     */
    public List<MMHormigaDTO> obtenerTodasLasHormigas() throws Exception {
        return hormigaDAO.mmReadAll();
    }

    /**
     * Determina la evolución de una hormiga en función de su tipo anterior y el
     * alimento consumido.
     * 
     * @param tipoHormigaAnterior El tipo anterior de la hormiga.
     * @param tipoAlimento        El tipo de alimento que la hormiga ha consumido.
     * @return El nuevo tipo de hormiga después de la evolución.
     */
    public String determinarEvolucion(String tipoHormigaAnterior, String tipoAlimento) {
        return hormigaDAO.determinarEvolucion(tipoHormigaAnterior, tipoAlimento);
    }

    /**
     * Guarda todos los cambios realizados en las hormigas.
     * 
     * Este método recorre todas las hormigas obtenidas de la base de datos y guarda
     * los cambios realizados en cada una de ellas.
     * 
     * @throws Exception si ocurre un error durante el proceso de guardado.
     */
    public void guardarTodosLosCambios() throws Exception {
        // Obtiene todas las hormigas de la base de datos
        List<MMHormigaDTO> hormigas = obtenerTodasLasHormigas();

        // Itera sobre cada hormiga y la guarda en la base de datos
        for (MMHormigaDTO hormiga : hormigas) {
            hormigaDAO.mmUpdate(hormiga); // Guardar cada hormiga
        }
    }

}
