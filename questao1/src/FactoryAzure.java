import java.util.concurrent.atomic.AtomicInteger;

public class FactoryAzure implements iFactoryRecursosNuvem {
    private final AtomicInteger contadorMaquinaVirtual = new AtomicInteger();
    private final AtomicInteger contadorArmazenamento = new AtomicInteger();
    private final AtomicInteger contadorFila = new AtomicInteger();

    private String id;

    @Override
    public iMaquinaVirtual criarMaquinaVirtual() {
        id = "vm-" + contadorMaquinaVirtual.incrementAndGet();
        return new MaquinaVirtualAzure(id);
    }

    @Override
    public iArmazenamento criarArmazenamento() {
        id = "blob://" + contadorArmazenamento.incrementAndGet();
        return new ArmazenamentoAzure(id);
    }
    
    @Override
    public iFilaMensagem criarFila() {
        id = "sb-" + contadorFila.incrementAndGet();
        return new FilaAzure(id);
    }

    @Override
    public String getProvedor() {
        return "AZURE";
    }

    @Override
    public double getCustoPorHoraDaMaquinaVirtual() {
        return 0.12;
    }

    @Override
    public double getCustoMensalPorGbArmazenamento() {
        return 0.02;
    }

    @Override
    public double getCustoMensalFila() {
        return 0.50;
    }
}
