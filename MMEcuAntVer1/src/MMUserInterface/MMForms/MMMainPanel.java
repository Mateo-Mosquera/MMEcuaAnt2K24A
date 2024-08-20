package MMUserInterface.MMForms;

import javax.swing.*;

import MMUserInterface.MMCustomerController.MMStyles;

import java.awt.*;

public class MMMainPanel extends JPanel {

    private MMHormigueroPanel hormigueroPanel;
    // private JPanel panelCambio; // Panel que contiene el CardLayout

    public MMMainPanel() {
        setBackground(MMStyles.MMCOLOR_GREEN);
        setOpaque(true);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;

        // Fila 1: CedulaPanel
        gbc.gridx = 10;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new MMCedulaPanel(), gbc);

        // Panel para el hormiguero en el área principal
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weighty = 0.6;
        gbc.anchor = GridBagConstraints.CENTER;
        hormigueroPanel = new MMHormigueroPanel(this);
        add(hormigueroPanel, gbc);

        // Fila 3: ActionPanel
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        MMActionPanel actionPanel = new MMActionPanel(hormigueroPanel);
        add(actionPanel, gbc);

        // Fila 4: BottomPanel
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        MMBottomPanel bottomPanel = new MMBottomPanel(hormigueroPanel, actionPanel);
        add(bottomPanel, gbc);
    }

    // Método para obtener el panel del hormiguero
    public MMHormigueroPanel getHormigueroPanel() {
        return hormigueroPanel;
    }
}
