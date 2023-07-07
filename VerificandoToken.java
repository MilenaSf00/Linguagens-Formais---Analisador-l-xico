import Tokens.TokenCondicional;
import Tokens.TokenEscrita;
import Tokens.TokenLeitura;
import Tokens.TokenNString;
import Tokens.TokenPalavraReservadas;
import Tokens.TokenRepeticao;
import Tokens.TokenVariaveis;


//O método startsWith verifica se uma string começa com determinado prefixo. Por exemplo, codigo.startsWith("//") verifica se a string codigo começa com //. Se sim, significa que o trecho de código é um comentário de linha.
//O método matches verifica se uma string corresponde a um padrão regular expressão. 
// Essa classe  contém métodos para verificar diferentes tipos de tokens em um código-fonte.

public class VerificandoToken {
      // Verifica se o caractere é um número
    public boolean isNumber(char x) {
        return Character.isDigit(x);
    }

    // Verifica se o trecho de código corresponde a um número real
    public boolean isNReal(String codigo) {
        return codigo.matches("\\d+\\.\\d*");
    }

    // Verifica se o trecho de código corresponde a um número inteiro
    public boolean isNInt(String codigo) {
        return codigo.matches("\\d+");
    }

    // Verifica se o trecho de código corresponde a uma string delimitada por aspas
    public boolean isNString(char codigo) {
        return codigo == '"' || codigo == '\'';
    /*return codigo == TokenNString.ASPAS_SIMPLES.getValor() ||
           codigo == TokenNString.ASPAS_DUPLAS.getValor();*/
    }
    public boolean isOperador(String palavra) {
        return palavra.equals("+") || palavra.equals("-") || palavra.equals("*") || palavra.equals("=") ||
            palavra.equals("%") || palavra.equals("&&") || palavra.equals("||") || palavra.equals("!") ||
            palavra.equals(">") || palavra.equals("<") || palavra.equals(">=") || palavra.equals("<=") ||
            palavra.equals("==") ||  palavra.equals("/");
    }

    // Verifica se o trecho de código corresponde a um comentário
    public boolean isComentario(String codigo) {
        return codigo.startsWith("//") || codigo.startsWith("/*") || codigo.endsWith("*/");
    }

    // Verifica se o trecho de código corresponde à função prompt()
    public boolean isEscrita(String codigo) {
       // return codigo.startsWith("prompt(") && codigo.endsWith(")");
    
    for (TokenEscrita token : TokenEscrita.values()) {
        if (codigo.equals(token.getValor())) {
            return true;
        }
    }
        return false;
    }

    // Verifica se o trecho de código corresponde à função console.log()
    public boolean isLeitura(String codigo) {
       return codigo.startsWith(TokenLeitura.CONSOLE_LOG.getValor());
    }

    // Verifica se o caractere é um delimitador
    public boolean isDelimitador(char x) {
        return x == '{' || x == '}' || x == '(' || x == ')' ||
            x == '[' || x == ']' || x == ',' || x == ';' ||
            x == '.' || x == ':';}

    // Verifica se a palavra é uma estrutura condicional
    public boolean isCondicional(String palavra) {
        try {
            TokenCondicional.valueOf(palavra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Verifica se a palavra é uma palavra relacionada a variáveis
    public boolean isVariaveis(String palavra) {
        try {
            TokenVariaveis.valueOf(palavra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Verifica se a palavra é uma estrutura de repetição
    public boolean isRepeticao(String palavra) {
        try {
            TokenRepeticao.valueOf(palavra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Verifica se a palavra é um valor booleano
    public boolean isBoolean(String palavra) {
        return palavra.equals("true") || palavra.equals("false");
    }

    // Verifica se a palavra é uma palavra reservada
    public boolean isPalavraReservada(String palavra) {
        try {
            TokenPalavraReservadas.valueOf(palavra.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
