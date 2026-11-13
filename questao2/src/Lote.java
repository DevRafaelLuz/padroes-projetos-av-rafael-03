import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Lote {
    private String nome;
    private iCategoria categoria;

    private BigDecimal precoInicial;
    private BigDecimal precoReserva;
    private BigDecimal lanceAtual;

    private List<Lance> lances;
    private List<iInteressado> interessados;

    private boolean aberto;
    private boolean vendido;
    private BigDecimal valorVenda;
    private BigDecimal comissaoCobrada;

    private iStrategyComissao strategyComissao;

    public Lote(String nome, iCategoria categoria, BigDecimal precoInicial, BigDecimal precoReserva, iStrategyComissao strategyComissao) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.lanceAtual = precoInicial;
        this.strategyComissao = strategyComissao;
        this.lances = new ArrayList<>();
        this.interessados = new ArrayList<>();
        this.aberto = true;
        this.vendido = false;
        this.valorVenda = BigDecimal.ZERO;
        this.comissaoCobrada = BigDecimal.ZERO;
    }

    public String getNome() {
        return nome;
    }

    public iCategoria getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecoInicial() {
        return precoInicial;
    }

    public BigDecimal getPrecoReserva() {
        return precoReserva;
    }

    public BigDecimal getLanceAtual() {
        return lanceAtual;
    }

    public List<Lance> getLances() {
        return new ArrayList<>(lances);
    }

    public int getQuantidadeLances() {
        return lances.size();
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isVendido() {
        return vendido;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public BigDecimal getComissaoCobrada() {
        return comissaoCobrada;
    }

    public void adicionarInteressado(iInteressado interessado) {
        if (!interessados.contains(interessado)) {
            interessados.add(interessado);
        }
    }

    public void removerInteressado(iInteressado interessado) {
        interessados.remove(interessado);
    }

    public String darLance(Participante participante, BigDecimal valor) {
        if (!aberto) {
            return "Lance recusado: o lote está encerrado.";
        }

        if (participante == null || valor == null || valor.signum() <= 0) {
            return "Lance recusado: participante ou valor inválido.";
        }

        BigDecimal incrementoMinimo = lanceAtual.multiply(new BigDecimal("1.05"));

        if (valor.compareTo(incrementoMinimo) < 0) {
            return "Lance recusado: o valor mínimo permitido é R$ " + incrementoMinimo.setScale(2,java.math.RoundingMode.CEILING);
        }

        Lance lance = new Lance(participante, valor);

        lances.add(lance);

        lanceAtual = valor;

        notificarInteressados(lance);

        return "Lance aceito para o lote " + nome + "!";
    }

    private void notificarInteressados(Lance lance) {
        for (iInteressado interessado : interessados) {
            if (interessado != lance.getParticipante()) {
                interessado.atualizar(this, lance);
            }
        }
    }
    
    public void encerrar() {
        if (!aberto) {
            System.out.println("O lote já está encerrado.");
            return;
        }

        aberto = false;

        if (lances.isEmpty()) {
            System.out.println("Lote " + nome + " não vendido: nenhum lance recebido.");
            return;
        }

        if (lanceAtual.compareTo(precoReserva) >= 0) {
            vendido = true;
            valorVenda = lanceAtual;

            comissaoCobrada = calcularComissao(valorVenda).setScale(2, java.math.RoundingMode.HALF_UP);

            System.out.println("Lote " + nome + " vendido por R$ " + valorVenda);
            System.out.println("Comissão: R$ " + comissaoCobrada);
        } else {
            System.out.println("Lote " + nome + " não vendido: preço de reserva não atingido.");
        }
    }

    public void alterarComissao(iStrategyComissao strategy) {
        if (!aberto) {
            throw new IllegalStateException("Não é possível alterar a comissão de um lote encerrado.");
        }

        if (strategy == null) {
            throw new IllegalArgumentException("A estratégia de comissão não pode ser nula.");
        }
        this.strategyComissao = strategy;
    }

    public BigDecimal calcularComissao(BigDecimal valorVenda) {
        if (strategyComissao == null) {
            throw new IllegalStateException("Nenhuma estratégia de comissão foi definida.");
        }
        return strategyComissao.calcular(valorVenda);
    }
}
