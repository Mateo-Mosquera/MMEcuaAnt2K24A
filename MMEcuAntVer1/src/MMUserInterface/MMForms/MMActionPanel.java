package MMUserInterface.MMForms;

import MMBusinessLogic.MMGenoAlimentoBL;
import MMBusinessLogic.MMHormigaBL;
import MMBusinessLogic.MMIngestaNativaBL;
import MMDataAccess.MMDTO.MMHormigaDTO;
import MMUserInterface.MMCustomerController.MMPatButton;
import MMUserInterface.MMCustomerController.MMPatComboBox;
import MMUserInterface.MMCustomerController.MMPatPanel;
import MMUserInterface.MMCustomerController.MMStyles;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MMActionPanel extends MMPatPanel {

    private MMHormigaBL hormigaBL;
    private MMPatComboBox genoAlimentoComboBox;
    private MMPatComboBox ingestaNativaComboBox;
    private MMHormigueroPanel hormigueroPanel;
    private MMIngestaNativaBL ingestaNativa;
    private MMGenoAlimentoBL genoAlimento;

    public MMActionPanel(MMHormigueroPanel hormigueroPanel) {
        super(20, MMStyles.MMCOLOR_BORDER);
        this.hormigueroPanel = hormigueroPanel;

        hormigaBL = new MMHormigaBL();
        ingestaNativa = new MMIngestaNativaBL();
        genoAlimento = new MMGenoAlimentoBL();

        genoAlimentoComboBox = new MMPatComboBox(genoAlimento.obtenerNombresGenoAlimento(), "GenoAlimento");
        ingestaNativaComboBox = new MMPatComboBox(ingestaNativa.obtenerNombresIngestaNativa(), "IngestaNativa");

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(genoAlimentoComboBox, gbc);

        gbc.gridx = 1;
        add(ingestaNativaComboBox, gbc);
    }

    // Cambiar la visibilidad de private a public
    public void alimentarHormiga() {
        // Obtener la hormiga seleccionada
        MMHormigaDTO hormigaSeleccionada = hormigueroPanel.obtenerHormigaSeleccionada();

        if (hormigaSeleccionada == null) {
            System.out.println("No hay ninguna hormiga seleccionada.");
            return; // Salir del método si no hay hormiga seleccionada
        }

        String tipoAlimentoSeleccionado = "";
        boolean esGenoAlimento = genoAlimentoComboBox.getSelectedItem() != null;
        boolean esIngestaNativa = ingestaNativaComboBox.getSelectedItem() != null;

        if (esGenoAlimento) {
            tipoAlimentoSeleccionado = genoAlimentoComboBox.getSelectedItem().toString();
            hormigaSeleccionada.setIdGenoAlimento(genoAlimento.obtenerIdGenoAlimento(tipoAlimentoSeleccionado));
        } else if (esIngestaNativa) {
            tipoAlimentoSeleccionado = ingestaNativaComboBox.getSelectedItem().toString();
            hormigaSeleccionada.setIdIngestaNativa(ingestaNativa.obtenerIdIngestaNativa(tipoAlimentoSeleccionado));
        } else {
            MMStyles.showMsgError("Debe seleccionar un tipo de alimento.");
            return;
        }

        System.out.println("Alimento seleccionado: " + tipoAlimentoSeleccionado);

        if ("MUERTA".equalsIgnoreCase(hormigaSeleccionada.getNombreVida())) {
            MMStyles.showMsgError("La hormiga ya está muerta y no puede alimentarse.");
            return;
        }

        boolean mostrarEvolucion = false;

        if ("Larva".equalsIgnoreCase(hormigaSeleccionada.getTipoHormiga())
                && "VIVA".equalsIgnoreCase(hormigaSeleccionada.getNombreVida())
                && "XY".equals(tipoAlimentoSeleccionado)) {

            hormigaSeleccionada.setTipoHormiga("Zangano");
            mostrarEvolucion = true;
            hormigueroPanel.actualizarHormigaEnTabla(hormigaSeleccionada, true);
        } else if ("Larva".equalsIgnoreCase(hormigaSeleccionada.getTipoHormiga())
                && !"XY".equals(tipoAlimentoSeleccionado)) {
            System.out.println("La hormiga no puede evolucionar con este alimento.");
            MMStyles.showMsg("La hormiga no puede evolucionar con este alimento.");
        } else if ("Zangano".equalsIgnoreCase(hormigaSeleccionada.getTipoHormiga())
                && ("XY".equals(tipoAlimentoSeleccionado) || "XX".equals(tipoAlimentoSeleccionado)
                        || "X".equals(tipoAlimentoSeleccionado))) {
            MMStyles.showMsg("La hormiga Zángano no puede consumir este alimento.");
        } else if ("VIVA".equalsIgnoreCase(hormigaSeleccionada.getNombreVida())
                && "Omnivoro".equalsIgnoreCase(tipoAlimentoSeleccionado)) {
            MMStyles.showMsg("La hormiga ha comido Omnivoro, pero no evoluciona.");
        } else if ("VIVA".equalsIgnoreCase(hormigaSeleccionada.getNombreVida())
                && !"Omnivoro".equalsIgnoreCase(tipoAlimentoSeleccionado)) {
            hormigaSeleccionada.setNombreVida("MUERTA");
            MMStyles.showMsgError("La hormiga ha muerto porque no se le dio el alimento adecuado.");
            hormigueroPanel.actualizarHormigaEnTabla(hormigaSeleccionada, false);
        } else if ("Zangano".equalsIgnoreCase(hormigaSeleccionada.getTipoHormiga())
                && "Omnivoro".equalsIgnoreCase(tipoAlimentoSeleccionado)) {
            MMStyles.showMsg("La hormiga Zángano ha comido Omnivoro, pero no sucede nada.");
        } else if ("Zangano".equalsIgnoreCase(hormigaSeleccionada.getTipoHormiga())
                && !"Omnivoro".equalsIgnoreCase(tipoAlimentoSeleccionado)) {
            System.out.println("La hormiga Zángano ya ha evolucionado y no puede morir.");
            MMStyles.showMsg("La hormiga Zángano ya ha evolucionado y no puede morir.");
        } else if ("MUERTA".equalsIgnoreCase(hormigaSeleccionada.getNombreVida())) {
            MMStyles.showMsgError("La hormiga ya está muerta y no puede alimentarse.");
        }

        if (mostrarEvolucion) {
            MMStyles.showMsg("¡La hormiga ha evolucionado a Zángano!");
        }

        try {
            hormigaBL.guardarHormiga(hormigaSeleccionada);
        } catch (Exception e) {
            e.printStackTrace();
            MMStyles.showMsgError("Error al guardar los cambios de la hormiga.");
        }

        hormigueroPanel.actualizarHormigaEnTabla(hormigaSeleccionada, false);
    }
}
