import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String nomeArquivo = "arquivo//VerificaSalarioTotal.txt";
        //String nomeArquivo = "arquivo//calcularMediaEVerificarAprovacao.txt";
        try {
            // Lê o conteúdo do arquivo
            String codigo = new String(Files.readAllBytes(Paths.get(nomeArquivo)));

            AnalisadorLexico analisador = new AnalisadorLexico();
            List<Token> tokens = analisador.analisarCodigo(codigo);

            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}
