package MMUserInterface.MMCustomerController;

import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Insets;

public class MMPatTextBox extends JTextField {

    public MMPatTextBox() {
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(MMStyles.MMFONT);
        setForeground(MMStyles.MMCOLOR_FONT_LIGHT);
        setCaretColor(MMStyles.MMCOLOR_CURSOR);
        setMargin(new Insets(5, 5, 5, 5));
        setBorderRect();
    }

    public void setBorderRect() {
        Border lineBorder = BorderFactory.createLineBorder(MMStyles.MMCOLOR_BORDER);
        Border emptyBorder = new EmptyBorder(5, 5, 5, 5);
        setBorder(new CompoundBorder(lineBorder, emptyBorder));
    }
}
