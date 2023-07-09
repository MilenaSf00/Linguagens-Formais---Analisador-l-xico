import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            String nomeArquivo1 = "arquivo//VerificaSalarioTotal.txt";
            String codigo1 = new String(Files.readAllBytes(Paths.get(nomeArquivo1)));

            AnalisadorLexico analisador1 = new AnalisadorLexico();
            List<Token> tokens1 = analisador1.le_token(codigo1);



            System.out.println("////////////////////////////////////////////  ANALISADOR LÉXICO   ////////////////////////////////////////////");
            System.out.println("******************************************************************************************************************************************************************");
            System.out.println("******************************************************************************************************************************************************************");
            System.out.println("Tokens do Arquivo 1 ( VERIFICA SALÁRIO TOTAL ):");
            System.out.println("| Tipo                 | Token                                                    | Simbolo                                                  | Linha | Coluna");

            for (Token token : tokens1) {
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(token);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo VerificaSalarioTotal: " + e.getMessage());
        }

        try {
            String nomeArquivo2 = "arquivo//calcularMediaEVerificarAprovacao.txt";
            String codigo2 = new String(Files.readAllBytes(Paths.get(nomeArquivo2)));

            AnalisadorLexico analisador2 = new AnalisadorLexico();
            List<Token> tokens2 = analisador2.le_token(codigo2);


            System.out.println("******************************************************************************************************************************************************************");
            System.out.println("******************************************************************************************************************************************************************");
            System.out.println("******************************************************************************************************************************************************************");
            System.out.println("******************************************************************************************************************************************************************");
            System.out.println("******************************************************************************************************************************************************************");
            System.out.println("******************************************************************************************************************************************************************");
            System.out.println("******************************************************************************************************************************************************************");
            System.out.println("Tokens do Arquivo 2 (CALCULAR MÉDIA E VERIFICAR APROVAÇÃO):");
            System.out.println("| Tipo                 | Token                                                    | Simbolo                                                  | Linha | Coluna");

            for (Token token : tokens2) {
                System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(token);
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo calcularMediaEVerificarAprovacao: " + e.getMessage());
        }
    }
}
