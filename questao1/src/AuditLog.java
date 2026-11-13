import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuditLog {
    private static AuditLog instancia;
    
    private final List<AuditRecord> gravacoes = new ArrayList<>();

    private AuditLog() { }

    public static AuditLog getInstance() {
        if (instancia == null) {
            instancia = new AuditLog();
        }
        return instancia;
    }

    public void registrar(String ambiente, String provedor, String resultado, String motivo) {
        AuditRecord gravacao = new AuditRecord(LocalDateTime.now(), ambiente, provedor, resultado, motivo);

        gravacoes.add(gravacao);
    }

    public List<AuditRecord> getGravacoes() {
        return Collections.unmodifiableList(new ArrayList<>(gravacoes));
    }

    public void exibirHistorico() {
        System.out.println("\n========== HISTÓRICO DE AUDITORIA ==========");

        if (gravacoes.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
            return;
        }

        for (AuditRecord gravacao : gravacoes) {
            System.out.println(gravacao);
        }

        System.out.println("============================================");
    }
}
