public class Main {
    public static void main(String[] args) throws Exception {
        FactoryAws fabricaAws = new FactoryAws();
        FactoryAzure fabricaAzure = new FactoryAzure();

        SolicitacaoAmbiente solicitacaoA = new SolicitacaoAmbiente("Ambiente-A", "AWS", 1, 50);
        SolicitacaoAmbiente solicitacaoB = new SolicitacaoAmbiente("Ambiente-B", "AWS", 1, 20);
        SolicitacaoAmbiente solicitacaoC = new SolicitacaoAmbiente("Ambiente-C", "AWS", 1, 30);
        SolicitacaoAmbiente solicitacaoD = new SolicitacaoAmbiente("Ambiente-D", "AWS", 10, 500);

        ProvisionadorAmbientes provisionadorA = new ProvisionadorAmbientes(selecionarFabrica(solicitacaoA.getProvedor(), fabricaAws, fabricaAzure));
        ProvisionadorAmbientes provisionadorB = new ProvisionadorAmbientes(selecionarFabrica(solicitacaoB.getProvedor(), fabricaAws, fabricaAzure));
        ProvisionadorAmbientes provisionadorC = new ProvisionadorAmbientes(selecionarFabrica(solicitacaoC.getProvedor(), fabricaAws, fabricaAzure));
        ProvisionadorAmbientes provisionadorD = new ProvisionadorAmbientes(selecionarFabrica(solicitacaoD.getProvedor(), fabricaAws, fabricaAzure));

        provisionadorA.provisao(solicitacaoA);
        provisionadorB.provisao(solicitacaoB);
        provisionadorC.provisao(solicitacaoC);
        provisionadorD.provisao(solicitacaoD);

        AuditLog.getInstance().exibirHistorico();
    }

    private static iFactoryRecursosNuvem selecionarFabrica(String provedor, FactoryAws fabricaAws, FactoryAzure fabricaAzure) {
        switch (provedor) {
            case "AWS":
                return fabricaAws;

            case "AZURE":
                return fabricaAzure;

            default:
                throw new IllegalArgumentException("Provedor não suportado: " + provedor);
        }
    }
}
