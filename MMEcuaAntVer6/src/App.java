import javax.swing.SwingUtilities;

import MMGUI.MMUIComponent;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(() -> new MMUIComponent().setVisible(true));
    }
}