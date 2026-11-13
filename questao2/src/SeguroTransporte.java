import java.math.BigDecimal;
import java.math.RoundingMode;

public class SeguroTransporte implements iServicoAdicional {

    @Override
    public String getDescricao() {
        return "Seguro de transporte";
    }

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.015")).setScale(2, RoundingMode.HALF_UP);
    }
}
