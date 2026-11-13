import java.math.BigDecimal;

public class Fatura {
    private Venda venda;

    public Fatura(Venda venda) {
        this.venda = venda;
    }

    public void imprimir() {
        System.out.println("\n========== FATURA DO COMPRADOR ==========");
        System.out.println("Lote: " + venda.getLote().getNome());
        System.out.println("Categoria: " + venda.getLote().getCategoria().getNome());
        System.out.println("Valor de venda: R$ " + venda.getValorVenda());
        System.out.println("\n--- Serviços adicionais ---");
        if (venda.getServicos().isEmpty()) {
            System.out.println("Nenhum serviço adicional contratado.");
        }
        for (iServicoAdicional servico : venda.getServicos()) {
            BigDecimal custo = servico.calcular(venda.getValorVenda());
            System.out.println(servico.getDescricao() + ": R$ " + custo);
        }
        System.out.println("-----------------------------------------");
        System.out.println("Total dos serviços: R$ " + venda.calcularTotalServicos());
        System.out.println("TOTAL A PAGAR: R$ " + venda.calcularTotal());
        System.out.println("=========================================");
    }
}
