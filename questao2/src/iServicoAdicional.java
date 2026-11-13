import java.math.BigDecimal;

public interface iServicoAdicional {
    String getDescricao();
    BigDecimal calcular(BigDecimal valorVenda);
}
