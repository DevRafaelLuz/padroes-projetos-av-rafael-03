public class ArmazenamentoAzure implements iArmazenamento {
    private final String id;

    public ArmazenamentoAzure(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public double getCustoMensalPorGb() {
        return 0.02;
    }
}
