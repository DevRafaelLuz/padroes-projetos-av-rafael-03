import java.math.BigDecimal;

public class ResultadoLeilao {
    private Lote lote;
    private boolean vendido;
    private BigDecimal valorVenda;
    private BigDecimal comissao;
    private Lance lanceVencedor;

    public ResultadoLeilao(Lote lote) {
        if (lote == null) {
            throw new IllegalArgumentException("O lote não pode ser nulo.");
        }

        if (lote.isAberto()) {
            throw new IllegalArgumentException("Não é possível gerar o resultado de um lote ainda aberto.");
        }

        this.lote = lote;
        this.vendido = lote.isVendido();
        this.valorVenda = lote.getValorVenda();
        this.comissao = lote.getComissaoCobrada();

        if (vendido && !lote.getLances().isEmpty()) {
            this.lanceVencedor = lote.getLances().get(lote.getLances().size() - 1);
        }
    }

    public Lote getLote() {
        return lote;
    }

    public boolean isVendido() {
        return vendido;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public Lance getLanceVencedor() {
        return lanceVencedor;
    }

    public BigDecimal getValorLiquidoVendedor() {
        return valorVenda.subtract(comissao);
    }
}
