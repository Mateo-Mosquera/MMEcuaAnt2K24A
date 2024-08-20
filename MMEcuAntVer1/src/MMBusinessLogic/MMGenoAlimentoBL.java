package MMBusinessLogic;

import java.util.List;

import MMDataAccess.MMDAO.MMGenoAlimentoDAO;
import MMDataAccess.MMDTO.MMGenoAlimentoDTO;

public class MMGenoAlimentoBL {
    private MMGenoAlimentoDAO genoAlimentoDAO;

    public MMGenoAlimentoBL() {
        this.genoAlimentoDAO = new MMGenoAlimentoDAO();
    }

    /**
     * Obtiene el ID de un GenoAlimento basado en su nombre.
     * Este método recorre toda la lista de GenoAlimentos y compara el nombre dado
     * con los nombres almacenados.
     * 
     * @param nombreGenoAlimento el nombre del GenoAlimento cuyo ID se quiere
     *                           obtener
     * @return el ID del GenoAlimento si se encuentra, de lo contrario, -1
     */
    public int obtenerIdGenoAlimento(String nombreGenoAlimento) {
        try {
            // Carga todos los GenoAlimentos de la base de datos
            List<MMGenoAlimentoDTO> genoAlimentos = genoAlimentoDAO.mmReadAll();

            // Itera sobre la lista de GenoAlimentos
            for (MMGenoAlimentoDTO genoAlimento : genoAlimentos) {
                // Compara el nombre del GenoAlimento con el nombre proporcionado
                if (genoAlimento.getNombre().equals(nombreGenoAlimento)) {
                    // Retorna el ID del GenoAlimento si encuentra una coincidencia
                    return genoAlimento.getIdCatalogoAl();
                }
            }
        } catch (Exception e) {
            // Imprime el stack trace en caso de excepción (¡Recuerda no dejar errores sin
            // manejar!)
            e.printStackTrace();
        }
        // Si no se encuentra ningún GenoAlimento con ese nombre, retorna -1
        return -1;
    }

    /**
     * Obtiene una lista de nombres de todos los GenoAlimentos.
     * Este método utiliza streams para mapear los nombres de GenoAlimento y
     * devolverlos en una lista.
     * 
     * @return una lista de nombres de GenoAlimentos, o un mensaje de error si
     *         ocurre un problema
     */
    public List<String> obtenerNombresGenoAlimento() {
        try {
            // Carga todos los GenoAlimentos de la base de datos y obtiene sus nombres
            // usando streams
            return genoAlimentoDAO.mmReadAll().stream()
                    .map(MMGenoAlimentoDTO::getNombre)
                    .toList();
        } catch (Exception e) {
            // Captura cualquier excepción que pueda ocurrir y la maneja mostrando un
            // mensaje de error
            e.printStackTrace();
            return List.of("Error al cargar");
        }
    }
}
