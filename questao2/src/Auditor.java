import java.util.ArrayList;
import java.util.List;

public class Auditor implements iInteressado {
    private List<Lance> registros = new ArrayList<>();

    @Override
    public void atualizar(Lote lote, Lance lance) {
        registros.add(lance);

        System.out.println("\n[Auditoria] Lance registrado!");
        System.out.println("Lote: " + lote.getNome());
        System.out.println("Participante: " + lance.getParticipante().getNome());
        System.out.println("Valor: R$ " + lance.getValor());
        System.out.println("Data/Hora: " + lance.getDataHora());
    }

    public List<Lance> getRegistros() {
        return new ArrayList<>(registros);
    }
}
