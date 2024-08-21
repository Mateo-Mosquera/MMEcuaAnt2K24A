package MMDataAccess;

public class MMDTO {
    private String tipoHormiga;
    private String sexo;
    private String genoAlimento;
    private String ingestaNativa;
    private String estado;
    private String ubicacion;
    private int regNro; // Agregado para el número de registro

    // Constructor con todos los parámetros
    public MMDTO(String tipoHormiga, String sexo, String genoAlimento, String ingestaNativa, String estado, String ubicacion, int regNro) {
        this.tipoHormiga = tipoHormiga;
        this.sexo = sexo;
        this.genoAlimento = genoAlimento;
        this.ingestaNativa = ingestaNativa;
        this.estado = estado;
        this.ubicacion = ubicacion;
        this.regNro = regNro;
    }

    // Getters y setters
    public String getTipoHormiga() { return tipoHormiga; }
    public String getSexo() { return sexo; }
    public String getGenoAlimento() { return genoAlimento; }
    public String getIngestaNativa() { return ingestaNativa; }
    public String getEstado() { return estado; }
    public String getUbicacion() { return ubicacion; }
    public int getRegNro() { return regNro; }

    public void setRegNro(int regNro) { this.regNro = regNro; }

    public String toCSV() {
        return tipoHormiga + "," + sexo + "," + genoAlimento + "," + ingestaNativa + "," + estado + "," + ubicacion + "," + regNro;
    }

    public static MMDTO fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        if (values.length != 7) {
            throw new IllegalArgumentException("La línea CSV no tiene el formato correcto");
        }
        return new MMDTO(
            values[0], // tipoHormiga
            values[1], // sexo
            values[2], // genoAlimento
            values[3], // ingestaNativa
            values[4], // estado
            values[5], // ubicacion
            Integer.parseInt(values[6]) // regNro
        );
    }
}
