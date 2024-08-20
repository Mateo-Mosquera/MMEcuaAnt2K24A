package MMUserInterface.MMCustomerController;

import javax.swing.JComboBox;
import java.awt.Dimension;
import java.util.List;

public class MMPatComboBox extends JComboBox<String> {

    public MMPatComboBox(String[] items) {
        super(items);
        customizeComponent();

    }

    public MMPatComboBox(List<String> items, String MMNombreComboBox) {
        super();
        // Agregar el elemento por defecto al inicio
        addItem(MMNombreComboBox);

        // Agregar el resto de los elementos
        for (String item : items) {
            addItem(item);
        }

        // Personalizar el componente
        customizeComponent();
    }

    private void customizeComponent() {
        setFont(MMStyles.MMFONT_LANGOSTIONS_SMALL);
        setForeground(MMStyles.MMCOLOR_FONT);
        // Ajustar el tamaño preferido del ComboBox
        setPreferredSize(new Dimension(150, 30)); // Puedes ajustar estas dimensiones según tus necesidades
        // setBackground(new Color(173, 216, 230)); // Color azul claro
        // setBorder(MMStyles.MMCreateBorderRect()); // Bordes redondeados
        setSelectedIndex(0);
    }

    @Override
    public void setSelectedIndex(int index) {
        // Evitar la selección del primer elemento (el texto por defecto)
        if (index == 0) {
            return;
        }
        super.setSelectedIndex(index);
    }
}
