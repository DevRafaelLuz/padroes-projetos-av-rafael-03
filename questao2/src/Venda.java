import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Lote lote;
    private List<iServicoAdicional> servicos;

    public Venda(Lote lote) {
        if (lote == null || !lote.isVendido()) {
            throw new IllegalArgumentException("Só é possível criar uma venda para um lote vendido.");
        }

        this.lote = lote;
        this.servicos = new ArrayList<>();
    }

    public Lote getLote() {
        return lote;
    }

    public BigDecimal getValorVenda() {
        return lote.getValorVenda();
    }

    public void adicionarServico(iServicoAdicional servico) {
        if (servico == null) {
            throw new IllegalArgumentException("O serviço não pode ser nulo.");
        }

        servicos.add(servico);
    }

    public List<iServicoAdicional> getServicos() {
        return new ArrayList<>(servicos);
    }

    public BigDecimal calcularTotalServicos() {
        BigDecimal total = BigDecimal.ZERO;

        for (iServicoAdicional servico : servicos) {
            total = total.add(servico.calcular(getValorVenda()));
        }

        return total;
    }

    public BigDecimal calcularTotal() {
        return getValorVenda().add(calcularTotalServicos());
    }
}
