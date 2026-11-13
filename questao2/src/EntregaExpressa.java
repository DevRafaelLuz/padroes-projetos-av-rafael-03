import java.math.BigDecimal;

public class EntregaExpressa implements iServicoAdicional {
    @Override
    public String getDescricao() {
        return "Entrega expressa";
    }

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return new BigDecimal("450.00");
    }
}
