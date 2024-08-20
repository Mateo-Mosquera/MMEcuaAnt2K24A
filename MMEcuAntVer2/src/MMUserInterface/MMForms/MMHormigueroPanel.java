package MMUserInterface.MMForms;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import MMBusinessLogic.MMCrearHormigaBL;
import MMBusinessLogic.MMGetHormigaBL;
import MMDataAccess.MMDAO.MMHormigaDAO;
import MMDataAccess.MMDTO.MMHormigaDTO;
import MMUserInterface.MMCustomerController.MMPatPanel;
import MMUserInterface.MMCustomerController.MMStyles;

import java.awt.*;
import java.util.List;

public class MMHormigueroPanel extends MMPatPanel {

    private JTable tablaHormigas;
    private MMHormigaDAO hormigaDAO;
    private MMGetHormigaBL getHormigaBL;
    private MMCrearHormigaBL crearHormigaBL;
    // private MMMainPanel mainPanel;

    public MMHormigueroPanel(MMMainPanel mainPanel) {
        super(20, MMStyles.MMCOLOR_BORDER); // color de cuadro de fondo de Hormiguero Virtual
        setOpaque(true);
        // this.mainPanel = mainPanel;
        hormigaDAO = new MMHormigaDAO();
        getHormigaBL = new MMGetHormigaBL();
        crearHormigaBL = new MMCrearHormigaBL(); // Instancia de MMCrearHormigaBL

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        initializeComponents(gbc);
        cargarHormigas();
    }

    private void initializeComponents(GridBagConstraints gbc) {
        // Configuración del contenedor para el icono y el título
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; // Abarca varias columnas
        gbc.anchor = GridBagConstraints.CENTER; // Centra el contenedor
        gbc.weightx = 1.0; // Abarca todo el ancho disponible
    
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false); // Hacer que el panel sea transparente
    
        // Configuración del logo
        JLabel iconLabel = new JLabel(new ImageIcon(MMStyles.URL_ICON_ANT));
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(iconLabel, BorderLayout.NORTH);
    
        // Configuración del título del panel
        JLabel titleLabel = new JLabel("Hormiguero Virtual", JLabel.CENTER);
        titleLabel.setFont(MMStyles.MMFONT_LANGOSTIONS);
        titlePanel.add(titleLabel, BorderLayout.CENTER);
    
        add(titlePanel, gbc);
    
        // Configuración de la tabla que muestra las hormigas
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tablaHormigas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaHormigas);
        add(scrollPane, gbc);
    }
    

    // Método para cargar las hormigas en la tabla
    public void cargarHormigas() {
        try {
            List<MMHormigaDTO> hormigas = hormigaDAO.mmReadAll();
            // Se crea una tabla estandar y se coloca el nombre a sus respectivos campos
            DefaultTableModel model = new DefaultTableModel(
                    new String[] { "ID Hormiga", "Tipo Hormiga", "Sexo", "GenoAlimento", "IngestaNativa", "Provincia",
                            "Estado" },
                    0);

            for (MMHormigaDTO hormiga : hormigas) {
                // Estos String estaren los nombres o los registros de cada campo de la hormiga
                String nombreSexo = getHormigaBL.obtenerNombreSexo(hormiga.getIdSexo());
                String nombreGenoAlimento = getHormigaBL.obtenerNombreGenoAlimento(hormiga.getIdGenoAlimento());
                String nombreIngestaNativa = getHormigaBL.obtenerNombreIngestaNativa(hormiga.getIdIngestaNativa());
                String nombreProvincia = getHormigaBL.obtenerNombreProvincia(hormiga.getIdProvincia());

                // Coloca un N/A en los campos que deberian estar vacios pero la tabla no acepta
                // Null nombreSexo = nombreSexo.equalsIgnoreCase("Desconocido") ? "N/A" :
                // nombreSexo;
                nombreGenoAlimento = nombreGenoAlimento.equalsIgnoreCase("Desconocido") ? "N/A" : nombreGenoAlimento;
                nombreIngestaNativa = nombreIngestaNativa.equalsIgnoreCase("Desconocido") ? "N/A" : nombreIngestaNativa;

                // Se les añado estos valores a la fila de la tabla
                model.addRow(new Object[] {
                        hormiga.getIdHormiga(),
                        hormiga.getTipoHormiga(),
                        nombreSexo,
                        nombreGenoAlimento,
                        nombreIngestaNativa,
                        nombreProvincia,
                        hormiga.getNombreVida()
                });

                // Este proceso se realizara hasta que terminen todos las hormigas registradas
                // por eso se usar el for(Enty enty : enty)
            }

            tablaHormigas.setModel(model);

            // Ajustar el ancho de la columna del ID para mejor visibilidad
            tablaHormigas.getColumnModel().getColumn(0).setMinWidth(50);
            tablaHormigas.getColumnModel().getColumn(0).setMaxWidth(100);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar hormigas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para obtener la hormiga seleccionada en la tabla
    public MMHormigaDTO obtenerHormigaSeleccionada() {
        int selectedRow = tablaHormigas.getSelectedRow();
        if (selectedRow != -1) {
            try {
                // Obtener el ID de la hormiga desde la columna 0
                Integer hormigaId = (Integer) tablaHormigas.getValueAt(selectedRow, 0);

                return hormigaDAO.mmReadAll().stream()
                        .filter(h -> h.getIdHormiga() == hormigaId) // Comparación por ID
                        .findFirst().orElse(null);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al obtener la hormiga seleccionada", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    // Método para actualizar la hormiga en la tabla, especialmente después de la
    // evolución
    public void actualizarHormigaEnTabla(MMHormigaDTO hormiga, boolean evolucionada) {
        int selectedRow = tablaHormigas.getSelectedRow();
        if (selectedRow != -1) {
            try {
                // Actualizar las celdas de la fila seleccionada
                tablaHormigas.setValueAt(hormiga.getIdHormiga(), selectedRow, 0); // Actualizar el ID
                tablaHormigas.setValueAt(hormiga.getTipoHormiga(), selectedRow, 1); // Actualizar el tipo de hormiga
                tablaHormigas.setValueAt(getHormigaBL.obtenerNombreSexo(hormiga.getIdSexo()), selectedRow, 2); // Actualizar
                                                                                                               // sexo
                tablaHormigas.setValueAt(getHormigaBL.obtenerNombreGenoAlimento(hormiga.getIdGenoAlimento()),
                        selectedRow, 3); // Actualizar genoalimento
                tablaHormigas.setValueAt(getHormigaBL.obtenerNombreIngestaNativa(hormiga.getIdIngestaNativa()),
                        selectedRow, 4); // Actualizar ingesta nativa
                tablaHormigas.setValueAt(getHormigaBL.obtenerNombreProvincia(hormiga.getIdProvincia()), selectedRow, 5); // Actualizar
                                                                                                                         // provincia
                tablaHormigas.setValueAt(hormiga.getNombreVida(), selectedRow, 6); // Actualizar el estado (nombreVida)

                // Mostrar un mensaje si la hormiga ha evolucionado
                if (evolucionada) {
                    JOptionPane.showMessageDialog(this,
                            "La hormiga ha evolucionado a " + hormiga.getTipoHormiga() + "!",
                            "Evolución", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (ClassCastException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al actualizar la tabla: Tipo de dato incorrecto", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al actualizar la hormiga en la tabla", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
