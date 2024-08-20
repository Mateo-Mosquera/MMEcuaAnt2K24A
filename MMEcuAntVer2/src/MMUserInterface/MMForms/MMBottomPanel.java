package MMUserInterface.MMForms;

import MMBusinessLogic.MMCrearHormigaBL;
import MMBusinessLogic.MMHormigaBL;
import MMDataAccess.MMDTO.MMHormigaDTO;
import MMUserInterface.MMCustomerController.MMPatButton;
import MMUserInterface.MMCustomerController.MMPatPanel;
import MMUserInterface.MMCustomerController.MMStyles;

import java.awt.*;

public class MMBottomPanel extends MMPatPanel {

    private MMHormigueroPanel hormigueroPanel;
    private MMActionPanel actionPanel;
    private MMHormigaBL hormigaBL;
    private MMCrearHormigaBL crearHormigaBL;

    public MMBottomPanel(MMHormigueroPanel hormigueroPanel, MMActionPanel actionPanel) {
        super(15, MMStyles.MMCOLOR_BORDER); // color cuadro fondo de botones eliminar, crear, guardar, alimentar
        this.hormigueroPanel = hormigueroPanel;
        this.actionPanel = actionPanel;
        hormigaBL = new MMHormigaBL();
        crearHormigaBL = new MMCrearHormigaBL();

        MMPatButton buttonEliminar = new MMPatButton("Eliminar", false, MMStyles.MMFONT_LANGOSTIONS_SMALL);
        MMPatButton buttonCrear = new MMPatButton("Crear hormiga larva", false, MMStyles.MMFONT_LANGOSTIONS_SMALL);
        MMPatButton buttonGuardar = new MMPatButton("Guardar", false, MMStyles.MMFONT_LANGOSTIONS_SMALL);
        MMPatButton buttonAlimentar = new MMPatButton("Alimentar", false, MMStyles.MMFONT_LANGOSTIONS_SMALL);

        buttonEliminar.setBackground(MMStyles.MMCOLOR_DANGER_RED);
        buttonCrear.setBackground(MMStyles.MMCOLOR_FOREST_GREEN);
        buttonGuardar.setBackground(MMStyles.MMCOLOR_GOLD);
        buttonAlimentar.setBackground(MMStyles.MMCOLOR_FOREST_GREEN);

        buttonGuardar.addActionListener(e -> guardarCambios());
        buttonEliminar.addActionListener(e -> eliminarHormiga());
        
        buttonCrear.addActionListener(e -> {
            boolean confirm = MMStyles.showConfirmYesNo("¿Estás seguro de que deseas crear una nueva hormiga larva?");
            if (confirm) {
                boolean creada = crearHormigaBL.crearNuevaHormigaConValoresPorDefecto();
                if (creada) {
                    hormigueroPanel.cargarHormigas();
                    MMStyles.showMsg("Hormiga larva creada exitosamente");
                } else {
                    MMStyles.showMsgError("Error al crear la hormiga larva");
                }
            }
        });

        buttonAlimentar.addActionListener(e -> {
            if (actionPanel != null) {
                actionPanel.alimentarHormiga(); // Llama al método alimentarHormiga() de MMActionPanel
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(buttonCrear, gbc);       

        gbc.gridx = 1;
        add(buttonAlimentar, gbc);

        gbc.gridx = 2;
        add(buttonEliminar, gbc);

        gbc.gridx = 3;
        add(buttonGuardar, gbc);
    }

    private void guardarCambios() {
        boolean confirmacion = MMStyles.showConfirmYesNo("¿Está seguro que desea guardar todos los cambios?");
        System.out.println("Confirmación de guardado: " + confirmacion);

        if (confirmacion) {
            try {
                hormigaBL.guardarTodosLosCambios();
                MMStyles.showMsg("Todos los cambios han sido guardados exitosamente.");
                hormigueroPanel.cargarHormigas();
            } catch (Exception e) {
                e.printStackTrace();
                MMStyles.showMsgError("Error al guardar los cambios.");
            }
        } else {
            System.out.println("Guardado cancelado por el usuario.");
        }
    }

    private void eliminarHormiga() {
        MMHormigaDTO hormiga = hormigueroPanel.obtenerHormigaSeleccionada();
        if (hormiga != null) {
            boolean confirmacion = MMStyles.showConfirmYesNo("¿Está seguro que desea eliminar esta hormiga?");
            if (confirmacion) {
                try {
                    hormigaBL.eliminarHormiga(hormiga.getIdHormiga());
                    hormigueroPanel.cargarHormigas();
                    MMStyles.showMsg("Hormiga eliminada exitosamente.");
                } catch (Exception e) {
                    e.printStackTrace();
                    MMStyles.showMsgError("Error al eliminar la hormiga.");
                }
            }
        }
    }
}
