public class FilaAws implements iFilaMensagem {
    private final String id;

    public FilaAws(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public double getCustoMensal() {
        return 0.40;
    }
}
