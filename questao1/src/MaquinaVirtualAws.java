public class MaquinaVirtualAws implements iMaquinaVirtual {
    private final String id;

    public MaquinaVirtualAws(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public double getCustoPorHora() {
        return 0.10;
    }
}
