package MMBusinessLogic.Entity;

public abstract class MMAlimento {
    protected String dkTipo;

    public MMAlimento(String tipo) {
        this.dkTipo = tipo;
    }

    @Override
    public String toString() {
        return dkTipo;
    }
}
