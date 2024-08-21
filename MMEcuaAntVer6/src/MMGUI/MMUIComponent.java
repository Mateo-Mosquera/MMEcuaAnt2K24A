package MMGUI;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

import MMBusinessLogic.Entity.MMGenoAlimento;
import MMBusinessLogic.Entity.MMX;
import MMBusinessLogic.Entity.MMXX;
import MMBusinessLogic.Entity.MMXY;

public class MMUIComponent extends JFrame {

    private JComboBox<MMGenoAlimento> genoCombo;
    private JComboBox<String> ingestaCombo;
    private DefaultTableModel model;
    private JButton alimentarButton;
    private JButton eliminarButton;
    private JButton guardarButton;
    private int secuencial;

    // Colores Naturaleza
    public static final Color MMCOLOR_FOREST_GREEN = new Color(34, 139, 34); // Verde bosque
    public static final Color MMCOLOR_SAND = new Color(194, 178, 128); // Color arena
    public static final Color MMCOLOR_EARTH_BROWN = new Color(139, 69, 19); // Marrón tierra
    public static final Color MMCOLOR_SKY_BLUE = new Color(135, 206, 235); // Azul cielo
    public static final Color MMCOLOR_OCEAN_BLUE = new Color(0, 105, 148); // Azul océano
    public static final Color MMCOLOR_LEAF_GREEN = new Color(50, 205, 50); // Verde hoja

    // Colores de Advertencia y Peligro
    public static final Color MMCOLOR_WARNING_ORANGE = new Color(255, 165, 0); // Naranja de advertencia
    public static final Color MMCOLOR_DANGER_RED = new Color(220, 20, 60); // Rojo peligro
    public static final Color MMCOLOR_CAUTION_YELLOW = new Color(255, 255, 0); // Amarillo precaución
    public static final Color MMCOLOR_ALERT_RED = new Color(255, 0, 0); // Rojo alerta
    public static final Color MMCOLOR_CAUTION_ORANGE = new Color(255, 140, 0); // Naranja precaución

    // Colores Amarillos
    public static final Color MMCOLOR_SUN_YELLOW = new Color(255, 223, 0); // Amarillo sol
    public static final Color MMCOLOR_GOLDEN_YELLOW = new Color(255, 215, 0); // Amarillo dorado
    public static final Color MMCOLOR_MUSTARD_YELLOW = new Color(255, 219, 88); // Amarillo mostaza

    // Otros Colores
    public static final Color MMCOLOR_ROSE_PINK = new Color(255, 182, 193); // Rosa
    public static final Color MMCOLOR_LAVENDER_PURPLE = new Color(230, 230, 250); // Lavanda
    public static final Color MMCOLOR_MINT_GREEN = new Color(189, 252, 201); // Verde menta
    public static final Color MMCOLOR_STONE_GRAY = new Color(112, 128, 144); // Gris piedra
    public static final Color MMCOLOR_BLACK = new Color(0, 0, 0); // Negro
    public static final Color MMCOLOR_BORDER = new Color(180, 180, 180); // Color del borde claro

    // Azules
    public static final Color MMCOLOR_BLUE1 = new Color(13, 27, 42);
    public static final Color MMCOLOR_BLUE2 = new Color(27, 38, 59);
    public static final Color MMCOLOR_DEEP_SEA_BLUE = new Color(0, 51, 102); // Azul mar profundo
    public static final Color MMCOLOR_LIGHT_SKY_BLUE = new Color(135, 206, 250); // Azul cielo claro

    public MMUIComponent() {
        // Inicializar los controles y configuraciones
        customerControls();
        secuencial = loadDataFromCSV("MMData/MMHormiguero.csv"); // Cambia el path al archivo CSV
    }

    private void customerControls() {
        // Configuración del JFrame
        setTitle("MMEcuAnt 2K24A");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colores personalizados 
        Color backgroundColor = new Color(255, 219, 88); // Color del panel Hormiguero Virtual, GenoAlimento e Ingesta Nativa
        //Color buttonColor = new Color(0x4CAF50); // Color de los botones crear, alimentar, eliminar y guardar
        Color textColor = new Color(0x004D40); // Color del texto

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        // Título y logo
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setBackground(backgroundColor);

        ImageIcon frascoIcon = new ImageIcon("src/MMGUI/MMResource/MMimageFrasco.png");
        JLabel iconLabel = new JLabel(frascoIcon);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(iconLabel);

        JLabel titleLabel = new JLabel("Hormiguero Virtual");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        
        titleLabel.setForeground(MMCOLOR_BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titlePanel.add(titleLabel);

        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.setBorder(BorderFactory.createLineBorder(MMCOLOR_SAND, 4)); // Establecer borde 

        // Panel de Tabla de datos
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Registro Nro", "Tipo Hormiga", "Ubicación", "Sexo", "Ingesta Nativa", "GenoAlimento", "Estado"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setForeground(textColor);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane tableScrollPane = new JScrollPane(table);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        mainPanel.add(tablePanel, BorderLayout.CENTER);

        // Listener para habilitar botones según la selección
        table.getSelectionModel().addListSelectionListener(e -> {
            boolean isSelected = table.getSelectedRow() != -1;
            alimentarButton.setEnabled(isSelected);
            eliminarButton.setEnabled(isSelected);
        });

        // Panel de Combos y Botones
        JPanel comboButtonPanel = new JPanel(new BorderLayout());
        comboButtonPanel.setBackground(backgroundColor);

        JPanel actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setBackground(backgroundColor);
        actionPanel.setBorder(BorderFactory.createLineBorder(MMCOLOR_BORDER, 2)); // Establecer borde

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
        JLabel ingestaLabel = new JLabel("Ingesta Nativa:");
        ingestaLabel.setForeground(textColor);
        ingestaCombo = new JComboBox<>(new String[]{"Carnívoro", "Herbívoro", "Omnívoro", "Insectívoro", "Nectívoro"});

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
        buttonPanel.setBackground(MMCOLOR_BORDER); // Color de cuadro de fondo de los bonotes crear,eliminar,etc
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));    

        // Crear los botones
        JButton crearButton = new JButton("Crear Larva");
        alimentarButton = new JButton("Alimentar");
        eliminarButton = new JButton("Eliminar");
        guardarButton = new JButton("Guardar");

        // Deshabilitar botones de alimentar y eliminar hasta que se seleccione una hormiga
        alimentarButton.setEnabled(false);
        eliminarButton.setEnabled(false);

        // Añadir la acción de crear al botón
        crearButton.addActionListener(e -> {
            String tipoHormiga = "Larva";
            String ubicacion = "Aleatoria";  // Puedes cambiarlo a la ubicación deseada
            String sexo = "Asexual";
            String ingestaNativa = (String) ingestaCombo.getSelectedItem();
            String genoAlimento = ((MMGenoAlimento) genoCombo.getSelectedItem()).getTipo();
            String estado = "VIVA";

            // Añadir la nueva hormiga al modelo de la tabla
            model.addRow(new Object[]{secuencial++, tipoHormiga, ubicacion, sexo, ingestaNativa, genoAlimento, estado});
        });

        // Acción para alimentar hormiga
        alimentarButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                // Obtener datos actuales de la fila seleccionada
                String tipoHormiga = (String) model.getValueAt(selectedRow, 1);
                String estado = (String) model.getValueAt(selectedRow, 6);
                String ingestaNativa = (String) ingestaCombo.getSelectedItem();
                String genoAlimento = ((MMGenoAlimento) genoCombo.getSelectedItem()).getTipo();

                // Verificar si la combinación es XY y Nectívoro para evolución
                if (tipoHormiga.equals("Larva") && genoAlimento.equals("XY") && ingestaNativa.equals("Nectívoro")) {
                    tipoHormiga = "Zangano"; // Evolución a Zangano
                }    else if (estado.equals("VIVA") && !ingestaNativa.equals("Nectívoro")) {
                        estado = "MUERTA"; // La hormiga muere si ingesta no es Nectívoro
                }

                // Actualizar el estado de la hormiga en la tabla
                model.setValueAt(ingestaNativa, selectedRow, 4); // Actualizar IngestaNativa
                model.setValueAt(genoAlimento, selectedRow, 5);  // Actualizar GenoAlimento
                model.setValueAt(tipoHormiga, selectedRow, 1);   // Actualizar el tipo de hormiga
                model.setValueAt(estado, selectedRow, 6);        // Actualizar el estado de la hormiga
                    
                if (estado.equals("MUERTA")) {
                    JOptionPane.showMessageDialog(this, "La hormiga ha muerto al comer " + ingestaNativa + ".", "Alimentar", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Larva ha sido alimentada y evolucionada a " + tipoHormiga + ".", "Alimentar", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Acción para eliminar hormiga
        eliminarButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Hormiga eliminada.", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Acción para guardar datos
        guardarButton.addActionListener(e -> saveDataToCSV("MMData/MMHormiguero.csv"));

        // Personalizar botones de crear, guardar, eliminar, alimentar
        Color buttonTextColor = Color.WHITE;

        crearButton.setBackground(MMCOLOR_FOREST_GREEN);
        crearButton.setForeground(buttonTextColor);

        alimentarButton.setBackground(MMCOLOR_WARNING_ORANGE);
        alimentarButton.setForeground(buttonTextColor);

        eliminarButton.setBackground(MMCOLOR_DANGER_RED);
        eliminarButton.setForeground(buttonTextColor);

        guardarButton.setBackground(MMCOLOR_BLUE2);
        guardarButton.setForeground(buttonTextColor);
        // Agregar los botones al panel
        buttonPanel.add(crearButton);
        buttonPanel.add(alimentarButton);
        buttonPanel.add(eliminarButton);
        buttonPanel.add(guardarButton);

        comboButtonPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(comboButtonPanel, BorderLayout.SOUTH);

        // Configurar la barra de estado
        JPanel statusBar = new JPanel(new GridBagLayout());
        statusBar.setBackground(MMCOLOR_MUSTARD_YELLOW); // Color de fondo
        statusBar.setBorder(BorderFactory.createLineBorder(MMCOLOR_SAND, 4)); // Borde

        GridBagConstraints mmGBC = new GridBagConstraints();
        mmGBC.insets = new Insets(5, 10, 5, 10);
        mmGBC.anchor = GridBagConstraints.CENTER;
        mmGBC.fill = GridBagConstraints.HORIZONTAL;
        mmGBC.weightx = 1.0;

        // Configurar las etiquetas
        JLabel programacionLabel = new JLabel("Programación II");
        JLabel cedulaLabel = new JLabel("Cédula: 1751366889");
        JLabel nombresLabel = new JLabel("Nombres: Mosquera Mateo");

        programacionLabel.setForeground(MMCOLOR_BLACK);
        cedulaLabel.setForeground(MMCOLOR_BLACK);
        nombresLabel.setForeground(MMCOLOR_BLACK);

        // Añadir componentes al panel con GridBagLayout
        mmGBC.gridx = 0;
        mmGBC.weightx = 1.0;
        mmGBC.anchor = GridBagConstraints.WEST;
        statusBar.add(programacionLabel, mmGBC);

        mmGBC.gridx = 1;
        mmGBC.weightx = 1.0;
        mmGBC.anchor = GridBagConstraints.CENTER;
        statusBar.add(cedulaLabel, mmGBC);

        mmGBC.gridx = 2;
        mmGBC.weightx = 1.0;
        mmGBC.anchor = GridBagConstraints.EAST;
        statusBar.add(nombresLabel, mmGBC);

        // Añadir la barra de estado al JFrame
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }
        
    private int loadDataFromCSV(String filePath) {
        int lastSecuencial = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    try {
                        int sec = Integer.parseInt(data[0].trim());
                        if (sec >= lastSecuencial) {
                            lastSecuencial = sec + 1;
                        }
                    } catch (NumberFormatException e) {
                        // Ignorar líneas con formato incorrecto
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastSecuencial;
    }

    private void saveDataToCSV(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))) { // 'true' para modo append
            for (int i = 0; i < model.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    sb.append(model.getValueAt(i, j)).append(",");
                }
                sb.deleteCharAt(sb.length() - 1); // Eliminar la última coma
                bw.write(sb.toString());
                bw.newLine();
            }
            JOptionPane.showMessageDialog(this, "Datos guardados exitosamente.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }      
}
