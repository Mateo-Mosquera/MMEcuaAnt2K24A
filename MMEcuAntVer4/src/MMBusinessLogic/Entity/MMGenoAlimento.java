package MMBusinessLogic.Entity;

public abstract class MMGenoAlimento extends MMAlimento {

    public MMGenoAlimento(String tipo) {
        super(tipo);
    }

    public String getTipo() {
        return mmTipo;
    }
}
