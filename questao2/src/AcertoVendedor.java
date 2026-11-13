import java.math.BigDecimal;

public class AcertoVendedor {
    private ResultadoLeilao resultado;

    public AcertoVendedor(ResultadoLeilao resultado) {
        if (resultado == null || !resultado.isVendido()) {
            throw new IllegalArgumentException("O acerto só pode ser realizado para um lote vendido.");
        }
        this.resultado = resultado;
    }

    public BigDecimal calcularValorLiquido() {
        return resultado.getValorLiquidoVendedor();
    }

    public void imprimir() {
        System.out.println("\n========== ACERTO DO VENDEDOR ==========");
        System.out.println("Lote: " + resultado.getLote().getNome());
        System.out.println("Valor da venda: R$ " + resultado.getValorVenda());
        System.out.println("Comissão da plataforma: R$ " + resultado.getComissao());
        System.out.println("----------------------------------------");
        System.out.println("VALOR LÍQUIDO DO VENDEDOR: R$ " + calcularValorLiquido());
        System.out.println("========================================");
    }
}
