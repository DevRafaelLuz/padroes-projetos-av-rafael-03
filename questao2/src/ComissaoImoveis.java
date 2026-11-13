import java.math.BigDecimal;

public class ComissaoImoveis implements iStrategyComissao {
    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return valorVenda.multiply(new BigDecimal("0.03")).add(new BigDecimal("1500.00"));
    }
}
