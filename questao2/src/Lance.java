import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Lance {
    private Participante participante;
    private BigDecimal valor;
    private LocalDateTime dataHora;

    public Lance(Participante participante, BigDecimal valor) {
        this.participante = participante;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }

    public Participante getParticipante() {
        return participante;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}
