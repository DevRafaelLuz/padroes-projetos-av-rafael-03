import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ambiente {
    private final String nome;
    private final String provedor;
    private final String regiao;
    private final List<iMaquinaVirtual> maquinaVirtual;
    private final iArmazenamento armazenamento;
    private final iFilaMensagem fila;
    private final double custoMensal;

    public Ambiente(String nome, String provedor, String regiao, List<iMaquinaVirtual> maquinaVirtual, iArmazenamento armazenamento, iFilaMensagem fila, double custoMensal) {
        this.nome = nome;
        this.provedor = provedor;
        this.regiao = regiao;
        this.maquinaVirtual = new ArrayList<>(maquinaVirtual);
        this.armazenamento = armazenamento;
        this.fila = fila;
        this.custoMensal = custoMensal;
    }

    public String getNome() {
        return this.nome;
    }

    public String getProvedor() {
        return this.provedor;
    }

    public String getRegiao() {
        return this.regiao;
    }

    public List<iMaquinaVirtual> getMaquinaVirtual() {
        return Collections.unmodifiableList(this.maquinaVirtual);
    }

    public iArmazenamento getArmazenamento() {
        return this.armazenamento;
    }

    public iFilaMensagem getFila() {
        return this.fila;
    }

    public double getCustoMensal() {
        return this.custoMensal;
    }
}
