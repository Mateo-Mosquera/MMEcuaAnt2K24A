package MMGUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import MMBusinessLogic.Entity.MMGenoAlimento;
import MMDataAccess.MMDTO;
import MMBusinessLogic.Entity.MMX;
import MMBusinessLogic.Entity.MMXX;
import MMBusinessLogic.Entity.MMXY;
import MMDataAccess.MMDAO;

public class MMUIComponent extends JFrame {

    private JComboBox<MMGenoAlimento> genoCombo;
    private JComboBox<String> ingestaCombo;
    private DefaultTableModel model;
    private MMDAO mmDao;

    public MMUIComponent() {
        // Inicializar los controles y configuraciones
        customerControls();
        // Asegurarse de que MMDAO se inicializa correctamente
        mmDao = new MMDAO();
    }

    private void customerControls() {
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
        String[] columnNames = {"Tipo Hormiga", "Ubicacion", "Sexo", "IngestaNativa con GenoAlimento", "Estado"};
        model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        table.setBackground(Color.WHITE);
        table.setForeground(textColor);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
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
        String ubicacion = "N/A"; 

        MMDTO nuevaHormiga = new MMDTO(tipoHormiga, sexo, genoAlimento, ingestaNativa, estado, ubicacion);

        try {
            mmDao.mmSave(nuevaHormiga);
            model.addRow(new Object[]{
                tipoHormiga, ubicacion, sexo, ingestaNativa + " con " + genoAlimento, estado
            });
            JOptionPane.showMessageDialog(this, "Hormiga creada y datos guardados correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar datos: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isValidAlimento(String genoAlimento, String ingestaNativa) {
        // Aquí defines las reglas de validación para saber si un alimento es válido
        switch (genoAlimento) {
            case "X":
                return ingestaNativa.equals("Carnívoro") || ingestaNativa.equals("Omnívoro");
            case "XX":
                return ingestaNativa.equals("Herbívoro") || ingestaNativa.equals("Nectanívoro");
            case "XY":
                return ingestaNativa.equals("Insectívoro") || ingestaNativa.equals("Omnívoro");
            default:
                return false;
        }
    }

}
