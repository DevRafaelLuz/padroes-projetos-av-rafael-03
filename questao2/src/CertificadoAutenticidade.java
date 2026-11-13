import java.math.BigDecimal;

public class CertificadoAutenticidade implements iServicoAdicional {
    @Override
    public String getDescricao() {
        return "Certificado de autenticidade";
    }

    @Override
    public BigDecimal calcular(BigDecimal valorVenda) {
        return new BigDecimal("300.00");
    }
}
