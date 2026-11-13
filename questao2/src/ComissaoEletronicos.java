import java.math.BigDecimal;

public class ComissaoEletronicos implements iStrategyComissao {
    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        BigDecimal comissao = valorVenda.multiply(new BigDecimal("0.12"));
        return comissao.max(new BigDecimal("50.00"));
    }
}
