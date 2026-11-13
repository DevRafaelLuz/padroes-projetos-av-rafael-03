import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carlos = new Participante("Carlos");

        PainelLeiloeiro painel = new PainelLeiloeiro();
        Auditor auditor = new Auditor();

        Lote loteArte = new Lote(
            "Pintura Abstrata",
            new Arte(),
            new BigDecimal("10000.00"),
            new BigDecimal("10500.00"),
            new ComissaoArte()
        );

        Lote loteEletronico = new Lote(
            "Notebook Profissional",
            new Eletronicos(),
            new BigDecimal("5000.00"),
            new BigDecimal("6000.00"),
            new ComissaoEletronicos()
        );

        loteArte.adicionarInteressado(ana);
        loteArte.adicionarInteressado(bruno);
        loteArte.adicionarInteressado(painel);
        loteArte.adicionarInteressado(auditor);

        loteEletronico.adicionarInteressado(ana);
        loteEletronico.adicionarInteressado(carlos);
        loteEletronico.adicionarInteressado(painel);
        loteEletronico.adicionarInteressado(auditor);

        System.out.println("\n========== LEILÃO DE ARTE ==========");
        System.out.println(loteArte.darLance(ana, new BigDecimal("10500.00")));
        System.out.println(loteArte.darLance(bruno, new BigDecimal("11000.00")));
        System.out.println(loteArte.darLance(ana, new BigDecimal("11000.00")));

        loteArte.removerInteressado(bruno);

        System.out.println("\nBruno deixou de acompanhar o lote de arte.");
        System.out.println(loteArte.darLance(ana, new BigDecimal("12000.00")));
        System.out.println("\n========== CAMPANHA PROMOCIONAL ==========");

        loteArte.alterarComissao(new ComissaoEletronicos());

        System.out.println("Estratégia de comissão alterada para a campanha.");
        System.out.println("\n========== ENCERRAMENTO DO LOTE DE ARTE ==========");

        loteArte.encerrar();

        ResultadoLeilao resultadoArte = new ResultadoLeilao(loteArte);

        if (resultadoArte.isVendido()) {
            Venda venda = new Venda(loteArte);

            venda.adicionarServico(new SeguroTransporte());
            venda.adicionarServico(new CertificadoAutenticidade());
            venda.adicionarServico(new EmbalagemEspecial());
            venda.adicionarServico(new EntregaExpressa());
            venda.adicionarServico(new CertificadoAutenticidade());

            Fatura fatura = new Fatura(venda);
            fatura.imprimir();

            AcertoVendedor acerto = new AcertoVendedor(resultadoArte);

            acerto.imprimir();
        }

        System.out.println("\n========== LEILÃO DE ELETRÔNICO ==========");
        System.out.println(loteEletronico.darLance(carlos, new BigDecimal("5200.00")));
        System.out.println(loteEletronico.darLance(ana, new BigDecimal("5500.00")));
        System.out.println(loteEletronico.darLance(carlos, new BigDecimal("5800.00")));
        System.out.println("\n========== ENCERRAMENTO DO LOTE ELETRÔNICO ==========");

        loteEletronico.encerrar();

        ResultadoLeilao resultadoEletronico = new ResultadoLeilao(loteEletronico);

        if (!resultadoEletronico.isVendido()) {
            System.out.println("Não haverá venda nem acerto financeiro para este lote.");
        }

        System.out.println("\n========== RESUMO DA AUDITORIA ==========");
        System.out.println("Quantidade de lances registrados: " + auditor.getRegistros().size());
        System.out.println("=========================================");
    }
}
