import java.math.BigDecimal;

public class ComissaoArte implements iStrategyComissao {
    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.20"));
    }
}
