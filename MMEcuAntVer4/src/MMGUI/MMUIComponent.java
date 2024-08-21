package MMGUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import MMBusinessLogic.Entity.MMGenoAlimento;
import MMBusinessLogic.Entity.MMX;
import MMBusinessLogic.Entity.MMXX;
import MMBusinessLogic.Entity.MMXY;
import MMFramework.MMException;
import MMDataAccess.MMDAO;
import MMDataAccess.MMDTO;

public class MMUIComponent extends JFrame {

    private JComboBox<MMGenoAlimento> genoCombo;
    private JComboBox<String> ingestaCombo;
    private DefaultTableModel model;
    private MMDAO mmDao;
    private JTable table;

    public MMUIComponent() {
        customerControls();
        mmDao = new MMDAO();
    }

    private void customerControls() {
        String[] columnNames = {"RegNro", "TipoHormiga", "Ubicacion", "Sexo", "IngestaNativa", "GenoAlimento", "Estado"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model); // Usa la tabla declarada a nivel de clase

        // Configuración del JFrame
        setTitle("MMEcuAnt 2K24A");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colores personalizados
        Color backgroundColor = new Color(0xC8DCE0);
        Color buttonColor = new Color(0x6496C8);
        Color textColor = new Color(0x333333);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Título y logo
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(backgroundColor);

        ImageIcon frascoIcon = new ImageIcon("src/MMGUI/MMimageFrasco.png");
        JLabel iconLabel = new JLabel(frascoIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(iconLabel);

        JLabel titleLabel = new JLabel("Hormiguero virtual");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(textColor);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Panel de Tabla de datos
        JPanel tablePanel = new JPanel(new BorderLayout());
        table.setBackground(Color.WHITE);
        table.setForeground(textColor);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permite seleccionar una fila
        JScrollPane tableScrollPane = new JScrollPane(table);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Panel de Combos y Botones
        JPanel comboButtonPanel = new JPanel(new BorderLayout());
        comboButtonPanel.setBackground(backgroundColor);

        JPanel actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setBackground(backgroundColor);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Etiqueta y ComboBox para GenoAlimento
        JLabel genoAlimentoLabel = new JLabel("Geno Alimento:");
        genoAlimentoLabel.setForeground(textColor);

        ArrayList<MMGenoAlimento> lstGenoAlimentos = new ArrayList<>();
        lstGenoAlimentos.add(new MMX("X"));
        lstGenoAlimentos.add(new MMXX("XX"));
        lstGenoAlimentos.add(new MMXY("XY"));

        genoCombo = new JComboBox<>(lstGenoAlimentos.toArray(new MMGenoAlimento[0]));

        // Etiqueta y ComboBox para IngestaNativa
        JLabel ingestaLabel = new JLabel("IngestaNativa:");
        ingestaLabel.setForeground(textColor);
        ingestaCombo = new JComboBox<>(new String[]{"Carnívoro", "Herbívoro", "Omnívoro", "Insectívoro", "Nectanívoro"});

        // Agregar componentes al layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        actionPanel.add(genoAlimentoLabel, gbc);

        gbc.gridx = 1;
        actionPanel.add(genoCombo, gbc);

        gbc.gridx = 2;
        actionPanel.add(ingestaLabel, gbc);

        gbc.gridx = 3;
        actionPanel.add(ingestaCombo, gbc);

        comboButtonPanel.add(actionPanel, BorderLayout.CENTER);

        // Panel para los botones de Crear, Alimentar, Eliminar, Guardar
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(new Color(0xE6E6FA));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear los botones
        JButton crearButton = new JButton("Crear Larva");
        JButton alimentarButton = new JButton("Alimentar");
        JButton eliminarButton = new JButton("Eliminar");
        JButton guardarButton = new JButton("Guardar");

        // Añadir la acción de guardar al botón
        crearButton.addActionListener(e -> mmSaveData());
        alimentarButton.addActionListener(e -> mmFeedData());
        eliminarButton.addActionListener(e -> mmDeleteData());
        guardarButton.addActionListener(e -> mmGuardarDatosEnArchivo()); // Añadir acción para guardar en archivo

        // Personalizar botones
        Color buttonTextColor = Color.WHITE;

        crearButton.setBackground(buttonColor);
        crearButton.setForeground(buttonTextColor);

        alimentarButton.setBackground(buttonColor);
        alimentarButton.setForeground(buttonTextColor);

        eliminarButton.setBackground(buttonColor);
        eliminarButton.setForeground(buttonTextColor);

        guardarButton.setBackground(buttonColor);
        guardarButton.setForeground(buttonTextColor);

        // Agregar los botones al panel
        buttonPanel.add(crearButton);
        buttonPanel.add(alimentarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(guardarButton);

        comboButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(comboButtonPanel, BorderLayout.SOUTH);

        // Barra de estado
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel programacionLabel = new JLabel("Programación II");
        JLabel cedulaLabel = new JLabel("Cédula: 1751366889");
        JLabel nombresLabel = new JLabel("Nombres: Mosquera Mateo");
        programacionLabel.setForeground(textColor);
        cedulaLabel.setForeground(textColor);
        nombresLabel.setForeground(textColor);
        statusBar.add(programacionLabel);
        statusBar.add(new JLabel("               |"));
        statusBar.add(cedulaLabel);
        statusBar.add(new JLabel("               |"));
        statusBar.add(nombresLabel);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void mmSaveData() {
        if (mmDao == null) {
            JOptionPane.showMessageDialog(this, "Error: mmDao no está inicializado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Configurando valores predeterminados para la hormiga larva
        String tipoHormiga = "Larva";
        String sexo = "Asexual";
        String genoAlimento = ((MMGenoAlimento) genoCombo.getSelectedItem()).getTipo();
        String ingestaNativa = (String) ingestaCombo.getSelectedItem();
        String estado = "VIVA";
        String ubicacion = "Ecuador";

        // Obtener el siguiente número de registro
        int regNro = model.getRowCount() + 1;

        // Crear nueva hormiga con el número de registro
        MMDTO nuevaHormiga = new MMDTO(tipoHormiga, sexo, genoAlimento, ingestaNativa, estado, ubicacion, regNro);

        try {
            mmDao.mmSave(nuevaHormiga);
            model.addRow(new Object[]{
                regNro, tipoHormiga, ubicacion, sexo, ingestaNativa, genoAlimento, estado
            });            
            JOptionPane.showMessageDialog(this, "Hormiga creada y datos guardados correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mmFeedData() {
        int selectedRow = table.getSelectedRow(); // Obtén la fila seleccionada desde la tabla
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una hormiga para alimentar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tipoHormiga = (String) model.getValueAt(selectedRow, 1); // Tipo de hormiga
        if ("Larva".equals(tipoHormiga)) {
            model.setValueAt("Hormiga", selectedRow, 1); // Cambia el tipo de la hormiga a "Hormiga"
            model.setValueAt("ACTIVA", selectedRow, 6); // Actualiza el estado a "ACTIVA"
            JOptionPane.showMessageDialog(this, "La hormiga ha sido alimentada y ahora es una Hormiga ACTIVA.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Solo las larvas pueden ser alimentadas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mmDeleteData() {
        int selectedRow = table.getSelectedRow(); // Obtén la fila seleccionada
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una hormiga para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Confirmación antes de eliminar
        int confirm = JOptionPane.showConfirmDialog(this, 
        "¿Está seguro de que desea eliminar esta hormiga?", 
                "Confirmar eliminación", 
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.removeRow(selectedRow); // Elimina la fila seleccionada de la tabla
            JOptionPane.showMessageDialog(this, 
            "La hormiga ha sido eliminada.", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    

    private void mmGuardarDatosEnArchivo() {
        try {
            FileWriter csvWriter = new FileWriter("MMHormiguero.csv");

            // Escribir encabezados
            for (int i = 0; i < model.getColumnCount(); i++) {
                csvWriter.append(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    csvWriter.append(",");
                }
            }
            csvWriter.append("\n");

            // Escribir datos de las filas
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < model.getColumnCount(); j++) {
                    csvWriter.append(String.valueOf(model.getValueAt(i, j)));
                    if (j < model.getColumnCount() - 1) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            JOptionPane.showMessageDialog(this, "Datos guardados en MMHormiguero.csv", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar datos en archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MMUIComponent::new);
    }
}
