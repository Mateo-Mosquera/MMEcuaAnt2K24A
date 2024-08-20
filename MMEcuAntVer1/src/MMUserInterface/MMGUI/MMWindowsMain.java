package MMUserInterface.MMGUI;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import MMUserInterface.MMCustomerController.MMPatPanelBar;
import MMUserInterface.MMForms.MMMainPanel;

import java.awt.BorderLayout;
import java.awt.Container;

public class MMWindowsMain extends JFrame {

    public MMWindowsMain(String titleApp) {
        super(titleApp); // Pasar el título a la superclase JFrame
        customizeComponent(titleApp);
    }

    private void customizeComponent(String titleApp) {
        setUndecorated(true);
        setSize(600, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Configurar el contenedor principal
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        // Añadir barra de título personalizada
        container.add(new MMPatPanelBar(this), BorderLayout.NORTH);

        // Añadir panel principal con CardLayout
        MMMainPanel mainPanel = new MMMainPanel();
        container.add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Iniciar la aplicación en el hilo de despacho de eventos
        SwingUtilities.invokeLater(() -> {
            new MMWindowsMain("Hormiguero Virtual").setVisible(true);
        });
    }
}
