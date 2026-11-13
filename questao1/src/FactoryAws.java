import java.util.concurrent.atomic.AtomicInteger;

public class FactoryAws implements iFactoryRecursosNuvem {
    private final AtomicInteger contadorMaquinaVirtual = new AtomicInteger();
    private final AtomicInteger contadorArmazenamento = new AtomicInteger();
    private final AtomicInteger contadorFila = new AtomicInteger();
    
    private String id;

    @Override
    public iMaquinaVirtual criarMaquinaVirtual() {
        id = "i-" + contadorMaquinaVirtual.incrementAndGet();
        return new MaquinaVirtualAws(id);
    }

    @Override
    public iArmazenamento criarArmazenamento() {
        id = "s3://" + contadorArmazenamento.incrementAndGet();
        return new ArmazenamentoAws(id);
    }

    @Override
    public iFilaMensagem criarFila() {
        id = "sqs-" + contadorFila.incrementAndGet();
        return new FilaAws(id);
    }

    @Override
    public String getProvedor() {
        return "AWS";
    }

    @Override
    public double getCustoPorHoraDaMaquinaVirtual() {
        return 0.10;
    }

    @Override
    public double getCustoMensalPorGbArmazenamento() {
        return 0.023;
    }

    @Override
    public double getCustoMensalFila() {
        return 0.40;
    }
}
