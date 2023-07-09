import java.util.ArrayList;
import java.util.List;
import Tokens.TokenBoolean;
import Tokens.TokenComentario;
import Tokens.TokenCondicional;
import Tokens.TokenDelimitador;
import Tokens.TokenNome;
import Tokens.TokenOperador;
import Tokens.TokenRepeticao;
import Tokens.TokenVariaveis;

public class AnalisadorLexico {
    private VerificandoToken verificador;

    public AnalisadorLexico() {
        this.verificador = new VerificandoToken();
    }

    public List<Token> le_token(String codigo) {
        List<Token> tokens = new ArrayList<>();
        String[] linhas = codigo.split("\\n");

        for (int linha = 0; linha < linhas.length; linha++) {
            String[] palavras = separarPalavras(linhas[linha]);

            for (int coluna = 0; coluna < palavras.length; coluna++) {
                String palavra = palavras[coluna];

                if (!palavra.isEmpty()) {
                    // Verifica se a palavra é uma estrutura de repetição "do-while"
                    if (palavra.equalsIgnoreCase("do")) {
                        // Verifica se o próximo token é uma abertura de chaves
                        if (coluna + 1 < palavras.length && palavras[coluna + 1].trim().equals("{")) {
                            tokens.add(new Token(TokenNome.REPETICAO, TokenRepeticao.DO_WHILE.name(), linha + 1,
                                    coluna + 1));
                            // Verifica se o token anterior é uma abertura de chaves
                            if (tokens.size() > 1 && tokens.get(tokens.size() - 1).getTipo() == TokenNome.DELIMITADOR &&
                                    tokens.get(tokens.size() - 1).getToken()
                                            .equals(TokenDelimitador.ABRE_CHAVES.name())) {
                                // Pula a adição do token ABRE_CHAVES
                                coluna++;
                                continue;
                            }
                            tokens.add(new Token(TokenNome.DELIMITADOR, TokenDelimitador.ABRE_CHAVES.name(), linha + 1,
                                    coluna + 2));
                            coluna++; // Avança para a próxima palavra após a abertura de chaves
                            continue; // Pula para a próxima palavra
                        }
                    }
                    // Verifica se a palavra é o fechamento de chaves seguido de "while"
                    if (palavra.equals("}") && coluna + 1 < palavras.length
                            && palavras[coluna + 1].equalsIgnoreCase("while")) {
                        tokens.add(new Token(TokenNome.DELIMITADOR, TokenDelimitador.FECHA_CHAVES.name(), linha + 1,
                                coluna + 1));
                        tokens.add(
                                new Token(TokenNome.REPETICAO, TokenRepeticao.DO_WHILE.name(), linha + 1, coluna + 1));

                        coluna++; // Avança para a próxima palavra após a palavra "while"
                        continue; // Pula para a próxima palavra
                    }
                    // Verifica se a palavra é um token de repetição (while, for, etc.)
                    try {
                        TokenRepeticao repeticao = TokenRepeticao.valueOf(palavra.toUpperCase());
                        tokens.add(new Token(TokenNome.REPETICAO, repeticao.name(), linha + 1, coluna + 1));
                    } catch (IllegalArgumentException e) {
                        // Palavra não é um token de repetição
                    }
                    // Restante das condições para identificar os outros tipos de token
                    if (verificador.isNumber(palavra.charAt(0))) {
                        if (verificador.isNReal(palavra)) {
                            tokens.add(new Token(TokenNome.NREAL, palavra, linha + 1, coluna + 1));
                        } else if (verificador.isNInt(palavra)) {
                            tokens.add(new Token(TokenNome.NINT, palavra, linha + 1, coluna + 1));
                        }
                    } else if (palavra.startsWith("\"") || palavra.startsWith("'")) {
                        // Verifica se a palavra é uma string delimitada por aspas simples ou aspas
                        // duplas
                        StringBuilder fraseCompleta = new StringBuilder(palavra);
                        coluna++; // Avança para a próxima palavra

                        // Concatena palavras subsequentes até encontrar a palavra que termina com o
                        // mesmo tipo de aspas
                        while (coluna < palavras.length && !palavras[coluna].endsWith(palavra.substring(0, 1))) {
                            fraseCompleta.append(" ").append(palavras[coluna]);
                            coluna++; // Avança para a próxima palavra
                        }
                        if (coluna < palavras.length && palavras[coluna].endsWith(palavra.substring(0, 1))) {
                            fraseCompleta.append(" ").append(palavras[coluna]);
                            String nstring = fraseCompleta.toString();
                            tokens.add(new Token(TokenNome.NSTRING, nstring, linha + 1, coluna + 1));

                            // Atualiza a palavra com a string completa
                            palavra = nstring;
                        } else {
                            // Caso não seja encontrado o fechamento das aspas, trata como um erro
                            System.out.println("Erro: Aspas não fechadas na linha " + (linha + 1));
                        }
                    } else if (verificador.isOperador(palavra)) {
                        tokens.add(criarTokenOperador(palavra, linha + 1, coluna + 1));

                    } else if (verificador.isComentario(palavra)) {
                        tokens.add(criarTokenComentario(palavra, linha + 1, coluna + 1));

                    } else if (verificador.isEscrita(palavra)) {
                        tokens.add(new Token(TokenNome.ESCRITA, palavra, linha + 1, coluna + 1));

                    } else if (palavra.equals("console") && coluna + 1 < palavras.length
                            && palavras[coluna + 1].equals(".")) {
                        if (coluna + 2 < palavras.length && palavras[coluna + 2].equals("log")) {
                            tokens.add(new Token(TokenNome.LEITURA, "console.log", linha + 1, coluna + 1));
                            coluna += 2; // Avança para a próxima palavra após "log"
                            continue; // Pula para a próxima palavra
                        }
                    } else if (verificador.isDelimitador(palavra.charAt(0))) {
                        tokens.add(criarTokenDelimitador(palavra, linha + 1, coluna + 1));

                    } else if (verificador.isCondicional(palavra)) {
                        tokens.add(criarTokenCondicional(palavra, linha + 1, coluna + 1));

                    } else if (verificador.isVariaveis(palavra)) {
                        tokens.add(criarTokenVariaveis(palavra, linha + 1, coluna + 1));

                    } else if (verificador.isBoolean(palavra)) {
                        tokens.add(criarTokenBoolean(palavra, linha + 1, coluna + 1));

                    } else if (verificador.isPalavraReservada(palavra)) {
                        tokens.add(new Token(TokenNome.PALAVRA_RESERVADA, palavra.toUpperCase(), linha + 1, coluna + 1));
                    } else if (verificador.isIdentificador(palavra)) {
                        // Verifica se é um identificador
                        tokens.add(new Token(TokenNome.ID, palavra, linha + 1, coluna + 1));
                    }
                }
            }
        }
        return tokens;
    }

    private String[] separarPalavras(String linha) {
        return linha.split(
                "\\s+|(?<=\\()|(?=\\()|(?<=\\))|(?=\\))|(?<=,)|(?=,)|(?<=;)|(?=;)|(?<=:)|(?=:)|(?<!>)>(?!=)|(?<=\\.)|(?=\\.)");
    }

    private Token criarTokenOperador(String palavra, int linha, int coluna) {
        TokenOperador operador = null;
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
                break;
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
        if (operador != null) {
            return new Token(TokenNome.OP, operador.name(), linha, coluna);
        } else {
            return null;
        }
    }

    private Token criarTokenComentario(String palavra, int linha, int coluna) {
        TokenComentario comentario = null;
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
        if (comentario != null) {
            return new Token(TokenNome.COMENTARIO, comentario.name(), linha, coluna);
        } else {
            return null;
        }
    }

    private Token criarTokenDelimitador(String palavra, int linha, int coluna) {
        TokenDelimitador delimitador = null;
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
        if (delimitador != null) {
            return new Token(TokenNome.DELIMITADOR, delimitador.name(), linha, coluna);
        } else {
            return null;
        }
    }

    private Token criarTokenCondicional(String palavra, int linha, int coluna) {
        TokenCondicional condicional = TokenCondicional.valueOf(palavra.toUpperCase());
        return new Token(TokenNome.CONDICIONAL, condicional.name(), linha, coluna);
    }

    private Token criarTokenVariaveis(String palavra, int linha, int coluna) {
        TokenVariaveis variavel = TokenVariaveis.valueOf(palavra.toUpperCase());
        return new Token(TokenNome.VARIAVEIS, variavel.name(), linha, coluna);
    }

    private Token criarTokenBoolean(String palavra, int linha, int coluna) {
        boolean valor = palavra.equals("true");
        TokenBoolean valorBooleano = valor ? TokenBoolean.TRUE : TokenBoolean.FALSE;
        return new Token(TokenNome.BOOLEAN, valorBooleano.name(), linha, coluna);
    }
}
