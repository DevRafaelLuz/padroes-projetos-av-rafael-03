public class PainelLeiloeiro implements iInteressado {
    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println("\n[Painel do Leiloeiro]");
        System.out.println("Lote: " + lote.getNome());
        System.out.println("Maior lance: R$ " + lote.getLanceAtual());
        System.out.println("Total de lances: " + lote.getQuantidadeLances());
    }
}
