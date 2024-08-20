package MMUserInterface.MMForms;

import java.awt.*;

import javax.swing.JLabel;

import MMUserInterface.MMCustomerController.MMPatPanel;
import MMUserInterface.MMCustomerController.MMStyles;

public class MMCedulaPanel extends MMPatPanel {

    public String mmNombre = "Mateo Mosquera";
    public String mmCedula = "175136688-9";
    public String mmProgramacion = ""; // Valor para Programacion II

    public MMCedulaPanel() {
        // Configuración del panel con bordes redondeados y fondo transparente
        super(20, MMStyles.MMCOLOR_BORDER); // Color panel de cedula y nombre

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Márgenes

        // Etiqueta para "Programacion II"
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel programacionLabel = new JLabel("Programacion II:");
        programacionLabel.setFont(MMStyles.MMFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        add(programacionLabel, gbc);

    ;

        // Etiqueta para "Cedula"
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel cedulaLabel = new JLabel("Cedula:");
        cedulaLabel.setFont(MMStyles.MMFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        add(cedulaLabel, gbc);

        // Mostrar el valor de la Cedula como texto
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel cedulaValueLabel = new JLabel(mmCedula);
        cedulaValueLabel.setFont(MMStyles.MMFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        add(cedulaValueLabel, gbc);

        // Etiqueta para "Nombres"
        gbc.gridx = 4;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel nombresLabel = new JLabel("Nombres:");
        nombresLabel.setFont(MMStyles.MMFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        add(nombresLabel, gbc);

        // Mostrar el valor de los Nombres como texto
        gbc.gridx = 5;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel nombresValueLabel = new JLabel(mmNombre);
        nombresValueLabel.setFont(MMStyles.MMFONT_LANGOSTIONS_SMALL); // Establecer fuente personalizada
        add(nombresValueLabel, gbc);
    }
}
