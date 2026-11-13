public class Participante implements iInteressado {
    private String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println(nome + " foi notificado: o lote " + lote.getNome() + " recebeu um novo lance de R$ " + lance.getValor());
    }
}
