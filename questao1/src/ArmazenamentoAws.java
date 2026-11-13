public class ArmazenamentoAws implements iArmazenamento {
    private final String id;

    public ArmazenamentoAws(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public double getCustoMensalPorGb() {
        return 0.023;
    }
}
