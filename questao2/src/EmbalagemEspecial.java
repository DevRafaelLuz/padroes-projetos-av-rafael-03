import java.math.BigDecimal;

public class EmbalagemEspecial implements iServicoAdicional {
    @Override
    public String getDescricao() {
        return "Embalagem especial";
    }

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return new BigDecimal("180.00");
    }
}
