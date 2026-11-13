import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditRecord {
    private final LocalDateTime dataHora;
    private final String ambiente;
    private final String provedor;
    private final String resultado;
    private final String motivo;

    public AuditRecord(LocalDateTime dataHora, String ambiente, String provedor, String resultado, String motivo) {
        this.dataHora = dataHora;
        this.ambiente = ambiente;
        this.provedor = provedor;
        this.resultado = resultado;
        this.motivo = motivo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public String getProvedor() {
        return provedor;
    }

    public String getResultado() {
        return resultado;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return "Data/Hora: " + dataHora.format(formatter) +
               " | Ambiente: " + ambiente +
               " | Provedor: " + provedor +
               " | Resultado: " + resultado +
               " | Motivo: " + motivo;
    }
}
