package MMBusinessLogic.Entity;

public class MMIngestaNativa {
    private String tipoIngesta;

    public MMIngestaNativa(String tipoIngesta) {
        this.tipoIngesta = tipoIngesta;
    }

    public String getTipoIngesta() {
        return tipoIngesta;
    }

    // Método para realizar una acción específica
    public void realizarAccion() {
        // Implementa la lógica de negocio aquí
        System.out.println("Acción realizada para IngestaNativa: " + tipoIngesta);
    }
}
