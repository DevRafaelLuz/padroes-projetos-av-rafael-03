public class SolicitacaoAmbiente {
    private final String ambiente;
    private final String provedor;
    private final int quantidadeMaquinaVirtual;
    private final int armazenamentoGb;

    public SolicitacaoAmbiente(String ambiente, String provedor, int quantidadeMaquinaVirtual, int armazenamentoGb) {
        if (ambiente == null || ambiente.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do ambiente é obrigatório.");
        }

        if (provedor == null) {
            throw new IllegalArgumentException("O provedor é obrigatório.");
        }

        if (quantidadeMaquinaVirtual <= 0 || armazenamentoGb <= 0) {
            throw new IllegalArgumentException("As quantidades devem ser maiores que zero.");
        }

        this.ambiente = ambiente;
        this.provedor = provedor;
        this.quantidadeMaquinaVirtual = quantidadeMaquinaVirtual;
        this.armazenamentoGb = armazenamentoGb;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public String getProvedor() {
        return provedor;
    }

    public int getQuantidadeMaquinaVirtual() {
        return quantidadeMaquinaVirtual;
    }

    public int getArmazenamentoGb() {
        return armazenamentoGb;
    }
}
