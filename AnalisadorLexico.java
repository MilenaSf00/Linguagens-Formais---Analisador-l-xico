
import java.util.ArrayList;
import java.util.List;

import Tokens.TokenBoolean;
import Tokens.TokenComentario;
import Tokens.TokenCondicional;
import Tokens.TokenDelimitador;
import Tokens.TokenLeitura;
import Tokens.TokenNome;
import Tokens.TokenOperador;
import Tokens.TokenPalavraReservadas;
import Tokens.TokenRepeticao;
import Tokens.TokenVariaveis;

public class AnalisadorLexico {
    private VerificandoToken verificador;

    public AnalisadorLexico() {
        this.verificador = new VerificandoToken();
    }



    /*private boolean isInsideSpecificFunction(String[] palavras, int coluna) {
        // Verifique se a palavra está dentro da função console.log()
        // Verifique os elementos anteriores e posteriores para garantir que a função esteja completa
        if (coluna > 0 && coluna < palavras.length - 1) {
            String palavraAnterior = palavras[coluna - 1];
            String palavraPosterior = palavras[coluna + 1];
            if (palavraAnterior.equals("console") && palavraPosterior.equals("log")) {
                return true;
            }
        }
        return false;
    }*/

    // Método para analisar o código e retornar a lista de tokens encontrados
    public List<Token> analisarCodigo(String codigo) {
        List<Token> tokens = new ArrayList<>();
        String[] linhas = codigo.split("\\n");
    
        // Itera sobre as linhas do código
        for (int linha = 0; linha < linhas.length; linha++) {
            String[] palavras = linhas[linha].split("\\s+|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=,)|(?=,)|(?<=;)|(?=;)|(?<=\")|(?=\")|(?<=')|(?=')|(?<=:)|(?=:)|(?<!>)>(?!=)|(?<=\\.)|(?=\\.)");

            // Itera sobre as palavras encontradas na linha
            for (int coluna = 0; coluna < palavras.length; coluna++) {
                String palavra = palavras[coluna];

                // Verifica se a palavra é um número
            if (!palavra.isEmpty() && verificador.isNumber(palavra.charAt(0))) {
                    
                    // Verifica se é um número real
                    if (verificador.isNReal(palavra)) {
                        tokens.add(new Token(TokenNome.NREAL, palavra, linha + 1, coluna + 1));
                    
                    // Verifica se é um número inteiro
                    } else if (verificador.isNInt(palavra)) {
                        tokens.add(new Token(TokenNome.NINT, palavra, linha + 1, coluna + 1));
                    }

                    // Verifica se a palavra é uma string delimitada por aspas
                } else if (!palavra.isEmpty() && verificador.isNString(palavra.charAt(0))) {
                        tokens.add(new Token(TokenNome.NSTRING, palavra, linha + 1, coluna + 1));  } 
                
                    // Verifica se a palavra é um operador
                else if (!palavra.isEmpty() && verificador.isOperador(palavra))  {
                    TokenOperador operador = null;
                    // Mapeia a palavra para o enum correspondente ao operador
                    switch (palavra) {
                        case "+":
                            operador = TokenOperador.ADICAO;
                            break;
                        case "-":
                            operador = TokenOperador.SUBTRACAO;
                            break;
                        case "*":
                            operador = TokenOperador.MULTIPLICACAO;
                            break;
                        case "=":
                            operador = TokenOperador.ATRIBUICAO;
                            break;
                        case "%":
                            operador = TokenOperador.PORCENTAGEM;
                            break;
                        case "&&":
                            operador = TokenOperador.E_LOGICO;
                            break;
                        case "||":
                            operador = TokenOperador.OU_LOGICO;
                        case "!":
                            operador = TokenOperador.NEGACAO_LOGICA;
                            break;
                        case ">":
                            operador = TokenOperador.MAIOR;
                            break;
                        case "<":
                            operador = TokenOperador.MENOR;
                            break;
                    case ">=":
                            operador = TokenOperador.MAIOR_IGUAL;
                            break;
                    case "<=":
                        operador = TokenOperador.MENOR_IGUAL;
                        break;
                    case "==":
                        operador = TokenOperador.IGUAL;
                        break;
                    case "/":
                        operador = TokenOperador.DIVISOR;
                        break;
                }
                // Se um operador válido for identificado, adiciona o token correspondente à lista de tokens
                    if (operador != null) {
                        tokens.add(new Token(TokenNome.OP, operador.name(), linha + 1, coluna + 1));
                    }
                } 
                // Verifica se a palavra é um comentário
                else if (verificador.isComentario(palavra)) {
                    TokenComentario comentario = null;
                    // Mapeia a palavra para o enum correspondente ao tipo de comentário
                    switch (palavra) {
                        case "//":
                            comentario = TokenComentario.BARRA_DE_COMENTARIO;
                            break;
                        case "/*":
                            comentario = TokenComentario.INICIO_COMENTARIO_BLOCO;
                            break;
                        case "*/":
                            comentario = TokenComentario.FIM_COMENTARIO_BLOCO;
                            break;
                    }
                // Se um tipo de comentário válido for identificado, adiciona o token correspondente à lista de tokens
                    if (comentario != null) {
                        tokens.add(new Token(TokenNome.COMENTARIO, comentario.name(), linha + 1, coluna + 1));
                    }
                } 
                // Verifica se a palavra é uma função de escrita (prompt())
                else if (verificador.isEscrita(palavra)) {
                    tokens.add(new Token(TokenNome.ESCRITA, palavra, linha + 1, coluna + 1));
                } 
                // Verifica se a palavra é uma função de leitura (console.log())
               else if (palavra.equals("console") && coluna + 1 < palavras.length && palavras[coluna + 1].equals(".")) {
    if (coluna + 2 < palavras.length && palavras[coluna + 2].equals("log")) {
        tokens.add(new Token(TokenNome.LEITURA, TokenLeitura.CONSOLE_LOG.getValor(), linha + 1, coluna + 1));
        coluna += 2; // Avança para a próxima palavra após "log"
    }
}

               

                // Verifica se a palavra é um delimitador
                else if (!palavra.isEmpty() && verificador.isDelimitador(palavra.charAt(0))){TokenDelimitador delimitador = null;
                // Mapeia a palavra para o enum correspondente ao delimitador
                    switch (palavra) {
                        case "{":
                            delimitador = TokenDelimitador.ABRE_CHAVES;
                            break;
                        case "}":
                            delimitador = TokenDelimitador.FECHA_CHAVES;
                            break;
                        case "(":
                            delimitador = TokenDelimitador.ABRE_PARENTESES;
                            break;
                        case ")":
                            delimitador = TokenDelimitador.FECHA_PARENTESES;
                            break;
                        case "[":
                            delimitador = TokenDelimitador.ABRE_COLCHETES;
                            break;
                        case "]":
                            delimitador = TokenDelimitador.FECHA_COLCHETES;
                            break;
                        case ",":
                            delimitador = TokenDelimitador.VIRGULA;
                            break;
                        case ";":
                            delimitador = TokenDelimitador.PONTO_E_VIRGULA;
                            break;
                        case ".":
                            delimitador = TokenDelimitador.PONTO;
                            break;
                        case ":":
                            delimitador = TokenDelimitador.DOIS_PONTOS;
                            break;
                        
                    }
                    // Se um delimitador válido for identificado, adiciona o token correspondente à lista de tokens
                    if (delimitador != null) {
                        tokens.add(new Token(TokenNome.DELIMITADOR, delimitador.name(), linha + 1, coluna + 1));
                    }
                } 
                // Verifica se a palavra é uma estrutura condicional
                else if (verificador.isCondicional(palavra)) {
                    TokenCondicional condicional = TokenCondicional.valueOf(palavra.toUpperCase());
                    tokens.add(new Token(TokenNome.CONDICIONAL, condicional.name(), linha + 1, coluna + 1));
                } 
                
                // Verifica se a palavra é uma palavra relacionada a variáveis
                else if (verificador.isVariaveis(palavra)) {
                    TokenVariaveis variavel = TokenVariaveis.valueOf(palavra.toUpperCase());
                    tokens.add(new Token(TokenNome.VARIAVEIS, variavel.name(), linha + 1, coluna + 1));
                } 


                // Verifica se a palavra é uma estrutura de repetição
                else if (palavra.equals("do") && coluna + 1 < palavras.length && palavras[coluna + 1].trim().equals("{")) {
                    tokens.add(new Token(TokenNome.REPETICAO, TokenRepeticao.DO_WHILE.name(), linha + 1, coluna + 1));
                    coluna++; // Avança para a próxima palavra para evitar repetição
                }

                // Verifica se há um fechamento de chaves e se a próxima palavra é "while"
                if (palavra.equals("}") && coluna + 1 < palavras.length && palavras[coluna + 1].trim().equals("while")) {
                    tokens.add(new Token(TokenNome.REPETICAO, TokenRepeticao.DO_WHILE.name(), linha + 1, coluna + 1));
                    coluna++; // Avança para a próxima palavra para evitar repetição
                }

                






                // Verifica se a palavra é um valor booleano
                else if (verificador.isBoolean(palavra)) {
                // Remove o ponto e vírgula do final da palavra, se houver
                    if (palavra.endsWith(";")) {
                        palavra = palavra.substring(0, palavra.length() - 1);
                    }
                    TokenBoolean valorBooleano = (palavra.equals("true")) ? TokenBoolean.TRUE : TokenBoolean.FALSE;
                    tokens.add(new Token(TokenNome.BOOLEAN, valorBooleano.name(), linha + 1, coluna + 1));

                } 
                // Verifica se a palavra é uma palavra reservada
                else if (verificador.isPalavraReservada(palavra)) {
                    TokenPalavraReservadas palavraReservada = TokenPalavraReservadas.valueOf(palavra.toUpperCase());
                    tokens.add(new Token(TokenNome.PALAVRA_RESERVADA, palavraReservada.name(), linha + 1, coluna + 1));
                } 
                // Se nenhuma das condições anteriores for atendida, assume-se que a palavra é um identificador
                else {
                    tokens.add(new Token(TokenNome.ID, palavra, linha + 1, coluna + 1));
                }
            }
            
                // Verificação da estrutura de repetição "do while"
                /* if (linhas[linha].startsWith("do") && linha + 1 < linhas.length && linhas[linha + 1].trim().startsWith("{")) {
                        tokens.add(new Token(TokenNome.REPETICAO, TokenRepeticao.DO_WHILE.name(), linha + 1, 1));
                    }*/

        }
        return tokens;
    }
}