import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class AnalisadorLexico {
    private BufferedReader reader;
    private String linhaAtual;
    private int posicao;

    public AnalisadorLexico(String codigoFonte) {
        reader = new BufferedReader(new StringReader(codigoFonte));
        proximaLinha();
    }

    private void proximaLinha() {
        try {
            linhaAtual = reader.readLine();
            posicao = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private char proximoChar() {
        if (linhaAtual != null && posicao < linhaAtual.length()) {
            char c = linhaAtual.charAt(posicao);
            posicao++;
            return c;
        } else if (linhaAtual != null) {
            proximaLinha();
            return proximoChar();
        }
        return '\0'; // Retorna '\0' se não houver mais caracteres
    }

    private void retroceder() {
        if (posicao > 0) {
            posicao--;
        }
    }

    private Token leToken() {
        StringBuilder lexema = new StringBuilder();
        char c = proximoChar();

        // Ignorar espaços em branco e quebras de linha
        while (Character.isWhitespace(c)) {
            c = proximoChar();
        }

        if (c == '\0') {
            return null; // Retorna null quando não há mais caracteres
        }

        if (Character.isLetter(c) || c == '_') {
            lexema.append(c);
            c = proximoChar();

            // Identificador (Token: ID)
            while (Character.isLetterOrDigit(c) || c == '_') {
                lexema.append(c);
                c = proximoChar();
            }

            retroceder();
            return new Token("ID", lexema.toString());
        }

        if (Character.isDigit(c)) {
            lexema.append(c);
            c = proximoChar();

            // Número inteiro ou número real
            while (Character.isDigit(c) || c == '.') {
                lexema.append(c);
                c = proximoChar();
            }

            retroceder();
            return new Token("NUMERO", lexema.toString());
        }

        if (c == '"') {
            lexema.append(c);
            c = proximoChar();

            // String delimitada por aspas duplas (Token: STRING)
            while (c != '"' && c != '\0') {
                lexema.append(c);
                c = proximoChar();
            }

            if (c == '"') {
                lexema.append(c);
                return new Token("STRING", lexema.toString());
            }
        }

        if (c == '+' || c == '-' || c == '*' || c == '/' || c == '.') {
            lexema.append(c);
            return new Token("OPERADOR", lexema.toString());
        }

        if (c == '=') {
            lexema.append(c);
            return new Token("ATRIBUICAO", lexema.toString());
        }

        if (c == ';') {
            lexema.append(c);
            return new Token("PONTOVIRGULA", lexema.toString());
        }

        if (c == '(') {
            lexema.append(c);
            return new Token("ABREPARENTESES", lexema.toString());
        }

        if (c == ')') {
            lexema.append(c);
            return new Token("FECHAPARENTESES", lexema.toString());
        }

        // Verificar palavras reservadas
        if (c == 'v') {
            // Verificar se é a palavra reservada "var" (Token: VAR)
            String palavraReservada = "var";
            lexema.append(c);

            for (int i = 1; i < palavraReservada.length(); i++) {
                c = proximoChar();

                if (c != palavraReservada.charAt(i)) {
                    return new Token("ID", lexema.toString()); // Tratar como um identificador
                }

                lexema.append(c);
            }

            return new Token("VAR", lexema.toString());
        }

        if (c == 'c') {
            // Verificar se é a função console.log() (Token: ESCRITA)
            String funcaoConsoleLog = "console.log";
            lexema.append(c);

            for (int i = 1; i < funcaoConsoleLog.length(); i++) {
                c = proximoChar();

                if (c != funcaoConsoleLog.charAt(i)) {
                    return new Token("ID", lexema.toString()); // Tratar como um identificador
                }

                lexema.append(c);
            }

            if (c == '(') {
                lexema.append(c);
                c = proximoChar();

                if (c == ')') {
                    lexema.append(c);
                    return new Token("ESCRITA", lexema.toString());
                }
            }
        }

        // Caractere inválido (Token: ERRO)
        lexema.append(c);
        return new Token("ERRO", lexema.toString());
    }

    public void analisar() {
        Token token = leToken();
        while (token != null) {
            System.out.println("Token: " + token.getTipo() + " | Lexema: " + token.getLexema());
            token = leToken();
        }
    }

    private static String lerArquivo(String nomeArquivo) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            builder.append(line).append("\n");
            line = reader.readLine();
        }
        reader.close();
        return builder.toString();
    }

    public static void main(String[] args) {
        String nomeArquivo = "CodigoFonte.txt";
        try {
            String codigoFonte = lerArquivo(nomeArquivo);
            AnalisadorLexico analisador = new AnalisadorLexico(codigoFonte);
            analisador.analisar();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
