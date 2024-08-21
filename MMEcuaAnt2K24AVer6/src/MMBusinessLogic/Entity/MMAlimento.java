package MMBusinessLogic.Entity;

public abstract class MMAlimento {
    protected String mmTipo;

    public MMAlimento(String tipo) {
        this.mmTipo = tipo;
    }

    @Override
    public String toString() {
        if(mmTipo == null)
            mmTipo = "";
        return mmTipo.toUpperCase();
    }
}
