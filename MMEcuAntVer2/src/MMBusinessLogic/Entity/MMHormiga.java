package MMBusinessLogic.Entity;

import MMBusinessLogic.MMIHormiga;

public class MMHormiga implements MMIHormiga {

    protected String mmTipo;

    public MMHormiga(String tipo) {
        this.mmTipo = tipo;
    }

    @Override
    public String toString() {
        return mmTipo;
    }

    @Override
    public boolean mmComer(MMAlimento mmAlimento) {
        String tipoAlimento = mmAlimento.toString();
        // Permitir evolución solo si se ha comido XY o Omnivoro
        if ("XY".equals(tipoAlimento) || "Omnivoro".equals(tipoAlimento)) {
            return true;
        }
        return false;
    }
}
