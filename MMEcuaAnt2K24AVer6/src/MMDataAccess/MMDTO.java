package MMDataAccess;

public class MMDTO {
    private String tipoHormiga;
    private String sexo;
    private String genoAlimento;
    private String ingestaNativa;
    private String estado;
    private String ubicacion;

    public MMDTO(String tipoHormiga, String sexo, String genoAlimento, String ingestaNativa, String estado, String ubicacion) {
        this.tipoHormiga = tipoHormiga;
        this.sexo = sexo;
        this.genoAlimento = genoAlimento;
        this.ingestaNativa = ingestaNativa;
        this.estado = estado;
        this.ubicacion = ubicacion;
    }

    public String toCSV() {
        return tipoHormiga + "," + sexo + "," + genoAlimento + "," + ingestaNativa + "," + estado + "," + ubicacion;
    }

    public String getTipoHormiga() {
        return tipoHormiga;
    }

    public void setTipoHormiga(String tipoHormiga) {
        this.tipoHormiga = tipoHormiga;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getGenoAlimento() {
        return genoAlimento;
    }

    public void setGenoAlimento(String genoAlimento) {
        this.genoAlimento = genoAlimento;
    }

    public String getIngestaNativa() {
        return ingestaNativa;
    }

    public void setIngestaNativa(String ingestaNativa) {
        this.ingestaNativa = ingestaNativa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}
