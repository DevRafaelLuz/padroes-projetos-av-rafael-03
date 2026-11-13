import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProvisionadorAmbientes {
    private static final int HORAS_POR_MES = 730;

    private final iFactoryRecursosNuvem fabrica;

    public ProvisionadorAmbientes(iFactoryRecursosNuvem fabrica) {
        if (fabrica == null) {
            throw new IllegalArgumentException("A fábrica não pode ser nula.");
        }
        this.fabrica = fabrica;
    }

    public Optional<Ambiente> provisao(SolicitacaoAmbiente solicitacao) {
        if (solicitacao == null) {
            throw new IllegalArgumentException("A solicitação não pode ser nula.");
        }

        String ambiente = solicitacao.getAmbiente();
        String provedor = solicitacao.getProvedor().toString();

        try {
            if (fabrica.getProvedor() != solicitacao.getProvedor()) {
                recusaRegistro(ambiente, provedor, "A fábrica não corresponde ao provedor solicitado.");
                return Optional.empty();
            }

            Configuracao configuracao = Configuracao.getInstance();
            double orcamento = configuracao.getOrcamentoMensal();
            String regiao = configuracao.getRegiao();

            BigDecimal custoMaquinaVirtual = BigDecimal.valueOf(fabrica.getCustoPorHoraDaMaquinaVirtual());
            BigDecimal custoArmazenamento = BigDecimal.valueOf(fabrica.getCustoMensalPorGbArmazenamento());
            BigDecimal custoFila = BigDecimal.valueOf(fabrica.getCustoMensalFila());
            BigDecimal custoMensal = custoMaquinaVirtual.multiply(BigDecimal.valueOf(solicitacao.getQuantidadeMaquinaVirtual())).multiply(BigDecimal.valueOf(HORAS_POR_MES));

            custoMensal = custoMensal.add(custoArmazenamento.multiply(BigDecimal.valueOf(solicitacao.getArmazenamentoGb())));
            custoMensal = custoMensal.add(custoFila);
            custoMensal = custoMensal.setScale(2, RoundingMode.HALF_UP);

            if (custoMensal.compareTo(BigDecimal.valueOf(orcamento)) > 0) {
                recusaRegistro(ambiente, provedor, "Custo mensal estimado de R$ " + custoMensal + " ultrapassa o orçamento de R$ " + String.format("%.2f", orcamento) + ".");

                System.out.println("\nProvisionamento recusado: " + ambiente);

                return Optional.empty();
            }

            List<iMaquinaVirtual> maquinas = new ArrayList<>();

            for (int i = 0; i < solicitacao.getQuantidadeMaquinaVirtual(); i++) {
                maquinas.add(fabrica.criarMaquinaVirtual());
            }

            iArmazenamento armazenamento = fabrica.criarArmazenamento();
            iFilaMensagem fila = fabrica.criarFila();

            Ambiente novoAmbiente = new Ambiente(
                ambiente,
                solicitacao.getProvedor(),
                regiao,
                maquinas,
                armazenamento,
                fila,
                custoMensal.doubleValue()
            );

            exibirSumario(novoAmbiente);

            AuditLog.getInstance().registrar(ambiente, provedor, "ACEITO", "Provisionamento realizado com sucesso.");

            return Optional.of(novoAmbiente);
        } catch (RuntimeException e) {
            recusaRegistro(ambiente, provedor, "Falha durante o provisionamento: " + e.getMessage());
            return Optional.empty();
        }
    }

    private void recusaRegistro(String ambiente, String provedor, String motivo) {
        AuditLog.getInstance().registrar(ambiente, provedor, "RECUSADO", motivo);
    }

    private void exibirSumario(Ambiente ambiente) {
        System.out.println("\n========== AMBIENTE PROVISIONADO ==========");
        System.out.println("Nome: " + ambiente.getNome());
        System.out.println("Provedor: " + ambiente.getProvedor());
        System.out.println("Região: " + ambiente.getRegiao());
        System.out.println("Máquinas virtuais:");
        for (iMaquinaVirtual maquinaVirtual : ambiente.getMaquinaVirtual()) {
            System.out.println("- " + maquinaVirtual.getId());
        }
        System.out.println("Armazenamento: " + ambiente.getArmazenamento().getId());
        System.out.println("Fila de mensagens: " + ambiente.getFila().getId());
        System.out.printf("Custo mensal estimado: R$ %.2f%n", ambiente.getCustoMensal());
    }
}
