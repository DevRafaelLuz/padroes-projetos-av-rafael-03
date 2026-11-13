import java.math.BigDecimal;

public class ComissaoVeiculos implements iStrategyComissao {
    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        BigDecimal comissao = valorVenda.multiply(new BigDecimal("0.08"));
        return comissao.min(new BigDecimal("5000.00"));
    }
}
