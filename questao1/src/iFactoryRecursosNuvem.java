public interface iFactoryRecursosNuvem {
    iMaquinaVirtual criarMaquinaVirtual();
    iArmazenamento criarArmazenamento();
    iFilaMensagem criarFila();
    String getProvedor();
    double getCustoPorHoraDaMaquinaVirtual();
    double getCustoMensalPorGbArmazenamento();
    double getCustoMensalFila();
}
