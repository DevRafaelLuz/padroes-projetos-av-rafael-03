import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuracao {
    private static Configuracao instancia;
    
    private final Properties properties;
    
    public static Configuracao getInstance() {
        if (instancia == null) {
            instancia = new Configuracao();
        }
        return instancia;
    }

    private Configuracao() {
        properties = new Properties();

        try (InputStream input = Configuracao.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IllegalStateException("Arquivo config.properties não encontrado.");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException("Erro ao carregar configurações.", e);
        }

        if (properties.getProperty("region") == null || properties.getProperty("monthly.budget") == null) {
            throw new IllegalStateException("Configurações obrigatórias ausentes.");
        }
    }

    public String getRegiao() {
        return properties.getProperty("region");
    }

    public double getOrcamentoMensal() {
        return Double.parseDouble(properties.getProperty("monthly.budget"));
    }
}
