package MMBusinessLogic.Entity;

public class MMIngestaNativa {
    private String tipoIngesta;

    public MMIngestaNativa(String tipoIngesta) {
        this.tipoIngesta = tipoIngesta;
    }

    public String getTipoIngesta() {
        return tipoIngesta;
    }

    public void realizarAccion() {
        System.out.println("Acción realizada para IngestaNativa: " + tipoIngesta);
    }
}
