public class MaquinaVirtualAzure implements iMaquinaVirtual {
    private final String id;

    public MaquinaVirtualAzure(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public double getCustoPorHora() {
        return 0.12;
    }
}
